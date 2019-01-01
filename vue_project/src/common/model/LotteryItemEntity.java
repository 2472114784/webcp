package com.game.cp.model.entity;

import com.game.cp.config.LotteryConfig;
import com.game.cp.utils.StringUtils;

import javax.persistence.*;

@Entity
@Table(name = "lottery_type_item")
public class LotteryItemEntity {
    private long lotteryItemId;
    private String lotteryItemName;
    private double odds;
    private String desc;
    private long lotteryChildId;
    @Transient
    private double money;//单注的金额
    @Transient
    private int lotteryChildType;//用于区分单式 复式等
    @Transient
    private int lotteryDuplexNum;//复式时需要的个数
    @Transient
    private String lotteryItemValue;//用于存放值
    @Transient
    private boolean isSelected;

    @Id
    @Column(name = "lottery_item_id", nullable = false)
    public long getLotteryItemId() {
        return lotteryItemId;
    }

    public void setLotteryItemId(long lotteryItemId) {
        this.lotteryItemId = lotteryItemId;
    }

    @Basic
    @Column(name = "lottery_item_name")
    public String getLotteryItemName() {
        return lotteryItemName;
    }

    public void setLotteryItemName(String lotteryItemName) {
        this.lotteryItemName = lotteryItemName;
    }

    @Basic
    @Column(name = "odds", nullable = false)
    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
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
    @Column(name = "lottery_child_id", nullable = false)
    public long getLotteryChildId() {
        return lotteryChildId;
    }

    public void setLotteryChildId(long lotteryChildId) {
        this.lotteryChildId = lotteryChildId;
    }

    @Transient
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Transient
    public int getLotteryChildType() {
        return lotteryChildType;
    }

    public void setLotteryChildType(int lotteryChildType) {
        this.lotteryChildType = lotteryChildType;
    }

    @Transient
    public int getLotteryDuplexNum() {
        return lotteryDuplexNum;
    }

    public void setLotteryDuplexNum(int lotteryDuplexNum) {
        this.lotteryDuplexNum = lotteryDuplexNum;
    }

    @Transient
    public String getLotteryItemValue() {
        return lotteryItemValue;
    }

    public void setLotteryItemValue(String lotteryItemValue) {
        this.lotteryItemValue = lotteryItemValue;
    }

    @Override
    public String toString() {
        return "LotteryItemEntity{" +
                "lotteryItemId=" + lotteryItemId +
                ", lotteryItemName='" + lotteryItemName + '\'' +
                ", odds=" + odds +
                ", desc='" + desc + '\'' +
                ", money=" + money +
                ", lotteryChildType=" + lotteryChildType +
                ", lotteryDuplexNum=" + lotteryDuplexNum +
                ", lotteryItemValue='" + lotteryItemValue + '\'' +
                ", lotteryChildId=" + lotteryChildId +
                ", isSelected=" + isSelected +
                '}';
    }

    /**
     * 检测数据是否符合下单要求
     *
     * @return
     */
    @Transient
    public boolean checkData() {
        switch (getLotteryChildType()) {
            case LotteryConfig.LOTTERY_CHILD_TYPE_NORMAL:
                if (getLotteryItemId() == 0 || getOdds() == 0) {
                    return false;
                }
                break;
            case LotteryConfig.LOTTERY_CHILD_TYPE_DUPLEX:
                if (StringUtils.isEmpty(getLotteryItemValue())) {
                    return false;
                }
                if (getLotteryDuplexNum() == 0 || getOdds() == 0) {
                    return false;
                }
                String[] items = getLotteryItemValue().split("\\|",-1);
                if (items == null || items.length != getLotteryDuplexNum()) {
                    return false;
                }
                break;
            case LotteryConfig.LOTTERY_CHILD_TYPE_POSITION:
                if (StringUtils.isEmpty(getLotteryItemValue())) {
                    return false;
                }
                if (getLotteryDuplexNum() <= 0 || getOdds() <= 0) {
                    return false;
                }
                String[] positonItems = getLotteryItemValue().split("\\|",-1);

                if (positonItems == null || positonItems.length != getLotteryDuplexNum()) {
                    return false;
                }
                break;
        }
        return true;
    }

    /**
     * 获取订单数
     */
    @Transient
    public int getOrderNum() {
        switch (getLotteryChildType()) {
            case LotteryConfig.LOTTERY_CHILD_TYPE_NORMAL:
                return 1;
            case LotteryConfig.LOTTERY_CHILD_TYPE_DUPLEX:
                int count = 1;
                String lotteryItemValue = getLotteryItemValue();
                if (!StringUtils.isEmpty(lotteryItemValue)) {
                    String[] split = lotteryItemValue.split("\\|",-1);
                    if (split != null) {
                        for (int i = 0; i < split.length; i++) {
                            String temp = split[i];
                            if (StringUtils.isEmpty(temp)) {
                                return 0;
                            }
                            String[] items = temp.split(",");
                            count *= items.length;
                        }
                    }
                }
                return count;
            case LotteryConfig.LOTTERY_CHILD_TYPE_POSITION:
                count = 0;
                lotteryItemValue = getLotteryItemValue();
                if (!StringUtils.isEmpty(lotteryItemValue)) {
                    String[] split = lotteryItemValue.split("\\|",-1);
                    if (split != null) {
                        for (int i = 0; i < split.length; i++) {
                            String temp = split[i];
                            if (!StringUtils.isEmpty(temp)) {
                                String[] items = temp.split(",");
                                count += items.length;
                            }
                        }
                    }
                }
                return count;
        }
        throw new RuntimeException("计算订单数时数据错误");
    }

    /**
     * 获取订单总金额
     *
     * @return
     */
    @Transient
    public double getTotalMoney() {
        return getMoney() * getOrderNum();
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Transient
    public boolean isSelected() {
        return isSelected;
    }
}
