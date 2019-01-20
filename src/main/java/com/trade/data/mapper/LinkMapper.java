package com.trade.data.mapper;

import com.trade.data.model.Link;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Wayne.A.Z on 2019/1/20.
 */
@Mapper
public interface LinkMapper {
    @Select("select * from Links")
    List<Link> getLinks();

    @Insert("insert into Links (partyAName,partyBName,linkWeight) values (#{partyAName},#{partyBName},#{linkWeight})")
    int createLink(Link link);
}
