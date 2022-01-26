package systems.fundur.FundurASM.util;

import java.util.Arrays;

public class Logger {
    private static boolean debug = false;

    public static final synchronized void log(Object o) {
        if (!debug) return;
        System.out.println(o.toString());
    }

    public static final synchronized void log(Object... os) {
        if (!debug) return;
        Arrays.stream(os).forEach(o -> System.out.print(o.toString() + " | "));
        System.out.println();
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        Logger.debug = debug;
    }
}
