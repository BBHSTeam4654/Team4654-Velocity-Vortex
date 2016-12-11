package org.firstinspires.ftc.teamcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by exploravision on 12/10/2016.
 */
public class EncoderAutoOpMode extends BaseOpMode {

    DriveMode driveMode;
    Scanner scan;

    public void init() {
    try{scan = new Scanner(new File("EncoderRecorded.txt"));
    }
    catch (FileNotFoundException e){
     telemetry.addData("Error", e.getMessage());
    }



    }

    public void loop() {



    }

    public void drive(){
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
}
