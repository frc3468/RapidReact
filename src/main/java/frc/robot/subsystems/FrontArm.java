// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FrontArmConstants;

public class FrontArm extends SubsystemBase {
  
  private CANSparkMax m_frontArmNEO;

  /** Creates a new Climbing. */
  public FrontArm() {
    m_frontArmNEO = new CANSparkMax(FrontArmConstants.frontSparkMaxID,MotorType.kBrushless);
  }

  public void ascendFrontArm(){
    m_frontArmNEO.set(FrontArmConstants.ascensionSpeed);
  }

  public void descendFrontArm(){
    m_frontArmNEO.set(FrontArmConstants.descensionSpeed);
  }

  public void stopFrontArm(){
    m_frontArmNEO.set(FrontArmConstants.stopSpeed);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
