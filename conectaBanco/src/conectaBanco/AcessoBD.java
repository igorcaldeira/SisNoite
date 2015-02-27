package conectaBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AcessoBD {

	// Onde o banco est� localizado ?
	private String banco = "SisNoite";
	private String host = "BHD0000808";
	
	// Quem est� manuseando o banco ?
	private String usuario = "igor";
	private String senha = "123";
	
	// Informa��es referentes a biblioteca e a conex�o
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://" + host + ";DatabaseName=" + banco;
	
	/**
	 * 
	 * Conecta no banco (definido acima) e executa a query passada.
	 * No caso, este m�todo � usado em situa��es SEM RETORNO.
	 * Por exemplo: INSERT, UPDATE, DELETE e afins.
	 * 
	 * @param query a query que ser� executada no banco de dados
	 * @throws Exception pode retornar uma s�rie de erros do SQL
	 */
	public void aplicaQuerySemRetorno(String query) throws Exception 
	{

		try {
			
			//Verifica acesso a biblioteca SQL
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
			//Conectando ao banco de dados Sisnoite, com o login do igor e biblioteca do sqlserver
			Connection conecta = DriverManager.getConnection("jdbc:sqlserver://BHD0000808;DatabaseName=SisNoite", "igor", "123"); 
			
			//Cria objeto que faz a manipula��o de queryes no banco
			Statement stm = conecta.createStatement();
			
			//Executa uma query (a que recebemos como parametro) 
			//no banco de dados onde a conex�o foi feita
			stm.executeUpdate(query);
			
			//Fecha a conex�o com o banco
			conecta.close();

	
		} 
		//Qualquer exce��o, � jogada para quem chamou o
		//m�todo, de forma que a mesma seja tratada corretamente
		catch (Exception e) {

			throw e;
		}
	}
	

	/**
	 * 
	 * Conecta no banco (definido acima) e executa a query passada.
	 * No caso, este m�todo � usado em situa��es COM RETORNO.
	 * Por exemplo: SELECT.
	 * 
	 * @param query a query que ser� executada no banco de dados
	 * @throws Exception pode retornar uma s�rie de erros do SQL
	 */
	public ResultSet aplicaQueryComRetorno(String query) throws Exception 
	{

		ResultSet rs = null;
		
		try {
			
			//Verifica acesso a biblioteca SQL
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
			//Conectando ao banco de dados Sisnoite, com o login do igor e biblioteca do sqlserver
			Connection conecta = DriverManager.getConnection("jdbc:sqlserver://BHD0000808;DatabaseName=SisNoite", "igor", "123"); 
			
			//Cria objeto que faz a manipula��o de queryes no banco
			Statement stm = conecta.createStatement();
			
			//Executa uma query (a que recebemos como parametro) 
			//no banco de dados onde a conex�o foi feita
			rs = stm.executeQuery(query);
			
		} 
		//Qualquer exce��o, � jogada para quem chamou o
		//m�todo, de forma que a mesma seja tratada corretamente
		catch (Exception e) {

			throw e;
		}
		
		return rs;
	}
	
}
