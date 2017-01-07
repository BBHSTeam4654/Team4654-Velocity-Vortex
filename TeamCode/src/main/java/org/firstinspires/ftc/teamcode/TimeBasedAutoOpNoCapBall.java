package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Time-based Auto Op (without ball)", group = "timebased")
public class TimeBasedAutoOpNoCapBall extends BaseOpMode {

    private long startTime;
    private int paddleEncoderPosition;

    public void start() {
        super.start();

        startTime = System.currentTimeMillis();
        paddleEncoderPosition = paddle.getCurrentPosition();
    }

    public void loop() {
        double time = (System.currentTimeMillis() - startTime) / 1000F;
        accelerate(leftShooter, time < 15 ? 0.42 : 0, 0.05);
        accelerate(rightShooter, time < 15 ? 0.22 : 0, 0.05);

        accelerate(paddle, time > 7 && time < 14 && paddle.getCurrentPosition() - paddleEncoderPosition < ENCODER_PER_PADDLE ? -0.32 : 0, 0.1);
    }

}
