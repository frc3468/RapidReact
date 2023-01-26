// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAnalogSensor;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BallLiftConstants;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;

public class BallLift extends SubsystemBase {
  
  private CANSparkMax m_liftMotor;
  private SparkMaxLimitSwitch m_forwardLimit;
  private SparkMaxLimitSwitch m_reverseLimit;
  private SparkMaxPIDController m_liftPidController;
  private SparkMaxAnalogSensor m_potentiometor;
  private double m_setPoint;
  private RelativeEncoder m_ballLiftEncoder;

  public BallLift() {
    m_liftMotor = new CANSparkMax(BallLiftConstants.liftMotor,MotorType.kBrushless);     
    
    m_forwardLimit = m_liftMotor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
    m_reverseLimit = m_liftMotor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
    
    m_forwardLimit.enableLimitSwitch(true);
    m_reverseLimit.enableLimitSwitch(true);

    m_ballLiftEncoder = m_liftMotor.getEncoder();

    m_liftPidController = m_liftMotor.getPIDController();
    m_potentiometor = m_liftMotor.getAnalog(SparkMaxAnalogSensor.Mode.kAbsolute);

    m_liftPidController.setP(BallLiftConstants.liftP);
    m_liftPidController.setI(BallLiftConstants.liftI);
    m_liftPidController.setD(BallLiftConstants.liftD);
    m_liftPidController.setIZone(BallLiftConstants.liftIZone);
    m_liftPidController.setFF(BallLiftConstants.liftFF);
    m_liftPidController.setOutputRange(BallLiftConstants.liftMin, BallLiftConstants.liftMax);
    m_liftPidController.setFeedbackDevice(m_potentiometor);

    m_liftMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_liftMotor.burnFlash();
  }

  
  public void raise() {
    m_liftPidController.setReference(BallLiftConstants.upPIDReference, ControlType.kPosition);
    m_setPoint = BallLiftConstants.upPIDReference;
  }

  public void lower() {
    m_liftPidController.setReference(BallLiftConstants.downPIDReference, ControlType.kPosition);
    m_setPoint = BallLiftConstants.downPIDReference; 
  }

  public void startingConfig() {
    m_liftPidController.setReference(BallLiftConstants.startingConfig, ControlType.kPosition);
  }

  public void liftingConfig() {
    m_liftPidController.setReference(BallLiftConstants.liftingConfig, ControlType.kPosition);
    m_setPoint = BallLiftConstants.liftingConfig;
  }

  public void raiseManual() {
    m_liftMotor.set(BallLiftConstants.raiseSpeed);
  }

  public void lowerManual() {
  m_liftMotor.set(BallLiftConstants.lowerSpeed);
  }

  public void stop() {
    m_liftMotor.set(BallLiftConstants.stopSpeed);
  }

  public boolean isAtSetPoint() {
    return (Math.abs(m_setPoint - m_potentiometor.getPosition()) <= BallLiftConstants.liftPIDTolorence);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Ball Lift Position", m_potentiometor.getPosition());
  }
}
