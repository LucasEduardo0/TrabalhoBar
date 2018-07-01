package br.com.proway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.com.proway.bean.ACargosBean;
import br.com.proway.bean.BClienteFixoEnderecoBean;
import br.com.proway.connection.ConnectionFactory;

public class BClienteFixoEnderecoDao {
	
	//Atributo para obter a conex�o
	private Connection conexao;
	
	//Construtor
	public BClienteFixoEnderecoDao() {
		//Maneiras de se obter uma conex�o
		//ConnectionFactory cf = new ConnectionFactory();
		//this.conexao = cf.obterConexao();
		
		this.conexao = new ConnectionFactory().obterConexao();
	}
	
	
	/**
	 * M�todo para cadastrar um curso
	 * @param cfeb
	 */
	public void cadastrarCargo(BClienteFixoEnderecoBean cfeb){
		
		//Comando SQL
		String sql = "INSERT INTO clientefixoendereco (idCliente, endereco) VALUES (?, ?)";
		
		/**
		 * Tentar realizar o comando SQL
		 */
		try{
			
			/**
			 * Enviando os par�metros e executando
			 */
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setInt(1, cfeb.getIdCliente());
			pstmt.setString(2, cfeb.getEndereco());
			pstmt.execute();
			
			//Fechar a conex�o
			pstmt.close();
			
		}catch(Exception e){
			
			//Caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao inserir os dados");
			
		}
		
		
		
	}
	
	/**
	 * M�todo para selecionar cursos
	 * @return
	 */
	public DefaultTableModel listarEndereco(){
		
		//Criando o DefaultTableModel
		DefaultTableModel modelo = new DefaultTableModel();
		
		//Definir as colunas do DefaultTableModel
		modelo.addColumn("C�digo");
		modelo.addColumn("C�digo Cliente");
		modelo.addColumn("Nome");
		modelo.addColumn("Endere�o");
		
		//Comando SQL
		String sql = "SELECT idEndereco, clientefixoendereco.idCliente, clientesfixoscontato.nomeCliente, endereco FROM clientefixoendereco, clientesfixoscontato WHERE clientefixoendereco.idCliente = clientesfixoscontato.idCliente";
		
		//Tentar realizar o comando SQL
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
			 * Listando endere�os
			 */
			while(rs.next()){
				
				modelo.addRow(new Object[]{
					rs.getInt("idEndereco"),
					rs.getInt("idCliente"),
					rs.getString("nomeCliente"),
					rs.getString("endereco")
				});
				
			}
			
			//Fechar a conex�o
			stmt.close();	
			
			
		}catch(Exception e){
			
			//Caso haja falhas na sele��o
			JOptionPane.showMessageDialog(null, "Falha ao selecionar os ender�os.");
			
		}
		
		//Retornar o DefaultTableModel
		return modelo;
		
	}
	
	/**
	 * Alterar dados
	 * @param cfeb
	 */
	public void alterar(BClienteFixoEnderecoBean cfeb) {
		
		//Comando SQL
		String sql = "UPDATE `clientefixoendereco` SET idCliente = ?, endereco = ? WHERE idEndereco = ?";
				
				//Tentar realizar o comando SQL
				try{
					
					//Enviando os par�metros e executando
					PreparedStatement pstmt = this.conexao.prepareStatement(sql);
					pstmt.setInt(1, cfeb.getIdCliente());
					pstmt.setString(2, cfeb.getEndereco());
					pstmt.setInt(3, cfeb.getIdEndereco());
					pstmt.execute();
					
					
					//Fechar a conex�o
					pstmt.close();
					
				}catch(Exception e){
					
					//Caso haja falhas
					JOptionPane.showMessageDialog(null, "Falha ao alterar dados"+e.getMessage());
					
				}
		
	}
	
	
	public BClienteFixoEnderecoBean retornarDados(int codigo) {
		
		String sql = "SELECT * FROM clientefixoendereco WHERE idEndereco = ?";
		
		BClienteFixoEnderecoBean cfeb = new BClienteFixoEnderecoBean();
		
		//Tentar realizar o comando SQL
				try{
					
					/**
					 * Conectar e selecionar o comando SQL
					 */
					Statement stmt = conexao.createStatement();
					
					
					/**
					 * Executando comando SQL e obtendo dados
					 */
					ResultSet rs = stmt.executeQuery(sql);
					
					
					cfeb.setIdEndereco(rs.getInt("idEndereco"));
					cfeb.setIdCliente(rs.getInt("idCliente"));
					cfeb.setEndereco(rs.getString("endereco"));
					
					//Fechar a conex�o
					stmt.close();	
					
					
				}catch(Exception e){
					
					//Caso haja falhas na sele��o
					JOptionPane.showMessageDialog(null, "Falha ao alterar dados"+e.getMessage());
					
				}
				
				return cfeb;
			
		
	}
	
	/**
	 * Deletar curso
	 * @param idCodigo
	 */
	public void deletarEnderecos(int idCodigo) {
		
		//Comando SQL
		String sql = "DELETE FROM clientefixoendereco WHERE idEndereco = ?";
		
		//Tentar realizar o comando SQL
		try{
			
			/**
			 * Enviando os par�metros e executando
			 */
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			BClienteFixoEnderecoBean cfeb = new BClienteFixoEnderecoBean();
			pstmt.setInt(1, idCodigo);
			pstmt.execute();
			
			
			//Fechar a conex�o
			pstmt.close();
			
		}catch(Exception e){
			
			//Caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao deletar");
			
		}

		
	}
	
}