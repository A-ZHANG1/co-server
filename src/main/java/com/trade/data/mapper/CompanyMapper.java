package com.trade.data.mapper;

import com.trade.data.model.Company;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/19.
 */
@Mapper
public interface CompanyMapper {
//    @Insert("insert into Company (companyName,capital,nodeWeight,companyId) values (#{companyName},#{capital},#{nodeWeight},#{companyId})")
//    int insertCompany(Company company);

    @Insert("insert into Company (companyName,capital,nodeWeight) values (#{companyName},#{capital},#{nodeWeight})")
    @Options(useGeneratedKeys = true, keyProperty = "companyId", keyColumn = "companyId")
    int insertCompany(Company company);

    @Select("select * from Company")
    List<Company> getCompanies();

    @Select("select * from Company where companyName=#{companyName}")
    Company getCompanyByCompanyName(String companyName);

    @Select("select SUM(nodeWeight) from Company")
    Double getNodeWeightSum();

    @Update("update Company SET nodeWeight=#{nodeWeight} where companyId=#{companyId}")
    void updateCompanyNodeWeight(Company company);
}
