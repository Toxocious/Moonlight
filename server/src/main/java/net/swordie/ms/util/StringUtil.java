package net.swordie.ms.util;

/**
 * Provides a suite of utilities for manipulating strings.
 *
 * @author Frz
 * @since Revision 336
 * @version 1.0
 *
 */
public class StringUtil {

    /**
     * Gets a string padded from the left to <code>length</code> by
     * <code>padchar</code>.
     *
     * @param in The input string to be padded.
     * @param padchar The character to pad with.
     * @param length The length to pad to.
     * @return The padded string.
     */
    public static String getLeftPaddedStr(final String in, final char padchar, final int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int x = in.length(); x < length; x++) {
            builder.append(padchar);
        }
        builder.append(in);
        return builder.toString();
    }

    /**
     * Gets a string padded from the right to <code>length</code> by
     * <code>padchar</code>.
     *
     * @param in The input string to be padded.
     * @param padchar The character to pad with.
     * @param length The length to pad to.
     * @return The padded string.
     */
    public static String getRightPaddedStr(final String in, final char padchar, final int length) {
        StringBuilder builder = new StringBuilder(in);
        for (int x = in.length(); x < length; x++) {
            builder.append(padchar);
        }
        return builder.toString();
    }

    /**
     * Joins an array of strings starting from string <code>start</code> with a
     * space.
     *
     * @param arr The array of strings to join.
     * @param start Starting from which string.
     * @return The joined strings.
     */
    public static String joinStringFrom(final String arr[], final int start) {
        return joinStringFrom(arr, start, " ");
    }

    /**
     * Joins an array of strings starting from string <code>start</code> with
     * <code>sep</code> as a seperator.
     *
     * @param arr The array of strings to join.
     * @param start Starting from which string.
     * @param sep
     * @return The joined strings.
     */
    private static String joinStringFrom(final String arr[], final int start, final String sep) {
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < arr.length; i++) {
            builder.append(arr[i]);
            if (i != arr.length - 1) {
                builder.append(sep);
            }
        }
        return builder.toString();
    }

    /**
     * Makes an enum name human readable (fixes spaces, capitalization, etc)
     *
     * @param enumName The name of the enum to neaten up.
     * @return The human-readable enum name.
     */
    public static String makeEnumHumanReadable(final String enumName) {
        StringBuilder builder = new StringBuilder(enumName.length() + 1);
        for (String word : enumName.split("_")) {
            if (word.length() <= 2) {
                builder.append(word); // assume that it's an abbrevation
            } else {
                builder.append(word.charAt(0));
                builder.append(word.substring(1).toLowerCase());
            }
            builder.append(' ');
        }
        return builder.substring(0, enumName.length());
    }

    /**
     * Counts the number of <code>chr</code>'s in <code>str</code>.
     *
     * @param str The string to check for instances of <code>chr</code>.
     * @param chr The character to check for.
     * @return The number of times <code>chr</code> occurs in <code>str</code>.
     */
    public static int countCharacters(final String str, final char chr) {
        int ret = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == chr) {
                ret++;
            }
        }
        return ret;
    }

    public static String getReadableMillis(long startMillis, long endMillis) {
        StringBuilder sb = new StringBuilder();
        double elapsedSeconds = (endMillis - startMillis) / 1000.0;
        int elapsedSecs = ((int) elapsedSeconds) % 60;
        int elapsedMinutes = (int) (elapsedSeconds / 60.0);
        int elapsedMins = elapsedMinutes % 60;
        int elapsedHrs = elapsedMinutes / 60;
        int elapsedHours = elapsedHrs % 24;
        int elapsedDays = elapsedHrs / 24;
        if (elapsedDays > 0) {
            boolean mins = elapsedHours > 0;
            sb.append(elapsedDays);
            sb.append(" day").append(elapsedDays > 1 ? "s" : "").append(mins ? ", " : ".");
            if (mins) {
                boolean secs = elapsedMins > 0;
                if (!secs) {
                    sb.append("and ");
                }
                sb.append(elapsedHours);
                sb.append(" hour").append(elapsedHours > 1 ? "s" : "").append(secs ? ", " : ".");
                if (secs) {
                    boolean millis = elapsedSecs > 0;
                    if (!millis) {
                        sb.append("and ");
                    }
                    sb.append(elapsedMins);
                    sb.append(" minute").append(elapsedMins > 1 ? "s" : "").append(millis ? ", " : ".");
                    if (millis) {
                        sb.append("and ");
                        sb.append(elapsedSecs);
                        sb.append(" second").append(elapsedSecs > 1 ? "s" : "").append(".");
                    }
                }
            }
        } else if (elapsedHours > 0) {
            boolean mins = elapsedMins > 0;
            sb.append(elapsedHours);
            sb.append(" hour").append(elapsedHours > 1 ? "s" : "").append(mins ? ", " : ".");
            if (mins) {
                boolean secs = elapsedSecs > 0;
                if (!secs) {
                    sb.append("and ");
                }
                sb.append(elapsedMins);
                sb.append(" minute").append(elapsedMins > 1 ? "s" : "").append(secs ? ", " : ".");
                if (secs) {
                    sb.append("and ");
                    sb.append(elapsedSecs);
                    sb.append(" second").append(elapsedSecs > 1 ? "s" : "").append(".");
                }
            }
        } else if (elapsedMinutes > 0) {
            boolean secs = elapsedSecs > 0;
            sb.append(elapsedMinutes);
            sb.append(" minute").append(elapsedMinutes > 1 ? "s" : "").append(secs ? " " : ".");
            if (secs) {
                sb.append("and ");
                sb.append(elapsedSecs);
                sb.append(" second").append(elapsedSecs > 1 ? "s" : "").append(".");
            }
        } else if (elapsedSeconds > 0) {
            sb.append((int) elapsedSeconds);
            sb.append(" second").append(elapsedSeconds > 1 ? "s" : "").append(".");
        } else {
            sb.append("None.");
        }
        return sb.toString();
    }

    public static int getDaysAmount(long startMillis, long endMillis) {
        double elapsedSeconds = (endMillis - startMillis) / 1000.0;
        int elapsedMinutes = (int) (elapsedSeconds / 60.0);
        int elapsedHrs = elapsedMinutes / 60;
        int elapsedDays = elapsedHrs / 24;
        return elapsedDays;
    }

    public static int getOptionalIntArg(String[] splitted, int position, int def) {
        if (splitted.length > position) {
            try {
                return Integer.parseInt(splitted[position]);
            } catch (NumberFormatException nfe) {
                return def;
            }
        }
        return def;
    }

    public static boolean isNumber(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
