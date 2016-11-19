package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by exploravision on 11/18/2016.
 */
public enum DriveMode {

    ONE_STICK {

        @Override
        public float[] getPowers(Gamepad gamepad1, Gamepad gamepad2) {
            float multiplier = 1.0F - gamepad1.left_trigger * 0.75F;

            float driveLeftPower = -gamepad1.left_stick_y - gamepad1.left_stick_x;
            float driveRightPower = -gamepad1.left_stick_y + gamepad1.left_stick_x;

            driveLeftPower = multiplier * Range.clip(driveLeftPower, -1, 1);
            driveRightPower = multiplier * Range.clip(driveRightPower, -1, 1);

            return new float[] {driveLeftPower, driveLeftPower, driveRightPower, driveRightPower};
        }

    },
    TANK {

        @Override
        public float[] getPowers(Gamepad gamepad1, Gamepad gamepad2) {
            float multiplier = 1.0F - gamepad1.left_trigger * 0.75F;
            return new float[] {multiplier * -gamepad1.left_stick_y, multiplier * -gamepad1.left_stick_y, multiplier * -gamepad1.right_stick_y, multiplier * -gamepad1.right_stick_y};
        }

    };

    public float[] getPowers(Gamepad gamepad1, Gamepad gamepad2) {
        return new float[4];
    }

}
