package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Time-based Auto Op (with ball)", group = "timebased")
public class TimeBasedAutoOpCapBall extends BaseOpMode {

    private long startTime;
    private int paddleEncoderPosition;

    public void start() {
        super.start();

        startTime = System.currentTimeMillis();
        paddleEncoderPosition = paddle.getCurrentPosition();
    }

    public void loop() {
        super.loop();

        double time = (System.currentTimeMillis() - startTime) / 1000F;
        accelerate(leftShooter, time < 14 ? 0.35 : 0, 0.05);
        accelerate(rightShooter, time < 14 ? 0.35 : 0, 0.05);

        accelerate(paddle, time > 7 && time < 14 && paddle.getCurrentPosition() - paddleEncoderPosition < ENCODER_PER_PADDLE ? -0.32 : 0, 0.1);

        leftFront.setPower(time > 14 && time < 17 ? -1 : 0);
        leftBack.setPower(time > 14 && time < 17 ? -1 : 0);
        rightFront.setPower(time > 14 && time < 17 ? -1 : 0);
        rightBack.setPower(time > 14 && time < 17 ? -1 : 0);
    }

}
