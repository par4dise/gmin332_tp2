package skos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.DC;

import exos.Q2;

/* Exercice 3 : annotation s√©mantique du jeu de donn√©es */
public class livresEtSkos {
	// Namespaces
	public static final String fileCS = "out/computerscience.rdf";
	public static final String fileBooks = "out/livres.rdf";

    public static void main( String[] args ) {
		// Chargement des mod√®les
		Model CS = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(fileCS);
		CS.read(in, ""); 
		  
		Model books = ModelFactory.createDefaultModel();
		in = FileManager.get().open(fileBooks);
		books.read(in, ""); 
        
		// Sujets
		//Resource s1 = CS.getResource(skosComputerScience.getUri() + skosComputerScience.getLabel(0));
		//Resource s11 = CS.getResource(skosComputerScience.getUri() + skosComputerScience.getLabel(11));
		//Resource s12 = CS.getResource(skosComputerScience.getUri() + skosComputerScience.getLabel(12));
		//Resource s13 = CS.getResource(skosComputerScience.getUri() + skosComputerScience.getLabel(13));
		//Resource s21 = CS.getResource(skosComputerScience.getUri() + skosComputerScience.getLabel(21));
    	
		// Livre 1 : "A First Course in Database Systems"
		System.out.println("Namespace : " + Q2.getUriBooks());  // Èquivalent ‡ : books.getNsPrefixURI("Livres")
		//Resource b1 = books.getResource(books.getNsPrefixURI("Livres") + "0-13-861337-0");
		Resource b1 = books.getResource(Q2.getUriBooks() + "0-13-861337-0");
		  
    	//books.add(b1, DC.subject, s1);
    	b1.addProperty(DC.subject, CS.getResource(skosComputerScience.getUri() + skosComputerScience.getLabel(0)));
    	b1.addProperty(DC.subject, CS.getResource(skosComputerScience.getUri() + skosComputerScience.getLabel(13)));
    	b1.addProperty(DC.subject, CS.getResource(skosComputerScience.getUri() + skosComputerScience.getLabel(21)));
    	
    	try {
			FileOutputStream ost = new FileOutputStream("out/livres_et_computerscience.rdf");
			
			books.write(ost, "RDF/XML-ABBREV" );
			System.out.println("Exercice 3 : annotation sÈmantique du jeu de donnÈes");
			//books.write(System.out, "N3"); 
			books.write(System.out, "RDF/XML-ABBREV"); 
		} 
    	catch (FileNotFoundException e) {
			e.printStackTrace();
		}
      }
}
