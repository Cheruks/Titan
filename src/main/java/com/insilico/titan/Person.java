/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insilico.titan;

import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.annotations.gremlin.GremlinGroovy;
import java.util.HashMap;

public interface Person 
{
  @Property("type")
  public String getType();
  
  @Property("type")
  public String setType(String type);
  
  @Property("name")
  public String getName();
  
  @Property("name")
  public void setName(String name);
  
  @Property("age")
  public Integer getAge();
  
  @Property("age")
  public void setAge(Integer name);
  
  @Property("uid")
  public long getUID();
  
  @Property("uid")
  public void setUID(long uid);

  @Property("props")
  public HashMap getProps();
  
  @Property("props")
  public void setProps(HashMap map);
  
  @Adjacency(label="knows")
  public Iterable<Person> getKnowsPeople();

  @Adjacency(label="knows")
  public void addKnowsPerson(final Person person);

  @GremlinGroovy("it.out('knows').out('knows').dedup") //Make sure you use the GremlinGroovy module! #1
  public Iterable<Person> getFriendsOfAFriend();
}
