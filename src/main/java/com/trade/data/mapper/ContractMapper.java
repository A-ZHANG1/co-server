package com.trade.data.mapper;

import com.trade.data.model.Contract;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/18.
 */
@Mapper
public interface ContractMapper {
    //keyProperty:model中的主键名
    //keyColumn:DB中的主键名
    @Insert("insert into Contract (contractId, partyAName, partyBName, amount) values(#{contractId}, #{partyAName}, #{partyBName}, #{amount})")
    @Options(useGeneratedKeys = true, keyProperty = "contractId", keyColumn = "contractId")
    int createContract(Contract contract);

    @Select("select * from Contract where contractId=#{contractId}")
    List<Contract> getContractByContractId(@Param("contractId") String contractId);

    @Select("select * from Contract")
    List<Contract> getAllContracts();
}
