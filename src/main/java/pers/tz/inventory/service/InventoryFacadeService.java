package pers.tz.inventory.service;

import pers.tz.inventory.mockito.OrderDTO;
import pers.tz.inventory.mockito.PurchaseInputOrderDTO;
import pers.tz.inventory.mockito.ReturnGoodsInputOrderDTO;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 库存中心对外提供的接口
 */
public interface InventoryFacadeService {

    /**
     * 通知库存中心采购入库完成事件
     * @param purchaseInputOrderDTO 采购入库单DTO
     * @return 处理结果
     */
    Boolean informPurchaseInputFinishedEvent(PurchaseInputOrderDTO purchaseInputOrderDTO);

    /**
     * 通知库存中心提交订单事件
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean informSubmitOrderEvent(OrderDTO orderDTO);

    /**
     * 通知库存中心支付订单事件
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean informPayOrderEvent(OrderDTO orderDTO);

    /**
     * 通知库存中心取消订单事件
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean informCancelOrderEvent(OrderDTO orderDTO);

    /**
     * 通知库存中心完成退货入库事件
     * @param returnGoodsInputOrderDTO 退货入库单DTO
     * @return 处理结果
     */
    Boolean informReturnGoodsInputFinishedEvent(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO);

    /**
     * 查询商品sku库存
     * @param goodsSkuId 商品sku id
     * @return 库存数量
     */
    Long getSaleStockQuantity(Long goodsSkuId);

}
