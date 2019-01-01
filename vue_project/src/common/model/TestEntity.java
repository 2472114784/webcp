package com.game.cp.model.entity;

import com.game.cp.utils.SeedUtil;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class TestEntity {
    private long id;
    private String one;
    private String two;
    private String three;
    private String four;
    private String five;
    private String six;

    public static TestEntity create() {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(SeedUtil.getId());
        testEntity.setOne("one");
        testEntity.setTwo("two");
        testEntity.setThree("three");
        testEntity.setFour("four");
        testEntity.setFive("five");
        testEntity.setSix("six");
        return testEntity;
    }

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "one")
    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }
    @Basic
    @Column(name = "two")
    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }
    @Basic
    @Column(name = "three")
    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }
    @Basic
    @Column(name = "four")
    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }
    @Basic
    @Column(name = "five")
    public String getFive() {
        return five;
    }

    public void setFive(String five) {
        this.five = five;
    }
    @Basic
    @Column(name = "six")
    public String getSix() {
        return six;
    }

    public void setSix(String six) {
        this.six = six;
    }
}
