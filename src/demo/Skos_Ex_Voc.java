package demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.XSD;
import vocabulary.*;

public class Skos_Ex_Voc {

	static final String filiere_ns ="http://www.test.fr/filiere#";
	
	public static void main(String args[]) {
		try {	
			 Model m = ModelFactory.createDefaultModel();
				m.setNsPrefix("filiere",filiere_ns);
				m.setNsPrefix("skos",Skos_Voc.getUri());
				m.setNsPrefix("FOAF",FOAF.getURI());
				m.setNsPrefix("DC",DC.getURI());
				m.setNsPrefix("XSD", XSD.getURI());
				
			// un individu skos:ConceptScheme
			 Resource cs1 = m.createResource(filiere_ns+"Filieres_universitaires");
			 cs1.addProperty(RDF.type, Skos_Voc.ConceptScheme);
			 
			 // un premier individu skos:Concept
			 Resource f1 = m.createResource(filiere_ns+"informatique");
			 f1.addProperty(RDF.type, Skos_Voc.Concept);
			
			 // un second individu skos:Concept
			 Resource f2 = m.createResource(filiere_ns+"decol");
			 f2.addProperty(RDF.type, Skos_Voc.Concept);
			 f2.addProperty(Skos_Voc.broader, f1);
			 f2.addProperty(Skos_Voc.definition, m.createTypedLiteral("data and knowledge", XSD.getURI()+"string"));
			 
			// un troisieme individu skos:Concept
			 Resource f3 = m.createResource(filiere_ns+"aigle");
			 f3.addProperty(RDF.type, Skos_Voc.Concept);
			 f3.addProperty(Skos_Voc.broader, f1);
			 
				// un quatrieme individu skos:Concept
			 Resource u1 = m.createResource(filiere_ns+"universite_montpellier_2");
			 u1.addProperty(RDF.type, Skos_Voc.Concept);
			 u1.addProperty(Skos_Voc.related, f2);
			
			 
			 // annotations pour ConceptScheme
			 
			// Literal maintenant = m.createTypedLiteral(Calendar.getInstance(),XSD.getURI()+"dateTime");
			 Literal maintenant = m.createTypedLiteral(Calendar.getInstance());
			 cs1.addLiteral(DC.date, maintenant);
			 cs1.addProperty(DC.subject,"un thesaurus sur les filieres univ.");
			 cs1.addProperty(FOAF.maker, m.createResource(FOAF.getURI()+"Pierre_Dupond").addProperty(RDF.type, FOAF.Person).addLiteral(FOAF.name, "Dupont").addLiteral(FOAF.mbox,"dupont@lirmm.fr"));
			 
			 // afficher les triplets ainsi definis
			 m.write(System.out, "N3");
	    	 try {       
		    	  FileOutputStream outStream = new FileOutputStream("out/filieres.rdf");
		             // exporte le resultat dans un fichier
		             m.write(outStream, "RDF/XML");
		             outStream.close();
		             
		           FileOutputStream outStream1 = new FileOutputStream("out/filieres.n3");
		             m.write(outStream1, "N3");
		             outStream1.close();
	    	 }
		      catch (FileNotFoundException e) {System.out.println("File not found");}
		      catch (IOException e) {System.out.println("IO problem");}
			 
		}
		catch (Exception e)
		{System.out.println("failure"+e);}
	} 
	
}