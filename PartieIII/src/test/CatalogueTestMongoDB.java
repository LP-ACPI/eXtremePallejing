package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import application.ControleurAccueil;
import dao.DAOException;
import dao.fabrique.FabriqueAbstraiteDAO;
import dao.fabrique.FabriqueDAOMongoDB;
import metier.Catalogue;

public class CatalogueTestMongoDB extends AbstractCatalogueTest {

	@BeforeClass
	public static void preSetUp() throws DAOException{
		FabriqueAbstraiteDAO.setInstance(new FabriqueDAOMongoDB());
	}
	
	@Before
	public void setUp() throws DAOException {
		cat = new Catalogue("test");
		cat.persist();
	}
	
	@After
	public void cleanUp() throws DAOException{
		cat.clear();
	}

    @AfterClass
	public static void postCleanUp(){
    	ControleurAccueil.quit();
    }
}
