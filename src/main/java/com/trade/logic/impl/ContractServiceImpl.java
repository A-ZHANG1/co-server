package com.trade.logic.impl;

import com.trade.data.mapper.CompanyMapper;
import com.trade.data.mapper.ContractMapper;
import com.trade.data.mapper.LinkMapper;
import com.trade.data.model.Company;
import com.trade.data.model.Contract;
import com.trade.data.model.Link;
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

    @Autowired
    LinkMapper linkMapper;

    @Autowired
    CompanyMapper companyMapper;

    @Override
    public GeneralResponse<Contract> createContract(Contract contract) {

        //数据库插入合同记录
//        contractMapper.createContract(new Contract(contract.getPartyAName(),contract.getPartyBName(),contract.getAmount()));
        contractMapper.createContract(contract);

        //新建公司属性为空的company节点
        List<Company> companies=companyMapper.getCompanyByCompanyName(contract.getPartyAName());
        if(companies.size()==0){
            Company company=new Company(contract.getPartyAName());
            companyMapper.insertCompany(company);
        }

        companies=companyMapper.getCompanyByCompanyName(contract.getPartyBName());
        if(companies.size()==0){
            Company company=new Company(contract.getPartyBName());
            companyMapper.insertCompany(company);
        }


        //新建连接
        List<Link> links = linkMapper.getLinkByAB(contract.getPartyAName(),contract.getPartyBName());

        if(links.size()== 0){
            Link link=new Link(contract.getPartyAName(),contract.getPartyBName(),contract.getAmount());
            linkMapper.createLink(link);
        }else{
            for (Link l : links){
                l.setLinkWeight(l.getLinkWeight()+1);
                linkMapper.updateLink(l);
            }
        }

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
        return resp;
    }
}
