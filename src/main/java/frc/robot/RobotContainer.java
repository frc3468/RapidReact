// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.m_driverControllerConstants;
import frc.robot.commands.RightClimbTop;
import frc.robot.commands.SetLeftArmClimbSpeed;
import frc.robot.commands.SetRightArmClimbSpeed;
import frc.robot.commands.RightClimbBottom;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.LeftClimbArmHome;
import frc.robot.commands.LeftClimbTop;
import frc.robot.commands.RightClimbArmHome;
import frc.robot.commands.LeftClimbBottom;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.RightArm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LeftArm;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

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
  private final RightArm m_rightArm = new RightArm();

  private final XboxController m_driverController = new XboxController(OperatorConstants.driverControllerUSB);

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    m_driveTrain.setDefaultCommand(new TankDrive(m_driveTrain, m_driverController::getLeftY, m_driverController::getRightY));
    m_leftArm.setDefaultCommand(new LeftClimbBottom(m_leftArm).perpetually());
    m_rightArm.setDefaultCommand(new RightClimbBottom(m_rightArm).perpetually());

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton m_LeftArmBottomButton = new JoystickButton(m_driverController,m_driverControllerConstants.leftArmDescend);
    JoystickButton m_LeftArmtopButton = new JoystickButton(m_driverController,m_driverControllerConstants.leftArmAscend);
    JoystickButton m_RightArmBottomButton = new JoystickButton(m_driverController,m_driverControllerConstants.rightArmDescend);
    JoystickButton m_RightArmTopButton = new JoystickButton(m_driverController,m_driverControllerConstants.rightArmAscend);

    //Homing
    Trigger m_leftClimbArmLimitSwitch = new Trigger(() -> m_leftArm.leftLimitSwitch());
    Trigger m_rightClimbArmLimitSwitch = new Trigger(() -> m_rightArm.rightLimitSwitch());

    m_leftClimbArmLimitSwitch.whenActive(new LeftClimbArmHome(m_leftArm));
    m_rightClimbArmLimitSwitch.whenActive(new RightClimbArmHome(m_rightArm));


    //Together L/R controls for climbing 
    m_RightArmTopButton.whileHeld(new ParallelCommandGroup(new LeftClimbTop(m_leftArm), new RightClimbTop(m_rightArm)));

    //Separate L/R controls for climbing
    /* m_LeftArmBottomButton.whileHeld(new LeftClimbBottom(m_leftArm));
    *  m_LeftArmTopButton.whileHeld(new LeftClimbTop(m_leftArm));
    *  m_RightArmBottomButton.whileHeld(new RightClimbBottom(m_rightArm));
    *  m_RightArmTopButton.whileHeld(new SetLeftArmClimbSpeed(m_leftArm));
    */
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
