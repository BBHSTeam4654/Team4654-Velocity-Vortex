package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class BaseOpMode extends OpMode {

    public static final int ENCODER_PER_PADDLE = 525; // 800 for neverest 60; don't remember which

    public DcMotor leftFront, leftBack, rightFront, rightBack, rightShooter, leftShooter, paddle;
    public Servo pushLeft, pushRight, flipper;

    public long lastTime = 0;
    public int lastEncoder = 0;

    public void init() {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        rightShooter = hardwareMap.dcMotor.get("rightShooter");
        leftShooter = hardwareMap.dcMotor.get("leftShooter");
        paddle = hardwareMap.dcMotor.get("paddle");

        leftShooter.setDirection(DcMotor.Direction.REVERSE);
        leftShooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry.addData("RunMode", leftShooter.getMode().name());
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);

        pushLeft = hardwareMap.servo.get("pushLeft");
        pushRight = hardwareMap.servo.get("pushRight");
        flipper = hardwareMap.servo.get("flipper");

        pushRight.setDirection(Servo.Direction.REVERSE);

        pushLeft.setPosition(1);
        pushRight.setPosition(1);
        flipper.setPosition(1);
    }

    public void start() {
        leftShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void loop() {
        telemetry.addData("Left Front Power", leftFront.getPower());
        telemetry.addData("Left Back Power", leftBack.getPower());
        telemetry.addData("Right Front Power", rightFront.getPower());
        telemetry.addData("Right Back Power", rightBack.getPower());
        telemetry.addData("Right Shooter Power", rightShooter.getPower());
        telemetry.addData("Left Shooter Power", leftShooter.getPower());
        telemetry.addData("Paddle Power", paddle.getPower());

        telemetry.addData("Push Left Pos", pushLeft.getPosition());
        telemetry.addData("Push Right Pos", pushRight.getPosition());
        telemetry.addData("Flipper Pos", flipper.getPosition());

        long time = System.currentTimeMillis();
        if (time > 250) {
            int encoder = leftShooter.getCurrentPosition();
            telemetry.addData("Left Shooter RPM", 1748.0 * (encoder - lastEncoder) / (time - lastTime));
            telemetry.addData("Encoder Pos", encoder);
            lastEncoder = encoder;
            lastTime = time;
        }
    }

    public void accelerate(DcMotor motor, double targetValue, double maxChange) {
        double power = motor.getPower();
        if (targetValue > power) {
            if ((power += maxChange) > targetValue) power = targetValue;
        } else {
            if ((power -= maxChange) < targetValue) power = targetValue;
        }
        motor.setPower(Range.clip(power, -1, 1));
    }
}
