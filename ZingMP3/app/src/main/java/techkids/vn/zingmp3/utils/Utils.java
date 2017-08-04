package techkids.vn.zingmp3.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by ADMIN on 7/29/2017.
 */

public class Utils {
    public static String convertTime(int time) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(time),
                TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
    }
}
