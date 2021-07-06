package pers.tz.inventory.factory;

import pers.tz.inventory.command.GoodsStockUpdaterCommand;
import pers.tz.inventory.command.ReturnGoodsInputStockUpdateCommand;
import pers.tz.inventory.mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 退货入库库存更新Command类的工厂
 */
@Component
public class ReturnGoodsInputStockUpdateCommandFactory<T> extends AbstractGoodsStockUpdateCommandFactory<T> {

    private GoodsStockDAO goodsStockDAO;

    @Autowired
    public ReturnGoodsInputStockUpdateCommandFactory(GoodsStockDAO goodsStockDAO) {
        super(goodsStockDAO);
    }

    @Override
    protected List<Long> getGoodsSkuIds(T parameter) throws Exception {
        ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO = (ReturnGoodsInputOrderDTO) parameter;
        List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOs = returnGoodsInputOrderDTO.getReturnGoodsInputOrderItemDTOs();
        if (returnGoodsInputOrderItemDTOs == null || returnGoodsInputOrderItemDTOs.size() <= 0) {
            return new ArrayList<>();
        }

        List<Long> goodsSkuIds = new ArrayList<>();
        for (ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO : returnGoodsInputOrderItemDTOs) {
            goodsSkuIds.add(returnGoodsInputOrderItemDTO.getId());
        }
        return goodsSkuIds;
    }

    @Override
    protected GoodsStockUpdaterCommand create(List<GoodsStockDO> goodsStockDOs, T parameter) throws Exception {

        ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO = (ReturnGoodsInputOrderDTO) parameter;
        List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOs = returnGoodsInputOrderDTO.getReturnGoodsInputOrderItemDTOs();

        if (returnGoodsInputOrderItemDTOs == null || returnGoodsInputOrderItemDTOs.size() <= 0) {
            return null;
        }

        Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap = new HashMap<>(returnGoodsInputOrderItemDTOs.size());
        for (ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO : returnGoodsInputOrderItemDTOs) {
            returnGoodsInputOrderItemDTOMap.put(returnGoodsInputOrderItemDTO.getId(), returnGoodsInputOrderItemDTO);
        }
        return new ReturnGoodsInputStockUpdateCommand(goodsStockDOs, goodsStockDAO, returnGoodsInputOrderItemDTOMap);
    }

}
