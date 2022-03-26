// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxLimitSwitch.Type;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RightArmConstants;

public class RightArm extends SubsystemBase {

  private CANSparkMax m_rightArmNEO;
  private SparkMaxPIDController m_backPIDController;
  private SparkMaxLimitSwitch m_reverseLimitSwitch;
  private RelativeEncoder m_rightEncoder;
  private double m_setPoint;

  /** Creates a new BackArm. */
  public RightArm() {
    m_rightArmNEO = new CANSparkMax(RightArmConstants.rightSparkMaxID,MotorType.kBrushless);

    m_rightEncoder = m_rightArmNEO.getEncoder();

    m_reverseLimitSwitch = m_rightArmNEO.getReverseLimitSwitch(Type.kNormallyOpen);
    m_reverseLimitSwitch.enableLimitSwitch(true);

    m_backPIDController = m_rightArmNEO.getPIDController(); 

    m_backPIDController.setP(RightArmConstants.rightArmP);
    m_backPIDController.setI(RightArmConstants.rightArmI);
    m_backPIDController.setD(RightArmConstants.rightArmD);
    m_backPIDController.setIZone(RightArmConstants.rightArmIZone);
    m_backPIDController.setFF(RightArmConstants.rightArmFF);
    m_backPIDController.setOutputRange(RightArmConstants.rightArmMin,RightArmConstants.rightArmMax);

    m_rightArmNEO.setIdleMode(IdleMode.kBrake);

    m_rightArmNEO.setInverted(true);
    m_rightArmNEO.burnFlash();
  }

  public void topRightArmPID() {
    m_backPIDController.setReference(RightArmConstants.upPIDReference, ControlType.kPosition);
    m_setPoint = RightArmConstants.upPIDReference;
  }

  public void bottomRightArmPID() {
    m_backPIDController.setReference(RightArmConstants.downPIDReference, ControlType.kPosition);
    m_setPoint = RightArmConstants.downPIDReference;
  }

  public void setRightAscendSpeed() {
    m_rightArmNEO.set(RightArmConstants.ascensionSpeed);
  }

  public void setRightDescendSpeed() {
    m_rightArmNEO.set(RightArmConstants.descensionSpeed);    
  }

  public void stopRightArm() {
    m_rightArmNEO.set(RightArmConstants.stopSpeed);
  }

  public boolean rightLimitSwitch() {
    return m_reverseLimitSwitch.isPressed();
  }

  public void setHome() {
    m_rightEncoder.setPosition(0.0);
  }

  public boolean isAtSetPoint() {
    return Math.abs(m_setPoint - m_rightEncoder.getPosition()) <= RightArmConstants.rightPIDTolerance;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("RightLimSwitch", rightLimitSwitch());
    SmartDashboard.putNumber("Right Arm Position", m_rightEncoder.getPosition());
  }
}
