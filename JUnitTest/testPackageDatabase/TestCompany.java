package testPackageDatabase;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import database.*;

public class TestCompany {
	public Database database;
	
	@Before
	public void beforeTest(){
		String dbDriver = "com.mysql.jdbc.Driver";
		String username = "espenme";
		String password = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database(dbDriver, databasename);
	}
	
	@Test
	public void companyTest() throws Exception{

		String[][] companies = QMCustomer.viewAllCompanies(database);
		String[] company = QMCustomer.viewCompany(Integer.parseInt(companies[0][0]), database);
		
		for(int i=0;i<company.length;i++){
			assertEquals(company[i], companies[0][i]);
		}
		
		boolean exp = QMCustomer.updateCompany(Integer.parseInt(company[0]), "feil navn", database);
		assertEquals(exp, true);		
		
		exp = QMCustomer.removeCompany(Integer.parseInt(company[0]), database);
		assertEquals(exp, true);
		
		exp = QMCustomer.registerCompanyToCustomer(Integer.parseInt(company[0]), company[1], database);
		assertEquals(exp, true);		
	}
}
