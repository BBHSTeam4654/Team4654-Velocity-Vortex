package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

public enum DriveMode {

    ONE_STICK {

        @Override
        public float[] getPowers(Gamepad gamepad1, Gamepad gamepad2) {
            float driveLeftPower = -gamepad1.left_stick_y - gamepad1.left_stick_x;
            float driveRightPower = -gamepad1.left_stick_y + gamepad1.left_stick_x;

            driveLeftPower = Range.clip(driveLeftPower, -1, 1);
            driveRightPower = Range.clip(driveRightPower, -1, 1);

            return new float[] {driveLeftPower, driveLeftPower, driveRightPower, driveRightPower};
        }

    },
    TANK {

        @Override
        public float[] getPowers(Gamepad gamepad1, Gamepad gamepad2) {
            return new float[] {-gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.right_stick_y, -gamepad1.right_stick_y};
        }

    },
    REVERSE {

        @Override
        public float[] getPowers(Gamepad gamepad1, Gamepad gamepad2) {
            float driveLeftPower = gamepad1.left_stick_y - gamepad1.left_stick_x;
            float driveRightPower = gamepad1.left_stick_y + gamepad1.left_stick_x;

            driveLeftPower = Range.clip(driveLeftPower, -1, 1);
            driveRightPower = Range.clip(driveRightPower, -1, 1);

            return new float[] {driveLeftPower, driveLeftPower, driveRightPower, driveRightPower};
        }

    },
    MECANUM {

        @Override
        public float[] getPowers(Gamepad gamepad1, Gamepad gamepad2) {
            //if (Math.abs(gamepad1.right_stick_x) > 0.1) { // Rotate
                //float power = gamepad1.right_stick_x;
                //return new float[] {power, power, -power, -power};
            //} else {
            return BasicDriveFunctions.mecanumPowers(1F, gamepad1.left_stick_x, gamepad1.left_stick_y);
            //}
        }

    };

    /**
     * Calculate and return the powers for the four wheel motors, in the order, Left Front, Left Back, Right Front, Right Back.
     * @param gamepad1 - gamepad1 from the OpMode
     * @param gamepad2 - gamepad2 from the OpMode
     * @return The four motor powers.
     * @see {@link com.qualcomm.robotcore.hardware.DcMotor#setPower(double)}
     */
    public float[] getPowers(Gamepad gamepad1, Gamepad gamepad2) {
        return new float[4];
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase().replace('_', ' ');
    }

    public static void stopMotors() {

    }

}
