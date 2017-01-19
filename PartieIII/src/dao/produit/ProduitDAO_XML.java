package dao.produit;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;

public class ProduitDAO_XML {
	private String uri = "Catalogues.xml";
	private static Document doc;
	private static String nomCatalogueParent;

	public ProduitDAO_XML() {
		SAXBuilder sdoc = new SAXBuilder();
		try {
			doc = sdoc.build(uri);
		} catch (Exception e) {
			System.out.println("erreur construction arbre JDOM");
		}
	}

	public boolean creer(I_Produit p) {
		try {
			Element catal = chercheCatalogueParent();
			Element prod = new Element("produit");
			prod.setAttribute("nom", p.getNom());
			Element prix = new Element("prixHT");
			prod.addContent(prix.setText(String.valueOf(p.getPrixUnitaireHT())));
			Element qte = new Element("quantite");
			prod.addContent(qte.setText(String.valueOf(p.getQuantite())));
			catal.addContent(prod);
			return sauvegarde();
		} catch (Exception e) {
			System.out.println("erreur creer produit");
			e.printStackTrace();
			return false;
		}
	}

	public boolean maj(I_Produit p) {
		try {
			Element prod = chercheProduit(p.getNom());
			if (prod != null) {
				prod.getChild("prixHT").setText(String.valueOf(p.getPrixUnitaireHT()));
				prod.getChild("quantite").setText(String.valueOf(p.getQuantite()));
				return sauvegarde();
			}
			return false;
		} catch (Exception e) {
			System.out.println("erreur maj produit");
			return false;
		}
	}

	public boolean supprimer(I_Produit p) {
		try {
			Element prod = chercheProduit(p.getNom());
			if (prod != null) {
				chercheCatalogueParent().removeContent(prod);
				return sauvegarde();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("erreur supprimer produit");
			return false;
		}
	}

	public I_Produit lire(String nom) {
		Element e = chercheProduit(nom);
		if (e != null)
			return new Produit(e.getAttributeValue("nom"), Double.parseDouble(e.getChildText("prixHT")), Integer.parseInt(e.getChildText("quantite")));
		else
			return null;
	}

	public List<I_Produit> lireTous() {

		List<I_Produit> l = new ArrayList<I_Produit>();
		try {
			List<Element> lProd = chercheCatalogueParent().getChildren("produit");
			for (Element prod : lProd) {
				String nomP = prod.getAttributeValue("nom");
				Double prix = Double.parseDouble(prod.getChild("prixHT").getText());
				int qte = Integer.parseInt(prod.getChild("quantite").getText());
				l.add(new Produit(nomP, prix, qte));
			}
		} catch (Exception e) {
			System.out.println("erreur lireTous tous les produits");
		}
		return l;
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

	private Element chercheProduit(String nom) {
		int iProd = 0;
		List<Element> lProd = chercheCatalogueParent().getChildren("produit");
		while (iProd < lProd.size() && 
				!lProd.get(iProd).getAttributeValue("nom").equals(nom))
			iProd++;
		if (iProd < lProd.size())
			return lProd.get(iProd);
		else
			return null;
	}
	
	public void instaurerCatalogue(I_Catalogue catalog){
		nomCatalogueParent = catalog.getNom();
	}
	
	private Element chercheCatalogueParent() {
		Element root = doc.getRootElement();
		List<Element> lCatalogs = root.getChildren("catalogue");
		int iCatal = 0;
		while (iCatal < lCatalogs.size() && 
			!lCatalogs.get(iCatal).getAttributeValue("nom").equals(nomCatalogueParent))
			iCatal++;
		if (iCatal < lCatalogs.size())
			return lCatalogs.get(iCatal);
		return null;
	}
	
	public int compterLeNombreDeProduitsDuCatalogue(){
		return chercheCatalogueParent().getContentSize();
	}
}
