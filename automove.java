import java.awt.AWTException;
import java.awt.Robot;
import java.util.Calendar;

//import java.awt.PointerInfo;
//import java.awt.MouseInfo;

public class automove {
    final static int MILLITOSECCALC = 60000;// 60*1000

    public static void main(String[] args) throws AWTException, InterruptedException {

        while (true) {
            automove auto = new automove();
            // weekend didn't work program
            if (auto.CalendarCheckWeekend()) {
            }else{
                // button sta
                automove.shiftAction();
                // program pause
                Thread.sleep(auto.CalendarCheckTime());
            }
        }
    }

    // keyboard keypress, keyrelease (ctrl)
    private static void shiftAction() throws AWTException {
        Robot robot = new Robot();
        //PointerInfo info;
        //key press
        robot.keyPress(17);
        robot.keyRelease(17);

        // mouse move
        //info = MouseInfo.getPointerInfo();
        //robot.mouseMove(info.getLocation().x+1, info.getLocation().y+1);
    }

    //weekend check
    private boolean CalendarCheckWeekend() {
        Calendar cal = Calendar.getInstance();
        if ((cal.get(Calendar.DAY_OF_WEEK) == 1) || (cal.get(Calendar.DAY_OF_WEEK) == 7)) {
            return true;
        }
        return false;
    }

    //stop time move for time
    private long CalendarCheckTime() {
        Calendar cal = Calendar.getInstance();
        int hour, minute;
        hour = cal.get(Calendar.HOUR);
        minute = cal.get(Calendar.MINUTE);

        // 09 O'clock start
        if (hour >= 9) {
            // 12 O'clock launch time before 13 O'clock
            if (hour >= 12) {
                if (hour < 13) {
                    minute = 60 - minute;
                    return minute * MILLITOSECCALC;// 13 O'clock - timenow
                } else if (hour < 18) {// before 18 O'clock 2 minute
                    return 2 * MILLITOSECCALC;
                } else {
                    // after 18 O'clock 09 O'clock
                    hour = 24 - hour + 9;
                    return hour * 60 * MILLITOSECCALC;
                }
            } else if (hour < 18) {
                // before 18 O'clock 2minute
                return 2 * MILLITOSECCALC;
            } else {
                // after 18 O'clock 09 O'clock
                hour = 24 - hour + 9;
                return hour * 60 * MILLITOSECCALC;
            }
        } else {
            // after 00 O'clock start time
            hour = 9 - hour;
            return hour * 60 * MILLITOSECCALC;
        }
    }
}