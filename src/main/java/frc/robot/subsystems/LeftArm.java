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

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LeftArmConstants;

public class LeftArm extends SubsystemBase {
  
  private CANSparkMax m_leftArmNEO;
  private SparkMaxPIDController m_backPIDController;
  private SparkMaxLimitSwitch m_reverseLimitSwitch;
  private RelativeEncoder m_leftEncoder;

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

  public void ascendLeftArmPID(){
    m_backPIDController.setReference(LeftArmConstants.upPIDReference, ControlType.kPosition);
  }

  public void descendLeftArmPID(){
    m_backPIDController.setReference(LeftArmConstants.downPIDReference, ControlType.kPosition);
  }

  public void setRightAscendSpeed() {
    m_leftArmNEO.set(LeftArmConstants.ascensionSpeed);
  }

  public void setRightDescendSpeed() {
    m_leftArmNEO.set(LeftArmConstants.descensionSpeed);    
  }

  public void stopLeftArm(){
    m_leftArmNEO.set(LeftArmConstants.stopSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
