package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class BaseOpMode extends OpMode {

    public DcMotor leftFront, leftBack, rightFront, rightBack, rightShooter, leftShooter, conveyor;
    public Servo pushLeft, pushRight;

    public void init() {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        rightShooter = hardwareMap.dcMotor.get("rightShooter");
        leftShooter = hardwareMap.dcMotor.get("leftShooter");
        conveyor = hardwareMap.dcMotor.get("leftPaddle");



        leftShooter.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);


        pushLeft = hardwareMap.servo.get("pushLeft");
        pushRight = hardwareMap.servo.get("pushRight");
    }

}
