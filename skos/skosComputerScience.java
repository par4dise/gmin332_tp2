
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.DCTerms;

/* Exercice 2 : construction du référentiel en SKOS */
public class skosComputerScience {
	public static final String skos_ns ="http://www.w3.org/2004/02/skos/core#";
	public static final String cs_ns ="http://computerscience#";
	
	public static void main(String args[])
	{
		Model m = ModelFactory.createDefaultModel();
			 
		m.setNsPrefix("skos", skos_ns);
		m.setNsPrefix("cs", cs_ns);				
				
		// ressource descripteur
		Resource skConcept = m.createResource(skos_ns+"Concept");
		Property skPrefLabel = m.createProperty(skos_ns+"prefLabel");
		/* A hierarchical link between two concepts indicates that one is 
		 * in some way more general ("broader") than the other ("narrower").
		 */
		Property hasBroader = m.createProperty(skos_ns+"broader");
		
		// litteraux
		String lab0 ="ComputerScience";
		String lab11 ="Language";
		String lab12 ="Software";
		String lab13 ="System";
		String lab21 ="DatabaseSystem";
		String lab22 ="OperatingSystem";
		String lab23 ="ObjectOriented";
		String lab24 ="Descriptive";
		String lab25 ="Functional";
		String lab26 ="QueryBased";
		String lab31 ="KnowledgeBased";
		String lab32 ="RDF";
		String lab33 ="SQL";
		String lab34 ="SPARQL";
		String lab35 ="Relational";
		String lab36 ="Object-Relational";
		String lab37 ="NoSQL";

		// 1. Description & prefLabel
		Resource c0 = m.createResource(cs_ns+lab0);
		m.add(c0, RDF.type, skConcept);
		m.add(c0, skPrefLabel, lab0);
		
		Resource c11 = m.createResource(cs_ns+lab11);
		m.add(c11, RDF.type, skConcept);
		m.add(c11, skPrefLabel, lab11);
		
		Resource c12 = m.createResource(cs_ns+lab12);
		m.add(c12, RDF.type, skConcept);
		m.add(c12, skPrefLabel, lab12);
		
		Resource c13 = m.createResource(cs_ns+lab13);
		m.add(c13, RDF.type, skConcept);
		m.add(c13, skPrefLabel, lab13);
		
		Resource c21 = m.createResource(cs_ns+lab21);
		m.add(c21, RDF.type, skConcept);
		m.add(c21, skPrefLabel, lab21);
		
		Resource c22 = m.createResource(cs_ns+lab22);
		m.add(c22, RDF.type, skConcept);
		m.add(c22, skPrefLabel, lab22);
		
		Resource c23 = m.createResource(cs_ns+lab23);
		m.add(c23, RDF.type, skConcept);
		m.add(c23, skPrefLabel, lab23);
		
		Resource c24 = m.createResource(cs_ns+lab24);
		m.add(c24, RDF.type, skConcept);
		m.add(c24, skPrefLabel, lab24);
		
		Resource c25 = m.createResource(cs_ns+lab25);
		m.add(c25, RDF.type, skConcept);
		m.add(c25, skPrefLabel, lab25);
		
		Resource c26 = m.createResource(cs_ns+lab26);
		m.add(c26, RDF.type, skConcept);
		m.add(c26, skPrefLabel, lab26);
		
		Resource c31 = m.createResource(cs_ns+lab31);
		m.add(c31, RDF.type, skConcept);
		m.add(c31, skPrefLabel, lab31);
		
		Resource c32 = m.createResource(cs_ns+lab32);
		m.add(c32, RDF.type, skConcept);
		m.add(c32, skPrefLabel, lab32);
		
		Resource c33 = m.createResource(cs_ns+lab33);
		m.add(c33, RDF.type, skConcept);
		m.add(c33, skPrefLabel, lab33);
		
		Resource c34 = m.createResource(cs_ns+lab34);
		m.add(c34, RDF.type, skConcept);
		m.add(c34, skPrefLabel, lab34);
		
		Resource c35 = m.createResource(cs_ns+lab35);
		m.add(c35, RDF.type, skConcept);
		m.add(c35, skPrefLabel, lab35);
		
		Resource c36 = m.createResource(cs_ns+lab36);
		m.add(c36, RDF.type, skConcept);
		m.add(c36, skPrefLabel, lab36);
		
		Resource c37 = m.createResource(cs_ns+lab37);
		m.add(c37, RDF.type, skConcept);
		m.add(c37, skPrefLabel, lab37);

		m.add(c0, hasBroader, c11);
		m.add(c0, hasBroader, c12);
		m.add(c0, hasBroader, c13);
		m.add(c13, hasBroader, c21);
		m.add(c13, hasBroader, c22);
		m.add(c11, hasBroader, c23);
		m.add(c11, hasBroader, c24);
		m.add(c11, hasBroader, c25);
		m.add(c11, hasBroader, c26);
		m.add(c24, hasBroader, c31);
		m.add(c24, hasBroader, c32);
		m.add(c26, hasBroader, c33);
		m.add(c26, hasBroader, c34);
		m.add(c21, hasBroader, c35);
		m.add(c21, hasBroader, c36);
		m.add(c21, hasBroader, c37);
		/*
		 * inversé
		m.add(c11, hasBroader, c0);
		m.add(c12, hasBroader, c0);
		m.add(c13, hasBroader, c0);
		m.add(c21, hasBroader, c13);
		m.add(c22, hasBroader, c13);
		m.add(c23, hasBroader, c11);
		m.add(c24, hasBroader, c11);
		m.add(c25, hasBroader, c11);
		m.add(c26, hasBroader, c11);
		m.add(c31, hasBroader, c24);
		m.add(c32, hasBroader, c24);
		m.add(c33, hasBroader, c26);
		m.add(c34, hasBroader, c26);
		m.add(c35, hasBroader, c21);
		m.add(c36, hasBroader, c21);
		m.add(c37, hasBroader, c21);*/
		
		//c31.addProperty(hasBroader, 24);

		try {
			FileOutputStream ost = new FileOutputStream("skos_computer_science.rdf");
			//
			System.out.println("Exercice 2 : construction du référentiel en SKOS");
			m.write(System.out, "N3");
			m.write(ost, "RDF/XML-ABBREV" ); }
			catch (FileNotFoundException e) {
				System.out.println("pb de fichier");
		} 
	}
}
