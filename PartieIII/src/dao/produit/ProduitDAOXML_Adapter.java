package dao.produit;

import java.util.List;

import dao.DAOException;
import metier.I_Catalogue;
import metier.I_Produit;

public class ProduitDAOXML_Adapter implements I_ProduitDAO {

	private static ProduitDAO_XML produitDAOXmlDOrigine;
	
	public ProduitDAOXML_Adapter() {
		super();
		produitDAOXmlDOrigine = new ProduitDAO_XML();
	}

	@Override
	public boolean create(I_Produit p) throws DAOException{
		if(produitDAOXmlDOrigine.creer(p))
			return true;
		else throw new DAOException("Erreur création produit");
	}

	@Override
	public boolean update(I_Produit p) throws DAOException {
		if(produitDAOXmlDOrigine.maj(p))
			return true;
		else throw new DAOException("Erreur mise à jour produit");
	}

	@Override
	public boolean delete(I_Produit p) throws DAOException {
		if(produitDAOXmlDOrigine.supprimer(p))
			return true;
		else throw new DAOException("Erreur suppression produit");
	}

	@Override
	public I_Produit read(String nomProduit) throws DAOException {
		I_Produit produit = produitDAOXmlDOrigine.lire(nomProduit);
		if(produit != null)
			return produit;
		else throw new DAOException("Erreur lecture produit");
	}

	@Override
	public List<I_Produit> readAll() throws DAOException {
		List<I_Produit> listProduits = produitDAOXmlDOrigine.lireTous();
		if(listProduits != null)
			return listProduits;
		else throw new DAOException("Erreur lister produits");
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
