package com.trade.logic.service;

import com.trade.data.model.Company;
import com.trade.data.model.Link;
import com.trade.web.response.GeneralResponse;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/2/7.
 */
public interface GraphService {

    GeneralResponse<Double> getCompanyWeight(String companyName);

    GeneralResponse<List<Link>> getLinksSurroundingCompany(String companyName);

    GeneralResponse<List<Company>> getNodesSurroundingCompany(String companyName);


}
