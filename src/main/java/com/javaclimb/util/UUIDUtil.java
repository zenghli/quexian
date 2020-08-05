package com.javaclimb.util;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {

    public static String getUUID() {
        String str = UUID.randomUUID().toString();
        str = str.replace("-", "");
        return str;
    }

    public static String getOrderSn() {
        String uuid = getUUID();
        return uuid + new Random().nextInt(1000);
    }

}
