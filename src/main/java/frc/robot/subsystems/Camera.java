// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {
  UsbCamera m_camera; 
  /** Creates a new Camera. */
  public Camera() {
    m_camera = CameraServer.startAutomaticCapture();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
