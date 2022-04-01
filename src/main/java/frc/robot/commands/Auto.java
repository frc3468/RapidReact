// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BallMechinism;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LeftArm;
import frc.robot.subsystems.RightArm;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto extends SequentialCommandGroup {
  /** Creates a new Auto. */
  public Auto(LeftArm m_leftArm, RightArm m_rightArm, Drivetrain m_driveTrain, BallMechinism m_ballMechinism) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelCommandGroup(
        new LeftClimbArmHome(m_leftArm),
        new RightClimbArmHome(m_rightArm)).withTimeout(2),
      new TankDrive(m_driveTrain, ()-> 0.5, ()-> 0.5).withTimeout(.5),
      new Dispose(m_ballMechinism).withTimeout(2),
      new TankDrive(m_driveTrain, ()-> -0.5, ()-> -0.5).withTimeout(3)
    );
  }
}
