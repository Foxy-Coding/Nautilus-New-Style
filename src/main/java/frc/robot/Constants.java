// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class CANIds{
    public static final int iElevator = 21;
    public static final int iTilterA = 23;
    public static final int iTilterB = 24;
    public static final int iAlgaeIntake = 25;
    public static final int iCoralIntake = 26;
    public static final int iGroundPivot = 28;
    public static final int iGroundIntake = 31;
  }

  public static class MotorSpeeds{
    public static final double dGroundCoralPositive = 0.2;
    public static final double dGroundCoralNegative = -0.2;

    public static final double dCoralHold = 0.04;
  }
}
