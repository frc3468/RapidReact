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
        public static final int driverControllerUSB = 1;
    }

    public static final class DrivetrainConstants {
        public static final int leftSparkMaxCANID = 1;
        public static final int rightSparkMaxCANID = 2;
    }

    public static final class FrontArmConstants {
        public static final int frontSparkMaxID = 3;
        public static final double ascensionSpeed = 1.0;
        public static final double descensionSpeed = -1.0;
        public static final double stopSpeed = 0.0;
    }

    public static final class BackArmConstants {
        public static final int backSparkMaxID = 4;
        public static final double ascensionSpeed = 1.0;
        public static final double descensionSpeed = -1.0;
        public static final double stopSpeed = 0.0;
        public static final double backArmP = 1.0;
        public static final double backArmI = 1.0;
        public static final double backArmD = 1.0;
        public static final double backArmIZone = 1.0;
        public static final double backArmFF = 1.0;        
        public static final double backArmMin = 1.0;
        public static final double backArmMax = 0.0;
        public static final double upPIDReference = 10.0;
        public static final double downPIDReference = 0.0;
    }

    public static final class m_driverControllerConstants {
        public static final int frontArmAscend = XboxController.Button.kLeftBumper.value;
        public static final int frontArmDescend = XboxController.Axis.kLeftTrigger.value;
        public static final int backArmAscend = XboxController.Button.kRightBumper.value;
        public static final int backArmDescend = XboxController.Axis.kRightTrigger.value;
    }
}
