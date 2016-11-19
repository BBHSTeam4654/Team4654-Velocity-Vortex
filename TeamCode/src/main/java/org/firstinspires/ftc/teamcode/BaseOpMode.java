package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class BaseOpMode extends OpMode {

    public DcMotor leftFront, leftBack, rightFront, rightBack, rightShooter, leftShooter, conveyor;

    public void init() {
        leftFront = hardwareMap.dcMotor.get("leftfront");
        leftBack = hardwareMap.dcMotor.get("leftback");
        rightFront = hardwareMap.dcMotor.get("rightfront");
        rightBack = hardwareMap.dcMotor.get("rightback");
        rightShooter = hardwareMap.dcMotor.get("rightshooter");
        leftShooter = hardwareMap.dcMotor.get("leftshooter");
        conveyor = hardwareMap.dcMotor.get("rightpaddle");
    }

}
