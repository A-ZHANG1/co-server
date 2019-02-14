package com.trade.logic.impl;

import com.trade.data.mapper.CompanyMapper;
import com.trade.data.mapper.ContractMapper;
import com.trade.data.mapper.LinkMapper;
import com.trade.data.model.Company;
import com.trade.data.model.Contract;
import com.trade.data.model.Link;
import com.trade.logic.service.ContractService;
import com.trade.web.response.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/18.
 */
@Service
public class ContractServiceImpl implements ContractService{
    @Autowired
    ContractMapper contractMapper;

    @Autowired
    LinkMapper linkMapper;

    @Autowired
    CompanyMapper companyMapper;

    List<Contract> contractList = new ArrayList<>();

  @Override
    public GeneralResponse<Contract> createContract(Contract contract) {

        //数据库插入合同记录
        contractMapper.createContract(contract);

        //新建公司属性为空的company节点
         Company partyA = companyMapper.getCompanyByCompanyName(contract.getPartyAName());
        if(partyA == null){
            Company company=new Company(contract.getPartyAName());
            companyMapper.insertCompany(company);
        }

        Company partyB = companyMapper.getCompanyByCompanyName(contract.getPartyBName());
        if(partyB == null){
            Company company=new Company(contract.getPartyBName());
            companyMapper.insertCompany(company);
        }


        //新建连接
        List<Link> links = linkMapper.getLinksByAB(contract.getPartyAName(),contract.getPartyBName());

        if(links.size()== 0){
            Link link=new Link(contract.getPartyAName(),contract.getPartyBName(),contract.getAmount());
            linkMapper.createLink(link);
        }else{
            for (Link l : links){
                l.setLinkWeight(l.getLinkWeight()+1);
                linkMapper.updateLink(l);
            }
        }

        return new GeneralResponse<>(contract);
    }

    @Override
    public GeneralResponse<List<Contract>> getContractByContractId(String id) {
        GeneralResponse<List<Contract>> resp=new GeneralResponse<>();
        List<Contract> contractList = contractMapper.getContractByContractId(id);
        resp.setObj(contractList);
        return resp;
    }

    @Override
    public GeneralResponse<List<Contract>> getAllContracts() {
        GeneralResponse<List<Contract>> resp=new GeneralResponse<>();
        List<Contract> contractList = contractMapper.getAllContracts();
        resp.setObj(contractList);
        return resp;
    }

    /*
    *随机生成10000合同
    */
    private static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);// 构造开始日期
            Date end = format.parse(endDate);// 构造结束日期
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());

            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

    private Contract generateContract(Integer partyAId, Integer partyBId){

        Company a = companyMapper.getCompanyByCompanyId(partyAId);
        String partyAName = a.getCompanyName();


        Company b = companyMapper.getCompanyByCompanyId(partyBId);
        String  partyBName = b.getCompanyName();

        double amount = (Math.random()*100000);
        DecimalFormat def = new DecimalFormat("0.00");
        amount = Double.parseDouble(def.format(amount));

        Date randomStartTime = randomDate("2016-06-03", "2019-06-03");
        Date randomEndTime = randomDate("2019-06-03", "2023-06-03");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = df.format(randomStartTime);
        String endTime = df.format(randomEndTime);


        String[]  locationList = {"张江","浦东"};
        String location = locationList[(int)(Math.random()*2)];
        String type = "0";
        Contract contract = new Contract(partyAName,partyBName,amount,startTime,endTime,location,type);
        return contract;
    }

  //由生成的合同补全link
  private void setLinks(){
    for(Contract c : contractMapper.getAllContracts()){
      List<Link> links = linkMapper.getLinksByAB(c.getPartyAName(),c.getPartyBName());

      if(links.size()== 0){
        Link link=new Link(c.getPartyAName(),c.getPartyBName(),c.getAmount());
        linkMapper.createLink(link);
      }else{
        for (Link l : links){
          l.setLinkWeight(l.getLinkWeight()+ c.getAmount());
          linkMapper.updateLink(l);
        }
      }
    }
  }

    @Override
    public GeneralResponse<List<Contract>> randomizeContract() {
        GeneralResponse<List<Contract>> resp=new GeneralResponse<>();
        List<Company> allCompanies=companyMapper.getCompanies();
//        List<Contract> contractList = new ArrayList<>();

        int range=allCompanies.size();

        for(int i =0;i<1000;i++){

            int partyAId=(int)(Math.random()*range) + 1;
            int partyBId=(int)(Math.random()*range) + 1;

            while(partyBId == partyAId){
                partyBId = (int)(Math.random()*range);
            }

            Contract newContract = this.generateContract(partyAId,partyBId);
            contractList.add(newContract);
            contractMapper.createContract(newContract);
        }
        this.setLinks();

        resp.setObj(contractList);
        return resp;
    }

//    public void rollBack

}
