package pers.tz.inventory.mockito;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 采购入库单条目DTO
 */
public class PurchaseInputOrderItemDTO {

    private Long id;

    private Long arrivalCount;

    public Long getArrivalCount() {
        return arrivalCount;
    }

    public void setArrivalCount(Long arrivalCount) {
        this.arrivalCount = arrivalCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
