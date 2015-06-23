package org.jointheleague.erik.cleverrobot;

import android.os.SystemClock;
import android.util.Log;

import org.jointheleague.erik.irobot.IRobotAdapter;
import org.jointheleague.erik.irobot.IRobotInterface;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;

public class GoldRush extends IRobotAdapter{
    private static final String TAG = "Pilot";
    // The following measurements are taken from the interface specification
    private static final double WHEEL_DISTANCE = 235.0; //in mm
    private static final double WHEEL_DIAMETER = 72.0; //in mm
    private static final double ENCODER_COUNTS_PER_REVOLUTION = 508.8;

    private final Dashboard dashboard;
//    public UltraSonicSensors sonar; hgfytfytrsfxhgihilhihuig

    private int startLeft;
    private int startRight;
    private int countsToGoWheelLeft;
    private int countsToGoWheelRight;
    private int directionLeft;
    private int directionRight;
    private static final int STRAIGHT_SPEED = 200;
    private static final int TURN_SPEED = 100;

    private int currentCommand = 0;
    private final boolean debug = false; // Set to true to get debug messages.

    public GoldRush(IRobotInterface iRobot, Dashboard2 dashboard, IOIO ioio)
            throws ConnectionLostException {
        super(iRobot);
//        sonar = new UltraSonicSensors(ioio);
        this.dashboard = dashboard;
    }

    /* This method is executed when the robot first starts up. */
    public void initialize() throws ConnectionLostException {
        dashboard.log(dashboard.getString(R.string.hello));
        //what would you like me to do, Clever Human?



    }

    /* This method is called repeatedly. */
    public void loop() throws ConnectionLostException {

        driveDirect(100,100);
        SystemClock.sleep(500);
        readSensors(SENSORS_INFRARED_BYTE);
        readSensors (SENSORS_BUMPS_AND_WHEEL_DROPS);

        if (getInfraredByte(248)){


        }

        if (isBumpLeft()){
            driveDirect(-400,-400);
            SystemClock.sleep(500);
            driveDirect(200,50);
            SystemClock.sleep(500);
        }

        else if (isBumpRight()) {
            driveDirect(-400,-400);
            SystemClock.sleep(500);
            driveDirect(50,200);
            SystemClock.sleep(500);
        }

    }
}
