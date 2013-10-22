package tp2_correction;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import java.util.ArrayList;
import java.util.List;
import vocabulary.Skos_Voc;

public class ModelManip {

    public static final String NL = System.getProperty("line.separator");
    public static final String info = "info.rdf";
    public static final String livres = "livres.rdf";

    public static void main(String[] args) {

        String ns_livres = "http://www.livres.fr#";
        String ns_auteurs = "http://www.auteurs.fr#";
        String cs_ns = "http://www.computerscience.fr/skos#";

        Model m = ModelFactory.createDefaultModel();
        m.setNsPrefix("livres", ns_livres);
        m.setNsPrefix("auteurs", ns_auteurs);
        m.setNsPrefix("info", cs_ns);
        m.setNsPrefix("foaf", FOAF.getURI());
        m.setNsPrefix("dc", DC.getURI());

        FileManager.get().readModel(m, info);
        FileManager.get().readModel(m, livres);

        //m.write(System.out,"N3");
        // Toutes les ressouces de type Livre 
        Resource livre = m.createResource(ns_livres + "Book");
        ResIterator res_livre = m.listSubjectsWithProperty(RDF.type, livre);
        System.out.println("les ressources de type Livre : ");
        while (res_livre.hasNext()) {
            Resource l = res_livre.nextResource();
            System.out.println("URI du livre : " + l.toString());
        }
        // Toutes les ressouces de type Livre avec une étiquette relational
        ResIterator res_relational = m.listSubjectsWithProperty(Skos_Voc.prefLabel, "Relational Database");
        System.out.println("Toutes les ressouces de type Livre avec une étiquette 'relational'");
        while (res_relational.hasNext()) {
            Resource c = res_relational.nextResource();
            ResIterator res_l = m.listResourcesWithProperty(DC.subject, c);
            while (res_l.hasNext()) {
                Resource l = res_l.nextResource();
                if (m.contains(l, RDF.type, livre)) {
                    System.out.println(l.toString());
                }
            }
        }
        // Toutes les ressouces de type Livre qui ont pour sujet DatabaseSystem
        Resource database_concept = m.getResource(cs_ns + "DatabaseSystem");
        System.out.println("Toutes les ressouces de type Livre qui ont pour sujet 'DatabaseSystem'");
        ResIterator res_database = m.listSubjectsWithProperty(DC.subject, database_concept);
        while (res_database.hasNext()) {
            Resource l = res_database.nextResource();
            if (m.contains(l, RDF.type, livre)) {
                System.out.println(l.toString());
            }
        }

        System.out.println("--------------------------------------------");
        System.out.println("Le raisonnement sur le modele rdfs permet seulement 3 mecanismes d'interpretation");
        System.out.println("	-sur les sous-proprietes");
        System.out.println("	-sur les individus attachés à une sous-classe");
        System.out.println("	-sur les domaines de définition et image pour \"realiser\" un individu");
        System.out.println("ICI on fait jouer la transivité sur les sous-classes de concepts à partir d'une sous-proprieté");
        System.out.println("Ensuite, on explore ces concepts avec une arrayList");
        System.out.println("Autre moyen : Passer à un modele plus expressif = OWL!");

        m.add(Skos_Voc.broader, RDFS.subPropertyOf, RDFS.subClassOf);
	// Création modèle implicite
        InfModel inf_m = ModelFactory.createRDFSModel(m);
	// Ajout de déclas par le raisonneur RDFS
        System.out.println("--------------------------------------------");
        System.out.println("Les livres traitant de " + database_concept.getLocalName());
        // la transivité ne s'applique pas skosBroader -> necessite le passage à owl
        List<Resource> list_SKOSconcepts = new ArrayList<Resource>();
	// "database_concept" = concept sur lequel on veut interroger
        ResIterator res_s = inf_m.listSubjectsWithProperty(RDFS.subClassOf, database_concept);
        while (res_s.hasNext()) {
            Resource cpt = res_s.nextResource();
            list_SKOSconcepts.add(cpt);
        }
        for (Resource SKOSconcept : list_SKOSconcepts) {
            ResIterator res_d = inf_m.listSubjectsWithProperty(DC.subject, SKOSconcept);
            while (res_d.hasNext()) {
                Resource cpt = res_d.nextResource();
                System.out.println("- " + cpt.toString());
            }
        }
        // ICI on fait jouer la transivité sur les sous-classes de concepts à partir d'individus 
	// attaches a ces concepts
        System.out.println("--------------------------------------------");
        System.out.println("Une solution faisant jouée l'inférence sur les individus est aussi proposée :");
        System.out.println("");
        System.out.println("On a annoté en utilisant la propriété dc:subject les livres book 6, book 7 et book 8 respectivement avec les individus i_database, i_relational et i_NoSql");
        System.out.println("Les livres traitant de l'individu i_databaseSystem :");	 	    		 	 
        // Recherche des individus sur le concept parcouru, utilise l'inférence
        ResIterator res_inf_individus = inf_m.listSubjectsWithProperty(RDF.type, database_concept );
	    	while (res_inf_individus.hasNext())
	    	{ 
	    		 Resource cpt = res_inf_individus.nextResource();
	    		 ResIterator res_d = inf_m.listSubjectsWithProperty(DC.subject, cpt );
	 		    
	 	    	while (res_d.hasNext())	{ 
	 	    		 Resource livr = res_d.nextResource();
	 	    		 System.out.println("- "+livr.getLocalName());
	 	    	}
	    	}
    }
}
