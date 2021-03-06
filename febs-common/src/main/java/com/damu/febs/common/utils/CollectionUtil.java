package com.damu.febs.common.utils;

import java.util.List;
import java.util.Map;

public class CollectionUtil {

    /**
     * 判断List是否空，空返回True,非空返回false
     * @param list
     * @return
     */
    public static boolean isNullList(List<?> list) {

        return null == list || list.size() <= 0;
    }

    /**
     * 判断List是否不为空，非空返回True,空返回false
     * @param list
     * @return
     */
    public static boolean isNotNullList(List<?> list) {

        return null != list && list.size() > 0;
    }

    /**
     * 判断Map是否空，空返回True,非空返回false
     * @param map
     * @return
     */
    public static boolean isNullMap(Map<?,?> map) {

        return null == map || map.size() <= 0;
    }

    /**
     * 判断map是否不为空，非空返回True,空返回false
     * @param map
     * @return boolean
     */
    public static boolean isNotNullMap(Map<?,?> map) {

        return null != map && map.size() > 0;
    }
}
