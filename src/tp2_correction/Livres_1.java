package tp2_correction;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.DB;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

// Construction du dataset
public class Livres_1 {
	
	private static Resource addBook(Model m,String ns,Resource Book,
			Property PublicationDate,Property PublicationTitle,Property PublicationPrice,Property PublicationRemark,
			String title,String year,String price, String isbn, String remark){
    	Resource b = m.createResource(ns+isbn);
    	Literal l_title1 = m.createTypedLiteral(title, XSDDatatype.XSDstring) ;
    	if(remark != ""){
    		Literal l_remark1 = m.createTypedLiteral(remark, XSDDatatype.XSDstring) ;
    		b.addLiteral(PublicationRemark, l_remark1);
    	}
    	Literal l_year1 = m.createTypedLiteral(year, XSDDatatype.XSDdecimal) ;
    	Literal l_price1 = m.createTypedLiteral(price, XSDDatatype.XSDdecimal) ;
    	b.addLiteral(PublicationDate, l_year1);
    	b.addLiteral(PublicationPrice, l_price1);
    	m.add(b,PublicationTitle,l_title1);
    	m.add(b,RDF.type,Book);	
    	return b;
    	}
	
	public static void main( String[] args ) {
        
        Model m = ModelFactory.createDefaultModel();
    	String ns = "http://www.livres.fr#";
    	String nsAuthor = "http://www.auteur.fr#";
		m.setNsPrefix("livres", ns);
		m.setNsPrefix("author", nsAuthor);
		m.setNsPrefix("foaf", FOAF.getURI());
	
    	// predicats qui vont pointer sur des litteraux types
    	//Property PublicationDate = m.createProperty(ns+"PublicationDate");
		Property PublicationDate = DC.date;
    	//Property PublicationTitle = m.createProperty(ns+"PublicationTitle");
    	Property PublicationTitle = DC.title;
    	Property PublicationPrice = m.createProperty(ns+"PublicationPrice");
    	Property PublicationRemark = m.createProperty(ns+"PublicationRemark");
    	
    	// predicats qui vont pointer sur d'autres ressources
    	//Property PublicationAuthor = m.createProperty(nsAuthor+"PublicationAuthor");
    	Property PublicationAuthor = DC.creator;
    	// ressource Livre
    	Resource Book = m.createResource(ns+"Book");
    	Resource Author = m.createResource(ns+"Author");
    	
    	m.add(PublicationAuthor, RDFS.domain, Author);
    	m.add(Author, RDF.type, FOAF.maker);
    	
    	// exemple de donnees

//    	Literal l_title1 = m.createTypedLiteral("A First Course in Database Systems", XSDDatatype.XSDstring) ;
//    	Literal l_year1 = m.createTypedLiteral("2002", XSDDatatype.XSDdecimal) ;
//    	Literal l_price1 = m.createTypedLiteral("58", XSDDatatype.XSDdecimal) ;
//    	Resource b1 = m.createResource(ns+"0-13-861337-0");
//    	b1.addLiteral(PublicationDate, l_year1);
//    	b1.addLiteral(PublicationPrice, l_price1);
//    	m.add(b1,PublicationTitle,l_title1);
//    	m.add(b1,RDF.type,Book);
    	
    	Resource b1 = addBook(m,ns,Book,PublicationDate,PublicationTitle,PublicationPrice,PublicationRemark,
    			"A First Course in Database Systems","2002","58","0-13-861337-0","");
    	Resource b2 = addBook(m,ns,Book,PublicationDate,PublicationTitle,PublicationPrice,PublicationRemark,
    			"Database System Implementation","2000","60","0-13-040264-8","Buy this book bundled with \"A First Course\", it's a great deal!");
    	Resource b3 = addBook(m,ns,Book,PublicationDate,PublicationTitle,PublicationPrice,PublicationRemark,
    			"SQL for Smarties, Fourth Edition, Advanced SQL Programming","2005","21.48","0-12-369379-9","");
    	Resource b4 = addBook(m,ns,Book,PublicationDate,PublicationTitle,PublicationPrice,PublicationRemark,
    			"Trees and Hierarchies in SQL for Smarties","2004","21.48","1-55-860920-2","");
    	Resource b5 = addBook(m,ns,Book,PublicationDate,PublicationTitle,PublicationPrice,PublicationRemark,
    			"Thinking in Sets: Auxiliary, Temporal, and Virtual Tables in SQL","2008","21.48","9-78-012374137-0","");
    	Resource b6 = addBook(m,ns,Book,PublicationDate,PublicationTitle,PublicationPrice,PublicationRemark,
    			"Architecture of a Database System","2008","21.48","","");
    	Resource b7 = addBook(m,ns,Book,PublicationDate,PublicationTitle,PublicationPrice,PublicationRemark,
    			"NoSQL Distilled: A Brief Guide to the Emerging World of Polyglot Persistence","2012","48","9-78-032182662-6","");
    	Resource b8 = addBook(m,ns,Book,PublicationDate,PublicationTitle,PublicationPrice,PublicationRemark,
    			"Object-Relational DBMSs: The Next Great Wave","1998","21.48","1-55-8605231-I","");
    	Resource b9 = addBook(m,ns,Book,PublicationDate,PublicationTitle,PublicationPrice,PublicationRemark,
    			"Learning SPARQL, 2nd Edition Querying and Updating with SPARQL 1.1","2013","41","978-1-4493-7146-3","");
    	

    	Resource a1 = m.createResource(nsAuthor+"Jeffrey_Ullman");
    	a1.addLiteral(FOAF.family_name, "Ullman");
    	a1.addLiteral(FOAF.firstName, "Jeffrey");
    	m.add(a1,RDF.type,Author);
    	m.add(b1,PublicationAuthor,a1);
    	m.add(b2,PublicationAuthor,a1);

    	Resource a2 = m.createResource(nsAuthor+"Jennifer_Widom");
    	a2.addLiteral(FOAF.family_name, "Widom");
    	a2.addLiteral(FOAF.firstName, "Jennifer");
    	m.add(a2,RDF.type,Author);
    	m.add(b1,PublicationAuthor,a2);
    	m.add(b2,PublicationAuthor,a2);

    	Resource a3 = m.createResource(nsAuthor+"Hector_Garcia-Molina");
    	a1.addLiteral(FOAF.family_name, "Garcia-Molina");
    	a1.addLiteral(FOAF.firstName, "Hector");
    	m.add(a1,RDF.type,Author);
    	m.add(b2,PublicationAuthor,a3);

    	Resource a11 = m.createResource(nsAuthor+"Joe_Celko");
    	a1.addLiteral(FOAF.family_name, "Celko");
    	a1.addLiteral(FOAF.firstName, "Joe");
    	m.add(a1,RDF.type,Author);
    	m.add(b3,PublicationAuthor,a11);
    	m.add(b4,PublicationAuthor,a11);
    	m.add(b5,PublicationAuthor,a11);

    	Resource a4 = m.createResource(nsAuthor+"Joseph_Hellerstein");
    	a1.addLiteral(FOAF.family_name, "Hellerstein");
    	a1.addLiteral(FOAF.firstName, "Joseph");
    	m.add(a1,RDF.type,Author);
    	m.add(b6,PublicationAuthor,a4);

    	Resource a5 = m.createResource(nsAuthor+"James_Hamilton");
    	a1.addLiteral(FOAF.family_name, "Hamilton");
    	a1.addLiteral(FOAF.firstName, "James");
    	m.add(a1,RDF.type,Author);
    	m.add(b6,PublicationAuthor,a5);

    	Resource a6 = m.createResource(nsAuthor+"Michael_Stonebraker");
    	a1.addLiteral(FOAF.family_name, "Stonebraker");
    	a1.addLiteral(FOAF.firstName, "Michael");
    	m.add(a1,RDF.type,Author);
    	m.add(b6,PublicationAuthor,a6);
    	m.add(b7,PublicationAuthor,a6);

    	Resource a7 = m.createResource(nsAuthor+"dorothy_Moore");
    	a1.addLiteral(FOAF.family_name, "Moore");
    	a1.addLiteral(FOAF.firstName, "Dorothy");
    	m.add(a1,RDF.type,Author);
    	m.add(b7,PublicationAuthor,a7);
    	
    	Resource a8 = m.createResource(nsAuthor+"Pramod_Sadalage");
    	a1.addLiteral(FOAF.family_name, "Sadalage");
    	a1.addLiteral(FOAF.firstName, "Pramod");
    	m.add(a1,RDF.type,Author);
    	m.add(b8,PublicationAuthor,a8);

    	Resource a9 = m.createResource(nsAuthor+"Martin_Fowler");
    	a1.addLiteral(FOAF.family_name, "Fowler");
    	a1.addLiteral(FOAF.firstName, "Martin");
    	m.add(a1,RDF.type,Author);
    	m.add(b8,PublicationAuthor,a9);

    	Resource a10 = m.createResource(nsAuthor+"Bob_Ducharme");
    	a1.addLiteral(FOAF.family_name, "Ducharme");
    	a1.addLiteral(FOAF.firstName, "Bob");
    	m.add(a1,RDF.type,Author);
    	m.add(b9,PublicationAuthor,a10);

   	 try {       
   	  FileOutputStream outStream = new FileOutputStream("out/Livres.rdf");
            // exporte le resultat dans un fichier
            m.write(outStream, "RDF/XML");
            outStream.close();
            
          FileOutputStream outStream1 = new FileOutputStream("out/Livres.n3");
            m.write(outStream1, "N3");
            outStream1.close();
	 }
     catch (FileNotFoundException e) {System.out.println("File not found");}
     catch (IOException e) {System.out.println("IO problem");}
       
}
}