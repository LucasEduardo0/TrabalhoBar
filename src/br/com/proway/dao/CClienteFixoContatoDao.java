package br.com.proway.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.com.proway.bean.CClienteFixoContatoBean;
import br.com.proway.connection.ConnectionFactory;

public class CClienteFixoContatoDao {
	
	/**
	 * Atributo para obter a conexão
	 */
	private Connection conexao;
	
	//Construtor
	public CClienteFixoContatoDao() {
		
		this.conexao = new ConnectionFactory().obterConexao();
		
	}
	
	/**
	 * Método para cadastrar contato
	 * @param cfcb
	 */
	public void cadastrarContato(CClienteFixoContatoBean cfcb) {
		
		//Comando SQL
		String sql = "INSERT INTO clientesfixoscontato (nomeCliente, telefone) VALUES (?, ?)";
		
		//Tentar realizar o comando SQL
		try {
			System.out.println("ola");
			
			//Enviando os parâmetros e executando
			java.sql.PreparedStatement pstmt =  this.conexao.prepareStatement(sql);
			pstmt.setString(1, cfcb.getNomeCliente());
			pstmt.setInt(2, cfcb.getTelefone());
			pstmt.execute();
			
		}catch (Exception e){
			
			//Caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao inserir os dados");
			
		}
		
	}
	
	/**
	 * Método para selecionar contatos
	 * @return
	 */
	public DefaultTableModel listarContatos() {
		
		//Criando o DefaultTableModel
		DefaultTableModel modelo = new DefaultTableModel();
		
		//Definir as colunas do DefaultTableModel
		modelo.addColumn("Código");
		modelo.addColumn("Nome Cliente");
		modelo.addColumn("Telefone");
		
		//Comando SQL
		String sql = "SELECT idCliente, nomeCliente, telefone FROM `clientesfixoscontato`";
		
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
			
			//Listar contatos
			while(rs.next()) {
				
				modelo.addRow(new Object[] {
						rs.getInt("idCliente"),
						rs.getString("nomeCliente"),
						rs.getInt("telefone")
				});
				
			}
			
			//Fechar a conxão
			stmt.close();
			
		}catch(Exception e) {
			
			//Caso haja falhas na seleção
			JOptionPane.showMessageDialog(null, "falha ao selecionar os contatos");
			
		}
		
		//Retornar o DefaultTableModel
		return modelo;
		
	}
	
	/**
	 * Alterar dados
	 * @param cfcb
	 */
	public void alterar(CClienteFixoContatoBean cfcb) {
		
		//Comando sql
		String sql = "UPDATE clientesfixoscontato SET nomeCliente = ?, telefone = ? WHERE idCliente = ?";
		
		/**
		 * Tentar realizar o comando SQL
		 */
		try {
			
			/**
			 * Enviando os parâmetros e executando
			 */
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setString(1, cfcb.getNomeCliente());
			pstmt.setInt(2, cfcb.getTelefone());
			pstmt.setInt(3, cfcb.getIdCliente());
			
			pstmt.execute();
			
			//Fechar conexção
			pstmt.close();
			
		}catch(Exception e) {
			
			//Caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao alterar dados"+e.getMessage());
			
		}
		
	}
	
	/**
	 * Método para retornar dados
	 * @param codigo
	 * @return
	 */
	public CClienteFixoContatoBean retornarDados(int codigo) {
		
		//Comando SQL
		String sql = "SELECT * FROM clientesfixoscontato WHERE idCliente = ?";
		
		CClienteFixoContatoBean cfcb = new CClienteFixoContatoBean();
		
		/**
		 * Tentar realizar o comando SQL
		 */
		try {
			
			
			/**
			 * *Conectar e selecionar o comando SQL
			 */
			Statement stmt = conexao.createStatement();
			
			/**
			 * Executando comando SQL e obtendo dados
			 */
			ResultSet rs = stmt.executeQuery(sql);
			
			cfcb.setIdCliente(rs.getInt("idCliente"));
			cfcb.setNomeCliente(rs.getString("nomeCliente"));
			cfcb.setTelefone(rs.getInt("telefone"));
			
			//Fechar a conexão
			stmt.close();
			
		}catch(Exception e) {
			
			//Caso haja falhas na seleção
			JOptionPane.showMessageDialog(null, "Falha ao alterar dados"+e.getMessage());
			
		}
		
		return cfcb;
		
	}
	
	/**
	 * Método para excluir dados
	 * @param idCodigo
	 */
	public void deletarContatos(int idCodigo) {
		
		//Comando SQL
		String sql = "DELETE FROM clientesfixoscontato WHERE idCliente = ?";
		
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
					JOptionPane.showMessageDialog(null, "Falha ao deletar"+e.getMessage());
					
				}
		
	}

}
