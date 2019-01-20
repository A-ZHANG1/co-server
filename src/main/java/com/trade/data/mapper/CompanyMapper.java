package com.trade.data.mapper;

import com.trade.data.model.Company;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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
    List<Company> getCompanyByCompanyName(String companyName);
}
