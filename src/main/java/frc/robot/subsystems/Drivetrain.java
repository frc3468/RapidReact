// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {

  private CANSparkMax m_leftNEO;
  private CANSparkMax m_rightNEO;

  private DifferentialDrive m_diffDrive;

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    m_leftNEO = new CANSparkMax(DrivetrainConstants.leftSparkMaxCANID,MotorType.kBrushless);
    m_rightNEO = new CANSparkMax(DrivetrainConstants.rightSparkMaxCANID,MotorType.kBrushless);
    m_leftNEO.setInverted(false);
    m_rightNEO.setInverted(true);

    m_leftNEO.setIdleMode(IdleMode.kCoast);
    m_rightNEO.setIdleMode(IdleMode.kCoast);

    m_diffDrive = new DifferentialDrive(m_leftNEO, m_rightNEO);

    m_leftNEO.burnFlash();
    m_rightNEO.burnFlash();
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
    SmartDashboard.putNumber("Left Drive Speed", m_leftNEO.get());
    SmartDashboard.putNumber("Right Drive Speed", m_rightNEO.get());
  }
}
