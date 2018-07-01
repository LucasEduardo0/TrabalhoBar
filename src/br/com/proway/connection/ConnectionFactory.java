package br.com.proway.connection;

//Importar classes
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	//Atributos
	private final String url = "jdbc:mysql://localhost:3306/bar";
	private final String user = "root";
	private final String password = "";
	
	//M�todo para obter a conex�o
	public Connection obterConexao(){
		
		//Vari�vel contendo a conex�o
		Connection conexao = null;
		
		//Tentar realizar a conex�o
		try{
			conexao = DriverManager.getConnection(url, user, password);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		//Retornar a conex�o
		return conexao;
	}
}

