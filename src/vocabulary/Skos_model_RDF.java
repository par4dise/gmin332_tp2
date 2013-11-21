package vocabulary;

import java.util.Iterator;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDF;

public class Skos_model_RDF {

    public static final String NL = System.getProperty("line.separator") ;
    public static final String n3_file = "out/filieres.n3";
    public static final String rdf_file = "out/filieres.rdf";
    
	  public static void main(String[] args)
	    {
		  Model m = ModelFactory.createOntologyModel();
		  m.read(rdf_file);
		  //FileManager.get().readModel( m, n3_file );
		  
		  String fil_ns ="http://www.test.fr/filiere#";
		  String skos_ns="http://www.w3.org/2004/02/skos/core#";
		  
	      String country = m.getNsPrefixURI("filiere" );
		 
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