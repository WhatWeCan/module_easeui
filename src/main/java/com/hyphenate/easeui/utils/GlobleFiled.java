package com.hyphenate.easeui.utils;

/**
 * 项目常量
 */

public class GlobleFiled {

    /**
     * 订单出租方式：1 按天出租 2 按月出租 3 按年出租，默认为 1
     */
    public static final byte RENT_TYPE_DAY = 1;
    public static final byte RENT_TYPE_WEEK = 2;
    public static final byte RENT_TYPE_MONTH = 3;
    public static final byte RENT_TYPE_YEAR = 4;

    public static String getRentType(int rent_way) {
        String rentStr;
        switch (rent_way) {
            case GlobleFiled.RENT_TYPE_MONTH:
                rentStr = "/月";
                break;
            case GlobleFiled.RENT_TYPE_YEAR:
                rentStr = "/年";
                break;
            case GlobleFiled.RENT_TYPE_WEEK:
                rentStr = "/周";
                break;
            default:
                rentStr = "/天";
        }
        return rentStr;
    }
}
