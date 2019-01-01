package com.game.cp.model.entity;

import com.game.cp.config.LotteryConfig;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "lottery_type_child")
public class LotteryChildEntity {
    private long lotteryChildId;
    private String lotteryChildName;
    private long lotteryId;
    private String desc;
    private int lotteryChildType;
    private int lotteryChildDuplexNum;//仅用于复式
    private double odds;//赔率 仅用于复式
    @Transient
    private List<LotteryItemEntity> lotteryItemEntityList;
    @Transient
    private List<LotteryItemEntity> orderList;//用于下单


    @Id
    @Column(name = "lottery_child_id", nullable = false)
    public long getLotteryChildId() {
        return lotteryChildId;
    }

    public void setLotteryChildId(long lotteryChildId) {
        this.lotteryChildId = lotteryChildId;
    }

    @Basic
    @Column(name = "lottery_child_name", nullable = false)
    public String getLotteryChildName() {
        return lotteryChildName;
    }

    public void setLotteryChildName(String lotteryChildName) {
        this.lotteryChildName = lotteryChildName;
    }

    @Basic
    @Column(name = "lottery_id", nullable = false)
    public long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(long lotteryId) {
        this.lotteryId = lotteryId;
    }

    @Basic
    @Column(name = "lottery_desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "lottery_child_type")
    public int getLotteryChildType() {
        return lotteryChildType;
    }

    public void setLotteryChildType(int lotteryChildType) {
        this.lotteryChildType = lotteryChildType;
    }

    @Basic
    @Column(name = "lottery_child_duplex_num")
    public int getLotteryChildDuplexNum() {
        return lotteryChildDuplexNum;
    }

    public void setLotteryChildDuplexNum(int lotteryChildDuplexNum) {
        this.lotteryChildDuplexNum = lotteryChildDuplexNum;
    }

    @Basic
    @Column(name = "odds")
    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    @Transient
    public List<LotteryItemEntity> getLotteryItemEntityList() {
        return lotteryItemEntityList;
    }

    public void setLotteryItemEntityList(List<LotteryItemEntity> lotteryItemEntityList) {
        this.lotteryItemEntityList = lotteryItemEntityList;
    }
    @Transient
    public List<LotteryItemEntity> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<LotteryItemEntity> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "LotteryChildEntity{" +
                "lotteryChildId=" + lotteryChildId +
                ", lotteryChildName='" + lotteryChildName + '\'' +
                ", okey_lottery_id=" + lotteryId +
                ", desc='" + desc + '\'' +
                ", lotteryChildType=" + lotteryChildType +
                ", lotteryChildDuplexNum=" + lotteryChildDuplexNum +
                ", odds=" + odds +
                ", lotteryItemEntityList=" + lotteryItemEntityList +
                ", orderList=" + orderList +
                '}';
    }

    /**
     * 获取订单显示文案
     *
     * @return
     */
    @Transient
    public String getOrderLotteryValue() {
        switch (getLotteryChildType()) {
            case LotteryConfig.LOTTERY_CHILD_TYPE_NORMAL:
                //非复式的订单显示方式
                List<LotteryItemEntity> lotteryItemEntityList = getOrderList();
                if (lotteryItemEntityList != null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    Iterator<LotteryItemEntity> iterator = lotteryItemEntityList.iterator();
                    while (iterator.hasNext()) {
                        LotteryItemEntity itemEntity = iterator.next();
                        if (itemEntity != null) {
                            stringBuffer.append(itemEntity.getLotteryItemName());
                            if (iterator.hasNext()) {
                                stringBuffer.append(",");
                            }
                        }
                    }
                    return stringBuffer.toString();
                }
                break;
            case LotteryConfig.LOTTERY_CHILD_TYPE_DUPLEX:
                //复式的订单显示
                List<LotteryItemEntity> items = getOrderList();
                if (items != null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    Iterator<LotteryItemEntity> iterator = items.iterator();
                    while (iterator.hasNext()) {
                        LotteryItemEntity item = iterator.next();
                        if (item != null) {
                            stringBuffer.append(item.getLotteryItemName());
                            if (iterator.hasNext()) {
                                stringBuffer.append("  ");
                            }
                        }
                    }
                    return stringBuffer.toString();
                }
                break;
            case LotteryConfig.LOTTERY_CHILD_TYPE_POSITION:
                items = getOrderList();
                if (items != null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    Iterator<LotteryItemEntity> iterator = items.iterator();
                    while (iterator.hasNext()) {
                        LotteryItemEntity item = iterator.next();
                        if (item != null) {
                            stringBuffer.append(item.getLotteryItemName());
                            if (iterator.hasNext()) {
                                stringBuffer.append("  ");
                            }
                        }
                    }
                    return stringBuffer.toString();
                }
                break;
        }
        return null;
    }

    /**
     * 获取订单总金额
     */
    @Transient
    public int getOrderTotalMoney() {
        int totalMoney = 0;
        List<LotteryItemEntity> lotteryItemEntityList = getOrderList();
        if (lotteryItemEntityList != null) {
            Iterator<LotteryItemEntity> iterator = lotteryItemEntityList.iterator();
            while (iterator.hasNext()) {
                LotteryItemEntity itemEntity = iterator.next();
                if (itemEntity != null) {
                    totalMoney += itemEntity.getTotalMoney();
                }
            }
        }
        return totalMoney;
    }

}
