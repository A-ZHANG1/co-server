package com.trade.logic.impl;

import com.trade.data.mapper.CompanyMapper;
import com.trade.data.mapper.ContractMapper;
import com.trade.data.model.Company;
import com.trade.data.model.Contract;
import com.trade.logic.service.CommunityDetectionService;
import com.trade.logic.service.LinkService;
import com.trade.web.response.GeneralResponse;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Wayne.A.Z on 2019/3/22.
 */

@Service
public class CommunityDetectionServiceImpl implements CommunityDetectionService {
  @Autowired
  ContractMapper contractMapper;

  @Autowired
  CompanyMapper companyMapper;

  @Autowired
  LinkService linkService;

  Map<Company, List<Double>> initialCommunity = new HashMap<>();


  //原始BMLPA按照节点度初始化core
//  public void roughCore(){
//    Map<Integer,Set<Company>> communities = new HashMap<>();//<社团编号，该社团公司节点集合>
//
//    Map<Integer,Company> sortDegree = new HashMap<>(); //<node degree,node(公司)>
//
//    int labelnum;
//
//    for(Company c : companyMapper.getCompanies()){
//      sortDegree.put(contractMapper.countContractsOfCompanyByCompanyName(c.getCompanyName()),c)
//    }
//
//    Map<Company,Integer> getAttached = new HashMap<>();//<公司，社团编号>
//    for(Map.Entry<Company,Integer> entry :getAttached.entrySet()){
//      if()
//    }
//  }

  //低风险BMLPA，按照节点注册资本初始化core
//  public void roughCoreByCapital() {
  public GeneralResponse<Map<Company, List<Double>>> roughCoreByCapital() {

    //todo: k -core k参数可设定。 但是没有验证数据所以必要性存疑

    List<Company> companies = companyMapper.getCompanies();
    companies.sort(Comparator.comparing(Company::getCapital));

    double label = 1;

    for (Company c : companies) {

      List<Double> labelList = new ArrayList<>();
      //若尚未打社群标签
      if (!initialCommunity.containsKey(c)) {
        labelList.add(label);
        labelList.add(1.0);//初始化belonging_coefficient归属因子为1
        initialCommunity.put(c, labelList);

        //邻接最大注册资本节点加入core
        List<Company> neighbors = linkService.getLinkedNodesOfC(c);
        neighbors.sort(Comparator.comparing(Company::getCapital));
        Company maxNb = neighbors.get(0);
        initialCommunity.put(maxNb, labelList);

        //共有邻居中最大注册资本入core
        List<Company> nbNeighbors = linkService.getLinkedNodesOfC(maxNb);

        for (Company commonNb : neighbors) {
          if (nbNeighbors.indexOf(commonNb) != -1) {
            initialCommunity.put(commonNb, labelList);
            break;
          }
        }
        label++;
      }
    }

    GeneralResponse<Map<Company, List<Double>>> resp = new GeneralResponse<>();
    resp.setObj(initialCommunity);
    return resp;
  }

  //高投资回报率BMLPA，按照节点发生合同数初始化core
//  public void roughCoreByContractAmount() {
//    List<Company> companies = companyMapper.getCompanies();
//  }

  //  public GeneralResponse<Map<Company, Map<Double,Double>>> propagation(int threshold,int iter){
  public GeneralResponse<Map<Company, Map<Double, Double>>> propagation(double threshold) {

    roughCoreByCapital();

    double communityId, belongingCoeff;
    Map<Company, Map<Double, Double>> nbPropagation = new HashMap<>();

    int converge = 0;
    int iter = 0;

    while (converge != companyMapper.getCompanyNum()) {
      iter++;

      //一次迭代 ，更新所有节点
      for (Company c : companyMapper.getCompanies()) {

        Map<Double, Double> nodeCommunityMap = new HashMap<>(); //iter i不考虑当前节点iter i-1被打的标签

        //邻接节点(community_label,belonging_coefficient)求和
        for (Company nb : linkService.getLinkedNodesOfC(c)) {
          communityId = initialCommunity.get(nb).get(0);
          belongingCoeff = initialCommunity.get(nb).get(1);

          if (nodeCommunityMap.containsKey(communityId)) {
            nodeCommunityMap.replace(communityId, nodeCommunityMap.get(communityId) + belongingCoeff);
          } else {
            nodeCommunityMap.put(communityId, belongingCoeff);
          }
        }

        double maxB = nodeCommunityMap.entrySet().stream().
          max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getValue();

        //除以最大值
        Map<Double, Double> normalizedNodeCommunityMap = nodeCommunityMap.entrySet().stream()
          .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue() / maxB));

        //剔除过小的b。（可能造成所有c都被剔除）
        Map<Double, Double> filteredNodeCommunityMap = (Map<Double, Double>) normalizedNodeCommunityMap.entrySet().stream()
          .filter(e -> e.getValue() < threshold).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

        //剩余b和调整为1
        double sumB = filteredNodeCommunityMap.entrySet().stream().mapToDouble(e -> e.getValue()).sum();
        Map<Double, Double> propagatedNodeCommunityMap = filteredNodeCommunityMap.entrySet().stream()
          .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue() / sumB));

        if (converge == 0) {
          nbPropagation.put(c, propagatedNodeCommunityMap);
        }

        //若每个节点标签集不变（converge==201,公司节点数），退出循环
        if (nbPropagation.get(c) == nodeCommunityMap) {
          converge++;
        } else {
          nbPropagation.put(c, propagatedNodeCommunityMap);
        }
      }
      System.out.println("community amount: " + nbPropagation.size() + " in iter "+ iter);

    }

    GeneralResponse<Map<Company, Map<Double, Double>>> resp = new GeneralResponse<>();
    resp.setObj(nbPropagation);
    return resp;
  }

  @Override
  public GeneralResponse<List<Company>> executeBMLPA(int type, double threshold) {

    List<Company> groupedCompanies = new ArrayList<>();
    switch (type) {
      case 0:
//        roughCore();break;
      case 1:
        roughCoreByCapital();
        break;
      case 2:
//        roughCoreByContractAmount();
        break;
    }

    GeneralResponse resp = new GeneralResponse();
    resp.setObj(groupedCompanies);
    return resp;
  }
}
