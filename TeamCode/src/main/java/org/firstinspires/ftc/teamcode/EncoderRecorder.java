package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by exploravision on 12/10/2016.
 */
@TeleOp(name = "Encoder Recorder")
public class EncoderRecorder extends BaseOpMode {

    FileWriter writer;
    File file;
    DriveMode driveMode;
    long start = System.currentTimeMillis();

    public void init() {
        try {
            writer = new FileWriter(new File("EncoderRecorded.txt"));
        } catch (Exception e) {
            telemetry.addData("Error", e.getMessage());
        }

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        conveyor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    public void loop() {
        String state = "";
        drive();
        state += leftFront.getCurrentPosition() + " " + rightFront.getCurrentPosition() + " " + leftBack.getCurrentPosition() + " " + rightBack.getCurrentPosition()
                + " " + leftShooter.getCurrentPosition() + " " + rightShooter.getCurrentPosition() + " " +
                conveyor.getCurrentPosition() + " " +
                pushRight.getPosition() + " " + pushLeft.getPosition() + "  " + (System.currentTimeMillis() - start) + "\n";

        try {
            writer.write(state);
        } catch (IOException e) {
            telemetry.addData("Error", e.getMessage());
        }
    drive();
    }

    public void drive() {
        try {
            if (gamepad1.dpad_up)
                driveMode = DriveMode.ONE_STICK;
            if (gamepad1.dpad_left)
                driveMode = DriveMode.TANK;
            if (gamepad1.dpad_down)
                driveMode = DriveMode.REVERSE;
            if (gamepad1.dpad_right)
                driveMode = DriveMode.MECANUM;

            float[] motorPowers = driveMode.getPowers(gamepad1, gamepad2);

            leftFront.setPower(motorPowers[0]);
            leftBack.setPower(motorPowers[1]);
            rightFront.setPower(motorPowers[2]);
            rightBack.setPower(motorPowers[3]);

            float shooter = gamepad2.right_trigger;

            leftShooter.setPower(shooter);
            rightShooter.setPower(shooter);

            conveyor.setPower(gamepad2.left_stick_y);
        } catch (Exception e) {
        }
    }


    @Override
    public void stop() {
        try {
            writer.close();
        } catch (IOException e) {
            telemetry.addData("Error", e.getMessage());
        }
    }
}
