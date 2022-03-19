// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.m_driverControllerConstants;
import frc.robot.commands.BallIdle;
import frc.robot.commands.Dispose;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Retrieve;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.BallMechinism;
import frc.robot.commands.LowerBallLift;
import frc.robot.commands.RaiseBallLift;
import frc.robot.commands.StopBallLift;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.BallLift;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Drivetrain m_driveTrain = new Drivetrain();
  private final BallMechinism m_ballMechinism = new BallMechinism();
  private final BallLift m_ballLift = new BallLift();

  private final XboxController m_driverController = new XboxController(OperatorConstants.driverControllerUSB);
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    configureButtonBindings();

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    m_driveTrain.setDefaultCommand(new TankDrive(m_driveTrain, m_driverController::getLeftY, m_driverController::getRightY));
    m_ballMechinism.setDefaultCommand(new BallIdle(m_ballMechinism));

    JoystickButton m_intakeButton = new JoystickButton(m_driverController,m_driverControllerConstants.intakeButton );
    JoystickButton m_extakeButton = new JoystickButton(m_driverController,m_driverControllerConstants.extakeButton );

    //intake
    m_intakeButton.whileHeld(new ParallelCommandGroup(
      new Retrieve(m_ballMechinism),
      new LowerBallLift(m_ballLift)
    )).whenReleased(new RaiseBallLift(m_ballLift));

    //Extake
    m_extakeButton.whileHeld(new SequentialCommandGroup(
      new RaiseBallLift(m_ballLift),
      new Dispose(m_ballMechinism)
      ));
    
    m_ballLift.setDefaultCommand(new StopBallLift(m_ballLift));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
