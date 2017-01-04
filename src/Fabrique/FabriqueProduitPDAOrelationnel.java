package Fabrique;

import java.sql.SQLException;

import DAO.I_ProduitDAO;
import DAO.ProduitDAOrelationnel;

public class FabriqueProduitPDAOrelationnel {
	
	private static FabriqueProduitPDAOrelationnel instance;
	
	protected FabriqueProduitPDAOrelationnel(){}
	
	public static I_ProduitDAO CreateProduitPDAO() {

		String driver 	= "oracle.jdbc.driver.OracleDriver";
		String url 		= "jdbc:oracle:thin:@162.38.222.149:1521:iut";
		String login 	= "necesanym";
		String mdp 		= "Neces#9A";
		
		try {
			return new ProduitDAOrelationnel(driver, url, login, mdp);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public synchronized static FabriqueProduitPDAOrelationnel getInstance(){
		if(instance == null){
			instance = new FabriqueProduitPDAOrelationnel();
		}
		return instance;
	}

}
