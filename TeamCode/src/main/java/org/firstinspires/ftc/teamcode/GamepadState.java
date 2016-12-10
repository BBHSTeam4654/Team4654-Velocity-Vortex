package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by exploravision on 12/9/2016.
 */
public   class GamepadState {
    public Object[] array;
     public int timestamp;
    public GamepadState(Gamepad gamepad) {
        Object[] array = new Object[21];
        array[0] = gamepad.a;
        array[1] = gamepad.b;
        array[2] = gamepad.x;
        array[3] = gamepad.y;
        array[4] = gamepad.dpad_down;
        array[5] = gamepad.dpad_left;
        array[6] = gamepad.dpad_right;
        array[7] = gamepad.dpad_up;
        array[8] = gamepad.back;
        array[9] = gamepad.guide;
        array[10] = gamepad.start;
        array[11] = gamepad.left_bumper;
        array[12] = gamepad.left_stick_button;
        array[13] = gamepad.right_bumper;
        array[14] = gamepad.right_stick_button;
        array[15] = gamepad.left_stick_x;
        array[16] = gamepad.left_stick_y;
        array[17] = gamepad.left_trigger;
        array[18] = gamepad.right_stick_x;
        array[19] = gamepad.right_stick_y;
        array[20] = gamepad.right_trigger;
    }


    public GamepadState(){
        Object array = new Object[21];

    }

}