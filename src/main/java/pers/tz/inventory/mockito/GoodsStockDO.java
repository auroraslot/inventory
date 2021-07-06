package pers.tz.inventory.mockito;

import java.util.Date;

/**
 * @auth tangweize
 * @date 2021/7/6
 * @desc 商品库存DO
 */
public class GoodsStockDO {

    private Long id;

    private Long goodsSkuId;

    private Long saleStockQuantity;

    private Long lockedStockQuantity;

    private Long saledStockQuantity;

    private Byte stockStatus;

    private Date gmtModified;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getSaleStockQuantity() {
        return saleStockQuantity;
    }

    public void setSaleStockQuantity(Long saleStockQuantity) {
        this.saleStockQuantity = saleStockQuantity;
    }

    public Byte getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Byte stockStatus) {
        this.stockStatus = stockStatus;
    }

    public Long getSaledStockQuantity() {
        return saledStockQuantity;
    }

    public void setSaledStockQuantity(Long saledStockQuantity) {
        this.saledStockQuantity = saledStockQuantity;
    }

    public Long getGoodsSkuId() {
        return goodsSkuId;
    }

    public void setGoodsSkuId(Long goodsSkuId) {
        this.goodsSkuId = goodsSkuId;
    }

    public Long getLockedStockQuantity() {
        return lockedStockQuantity;
    }

    public void setLockedStockQuantity(Long lockedStockQuantity) {
        this.lockedStockQuantity = lockedStockQuantity;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
