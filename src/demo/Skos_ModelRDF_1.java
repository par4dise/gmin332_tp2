package demo;

import java.util.Iterator;

import com.hp.hpl.jena.sparql.core.ResultBinding;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;


public class Skos_ModelRDF_1
{

    public static final String NL = System.getProperty("line.separator") ;

	  public static void main(String[] args)
	    {
		  Model m = ModelFactory.createOntologyModel();
		  String fil_URL = "file:filieres.rdf";
		  m.read(fil_URL);
		  String fil_ns="http://www.test.fr/filiere#";
		  String skos_ns="http://www.w3.org/2004/02/skos/core#";
		 
		  Resource skosConcept = m.getResource(skos_ns+"Concept" );  
	    	System.out.println("Les individus de la classe "+skosConcept.getLocalName());
	    	
	    	
	    	ResIterator res_i = m.listSubjectsWithProperty( RDF.type, skosConcept );
	    
	    	while (res_i.hasNext())
	    	{ 
	    		 Resource cpt = res_i.nextResource();
	    		 String name = cpt.getLocalName();
	    		 System.out.println("les concepts skos "+cpt.getLocalName());
	    		 
	    	}
		  
	    }
	}