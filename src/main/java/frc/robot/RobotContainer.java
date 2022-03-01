// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.m_driverControllerConstants;
import frc.robot.commands.BackClimbAscention;
import frc.robot.commands.BackClimbDescention;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.FrontClimbAscention;
import frc.robot.commands.FrontClimbDescention;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.BackArm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.FrontArm;
import edu.wpi.first.wpilibj2.command.Command;
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
  private final FrontArm m_frontArm = new FrontArm();
  private final FrontClimbAscention m_frontClimbAscention = new FrontClimbAscention(m_frontArm);
  private final FrontClimbDescention m_frontClimbDescention = new FrontClimbDescention(m_frontArm);
  private final BackArm m_backArm = new BackArm();
  private final BackClimbAscention m_backClimbAscention = new BackClimbAscention(m_backArm);
  private final BackClimbDescention m_backClimbDescention = new BackClimbDescention(m_backArm);

  private final XboxController m_driverController = new XboxController(OperatorConstants.driverControllerUSB);

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_driveTrain.setDefaultCommand(new TankDrive(m_driveTrain, m_driverController::getLeftY, m_driverController::getRightY));

    JoystickButton m_FrontArmDescentButton = new JoystickButton(m_driverController,m_driverControllerConstants.frontArmDescend);
    JoystickButton m_FrontArmAscentButton = new JoystickButton(m_driverController,m_driverControllerConstants.frontArmAscend);
    JoystickButton m_BackArmDescentButton = new JoystickButton(m_driverController,m_driverControllerConstants.backArmDescend);
    JoystickButton m_BackArmAscentButton = new JoystickButton(m_driverController,m_driverControllerConstants.backArmAscend);

    m_FrontArmDescentButton.whileHeld(new FrontClimbDescention(m_frontArm));
    m_FrontArmAscentButton.whileHeld(new FrontClimbDescention(m_frontArm));
    m_BackArmDescentButton.whileHeld(new BackClimbAscention(m_backArm));
    m_BackArmAscentButton.whileHeld(new BackClimbAscention(m_backArm));
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
