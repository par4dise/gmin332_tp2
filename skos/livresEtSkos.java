//package vocabulary;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DC;

/* Exercice 3 : annotation sémantique du jeu de données */
public class livresEtSkos {
	// Namespaces
	public static final String nsB = "http://www.livres.fr#";
	public static final String nsA = "http://www.auteurs.fr#";
	public static final String skos_ns ="http://www.w3.org/2004/02/skos/core#";
	public static final String cs_ns ="http://computerscience#";

      public static void main( String[] args ) {
        
        Model m = ModelFactory.createDefaultModel();
    	// 2 NS différents 
		m.setNsPrefix("Livres", nsB);
		m.setNsPrefix("Auteurs", nsA);
		m.setNsPrefix("skos", skos_ns);
		m.setNsPrefix("cs", cs_ns);			
	
    	// predicats qui vont pointer sur des litteraux types
    	Property PublicationPrice = m.createProperty(nsB+"PublicationPrice");  
    	Property Remarks = m.createProperty(nsB+"Remarks"); 
    	
    	// ressource Livre
    	Resource Book = m.createResource(nsB+"Book");
    	   	
    	// Auteur 1 : Jeffrey Ullman
    	Resource a1 = m.createResource(nsA+"Jeffrey_Ullman");
    	a1.addLiteral(FOAF.family_name, "Ullman");
    	a1.addLiteral(FOAF.firstName, "Jeffrey");
    	m.add(a1, RDF.type, DC.creator);    	
    	// Auteur 2 : Jennifer Widom
    	Resource a2 = m.createResource(nsA+"Jennifer_Widom");
    	a2.addLiteral(FOAF.family_name, "Widom");
    	a2.addLiteral(FOAF.firstName, "Jennifer");
    	m.add(a2, RDF.type, DC.creator);    	
    	// Auteur 3 : Hector Garcia-Molina
    	Resource a3 = m.createResource(nsA+"Hector_Garcia-Molina");
    	a3.addLiteral(FOAF.family_name, "Garcia-Molina");
    	a3.addLiteral(FOAF.firstName, "Hector");
    	m.add(a3, RDF.type, DC.creator);    	
    	// Auteur 4 : Joe Celko
    	Resource a4 = m.createResource(nsA+"Hector_Garcia-Molina");
    	a4.addLiteral(FOAF.family_name, "Celko");
    	a4.addLiteral(FOAF.firstName, "Joe");
    	m.add(a4, RDF.type, DC.creator);    	
    	// Auteur 5 : Joseph Hellerstein
    	Resource a5 = m.createResource(nsA+"Joseph_Hellerstein");
    	a5.addLiteral(FOAF.family_name, "Hellerstein");
    	a5.addLiteral(FOAF.firstName, "Joseph");
    	m.add(a5, RDF.type, DC.creator);    	
    	// Auteur 6 : James Hamilton
    	Resource a6 = m.createResource(nsA+"James_Hamilton");
    	a6.addLiteral(FOAF.family_name, "Hamilton");
    	a6.addLiteral(FOAF.firstName, "James");
    	m.add(a6, RDF.type, DC.creator);    	
    	// Auteur 7 : Michael Stonebraker
    	Resource a7 = m.createResource(nsA+"Michael_Stonebraker");
    	a7.addLiteral(FOAF.family_name, "Stonebraker");
    	a7.addLiteral(FOAF.firstName, "Michael");
    	m.add(a7, RDF.type, DC.creator);    	
    	// Auteur 8 : Dorothy Moore
    	Resource a8 = m.createResource(nsA+"Dorothy_Moore");
    	a8.addLiteral(FOAF.family_name, "Moore");
    	a8.addLiteral(FOAF.firstName, "Dorothy");
    	m.add(a8, RDF.type, DC.creator);    	
    	// Auteur 9 : Pramod Sadalage
    	Resource a9 = m.createResource(nsA+"Pramod_Sadalage");
    	a9.addLiteral(FOAF.family_name, "Sadalage");
    	a9.addLiteral(FOAF.firstName, "Pramod");
    	m.add(a9, RDF.type, DC.creator);    	
    	// Auteur 10 : Martin Fowler
    	Resource a10 = m.createResource(nsA+"Martin_Fowler");
    	a10.addLiteral(FOAF.family_name, "Fowler");
    	a10.addLiteral(FOAF.firstName, "Martin");
    	m.add(a10, RDF.type, DC.creator);    	
    	// Auteur 11 : Bob Ducharme
    	Resource a11 = m.createResource(nsA+"Bob_Ducharme");
    	a11.addLiteral(FOAF.family_name, "Ducharme");
    	a11.addLiteral(FOAF.firstName, "Bob");
    	m.add(a11, RDF.type, DC.creator);
    	
    	// Sujets
		String lab0 ="ComputerScience";  	
    	Resource s1 = m.createResource(cs_ns+"ComputerScience");
    	
    	// Livre 1 : "http://www.livres.fr#0-13-861337-0"
    	Resource b1 = m.createResource(nsB+"0-13-861337-0");
    	b1.addLiteral(DC.date, m.createTypedLiteral("2002", XSDDatatype.XSDdecimal));
    	b1.addLiteral(PublicationPrice, m.createTypedLiteral("58", XSDDatatype.XSDdecimal));
    	m.add(b1, DC.title, m.createTypedLiteral("A First Course in Database Systems", XSDDatatype.XSDstring));
    	m.add(b1, RDF.type, Book);
    	m.add(b1, DC.creator, a1);
    	m.add(b1, DC.creator, a2);
    	m.add(b1, DC.subject, s1);
    	//b1.addProperty(DC.subject, m.createResource(cs_ns.ge))
    	// Lien du type :
    	/* cs1.addProperty(FOAF.maker, m.createResource(FOAF.getURI()+"Pierre_Dupond").addProperty(RDF.type, FOAF.Person).addLiteral(FOAF.name, "Dupont").addLiteral(FOAF.mbox,"dupont@lirmm.fr")); */
    	
    	// Livre 2 : "http://www.livres.fr#0-13-040264-8"
    	Literal l_title2 = m.createTypedLiteral("Database System Implementation", XSDDatatype.XSDstring) ;
    	Literal l_year2 = m.createTypedLiteral("2000", XSDDatatype.XSDdecimal) ;
    	Literal l_price2 = m.createTypedLiteral("60", XSDDatatype.XSDdecimal) ;
    	Literal l_remark2 = m.createTypedLiteral("Buy this book bundled with \"A First Course\", it's a great deal!", XSDDatatype.XSDstring) ;
    	Resource b2 = m.createResource(nsB+"0-13-040264-8");
    	b2.addLiteral(DC.date, l_year2);
    	b2.addLiteral(PublicationPrice, l_price2);
    	m.add(b2, DC.title, l_title2);
    	m.add(b2, RDF.type, Book);
    	m.add(b2, DC.creator, a1);
    	m.add(b2, DC.creator, a2);
    	m.add(b2, DC.creator, a3);
    	m.add(b2, Remarks, l_remark2);
    	
    	// Livre 3 : "http://www.livres.fr#0-12-369379-9"
    	Literal l_title3 = m.createTypedLiteral("SQL for Smarties, Fourth Edition, Advanced SQL Programming", XSDDatatype.XSDstring) ;
    	Literal l_year3 = m.createTypedLiteral("2005", XSDDatatype.XSDdecimal) ;
    	Literal l_price3 = m.createTypedLiteral("21.48", XSDDatatype.XSDdecimal) ;
    	Resource b3 = m.createResource(nsB+"0-12-369379-9");
    	b3.addLiteral(DC.date, l_year3);
    	b3.addLiteral(PublicationPrice, l_price3);
    	m.add(b3, DC.title, l_title3);
    	m.add(b3, RDF.type, Book);
    	m.add(b3, DC.creator, a4);
    	
    	// Livre 4 : "http://www.livres.fr#"
    	Literal l_title4 = m.createTypedLiteral("Architecture of a Database System", XSDDatatype.XSDstring) ;
    	Literal l_year4 = m.createTypedLiteral("2008", XSDDatatype.XSDdecimal) ;
    	//Literal l_price4 = m.createTypedLiteral("21.48", XSDDatatype.XSDdecimal) ;
    	Resource b4 = m.createResource(nsB+"<unknown>");
    	b4.addLiteral(DC.date, l_year4);
    	b4.addLiteral(PublicationPrice, l_price3);
    	m.add(b4, DC.title, l_title4);
    	m.add(b4, RDF.type, Book);
    	m.add(b4, DC.creator, a5);
    	m.add(b4, DC.creator, a6);
    	m.add(b4, DC.creator, a7);
    	
    	// Livre 5 : "http://www.livres.fr#1-55-8605231-I"
    	Literal l_title5 = m.createTypedLiteral("Object-Relational DBMSs: The Next Great Wave", XSDDatatype.XSDstring) ;
    	Literal l_year5 = m.createTypedLiteral("1998", XSDDatatype.XSDdecimal) ;
    	//Literal l_price5 = m.createTypedLiteral("21.48", XSDDatatype.XSDdecimal) ;
    	Resource b5 = m.createResource(nsB+"1-55-8605231-I");
    	b5.addLiteral(DC.date, l_year5);
    	b5.addLiteral(PublicationPrice, l_price3);
    	m.add(b5, DC.title, l_title5);
    	m.add(b5, RDF.type, Book);
    	m.add(b5, DC.creator, a7);
    	m.add(b5, DC.creator, a8);
    	
    	// Livre 6 : "http://www.livres.fr#9-78-032182662-6"
    	Literal l_title6 = m.createTypedLiteral("NoSQL Distilled: A Brief Guide to the Emerging World of Polyglot Persistence", XSDDatatype.XSDstring) ;
    	Literal l_year6 = m.createTypedLiteral("2012", XSDDatatype.XSDdecimal) ;
    	Literal l_price6 = m.createTypedLiteral("48", XSDDatatype.XSDdecimal) ;
    	Resource b6 = m.createResource(nsB+"9-78-032182662-6");
    	b6.addLiteral(DC.date, l_year6);
    	b6.addLiteral(PublicationPrice, l_price6);
    	m.add(b6, DC.title, l_title6);
    	m.add(b6, RDF.type, Book);
    	m.add(b6, DC.creator, a9);
    	m.add(b6, DC.creator, a10); 
    	
    	// Livre 7 : "http://www.livres.fr#978-1-4493-7146-3"
    	Literal l_title7 = m.createTypedLiteral("Learning SPARQL, 2nd Edition Querying and Updating with SPARQL 1.1", XSDDatatype.XSDstring) ;
    	Literal l_year7 = m.createTypedLiteral("2013", XSDDatatype.XSDdecimal) ;
    	Literal l_price7 = m.createTypedLiteral("41", XSDDatatype.XSDdecimal) ;
    	Resource b7 = m.createResource(nsB+"978-1-4493-7146-3");
    	b7.addLiteral(DC.date, l_year7);
    	b7.addLiteral(PublicationPrice, l_price7);
    	m.add(b7, DC.title, l_title7);
    	m.add(b7, RDF.type, Book);
    	m.add(b7, DC.creator, a11); 	
    	
    	try {
			FileOutputStream ost = new FileOutputStream("livres_et_skos.rdf");
			
			m.write(ost, "RDF/XML-ABBREV" );
			System.out.println("Exercice 3 : annotation sémantique du jeu de données");
	    	m.write(System.out, "N3"); 
	    	//m.write(System.out, "RDF/XML-ABBREV"); 
		} 
    	catch (FileNotFoundException e) {
			e.printStackTrace();
		}

    	
    	
       
}
}
