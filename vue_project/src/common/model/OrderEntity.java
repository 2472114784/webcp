package com.game.cp.model.entity;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.game.cp.config.LotteryConfig;
import com.game.cp.utils.StringUtils;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "lottery_order")
public class OrderEntity {

    private long id;
    /**
     * 期号
     */
    private long lotteryNo;
    /**
     * 开奖结果
     */
    private String lotteryResult;
    /**
     * 注数
     */
    private int orderNum;
    /**
     * 单个订单金额（暂保留，未用）
     */
    private double orderItemMoney;
    /**
     * 订单类型
     */
    private int orderType;
    /**
     * 订单状态
     */
    private int orderStatus;
    /**
     * 用户id
     */
    private long userId;
    /**
     * lottery一级id
     */
    private long lotteryTypeId;
    /**
     * lottery一级　name
     */
    private String lotteryTypeName;
    /**
     * lottery二级 id
     */
    private long lotteryChildId;
    /**
     * lottery二级 name
     */
    private String lotteryChildName;
    /**
     * 订单购买value 仅用于显示
     */
    private String lotteryValue;
    /**
     * 子订单　的json
     */
    private String orderChildJson;
    /**
     * 订单总金额
     */
    private double totalMoney;
    /**
     * 订单输赢金额
     */
    private double winMoney;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", locale = "zh", timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", locale = "zh", timezone = "GMT+8")
    private Timestamp updateTime;
    private List<OrderChildEntity> childOrders;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "lottery_no", nullable = true, length = 255)
    public long getLotteryNo() {
        return lotteryNo;
    }

    public void setLotteryNo(long lotteryNo) {
        this.lotteryNo = lotteryNo;
    }

    @Basic
    @Column(name = "lottery_result", nullable = true, length = 255)
    public String getLotteryResult() {
        return lotteryResult;
    }

    public void setLotteryResult(String lotteryResult) {
        this.lotteryResult = lotteryResult;
    }


    @Basic
    @Column(name = "order_type", nullable = false)
    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }


    @Basic
    @Column(name = "order_status", nullable = false)
    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }


    @Basic
    @Column(name = "lottery_type_id", nullable = true)
    public long getLotteryTypeId() {
        return lotteryTypeId;
    }

    public void setLotteryTypeId(long lotteryTypeId) {
        this.lotteryTypeId = lotteryTypeId;
    }

    @Basic
    @Column(name = "lottery_type_name", nullable = true, length = 255)
    public String getLotteryTypeName() {
        return lotteryTypeName;
    }

    public void setLotteryTypeName(String lotteryTypeName) {
        this.lotteryTypeName = lotteryTypeName;
    }

    @Basic
    @Column(name = "lottery_child_id", nullable = true)
    public long getLotteryChildId() {
        return lotteryChildId;
    }

    public void setLotteryChildId(long lotteryChildId) {
        this.lotteryChildId = lotteryChildId;
    }

    @Basic
    @Column(name = "lottery_child_name", nullable = true, length = 255)
    public String getLotteryChildName() {
        return lotteryChildName;
    }

    public void setLotteryChildName(String lotteryChildName) {
        this.lotteryChildName = lotteryChildName;
    }

    @Basic
    @Column(name = "lottery_value", nullable = true)
    public String getLotteryValue() {
        return lotteryValue;
    }

    public void setLotteryValue(String lotteryValue) {
        this.lotteryValue = lotteryValue;
    }

    @Basic
    @Column(name = "total_money", nullable = true, precision = 0)
    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Basic
    @Column(name = "win_money", nullable = true, precision = 0)
    public Double getWinMoney() {
        return winMoney;
    }

    public void setWinMoney(Double winMoney) {
        this.winMoney = winMoney;
    }

    @Basic
    @Column(name = "order_num", nullable = true, precision = 0)
    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    @Basic
    @Column(name = "order_item_money", nullable = true, precision = 0)
    public double getOrderItemMoney() {
        return orderItemMoney;
    }

    public void setOrderItemMoney(double orderItemMoney) {
        this.orderItemMoney = orderItemMoney;
    }

    public void addWinMoney(double money) {
        this.winMoney += money;
    }

    public void removeWinMoney(double money) {
        this.winMoney -= money;
    }

    @Basic
    @Column(name = "order_child_json")
    public String getOrderChildJson() {
        return orderChildJson;
    }

    public void setOrderChildJson(String orderChildJson) {
        this.orderChildJson = orderChildJson;
    }

    @Transient
    public List<OrderChildEntity> getChildOrders() {
        if (childOrders == null && !StringUtils.isEmpty(orderChildJson)) {
            childOrders = JSON.parseArray(orderChildJson, OrderChildEntity.class);
        }
        return childOrders;
    }

    public void setChildOrders(List<OrderChildEntity> childOrders) {
        this.childOrders = childOrders;
        setOrderChildJson(JSON.toJSONString(childOrders));
    }

    /**
     * 根据子订单开奖后更新母订单信息
     */
    public void updateByopenLottery() {
        double winTotalMoney = 0;
        List<OrderChildEntity> childOrders = getChildOrders();
        if (childOrders != null) {
            Iterator<OrderChildEntity> iterator = childOrders.iterator();
            while (iterator.hasNext()) {
                OrderChildEntity orderChildEntity = iterator.next();
                if (orderChildEntity != null) {
                    winTotalMoney += orderChildEntity.getWinMoney();
                }
            }
        }
        setWinMoney(winTotalMoney);
        if (winTotalMoney >= 0) {
            setOrderStatus(LotteryConfig.ORDER_STATUS_COMPLETED_WIN);
        } else {
            setOrderStatus(LotteryConfig.ORDER_STATUS_COMPLETED_LOSE);
        }
    }

    /**
     * 获取所有中奖金额（不扣除购彩金额）
     *
     * @return
     */
    @Transient
    public double getTotalWinMoney() {
        return getWinMoney() + getTotalMoney();
    }


    public static class OrderChildEntity {
        private long id;
        private long lotteryNo;
        private String lotteryResult;
        private int orderType;
        private double odds;
        private int orderStatus;
        private long userId;
        private double money;
        private long lotteryTypeId;
        private String lotteryTypeName;
        private long lotteryChildId;
        private String lotteryChildName;
        private long lotteryItemId;
        private String lotteryItemName;
        private String lotteryValue;
        private double winMoney;
        private int orderWinNum;//该订单中奖注数

        @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", locale = "zh", timezone = "GMT+8")
        private Timestamp createTime;
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", locale = "zh", timezone = "GMT+8")
        private Timestamp updateTime;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getLotteryNo() {
            return lotteryNo;
        }

        public void setLotteryNo(long lotteryNo) {
            this.lotteryNo = lotteryNo;
        }

        public String getLotteryResult() {
            return lotteryResult;
        }

        public void setLotteryResult(String lotteryResult) {
            this.lotteryResult = lotteryResult;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public double getOdds() {
            return odds;
        }

        public void setOdds(double odds) {
            this.odds = odds;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public long getLotteryTypeId() {
            return lotteryTypeId;
        }

        public void setLotteryTypeId(long lotteryTypeId) {
            this.lotteryTypeId = lotteryTypeId;
        }

        public String getLotteryTypeName() {
            return lotteryTypeName;
        }

        public void setLotteryTypeName(String lotteryTypeName) {
            this.lotteryTypeName = lotteryTypeName;
        }

        public long getLotteryChildId() {
            return lotteryChildId;
        }

        public void setLotteryChildId(long lotteryChildId) {
            this.lotteryChildId = lotteryChildId;
        }

        public String getLotteryChildName() {
            return lotteryChildName;
        }

        public void setLotteryChildName(String lotteryChildName) {
            this.lotteryChildName = lotteryChildName;
        }

        public long getLotteryItemId() {
            return lotteryItemId;
        }

        public void setLotteryItemId(long lotteryItemId) {
            this.lotteryItemId = lotteryItemId;
        }

        public String getLotteryItemName() {
            return lotteryItemName;
        }

        public void setLotteryItemName(String lotteryItemName) {
            this.lotteryItemName = lotteryItemName;
        }

        public String getLotteryValue() {
            return lotteryValue;
        }

        public void setLotteryValue(String lotteryValue) {
            this.lotteryValue = lotteryValue;
        }

        public double getWinMoney() {
            return winMoney;
        }

        public void setWinMoney(double winMoney) {
            this.winMoney = winMoney;
        }

        public int getOrderWinNum() {
            return orderWinNum;
        }

        public void setOrderWinNum(int orderWinNum) {
            this.orderWinNum = orderWinNum;
        }

        public Timestamp getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Timestamp createTime) {
            this.createTime = createTime;
        }

        public Timestamp getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Timestamp updateTime) {
            this.updateTime = updateTime;
        }

        public void addWinMoney(double money) {
            winMoney += money;
        }

        public void removeWinMoney(double money) {
            winMoney -= money;
        }

        /**
         * 获取订单总金额
         *
         * @return
         */
        public double getTotalMoney() {
            return getMoney() * getOrderNum();
        }

        /**
         * 获取总注数
         *
         * @return
         */
        public int getOrderNum() {
            switch (getOrderType()) {
                case LotteryConfig
                        .ORDER_TYPE_NORMAL:
                    return 1;
                case LotteryConfig.ORDER_TYPE_DUPLEX:
                    int count = 1;
                    String lotteryValue = getLotteryValue();
                    if (StringUtils.isEmpty(lotteryValue)) {
                        throw new RuntimeException("获取订单数错误");
                    }
                    String[] split = lotteryValue.split("\\|",-1);
                    for (int i = 0; i < split.length; i++) {
                        String temp=split[i];
                        if(StringUtils.isEmpty(temp)){
                            return 0;
                        }
                        String[] items = temp.split(",");
                        count *= items.length;
                    }
                    return count;
                case LotteryConfig.ORDER_TYPE_POSITION:
                    count = 0;
                    lotteryValue = getLotteryValue();
                    if (StringUtils.isEmpty(lotteryValue)) {
                        throw new RuntimeException("获取订单数错误");
                    }
                    split = lotteryValue.split("\\|",-1);
                    for (int i = 0; i < split.length; i++) {
                        String temp=split[i];
                        if(!StringUtils.isEmpty(temp)) {
                            String[] items = temp.split(",");
                            count += items.length;
                        }
                    }
                    return count;
            }
            throw new RuntimeException("获取订单数错误");
        }

        /**
         * 获取订单中奖mongy（不扣除购彩成本）
         */
        public double getLotteryOrderWinMoney() {
            return getOrderWinNum() * getOdds() * getMoney();
        }

        /**
         * 开奖
         *
         * @param winLotteries
         */
        public void openLottery(List<Long> winLotteries) {
            int orderWinNum = openLotteryForOrderWinNum(winLotteries);
            setOrderWinNum(orderWinNum);

            double winMoney = getLotteryOrderWinMoney() - getTotalMoney();
            setWinMoney(winMoney);
            if (getWinMoney() >= 0) {
                setOrderStatus(LotteryConfig.ORDER_STATUS_COMPLETED_WIN);
            } else {
                setOrderStatus(LotteryConfig.ORDER_STATUS_COMPLETED_LOSE);
            }

        }

        /**
         * 获取中奖注数
         *
         * @param winLotteries
         */
        private int openLotteryForOrderWinNum(List<Long> winLotteries) {
            int orderWinNum = 0;
            switch (getOrderType()) {
                case LotteryConfig.ORDER_TYPE_NORMAL:
                    orderWinNum = openLotteryForNormal(winLotteries);
                    break;
                case LotteryConfig.ORDER_TYPE_DUPLEX:
                    orderWinNum = openLotteryForDuplex(winLotteries);

                    break;
                case LotteryConfig.ORDER_TYPE_POSITION:
                    orderWinNum = openLotteryForPosition(winLotteries);
                    break;
            }
            return orderWinNum;
        }

        /**
         * 订单定位类型 获取中奖注数
         *
         * @param positionResult
         * @return
         */
        private int openLotteryForPosition(List<Long> positionResult) {
            int orderWinNum = 0;
            String lotteryValue = getLotteryValue();
            if (!StringUtils.isEmpty(lotteryValue)) {
                String[] split = lotteryValue.split("\\|",-1);
                if (split != null && split.length > 0) {
                    for (int i = 0; i < split.length; i++) {
                        long itemResult = positionResult.get(i);
                        String temp = split[i];
                        if (!StringUtils.isEmpty(temp)) {
                            String[] items = temp.split(",");
                            for (int j = 0; j < items.length; j++) {
                                String item = items[j];
                                if (StringUtils.parseLong(item) == itemResult) {
                                    orderWinNum++;
                                }
                            }
                        }
                    }
                }
            }
            return orderWinNum;
        }

        /**
         * 订单复式类型 获取中奖注数
         *
         * @param duplexResult
         * @return
         */
        private int openLotteryForDuplex(List<Long> duplexResult) {
            int orderWinNum = 0;
            System.out.println("=======复式开奖结果"+duplexResult);
            String lotteryValue = getLotteryValue();
            if (!StringUtils.isEmpty(lotteryValue)) {
                String[] split = lotteryValue.split("\\|",-1);
                if (split != null && split.length > 0) {
                    for (int i = 0; i < split.length; i++) {
                        boolean isItemWin=false;
                        long itemResult = duplexResult.get(i);
                        String temp = split[i];
                        if (StringUtils.isEmpty(temp)) {
                            return 0;
                        }
                        String[] items = temp.split(",");
                        for (int j = 0; j < items.length; j++) {
                            String item = items[j];
                            if (StringUtils.parseLong(item) == itemResult) {
                                isItemWin=true;
                            }
                        }
                        if(!isItemWin){
                            return 0;
                        }
                    }
                    orderWinNum = 1;
                }
            }
            return orderWinNum;
        }

        /**
         * 订单普通类型 获取中奖注数
         *
         * @param winLotteries
         */
        private int openLotteryForNormal(List<Long> winLotteries) {
            int orderWinNum = 0;
            Iterator<Long> iterator = winLotteries.iterator();
            while (iterator.hasNext()) {
                long next = iterator.next();
                if (next == getLotteryItemId()) {
                    orderWinNum++;
                }
            }
            return orderWinNum;
        }
    }
}


