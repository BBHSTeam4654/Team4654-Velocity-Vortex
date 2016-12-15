package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Auto Op (corner ramp, blue)", group = "RedHook")
public class EvenMoreSomethingsMode extends BaseOpMode {

    private long startTime;

    public void start() {
        super.start();

        startTime = System.currentTimeMillis();
    }

    public void loop() {
        double time = (System.currentTimeMillis() - startTime) / 1000F;
        accelerate(leftShooter, time < 14 ? 0.4 : 0, 0.05);
        accelerate(rightShooter, time < 14 ? 0.22 : 0, 0.05);

        accelerate(conveyor, time > 7 && time < 14 ? -0.42 : 0, 0.1);

        if (time > 14 && time < 15) {
            leftFront.setPower(-1);
            leftBack.setPower(-1);
            rightFront.setPower(-1);
            rightBack.setPower(-1);
        } else if (time > 15 && time < 15.8) {
            leftFront.setPower(1);
            leftBack.setPower(1);
            rightFront.setPower(-1);
            rightBack.setPower(-1);
        } else if (time > 17 && time < 19) {
            leftFront.setPower(-1);
            leftBack.setPower(-1);
            rightFront.setPower(-1);
            rightBack.setPower(-1);
        }
    }

}
