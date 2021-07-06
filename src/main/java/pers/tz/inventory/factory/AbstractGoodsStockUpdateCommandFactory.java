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
 *
 * 通过Command的Factory，将Command与外界隔离起来，这里说的外界，其实就是ServiceImpl
 * 在Service中通过不同的Factory拿到对应的Command，然后去执行
 * 而在Factory中，不同的Factory同样会有一部分代码是可以共用的，因此也使用方法模板模式抽取出来
 * 由于Service中的参数是DTO对象，但是不同的Service会对应不同的DTO，因此这里用泛型<T>描述
 * $AbstractGoodsStockUpdateCommandFactory作为父类工厂，也作为方法模板
 * 它提供的create(T parameter)方法，并不会提供具体实现，也无法提供具体实现，因为不同的子类工厂应该提供不同的Command逻辑
 * 所以它在这里的create(T parameter)实现只是定义了create Command的步骤
 *
 * 我们知道，更新库存需要DO和DTO来做计算，DO(数据层的库存) +/- DTO(业务层变化的库存) = 新的库存
 * 因此要把DO以及DTO都提供给Command，又因为这两个领域对象都是随着Service变动的，因此作为参数传递进去
 * 同时，更新还需要DAO来刷表，因为这个DAO是不会变动的，所以直接通过构造方法传入
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
