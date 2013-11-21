package tp2_correction;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import vocabulary.Skos_Voc;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
public class LiensSemantiques {


	public static void main(String[] args) {
		Model mCS = ModelFactory.createDefaultModel();
        FileManager.get().readModel( mCS, "CS.rdf" );
        //mCS = ModelFactory.createRDFSModel(mCS);

        Model mLivres = ModelFactory.createDefaultModel();
        FileManager.get().readModel( mLivres, "livres.rdf" );
        //mLivres = ModelFactory.createRDFSModel(mLivres);

        
        Model mLiens = ModelFactory.createDefaultModel();
        //Model mGeneral = mCS.union(mLivres);
        //Model minf = 
        String ns_cs = mCS.getNsPrefixURI("computerScience" );
        String ns_livres = mLivres.getNsPrefixURI("livres" );
        String ns_author = mLivres.getNsPrefixURI("author" );
        
        mLiens.setNsPrefix("livres", ns_livres);
        mLiens.setNsPrefix("author", ns_author);
        mLiens.setNsPrefix("foaf", FOAF.getURI());
        mLiens.setNsPrefix("skos", Skos_Voc.getUri());
        mLiens.setNsPrefix("computerScience", ns_cs);

        mLiens.add(mLivres.getResource(ns_livres+"0-13-861337-0"),DC.subject,mCS.getResource(ns_cs+"dataBaseSystem"));
        mLiens.add(mLivres.getResource(ns_livres+"0-13-040264-8"),DC.subject,mCS.getResource(ns_cs+"dataBaseSystem"));
        mLiens.add(mLivres.getResource(ns_livres+"0-12-369379-9"),DC.subject,mCS.getResource(ns_cs+"queryBased"));
        mLiens.add(mLivres.getResource(ns_livres+"1-55-860920-2"),DC.subject,mCS.getResource(ns_cs+"queryBased"));
        mLiens.add(mLivres.getResource(ns_livres+"9-78-012374137-0"),DC.subject,mCS.getResource(ns_cs+"queryBased"));
        mLiens.add(mLivres.getResource(ns_livres+"1-55-8605231-I"),DC.subject,mCS.getResource(ns_cs+"objectRelational"));
        mLiens.add(mLivres.getResource(ns_livres+"9-78-032182662-6"),DC.subject,mCS.getResource(ns_cs+"noSQL"));
        mLiens.add(mLivres.getResource(ns_livres+"978-1-4493-7146-3"),DC.subject,mCS.getResource(ns_cs+"sparql"));
        
        mLiens = ModelFactory.createRDFSModel(mLiens);
        
        //On ecrit les fichiers
      	 try {       
      	   	  FileOutputStream outStream = new FileOutputStream("liens.rdf");
      	            // exporte le resultat dans un fichier
      	            mLiens.write(outStream, "RDF/XML");
      	            outStream.close();
      	            
      	          FileOutputStream outStream1 = new FileOutputStream("liens.n3");
      	            mLiens.write(outStream1, "N3");
      	            outStream1.close();
      		 }
      	     catch (FileNotFoundException e) {System.out.println("File not found");}
      	     catch (IOException e) {System.out.println("IO problem");}
      	 
      	 //On requete
      	 
      	System.out.println("\nListe des livres :");
     	ResIterator ri = mLivres.listSubjectsWithProperty(RDF.type, mLivres.getResource(ns_livres + "Book"));
     	while (ri.hasNext())
     	{
     		RDFNode s = ri.next();
     		System.out.println("\t* " + s.toString());
     	}
        
        System.out.println("\nListe des livres ayant pour sujet relational:");
     	ResIterator ri2 = mLivres.listSubjectsWithProperty(RDF.type, mLivres.getResource(ns_livres + "Book"));
     	Set<Resource> set = ri2.toSet();
     	for (Resource s : set)
     	{
     		StmtIterator si = mLiens.listStatements(s,DC.subject,mCS.getResource(ns_cs+"relational"));
     		while(si.hasNext())
     		{
     			Statement st = si.next();
     			System.out.println("\t* " +st.getSubject().toString());
     		}
     		//SystemGeneral.out.println("\t* " + s.toString());
     	}
     	
        System.out.println("\nListe des livres ayant pour sujet dataBaseSystem:");
     	ResIterator ri3 = mLivres.listSubjectsWithProperty(RDF.type, mLivres.getResource(ns_livres + "Book"));
     	Set<Resource> set3 = ri3.toSet();
     	for (Resource s : set3)
     	{
     		StmtIterator si = mLiens.listStatements(s,DC.subject,mCS.getResource(ns_cs+"dataBaseSystem"));
     		while(si.hasNext())
     		{
     			Statement st = si.next();
     			System.out.println("\t* " +st.getSubject().toString());
     		}
     	} 
     	
        System.out.println("\nListe des livres ayant pour sujet dataBaseSystem en parcourant les sous-classes:");
     	ResIterator ri4 = mLivres.listSubjectsWithProperty(RDF.type, mLivres.getResource(ns_livres + "Book"));
     	Set<Resource> set4 = ri4.toSet();
     	for (Resource s : set4)
     	{
         	ResIterator ri5 = mCS.listResourcesWithProperty(RDFS.subClassOf, mCS.getResource(ns_cs + "dataBaseSystem"));
     		while(ri5.hasNext()){
     			Resource c = ri5.next();
         		StmtIterator si = mLiens.listStatements(s,DC.subject,c);
         		while(si.hasNext())
         		{
         			Statement st = si.next();
         			System.out.println("\t* " +st.getSubject().toString());
         		}
     		}

     	} 
        
        
	}

}
