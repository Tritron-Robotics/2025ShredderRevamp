// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.MatchStrikerSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class StrikeMatchCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final MatchStrikerSubsystem m_matchStrikerSubsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public StrikeMatchCommand(MatchStrikerSubsystem subsystem) {
    m_matchStrikerSubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_matchStrikerSubsystem.setMotorSpeed(MatchStrikerConstants.kMaxSpeed); // set motor speed to really fast
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_matchStrikerSubsystem.setMotorVoltage(MatchStrikerConstants.kHoldingVoltage);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
