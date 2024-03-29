package com.trade.logic.service;

import com.trade.data.model.Company;
import com.trade.data.model.Link;
import com.trade.web.response.GeneralResponse;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/20.
 */
public interface LinkService {
    GeneralResponse<List<Link>> getAllLinks();

    List<Link> getLinksStartFromC(Company c);

    List<Company> getLinkedNodesOfC(Company c);
}
