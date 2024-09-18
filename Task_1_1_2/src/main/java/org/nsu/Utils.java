package org.nsu;

/**
 * class Parent all our classes.
 * need fo something utils (dictNames and wait).
 */
public class Utils {
    /**
     * return correct string.
     *
     * @param value [1, 14]
     * @return string
     */
    public static String get(byte value) {
        return switch (value) {
            case 2 -> "Двойка";
            case 3 -> "Тройка";
            case 4 -> "Четверка";
            case 5 -> "Пятерка";
            case 6 -> "Шестерка";
            case 7 -> "Семерка";
            case 8 -> "Восьмерка";
            case 9 -> "Девятка";
            case 10 -> "Десятка";
            case 11 -> "Валет";
            case 12 -> "Дама";
            case 13 -> "Король";
            default -> "Туз";
        };
    }

    /**
     * wait n seconds for smoother gameplay.
     *
     * @param seconds seconds.
     */
    public static void wait(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch(InterruptedException ignored) {}
    }
}

