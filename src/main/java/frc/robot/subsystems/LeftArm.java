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
import frc.robot.Constants.LeftArmConstants;

public class LeftArm extends SubsystemBase {
  
  private CANSparkMax m_leftArmNEO;
  private SparkMaxPIDController m_backPIDController;
  private SparkMaxLimitSwitch m_reverseLimitSwitch;
  private RelativeEncoder m_leftEncoder;
  private double m_setPoint;

  /** Creates a new Climbing. */
  public LeftArm() {
    m_leftArmNEO = new CANSparkMax(LeftArmConstants.leftSparkMaxID,MotorType.kBrushless);

    m_leftEncoder = m_leftArmNEO.getEncoder();

    m_reverseLimitSwitch = m_leftArmNEO.getReverseLimitSwitch(Type.kNormallyOpen);
    m_reverseLimitSwitch.enableLimitSwitch(true);

    m_backPIDController = m_leftArmNEO.getPIDController(); 

    m_backPIDController.setP(LeftArmConstants.leftArmP);
    m_backPIDController.setI(LeftArmConstants.leftArmI);
    m_backPIDController.setD(LeftArmConstants.leftArmD);
    m_backPIDController.setIZone(LeftArmConstants.leftArmIZone);
    m_backPIDController.setFF(LeftArmConstants.leftArmFF);
    m_backPIDController.setOutputRange(LeftArmConstants.leftArmMin,LeftArmConstants.leftArmMax);

    m_leftArmNEO.setIdleMode(IdleMode.kBrake);

    m_leftArmNEO.burnFlash();
  }

  public void topLeftArmPID(){
    m_backPIDController.setReference(LeftArmConstants.upPIDReference, ControlType.kPosition);
    m_setPoint = LeftArmConstants.upPIDReference;
  }

  public void bottomLeftArmPID(){
    m_backPIDController.setReference(LeftArmConstants.downPIDReference, ControlType.kPosition);
    m_setPoint = LeftArmConstants.downPIDReference; 
  }

  public void setLeftAscendSpeed() {
    m_leftArmNEO.set(LeftArmConstants.ascensionSpeed);
  }

  public void setLeftDescendSpeed() {
    m_leftArmNEO.set(LeftArmConstants.descensionSpeed);    
  }

  public void stopLeftArm(){
    m_leftArmNEO.set(LeftArmConstants.stopSpeed);
  }

  public boolean leftLimitSwitch() {
    return m_reverseLimitSwitch.isPressed();
  }

  public void setHome() {
    m_leftEncoder.setPosition(0.0);
  }

  public boolean isAtSetPoint() {
    return Math.abs(m_setPoint - m_leftEncoder.getPosition()) <= LeftArmConstants.leftPIDTolerance;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("LeftLimSwitch", leftLimitSwitch());
    SmartDashboard.putNumber("Left Arm Position", m_leftEncoder.getPosition());
  }
}
