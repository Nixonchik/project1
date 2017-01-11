package com.korobkin.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * We send email to client to confirm his order.
 * we send orderId and code. If client sends request with this parameters.
 * This class consider Map to compare generated code.
 */
public class ConfirmOrderMap {
    private static ConfirmOrderMap instance = new ConfirmOrderMap();
    private Map<Integer, String> map = new HashMap<>();

    private ConfirmOrderMap() {}

    public static ConfirmOrderMap getInstance() { return instance; }

    public static String generateCode(int orderId) {
        StringBuilder result = new StringBuilder();
        for (int i=0; i< 45; i++) {
            result.append(new Random().nextInt(10));
        }
        String res = result.toString();
        synchronized (ConfirmOrderMap.class) {
            instance.map.put(orderId, res);
        }
        return res;
    }

    public static boolean isValidCode(int orderId, String code) {
        String expectedCode = instance.map.get(orderId);
        if (expectedCode == null || !expectedCode.equals(code)) return false;

        synchronized (ConfirmOrderMap.class) {
            instance.map.remove(orderId);
        }

        return true;
    }

}