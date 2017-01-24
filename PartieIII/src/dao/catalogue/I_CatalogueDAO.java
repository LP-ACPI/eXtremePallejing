package dao.catalogue;

import java.util.List;

import dao.DAOException;
import metier.I_Catalogue;

public interface I_CatalogueDAO {
	public abstract boolean create(I_Catalogue catalogue) throws DAOException;

	public abstract boolean update(I_Catalogue catalogue) throws DAOException;

	public abstract boolean delete(I_Catalogue catalogue) throws DAOException;

	public abstract I_Catalogue read(String nomCatalogue) throws DAOException;

	public abstract List<I_Catalogue> readAll() throws DAOException;

}
