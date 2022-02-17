// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbingConstants;

public class Climbing extends SubsystemBase {
  
  private CANSparkMax m_frontClimbNEO;
  private CANSparkMax m_backClimbNEO;

  /** Creates a new Climbing. */
  public Climbing() {
    m_frontClimbNEO = new CANSparkMax(ClimbingConstants.frontSparkMaxID,MotorType.kBrushless);
    m_backClimbNEO = new CANSparkMax(ClimbingConstants.backSparkMaxID,MotorType.kBrushless);
  }

  public void ascend(){
    m_frontClimbNEO.set(ClimbingConstants.ascensionSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
