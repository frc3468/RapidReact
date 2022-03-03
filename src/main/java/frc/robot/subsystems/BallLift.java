// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LiftConstants;

import com.revrobotics.SparkMaxLimitSwitch;

public class BallLift extends SubsystemBase {
  
  private CANSparkMax m_liftMotor;
  private SparkMaxLimitSwitch m_forwardLimit;
  private SparkMaxLimitSwitch m_reverseLimit;

  public BallLift() {
    m_liftMotor = new CANSparkMax(LiftConstants.liftMotor,MotorType.kBrushless); 
  }
  
  public void raise() {
    m_liftMotor.set(LiftConstants.raiseSpeed);
  }

  public void lower() {
    m_liftMotor.set(LiftConstants.lowerSpeed);
  }

  public void stop() {
    m_liftMotor.set(LiftConstants.stopSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void ballLiftLimitSwitch() {
    m_forwardLimit = m_liftMotor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
    m_reverseLimit = m_liftMotor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);

    m_forwardLimit.enableLimitSwitch(true);
    m_reverseLimit.enableLimitSwitch(true);
    SmartDashboard.putBoolean("BallLiftTop sensor", m_forwardLimit.isLimitSwitchEnabled());
    SmartDashboard.putBoolean("BallLiftBottem sensor", m_reverseLimit.isLimitSwitchEnabled());
  }

}
