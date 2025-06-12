// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Coral;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevCoral extends SubsystemBase {
  /** Creates a new ElevCoral. */
  
  private TalonFX objElevCoral = new TalonFX(Constants.CANIds.iCoralIntake, "canivore");
  private StatusCode objStatusCode;
  @SuppressWarnings("rawtypes")
  private StatusSignal objStatusSignal;
  
  public ElevCoral() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
