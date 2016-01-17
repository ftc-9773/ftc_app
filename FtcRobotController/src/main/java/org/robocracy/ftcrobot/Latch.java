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
        this.leftLatch.scaleRange(0.05, 0.5);
        this.leftLatch.setPosition(1.0); // 1.0 is actually 0.5 because of the above scaleRange call
        this.rightLatch = rightLatch;
        this.rightLatch.setDirection(Servo.Direction.REVERSE);
//        this.rightLatch.scaleRange(0.505, 0.9);
        this.rightLatch.scaleRange(0.05, 0.5);
        this.rightLatch.setPosition(1.0); // 1.0 == 0.5 because of scaleRange call above
        this.robot = robot;
        double leftPosition = leftLatch.getPosition();
        double rightPosition = rightLatch.getPosition();
        DbgLog.msg(String.format("LeftLathPosition = %f, RightLatchPosition = %f", leftPosition, rightPosition));
    }

    /**
     * Moves latch servos based on {@code enum direction} value set in {@link DriverStation#getNextLatchCmd()}
     * @param drvrcmd {@link DriverCommand} object with values.
     */
    public void applyDSCmd(DriverCommand drvrcmd){
        double leftPosition = leftLatch.getPosition();
        double rightPosition = rightLatch.getPosition();
        DbgLog.msg(String.format("LeftLathPosition = %f, RightLatchPosition = %f", leftPosition, rightPosition));
        switch (drvrcmd.latchCmd.direction){
            case DOWN:
//                leftLatch.setPosition(Range.clip((leftPosition-0.01), 0, 1));
                rightLatch.setPosition(Range.clip((rightPosition-0.01), 0, 1));
                break;
            case UP:
//                leftLatch.setPosition(Range.clip((leftPosition+0.01), 0, 1));
                rightLatch.setPosition(Range.clip((rightPosition+0.01), 0, 1));
                break;
            case NONE:
                break;
            default:
                break;
        }
    }
}
