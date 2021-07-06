package pers.tz.inventory.command;

import lombok.extern.slf4j.Slf4j;
import pers.tz.inventory.mockito.GoodsStockDAO;
import pers.tz.inventory.mockito.GoodsStockDO;
import pers.tz.inventory.mockito.StockStatus;

import java.util.Date;
import java.util.List;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 商品库存更新Command的抽象基类，方法模板模式和命令模式
 */
@Slf4j
public abstract class AbstractGoodsStockUpdaterCommand implements GoodsStockUpdaterCommand {

    protected List<GoodsStockDO> goodsStockDOs;
    protected GoodsStockDAO goodsStockDAO;

    public AbstractGoodsStockUpdaterCommand(List<GoodsStockDO> goodsStockDOs,
                                            GoodsStockDAO goodsStockDAO) {
        this.goodsStockDOs = goodsStockDOs;
        this.goodsStockDAO = goodsStockDAO;
    }

    @Override
    public Boolean updateGoodsStock() {
        try {
            // 1.更新可销售库存数
            this.updateSaleStockQuantity();
            // 2.更新锁定库存数
            this.updateLockedStockQuantity();
            // 3.更新已销售库存
            this.updateSaledStockQuantity();
            // 4.更新库存状态：有库存/无库存
            this.updateStockStatus();
            // 5.更新修改时间
            this.updateGmtModified();
            // 6.前面的操作只是在更新DO对象的属性，这一步才是去刷表，真正去更新
            this.executeUpdateGoodsStock();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 更新商品的库存状态
     *
     */
    private void updateStockStatus() {
        for (GoodsStockDO goodsStockDO : goodsStockDOs) {
            if (goodsStockDO.getSaleStockQuantity() > 0L) {
                goodsStockDO.setStockStatus(StockStatus.IN_STOCK);
            } else {
                goodsStockDO.setStockStatus(StockStatus.NOT_STOCK);
            }
        }
    }

    /**
     * 更新商品库存修改时间
     *
     */
    private void updateGmtModified() {
        for (GoodsStockDO goodsStockDO : goodsStockDOs) {
            goodsStockDO.setGmtModified(new Date());
        }
    }

    /**
     * 实际执行更新商品库存的操作(刷表)
     *
     */
    private void executeUpdateGoodsStock() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOs) {
            goodsStockDAO.updateGoodsStock(goodsStockDO);
        }
    }


    /**
     * 更新商品可销售库存数
     */
    protected abstract void updateSaleStockQuantity();

    /**
     * 更新商品锁定库存数
     */
    protected abstract void updateLockedStockQuantity();

    /**
     * 更新商品已销售库存数
     */
    protected abstract void updateSaledStockQuantity();

}
