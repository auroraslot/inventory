package pers.tz.inventory.factory;

import pers.tz.inventory.command.GoodsStockUpdaterCommand;
import pers.tz.inventory.mockito.GoodsStockDAO;
import pers.tz.inventory.mockito.GoodsStockDO;
import pers.tz.inventory.mockito.StockStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 库存更新Command类的工厂父类，模板方法模式
 */
public abstract class AbstractGoodsStockUpdateCommandFactory<T> implements GoodsStockUpdateCommandFactory<T> {
    // 商品库存DAO
    protected GoodsStockDAO goodsStockDAO;

    public AbstractGoodsStockUpdateCommandFactory(GoodsStockDAO goodsStockDAO) {
        this.goodsStockDAO = goodsStockDAO;
    }

    /**
     * 创建一个库存更新的Command类
     * @param parameter 参数对象
     * @return 库存更新的Command对象
     */
    @Override
    public GoodsStockUpdaterCommand create(T parameter){
        try {
            List<Long> goodsSkuIds = getGoodsSkuIds(parameter);
            List<GoodsStockDO> goodsStockDOs = createGoodsStockDOs(goodsSkuIds);
            return create(goodsStockDOs, parameter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 创建商品库存DOs
     * @param goodsSkuIds 商品sku ids
     * @return 商品库存DOs
     */
    private List<GoodsStockDO> createGoodsStockDOs(List<Long> goodsSkuIds) throws Exception {
        List<GoodsStockDO> goodsStockDOs = new ArrayList<>(goodsSkuIds.size());

        for (Long goodsSkuId : goodsSkuIds) {
            GoodsStockDO goodsStockDO = goodsStockDAO.getGoodsStockBySkuId(goodsSkuId);
            if (goodsStockDO == null) {
                goodsStockDO = new GoodsStockDO();
                goodsStockDO.setGoodsSkuId(goodsSkuId);
                goodsStockDO.setSaleStockQuantity(0L);
                goodsStockDO.setLockedStockQuantity(0L);
                goodsStockDO.setSaledStockQuantity(0L);
                goodsStockDO.setStockStatus(StockStatus.NOT_STOCK);
                goodsStockDO.setGmtCreate(new Date());
                goodsStockDO.setGmtModified(new Date());

                goodsStockDAO.saveGoodsStock(goodsStockDO);
            }
            goodsStockDOs.add(goodsStockDO);
        }
        return goodsStockDOs;
    }

    /**
     * 获取商品sku ids
     * @param parameter 参数对象
     * @return 商品sku ids
     */
    protected abstract List<Long> getGoodsSkuIds(T parameter) throws Exception;

    /**
     * 创建一个库存更新的Command类
     * @param goodsStockDOs 商品库存DOs
     * @param parameter 参数对象
     * @return Command类
     */
    protected abstract GoodsStockUpdaterCommand create(List<GoodsStockDO> goodsStockDOs, T parameter) throws Exception;
}
