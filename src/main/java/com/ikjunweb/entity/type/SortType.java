package com.ikjunweb.entity.type;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum SortType {
    TIME, POPULAR;

    public static Map<SortType, String> getSortTypeMap() {
        Map<SortType, String> result = new ConcurrentHashMap<>();
        for (SortType sortType : SortType.values()) {
            if (sortType == SortType.TIME) result.put(SortType.TIME, "최신순");
            else if (sortType == SortType.POPULAR) result.put(SortType.POPULAR, "인기순");
        }
        return result;
    }

    public static String getSortType(SortType sortType) {
        if (sortType == SortType.TIME) return "최신순";
        else if (sortType == SortType.POPULAR) return "인기순";
        return "찾을 수 없음";
    }
}
