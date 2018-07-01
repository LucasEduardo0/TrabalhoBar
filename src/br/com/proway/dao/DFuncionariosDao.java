package br.com.proway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.com.proway.bean.DFuncionariosBean;
import br.com.proway.connection.ConnectionFactory;

public class DFuncionariosDao {
	
	/**
	 * Atributo para obter a conex�o
	 */
	private Connection conexao;
	
	//Construtor
	public DFuncionariosDao() {
		
		this.conexao = new ConnectionFactory().obterConexao();
		
	}
	
	/**
	 * Metodo para cadastrar funcionario
	 * @param fb
	 */
	public void cadastrarFuncionario(DFuncionariosBean fb) {
		
		//Comando SQL
		String sql = "INSERT INTO funcionarios (nomeFuncionario, sexoFuncionario, idCargo, turnos) VALUES (?, ?, ?, ?)";
		
		/**
		 * Tentar realizar o comando SQL
		 */
		try {
			
			//Enviando os par�metros e executando
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setString(1, fb.getNomeFuncionario());
			pstmt.setString(2, fb.getSexofuncionario());
			pstmt.setInt(3, fb.getIdCargo());
			pstmt.setString(4, fb.getTurnos());
			pstmt.execute();
			
			//Fechar conexao
			pstmt.close();
			
		}catch(Exception e) {
			
			//Caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao inserir os dados");
			
		}
		
	}
	
	/**
	 * M�todo para selecionar dados
	 * @return
	 */
	public DefaultTableModel listarFuncionarios() {
		
		//Criando o DefaultTableModel
		DefaultTableModel modelo = new DefaultTableModel();
		
		modelo.addColumn("C�digo");
		modelo.addColumn("Funcion�rio");
		modelo.addColumn("Sexo");
		modelo.addColumn("C�digo Cargo");
		modelo.addColumn("Cargo");
		modelo.addColumn("Turno");
		
		//Comando SQL
		
		String sql = "SELECT idFuncionario, nomeFuncionario, sexoFuncionario, funcionarios.idCargo, cargos.nomeCargo, turnos FROM funcionarios, cargos WHERE funcionarios.idCargo = cargos.idCargo ORDER BY idFuncionario ASC";
		
		/**
		 * Tentar realizar o comando SQL
		 */
		try {
			
			/**
			 * Conectar e selecionar o comando SQL
			 */
			Statement stmt = conexao.createStatement();
			
			/**
			 * Executando comando SQL e obtendo dados
			 */
			ResultSet rs = stmt.executeQuery(sql);
			
			//Listando funcion�rios
			while(rs.next()) {
				
				modelo.addRow(new Object[] {
						rs.getInt("idFuncionario"),
						rs.getString("nomeFuncionario"),
						rs.getString("sexoFuncionario"),
						rs.getInt("idCargo"),
						rs.getString("nomeCargo"),
						rs.getString("turnos")
				});
				
			}
			
			//Fechar a conex�o
			stmt.close();
			
		}catch(Exception e) {
			
			//Caso haja falhas na sele��o
			JOptionPane.showMessageDialog(null, "Falha ao selecionar os cargos."+e.getMessage());
			
		}
		
		//Retornar o DefaultTableModel
		return modelo;
		
	}
	
	/**
	 * M�todo para alterar
	 * @param fb
	 */
	public void alterar(DFuncionariosBean fb) {
		
		//Comando SQL
		String sql = "UPDATE funcionarios SET nomeFuncionario = ?, sexoFuncionario = ?, idCargo = ?, turnos = ? WHERE idFuncionario = ?";
		
		/**
		 * Tentar realizar comando SQL
		 */
		try {
			
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setString(1, fb.getNomeFuncionario());
			pstmt.setString(2, fb.getSexofuncionario());
			pstmt.setInt(3, fb.getIdCargo());
			pstmt.setString(4, fb.getTurnos());
			pstmt.setInt(5, fb.getIdFuncionario());
			pstmt.execute();
			
			//Fechar conex�o
			pstmt.close();
			
		}catch(Exception e) {
			
			//Caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao alterar dados"+e.getMessage());
			
		}
		
	}
	
	/**
	 * M�todo para retornar os dados
	 * @param codigo
	 * @return
	 */
	public DFuncionariosBean retornarDados(int codigo){
		
		//Comando SQL
		String sql = "SELECT * FROM funcionarios WHERE idFuncionario = ?";
		
		DFuncionariosBean fb = new DFuncionariosBean();
		
		//Tentar realizar o comando SQL
		try {
			
			/**
			 * Conectar e selecionar o comando SQL
			 */
			Statement stmt = conexao.createStatement();
			
			/**
			 * Executando comando SQL e obtendo dados
			 */
			ResultSet rs = stmt.executeQuery(sql);
			
			fb.setIdFuncionario(rs.getInt("idFuncionario"));
			fb.setNomeFuncionario(rs.getString("nomeFuncionario"));
			fb.setSexofuncionario(rs.getString("sexoFuncionario"));
			fb.setIdCargo(rs.getInt("idCargo"));
			fb.setTurnos(rs.getString("turnos"));
			
			//Fechar a conex�o
			stmt.close();
			
		}catch(Exception e) {
			
			//Caso haja falhas na sele��o
			JOptionPane.showMessageDialog(null, "Falha ao alterar dados"+e.getMessage());
			
		}
		
		return fb;
		
	}
	
	/**
	 * M�todo para deletar
	 * @param idFuncionario
	 */
	public void deletarFuncionario(int idFuncionario) {
		
		//Comando SQL
		String sql = "DELETE FROM funcionarios WHERE idFuncionario = ?";
		
		//Tentar executar o comand SQL
		try{
			
			//Enviando os par�metros e executando
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setInt(1, idFuncionario);
			pstmt.execute();
			
			//Fechar a conex�o
			pstmt.close();
			
		}catch(Exception e) {
			
			//Caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao deletar"+e.getMessage());
			
		}
		
	}

}
