package DAO;

import java.util.List;

import Metier.I_Catalogue;
import Metier.I_Produit;

public class ProduitDAOXML_Adapter implements I_ProduitDAO {

	private static ProduitDAO_XML produitXMLDAO_orig;
	
	public ProduitDAOXML_Adapter() {
		super();
		produitXMLDAO_orig = new ProduitDAO_XML();
	}

	@Override
	public boolean create(I_Produit p) {
		return produitXMLDAO_orig.creer(p);
	}

	@Override
	public boolean update(I_Produit p) {
		return produitXMLDAO_orig.maj(p);
	}

	@Override
	public boolean delete(I_Produit p) {
		return produitXMLDAO_orig.supprimer(p);
	}

	@Override
	public I_Produit read(String nomProduit) {
		return produitXMLDAO_orig.lire(nomProduit);
	}

	@Override
	public List<I_Produit> readAll() {
		return produitXMLDAO_orig.lireTous();
	}


	@Override
	public void setCatalogue(I_Catalogue catalog) {
		produitXMLDAO_orig.instaurerCatalogue(catalog);		
	}

}
