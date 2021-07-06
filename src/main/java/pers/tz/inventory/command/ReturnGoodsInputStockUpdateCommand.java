package pers.tz.inventory.command;

import pers.tz.inventory.mockito.GoodsStockDAO;
import pers.tz.inventory.mockito.GoodsStockDO;
import pers.tz.inventory.mockito.PurchaseInputOrderItemDTO;
import pers.tz.inventory.mockito.ReturnGoodsInputOrderItemDTO;

import java.lang.annotation.Retention;
import java.util.List;
import java.util.Map;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 退货入库时商品库存更新的Command类
 */
public class ReturnGoodsInputStockUpdateCommand extends AbstractGoodsStockUpdaterCommand {

    // 退货入库单的商品条目，商品的sku为key
    private final Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap;

    public ReturnGoodsInputStockUpdateCommand(List<GoodsStockDO> goodsStockDOs, GoodsStockDAO goodsStockDAO,
                                              Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap) {
        super(goodsStockDOs, goodsStockDAO);
        this.returnGoodsInputOrderItemDTOMap = returnGoodsInputOrderItemDTOMap;
    }

    /**
     * 更新商品可销售库存
     * 退货入库时，这个库存需要修改
     * 新的可销售库存 = 商品可销售库存 + 退货入库单中对应sku的商品条目的到货数量
     */
    @Override
    protected void updateSaleStockQuantity() {
        for (GoodsStockDO goodsStockDO : goodsStockDOs) {
            ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO = returnGoodsInputOrderItemDTOMap.get(goodsStockDO.getId());
            goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaleStockQuantity() + returnGoodsInputOrderItemDTO.getArrivalCount());
        }
    }

    /**
     * 退货入库时，锁定商品库存不用修改
     */
    @Override
    protected void updateLockedStockQuantity() {}

    /**
     * 更新已销售库存
     * 退货入库时，已销售库存需要减少
     * 新的已销售库存 = 商品已销售库存 - 退货入库单中对应sku的商品条目的到货数量
     */
    @Override
    protected void updateSaledStockQuantity() {
        for (GoodsStockDO goodsStockDO : goodsStockDOs) {
            ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO = returnGoodsInputOrderItemDTOMap.get(goodsStockDO.getId());
            goodsStockDO.setSaledStockQuantity(goodsStockDO.getSaledStockQuantity() - returnGoodsInputOrderItemDTO.getArrivalCount());
        }
    }
}
