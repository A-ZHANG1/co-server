package com.trade.logic.impl;

import com.trade.data.mapper.CompanyMapper;
import com.trade.data.mapper.LinkMapper;
import com.trade.data.model.Company;
import com.trade.data.model.Link;
import com.trade.logic.service.GraphService;
import com.trade.logic.service.LinkService;
import com.trade.web.response.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/2/7.
 */
@Service
public class GraphServiceImpl implements GraphService{

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    LinkMapper linkMapper;

    @Autowired
    LinkService linkService;

    List<Company> visited =  new ArrayList<Company>();//已被访问过的元素
    List<Integer> depthList = new ArrayList<>();//元素深度

    @Override
    public GeneralResponse<Double> getCompanyWeight(String companyName) {

        GeneralResponse<Double> resp=new GeneralResponse<>();
        double companyWeight = 0;

        //bfs为结点标depth
        LinkedList<Company> bfsQueue = new LinkedList<Company>();//用linkedlist实现队列存放一次要遍历的元素
        LinkedList<Integer> depthQueue = new LinkedList<Integer>();//用linkedlist实现队列存放元素层数

        List<Company> linkedCompanyNodes;

        bfsQueue.add(companyMapper.getCompanyByCompanyName(companyName));
        depthQueue.add(0);

        while(!bfsQueue.isEmpty()){
            Company companyNode=bfsQueue.remove();
            Integer depth = depthQueue.remove();
            if(!visited.contains(companyNode)){
                visited.add(companyNode);
                depthList.add(depth);
                linkedCompanyNodes = linkService.getLinkedNodesOfC(companyNode);
                for(Company c : linkedCompanyNodes){
                    bfsQueue.add(c);
                    depthQueue.add(depth+1);
                }
            }
        }


//        Double CompanyWeight=new Double(companyWeight);
        resp.setObj(companyWeight);
        return resp;
    }

    @Override
    public GeneralResponse<List<Link>> getLinksSurroundingCompany(String companyName) {
        return null;
    }

    @Override
    public GeneralResponse<List<Company>> getNodesSurroundingCompany(String companyName) {
        return null;
    }
}
