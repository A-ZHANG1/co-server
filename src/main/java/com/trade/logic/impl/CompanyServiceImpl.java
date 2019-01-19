package com.trade.logic.impl;

import com.trade.data.mapper.CompanyMapper;
import com.trade.data.model.Company;
import com.trade.logic.service.CompanyService;
import com.trade.web.response.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/19.
 */
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
}
