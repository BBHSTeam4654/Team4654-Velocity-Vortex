package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

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

    },
    REVERSE {

        @Override
        public float[] getPowers(Gamepad gamepad1, Gamepad gamepad2) {
            float multiplier = 1.0F - gamepad1.left_trigger * 0.75F;

            float driveLeftPower = gamepad1.left_stick_y - gamepad1.left_stick_x;
            float driveRightPower = gamepad1.left_stick_y + gamepad1.left_stick_x;

            driveLeftPower = multiplier * Range.clip(driveLeftPower, -1, 1);
            driveRightPower = multiplier * Range.clip(driveRightPower, -1, 1);

            return new float[] {driveLeftPower, driveLeftPower, driveRightPower, driveRightPower};
        }

    },
    MECANUM {

        @Override
        public float[] getPowers(Gamepad gamepad1, Gamepad gamepad2) {
            float multiplier = 1.0F - gamepad1.left_trigger * 0.75F;

            if (Math.abs(gamepad1.right_stick_x) > 0.05) { // Rotate
                float power = multiplier * gamepad1.right_stick_x;
                return new float[] {power, power, -power, -power};
            } else {
                float a = multiplier * Range.clip(gamepad1.left_stick_y + gamepad1.left_stick_x, -1, 1); // front left and back right
                float b = multiplier * Range.clip(gamepad1.left_stick_y - gamepad1.left_stick_x, -1, 1); // not front left or back right

                return new float[] {a, b, b, a};
            }
        }

    };

    public float[] getPowers(Gamepad gamepad1, Gamepad gamepad2) {
        return new float[4];
    }

}
