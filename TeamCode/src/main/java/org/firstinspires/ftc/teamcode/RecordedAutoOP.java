package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

@Autonomous(name = "Recorded Op Mode")
public class RecordedAutoOP extends BaseOpMode {

    Scanner scan;

    ArrayList<GamepadState> states1 = new ArrayList<GamepadState>();
    ArrayList<GamepadState> states2 = new ArrayList<GamepadState>();

    GamepadState state1,state2;

    long startTime = System.currentTimeMillis();
    DriveMode driveMode = DriveMode.ONE_STICK;
    int i = 0;

    public void init() {

        DateFormat date = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH);
        try {
           // scan = new Scanner(new File(date.format(new Date()) + ".txt"));
            scan = new Scanner(new File("controllerRecord.txt"));
        } catch (IOException e) {
            telemetry.addData("Error ", e.getMessage());
        }
        StringTokenizer tokenizer;

        //Loop through each line building a controller state for each part;
        while (scan.hasNextLine()) {
            tokenizer = new StringTokenizer(scan.nextLine());

            GamepadState g = new GamepadState();
            GamepadState g2 = new GamepadState();
            for (int i = 0; i < 43; i++) {
                if (i <= 21) {
                    g.array[i] = tokenizer.nextToken(";/\n ");
                } else if (i <= 42 && i > 21) {
                    g2.array[i - 21] = tokenizer.nextToken(";/\n ");
                } else {
                    g.array[43] = tokenizer.nextToken(";/\n ");
                    g2.array[43] = tokenizer.nextToken(";/\n ");

                }
            }
            states1.add(g);
            states2.add(g2);
        }

    }

    public void loop() {
        if (states1.get(i).timestamp >= System.currentTimeMillis() - startTime) {
            state1 = states1.get(i);
            state2 = states2.get(i);
            i++;
        }
        loop(state1, state2);
    }

    public void loop(GamepadState g1, GamepadState g2) {

        if ((boolean) g1.array[7])
            driveMode = DriveMode.ONE_STICK;
        if ((boolean) g1.array[5])
            driveMode = DriveMode.TANK;
        if ((boolean) g1.array[4])
            driveMode = DriveMode.REVERSE;

        float[] motorPowers = driveMode.getPowers(gamepad1, gamepad2);

        leftFront.setPower(motorPowers[0]);
        leftBack.setPower(motorPowers[1]);
        rightFront.setPower(motorPowers[2]);
        rightBack.setPower(motorPowers[3]);

        float shooter = gamepad2.right_trigger;

        leftShooter.setPower(shooter);
        rightShooter.setPower(shooter);

        conveyor.setPower((float) g2.array[16]);

        //Telemetry goes down down here upside down ?
        telemetry.addData("Front wheel power is " + leftFront.getPower() + " " + rightFront.getPower(), new Object());
    }
}
