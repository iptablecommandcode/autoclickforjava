import java.awt.AWTException;
import java.awt.Robot;
import java.util.Calendar;

//import java.awt.PointerInfo;
//import java.awt.MouseInfo;

public class automove {
    final static int MILLITOSECCALC = 2 * 60 * 1000;// 60*1000

    public static void main(String[] args) throws AWTException, InterruptedException {
        long timecheck;
        while (true) {
            automove auto = new automove();
            // weekend didn't work program
            if (auto.CalendarCheckWeekend()) {
            } else {
                timecheck = auto.CalendarCheckTime();
                if (timecheck != 0) {
                    // button sta
                    automove.shiftAction();
                    // program pause
                    Thread.sleep(timecheck);
                }
            }
        }
    }

    // keyboard keypress, keyrelease (ctrl)
    private static void shiftAction() throws AWTException {
        Robot robot = new Robot();
        // PointerInfo info;
        // key press
        robot.keyPress(17);
        robot.keyRelease(17);

        // mouse move
        // info = MouseInfo.getPointerInfo();
        // robot.mouseMove(info.getLocation().x+1, info.getLocation().y+1);
    }

    // weekend check
    private boolean CalendarCheckWeekend() {
        Calendar cal = Calendar.getInstance();
        if ((cal.get(Calendar.DAY_OF_WEEK) == 1) || (cal.get(Calendar.DAY_OF_WEEK) == 7)) {
            return true;
        }
        return false;
    }

    // stop time move for time
    private long CalendarCheckTime() {
        Calendar cal = Calendar.getInstance();
        int hour, AM_PM;

        AM_PM = cal.get(Calendar.AM_PM);
        hour = cal.get(Calendar.HOUR);

        if (AM_PM == 0) {// AM
            if (hour >= 9) {
                return MILLITOSECCALC;
            } else {
                return 0;
            }
        } else {// PM
            if (hour == 0) {
                return 0;
            } else if (hour >= 1) {
                if (hour >= 6) {
                    return 0;
                } else {
                    return MILLITOSECCALC;
                }
            } else {
                return 0;
            }
        }
    }
}