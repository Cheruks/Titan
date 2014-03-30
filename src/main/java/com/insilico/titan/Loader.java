/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insilico.titan;

import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Index;
import com.tinkerpop.blueprints.Vertex;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

/**
 *
 * @author vijay
 */
public class Loader 
{
  public static Configuration initConfig()
  {
      Configuration conf = new BaseConfiguration();
      conf.setProperty("storage.backend","hbase");
      conf.setProperty("storage.tablename","spidral");
      
      conf.setProperty("storage.index.search.backend","elasticsearch");
      conf.setProperty("storage.index.search.directory","/app/elasticsearch");
      conf.setProperty("storage.index.search.client-only",false);
      conf.setProperty("storage.index.search.local-mode",true);
        
      return conf;
  }
  
  public static void initKeys(TitanGraph graph)
  {
      //graph.createKeyIndex("id", Vertex.class);
      //graph.makeKey("id").dataType(String.class).indexed(Vertex.class).unique().make();
      //graph.makeKey("id").dataType(String.class).indexed(Edge.class).unique().make();
    
      graph.makeKey("type").dataType(String.class).indexed(Vertex.class).make();
      graph.makeKey("user_handle").dataType(String.class).indexed(Vertex.class).unique().make();
      graph.makeKey("user_role").dataType(String.class).indexed(Vertex.class).make();
      graph.makeKey("card_category").dataType(ArrayList.class).indexed(Vertex.class).make();
      graph.makeKey("card_ext_attrib").dataType(HashMap.class).indexed(Vertex.class).make();
      //graph.makeKey("uid").dataType(String.class).indexed(Vertex.class).unique().make();
      //graph.makeKey("uid").dataType(String.class).indexed(Edge.class).unique().make();
      //graph.createKeyIndex("user.handle",String.class).indexed("search", Vertex.class).make();
      //graph.makeKey("card.category").dataType(String.class).indexed("search", Vertex.class).make();
      //graph.makeKey("card.priority").dataType(String.class).indexed("search", Vertex.class).make();
      //graph.makeKey("card.favourite").dataType(Boolean.class).indexed(Vertex.class).make();
      //graph.makeKey("category.name").dataType(String.class).indexed("search", Vertex.class).make();
  }
  
}
