package pers.tz.inventory.factory;

import pers.tz.inventory.command.GoodsStockUpdaterCommand;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 库存更新Command类的工厂顶级接口
 */
public interface GoodsStockUpdateCommandFactory<T> {

    /**
     * 创建一个商品库存更新Command类
     *
     * @param parameter 参数对象
     * @return 商品库存更新Command类
     */
    GoodsStockUpdaterCommand create(T parameter);

}
