// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.BasePlate.Elevator;
import frc.robot.subsystems.BasePlate.GroundCoral;
import frc.robot.subsystems.BasePlate.GroundPivot;
import frc.robot.subsystems.ElevatorMechs.ElevCoral;
import frc.robot.subsystems.ElevatorMechs.ElevWrist;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  // === Rio Subsystems === \\`
  private final ElevCoral objElevCoral = new ElevCoral();
  private final ElevWrist objElevWrist = new ElevWrist();

  // === Canivore Subsystems === \\
  private final Elevator objElevator = new Elevator();
  private final GroundPivot objGroundPivot = new GroundPivot();
  private final GroundCoral objGroundCoral = new GroundCoral();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController objXboxController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private final CommandGenericHID objButtonBoxA = new CommandGenericHID(2);
  private final CommandGenericHID objButtonBoxB = new CommandGenericHID(3);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

      


    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    objXboxController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    objXboxController.x().whileTrue(Commands.run(objGroundCoral :: runGroundCoralPos, objGroundCoral));
    objXboxController.a().whileTrue(Commands.run(objGroundCoral :: runGroundCoralNeg, objGroundCoral));

   

    
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
