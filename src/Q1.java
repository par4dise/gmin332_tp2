//package vocabulary;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

// Construction du dataset
public class Q1 {

      public static void main( String[] args ) {
        
        Model m = ModelFactory.createDefaultModel();
    	String ns = "http://www.livres.fr#";
		m.setNsPrefix("livres", ns);
	
    	// predicats qui vont pointer sur des litteraux types
    	Property PublicationDate = m.createProperty(ns+"PublicationDate");
    	Property PublicationTitle = m.createProperty(ns+"PublicationTitle");
    	Property PublicationPrice = m.createProperty(ns+"PublicationPrice");  
    	Property Remarks = m.createProperty(ns+"Remarks");    	
    	// predicats qui vont pointer sur d'autres ressources
    	Property PublicationAuthor = m.createProperty(ns+"PublicationAuthor");
    	
    	// ressource Livre
    	Resource Book = m.createResource(ns+"Book");
    	Resource Author = m.createResource(ns+"Author");
    	
    	// Relation "auteur"
    	m.add(PublicationAuthor, RDFS.domain, Author);
    	m.add(Author, RDF.type, FOAF.maker);    	
    	   	
    	// Auteur 1 : Jeffrey Ullman
    	Resource a1 = m.createResource(ns+"Jeffrey_Ullman");
    	a1.addLiteral(FOAF.family_name, "Ullman");
    	a1.addLiteral(FOAF.firstName, "Jeffrey");
    	m.add(a1, RDF.type, Author);
    	
    	// Auteur 2 : Jennifer Widom
    	Resource a2 = m.createResource(ns+"Jennifer_Widom");
    	a2.addLiteral(FOAF.family_name, "Widom");
    	a2.addLiteral(FOAF.firstName, "Jennifer");
    	m.add(a2, RDF.type, Author);
    	
    	// Auteur 3 : Hector Garcia-Molina
    	Resource a3 = m.createResource(ns+"Hector Garcia-Molina");
    	a3.addLiteral(FOAF.family_name, "Garcia-Molina");
    	a3.addLiteral(FOAF.firstName, "Hector");
    	m.add(a3, RDF.type, Author);
    	
    	// Auteur 4 : Joe Celko
    	Resource a4 = m.createResource(ns+"Hector Garcia-Molina");
    	a4.addLiteral(FOAF.family_name, "Celko");
    	a4.addLiteral(FOAF.firstName, "Joe");
    	m.add(a4, RDF.type, Author);
    	
    	// Auteur 5 : Joseph Hellerstein
    	Resource a5 = m.createResource(ns+"Joseph Hellerstein");
    	a5.addLiteral(FOAF.family_name, "Hellerstein");
    	a5.addLiteral(FOAF.firstName, "Joseph");
    	m.add(a5, RDF.type, Author);
    	
    	// Auteur 6 : James Hamilton
    	Resource a6 = m.createResource(ns+"James Hamilton");
    	a6.addLiteral(FOAF.family_name, "Hamilton");
    	a6.addLiteral(FOAF.firstName, "James");
    	m.add(a6, RDF.type, Author);
    	
    	// Auteur 7 : Michael Stonebraker
    	Resource a7 = m.createResource(ns+"Michael Stonebraker");
    	a7.addLiteral(FOAF.family_name, "Stonebraker");
    	a7.addLiteral(FOAF.firstName, "Michael");
    	m.add(a7, RDF.type, Author);
    	
    	// Auteur 8 : Dorothy Moore
    	Resource a8 = m.createResource(ns+"Dorothy Moore");
    	a8.addLiteral(FOAF.family_name, "Moore");
    	a8.addLiteral(FOAF.firstName, "Dorothy");
    	m.add(a8, RDF.type, Author);
    	
    	// Auteur 9 : Pramod Sadalage
    	Resource a9 = m.createResource(ns+"Pramod Sadalage");
    	a9.addLiteral(FOAF.family_name, "Sadalage");
    	a9.addLiteral(FOAF.firstName, "Pramod");
    	m.add(a9, RDF.type, Author);
    	
    	// Auteur 10 : Martin Fowler
    	Resource a10 = m.createResource(ns+"Martin Fowler");
    	a10.addLiteral(FOAF.family_name, "Fowler");
    	a10.addLiteral(FOAF.firstName, "Martin");
    	m.add(a10, RDF.type, Author);
    	
    	// Auteur 11 : Bob Ducharme
    	Resource a11 = m.createResource(ns+"Bob Ducharme");
    	a11.addLiteral(FOAF.family_name, "Ducharme");
    	a11.addLiteral(FOAF.firstName, "Bob");
    	m.add(a11, RDF.type, Author);
    	
    	// Livre 1 : "http://www.livres.fr#0-13-861337-0"
    	Literal l_title1 = m.createTypedLiteral("A First Course in Database Systems", XSDDatatype.XSDstring) ;
    	Literal l_year1 = m.createTypedLiteral("2002", XSDDatatype.XSDdecimal) ;
    	Literal l_price1 = m.createTypedLiteral("58", XSDDatatype.XSDdecimal) ;
    	Resource b1 = m.createResource(ns+"0-13-861337-0");
    	b1.addLiteral(PublicationDate, l_year1);
    	b1.addLiteral(PublicationPrice, l_price1);
    	m.add(b1, PublicationTitle, l_title1);
    	m.add(b1, RDF.type, Book);
    	m.add(b1, PublicationAuthor, a1);
    	m.add(b1, PublicationAuthor, a2);
    	
    	// Livre 2 : "http://www.livres.fr#0-13-040264-8"
    	Literal l_title2 = m.createTypedLiteral("Database System Implementation", XSDDatatype.XSDstring) ;
    	Literal l_year2 = m.createTypedLiteral("2000", XSDDatatype.XSDdecimal) ;
    	Literal l_price2 = m.createTypedLiteral("60", XSDDatatype.XSDdecimal) ;
    	Literal l_remark2 = m.createTypedLiteral("Buy this book bundled with \"A First Course\", it's a great deal!", XSDDatatype.XSDstring) ;
    	Resource b2 = m.createResource(ns+"0-13-040264-8");
    	b2.addLiteral(PublicationDate, l_year2);
    	b2.addLiteral(PublicationPrice, l_price2);
    	m.add(b2, PublicationTitle, l_title2);
    	m.add(b2, RDF.type, Book);
    	m.add(b2, PublicationAuthor, a1);
    	m.add(b2, PublicationAuthor, a2);
    	m.add(b2, PublicationAuthor, a3);
    	m.add(b2, Remarks, l_remark2);
    	
    	// Livre 3 : "http://www.livres.fr#0-12-369379-9"
    	Literal l_title3 = m.createTypedLiteral("SQL for Smarties, Fourth Edition, Advanced SQL Programming", XSDDatatype.XSDstring) ;
    	Literal l_year3 = m.createTypedLiteral("2005", XSDDatatype.XSDdecimal) ;
    	Literal l_price3 = m.createTypedLiteral("21.48", XSDDatatype.XSDdecimal) ;
    	Resource b3 = m.createResource(ns+"0-12-369379-9");
    	b3.addLiteral(PublicationDate, l_year3);
    	b3.addLiteral(PublicationPrice, l_price3);
    	m.add(b3, PublicationTitle, l_title3);
    	m.add(b3, RDF.type, Book);
    	m.add(b3, PublicationAuthor, a4);
    	
    	// Livre 4 : "http://www.livres.fr#"
    	Literal l_title4 = m.createTypedLiteral("Architecture of a Database System", XSDDatatype.XSDstring) ;
    	Literal l_year4 = m.createTypedLiteral("2008", XSDDatatype.XSDdecimal) ;
    	//Literal l_price4 = m.createTypedLiteral("21.48", XSDDatatype.XSDdecimal) ;
    	Resource b4 = m.createResource(ns+"<unknown>");
    	b4.addLiteral(PublicationDate, l_year4);
    	b4.addLiteral(PublicationPrice, l_price3);
    	m.add(b4, PublicationTitle, l_title4);
    	m.add(b4, RDF.type, Book);
    	m.add(b4, PublicationAuthor, a5);
    	m.add(b4, PublicationAuthor, a6);
    	m.add(b4, PublicationAuthor, a7);
    	
    	// Livre 5 : "http://www.livres.fr#1-55-8605231-I"
    	Literal l_title5 = m.createTypedLiteral("Object-Relational DBMSs: The Next Great Wave", XSDDatatype.XSDstring) ;
    	Literal l_year5 = m.createTypedLiteral("1998", XSDDatatype.XSDdecimal) ;
    	//Literal l_price5 = m.createTypedLiteral("21.48", XSDDatatype.XSDdecimal) ;
    	Resource b5 = m.createResource(ns+"1-55-8605231-I");
    	b5.addLiteral(PublicationDate, l_year5);
    	b5.addLiteral(PublicationPrice, l_price3);
    	m.add(b5, PublicationTitle, l_title5);
    	m.add(b5, RDF.type, Book);
    	m.add(b5, PublicationAuthor, a7);
    	m.add(b5, PublicationAuthor, a8);
    	
    	// Livre 6 : "http://www.livres.fr#9-78-032182662-6"
    	Literal l_title6 = m.createTypedLiteral("NoSQL Distilled: A Brief Guide to the Emerging World of Polyglot Persistence", XSDDatatype.XSDstring) ;
    	Literal l_year6 = m.createTypedLiteral("2012", XSDDatatype.XSDdecimal) ;
    	Literal l_price6 = m.createTypedLiteral("48", XSDDatatype.XSDdecimal) ;
    	Resource b6 = m.createResource(ns+"9-78-032182662-6");
    	b6.addLiteral(PublicationDate, l_year6);
    	b6.addLiteral(PublicationPrice, l_price6);
    	m.add(b6, PublicationTitle, l_title6);
    	m.add(b6, RDF.type, Book);
    	m.add(b6, PublicationAuthor, a9);
    	m.add(b6, PublicationAuthor, a10); 
    	
    	// Livre 7 : "http://www.livres.fr#978-1-4493-7146-3"
    	Literal l_title7 = m.createTypedLiteral("Learning SPARQL, 2nd Edition Querying and Updating with SPARQL 1.1", XSDDatatype.XSDstring) ;
    	Literal l_year7 = m.createTypedLiteral("2013", XSDDatatype.XSDdecimal) ;
    	Literal l_price7 = m.createTypedLiteral("41", XSDDatatype.XSDdecimal) ;
    	Resource b7 = m.createResource(ns+"978-1-4493-7146-3");
    	b7.addLiteral(PublicationDate, l_year7);
    	b7.addLiteral(PublicationPrice, l_price7);
    	m.add(b7, PublicationTitle, l_title7);
    	m.add(b7, RDF.type, Book);
    	m.add(b7, PublicationAuthor, a11); 	

    	m.write(System.out, "N3"); 
    	//m.write(System.out, "RDF/XML-ABBREV"); 
    	
       
}
}