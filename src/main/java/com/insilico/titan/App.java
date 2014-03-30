
package com.insilico.titan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import org.apache.commons.configuration.Configuration;

import com.tinkerpop.blueprints.util.wrappers.batch.BatchGraph;
import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws JsonProcessingException, IOException
    {
      
        System.out.println( "Hello World!" );
        
        Configuration config = Loader.initConfig();
        TitanGraph graph = TitanFactory.open(config);
        Loader.initKeys(graph);
        
        Utils util = new Utils();
        util.importGraphML(graph);
        
        //graph.createKeyIndex("name",Vertex.class);
        //graph.createKeyIndex("type",Vertex.class);
        //graph.createKeyIndex("uid",Vertex.class);
        //graph.createKeyIndex("uid",Edge.class);
        
        // 1) Index vertices by their unique name property
        //graph.makeKey("type").dataType(String.class).indexed(Vertex.class).make();
        // 2) Index vertices by their age property
        //graph.makeKey("age").dataType(Integer.class).indexed("search", Vertex.class).indexed(Vertex.class).unique().make();
        //graph.makeKey("age").dataType(Integer.class).indexed("search", Vertex.class).make();
        //graph.makeKey("name").dataType(String.class).indexed("search", Vertex.class).indexed(Vertex.class).unique().make();
                
    }
}
