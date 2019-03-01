package com.zts.xtp.common.enums;

/**
 * 逐笔回报类型
 */
public enum TickByTickType {
    /**逐笔委托 */
    ORDER(1),
    /**逐笔成交 */
    TRADE(2);

    private int type;

    TickByTickType(int type) {
        this.type = type;
    }

    public int getTbtType() {
        return type;
    }
}
