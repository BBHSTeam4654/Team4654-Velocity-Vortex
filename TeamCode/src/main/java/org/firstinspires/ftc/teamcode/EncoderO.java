package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Autonomous(name = "Owen's Test", group = "recorded")
@Disabled
public class EncoderO extends BaseOpMode{


    @Override
    public void init(){
        try {
            File directions = new File("EncoderDirections.txt");
            Scanner scan = new Scanner(directions);
        } catch (FileNotFoundException e) {
            telemetry.addData("Error:", e.getMessage());
        }


    }

    @Override
    public void loop(){

    }

}
