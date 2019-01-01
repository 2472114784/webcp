package com.game.cp.controller;

import com.game.cp.model.ResultModel;
import com.game.cp.model.entity.CustomerServerEntity;
import com.game.cp.services.service.CustomerServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerServerController {
    @Autowired
    private CustomerServerService customerServerService;
    @RequestMapping(value = "/findAllCustomerServers", method = RequestMethod.GET)
    public ResultModel findAllCustomerServers() {
        List<CustomerServerEntity> customerServers = customerServerService.findAllByStatus(1);
        return ResultModel.createResult(ResultModel.SUCCESS, "添加成功",customerServers);
    }
}
