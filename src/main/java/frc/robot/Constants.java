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

    public static final class OperatorConstants {
        public static final int driverControllerUSB = 0;
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
        public static final double leftArmP = 1.0;
        public static final double leftArmI = 1.0;
        public static final double leftArmD = 1.0;
        public static final double leftArmIZone = 1.0;
        public static final double leftArmFF = 1.0;
        public static final double leftArmMin = -0.25;
        public static final double leftArmMax = 0.25;
        public static final double upPIDReference = 10.0;
        public static final double downPIDReference = 0.0;
        public static final double leftPIDTolerance = 1.0;
    }

    public static final class RightArmConstants {
        public static final int rightSparkMaxID = 4;
        public static final double ascensionSpeed = 0.2;
        public static final double descensionSpeed = -0.25;
        public static final double stopSpeed = 0.0;
        public static final double rightArmP = 1.0;
        public static final double rightArmI = 1.0;
        public static final double rightArmD = 1.0;
        public static final double rightArmIZone = 1.0;
        public static final double rightArmFF = 1.0;
        public static final double rightArmMin = -0.25;
        public static final double rightArmMax = 0.25;
        public static final double upPIDReference = 10.0;
        public static final double downPIDReference = 0.0;
        public static final double rightPIDTolerance = 1.0;
    }

    public static final class m_driverControllerConstants {
        public static final int leftArmAscend = XboxController.Button.kLeftBumper.value;
        public static final int leftArmDescend = XboxController.Axis.kLeftTrigger.value;
        public static final int rightArmAscend = XboxController.Button.kRightBumper.value;
        public static final int rightArmDescend = XboxController.Axis.kRightTrigger.value;
    }
}
