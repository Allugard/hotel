package ua.allugard.hotel.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by allugard on 19.07.17.
 */
public class Hasher {

    public static String hashCode(String password) {
        return DigestUtils.md5Hex(password);
    }

}
