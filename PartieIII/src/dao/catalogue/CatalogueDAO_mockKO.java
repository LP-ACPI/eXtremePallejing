package dao.catalogue;

import java.util.List;

import dao.DAOException;
import metier.I_Catalogue;

public class CatalogueDAO_mockKO implements I_CatalogueDAO{

	@Override
	public boolean create(I_Catalogue catalogue) {
		return false;
	}

	@Override
	public boolean update(I_Catalogue catalogue) {
		return false;
	}

	@Override
	public boolean delete(I_Catalogue catalogue) {
		return false;
	}

	@Override
	public I_Catalogue read(String nomCatalogue) {
		return null;
	}

	@Override
	public List<I_Catalogue> readAll() {
		return null;
	}

	@Override
	public int getProductCount(I_Catalogue catalogue) throws DAOException {
		return 0;
	}

}

