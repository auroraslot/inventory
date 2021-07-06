package pers.tz.inventory.service.impl;

import pers.tz.inventory.command.GoodsStockUpdaterCommand;
import pers.tz.inventory.factory.PurchaseInputStockUpdateCommandFactory;
import pers.tz.inventory.factory.ReturnGoodsInputStockUpdateCommandFactory;
import pers.tz.inventory.mockito.*;
import pers.tz.inventory.service.InventoryFacadeService;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 库存中心对外提供接口的实现类
 */
@Service
public class InventoryFacadeServiceImpl implements InventoryFacadeService {

    /**
     * 采购入库库存更新Command类的创建工厂实现类
     */
    @Autowired
    private PurchaseInputStockUpdateCommandFactory<PurchaseInputOrderDTO> purchaseInputStockUpdateCommandFactory;

    /**
     * 退货入库库存更新Command类的创建工厂实现类
     */
    @Autowired
    private ReturnGoodsInputStockUpdateCommandFactory<ReturnGoodsInputOrderDTO> returnGoodsInputStockUpdateCommandFactory;

    /**
     * 通知库存中心采购入库完成事件
     * @param purchaseInputOrderDTO 采购入库单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informPurchaseInputFinishedEvent(PurchaseInputOrderDTO purchaseInputOrderDTO) {
        // 采购入库完成，意味着要去更新库存
        // 而库存有3个：可销售库存，锁定库存，已销售库存，那要更新哪些库存？？
        // 采购入库：更新可销售库存
        // 退货入库：更新可销售库存，已销售库存
        // 除了这两个事件会引起库存变化，还有取消订单，支付订单，提交订单等
        // 未来可能会新增加或者维护旧的，所以这里使用命令模式

        // 除了更新库存，还要更新状态、修改时间等
        // 而更新状态、修改时间的逻辑是一样的，所以用方法模板模式，将公用的方法抽象到父类中

        GoodsStockUpdaterCommand goodsStockUpdaterCommand = purchaseInputStockUpdateCommandFactory.create(purchaseInputOrderDTO);
        return goodsStockUpdaterCommand.updateGoodsStock();
    }

    /**
     * 通知库存中心提交订单事件
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informSubmitOrderEvent(OrderDTO orderDTO) {
        return null;
    }

    /**
     * 通知库存中心支付订单事件
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informPayOrderEvent(OrderDTO orderDTO) {
        return null;
    }

    /**
     * 通知库存中心取消订单事件
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informCancelOrderEvent(OrderDTO orderDTO) {
        return null;
    }

    /**
     * 通知库存中心完成退货入库事件
     * @param returnGoodsInputOrderDTO 退货入库单DTO
     * @return 处理结果
     */
    @Override
    public Boolean informReturnGoodsInputFinishedEvent(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO) {
        GoodsStockUpdaterCommand goodsStockUpdaterCommand = returnGoodsInputStockUpdateCommandFactory.create(returnGoodsInputOrderDTO);
        return goodsStockUpdaterCommand.updateGoodsStock();
    }

    /**
     * 查询商品sku库存
     * @param goodsSkuId 商品sku id
     * @return 库存数量
     */
    @Override
    public Long getSaleStockQuantity(Long goodsSkuId) {
        return null;
    }
}
