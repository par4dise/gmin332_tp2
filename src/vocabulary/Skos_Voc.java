package vocabulary;


import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;

public class Skos_Voc {

	protected static final String uri ="http://www.w3.org/2004/02/skos/core#";
	
	public static String getUri()
		{return uri;}

	// PropertyImpl = implementation of Property. 
	// http://www.cs.helsinki.fi/group/wwwmuseo/jena-doc/com/hp/hpl/mesa/rdf/jena/common/PropertyImpl.html
	public static Property getCProperty(String pName)
		{return new PropertyImpl(getUri(), pName); }
	
	public static Resource getCResource(String pName)
		{return new ResourceImpl(getUri(), pName); }
	
	// Properties
	public static Property altLabel = null;
	public static Property prefLabel = null;
	public static Property hiddenLabel = null;
	public static Property notation = null;
	public static Property broader = null;
	public static Property narrower = null;
	public static Property definition = null;
	public static Property note = null;
	public static Property related = null;
	
	// Ressources
	// Exemplification PropertyValuePair du DCAM
	public static Resource Concept = null;
	public static Resource ConceptScheme = null;
	public static Resource Collection = null;
	
	static {
		try { 
			// Properties
			altLabel = getCProperty("altLabel");
			prefLabel = getCProperty("preLabel");
			hiddenLabel = getCProperty("hiddenLabel");
			notation = getCProperty("notation");
			broader = getCProperty("broader");
			narrower = getCProperty("narrower");
			definition = getCProperty("definition");
			note = getCProperty("note");
		    related = getCProperty("related");
			
			
			// Resources
			Concept = getCResource("Concept");
			ConceptScheme = getCResource("ConceptScheme");
			Collection = getCResource("Unit");

		}
		catch (Exception e)
		{System.out.println("failure"+e);}
	} 
	
}