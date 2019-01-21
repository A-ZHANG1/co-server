package com.trade.data.mapper;

import com.trade.data.model.Link;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/20.
 */
@Mapper
public interface LinkMapper {
    @Select("select * from Links")
    List<Link> getLinks();

    @Insert("insert into Links (partyAName,partyBName,linkWeight) values (#{partyAName},#{partyBName},#{linkWeight})")
    @Options(useGeneratedKeys = true, keyProperty = "linkId", keyColumn = "linkId")
    int createLink(Link link);

    @Select("select * from Links where partyAName=#{partyAName} and partyBName=#{partyBName}")
    List<Link> getLinkByAB(@Param("partyAName") String partyAName, @Param("partyBName") String partyBName);

    @Update("update Links SET linkWeight=#{linkWeight} where linkId=#{linkId}")
    void updateLink(Link link);
}
