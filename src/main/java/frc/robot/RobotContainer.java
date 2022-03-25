// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.m_driverControllerConstants;
import frc.robot.commands.RightClimbTop;
import frc.robot.commands.StartingConfigureBallLift;
import frc.robot.commands.LeftArmAscendSpeed;
import frc.robot.commands.LeftArmDescendSpeed;
import frc.robot.commands.RightArmAscendSpeed;
import frc.robot.commands.RightArmDescendSpeed;
import frc.robot.commands.RightClimbBottom;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.LeftClimbArmHome;
import frc.robot.commands.LeftClimbTop;
import frc.robot.commands.RightClimbArmHome;
import frc.robot.commands.LeftClimbBottom;
import frc.robot.subsystems.RightArm;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.BallIdle;
import frc.robot.commands.Dispose;
import frc.robot.commands.Retrieve;
import frc.robot.subsystems.BallMechinism;
import frc.robot.commands.LowerBallLift;
import frc.robot.commands.ManualLowerBall;
import frc.robot.commands.ManualRaiseBall;
import frc.robot.commands.RaiseBallLift;
import frc.robot.commands.StopBallLift;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.BallLift;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LeftArm;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.POVButton;

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
  private final BallMechinism m_ballMechinism = new BallMechinism();
  private final BallLift m_ballLift = new BallLift();

  private final XboxController m_driverController = new XboxController(OperatorConstants.driverControllerUSB);
  private final XboxController m_overridXboxController = new XboxController(OperatorConstants.overrideControllerUSB);

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    m_driveTrain.setDefaultCommand(new ArcadeDrive(m_driveTrain, ()-> (m_driverController.getLeftY()/2),()-> -1*(m_driverController.getLeftX()/2)));
    m_leftArm.setDefaultCommand(new LeftClimbBottom(m_leftArm).perpetually());
    m_rightArm.setDefaultCommand(new RightClimbBottom(m_rightArm).perpetually());
    m_ballMechinism.setDefaultCommand(new BallIdle(m_ballMechinism));
    m_ballLift.setDefaultCommand(new StopBallLift(m_ballLift));

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    //Driver Controller
    JoystickButton m_intakeButton = new JoystickButton(m_driverController,m_driverControllerConstants.intakeButton );
    JoystickButton m_extakeButton = new JoystickButton(m_driverController,m_driverControllerConstants.extakeButton );
    JoystickButton m_homingButtom = new JoystickButton(m_driverController, m_driverControllerConstants.homingButton);
    JoystickButton m_topPositionClimbing = new JoystickButton(m_driverController,m_driverControllerConstants.climbUpButton);
    // JoystickButton m_bottomPositionClimbing = new JoystickButton(m_driverController,m_driverControllerConstants.climbDownButton);
    JoystickButton m_startingConfig = new JoystickButton(m_driverController, m_driverControllerConstants.startingConfig);


    //Overrides 
    JoystickButton m_overrideExtake = new JoystickButton(m_overridXboxController, OperatorConstants.extakeOverrideButton);
    JoystickButton m_overrideIntake = new JoystickButton(m_overridXboxController, OperatorConstants.intakeOverrideButton);
    JoystickButton m_leftArmUpButton = new JoystickButton(m_overridXboxController,m_driverControllerConstants.leftArmAscendOverrideButton);
    JoystickButton m_rightArmUpButton = new JoystickButton(m_overridXboxController,m_driverControllerConstants.rightArmAscendOverrideButton);
    Button m_leftArmDownButton = new Button(()-> m_overridXboxController.getLeftTriggerAxis() > 0.5);
    Button m_rightArmDownButton = new Button(()-> m_overridXboxController.getRightTriggerAxis() > 0.5);
    POVButton m_overrideDown = new POVButton(m_overridXboxController, 180);
    POVButton m_overrideUp = new POVButton(m_overridXboxController, 0);


    //Homing
    Trigger m_leftClimbArmLimitSwitch = new Trigger(() -> m_leftArm.leftLimitSwitch());
    Trigger m_rightClimbArmLimitSwitch = new Trigger(() -> m_rightArm.rightLimitSwitch());
    m_leftClimbArmLimitSwitch.whenActive(new LeftClimbArmHome(m_leftArm));
    m_rightClimbArmLimitSwitch.whenActive(new RightClimbArmHome(m_rightArm));
    m_homingButtom.whenPressed(new LeftArmDescendSpeed(m_leftArm).withTimeout(5.0));
    m_homingButtom.whenPressed(new RightArmDescendSpeed(m_rightArm).withTimeout(5.0));


    //Together L/R controls for climbing 
    m_topPositionClimbing.whileHeld(new ParallelCommandGroup(new LeftClimbTop(m_leftArm), new RightClimbTop(m_rightArm)));
    // m_bottomPositionClimbing.whileHeld(new ParallelCommandGroup(new LeftClimbBottom(m_leftArm), new RightClimbBottom(m_rightArm)));


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


    //Configure Robot for Stating Position
    m_startingConfig.whenPressed(new StartingConfigureBallLift(m_ballLift));


    //Separate L/R controls for climbing (overrides)
    m_leftArmDownButton.whileHeld(new LeftArmDescendSpeed(m_leftArm));
    m_leftArmUpButton.whileHeld(new LeftArmAscendSpeed(m_leftArm));
    m_rightArmDownButton.whileHeld(new RightArmDescendSpeed(m_rightArm));
    m_rightArmUpButton.whileHeld(new RightArmAscendSpeed(m_rightArm));
    

    //Ball Mechinism Overrides
    m_overrideExtake.whileHeld(new Dispose(m_ballMechinism));
    m_overrideIntake.whileHeld(new Retrieve(m_ballMechinism));
    m_overrideUp.whileHeld(new ManualRaiseBall(m_ballLift));
    m_overrideDown.whileHeld(new ManualLowerBall(m_ballLift));
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
