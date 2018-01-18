package com.hyphenate.easeui.callback;

/**
 * 商品接口处理类
 * Created by tjstudy on 2017/11/3.
 */

public interface OnGoodsOperate {

    /**
     * 商品点击
     *
     * @param goodsInfo
     */
    void onGoodsClick(String goodsInfo);

    /**
     * 商品 提交订单
     *
     * @param goodsInfo
     */
    void onClickGoodsUrl(String goodsInfo);

    void onGoodsUrge(String goodsId);
}
