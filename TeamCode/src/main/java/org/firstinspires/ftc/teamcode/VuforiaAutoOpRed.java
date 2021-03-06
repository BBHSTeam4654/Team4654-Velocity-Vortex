 package org.firstinspires.ftc.teamcode;
//XYZ RGB
import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.GyroSensor;

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


@Autonomous(name = "Vuforia Autonomous Mode (Red)", group = "vuforia")
public class VuforiaAutoOpRed extends BaseOpMode {
//when on red go for legos when on blue go for tools

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

    OpenGLMatrix prevPos = null;
    boolean ran = false;




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

        target = red ? tool : lego;
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

        if (vt.isVisible()) {
            if(!ran){
                prevPos = vt.getUpdatedRobotLocation();
                ran = true;
            }

            OpenGLMatrix pos = vt.getUpdatedRobotLocation();


            if (pos != null){ lastPosition = pos;
                telemetry.addData("fdfdsfs","lkjlkj");
            }

         try{
                telemetry.addData("translation",pos.getTranslation().toString());

          }catch(NullPointerException e){}
    prevPos =pos;

        } else {
            BasicDriveFunctions.rotate(0.5F, red, leftBack, leftFront, rightBack, rightFront);
            ran = false;


        }



    }


}

