package com.game.cp.model.entity;

import com.game.cp.utils.AppUtils;
import com.game.cp.utils.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "easy_shopping")
public class EasyShoppingEntity {
    /**
     * 以userId+"_"+lotteryId 组成
     */
    private String id;
    /**
     * 多个lotteryItemId 组成 以","分割
     */
    private String items;

    public static String createId(UserEntity user, long lotteryId) {

        return user.getId()+"_"+lotteryId;
    }

    @Id
    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "items")
    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    /**
     * 获取lotteryId
     * @return
     */
    @Transient
    public long getLotteryId() {
        String id = getId();
        if(id!=null){
            String[] split = id.split("_");
            if(split.length==2){
                long lotteryId = StringUtils.parseLong(split[1]);
                if(lotteryId!=0){
                    return lotteryId;
                }
            }
        }
        return 0;
    }

    /**
     * 获取所有lotteryItemIds
     * @return
     */
    @Transient
    public List<Long> getLotteryItemIds() {
        List<Long> lotteryItemIds = new ArrayList<>();
        String items = getItems();
        if(items!=null){
            String[] split = items.split(",");
            for(int i=0;i<split.length;i++){
                String temp=split[i];
                long lotteryItemId = StringUtils.parseLong(temp);
                if(lotteryItemId!=0){
                    lotteryItemIds.add(lotteryItemId);
                }
            }
        }
        return lotteryItemIds;
    }


    /**
     * 快购 包含了的lotteryChild
     */
    @Transient
    public List<Long> getLotteryChildIds() {
        List<Long> lotteryChildIds = new ArrayList<>();
        List<Long> lotteryItemIds = getLotteryItemIds();
        if(lotteryItemIds!=null){
            Iterator<Long> iterator = lotteryItemIds.iterator();
            while (iterator.hasNext()){
                Long lotteryItemId = iterator.next();
                long lotteryChildId = AppUtils.getLotteryChildId(lotteryItemId);
                if(lotteryChildId!=0&&!lotteryChildIds.contains(lotteryChildId)){
                    lotteryChildIds.add(lotteryChildId);
                }
            }
        }
        return lotteryChildIds;
    }
    @Transient
    public boolean containsById(LotteryItemEntity item) {
        List<Long> items = getLotteryItemIds();
        if(items.contains(item.getLotteryItemId())){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "EasyShoppingEntity{" +
                "id='" + id + '\'' +
                ", items='" + items + '\'' +
                '}';
    }
}
