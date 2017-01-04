package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Time-based Auto Op (without ball)", group = "timebased")
public class TimeBasedAutoOpNoCapBall extends BaseOpMode {

    private long startTime;

    public void start() {
        super.start();

        startTime = System.currentTimeMillis();
    }

    public void loop() {
        double time = (System.currentTimeMillis() - startTime) / 1000F;
        accelerate(leftShooter, time < 15 ? 0.42 : 0, 0.05);
        accelerate(rightShooter, time < 15 ? 0.22 : 0, 0.05);

        accelerate(paddle, time > 7 && time < 15 ? -0.42 : 0, 0.1);
    }

}
