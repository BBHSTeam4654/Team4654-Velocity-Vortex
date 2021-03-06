package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Driver Controlled Mode")
public class DriverControlledOpMode extends BaseOpMode {

    protected DriveMode driveMode = DriveMode.ONE_STICK;

    protected long pushLeftTime, pushRightTime;
    protected boolean pushLeftDown, pushRightDown;
    
    @Override
    public void loop() {
        try {
            super.loop();

            if (gamepad1.dpad_up)
                driveMode = DriveMode.ONE_STICK;
            if (gamepad1.dpad_left)
                driveMode = DriveMode.TANK;
            if (gamepad1.dpad_down)
                driveMode = DriveMode.REVERSE;
            if (gamepad1.dpad_right)
                driveMode = DriveMode.MECANUM;
            if(gamepad2.left_trigger > .1)
                release.setPosition(0);
            telemetry.addData("Drive Mode: ", driveMode.toString());

            float multiplier = 1.0F - gamepad1.left_trigger * 0.9F;
            float[] motorPowers = driveMode.getPowers(gamepad1, gamepad2);

            leftFront.setPower(multiplier * motorPowers[0]);
            leftBack.setPower(multiplier * motorPowers[1]);
            rightFront.setPower(multiplier * motorPowers[2]);
            rightBack.setPower(multiplier * motorPowers[3]);

            float shooter = gamepad2.dpad_down ? -0.1F : (gamepad2.left_bumper || gamepad2.y ? 0.17F : gamepad2.right_trigger * 0.3F);

            accelerate(leftShooter, shooter, 0.075);
            accelerate(rightShooter, shooter, 0.075);

            paddle.setPower(gamepad2.left_stick_y * 0.25);

            if (gamepad2.left_bumper && System.currentTimeMillis() - pushLeftTime > 1000) {
                pushLeftDown = !pushLeftDown;
                pushLeftTime = System.currentTimeMillis();
                pushLeft.setPosition(pushLeftDown ? 0.35 : 1);
            }
            
            if (gamepad2.right_bumper && System.currentTimeMillis() - pushRightTime > 1000) {
                pushRightDown = !pushRightDown;
                pushRight.setPosition(pushRightDown ? 0.35 : 1);
            }

            flipper.setPosition(gamepad2.b || gamepad2.a ? 0.75 : 1);
        } catch (Exception e) {
            telemetry.addData("Error: ", e.toString());
        }
    }

}

