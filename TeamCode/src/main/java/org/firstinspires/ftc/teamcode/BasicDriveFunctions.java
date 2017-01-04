package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public final class BasicDriveFunctions {

    public static float[] stopPowers() {
        return new float[4];
    }

    public static void stop(DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack) {
        float[] powers = stopPowers();

        leftFront.setPower(powers[0]);
        leftBack.setPower(powers[1]);
        rightFront.setPower(powers[2]);
        rightBack.setPower(powers[3]);
    }

    public static float[] straightPowers(float speed) {
        return straightPowers(speed, true);
    }

    public static float[] straightPowers(float speed, boolean forward) {
        float power = Range.clip(speed, -1, 1) * (forward ? 1 : -1);
        return new float[] {power, power, power, power};
    }

    public static void straight(float speed, DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack) {
        straight(speed, true, leftFront, leftBack, rightFront, rightBack);
    }

    public static void straight(float speed, boolean forwards, DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack) {
        float[] powers = straightPowers(speed, forwards);

        leftFront.setPower(powers[0]);
        leftBack.setPower(powers[1]);
        rightFront.setPower(powers[2]);
        rightBack.setPower(powers[3]);
    }

    public static float[] rotatePowers(float speed) {
        return rotatePowers(speed, true);
    }

    public static float[] rotatePowers(float speed, boolean counterclockwise) {
        float power = Range.clip(speed, -1, 1) * (counterclockwise ? 1 : -1);
        return new float[] {-power, -power, power, power};
    }

    public static void rotate(float speed, DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack) {
        straight(speed, true, leftFront, leftBack, rightFront, rightBack);
    }

    public static void rotate(float speed, boolean counterclockwise, DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack) {
        float[] powers = rotatePowers(speed, counterclockwise);

        leftFront.setPower(powers[0]);
        leftBack.setPower(powers[1]);
        rightFront.setPower(powers[2]);
        rightBack.setPower(powers[3]);
    }

    public static float[] mecanumPowers(float speed, float x, float y) {
        float a = Range.clip(speed * (-y - x), -1, 1);
        float b = Range.clip(speed * (-y + x), -1, 1);

        return new float[] {a, b, b, a};
    }

    /**
     * @param speed - The power of the motors
     * @param angle - The angle, in degrees, clockwise, from straight up
     */
    public static float[] mecanumPowers(float speed, float angle) {
        float x = speed * (float) Math.sin(Math.toRadians(angle));
        float y = speed * (float) Math.cos(Math.toRadians(angle));

        return mecanumPowers(1, x, y);
    }

    public static void mecanum(float speed, float x, float y, DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack) {
        float[] powers = mecanumPowers(speed, x, y);

        leftFront.setPower(powers[0]);
        leftBack.setPower(powers[1]);
        rightFront.setPower(powers[2]);
        rightBack.setPower(powers[3]);
    }

    public static void mecanum(float speed, float angle, DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack) {
        float[] powers = mecanumPowers(speed, angle);

        leftFront.setPower(powers[0]);
        leftBack.setPower(powers[1]);
        rightFront.setPower(powers[2]);
        rightBack.setPower(powers[3]);
    }
}
