// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import swervelib.SwerveInputStream;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.UpperJointSubsystem;
import frc.robot.subsystems.LowerJointSubsystem;
// import frc.robot.subsystems.KrakenTestSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final KrakenTestSubsystem m_KrakenTestSubsystem = new KrakenTestSubsystem();
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final SwerveSubsystem m_swerveSubsystem = new SwerveSubsystem();
  private final UpperJointSubsystem m_upperJointSubsystem = new UpperJointSubsystem();
  private final LowerJointSubsystem m_lowerJointSubsystem = new LowerJointSubsystem(m_upperJointSubsystem);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    //commented this just to check if what chat works
    m_swerveSubsystem.setDefaultCommand(!RobotBase.isSimulation() ? driveFieldOrientedAngularVelocity : driveFieldOrientedDirectAngleSim);
  }

  SwerveInputStream driveAngularVelocity = SwerveInputStream.of(m_swerveSubsystem.getSwerveDrive(), 
                                                                () -> m_driverController.getLeftY() * -1,
                                                                () -> m_driverController.getLeftX() * -1)
                                                                .withControllerRotationAxis(m_driverController::getRightX)
                                                                .scaleRotation(0.7)
                                                                .deadband(OperatorConstants.kDeadband)
                                                                .scaleTranslation(0.6)
                                                                .allianceRelativeControl(true); 
  /*                                                             
  SwerveInputStream driveDirectAngle = SwerveInputStream.of(m_swerveSubsystem.getSwerveDrive(), 
                                                                () -> 0.0, // No translation
                                                                () -> 0.0) // No strafing
                                                                .withControllerRotationAxis(m_driverController::getRightX) // Only Rotation
                                                                .scaleRotation(0.8)
                                                                .deadband(OperatorConstants.kDeadband);
  */

  //Command driveFieldOrientedDirectAngle = m_swerveSubsystem.driveFieldOriented(driveDirectAngle);

  Command driveFieldOrientedAngularVelocity = m_swerveSubsystem.driveFieldOriented(driveAngularVelocity);

  // Same as the code above, but is simulation code in order for it to work properly using SimGUI
  SwerveInputStream driveAngularVelocitySim = SwerveInputStream.of(m_swerveSubsystem.getSwerveDrive(),
                                                                   () -> -m_driverController.getLeftY(),
                                                                   () -> -m_driverController.getLeftX())
                                                               .withControllerRotationAxis(() -> m_driverController.getRawAxis(2))
                                                               .deadband(OperatorConstants.kDeadband)
                                                               .scaleTranslation(0.8)
                                                               .allianceRelativeControl(true);
  // Derive the heading axis with math!
  SwerveInputStream driveDirectAngleSim     = driveAngularVelocitySim.copy()
                                                                     .withControllerHeadingAxis(() -> Math.sin(
                                                                                                    m_driverController.getRawAxis(
                                                                                                        2) * Math.PI) * (Math.PI * 2),
                                                                                                () -> Math.cos(
                                                                                                    m_driverController.getRawAxis(
                                                                                                        2) * Math.PI) *
                                                                                                      (Math.PI * 2))
                                                                     .headingWhile(true);

  Command driveFieldOrientedDirectAngleSim      = m_swerveSubsystem.driveFieldOriented(driveDirectAngleSim);
  Command driveFieldOrientedAngularVelocitySim = m_swerveSubsystem.driveFieldOriented(driveAngularVelocitySim);
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

    // Upper-joint arm control using left bumper and trigger
    m_driverController.leftTrigger().whileTrue(m_upperJointSubsystem.manualControlCommand(() -> 2.0)); // Move arm down
    m_driverController.leftBumper().whileTrue(m_upperJointSubsystem.manualControlCommand(() -> -2.0)); // Move arm up

    m_driverController.rightTrigger().whileTrue(m_lowerJointSubsystem.manualControlCommand(() -> 2.0)); // Move arm down
    m_driverController.rightBumper().whileTrue(m_lowerJointSubsystem.manualControlCommand(() -> -2.0)); // Move arm up

    // m_driverController.a().whileTrue(m_KrakenTestSubsystem.forward());
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
