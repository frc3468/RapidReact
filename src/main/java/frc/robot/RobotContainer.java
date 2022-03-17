// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax.IdleMode;

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
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Subsystem;

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
  private final BallMechinism m_BallMechinism = new BallMechinism();
  private final BallLift m_ballLift = new BallLift();
  
  private final Retrieve m_Retrieve = new Retrieve(m_BallMechinism);
  private final Dispose m_Dispose = new Dispose(m_BallMechinism);

  private final LowerBallLift m_lowerBallLift = new LowerBallLift(m_ballLift);
  private final RaiseBallLift m_raiseBallLift = new RaiseBallLift(m_ballLift);
  private final StopBallLift m_stopBallLift = new StopBallLift(m_ballLift);

  private final XboxController m_driverController = new XboxController(OperatorConstants.driverControllerUSB);
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_driveTrain.setDefaultCommand(new TankDrive(m_driveTrain, m_driverController::getLeftY, m_driverController::getRightY));
    m_BallMechinism.setDefaultCommand(new BallIdle(m_BallMechinism));

    JoystickButton m_intakeButton = new JoystickButton(m_driverController,m_driverControllerConstants.intakeButton );
    JoystickButton m_extakeButton = new JoystickButton(m_driverController,m_driverControllerConstants.extakeButton );

    //intake
    m_intakeButton.whileHeld(new Retrieve(m_BallMechinism));
    m_intakeButton.whileHeld(new LowerBallLift(m_ballLift));
    m_intakeButton.whenReleased(new RaiseBallLift(m_ballLift));

    //Extake
    m_extakeButton.whileHeld(new Dispose(m_BallMechinism));
    m_ballLift.setDefaultCommand(new StopBallLift(m_ballLift));

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
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
