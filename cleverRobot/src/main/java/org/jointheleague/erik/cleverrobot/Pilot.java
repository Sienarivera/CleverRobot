package org.jointheleague.erik.cleverrobot;

import android.os.SystemClock;
import android.util.Log;

import org.jointheleague.erik.irobot.IRobotAdapter;
import org.jointheleague.erik.irobot.IRobotInterface;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;


public class Pilot extends IRobotAdapter {
    private static final String TAG = "GoldRush";
    // The following measurements are taken from the interface specification
    private static final double WHEEL_DISTANCE = 235.0; //in mm
    private static final double WHEEL_DIAMETER = 72.0; //in mm
    private static final double ENCODER_COUNTS_PER_REVOLUTION = 508.8;

    private final Dashboard dashboard;
    //    public UltraSonicSensors sonar;
    private int startLeft;
    private int startRight;
    private int countsToGoWheelLeft;
    private int countsToGoWheelRight;
    private int directionLeft;
    private int directionRight;
    private static final int STRAIGHT_SPEED = 200;
    private static final int TURN_SPEED = 100;

    int angle = 0;
    int initial = 0;


    private int currentCommand = 0;
    private final boolean debug = false; // Set to true to get debug messages.

    public Pilot(IRobotInterface iRobot, Dashboard dashboard, IOIO ioio)
            throws ConnectionLostException {
        super(iRobot);
//        sonar = new UltraSonicSensors(ioio);
        this.dashboard = dashboard;


    }

    /* This method is executed when the robot first startup. */
    public void initialize() throws ConnectionLostException {

        dashboard.log(dashboard.getString(R.string.hello));
        dashboard.speak("hi");
        //what would you like me to do, Clever Human?

        readSensors(SENSORS_ANGLE);
        initial = getAngle();

    }

    /* This method is called repeatedly. */
    public void loop() throws ConnectionLostException {

        readSensors(SENSORS_ANGLE);
        angle += getAngle();

//
//        driveDirect(300, 300);
//        SystemClock.sleep(2000);


        if (Math.abs(angle) > 120) {

            driveDirect(0, 0);

        } else {
            readSensors(SENSORS_INFRARED_BYTE);
            int infrared = getInfraredByte();
            dashboard.log("" + infrared);
            driveDirect(100, -100);
            if (infrared == 244 && infrared == 248) {
                dashboard.log("both");
                driveDirect(300, 300);
                SystemClock.sleep(10000);

            } else if (infrared == 248) {
                dashboard.log("red");
                driveDirect(200, 300);
                SystemClock.sleep(1000);

            } else if (infrared == 244) {
                dashboard.log("green");
                driveDirect(300, 200);
                SystemClock.sleep(1000);


            }
        }


//        dashboard.log(""+ infrared);
//        readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);


//


//         if (isBumpLeft()){
//            driveDirect(-400,-400);
//            SystemClock.sleep(500);
//            driveDirect(200,50);
//            SystemClock.sleep(500);
//        }
//
//        else if (isBumpRight()) {
//            driveDirect(-400,-400);
//            SystemClock.sleep(500);
//            driveDirect(50,200);
//            SystemClock.sleep(500);
//        }

    }
}


