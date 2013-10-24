package tp2_correction_2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import vocabulary.Skos_Voc;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.sparql.vocabulary.VocabTestQuery;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.DC_10;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.XSD;

public class CS {
	
	static final String computerSciences_ns ="http://www.test.fr/computerSciences#";
	
	public static void main(String args[])
	{

		try
		{	
			 Model m = ModelFactory.createDefaultModel();
				m.setNsPrefix("computerScience",computerSciences_ns);
				m.setNsPrefix("skos",Skos_Voc.getUri());
				m.setNsPrefix("FOAF",FOAF.getURI());
				m.setNsPrefix("DC",DC.getURI());
				m.setNsPrefix("XSD", XSD.getURI());
				
				// skos:ConceptScheme
				Resource csComputerSciences = m.createResource(computerSciences_ns+"Computer_Sciences");
				csComputerSciences.addProperty(RDF.type, Skos_Voc.ConceptScheme);
				 
				 
				// skos:Concept
				Resource rLanguage = m.createResource(computerSciences_ns+"language");
				rLanguage.addProperty(RDF.type, Skos_Voc.Concept);
				rLanguage.addProperty(Skos_Voc.broader, csComputerSciences);
				
				Resource rSystem = m.createResource(computerSciences_ns+"system");
				rSystem.addProperty(RDF.type, Skos_Voc.Concept);
				rSystem.addProperty(Skos_Voc.broader, csComputerSciences);
				
				Resource rSoftware = m.createResource(computerSciences_ns+"software");
				rSoftware.addProperty(RDF.type, Skos_Voc.Concept);
				rSoftware.addProperty(Skos_Voc.broader, csComputerSciences);

				Resource rDataBaseSystem = m.createResource(computerSciences_ns+"dataBaseSystem");
				rDataBaseSystem.addProperty(RDF.type, Skos_Voc.Concept);
				rDataBaseSystem.addProperty(Skos_Voc.broader, rSystem);
				
				Resource rOperatingSystem = m.createResource(computerSciences_ns+"operatingSystem");
				rOperatingSystem.addProperty(RDF.type, Skos_Voc.Concept);
				rOperatingSystem.addProperty(Skos_Voc.broader, rSystem);
				
				Resource rObjectOriented = m.createResource(computerSciences_ns+"objectOriented");
				rObjectOriented.addProperty(RDF.type, Skos_Voc.Concept);
				rObjectOriented.addProperty(Skos_Voc.broader, rLanguage);
				
				Resource rDescriptive = m.createResource(computerSciences_ns+"descriptive");
				rDescriptive.addProperty(RDF.type, Skos_Voc.Concept);
				rDescriptive.addProperty(Skos_Voc.broader, rLanguage);
				
				Resource rFunctional = m.createResource(computerSciences_ns+"functional");
				rFunctional.addProperty(RDF.type, Skos_Voc.Concept);
				rFunctional.addProperty(Skos_Voc.broader, rLanguage);
				
				Resource rQueryBased = m.createResource(computerSciences_ns+"queryBased");
				rQueryBased.addProperty(RDF.type, Skos_Voc.Concept);
				rQueryBased.addProperty(Skos_Voc.broader, rLanguage);
				
				Resource rKnowledgeBased = m.createResource(computerSciences_ns+"knowledgeBased");
				rKnowledgeBased.addProperty(RDF.type, Skos_Voc.Concept);
				rKnowledgeBased.addProperty(Skos_Voc.broader, rDescriptive);
				
				Resource rRDF = m.createResource(computerSciences_ns+"rdf");
				rRDF.addProperty(RDF.type, Skos_Voc.Concept);
				rRDF.addProperty(Skos_Voc.broader, rDescriptive);
				
				Resource rSQL = m.createResource(computerSciences_ns+"sql");
				rSQL.addProperty(RDF.type, Skos_Voc.Concept);
				rSQL.addProperty(Skos_Voc.broader, rQueryBased);
				
				Resource rSparql = m.createResource(computerSciences_ns+"sparql");
				rSparql.addProperty(RDF.type, Skos_Voc.Concept);
				rSparql.addProperty(Skos_Voc.broader, rQueryBased);
				
				Resource rRelational = m.createResource(computerSciences_ns+"relational");
				rRelational.addProperty(RDF.type, Skos_Voc.Concept);
				rRelational.addProperty(Skos_Voc.broader, rDataBaseSystem);
				
				Resource rObjectRelational = m.createResource(computerSciences_ns+"objectRelational");
				rObjectRelational.addProperty(RDF.type, Skos_Voc.Concept);
				rObjectRelational.addProperty(Skos_Voc.broader, rDataBaseSystem);
				
				Resource rnoSQL = m.createResource(computerSciences_ns+"noSQL");
				rnoSQL.addProperty(RDF.type, Skos_Voc.Concept);
				rnoSQL.addProperty(Skos_Voc.broader, rDataBaseSystem);
				
		        m.add(Skos_Voc.broader,RDFS.subPropertyOf,RDFS.subClassOf);
		        m=ModelFactory.createRDFSModel(m);
				
		    	 try {       
			    	  FileOutputStream outStream = new FileOutputStream("CS.rdf");
			             // exporte le resultat dans un fichier
			             m.write(outStream, "RDF/XML");
			             outStream.close();
			             
			           FileOutputStream outStream1 = new FileOutputStream("CS.n3");
			             m.write(outStream1, "N3");
			             outStream1.close();
		    	 }
			      catch (FileNotFoundException e) {System.out.println("File not found");}
			      catch (IOException e) {System.out.println("IO problem");}
				
		}
		catch (Exception e)
		{
			System.out.println("failure" + e);
		}
		
	}
}
