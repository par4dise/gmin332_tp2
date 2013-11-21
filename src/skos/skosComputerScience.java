package skos;

import java.io.FileOutputStream;
import java.io.IOException;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.RDF;
import vocabulary.*;

/* Exercice 2 : construction du rÃ©fÃ©rentiel en SKOS */
public class skosComputerScience {
	protected static final String cs_ns ="http://computerscience#";
	
	public static final String lab0 ="ComputerScience";
	public static final String lab11 ="Language";
	public static final String lab12 ="Software";
	public static final String lab13 ="System";
	public static final String lab21 ="DatabaseSystem";
	
	public static String getUri() {
		return cs_ns;
	}
	
	public static String getLabel(int label) {
		switch(label) {
			case 0: return lab0;
			case 11: return lab11;
			case 12: return lab12;
			case 13: return lab13;
			case 21: return lab21;
			default: return "";
		}
	}
	
	public static void main(String args[])
	{
		Model CS = ModelFactory.createDefaultModel();

		CS.setNsPrefix("skos", Skos_Voc.getUri());
		//CS.setNsPrefix("DC",DC.getURI());
		CS.setNsPrefix("cs", cs_ns);				
		
		// litteraux
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
		Resource c0 = CS.createResource(cs_ns+lab0);
		CS.add(c0, RDF.type, Skos_Voc.Concept);
		CS.add(c0, Skos_Voc.prefLabel, lab0);
		
		Resource c11 = CS.createResource(cs_ns+lab11);
		CS.add(c11, RDF.type, Skos_Voc.Concept);
		CS.add(c11, Skos_Voc.prefLabel, lab11);
		
		Resource c12 = CS.createResource(cs_ns+lab12);
		CS.add(c12, RDF.type, Skos_Voc.Concept);
		CS.add(c12, Skos_Voc.prefLabel, lab12);
		
		Resource c13 = CS.createResource(cs_ns+lab13);
		CS.add(c13, RDF.type, Skos_Voc.Concept);
		CS.add(c13, Skos_Voc.prefLabel, lab13);
		
		Resource c21 = CS.createResource(cs_ns+lab21);
		CS.add(c21, RDF.type, Skos_Voc.Concept);
		CS.add(c21, Skos_Voc.prefLabel, lab21);
		
		Resource c22 = CS.createResource(cs_ns+lab22);
		CS.add(c22, RDF.type, Skos_Voc.Concept);
		CS.add(c22, Skos_Voc.prefLabel, lab22);
		
		Resource c23 = CS.createResource(cs_ns+lab23);
		CS.add(c23, RDF.type, Skos_Voc.Concept);
		CS.add(c23, Skos_Voc.prefLabel, lab23);
		
		Resource c24 = CS.createResource(cs_ns+lab24);
		CS.add(c24, RDF.type, Skos_Voc.Concept);
		CS.add(c24, Skos_Voc.prefLabel, lab24);
		
		Resource c25 = CS.createResource(cs_ns+lab25);
		CS.add(c25, RDF.type, Skos_Voc.Concept);
		CS.add(c25, Skos_Voc.prefLabel, lab25);
		
		Resource c26 = CS.createResource(cs_ns+lab26);
		CS.add(c26, RDF.type, Skos_Voc.Concept);
		CS.add(c26, Skos_Voc.prefLabel, lab26);
		
		Resource c31 = CS.createResource(cs_ns+lab31);
		CS.add(c31, RDF.type, Skos_Voc.Concept);
		CS.add(c31, Skos_Voc.prefLabel, lab31);
		
		Resource c32 = CS.createResource(cs_ns+lab32);
		CS.add(c32, RDF.type, Skos_Voc.Concept);
		CS.add(c32, Skos_Voc.prefLabel, lab32);
		
		Resource c33 = CS.createResource(cs_ns+lab33);
		CS.add(c33, RDF.type, Skos_Voc.Concept);
		CS.add(c33, Skos_Voc.prefLabel, lab33);
		
		Resource c34 = CS.createResource(cs_ns+lab34);
		CS.add(c34, RDF.type, Skos_Voc.Concept);
		CS.add(c34, Skos_Voc.prefLabel, lab34);
		
		Resource c35 = CS.createResource(cs_ns+lab35);
		CS.add(c35, RDF.type, Skos_Voc.Concept);
		CS.add(c35, Skos_Voc.prefLabel, lab35);
		
		Resource c36 = CS.createResource(cs_ns+lab36);
		CS.add(c36, RDF.type, Skos_Voc.Concept);
		CS.add(c36, Skos_Voc.prefLabel, lab36);
		
		Resource c37 = CS.createResource(cs_ns+lab37);
		CS.add(c37, RDF.type, Skos_Voc.Concept);
		CS.add(c37, Skos_Voc.prefLabel, lab37);

		CS.add(c11, Skos_Voc.broader, c0);
		CS.add(c12, Skos_Voc.broader, c0);
		CS.add(c13, Skos_Voc.broader, c0);
		CS.add(c21, Skos_Voc.broader, c13);
		CS.add(c22, Skos_Voc.broader, c13);
		CS.add(c23, Skos_Voc.broader, c11);
		CS.add(c24, Skos_Voc.broader, c11);
		CS.add(c25, Skos_Voc.broader, c11);
		CS.add(c26, Skos_Voc.broader, c11);
		CS.add(c31, Skos_Voc.broader, c24);
		CS.add(c32, Skos_Voc.broader, c24);
		CS.add(c33, Skos_Voc.broader, c26);
		CS.add(c34, Skos_Voc.broader, c26);
		CS.add(c35, Skos_Voc.broader, c21);
		CS.add(c36, Skos_Voc.broader, c21);
		CS.add(c37, Skos_Voc.broader, c21);

		try {
			FileOutputStream ost = new FileOutputStream("seb/computerscience.rdf");
			CS.write(ost, "RDF/XML-ABBREV" ); 
            ost.close();
            
            FileOutputStream outStream1 = new FileOutputStream("seb/computerscience.n3");
            CS.write(outStream1, "N3");
            outStream1.close();
        }
		catch (IOException e) {
			System.out.println("pb de fichier");
		} 
		
		System.out.println("Exercice 2 : construction du référentiel en SKOS");
		CS.write(System.out, "N3");
	}
}
