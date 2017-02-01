package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Time-based Auto Op (without ball)", group = "timebased")
public class TimeBasedAutoOpBase extends BaseOpMode {

    public double time;
    public long startTime;
    public int paddleEncoderPosition;

    public void start() {
        super.start();

        startTime = System.currentTimeMillis();
        paddleEncoderPosition = paddle.getCurrentPosition();
    }

    public void loop() {
        time = (System.currentTimeMillis() - startTime) / 1000F - 2;
        accelerate(leftShooter, time < 15 ? 0.17 : 0, 0.05);
        accelerate(rightShooter, time < 15 ? 0.17 : 0, 0.05);

        flipper.setPosition((time > 5 && time < 6) || (time > 12 && time < 13) ? 0.75F : 1);

        accelerate(paddle, time > 7 && time < 14 && paddle.getCurrentPosition() - paddleEncoderPosition < ENCODER_PER_PADDLE ? -0.2 : 0, 0.1);
    }

}
