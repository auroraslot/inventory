package pers.tz.inventory.command;

import pers.tz.inventory.mockito.GoodsStockDAO;
import pers.tz.inventory.mockito.GoodsStockDO;
import pers.tz.inventory.mockito.PurchaseInputOrderItemDTO;

import java.util.List;
import java.util.Map;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 采购入库时商品库存更新的Command类
 */
public class PurchaseInputStockUpdateCommand extends AbstractGoodsStockUpdaterCommand {
    // 采购入库单的商品条目，商品的sku为key
    private final Map<Long, PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOMap;

    public PurchaseInputStockUpdateCommand(List<GoodsStockDO> goodsStockDOs, GoodsStockDAO goodsStockDAO,
                                           Map<Long, PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOMap) {
        super(goodsStockDOs, goodsStockDAO);
        this.purchaseInputOrderItemDTOMap = purchaseInputOrderItemDTOMap;
    }

    /**
     * 更新商品可销售库存数
     * 采购入库时仅更改可销售库存
     * 新的可销售库存 = 商品可销售库存 + 入库单中对应sku的商品条目的到货数量
     */
    @Override
    protected void updateSaleStockQuantity() {
        for (GoodsStockDO goodsStockDO : goodsStockDOs) {
            PurchaseInputOrderItemDTO purchaseInputOrderItemDTO = purchaseInputOrderItemDTOMap.get(goodsStockDO.getId());
            goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaleStockQuantity() + purchaseInputOrderItemDTO.getArrivalCount());
        }
    }

    /**
     * 更新商品锁定库存数
     * 采购入库时锁定库存不用更改
     */
    @Override
    protected void updateLockedStockQuantity() {}

    /**
     * 更新商品已销售库存数
     * 采购入库时已销售库存不用更改
     */
    @Override
    protected void updateSaledStockQuantity() {}
}
