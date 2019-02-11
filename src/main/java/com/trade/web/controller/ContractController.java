package com.trade.web.controller;

import com.trade.data.mapper.CompanyMapper;
import com.trade.data.model.Company;
import com.trade.data.model.Contract;
import com.trade.logic.service.CompanyService;
import com.trade.logic.service.ContractService;
import com.trade.web.response.GeneralResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/18.
 */
@Controller
@RequestMapping("/contract")
@Api(value = "合同管理Api", produces = "application/json")
public class ContractController {
    @Autowired
    ContractService contractService;

    @GetMapping("/getContractByContractId")
    @ResponseBody
    @ApiOperation(value = "按id查找合同")
    public GeneralResponse<List<Contract>> getContractByContractId(String contractId) {
        return contractService.getContractByContractId(contractId);
    }

    @GetMapping("/getAllContracts")
    @ResponseBody
    @ApiOperation(value = "返回所有合同")
    public GeneralResponse<List<Contract>> getAllContracts() {
        return contractService.getAllContracts();
    }

    @PostMapping("/createContract")
    @ResponseBody
    @ApiOperation(value = "添加合同")
    public GeneralResponse<Contract> createContract(@RequestBody Contract contract) {
        return contractService.createContract(contract);
    }

    @PostMapping("/randomizeContract")
    @ResponseBody
    @ApiOperation(value = "随机生成合同合同")
    public GeneralResponse<List<Contract>> randomizeContract() {
        return contractService.randomizeContract();
    }

}
