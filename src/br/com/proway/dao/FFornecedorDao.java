package br.com.proway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.com.proway.bean.ACargosBean;
import br.com.proway.bean.FFornecedorBean;
import br.com.proway.connection.ConnectionFactory;

public class FFornecedorDao {
	
	/**
	 * Atributo para obter a conexão
	 */
		private Connection conexao;
		
		//Construtor
		public FFornecedorDao() {
			
			this.conexao = new ConnectionFactory().obterConexao();
			
		}
		
		/**
		 * Método para cadastrar
		 * @param fb
		 */
		public void cadastrarFornecedor(FFornecedorBean fb) {
			
			//Comando SQL
			String sql = "INSERT INTO fornecedor (nomeMarca) VALUES (?)";
			
			//Tentar realizar comando SQL
			try {
				
				//Enviando os parâmetros e executando
				PreparedStatement pstmt = this.conexao.prepareStatement(sql);
				pstmt.setString(1, fb.getNomeMarca());
				pstmt.execute();
				
				//Fechar conexao
				pstmt.close();
				
			}catch(Exception e) {
				
				//Caso haja falhas
				JOptionPane.showMessageDialog(null, "Falha ao inserir os dados");
				
			}
			
		}
		
	/**
	 * Método para selecionar dados
	 * @return
	 */
		public DefaultTableModel listarFornecedor() {
			
			//Criando o DefaultTableModel
			DefaultTableModel modelo = new DefaultTableModel();
			
			modelo.addColumn("Código");
			modelo.addColumn("Fornecedor");
			
			//Comando SQL
			String sql = "SELECT * FROM fornecedor";
			
			//Tentar realizar o comando SQL
			try{
				
				//Conectar e selecionar o comando SQL
				Statement stmt = conexao.createStatement();
				
				//Executando comando SQL e obtendo dados
				ResultSet rs = stmt.executeQuery(sql);
				
				//Listando fornecedores
				while(rs.next()){
					
					modelo.addRow(new Object[]{
						rs.getInt("idMarca"),
						rs.getString("nomeMarca")
					});
					
				}
				
				//Fechar a conexão
				stmt.close();	
				
				
			}catch(Exception e){
				
				//Caso haja falhas na seleção
				JOptionPane.showMessageDialog(null, "Falha ao selecionar os forneceores.");
				
			}
			
			//Retornar o DefaultTableModel
			return modelo;
			
		}
		
	/**
	 * Método para alterar
	 * @param fb
	 */
		public void alterar(FFornecedorBean fb) {
			
			//Comando SQL
					String sql = "UPDATE fornecedor SET nomeMarca = ? WHERE idMarca = ?";
					
					//Tentar realizar o comando SQL
					try{
						
						//Enviando os parâmetros e executando
						PreparedStatement pstmt = this.conexao.prepareStatement(sql);
						pstmt.setString(1, fb.getNomeMarca());
						pstmt.setInt(2, fb.getIdMarca());
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
		public FFornecedorBean retornarFornecer(int codigo){
			
			//Comando SQL
			String sql = "SELECT * FROM fornecedor WHERE idMarca = ?";
			
			FFornecedorBean fb = new FFornecedorBean();
			
			//Tentar realizar o comando SQL
			try{
				
				
				//Conectar e selecionar o comando SQL
				Statement stmt = conexao.createStatement();
				
				//Executando comando SQL e obtendo dados
				ResultSet rs = stmt.executeQuery(sql);
				
				fb.setIdMarca(rs.getInt("idMarca"));
				fb.setNomeMarca(rs.getString("nomeMarca"));
				
				//Fechar a conexão
				stmt.close();	
				
				
			}catch(Exception e){
				
				//Caso haja falhas na seleção
				JOptionPane.showMessageDialog(null, "Falha ao alterar dados"+e.getMessage());
				
			}
			
			return fb;
			
		}
		
	/**
	 * Método para deletar fornecedor
	 * @param idCodigo
	 */
		public void deletarFornecedorCursos(int idCodigo) {
			
			//Comando SQL
			String sql = "DELETE FROM fornecedor WHERE idMarca = ?";
			
			//Tentar realizar o comando SQL
			try{
				
				//Enviando os parâmetros e executando
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
