// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.BasePlate;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.CANIds;
import frc.robot.Constants.MotorSpeeds;

public class GroundCoral extends SubsystemBase {
  /** Creates a new GroundIntake. */

  private TalonFX objGroundCoral = new TalonFX(CANIds.iGroundIntake, "canivore");
  private StatusCode objStatusCode;
  @SuppressWarnings("rawtypes")
  StatusSignal objStatusSignal;

  public GroundCoral() {
    TalonFXConfiguration objTalonFXConfig = new TalonFXConfiguration();
    objTalonFXConfig.CurrentLimits.SupplyCurrentLimit = 100.0;
    objTalonFXConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
    objTalonFXConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
    objTalonFXConfig.OpenLoopRamps.VoltageOpenLoopRampPeriod = 0.5;
    objStatusCode = StatusCode.StatusCodeNotInitialized;
    
    for (int i = 1; i < 5; i++) {
      objStatusCode = objGroundCoral.getConfigurator().apply(objTalonFXConfig);
      if (objStatusCode.isOK()) break;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void runGroundCoralPos(){
    objGroundCoral.set(MotorSpeeds.dGroundCoralPositive);
  }

  public void runGroundCoralNeg(){
    objGroundCoral.set(MotorSpeeds.dGroundCoralNegative);
  }

  public void stopMotor(){
    objGroundCoral.stopMotor();
  }
}
