// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LiftConstants;

public class BallLift extends SubsystemBase {
  
  private CANSparkMax m_liftMotor;

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
}
