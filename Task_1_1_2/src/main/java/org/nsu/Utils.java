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
        switch (value) {
            case 2: {
                return "Двойка";
            }
            case 3: {
                return "Тройка";
            }
            case 4: {
                return "Четверка";
            }
            case 5: {
                return "Пятерка";
            }
            case 6: {
                return "Шестерка";
            }
            case 7: {
                return "Семерка";
            }
            case 8: {
                return "Восьмерка";
            }
            case 9: {
                return "Девятка";
            }
            case 10: {
                return "Десятка";
            }
            case 11: {
                return "Валет";
            }
            case 12: {
                return "Дама";
            }
            case 13: {
                return "Король";
            }
            default: {
                return "Туз";
            }
        }
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

