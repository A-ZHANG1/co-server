package com.trade.logic.impl;

import com.trade.data.mapper.ContractMapper;
import com.trade.data.model.Contract;
import com.trade.logic.service.ContractService;
import com.trade.web.response.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/18.
 */
@Service
public class ContractServiceImpl implements ContractService{
    @Autowired
    ContractMapper contractMapper;

//    @Autowired
//    LinkMapper linkMapper;

    @Override
    public GeneralResponse<Contract> createContract(Contract contract) {
        contractMapper.createContract(contract);
        return new GeneralResponse<>(contract);
    }

    @Override
    public GeneralResponse<List<Contract>> getContractByContractId(String id) {
        GeneralResponse<List<Contract>> resp=new GeneralResponse<>();
        List<Contract> contractList = contractMapper.getContractByContractId(id);
        resp.setObj(contractList);
        return resp;
    }

    @Override
    public GeneralResponse<List<Contract>> getAllContracts() {
        GeneralResponse<List<Contract>> resp=new GeneralResponse<>();
        List<Contract> contractList = contractMapper.getAllContracts();
        resp.setObj(contractList);
        return resp;    }
}
