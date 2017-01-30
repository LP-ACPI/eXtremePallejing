package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import dao.DAOException;
import dao.fabrique.FabriqueAbstraiteDAO;
import dao.fabrique.FabriqueDAO_mockOK;
import metier.Catalogue;

public class CatalogueTestMockOK extends AbstractCatalogueTest{

	@BeforeClass
	public static void preSetUp(){
		FabriqueAbstraiteDAO.setInstance(new FabriqueDAO_mockOK());
	}
	
	@Before
	public void setUp() throws DAOException {
		cat = new Catalogue();
	}
	
	@After
	public void cleanUp(){}

    @AfterClass
	public static void postCleanUp(){}
}
