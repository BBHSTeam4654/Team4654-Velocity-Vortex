package org.firstinspires.ftc.teamcode;

import android.content.Context;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@TeleOp(name = "Recorder Mode")
public class RecorderOpMode extends DriverControlledOpMode {

    DriveMode driveMode = DriveMode.ONE_STICK;
    File file;
    FileWriter writer;
    GamepadState lastState1, lastState2;

    long start = System.currentTimeMillis();


    @Override
    public void start() {
        file = new File("controllerRecord.txt");
        telemetry.addData("File", file.getAbsolutePath());
        try {
            writer = new FileWriter(file);
        } catch (Exception e) {
            telemetry.addData("Error", e.getMessage());
        }
    }

    @Override

    public void loop() {
        // super.loop();
        //this section writes the controller state to the file
        telemetry.addData("Gamepads", gamepad1 + ", " + gamepad2);
        GamepadState state1 = new GamepadState(gamepad1), state2 = new GamepadState(gamepad2);
        String state = "";

        telemetry.addData("State 1", state1);
        telemetry.addData("State 1 Array", state1.array);
        telemetry.addData("State 2", state2);
        telemetry.addData("State 2 Array", state2.array);

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
            state += (i < state2.array.length) ? "," : ";";
        }

        try {
            writer.write(state + (System.currentTimeMillis() - start) + "\n");
        } catch (IOException e) {
            // error stuff goes in this line of this section of the code please thanky you
        }

        DriverControlledOpMode d = new DriverControlledOpMode();
        d.loop(); //please work idk if you will this s to move during recording
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


