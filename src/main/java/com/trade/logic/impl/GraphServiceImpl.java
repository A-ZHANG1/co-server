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

import java.util.*;
import java.util.stream.Collectors;

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

    List<Company> nodesSurroundingCompany = new ArrayList<>();
    List<Link> linksSurroundingCompany = new ArrayList<>();

    public Map<Company,Integer> getSubGraph(String companyName){

        LinkedList<Company> bfsQueue = new LinkedList<Company>();//用linkedlist实现队列,存放一次要遍历的元素
        LinkedList<Integer> depthQueue = new LinkedList<Integer>();//用linkedlist实现队列,存放元素层数

        Map<Company,Integer> visitedMap = new HashMap<>();

        bfsQueue.add(companyMapper.getCompanyByCompanyName(companyName));
        depthQueue.add(0);

        while(!bfsQueue.isEmpty()){

            Company companyNode = bfsQueue.remove();
            int depth = depthQueue.remove();
            visitedMap.put(companyNode,depth);

            for(Company c : linkService.getLinkedNodesOfC(companyNode)){

                if(!visitedMap.containsKey(c)){
                    bfsQueue.add(c);
                    depthQueue.add(depth+1);
                }
            }
            if(depth > 3) break;
        }
        return visitedMap;
    }


    @Override
    public GeneralResponse<List<Company>> getNodesSurroundingCompany(String companyName) {
        GeneralResponse<List<Company>> resp = new GeneralResponse<>();
        Map<Company,Integer> visitedMap = this.getSubGraph(companyName);

//        List<Company> nodesSurroundingCompany;
        nodesSurroundingCompany.clear();
        linksSurroundingCompany.clear();

        nodesSurroundingCompany = visitedMap.entrySet().stream().filter(e -> e.getValue()<3)
                .map(e->e.getKey()).collect(Collectors.toList());

        resp.setObj(nodesSurroundingCompany);
        return resp;
    }

    @Override
    public GeneralResponse<List<Link>> getLinksSurroundingCompany(String companyName) {
        //先调用getNodesSurroundingCompany()
        GeneralResponse<List<Link>> resp = new GeneralResponse<>();

//        List<Link> linksSurroundingCompany = new ArrayList<>();

        for(Company c : nodesSurroundingCompany){

            for(Link l : linkMapper.getLinksStartFromC(c)){
                Company compCLinkedTo = companyMapper.getCompanyByCompanyName(l.getPartyBName());
                if(nodesSurroundingCompany.contains(compCLinkedTo)
                        && !linksSurroundingCompany.contains(l)){
                    linksSurroundingCompany.add(l);
                }
            }
        }

        resp.setObj(linksSurroundingCompany);

        return resp;
    }

    @Override
    public GeneralResponse<Double> getCompanyWeight(String companyName) {
        //需要先调用getNodesSurroundingCompany和getLinksSurroundingCompany
        GeneralResponse<Double> resp=new GeneralResponse<>();

        double companyWeight = 0;
        for(Link l : linksSurroundingCompany){
            companyWeight += l.getLinkWeight();
        }

        companyWeight = companyWeight/1000;
        resp.setObj(companyWeight);
        return resp;
    }

}
