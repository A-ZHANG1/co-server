package com.trade.logic.service;

import com.trade.data.model.Company;
import com.trade.web.response.GeneralResponse;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/19.
 */
public interface CompanyService {

    GeneralResponse<List<Company>> getAllCompanies();

    GeneralResponse<Company> showCompanyInfo(String companyName);

  }
