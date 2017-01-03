package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Autonomous(name = "Owen's Test")
public class EncoderO extends BaseOpMode{


    @Override
    public void init(){
        try{
            File directions = new File("EncoderDirections.txt");
            Scanner scan = new Scanner(directions);
        }catch(FileNotFoundException e){
            telemetry.addData("Error:", e.getMessage());
        }


    }

    @Override
    public void loop(){

    }

}
