package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name = "Owen's Vuforia fail", group = "vuforia")
public class VuforiaAutoOpO extends BaseOpMode {

    /**
     * Whether or not this op mode is for the red side (blue otherwise). Note that thought this class is named the red op mode, it is written so that subclasses can change this variable in [@link #init()}.
     */
    public boolean red = true;

    private VuforiaLocalizer vuforia;
    private VuforiaTrackables trackables;
    private VuforiaTrackable wheel; // Blue, near corner vortex
    private VuforiaTrackable tool; // Red, near center
    private VuforiaTrackable lego; // Blue, near center
    private VuforiaTrackable gear; // Red, near corner vortex

    private VuforiaTrackable target;
    private OpenGLMatrix lastPosition;
    private OpenGLMatrix toTarget;

    private int[][] field =
            {
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            };

    @Override
    public void init() {
        super.init();

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AV7cAYn/////AAAAGXDR1Nv900lOoewPO1Nq3ypDBIfk+d8X+UJOgVQZn5ZvQIY5Y4yGL6DVf24bEoMOVLCq5sZXPs9937r2zpeSZQaaaJbxeWggveVuvccsVlBdR38brId6fIRi/ssxtkUpVppCaRDO1N6K7IVbAJWrhpv1rG2DqTcS51znxjEYDE34AN6sNkurIq/qs0tLfvI+lx5VYRKdqh5LwnVt2HnpdX836kSbAN/1wnupzlLSKHcVPF9zlmRjCXrHduW8ikVefKAPGNCEzaDj4D+X+YM9iaHj9H8qN23bbaT81Ze3g5WwrXsb6dsX1N3+FqeXbiEUB02lXsmGwtvCJI89xutgPzlDAHqerduaLS2WZbL3oVyS";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        trackables = vuforia.loadTrackablesFromAsset("FieldTargets");
        wheel = trackables.get(0); // Blue, near corner vortex
        tool = trackables.get(1); // Red, near center
        lego = trackables.get(2); // Blue, near center
        gear = trackables.get(3); // Red, near corner vortex

        wheel.setLocation(OpenGLMatrix.translation(1828.8F, 304.8F, 152.4F).multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XZX, AngleUnit.DEGREES, 90, -90, 0)));
        lego.setLocation(OpenGLMatrix.translation(1828.8F, -914.4F, 152.4F).multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XZX, AngleUnit.DEGREES, 90, -90, 0)));
        tool.setLocation(OpenGLMatrix.translation(914.4F, -1828.8F, 152.4F).multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XZX, AngleUnit.DEGREES, 90, 0, 0)));
        gear.setLocation(OpenGLMatrix.translation(-304.8F, -1828.8F, 152.4F).multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XZX, AngleUnit.DEGREES, 90, 0, 0)));

        for (VuforiaTrackable vt : trackables) {
            ((VuforiaTrackableDefaultListener) vt.getListener()).setPhoneInformation(OpenGLMatrix.translation(0F, 0F, 0F).multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.YZY, AngleUnit.DEGREES, -90, 0, 0)), parameters.cameraDirection);
        }
    }

    @Override
    public void start() {
        trackables.activate();

        target = red ? gear : wheel;
    }


    @Override
    public void loop() {

        //  super.loop();

//        for (VuforiaTrackable vt : trackables) {
//            telemetry.addData(Integer.toHexString(System.identityHashCode(vt)), ((VuforiaTrackableDefaultListener) vt.getListener()).isVisible() ? "Visible" : "Not Visible");
//            OpenGLMatrix pos = ((VuforiaTrackableDefaultListener) vt.getListener()).getUpdatedRobotLocation();
//            telemetry.addData(vt.toString(), pos == null ? "NOT VISIBLE" : pos.formatAsTransform());
//        }

        VuforiaTrackableDefaultListener vt = (VuforiaTrackableDefaultListener) target.getListener();
        double[] position = {0,0,0};

        if (vt.isVisible()) {
            OpenGLMatrix pos = vt.getUpdatedRobotLocation();
            if (pos != null) {
                String coord = pos.formatAsTransform().substring(pos.formatAsTransform().indexOf("}")+3);
                position[0] = Integer.getInteger(coord.substring(0, coord.indexOf(",")));
                coord = coord.substring(coord.indexOf(",")+1);
                position[1] = Integer.getInteger(coord.substring(0, coord.indexOf(",")));
//                position[2] = Integer.ge

                /*lastPosition = pos;
                int x = ((int) pos.get(3, 0) + pos.get(0, 3) < 0 ? -50 : 50) / 100;//find the correct matrix locations
                int y = ((int) pos.get(3, 1) + pos.get(1, 3) < 0 ? -50 : 50) / 100;
                int lastX = ((int) lastPosition.get(0, 3) + lastPosition.get(0, 3) < 0 ? -50 : 50) / 100;
                int lasty = ((int) lastPosition.get(1, 3) + lastPosition.get(1, 3) < 0 ? -50 : 50) / 100;
                field[lasty / 2 + 9][lastX / 2 + 9] = 0;
                field[y / 2 + 9][x / 2 + 9] = 1;

                String row = "";
                for (int i = 0; i < 19; i++) {
                    for (int k : field[i]) {
                        row += k + ",";
                    }
                    telemetry.addData("Row", row);
                    row = "";
                }*/


                telemetry.addData("Test:", pos.formatAsTransform());
                telemetry.addData("matrix", pos.toString());
                //telemetry.addData("fdfdsfs","lkjlkj");
            }



        } else {
            //BasicDriveFunctions.rotate(0.5F, red, leftBack, leftFront, rightBack, rightFront);


        }



    }

}

