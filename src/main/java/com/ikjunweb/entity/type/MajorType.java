package com.ikjunweb.entity.type;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum MajorType {
    COMPUTER, SOFTWARE, GLOBAL_MEDIA;

    public static Map<MajorType, String> getMajorTypeMap() {
        Map<MajorType, String> result = new ConcurrentHashMap<>();
        for (MajorType majorType : MajorType.values()) {
            if (majorType == MajorType.COMPUTER) {
                result.put(MajorType.COMPUTER, "컴퓨터학부");
            } else if (majorType == MajorType.SOFTWARE) {
                result.put(MajorType.SOFTWARE, "소프트웨어학부");
            } else if (majorType == MajorType.GLOBAL_MEDIA){
                result.put(MajorType.GLOBAL_MEDIA, "글로벌미디어학부");
            }
        }
        return result;
    }
}
