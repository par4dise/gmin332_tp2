package tp2_correction;

import java.util.Iterator;

import vocabulary.Skos_Voc;


import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;



public class Livre_CS_Explanation
{

	 public static final String n3_file = "CS_Livre_MD.n3";
	  public static void main(String[] args)
	    {
		  Model m = ModelFactory.createOntologyModel();
		  //FileManager.get().readModel( m, n3_file );
		  InfModel inf_m = ModelFactory.createRDFSModel(m);
	       
	      String computerScience = m.getNsPrefixURI("cs" );
	      Property dctSubject = m.getProperty("http://purl.org/dc/terms/"+"subject");
	      System.out.println(dctSubject);
		  Resource databaseSystem = m.getResource(computerScience+"DatabaseSystem" ); 
		  Property skosBroader = m.getProperty(Skos_Voc.getUri()+"broader");
	
	
		  // objectif : exploiter les concepts skos soit comme individus, soit comme classes
		  // attachement à RDFS.subClassOf permet d'envisager une arborescence de classes RDFS
		  // et d'appliquer la transitivité par le biais du prédicat subClassOf
		 
		  // TEST 1 : A commenter ou a decommenter pour voir les effets 
		   m.add(skosBroader, RDFS.subPropertyOf, RDFS.subClassOf);
	      System.out.println("Les individus specialisant "+databaseSystem.getLocalName());
	  
	    	// la transivite ne s'applique pas skosBroader -> necessite le passage a owl
	    	ResIterator res_i = inf_m.listSubjectsWithProperty(skosBroader, databaseSystem );
	    
	    	while (res_i.hasNext())
	    	{ 
	    		 Resource cpt = res_i.nextResource();
	    		 String name = cpt.getLocalName();
	    		 System.out.println("broader "+cpt.getLocalName());
	    		 
	    		 
	    	}
	    	
	   	ResIterator res_s = inf_m.listSubjectsWithProperty(RDFS.subClassOf, databaseSystem );
	 // la transivite s'applique si on a declare que skos:broader est une sous propriete de rdfs:subclassOf
	    	while (res_s.hasNext())
	    	{ 
	    		 Resource cpt = res_s.nextResource();
	    		 String name = cpt.getLocalName();
	    		 System.out.println("subclass of "+cpt.getLocalName());
	    		 
	    		 
	    	}
	 // TEST 2   	
	// idee ici : voir la difference de modele entre explicite et infere
	// on voit bien ici toute la difference entre skos:concept juste comme ressource (individu)
	// et skos:concept comme classe 
	//    	Model diff = inf_m.difference(m);
	//    	diff.write(System.out, "N3");
	    }
	}
