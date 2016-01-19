package org.robocracy.ftcrobot;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.hardware.Servo;

import org.robocracy.ftcrobot.DriverStation.DriverStation;
import org.robocracy.ftcrobot.FTCRobot;
import org.robocracy.ftcrobot.DriverStation.DriverCommand;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

/**
 * @author Team Robocracy
 *
 * Operates latches on robot that hold on to churros
 */
public class Latch {
    FTCRobot robot;
    LinearOpMode curOpMode;
    Servo leftLatch;
    Servo rightLatch;

    public Latch(FTCRobot robot, Servo leftLatch, Servo rightLatch, LinearOpMode curOpMode){
        this.curOpMode = curOpMode;
        this.leftLatch = leftLatch;
        this.rightLatch = rightLatch;
        this.robot = robot;
    }

    /**
     * Moves latch servos based on {@code enum direction} value set in {@link DriverStation#getNextLatchCmd()}
     * @param drvrcmd {@link DriverCommand} object with values.
     */
    public void applyDSCmd(DriverCommand drvrcmd){
        double leftPosition = leftLatch.getPosition();
        double rightPosition = rightLatch.getPosition();
        switch (drvrcmd.latchCmd.direction){
            case DOWN:
                leftLatch.setPosition(0.1);
                rightLatch.setPosition(1.0);
                break;
            case UP:
                leftLatch.setPosition(0.7);
                rightLatch.setPosition(0.3);
                break;
            case NONE:
                break;
            default:
                break;
        }
    }
}
