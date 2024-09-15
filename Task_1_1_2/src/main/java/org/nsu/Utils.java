package org.nsu;

import java.util.HashMap;

/**
 * class Parent all our classes.
 * need fo something utils (dictNames and wait).
 */
public class Utils {
    HashMap<Byte, String> dictNames;

    /**
     * Build dict for translte int to name.
     */
    public Utils() {
        dictNames = new HashMap<>();
        dictNames.put((byte) 2, "Двойка");
        dictNames.put((byte) 3, "Тройка");
        dictNames.put((byte) 4, "Четверка");
        dictNames.put((byte) 5, "Пятерка");
        dictNames.put((byte) 6, "Шестерка");
        dictNames.put((byte) 7, "Семерка");
        dictNames.put((byte) 8, "Восьмерка");
        dictNames.put((byte) 9, "Девятка");
        dictNames.put((byte) 10, "Десятка");
        dictNames.put((byte) 11, "Валет");
        dictNames.put((byte) 12, "Дама");
        dictNames.put((byte) 13, "Король");
        dictNames.put((byte) 14, "Туз");
        dictNames.put((byte) 1, "Туз");
    }

    /**
     * wait n seconds for smoother gameplay.
     *
     * @param seconds seconds.
     */
    public void wait(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch(InterruptedException ignored) {}
    }
}

