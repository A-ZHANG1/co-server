package com.trade.logic.impl;

import com.trade.data.mapper.CompanyMapper;
import com.trade.data.mapper.LinkMapper;
import com.trade.data.model.Company;
import com.trade.data.model.Link;
import com.trade.logic.service.LinkService;
import com.trade.web.response.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/20.
 */
@Service
public class LinkServiceImpl implements LinkService{
    @Autowired
    LinkMapper linkMapper;

    @Autowired
    CompanyMapper companyMapper;

    @Override
    public GeneralResponse<List<Link>> getLinks() {
        GeneralResponse<List<Link>> resp =new GeneralResponse<>();
        List<Link> links=linkMapper.getLinks();
        resp.setObj(links);
        return resp;
    }

    @Override
    public List<Link> getLinksStartFromC(Company c) {
        List<Link> links=linkMapper.getLinksStartFromC(c);
        return links;
    }

    @Override
    public List<Company> getLinkedNodesOfC(Company c) {
        List<Company> linkedNodesOfC = new ArrayList<>();
        for(Link l : linkMapper.getLinksStartFromC(c)){
                linkedNodesOfC.add(companyMapper.getCompanyByCompanyName(l.getPartyBName()));
        }
        for(Link l : linkMapper.getLinksPointToC(c)){
            linkedNodesOfC.add(companyMapper.getCompanyByCompanyName(l.getPartyAName()));
        }

        return linkedNodesOfC;
    }
}
