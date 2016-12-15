package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Auto Op (without ball)", group = "RedHook")
public class SomethingElseOpMode extends BaseOpMode {

    private long startTime;

    public void start() {
        super.start();

        startTime = System.currentTimeMillis();
    }

    public void loop() {
        double time = (System.currentTimeMillis() - startTime) / 1000F;
        accelerate(leftShooter, time < 15 ? 0.42 : 0, 0.05);
        accelerate(rightShooter, time < 15 ? 0.22 : 0, 0.05);

        accelerate(conveyor, time > 7 && time < 15 ? -0.42 : 0, 0.1);
    }

}
