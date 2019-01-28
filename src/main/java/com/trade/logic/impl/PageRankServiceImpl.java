package com.trade.logic.impl;

import com.trade.data.mapper.CompanyMapper;
import com.trade.data.mapper.LinkMapper;
import com.trade.data.model.Company;
import com.trade.data.model.Link;
import com.trade.logic.service.PageRankService;
import com.trade.web.response.GeneralResponse;
import com.trade.web.response.PageRankResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/21.
 */
@Service
public class PageRankServiceImpl implements PageRankService {
    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    LinkMapper linkMapper;

    @Override
    public GeneralResponse<PageRankResponse> pageRankCompany(int iter) {
        GeneralResponse<PageRankResponse> resp=new GeneralResponse<>();
        List<Company> companies=companyMapper.getCompanies();
        PageRankResponse prr=new PageRankResponse();

        //第0轮迭代，初始化所有节点权重（当前节点注册资本/图中结点总注册资本），和为1
        if(iter == 0){
            double nodeWeightSum=companyMapper.getNodeWeightSum();

            for(Company c : companies){
                c.setNodeWeight(c.getNodeWeight()/nodeWeightSum);
                companyMapper.updateCompanyNodeWeight(c);
            }
            prr.setDiff(0);
        }else {
            //每轮迭代更新节点权重
            List<Double> diffs = new ArrayList<>();
            double temp, sum = 0;

            for (Company c : companies) {
                List<Link> links = linkMapper.getLinksStartFromC(c);
                for (Link l : links) {
                    double linkedNodeWeight = companyMapper.getCompanyByCompanyName(l.getPartyBName()).get(0).getNodeWeight();
                    temp = l.getLinkWeight() * linkedNodeWeight / links.size();
                    sum += temp;
                }
                c.setNodeWeight(sum);
                diffs.add(sum - c.getNodeWeight());
                companyMapper.updateCompanyNodeWeight(c);
            }
            prr.setDiff(Collections.max(diffs));
        }

            prr.setCompanies(companies);

            resp.setObj(prr);
            return resp;
    }

    @Override
    public GeneralResponse<List<Company>> resetCompanyNodeWeight() {
        GeneralResponse<List<Company>> resp=new GeneralResponse<>();
        List<Company> companies=companyMapper.getCompanies();
        for(Company c : companies)
        {
            c.setNodeWeight(c.getCapital());
            companyMapper.updateCompanyNodeWeight(c);
        }
        resp.setObj(companies);
        return resp;
    }
}
