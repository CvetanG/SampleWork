package bg.swift.order.rest.utils;

import bg.swift.order.rest.entities.User;

/**
 * Created by Anatoli.
 */
public class ValidateUtils {
    private ValidateUtils() {}

    public static boolean userIsNull(User u) {
        return u == null;
    }
}
