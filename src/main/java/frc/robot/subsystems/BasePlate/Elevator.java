// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.BasePlate;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.CANIds;

public class Elevator extends SubsystemBase {
  /** Creates a new Elevator. */

  private TalonFX objElevatorWinch = new TalonFX(CANIds.iElevator, "rio");
  private TalonFXConfiguration objElevConfig = new TalonFXConfiguration();
  private StatusCode objElevatorStatusCode;
  @SuppressWarnings("rawtypes")
  private  StatusSignal objElevatorStatusSignal;
  private MotionMagicVoltage objMMV = new MotionMagicVoltage(0);

  public Elevator() {

     objElevConfig.CurrentLimits.SupplyCurrentLimit = 60.0;
    objElevConfig.CurrentLimits.SupplyCurrentLimitEnable = true;

    objElevConfig.CurrentLimits.StatorCurrentLimit = 120.0;
    objElevConfig.CurrentLimits.StatorCurrentLimitEnable = true;

    objElevConfig.CurrentLimits.SupplyCurrentLowerLimit = 40.0;
    objElevConfig.CurrentLimits.SupplyCurrentLowerTime = 1.0;
    // brake mode
    objElevConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    objElevConfig.OpenLoopRamps.VoltageOpenLoopRampPeriod = 0.2;

    // in init function
    var talonFXConfigs = new TalonFXConfiguration();

    // set slot 0 gains
    var slot0Configs = talonFXConfigs.Slot0;
    slot0Configs.kS = 0.25; // Add 0.25 V output to overcome static friction 
    slot0Configs.kV = 0.12; // A velocity target of 1 rps results in 0.12 V output
    slot0Configs.kA = 0.01; // An acceleration of 1 rps/s requires 0.01 V output
    slot0Configs.kP = 4.8; // A position error of 2.5 rotations results in 12 V output
    slot0Configs.kI = 0; // no output for integrated error
    slot0Configs.kD = 0.1; // A velocity error of 1 rps results in 0.1 V output

    // set Motion Magic settings
    var motionMagicConfigs = talonFXConfigs.MotionMagic;
    motionMagicConfigs.MotionMagicCruiseVelocity = 80; // Target cruise velocity of 80 rps
    motionMagicConfigs.MotionMagicAcceleration = 160; // Target acceleration of 160 rps/s (0.5 seconds)
    motionMagicConfigs.MotionMagicJerk = 1600; // Target jerk of 1600 rps/s/s (0.1 seconds)

    
    objElevatorWinch.getConfigurator().apply(talonFXConfigs);

    for (int i = 1; i < 5; i++) {
      objElevatorStatusCode = objElevatorWinch.getConfigurator().apply(objElevConfig);
      if (objElevatorStatusCode.isOK()) break;
    }
  }  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  
    SmartDashboard.putNumber("Elevator Position", getElevatorPosition());
    SmartDashboard.putNumber("Elevator Velocity", getElevatorVelocity());
    SmartDashboard.getNumber("Elevator Acceleration", getElevatorAcceleration());
  }

  // === SmartDashboard Values === \\

  public double getElevatorPosition(){
    objElevatorStatusSignal = objElevatorWinch.getPosition();
    return objElevatorStatusSignal.getValueAsDouble();
  }

  public double getElevatorVelocity(){
    objElevatorStatusSignal = objElevatorWinch.getVelocity();
    return objElevatorStatusSignal.getValueAsDouble();
  }

  public double getElevatorAcceleration(){
    objElevatorStatusSignal = objElevatorWinch.getAcceleration();
    return objElevatorStatusSignal.getValueAsDouble();
  }

  // === Functional Commands === \\

  public void runTargetMM(double dTarget){
    objElevatorWinch.setControl(objMMV.withPosition(dTarget).withSlot(0));
  }

  public void runElevator(double dSpeed){
    objElevatorWinch.set(dSpeed);
  }

  public void stopElevator(){
    objElevatorWinch.stopMotor();
  }
}
