package skos;
//package vocabulary;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DC;

import skos.*;

/* Exercice 3 : annotation sémantique du jeu de données */
public class livresEtSkos {
	// Namespaces
	public static final String fileCS = "out/computerscience.rdf";
	public static final String fileBooks = "out/livres.rdf";

      public static void main( String[] args ) {
    	  // Chargement des modèles
		  Model CS = ModelFactory.createDefaultModel();
		  InputStream in = FileManager.get().open(fileCS);
		  CS.read(in, ""); 
		  Model books = ModelFactory.createDefaultModel();
		  in = FileManager.get().open(fileBooks);
		  CS.read(in, ""); 
        
    	// Sujets
    	Resource s1 = CS.getResource(skosComputerScience.cs_ns + skosComputerScience.lab0);
    	Resource s11 = CS.getResource(skosComputerScience.cs_ns + skosComputerScience.lab11);
    	Resource s12 = CS.getResource(skosComputerScience.cs_ns + skosComputerScience.lab12);
    	Resource s13 = CS.getResource(skosComputerScience.cs_ns + skosComputerScience.lab13);
    	Resource s21 = CS.getResource(skosComputerScience.cs_ns + skosComputerScience.lab21);
    	
    	// Livre 1 : "A First Course in Database Systems"
		  System.out.println(books.getNsPrefixURI("Livres") + "0-13-861337-0");
	    	//Resource b1 = books.getResource(books.getNsPrefixURI("Livres") + "0-13-861337-0");
	    Resource b1 = books.getResource(Q2.nsB + "0-13-861337-0");
		  
    	//books.add(b1, DC.subject, s1);
    	b1.addProperty(DC.subject, s1);
    	b1.addProperty(DC.subject, s13);
    	b1.addProperty(DC.subject, s21);
    	
    	try {
			FileOutputStream ost = new FileOutputStream("out/livres_et_computerscience.rdf");
			
			books.write(ost, "RDF/XML-ABBREV" );
			System.out.println("Exercice 3 : annotation sémantique du jeu de données");
			books.write(System.out, "N3"); 
	    	//m.write(System.out, "RDF/XML-ABBREV"); 
		} 
    	catch (FileNotFoundException e) {
			e.printStackTrace();
		}
      }
}
