package com.github.xlpmg.utils.java;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple utility to measure the runtime of code blocks.
 * Usage:
 * <pre>
 * {@code
 * Watch.start("myId");
 * // Code block to measure
 * System.out.println(Watch.stopFormatted("myId"));
 * }
 * </pre>
 */
public class Watch {

    // ID -> Start time
    private static final Map<String, Long> timeMap = new HashMap<>();

    public static void start(String id) {
        timeMap.put(id, System.currentTimeMillis());
    }

    public static Long getRuntime(String id) {
        return System.currentTimeMillis() - timeMap.get(id);
    }

    public static long stop(String id) {
        long elapsed = System.currentTimeMillis() - timeMap.get(id);
        timeMap.remove(id);
        return elapsed;
    }

    public static String stopFormatted(String id, boolean shortenUnits) {
        long elapsed = stop(id);
        long seconds = elapsed / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        long remainingSeconds = seconds % 60;
        long remainingMinutes = minutes % 60;
        long remainingHours = hours % 24;

        if (shortenUnits) {
            if (days > 0)
                return String.format("%dd, %02dh, %02dm, %02ds", days, remainingHours, remainingMinutes, remainingSeconds);
            else if (hours > 0)
                return String.format("%dh, %02dm, %02ds", remainingHours, remainingMinutes, remainingSeconds);
            else if (minutes > 0)
                return String.format("%dm, %02ds", remainingMinutes, remainingSeconds);
            else
                return String.format("%ds", seconds);
        } else {
            if (days > 0)
                return String.format("%d days, %02d hours, %02d minutes, %02d seconds", days, remainingHours, remainingMinutes, remainingSeconds);
            else if (hours > 0)
                return String.format("%d hours, %02d minutes, %02d seconds", remainingHours, remainingMinutes, remainingSeconds);
            else if (minutes > 0)
                return String.format("%d minutes, %02d seconds", remainingMinutes, remainingSeconds);
            else
                return String.format("%d seconds", seconds);
        }
    }

}
