
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class m_driverControllerConstants {
        public static final int intakeButton = XboxController.Button.kB.value;
        public static final int extakeButton = XboxController.Button.kA.value;
        public static final int leftArmAscend = XboxController.Button.kLeftBumper.value;
        public static final int leftArmDescend = XboxController.Axis.kLeftTrigger.value;
        public static final int rightArmAscend = XboxController.Button.kRightBumper.value;
        public static final int rightArmDescend = XboxController.Axis.kRightTrigger.value;
        public static final int climbUpButton = XboxController.Button.kRightBumper.value;
        public static final int climbDownButton = XboxController.Axis.kRightTrigger.value;
        public static final int leftArmAscendOverrideButton = XboxController.Button.kLeftBumper.value;
        public static final int leftArmDescendOverrideButton = XboxController.Axis.kLeftTrigger.value;
        public static final int rightArmAscendOverrideButton = XboxController.Button.kRightBumper.value;
        public static final int rightArmDescendOverrideButton = XboxController.Axis.kRightTrigger.value;
        public static final int homingButton = XboxController.Button.kLeftBumper.value;
        public static final int startingConfig = XboxController.Button.kStart.value;
    }

    public static final class OperatorConstants {
        public static final int driverControllerUSB = 0;
        public static final int overrideControllerUSB = 1;
        public static final int intakeOverrideButton = XboxController.Button.kB.value;
        public static final int extakeOverrideButton = XboxController.Button.kA.value;
    }

    public static final class DrivetrainConstants {
        public static final int leftSparkMaxCANID = 1;
        public static final int rightSparkMaxCANID = 2;
    }

    public static final class LeftArmConstants {
        public static final int leftSparkMaxID = 3;
        public static final double ascensionSpeed = 0.25;
        public static final double descensionSpeed = -0.25;
        public static final double stopSpeed = 0.0;
        public static final double leftArmP = 3.0;
        public static final double leftArmI = 0.0;
        public static final double leftArmD = 0.0;
        public static final double leftArmIZone = 0.0;
        public static final double leftArmFF = 0.0;
        public static final double leftArmMin = -0.5;
        public static final double leftArmMax = 1.0;
        public static final double upPIDReference = 85.0;
        public static final double downPIDReference = 7.0;
        public static final double leftPIDTolerance = 0.01;
    }

    public static final class RightArmConstants {
        public static final int rightSparkMaxID = 4;
        public static final double ascensionSpeed = 0.25;
        public static final double descensionSpeed = -0.25;
        public static final double stopSpeed = 0.0;
        public static final double rightArmP = 3.0;
        public static final double rightArmI = 0.0;
        public static final double rightArmD = 0.0;
        public static final double rightArmIZone = 0.0;
        public static final double rightArmFF = 0.0;
        public static final double rightArmMin = -0.5;
        public static final double rightArmMax = 1.0;
        public static final double upPIDReference = 85.0;
        public static final double downPIDReference = 7.0;
        public static final double rightPIDTolerance = 0.01;
    }
    
    public static final class BallMechinismConstants {
        public static final int spinSparkMaxCANID = 6;
        public static final double intakeSpeed = 0.4;
        public static final double extakeSpeed = -0.6;
        public static final double idleSpeed = 0.075;
        public static final double stopSpeed = 0.0;
    }

    public static final class BallLiftConstants {
        public static final int liftMotor = 5;
        public static final double raiseSpeed = 0.4;
        public static final double lowerSpeed = -0.4;
        public static final double stopSpeed = 0.0;
        public static final double liftP = 10.0;
        public static final double liftI = 0.0;
        public static final double liftD = 0.0;
        public static final double liftIZone = 0.0;
        public static final double liftFF = 0.0;
        public static final double liftMin = -1.0;
        public static final double liftMax = 1.0;
        public static final double upPIDReference = 2.6;
        public static final double downPIDReference = 0.165;
        public static final double startingConfig = 2.5;
        public static final double liftPIDTolorence = 0.001;
        public static final double liftingConfig = 2.0;
    }
}