package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by exploravision on 2/2/2017.
 */

@Autonomous(name = "Red Auto Opmode", group = "test")
public class RedAutoOp extends LinearOpMode {

    public DcMotor leftFront, leftBack, rightFront, rightBack, rightShooter, leftShooter, paddle;
    public Servo pushLeft, pushRight, flipper;

    public long time;

    @Override
    public void runOpMode() throws InterruptedException {
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

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);








        pushRight.setDirection(Servo.Direction.REVERSE);

        pushLeft.setPosition(1);
        pushRight.setPosition(1);
        flipper.setPosition(1);

        waitForStart();

        time = System.currentTimeMillis();
    DcMotor[] motors = {leftFront,leftBack,rightFront,rightBack};


//move 8521 rot move 7521 rot move 3196

        runToPosition(motors, 8521);
        //rot
        runToPosition(motors,7521);
        //rot
        runToPosition(motors,3196);

    }



    public void runToPosition(DcMotor[] motors, int position){
        int currrentPosition  = motors[0].getCurrentPosition();
        int distanceTraveled =0;

        while(distanceTraveled<= position){
            for(DcMotor motor : motors){
                motor.setPower(100);

        }
    distanceTraveled += motors[0].getCurrentPosition()-currrentPosition;
            currrentPosition = motors[0].getCurrentPosition();


        }
}


public void rotate( int degrees){

}

}
