package ua.allugard.hotel.util;

/**
 * Created by allugard on 19.07.17.
 */
public class Resolver {
    private static String prefix = "/view/";
    private static String suffix = ".jsp";

    public static String resolve(String page) {
        return prefix + page + suffix;
    }
}
