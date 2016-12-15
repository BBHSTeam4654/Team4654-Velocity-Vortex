package org.firstinspires.ftc.teamcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by exploravision on 12/10/2016.
 */
public class EncoderAutoOpMode extends BaseOpMode {

    DriveMode driveMode;
    Scanner scan;
    MovableState state;
    StringTokenizer tokenizer;

    public void init() {
        try {
            scan = new Scanner(new File("EncoderRecorded.txt"));
        } catch (FileNotFoundException e) {
            telemetry.addData("Error", e.getMessage());
        }


    }

    public void loop() {

        tokenizer = new StringTokenizer(scan.nextLine());
            int i = 0;
            while(tokenizer.hasMoreTokens()){
                state.data[i] = Double.parseDouble(tokenizer.nextToken());
            }



    }

    public void drive() {
        leftFront.setTargetPosition((int)state.data[0]);
        rightFront.setTargetPosition((int)state.data[1]);

        leftBack.setTargetPosition((int)state.data[2]);
        leftFront.setTargetPosition((int)state.data[3]);

        leftShooter.setTargetPosition((int)state.data[4]);
        rightShooter.setTargetPosition((int)state.data[5]);




    }

    private static class MovableState {
        public Object[] data = new Object[9];
    }
}