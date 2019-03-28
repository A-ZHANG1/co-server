package com.trade.data.nodeEntity;
import lombok.Data;
import lombok.ToString;
import org.neo4j.ogm.annotation.*;

/**
 * Created by Wayne.A.Z on 2019/3/21.
 */
//lombok注解
@Data
@ToString
//neo4j注解
@NodeEntity
public class Person
{
  @Id
  @GeneratedValue
  private Long id;

  @Property
  private String name;

//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }
}
