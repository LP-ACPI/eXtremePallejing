package dao.catalogue;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import dao.DAOException;
import metier.Catalogue;
import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;

public class CatalogueDAOXML implements I_CatalogueDAO {

	private String uri = "Catalogues.xml";
	private Document doc;

	public CatalogueDAOXML() {
		SAXBuilder sdoc = new SAXBuilder();
		try {
			doc = sdoc.build(uri);
		} catch (Exception e) {
			System.out.println("erreur construction arbre JDOM");
		}
	}

	@Override
	public boolean create(I_Catalogue catalogue) {
		try {
			Element root = doc.getRootElement();
			Element catalog = new Element("catalogue");
			catalog.setAttribute("nom", catalogue.getNom());
			root.addContent(catalog);
			return sauvegarde();
		} catch (Exception e) {
			System.out.println("erreur creation produit");
			return false;
		}
	}


	@Override
	public boolean update(I_Catalogue catalogue) {
//		try {
//			Element catalog = chercheCatalogue(catalogue.getNom());
//			if (catalog != null) {
//				catalog.getChild("quantite").setText(String.valueOf(p.getQuantite()));
//				return sauvegarde();
//			}
//			return false;
//		} catch (Exception e) {
//			System.out.println("erreur maj produit");
//			return false;
//		}
		return sauvegarde();
	}

	@Override
	public boolean delete(I_Catalogue catalogue) {
		try {
			Element root = doc.getRootElement();
			Element catalog = chercheCatalogue(catalogue.getNom());
			if (catalog != null) {
				root.removeContent(catalog);
				return sauvegarde();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("erreur suppression Catalogue");
			return false;
		}
	}

	@Override
	public I_Catalogue read(String nomCatalogue) throws DAOException {
		Element e = chercheCatalogue(nomCatalogue);
		I_Catalogue catalogue = e == null ? null : new Catalogue(nomCatalogue);
		List<Element> lProduits = e.getChildren("produit");
		List<I_Produit> produits = new ArrayList<I_Produit>();
		for(Element eProd : lProduits){
			String nomP = eProd.getAttributeValue("nom");
			Double prix = Double.parseDouble(eProd.getChild("prixHT").getText());
			int qte = Integer.parseInt(eProd.getChild("quantite").getText());
			produits.add(new Produit(nomP,prix,qte));
		}
		catalogue.addProduits(produits);
		return catalogue;
	}

	@Override
	public List<I_Catalogue> readAll() {
		List<I_Catalogue> lcats = new ArrayList<I_Catalogue>();
		try {
			Element root = doc.getRootElement();
			List<Element> lCatalogs = root.getChildren("catalogue");

			for (Element catalog : lCatalogs) {
				String nomC = catalog.getAttributeValue("nom");
				lcats.add(new Catalogue(nomC));
			}
		} catch (Exception e) {
			System.out.println("erreur lireTous tous les produits");
		}
		return lcats;
	}
	
	@Override
	public int getProductCount(I_Catalogue catalogue){
		return chercheCatalogue(catalogue.getNom()).getContentSize();
	}


	private Element chercheCatalogue(String nom) {
		Element root = doc.getRootElement();
		List<Element> lCatalogs = root.getChildren("catalogue");
		int i = 0;
		while (i < lCatalogs.size() && !lCatalogs.get(i).getAttributeValue("nom").equals(nom))
			i++;
		if (i < lCatalogs.size())
			return lCatalogs.get(i);
		else
			return null;
	}
	
	
	
	private boolean sauvegarde() {
		System.out.println("Sauvegarde");
		XMLOutputter out = new XMLOutputter();
		try {
			out.output(doc, new PrintWriter(uri));
			return true;
		} catch (Exception e) {
			System.out.println("erreur sauvegarde dans fichier XML");
			return false;
		}
	}

}
