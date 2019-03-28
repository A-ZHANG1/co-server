package com.trade.data.neoRepository;

import com.trade.data.nodeEntity.Person;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Wayne.A.Z on 2019/3/21.
 */

@Repository
public interface personRepository extends Neo4jRepository<Person,Long> {
 Person findByName(@Param("name") String name);

  @Query("MATCH (m:Person) WHERE m.name =~ ('(?i).*'+{name}+'.*') RETURN m")
  Collection<Person> findByTitleContaining(@Param("name") String name);

//  @Query("MATCH (m:Person)<-[:ACTED_IN]-(a:Person)
//    RETURN m.title as movie, collect(a.name) as cast LIMIT {limit}")
//    List<Map<String,Object>> graph(@Param("limit") int limit);
}
