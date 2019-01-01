package com.game.cp.controller;

import com.game.cp.model.ResultModel;
import com.game.cp.model.entity.TestEntity;
import com.game.cp.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class Testcontroller {

    @Autowired
    private TestRepository testRepository;

    /**
     * 获取测试用户
     *
     * @param count
     * @return
     */
    @Transactional
    @RequestMapping(value = "/testInsertList", method = RequestMethod.GET)
    public ResultModel testInsertList(@RequestParam int count) {
        if (count > 2000) {
            return ResultModel.createResult(ResultModel.FAILE, "最多只能获取2000测试账号");
        }
        long startTime = System.currentTimeMillis();
        List<TestEntity> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(TestEntity.create());
        }
        testRepository.saveAll(list);
        long endTime = System.currentTimeMillis();
        return ResultModel.createResult(ResultModel.SUCCESS, "", endTime - startTime);
    }

    @RequestMapping(value = "/testInsertList2", method = RequestMethod.GET)
    public ResultModel testInsertList2(@RequestParam int count) {
        if (count > 2000) {
            return ResultModel.createResult(ResultModel.FAILE, "最多只能获取2000测试账号");
        }
        long startTime = System.currentTimeMillis();
        List<TestEntity> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(TestEntity.create());
        }
        for (int i = 0; i < list.size(); i += 20) {
            List<TestEntity> testEntities = list.subList(i, i + 20);
            testRepository.saveAll(testEntities);
        }
        long endTime = System.currentTimeMillis();
        return ResultModel.createResult(ResultModel.SUCCESS, "", endTime - startTime);
    }


    @RequestMapping(value = "/testUpdateList", method = RequestMethod.GET)
    public ResultModel testUpdateList(@RequestParam int count) {
        if (count > 2000) {
            return ResultModel.createResult(ResultModel.FAILE, "最多只能获取2000测试账号");
        }
        long startTime = System.currentTimeMillis();
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, count, sort);
        Page<TestEntity> entityPage = testRepository.findAll(pageable);
        List<TestEntity> list = entityPage.getContent();
        Iterator<TestEntity> iterator = list.iterator();
        while (iterator.hasNext()){
            TestEntity entity = iterator.next();
            if(entity!=null){
                entity.setSix("six_1");
            }
        }
        System.out.println("======size="+list.size());
        testRepository.saveAll(list);
        long endTime = System.currentTimeMillis();
        return ResultModel.createResult(ResultModel.SUCCESS, "", endTime - startTime);
    }


    @RequestMapping(value = "/testUpdateList2", method = RequestMethod.GET)
    public ResultModel testUpdateList2(@RequestParam int count) {
        if (count > 2000) {
            return ResultModel.createResult(ResultModel.FAILE, "最多只能获取2000测试账号");
        }
        long startTime = System.currentTimeMillis();
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, count, sort);
        Page<TestEntity> entityPage = testRepository.findAll(pageable);
        List<TestEntity> list = entityPage.getContent();
        Iterator<TestEntity> iterator = list.iterator();
        while (iterator.hasNext()){
            TestEntity entity = iterator.next();
            if(entity!=null){
                entity.setSix("six_3");
            }
        }
        for (int i = 0; i < list.size(); i += 20) {
            List<TestEntity> testEntities = list.subList(i, i + 20);
            testRepository.saveAll(testEntities);
        }
        long endTime = System.currentTimeMillis();
        return ResultModel.createResult(ResultModel.SUCCESS, "", endTime - startTime);
    }


}
