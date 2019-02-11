package com.trade.logic.service;

import com.trade.data.model.Contract;
import com.trade.web.response.GeneralResponse;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/18.
 */
public interface ContractService {

    GeneralResponse<Contract> createContract(Contract contract);

    GeneralResponse<List<Contract>> getContractByContractId(String id);

    GeneralResponse<List<Contract>> getAllContracts();

    GeneralResponse<List<Contract>> randomizeContract();
}
