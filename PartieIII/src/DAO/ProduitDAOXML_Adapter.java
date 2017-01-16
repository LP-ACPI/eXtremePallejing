package DAO;

import java.util.List;

import Metier.I_Catalogue;
import Metier.I_Produit;

public class ProduitDAOXML_Adapter implements I_ProduitDAO {

	private static ProduitDAO_XML produitDAOXmlDOrigine;
	
	public ProduitDAOXML_Adapter() {
		super();
		produitDAOXmlDOrigine = new ProduitDAO_XML();
	}

	@Override
	public boolean create(I_Produit p) {
		return produitDAOXmlDOrigine.creer(p);
	}

	@Override
	public boolean update(I_Produit p) {
		return produitDAOXmlDOrigine.maj(p);
	}

	@Override
	public boolean delete(I_Produit p) {
		return produitDAOXmlDOrigine.supprimer(p);
	}

	@Override
	public I_Produit read(String nomProduit) {
		return produitDAOXmlDOrigine.lire(nomProduit);
	}

	@Override
	public List<I_Produit> readAll() {
		return produitDAOXmlDOrigine.lireTous();
	}

	@Override
	public void setCatalogue(I_Catalogue catalog) {
		produitDAOXmlDOrigine.instaurerCatalogue(catalog);		
	}

	@Override
	public int catalogsProductCount() {
		return produitDAOXmlDOrigine.compterLeNombreDeProduitsDuCatalogue();
	}

}
