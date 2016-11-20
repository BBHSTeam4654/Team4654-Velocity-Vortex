package org.firstinspires.ftc.teamcode;

import android.content.Context;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by exploravision on 11/20/2016.
 */
public class RecorderOpMode extends BaseOpMode {

    DriveMode driveMode = DriveMode.ONE_STICK;
    File file;
    FileWriter writer;
    GamepadState lastState1, lastState2;

    @Override
    public void start() {
        DateFormat date = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH);
        file = new File(hardwareMap.appContext.getFilesDir(), date.format(new Date()) + ".txt");
        try{
            writer = new FileWriter(file);
        } catch (Exception e) {
            // handle
        }
    }

    @Override
    public void loop() {
        if (gamepad1.dpad_up)
            driveMode = DriveMode.ONE_STICK;
        if (gamepad1.dpad_down)
            driveMode = DriveMode.TANK;

        float[] motorPowers = driveMode.getPowers(gamepad1, gamepad2);

        leftFront.setPower(motorPowers[0]);
        leftBack.setPower(motorPowers[1]);
        rightFront.setPower(motorPowers[2]);
        rightBack.setPower(motorPowers[3]);

        float shooter = gamepad2.right_trigger;

        leftShooter.setPower(shooter);
        rightShooter.setPower(shooter);

        conveyor.setPower(-gamepad2.left_stick_y);
        //this section writes the controller state to the file
        {
            try {
                GamepadState state1 = new GamepadState(gamepad1), state2 = new GamepadState(gamepad2);
                String state = "";
                for (int i = 0; i < state1.array.length; i++) {
                    if (state1.array[i] == lastState1.array[i]) {
                        state += state1.array[i];
                    }
                    state += (i < state1.array.length) ? "," : ";";
                }
                for (int i = 0; i < state2.array.length; i++) {
                    if (state2.array[i] == lastState2.array[i]) {
                        state += state2.array[i];
                    }
                    state += (i < state2.array.length) ? "," : "\n";
                }
                writer.write(state);
            } catch (IOException e) {

            }
        }
    }

    private class GamepadState {
        public Object[] array;

        public GamepadState(Gamepad gamepad) {
            Object[] array = new Object[21];
            array[0] = gamepad.a;
            array[1] = gamepad.b;
            array[2] = gamepad.x;
            array[3] = gamepad.y;
            array[4] = gamepad.dpad_down;
            array[5] = gamepad.dpad_left;
            array[6] = gamepad.dpad_right;
            array[7] = gamepad.dpad_up;
            array[8] = gamepad.back;
            array[9] = gamepad.guide;
            array[10] = gamepad.start;
            array[11] = gamepad.left_bumper;
            array[12] = gamepad.left_stick_button;
            array[13] = gamepad.right_bumper;
            array[14] = gamepad.right_stick_button;
            array[15] = gamepad.left_stick_x;
            array[16] = gamepad.left_stick_y;
            array[17] = gamepad.left_trigger;
            array[18] = gamepad.right_stick_x;
            array[19] = gamepad.right_stick_y;
            array[20] = gamepad.right_trigger;
        }
    }
}


