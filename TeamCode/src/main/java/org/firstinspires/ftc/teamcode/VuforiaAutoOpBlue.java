package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Vuforia Auto Op (Blue)", group = "vuforia")
public class VuforiaAutoOpBlue extends VuforiaAutoOpRed {

    @Override
    public void init() {
        super.init();
        this.red = false;
    }
}
