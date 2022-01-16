// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {
  private CANSparkMax m_frontLeftNEO;
  private CANSparkMax m_rearLeftNEO;
  private CANSparkMax m_frontRightNEO;
  private CANSparkMax m_rearRightNEO;

  private MotorControllerGroup m_leftMotors;
  private MotorControllerGroup m_rightMotors;

  private DifferentialDrive m_diffDrive;

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    m_frontLeftNEO = new CANSparkMax(DrivetrainConstants.frontLeftSparkMaxCANID,MotorType.kBrushless);
    m_rearLeftNEO = new CANSparkMax(DrivetrainConstants.rearLeftSparkMaxCANID,MotorType.kBrushless);
    m_frontRightNEO = new CANSparkMax(DrivetrainConstants.frontRightSparkMaxCANID,MotorType.kBrushless);
    m_rearRightNEO = new CANSparkMax(DrivetrainConstants.rearRightSparkMaxCANID,MotorType.kBrushless);

    m_leftMotors = new MotorControllerGroup(m_frontLeftNEO, m_rearLeftNEO);
    m_rightMotors = new MotorControllerGroup(m_frontRightNEO, m_rearRightNEO);
    m_rightMotors.setInverted(true);

    m_diffDrive = new DifferentialDrive(m_leftMotors, m_rightMotors);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    m_diffDrive.tankDrive(leftSpeed, rightSpeed);
  }

  public void arcadeDrive(double speed, double rotation) {
    m_diffDrive.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Left Drive Motors", m_leftMotors.get());
    SmartDashboard.putNumber("Right Drive Motors", m_rightMotors.get());
  }
}
