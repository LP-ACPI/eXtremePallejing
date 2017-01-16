package DAO;

import java.util.List;

import Metier.I_Catalogue;
import Metier.I_Produit;

public class ProduitDAOXML_Adapter implements I_ProduitDAO {

	private static ProduitDAO_XML produitDAOXmlOriginel;
	
	public ProduitDAOXML_Adapter() {
		super();
		produitDAOXmlOriginel = new ProduitDAO_XML();
	}

	@Override
	public boolean create(I_Produit p) {
		return produitDAOXmlOriginel.creer(p);
	}

	@Override
	public boolean update(I_Produit p) {
		return produitDAOXmlOriginel.maj(p);
	}

	@Override
	public boolean delete(I_Produit p) {
		return produitDAOXmlOriginel.supprimer(p);
	}

	@Override
	public I_Produit read(String nomProduit) {
		return produitDAOXmlOriginel.lire(nomProduit);
	}

	@Override
	public List<I_Produit> readAll() {
		return produitDAOXmlOriginel.lireTous();
	}


	@Override
	public void setCatalogue(I_Catalogue catalog) {
		produitDAOXmlOriginel.instaurerCatalogue(catalog);		
	}

	@Override
	public int catalogsProductCount() {
		return produitDAOXmlOriginel.compterLeNombreDeProduitsDuCatalogue();
	}

}
