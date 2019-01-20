package com.trade.web.controller;

import com.trade.data.model.Company;
import com.trade.data.model.Link;
import com.trade.logic.service.CompanyService;
import com.trade.logic.service.LinkService;
import com.trade.web.response.GeneralResponse;
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
    LinkService linkService;

    //公司，即图节点
    @GetMapping("/getAllCompanies")
    @ResponseBody
    @ApiOperation(value = "返回所有公司节点")
    public GeneralResponse<List<Company>> getCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping("/getLinks")
    @ResponseBody
    @ApiOperation(value = "返回所有公司关联")
    public GeneralResponse<List<Link>> getLinks(){
        return linkService.getLinks();
    }

}
