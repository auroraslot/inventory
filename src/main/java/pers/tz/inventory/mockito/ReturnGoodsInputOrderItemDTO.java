package pers.tz.inventory.mockito;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 退货入库单DTO
 */
public class ReturnGoodsInputOrderItemDTO {

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
