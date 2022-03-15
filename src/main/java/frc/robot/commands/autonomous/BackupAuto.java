// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Dispose;
import frc.robot.commands.RaiseBallLift;
import frc.robot.subsystems.BallLift;
import frc.robot.subsystems.BallMechinism;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BackupAuto extends SequentialCommandGroup {
  /** Creates a new BackupAuto. */
  public BackupAuto(Drivetrain mDrivetrain, BallLift mBallLift, BallMechinism mBallMechinism) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(

      // completly just guessing the variables needed.
      new HomeLift(mBallLift).withTimeout(10),
      new DriveForward(mDrivetrain,Constants.AutoConstants.autospeed).withTimeout(10),
      new RaiseBallLift(mBallLift).withTimeout(15),  
      new Dispose(mBallMechinism).withTimeout(20),
      new DriveForward(mDrivetrain, -1*Constants.AutoConstants.autospeed).withTimeout(10)
      );
  }
}
