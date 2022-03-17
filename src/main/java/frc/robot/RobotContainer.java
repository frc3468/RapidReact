// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.m_driverControllerConstants;
import frc.robot.commands.RightClimbAscention;
import frc.robot.commands.RightClimbDescention;
import frc.robot.commands.RightClimbStop;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.LeftClimbAscention;
import frc.robot.commands.LeftClimbDescention;
import frc.robot.commands.LeftClimbStop;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.RightArm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LeftArm;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
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
  private final LeftArm m_leftArm = new LeftArm();
  private final LeftClimbAscention m_leftClimbAscention = new LeftClimbAscention(m_leftArm);
  private final LeftClimbDescention m_leftClimbDescention = new LeftClimbDescention(m_leftArm);
  private final LeftClimbStop m_leftClimbStop = new LeftClimbStop(m_leftArm);
  private final RightArm m_rightArm = new RightArm();
  private final RightClimbAscention m_rightClimbAscention = new RightClimbAscention(m_rightArm);
  private final RightClimbDescention m_rightClimbDescention = new RightClimbDescention(m_rightArm);
  private final RightClimbStop m_rightClimbStop = new RightClimbStop(m_rightArm);

  private final XboxController m_driverController = new XboxController(OperatorConstants.driverControllerUSB);

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_driveTrain.setDefaultCommand(new TankDrive(m_driveTrain, m_driverController::getLeftY, m_driverController::getRightY));
    m_leftArm.setDefaultCommand(new LeftClimbStop(m_leftArm));
    m_rightArm.setDefaultCommand(new RightClimbStop(m_rightArm));

    JoystickButton m_LeftArmDescentButton = new JoystickButton(m_driverController,m_driverControllerConstants.leftArmDescend);
    JoystickButton m_LeftArmAscentButton = new JoystickButton(m_driverController,m_driverControllerConstants.leftArmAscend);
    JoystickButton m_RightArmDescentButton = new JoystickButton(m_driverController,m_driverControllerConstants.rightArmDescend);
    JoystickButton m_RightArmAscentButton = new JoystickButton(m_driverController,m_driverControllerConstants.rightArmAscend);


    //Together L/R controlls for climbing 
    /* m_RightArmDescentButton.whenPressed(new ParallelCommandGroup(new LeftClimbDescention(m_leftArm), new RightClimbDescention(m_rightArm)));
    *  m_RightArmAscentButton.whenPressed(new ParallelCommandGroup(new LeftClimbAscention(m_leftArm), new RightClimbAscention(m_rightArm)));
    */

    //Separate L/R controlls for climbing
    m_LeftArmDescentButton.whenPressed(new LeftClimbDescention(m_leftArm));
    m_LeftArmAscentButton.whenPressed(new LeftClimbDescention(m_leftArm));
    m_RightArmDescentButton.whenPressed(new RightClimbAscention(m_rightArm));
    m_RightArmAscentButton.whenPressed(new RightClimbAscention(m_rightArm));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

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
