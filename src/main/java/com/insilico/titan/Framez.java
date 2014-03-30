/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insilico.titan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkaurelius.titan.core.TitanGraph;
import static com.thinkaurelius.titan.core.attribute.Text.CONTAINS;
import static com.tinkerpop.blueprints.Compare.GREATER_THAN;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.impls.tg.TinkerGraphFactory;
import com.tinkerpop.frames.FramedGraph;
import com.tinkerpop.frames.FramedGraphFactory;
import com.tinkerpop.frames.modules.gremlingroovy.GremlinGroovyModule;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Framez 
{

    public void createGraph()
    {
      HashMap map = new HashMap();
      map.put("key","val1");
      
      TinkerGraph graph = TinkerGraphFactory.createTinkerGraph(); //This graph is pre-populated.
      Vertex card = graph.addVertex("001");
      card.setProperty("name","Sample");
      card.setProperty("label","Event");
      card.setProperty("categories",map);
            
      FramedGraphFactory factory = new FramedGraphFactory(new GremlinGroovyModule()); //(1) Factories should be reused for performance and memory conservation.
      FramedGraph framedGraph = factory.create(graph); //Frame the graph.
      
      Person person = (Person) framedGraph.addVertex("001", Person.class);
      person.setName("abc");
    }
  
    public void workGraph(TitanGraph graph)
    {
        FramedGraphFactory factory = new FramedGraphFactory(new GremlinGroovyModule());
        FramedGraph framedGraph = factory.create(graph); //Frame the graph.
        
        Person person = (Person) framedGraph.addVertex(null,Person.class);
        person.setName("vijay giri");        
        person.setType("alien");        
        person.setAge(70);
        person.setUID(100);
        
        Person friend = (Person) framedGraph.addVertex(null,Person.class);
        friend.setName("friend raj");
        friend.setType("martian");
        friend.setAge(100);
        friend.setUID(200);
        
        HashMap map = new HashMap();
        map.put("key1","value1");
        map.put("key2","value2");
        
        person.setProps(map);
        friend.setProps(map);
        
        //framedGraph.
        //person.setUID((Long) _v.getId());
        
        graph.commit();
    }
    
    public void queryGraph(TitanGraph graph , FramedGraph framedGraph)
    {
      try
      {
        Iterable<Person> itv = framedGraph.getVertices("type","person",Person.class);
        for (Person p : itv)
        {          
          //System.out.println(p);
        }
        //Person person = (Person) framedGraph.getVertices("name","vijay",Person.class);
       // System.out.println(person);
        //System.out.println(person.getName());
        
        GremlinPipeline pipe = new GremlinPipeline();
          
        Iterator itr = graph.getVertices().iterator();
        while (itr.hasNext())
        {
          Vertex v = (Vertex) itr.next();
          //System.out.println(v.getId());
          Set<String> set = v.getPropertyKeys();
          for (String s : set)
          {
            //System.out.println(v.getId()+"="+s);
          }
        }
        
        //System.out.println(graph.getVertex("002"));
        //pipe.start(framedGraph.getVertices("type", "person",Person.class));
                
        pipe.start(graph.getVertices());
        pipe.has("age",GREATER_THAN,65);
        pipe.has("name",CONTAINS,"giri");
        //pipe.filter();
        //pipe2.start(framedGraph.getVertices("name", "vijay",Person.class));
        //pipe3.start(framedGraph.getVertices("name", "friend",Person.class));
        
        //pipe.addPipe(pipe1);
        //pipe.addPipe(pipe2);
        //pipe.addPipe(pipe3);
        
        //System.out.println(pipe.count());
        //Iterator<Person> i = pipe.iterator();uid
        ArrayList q = new ArrayList();
        while (pipe.hasNext())
        {
          //Person y = (Person) pipe.next();
          Vertex x = (Vertex) pipe.next();
          System.out.println(x.getId());
          Person y = (Person) framedGraph.getVertex(x.getId(),Person.class);          
          HashMap m = y.getProps();
          q.add(y);
          ObjectMapper mapper = new ObjectMapper();
          System.out.println(y.getUID()+"="+mapper.writeValueAsString(y));
        }
    }
    catch (Exception e)
    {System.out.println(e);}
    }
}
