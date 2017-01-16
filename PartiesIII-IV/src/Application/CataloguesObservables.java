package Application;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Metier.I_Catalogue;

public class CataloguesObservables {

	private List<DetailCatalogue> catalogues = new ArrayList<DetailCatalogue>();
	private List<ObserverCatalogues> observateurs = new ArrayList<ObserverCatalogues>();
	
	public CataloguesObservables(List<I_Catalogue> catalogues) {
		for(I_Catalogue c : catalogues)
			this.catalogues.add(new DetailCatalogue(c.getNom(),
									Array.getLength(c.getNomProduits())));
	}
	
	public CataloguesObservables(String[] nomsCatalogue, int[] nombresProduit) {
		for(int i=0; i< Array.getLength(nombresProduit); i++){
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

	public void attacher(ObserverCatalogues observateur){
		observateurs.add(observateur);
	}
	
	public void avertir(){
		for(ObserverCatalogues o : observateurs)
			o.mettreAJour(this);
	}
	
	private class DetailCatalogue {
		private String nomCatalogue;
		private int NombreProduits;
		
		public DetailCatalogue(String nomCatalogue, int nombreProduits) {
			this.nomCatalogue = nomCatalogue;
			NombreProduits = nombreProduits;
		}
		public int getNombreProduits() {
			return NombreProduits;
		}
		public String getNomCatalogue() {
			return nomCatalogue;
		}
	}
}
