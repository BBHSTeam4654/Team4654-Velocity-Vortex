package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name = "Launch 1.0", group = "Launch")

public class BallLaunchAutoOp extends BaseOpMode {

    public double power = 0.4, cPower = 0.4;
    public boolean shooterOn = false, sBDown = false, upDown = false, downDown = false;
    public boolean converyerOn = false, cBDown = false, rightDown = false, leftDown = false;

    protected long startTime;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void loop() {
        float time = (System.currentTimeMillis() - startTime) / 1000F;

        if (gamepad1.y) {
            if (!sBDown) {
                shooterOn = !shooterOn;
                sBDown = true;
            }
        } else {
            sBDown = false;
        }

        if (gamepad1.b) {
            if (!cBDown) {
                converyerOn = !converyerOn;
                cBDown = true;
            }
        } else {
            cBDown = false;
        }

        if (gamepad1.dpad_up) {
            if (!upDown) {
                power = Math.min(1, power + .025);
                upDown = true;
            }
        } else {
            upDown = false;
        }
        if (gamepad1.dpad_down) {
            if (!downDown) {
                power = Math.max(0, power - .025);
                downDown = true;
            }
        } else {
            downDown = false;
        }
        if (gamepad1.dpad_right) {
            if (!rightDown) {
                cPower = Math.min(1, cPower + .05);
                rightDown = true;
            }
        } else {
            rightDown = false;
        }
        if (gamepad1.dpad_left) {
            if (!leftDown) {
                cPower = Math.max(0, cPower - .05);
                leftDown = true;
            }
        } else {
            leftDown = false;
        }

        accelerate(leftShooter, shooterOn ? power : 0, 0.05);
        accelerate(rightShooter, shooterOn ? power : 0, 0.05);

        accelerate(conveyor, converyerOn ? -cPower : 0, 0.1);

        telemetry.addData("Shooter On", shooterOn);
        telemetry.addData("Shooter Power", Math.round(power * 1000f) / 1000f);
        telemetry.addData("Conveyor On", converyerOn);
        telemetry.addData("Conveyor Power", Math.round(cPower));
    }

}
