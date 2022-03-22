// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BallMechinismConstants;

public class BallMechinism extends SubsystemBase {
 
  private CANSparkMax m_spinMotor;
  /** Creates a new BallMechinism. */
  public BallMechinism() {
    m_spinMotor = new CANSparkMax(BallMechinismConstants.spinSparkMaxCANID, MotorType.kBrushless);
  }

  public void BallIntake() {
    m_spinMotor.set(BallMechinismConstants.intakeSpeed);
  }

  public void BallExtake() {
    m_spinMotor.set(BallMechinismConstants.extakeSpeed);
  }

  public void IdleBall() {
    m_spinMotor.set(BallMechinismConstants.idleSpeed);
  }

  public void Stop() {
    m_spinMotor.set(BallMechinismConstants.stopSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
}
