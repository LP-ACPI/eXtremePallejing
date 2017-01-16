package DAO;

import java.util.List;

import Metier.I_Catalogue;

public interface I_CatalogueDAO {
	public abstract boolean create(I_Catalogue catalogue);

	public abstract boolean update(I_Catalogue catalogue);

	public abstract boolean delete(I_Catalogue catalogue);

	public abstract I_Catalogue read(String nomCatalogue);

	public abstract List<I_Catalogue> readAll();

}
