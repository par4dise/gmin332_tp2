package tp2_correction;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DC;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Livres {
    
    ArrayList<Livre> livres = null;
    Model m;
    String ns_livres = "http://www.livres.fr#";
    String ns_auteurs = "http://www.auteurs.fr#";  
    static final String cs_ns ="http://www.computerscience.fr/skos#";
    
    // predicats qui vont pointer sur des litteraux types
    Property PublicationDate ;
    Property PublicationTitle;
    Property PublicationPrice;

    // predicats qui vont pointer sur d'autres ressources
    Property PublicationAuthor;
    	
    // ressource Livre
    Resource Book ;
    Resource Author;
    	
    public Livres(){
        livres= new ArrayList<Livre>();
        
        m = ModelFactory.createDefaultModel();

        PublicationDate = m.createProperty(ns_livres+"PublicationDate");
        PublicationTitle = m.createProperty(ns_livres+"PublicationTitle");
        PublicationPrice = m.createProperty(ns_livres+"PublicationPrice");
        PublicationAuthor = m.createProperty(ns_livres+"PublicationAuthor");
        
        Book = m.createResource(ns_livres+"Book");
        Author = m.createResource(ns_auteurs+"Author");
        
        m.add(DC.creator, RDFS.domain, Author);
    	m.add(Author, RDF.type, FOAF.maker);
        m.setNsPrefix("livres", ns_livres);
        m.setNsPrefix("auteurs",ns_auteurs);
        m.setNsPrefix("info", cs_ns);
        m.setNsPrefix("foaf",FOAF.getURI());
        m.setNsPrefix("dc", DC.getURI());
    }
    
    public void add_livre(Livre l){
        Literal l_title = m.createTypedLiteral(l.l_title, XSDDatatype.XSDstring) ;
    	Literal l_year = m.createTypedLiteral(l.l_year, XSDDatatype.XSDdecimal) ;
    	Literal l_price = m.createTypedLiteral(l.l_price, XSDDatatype.XSDdecimal) ;
    	Resource livre = m.createResource(ns_livres+l.ISBN);
    	livre.addLiteral(DC.date, l_year);
    	livre.addLiteral(PublicationPrice, l_price);
    	m.add(livre,DC.title,l_title);
    	m.add(livre,RDF.type,Book);
        if(l.subject!=null){
            Resource infoConcept = m.createResource(l.subject);
            m.add(livre,DC.subject,infoConcept);
        }
        for (Auteur a : l.auteurs) {
        Resource auteur = m.createResource(ns_auteurs+a.nom+"_"+a.prenom);
    	auteur.addLiteral(FOAF.family_name, a.nom);
    	auteur.addLiteral(FOAF.firstName, a.prenom);
    	m.add(auteur,RDF.type,Author);
        m.add(livre ,DC.creator,auteur);
        }
    }
    public void add_livres(ArrayList<Livre> ls){
        for (Livre livre : ls) {
            this.add_livre(livre);
        }
    }
    public static void main(String[] args) {
       Livres livres = new Livres();
        
       Auteur a1= new Auteur("Ullman","Jeffrey");
       Auteur a2= new Auteur("Widom","Jennifer");       
       Auteur a3= new Auteur("Garcia-Molina", "Hector");
       Auteur a4= new Auteur("Celko","Joe");
       
       Livre l1 = new Livre("A First Course in Database Systems", 2002, 50,"0-13-861337-0",cs_ns+"DatabaseSystem");
       l1.add_auteur(a1);l1.add_auteur(a2);
       
       Livre l2 = new Livre("Database System Implementation",2000,60,"0-13-040264-8");
       l2.add_auteur(a3);l2.add_auteur(a1);l2.add_auteur(a2);
       
       Livre l3 = new Livre("SQL for Smarties, Fourth Edition, Advanced SQL Programming", 2005, 21.48,"0-12-369379-9",cs_ns+"Relational");
       l3.add_auteur(a4);
       
       Livre l4 = new Livre("Trees and Hierarchies in SQL for Smarties", 2004, 21.48,"1-55-860920-2");
       l4.add_auteur(a4);
       
       Livre l5 = new Livre("Thinking in Sets: Auxiliary, Temporal, and Virtual Tables in SQL", 2008, 21.48,"9-78-012374137-0");
       l5.add_auteur(a4);
       
       livres.add_livre(l1);
       livres.add_livre(l2);
       livres.add_livre(l3);
       livres.add_livre(l4);
       
       //livres étiquetés avec des individus typé avec les conceptes SKOS et non pas les conceptes SKOS directement

       Livre l6 = new Livre("Database book", 2013, 200, "book6",cs_ns+"i_databaseSystem");
       Livre l7 = new Livre("Relational book", 2013, 200, "book7",cs_ns+"i_relational");
       Livre l8 = new Livre("NoSql book", 2013, 200, "book8",cs_ns+"i_NoSql");
       
       livres.add_livre(l8);       livres.add_livre(l7);       livres.add_livre(l6);
       
       livres.m.write(System.out,"N3");
       
        try {       
		    	 // FileOutputStream outStream = new FileOutputStream("out/livres.rdf");
		             // exporte le resultat dans un fichier
		         //     livres.m.write(outStream, "RDF/XML");
		         //    outStream.close();
		           FileOutputStream outStream1 = new FileOutputStream("out/livres.n3");
		              livres.m.write(outStream1, "N3");
		             outStream1.close();
	    	 }
		      catch (FileNotFoundException e) {System.out.println("File not found");}
		      catch (IOException e) {System.out.println("IO problem");}
			 
    }
}
