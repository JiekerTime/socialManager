package com.jd.socialmanager.util;

public class UidGenerateUtil {
    public static String generateUid(int blockId){
        return blockId + System.currentTimeMillis() + "X";
    }
}
