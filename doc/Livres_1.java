package vocabulary;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

// Construction du dataset
public class Livres_1 {

      public static void main( String[] args ) {
        
        Model m = ModelFactory.createDefaultModel();
    	String ns = "http://www.livres.fr#";
		m.setNsPrefix("livres", ns);
	
    	// predicats qui vont pointer sur des litteraux types
    	Property PublicationDate = m.createProperty(ns+"PublicationDate");
    	Property PublicationTitle = m.createProperty(ns+"PublicationTitle");
    	Property PublicationPrice = m.createProperty(ns+"PublicationPrice");
    	
    	// predicats qui vont pointer sur d'autres ressources
    	Property PublicationAuthor = m.createProperty(ns+"PublicationAuthor");
    	
    	// ressource Livre
    	Resource Book = m.createResource(ns+"Book");
    	Resource Author = m.createResource(ns+"Author");
    	
    	m.add(PublicationAuthor, RDFS.domain, Author);
    	m.add(Author, RDF.type, FOAF.maker);
    	
    	// exemple de donnees

  
    	Literal l_title1 = m.createTypedLiteral("A First Course in Database Systems", XSDDatatype.XSDstring) ;
    	Literal l_year1 = m.createTypedLiteral("2002", XSDDatatype.XSDdecimal) ;
    	Literal l_price1 = m.createTypedLiteral("58", XSDDatatype.XSDdecimal) ;
    	Resource b1 = m.createResource(ns+"0-13-861337-0");
    	b1.addLiteral(PublicationDate, l_year1);
    	b1.addLiteral(PublicationPrice, l_price1);
    	m.add(b1,PublicationTitle,l_title1);
    	m.add(b1,RDF.type,Book);

    	Resource a1 = m.createResource(ns+"Jeffrey_Ullman");
    	a1.addLiteral(FOAF.family_name, "Ullman");
    	a1.addLiteral(FOAF.firstName, "Jeffrey");
    	m.add(a1,RDF.type,Author);
    	
    	Resource a2 = m.createResource(ns+"Jennifer_Widom");
    	a2.addLiteral(FOAF.family_name, "Widom");
    	a2.addLiteral(FOAF.firstName, "Jennifer");
    	m.add(a2,RDF.type,Author);
    	m.add(b1,PublicationAuthor,a1);
    	m.add(b1,PublicationAuthor,a2);
    	
    	m.write(System.out, "N3"); 
       
}
}