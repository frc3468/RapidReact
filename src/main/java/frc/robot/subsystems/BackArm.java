// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BackArmConstants;

public class BackArm extends SubsystemBase {

  private CANSparkMax m_backArmNEO;
  private SparkMaxPIDController m_backPIDController;

  /** Creates a new BackArm. */
  public BackArm() {
    m_backArmNEO = new CANSparkMax(BackArmConstants.backSparkMaxID,MotorType.kBrushless);

    m_backPIDController = m_backArmNEO.getPIDController(); 

    m_backPIDController.setP(BackArmConstants.backArmP);
    m_backPIDController.setI(BackArmConstants.backArmI);
    m_backPIDController.setD(BackArmConstants.backArmD);
    m_backPIDController.setIZone(BackArmConstants.backArmIZone);
    m_backPIDController.setFF(BackArmConstants.backArmFF);
    m_backPIDController.setOutputRange(BackArmConstants.backArmMin,BackArmConstants.backArmMax);
  }

  public void ascendBackArm(){
    m_backArmNEO.set(BackArmConstants.ascensionSpeed);
  }

  public void descendBackArm(){
    m_backArmNEO.set(BackArmConstants.descensionSpeed);
  }

  public void stopBackArm(){
    m_backArmNEO.set(BackArmConstants.stopSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
