package demo;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.RDF;

public class Skos_ModelRDF
{

    public static final String NL = System.getProperty("line.separator") ;
    public static final String n3_file = "out/filieres.n3";
    public static final String fil_URL = "file:out/filieres.rdf";

	  public static void main(String[] args)
	    {
		  Model m = ModelFactory.createOntologyModel();
		  m.read(fil_URL);
		  // FileManager.get().readModel( m, n3_file );
		  String skos_ns="http://www.w3.org/2004/02/skos/core#";
		 
		  Resource skosConcept = m.getResource(skos_ns+"Concept" );  
	    	System.out.println("Les individus de la classe "+skosConcept.getLocalName() + " :");
	    	
	    	ResIterator res_i = m.listSubjectsWithProperty( RDF.type, skosConcept );
	    
	    	while (res_i.hasNext())
	    	{ 
	    		 Resource cpt = res_i.nextResource();
	    		 String name = cpt.getLocalName();
	    		 System.out.println("    les concepts skos "+ name);	    		 
	    	}
		  
	    }
	}