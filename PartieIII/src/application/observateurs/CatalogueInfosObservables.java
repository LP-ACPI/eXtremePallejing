package application.observateurs;

import java.util.ArrayList;
import java.util.List;

import metier.I_Catalogue;

public class CatalogueInfosObservables{

	private List<DetailCatalogue>    catalogues   = new ArrayList<DetailCatalogue>();
	private List<ObserverInfosCatalogues> observators = new ArrayList<ObserverInfosCatalogues>();
	
	public CatalogueInfosObservables(List<I_Catalogue> catalogues) {
		for(I_Catalogue c : catalogues)
			this.catalogues.add(new DetailCatalogue(c.getNom(),c.getNomProduits().length));
	}
	
	public CatalogueInfosObservables(String[] nomsCatalogue, int[] nombresProduit) {
		for(int i=0; i< nombresProduit.length; i++){
			this.catalogues.add(new DetailCatalogue(nomsCatalogue[i],nombresProduit[i]));
		}
			
	}
	
	public String[] getNomsCatalogues(){
		String[] nomsCatalogues = new String[getNombreCatalogues()];
		for(int i=0;i<catalogues.size();i++)
			nomsCatalogues[i] = catalogues.get(i).getNomCatalogue();
		return nomsCatalogues;		
	}
	
	public String[] getNombresProduitsCatalogues(){
		String[] nombresProduitsCatalogues = new String[getNombreCatalogues()];
		for(int i=0;i<catalogues.size();i++)
			nombresProduitsCatalogues[i] = String.valueOf(catalogues.get(i).getNombreProduits());
		return nombresProduitsCatalogues;		
	}
	
	public int getNombreCatalogues(){
		return catalogues.size();
	}

	public void ajouterCatalogue(String nomCatalogue){
		catalogues.add(new DetailCatalogue(nomCatalogue,0));
		avertir();
	}
	
	public void supprimerCatalogue(String nomCatalogue){
		for(DetailCatalogue dc : catalogues)
			if(dc.getNomCatalogue().equals(nomCatalogue)){
				catalogues.remove(dc);
				break;
			}
		avertir();
	}
	
	public String[] getDetailsCatalogues(){
		int nombreDeCatalogues     = getNombreCatalogues();
		String[] detailsCatalogues = new String[nombreDeCatalogues];
		String[] nomsCatalogues    = getNomsCatalogues();
		String[] nombresDeProduitParCatalogue = getNombresProduitsCatalogues();
		
		for(int i = 0; i<nombreDeCatalogues; i++)
			detailsCatalogues[i] = nomsCatalogues[i] + " : " + nombresDeProduitParCatalogue[i] + " produits";
		
		return detailsCatalogues;		
	}
	

	public void attacher(ObserverInfosCatalogues observator) {
		observators.add(observator);		
	}
	
	public void avertir(){
		for(ObserverInfosCatalogues o : observators)
			o.mettreAJour(this);
	}
	
	private class DetailCatalogue {
		private String nomCatalogue;
		private int nombreProduits;
		
		public DetailCatalogue(String nomCatalogue, int nombreProduits) {
			this.nomCatalogue = nomCatalogue;
			this.nombreProduits = nombreProduits;
		}
		public int getNombreProduits() {
			return nombreProduits;
		}
		public String getNomCatalogue() {
			return nomCatalogue;
		}
	}
}
