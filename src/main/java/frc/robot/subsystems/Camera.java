// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.FileNotFoundException;
import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.common.hardware.VisionLEDMode;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {
  /** Creates a new Camera. */
  PhotonCamera m_camera;
  boolean tDrivermode;
  public Camera() {
    m_camera = new PhotonCamera("3468");
    tDrivermode = false;
    m_camera.setLED(VisionLEDMode.kOn);
  }

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
  }
  /**  since photon lib uses the var interface when trying to 
   *  read network tables Please start all methods with "var result = m_camera.getLatestResult();" */
  public boolean seetarget() { 
    //Failure to use this in methods will result in a null pointer exception
    var result = m_camera.getLatestResult(); 
    return result.hasTargets();
  }
  public double latency() { 
    if(seetarget() == true){
      var result = m_camera.getLatestResult();
      return result.getLatencyMillis(); 
    }
    else{
      return -1;
    }
  }
  public void toggledrivermode() {
    //might not need the flipper but it made me feel cool darn it
    tDrivermode = !tDrivermode;
    m_camera.setDriverMode(tDrivermode);
    if(tDrivermode == true){
      m_camera.setLED(VisionLEDMode.kOff);
    }
    else{m_camera.setLED(VisionLEDMode.kOn);
    };
  }
  public void changepipeline(int pipenum) {
    // as we set this up we should note pipenum numbers
  m_camera.setPipelineIndex(pipenum);
  }
  
  private List<PhotonTrackedTarget> gettargetsList() throws FileNotFoundException{
     
    if(seetarget() == true){
    var m_result = m_camera.getLatestResult();
    List<PhotonTrackedTarget> targets = m_result.getTargets();
    return targets;
    }
    else{  
      throw new FileNotFoundException("No targets found. Make rsure to run see targets before this.would've thrown a whole buffer overflow"); 
    }
  }
  public PhotonTrackedTarget getBestTarget() throws FileNotFoundException{
    if(seetarget()== true){
      var m_result = m_camera.getLatestResult();
      return m_result.getBestTarget();
    }
    else{ throw new FileNotFoundException("No targets found. Make sure to run see targets next time before you do this ");}
  };
}