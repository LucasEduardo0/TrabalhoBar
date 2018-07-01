package br.com.proway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Generated;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.com.proway.bean.ACargosBean;
import br.com.proway.connection.ConnectionFactory;

public class ACargosDao {
	
	//Atributo para obter a conexão
	private Connection conexao;
	
	//Construtor
	public ACargosDao(){
		//Maneiras de se obter uma conexão
		//ConnectionFactory cf = new ConnectionFactory();
		//this.conexao = cf.obterConexao();
		
		this.conexao = new ConnectionFactory().obterConexao();
	}
	
	
	/**
	 * Método para cadastrar um curso
	 * @param cb
	 */
	
	public void cadastrarCargo(ACargosBean cb){
		
		/**
		 * Comando SQL
		 */
		String sql = "INSERT INTO cargos (nomeCargo, valorSalario, descontoSalario) VALUES (?, ?, ?)";
		
		/**
		 * Tentar realizar o comando SQL
		 */
		try{
			
			/**
			 * Enviando os parâmetros e executando
			 */
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setString(1, cb.getNomeCargo());
			pstmt.setDouble(2, cb.getValorSalario());
			pstmt.setDouble(3, cb.getDescontoSalario());
			pstmt.execute();
			
			//Fechar a conexão
			pstmt.close();
			
		}catch(Exception e){
			
			//Caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao inserir os dados");
			
		}
		
		
		
	}
	
	/**
	 * Método para selecionar cursos
	 * @return
	 */
	public DefaultTableModel listarCursos(){
		
		/**
		 * Criando o DefaultTableModel
		 */
		DefaultTableModel modelo = new DefaultTableModel();
		
		/**
		 * Definir as colunas do DefaultTableModel
		 */
		modelo.addColumn("Código");
		modelo.addColumn("Cargo");
		modelo.addColumn("Salário");
		modelo.addColumn("Desconto Salário");
		
		//Comando SQL
		String sql = "SELECT * FROM cargos";
		
		/**
		 * Tentar realizar o comando SQL
		 */
		try{
			
			/**
			 * Conectar e selecionar o comando SQL
			 */
			Statement stmt = conexao.createStatement();
			
			/**
			 * Executando comando SQL e obtendo dados
			 */
			ResultSet rs = stmt.executeQuery(sql);
			
			/**
			 * Listando cursos
			 */
			while(rs.next()){
				
				modelo.addRow(new Object[]{
					rs.getInt("idCargo"),
					rs.getString("nomeCargo"),
					rs.getDouble("valorSalario"),
					rs.getDouble("descontoSalario")
				});
				
			}
			
			//Fechar a conexão
			stmt.close();	
			
			
		}catch(Exception e){
			
			//Caso haja falhas na seleção
			JOptionPane.showMessageDialog(null, "Falha ao selecionar os cargos.");
			
		}
		
		//Retornar o DefaultTableModel
		return modelo;
		
	}
	
	//Alterar dados
	public void alterar(ACargosBean cb) {
		
		/**
		 * Comando SQL
		 */
				String sql = "UPDATE cargos SET nomeCargo = ?, valorSalario = ?, descontoSalario = ? WHERE idCargo = ?";
				
				/**
				 * Tentar realizar o comando SQL
				 */
				try{
					
					//Enviando os parâmetros e executando
					PreparedStatement pstmt = this.conexao.prepareStatement(sql);
					pstmt.setString(1, cb.getNomeCargo());
					pstmt.setDouble(2, cb.getValorSalario());
					pstmt.setDouble(3, cb.getDescontoSalario());
					pstmt.setInt(4, cb.getIdCargo());
					pstmt.execute();
					
					
					//Fechar a conexão
					pstmt.close();
					
				}catch(Exception e){
					
					//Caso haja falhas
					JOptionPane.showMessageDialog(null, "Falha ao alterar dados"+e.getMessage());
					
				}
		
	}
	
	/**
	 * Método para retornar dados
	 * @param codigo
	 * @return
	 */
	public ACargosBean retornarDados(int codigo) {
		
		//CoamndoSQL
		String sql = "SELECT * FROM cargos WHERE idCargo = ?";
		
		ACargosBean cb = new ACargosBean();
		
		//Tentar realizar o comando SQL
				try{
					
					//Conectar e selecionar o comando SQL
					Statement stmt = conexao.createStatement();
					
					
					//Executando comando SQL e obtendo dados
					ResultSet rs = stmt.executeQuery(sql);
					
					
					cb.setIdCargo(rs.getInt("idCargo"));
					cb.setNomeCargo(rs.getString("nomeCargo"));
					cb.setValorSalario(rs.getDouble("valorSalario"));
					cb.setDescontoSalario(rs.getDouble("descontoSalario"));
					
					//Fechar a conexão
					stmt.close();	
					
					
				}catch(Exception e){
					
					//Caso haja falhas na seleção
					JOptionPane.showMessageDialog(null, "Falha ao alterar dados"+e.getMessage());
					
				}
				
				return cb;
			
		
	}
	
	/**
	 * Deletar curso
	 * @param idCodigo
	 */
	public void deletarCursos(int idCodigo) {
		
		//Comando SQL
		String sql = "DELETE FROM `cargos` WHERE idCargo = ?";
		
		//Tentar realizar o comando SQL
		try{
			
			/**
			 * Enviando os parâmetros e executando
			 */
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setInt(1, idCodigo);
			pstmt.execute();
			
			//Fechar a conexão
			pstmt.close();
			
		}catch(Exception e){
			
			//Caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao deletar");
			
		}

		
	}
	
}