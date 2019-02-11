package com.trade.web.controller;

import com.trade.data.model.Company;
import com.trade.data.model.Link;
import com.trade.logic.service.CompanyService;
import com.trade.logic.service.GraphService;
import com.trade.logic.service.LinkService;
import com.trade.logic.service.PageRankService;
import com.trade.web.response.GeneralResponse;
import com.trade.web.response.PageRankResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/19.
 */
@Controller
@RequestMapping("/graph")
@Api(value = "关系图节点Api" , produces = "application/json")
public class GraphController {
    @Autowired
    CompanyService companyService;

    @Autowired
    GraphService graphService;

    @Autowired
    LinkService linkService;

    @Autowired
    PageRankService pageRankService;

    //返回小图的nodes
    @GetMapping("/getNodesSurroundingCompany")
    @ResponseBody
    @ApiOperation(value = "返回当前公司三层连接以内的Node(Company)")
    public GeneralResponse<List<Company>> getNodesSurroundingCompany(String companyName){
        return graphService.getNodesSurroundingCompany(companyName);
    }

    //返回小图的Links
    @GetMapping("/getLinksSurroundingCompany")
    @ResponseBody
    @ApiOperation(value = "返回当前公司三层连接以内的Links")
    public GeneralResponse<List<Link>> getLinksSurroundingCompany(String companyName){
        return graphService.getLinksSurroundingCompany(companyName);
    }

    @GetMapping("/getCompanyWeight")
    @ResponseBody
    @ApiOperation(value = "返回该公司权重计算结果")
    public GeneralResponse<Double> getCompanyWeight(String companyName){
        return graphService.getCompanyWeight(companyName);
    }

    @GetMapping("/getAllCompanies")
    @ResponseBody
    @ApiOperation(value = "返回大图所有Node(Company)")
    public GeneralResponse<List<Company>> getCompanies(){
    return companyService.getAllCompanies();
}

    @GetMapping("/getAllLinks")
    @ResponseBody
    @ApiOperation(value = "返回大图所有Link(Link)")
    public GeneralResponse<List<Link>> getAllLinks(){
        return linkService.getLinks();
    }


    /*
    *legacy: pageRank确定节点权重和回退
    */
    //调用pageRank算法更新节点权重，返回第count次迭代的节点权重
    @GetMapping("/getPageRankByStep")
    @ResponseBody
    @ApiOperation(value = "legacy:更新公司节点权重，返回每轮迭代更新情况")
    public GeneralResponse<PageRankResponse> pageRankCompany(int iter){
        return pageRankService.pageRankCompany(iter);
    }

    //回退节点权重到初始值（公司注册资本）
    @GetMapping("/resetNodeWeight")
    @ResponseBody
    @ApiOperation(value = "legacy:回退公司权重")
    public GeneralResponse<List<Company>> resetCompanyNodeWeight(){
        return pageRankService.resetCompanyNodeWeight();
    }

}
