package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Time-based Auto Op (corner ramp, blue)", group = "timebased")
public class TimeBasedAutoOpCornerRampBlue extends TimeBasedAutoOpBase {

    public void loop() {
        super.loop();

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
