package com.trade.logic.impl;

import com.trade.data.mapper.CompanyMapper;
import com.trade.data.mapper.ContractMapper;
import com.trade.data.mapper.LinkMapper;
import com.trade.data.model.Company;
import com.trade.data.model.Contract;
import com.trade.data.model.Link;
import com.trade.logic.service.CompanyService;
import com.trade.web.response.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/19.
 */
@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    CompanyMapper companyMapper;


    @Override
    public GeneralResponse<List<Company>> getAllCompanies() {
        GeneralResponse<List<Company>> resp=new GeneralResponse<>();
        List companies=companyMapper.getCompanies();
        resp.setObj(companies);
        return resp;
    }



  @Override
  public GeneralResponse<Company> showCompanyInfo(String companyName) {
    GeneralResponse<Company> resp=new GeneralResponse<>();
    Company company=companyMapper.getCompanyByCompanyName(companyName);

    resp.setObj(company);
    return resp;
  }
}
