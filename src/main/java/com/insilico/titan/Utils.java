/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insilico.titan;

import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLReader;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.output.ByteArrayOutputStream;


public class Utils 
{
  public void importGraphML(TitanGraph graph)
  {
    try
    {
      InputStream in = new FileInputStream("/home/vijay/Documents/SpidralGraphML");
      GraphMLReader reader = new GraphMLReader(graph);
      reader.setVertexIdKey("_id");
      reader.setEdgeIdKey("_id");
      reader.setEdgeLabelKey("_label");
      reader.inputGraph(in);
      
      graph.commit();
    }
    catch (Exception e)
    {System.out.println(e);}
  }
  
  public void exportGraphML(TitanGraph graph)
  {
    try
    {
      OutputStream out = new ByteArrayOutputStream();        
      GraphMLWriter writer = new GraphMLWriter(graph);
      //writer.setVertexKeyTypes(vertexKeyTypes);
      //writer.setEdgeKeyTypes(edgeKeyTypes);
      writer.outputGraph(out);
      System.out.println(out.toString());
    }
    catch (Exception e)
    {System.out.println(e);}
  }
  
}
