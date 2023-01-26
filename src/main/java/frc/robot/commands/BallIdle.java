// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallMechinism;

public class BallIdle extends CommandBase {
  private BallMechinism idleBall;
  /** Creates a new IdleBall. */
  public BallIdle(BallMechinism subsystem) {
    idleBall = subsystem;
    addRequirements(idleBall);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    idleBall.idleBall();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    idleBall.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
