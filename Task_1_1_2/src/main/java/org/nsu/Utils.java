package org.nsu;

/**
 * need for something utils (wait).
 */
public class Utils {
    /**
     * wait n seconds for smoother gameplay.
     *
     * @param seconds seconds.
     */
    public static void wait(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

