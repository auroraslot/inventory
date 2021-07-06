package pers.tz.inventory.mockito;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 商品库存DAO
 */
public interface GoodsStockDAO {

    void updateGoodsStock(GoodsStockDO goodsStockDO);

    GoodsStockDO getGoodsStockBySkuId(Long id);

    void saveGoodsStock(GoodsStockDO goodsStockDO);
}
