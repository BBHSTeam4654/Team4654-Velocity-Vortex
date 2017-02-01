package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Time-based Auto Op (with ball)", group = "timebased")
public class TimeBasedAutoOpCapBall extends TimeBasedAutoOpBase {

    public void loop() {
        super.loop();

        leftFront.setPower(time > 14 && time < 17 ? -1 : 0);
        leftBack.setPower(time > 14 && time < 17 ? -1 : 0);
        rightFront.setPower(time > 14 && time < 17 ? -1 : 0);
        rightBack.setPower(time > 14 && time < 17 ? -1 : 0);
    }

}
