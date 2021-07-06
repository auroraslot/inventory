package pers.tz.inventory.mockito;

import java.util.List;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 退货入库单DTO
 */
public class ReturnGoodsInputOrderDTO {

    private List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOs;

    public List<ReturnGoodsInputOrderItemDTO> getReturnGoodsInputOrderItemDTOs() {
        return returnGoodsInputOrderItemDTOs;
    }

    public void setReturnGoodsInputOrderItemDTOs(List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOs) {
        this.returnGoodsInputOrderItemDTOs = returnGoodsInputOrderItemDTOs;
    }
}
