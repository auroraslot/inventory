package pers.tz.inventory.command;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 库存更新Command顶级接口
 */
public interface GoodsStockUpdaterCommand {

    /**
     * 更新商品库存的逻辑
     * @return 处理结果
     */
    Boolean updateGoodsStock();

}
