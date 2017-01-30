package Application;
import Metier.I_Catalogue;
import Metier.I_Produit;
public class ControleurProduits {

	private static I_Catalogue produits;

	public ControleurProduits(I_Catalogue prods) {
		produits = prods;
	}

	public boolean ajouterProduit(I_Produit produit){
		return produits.addProduit(produit);
	}
	
	public boolean enleverProduit(String nomProduit){
		return produits.removeProduit(nomProduit);
	}
	
	public static String[] listeNomsProduits(){
		return produits.getNomProduits();
	}

	public static I_Catalogue getProduits() {
		return produits;
	}

	public static void setProduits(I_Catalogue produits) {
		ControleurProduits.produits = produits;
	}
}
