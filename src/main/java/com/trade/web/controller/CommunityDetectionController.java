package com.trade.web.controller;

import com.trade.data.model.Company;
import com.trade.logic.service.CommunityDetectionService;
import com.trade.web.response.GeneralResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Wayne.A.Z on 2019/3/22.
 */

@Controller
@RequestMapping("/communityDetection")
@Api(value = "调用社群发现算法" , produces = "application/json")
public class CommunityDetectionController {

  @Autowired
  CommunityDetectionService communityDetectionService;

  @GetMapping("/executeBMLPA")
  @ResponseBody
  @ApiOperation(value = "执行bmlpa算法")
  public GeneralResponse<Company> executeBMLPA(int type,double threshold){
    return communityDetectionService.executeBMLPA(type,threshold);
  }

  @GetMapping("/roughCoreByCapital")
  @ResponseBody
  @ApiOperation(value = "高注册资本优先初始化核函数")
  public GeneralResponse<Map<Company, List<Double>>> roughCoreByCapital(){
    return communityDetectionService.roughCoreByCapital();
  }

  @GetMapping("/propagation")
  @ResponseBody
  @ApiOperation(value = "标签传播过程")
  public GeneralResponse<Map<Company, Map<Double, Double>>> propagation(@RequestParam double threshold){
    return communityDetectionService.propagation(threshold);
  }
}
