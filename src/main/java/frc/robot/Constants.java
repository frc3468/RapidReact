
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
        public static final int intakeButton = XboxController.Button.kA.value;
        public static final int extakeButton = XboxController.Button.kB.value;
        public static final int downButton = XboxController.Button.kX.value;
        public static final int upButton = XboxController.Button.kY.value;

    }

    public static final class OperatorConstants {
        public static final int driverControllerUSB = 0;
    }

    public static final class DrivetrainConstants {
        public static final int leftSparkMaxCANID = 1;
        public static final int rightSparkMaxCANID = 2;
    }
    public static final class BallMechinismConstants {
        public static final int spinSparkMaxCANID = 6;
        public static final double intakeSpeed = 0.4;
        public static final double extakeSpeed = -0.4;
        public static final double idleSpeed = 0.075;
        public static final double stopSpeed = 0.0;
    }


    public static final class BallLiftConstants {
        public static final int liftMotor = 5;
        public static final double raiseSpeed = 0.2;
        public static final double lowerSpeed = -0.2;
        public static final double stopSpeed = 0.0;
        public static final double liftP = 3.0;
        public static final double liftI = 0.0;
        public static final double liftD = 0.0;
        public static final double liftIZone = 0.0;
        public static final double liftFF = 0.0;
        public static final double liftMin = -1.0;
        public static final double liftMax = 1.0;
        public static final double upPIDReference = 2.4;
        public static final double downPIDReference = 0.17;
        public static final double liftPIDTolorence = 0.1;
    }
}
