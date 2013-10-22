package demo;

import com.hp.hpl.jena.rdf.model.*;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class RDF_Skos {
	public static final	String skos_ns ="http://www.w3.org/2004/02/skos/core#";
	public static final	String agrovoc_ns ="http://aims.fao.org/aos/agrovoc#";
	public static void main( String[] args ) {
		String sk = skos_ns;
		String fao = agrovoc_ns;
		Model m = ModelFactory.createDefaultModel();
		m.setNsPrefix("skos", sk);
		m.setNsPrefix("agrovoc", fao);
	// ressource descripteur
	Resource skConcept = m.createResource(sk+"Concept");
	Property prefLabel = m.getProperty(sk+"prefLabel");
	Property hasBroader = m.getProperty(sk+"broader");
	Resource c1 = m.createResource(fao+"c15807");
	Resource c2 = m.createResource(fao+"703");
	Resource c3 = m.createResource(fao+"704");
	m.add(c1, RDF.type, skConcept);
	m.add(c2, RDF.type, skConcept);
	m.add(c3, RDF.type, skConcept);
	
	// litteraux
	String lab2 ="Attalea";
	String lab1 ="Arecaceae";
	String lab3 ="Attalea funifera";
	
	m.add(c2,prefLabel,lab2 );
	m.add(c1,prefLabel,lab1 );
	m.add(c3,prefLabel,lab3 );
	m.add(c2,hasBroader,c1 );
	m.add(c3,hasBroader,c2);

	try {
	FileOutputStream ost = new FileOutputStream("out.rdf");
	//
	m.write(System.out, "N3");
	m.write(ost, "RDF/XML-ABBREV" ); }
	catch (FileNotFoundException e) {
		System.out.println("pb de fichier");
	} 
}}