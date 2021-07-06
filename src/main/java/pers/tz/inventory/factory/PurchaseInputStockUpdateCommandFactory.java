package pers.tz.inventory.factory;

import pers.tz.inventory.command.GoodsStockUpdaterCommand;
import pers.tz.inventory.command.PurchaseInputStockUpdateCommand;
import pers.tz.inventory.mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 采购入库库存更新Command类的工厂
 */
@Component
public class PurchaseInputStockUpdateCommandFactory<T> extends AbstractGoodsStockUpdateCommandFactory<T> {

    // 商品库存DAO
    private GoodsStockDAO goodsStockDAO;

    @Autowired
    public PurchaseInputStockUpdateCommandFactory(GoodsStockDAO goodsStockDAO) {
        super(goodsStockDAO);
    }

    @Override
    protected List<Long> getGoodsSkuIds(T parameter) throws Exception {
        PurchaseInputOrderDTO purchaseInputOrderDTO = (PurchaseInputOrderDTO) parameter;
        List<PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOs = purchaseInputOrderDTO.getPurchaseInputOrderItemDTOs();

        if (purchaseInputOrderItemDTOs == null || purchaseInputOrderItemDTOs.size() <= 0) {
            return new ArrayList<>();
        }

        List<Long> goodsSkuIds = new ArrayList<>(purchaseInputOrderItemDTOs.size());
        for (PurchaseInputOrderItemDTO purchaseInputOrderItemDTO : purchaseInputOrderItemDTOs) {
            goodsSkuIds.add(purchaseInputOrderItemDTO.getId());
        }

        return goodsSkuIds;
    }

    @Override
    protected GoodsStockUpdaterCommand create(List<GoodsStockDO> goodsStockDOs, T parameter) throws Exception {
        PurchaseInputOrderDTO purchaseInputOrderDTO = (PurchaseInputOrderDTO) parameter;
        List<PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOs = purchaseInputOrderDTO.getPurchaseInputOrderItemDTOs();

        if (purchaseInputOrderItemDTOs == null || purchaseInputOrderItemDTOs.size() <= 0) {
            return null;
        }

        Map<Long, PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOMap = new HashMap<>(purchaseInputOrderItemDTOs.size());
        for (PurchaseInputOrderItemDTO purchaseInputOrderItemDTO : purchaseInputOrderItemDTOs) {
            purchaseInputOrderItemDTOMap.put(purchaseInputOrderItemDTO.getId(), purchaseInputOrderItemDTO);
        }

        return new PurchaseInputStockUpdateCommand(goodsStockDOs, goodsStockDAO, purchaseInputOrderItemDTOMap);
    }
}
