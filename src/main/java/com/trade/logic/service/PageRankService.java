package com.trade.logic.service;

import com.trade.data.model.Company;
import com.trade.web.response.GeneralResponse;
import com.trade.web.response.PageRankResponse;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/21.
 */
public interface PageRankService {
    GeneralResponse<PageRankResponse> pageRankCompany(int iter);

    GeneralResponse<List<Company>> resetCompanyNodeWeight();
}
