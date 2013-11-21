
package tp2_correction;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.XSD;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import vocabulary.Skos_Voc;

public class ComputerScience_SKOS {
    static final String cs_ns ="http://www.computerscience.fr/skos#";
    Model m;

    public ComputerScience_SKOS() {
        m = ModelFactory.createDefaultModel();
        m.setNsPrefix("info",cs_ns);
        m.setNsPrefix("skos",Skos_Voc.getUri());
        m.setNsPrefix("foaf",FOAF.getURI());
	m.setNsPrefix("dc",DC.getURI());
	m.setNsPrefix("xsd", XSD.getURI());
        
    }
    
    public void addSkosConcept(String URI,String broaderConcept,String prefLabel, String definition, String altLabel){
        Resource c = m.createResource(URI);
        c.addProperty(RDF.type,Skos_Voc.Concept);
        if(!broaderConcept.isEmpty()){
           Resource c_broader=m.createResource(broaderConcept);
           c.addProperty(Skos_Voc.broader, c_broader);
        }
        if(!prefLabel.isEmpty())
            c.addProperty(Skos_Voc.prefLabel, m.createTypedLiteral(prefLabel, XSD.getURI()+"string"));
        if(!definition.isEmpty())
            c.addProperty(Skos_Voc.definition, m.createTypedLiteral(definition, XSD.getURI()+"string"));
        if(!altLabel.isEmpty())
            c.addProperty(Skos_Voc.altLabel, m.createTypedLiteral(altLabel, XSD.getURI()+"string"));
    }
    
     public static void main(String[] args) {
         
         ComputerScience_SKOS CSSkos = new ComputerScience_SKOS();
         CSSkos.addSkosConcept(cs_ns+"ComputerScience",   // URI
        		 			   // broaderConcept
        		 			   "", 			
        		 			   // prefLabel 
        		 			   "Computer Science", 						
        		 			   // definition
        		 			   "Computer Science" + " (abbreviated CS or CompSci) is the scientific and practical approach to computation "  + "and its applications", 
        		 			   // altLabel
        		 			   "Informatics");
         CSSkos.addSkosConcept(cs_ns+"System", cs_ns+"ComputerScience","System", "", "Informatic Systems");
         CSSkos.addSkosConcept(cs_ns+"DatabaseSystem", cs_ns+"System","Database System", "", "General Dababase");
         CSSkos.addSkosConcept(cs_ns+"Relational", cs_ns+"DatabaseSystem","Relational Database", "", "Traditional Database");
         CSSkos.addSkosConcept(cs_ns+"NoSql", cs_ns+"DatabaseSystem","Not Only SQL", "A NoSQL database provides "
                 + "a mechanism for storage and retrieval of data that employs less constrained consistency "
                 + "models than traditional relational databases", "NOSql Database");
         
         // ajout d'individus qui ont pour type les concepts skos qu'on a défini ( utilisé pour l'inference sur les individus )
         Resource i_databaseSystem = CSSkos.m.createResource(cs_ns+"i_databaseSystem");
         CSSkos.m.add(i_databaseSystem, RDF.type, CSSkos.m.getResource(cs_ns+"DatabaseSystem"));
         
         Resource i_relational = CSSkos.m.createResource(cs_ns+"i_relational");
         CSSkos.m.add(i_relational, RDF.type, CSSkos.m.getResource(cs_ns+"Relational"));
         
         Resource i_NoSql = CSSkos.m.createResource(cs_ns+"i_NoSql");
         CSSkos.m.add(i_NoSql, RDF.type, CSSkos.m.getResource(cs_ns+"NoSql"));
         
         CSSkos.m.write(System.out,"N3");
         
         try {       
		    	  FileOutputStream outStream = new FileOutputStream("out/info.rdf");
		             // exporte le resultat dans un fichier
		              CSSkos.m.write(outStream, "RDF/XML");
		             outStream.close();
		           FileOutputStream outStream1 = new FileOutputStream("out/info.n3");
		              CSSkos.m.write(outStream1, "N3");
		             outStream1.close();
	    	 }
		      catch (FileNotFoundException e) {System.out.println("File not found");}
		      catch (IOException e) {System.out.println("IO problem");}
			 
		
     }
}

