package pers.tz.inventory.mockito;

import java.util.List;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 采购入库单DTO
 */
public class PurchaseInputOrderDTO {

    private List<PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOs;

    public List<PurchaseInputOrderItemDTO> getPurchaseInputOrderItemDTOs() {
        return purchaseInputOrderItemDTOs;
    }

    public void setPurchaseInputOrderItemDTOs(List<PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOs) {
        this.purchaseInputOrderItemDTOs = purchaseInputOrderItemDTOs;
    }
}
