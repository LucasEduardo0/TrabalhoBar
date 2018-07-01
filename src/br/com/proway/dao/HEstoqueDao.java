package br.com.proway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.com.proway.bean.ACargosBean;
import br.com.proway.bean.HEstoqueBean;
import br.com.proway.connection.ConnectionFactory;

public class HEstoqueDao {
	
	/**
	 * Atributo para obter a conex�o
	 */
		private Connection conexao;
		
		//Construtor
		public HEstoqueDao() {
			
			this.conexao = new ConnectionFactory().obterConexao();
		}
		
		
		/**
		 * M�todo para cadastrar um estoque
		 * @param eb
		 */
		public void cadastrarEstoque(HEstoqueBean eb){
			
			//Comando SQL
			String sql = "INSERT INTO estoque (idProduto, quantidadeEstoque) VALUES (?, ?)";
			
			//Tentar realizar o comando SQL
			try{
				
				//Enviando os par�metros e executando
				PreparedStatement pstmt = this.conexao.prepareStatement(sql);
				pstmt.setInt(1, eb.getIdProduto());
				pstmt.setDouble(2, eb.getQuantidadeEstoque());
				pstmt.execute();
				
				//Fechar a conex�o
				pstmt.close();
				
			}catch(Exception e){
				
				//Caso haja falhas
				JOptionPane.showMessageDialog(null, "Falha ao inserir os dados");
				
			}
			
			
			
		}
		
		/**
		 * M�todo para selecionar estoque
		 * @return
		 */
		public DefaultTableModel listarEstoque(){
			
			//Criando o DefaultTableModel
			DefaultTableModel modelo = new DefaultTableModel();
			
			//Definir as colunas do DefaultTableModel
			modelo.addColumn("C�digo");
			modelo.addColumn("C�digo Produto");
			modelo.addColumn("Produto");
			modelo.addColumn("Quantidade Kg");
			
			//Comando SQL
			String sql = "SELECT `idEstoque`, estoque.`idProduto`, produtos.nomeProduto,`quantidadeEstoque` FROM `estoque`, produtos WHERE estoque.idProduto = produtos.idProduto";
			
			//Tentar realizar o comando SQL
			try{
				
				//Conectar e selecionar o comando SQL
				Statement stmt = conexao.createStatement();
				
				//Executando comando SQL e obtendo dados
				ResultSet rs = stmt.executeQuery(sql);
				
				//Listando cursos
				while(rs.next()){
					
					modelo.addRow(new Object[]{
						rs.getInt("idEstoque"),
						rs.getInt("idProduto"),
						rs.getString("nomeProduto"),
						rs.getDouble("quantidadeEstoque")
					});
					
				}
				
				//Fechar a conex�o
				stmt.close();	
				
				
			}catch(Exception e){
				
				//Caso haja falhas na sele��o
				JOptionPane.showMessageDialog(null, "Falha ao selecionar estoque.");
				
			}
			
			//Retornar o DefaultTableModel
			return modelo;
			
		}
		
		/**
		 * Alterar dados
		 * @param eb
		 */
		public void alterar(HEstoqueBean eb) {
			
			//Comando SQL
					String sql = "UPDATE `estoque` SET `idProduto`= ?, `quantidadeEstoque`= ? WHERE `idEstoque`= ?";
					
					//Tentar realizar o comando SQL
					try{
						
						//Enviando os par�metros e executando
						PreparedStatement pstmt = this.conexao.prepareStatement(sql);
						pstmt.setInt(1, eb.getIdProduto());
						pstmt.setDouble(2, eb.getQuantidadeEstoque());
						pstmt.setInt(3, eb.getIdEstoque());
						
						//Fechar a conex�o
						pstmt.close();
						
					}catch(Exception e){
						
						//Caso haja falhas
						JOptionPane.showMessageDialog(null, "Falha ao alterar dados"+e.getMessage());
						
					}
			
		}
		
		/**
		 * M�todo para retornar dados
		 * @param codigo
		 * @return
		 */
		public HEstoqueBean retornarDados(int codigo) {
			
			//CoamndoSQL
			String sql = "SELECT * FROM estoque WHERE idEstoque = ?";
			
			HEstoqueBean eb = new HEstoqueBean();
			
			//Tentar realizar o comando SQL
					try{
						
						//Conectar e selecionar o comando SQL
						Statement stmt = conexao.createStatement();
						
						
						//Executando comando SQL e obtendo dados
						ResultSet rs = stmt.executeQuery(sql);
						
						
						eb.setIdEstoque(rs.getInt("idEstoque"));
						eb.setIdProduto(rs.getInt("idProduto"));
						eb.setQuantidadeEstoque(rs.getDouble("quantidadeEstoque"));
						
						//Fechar a conex�o
						stmt.close();	
						
						
					}catch(Exception e){
						
						//Caso haja falhas na sele��o
						JOptionPane.showMessageDialog(null, "Falha ao alterar dados"+e.getMessage());
						
					}
					
					return eb;
				
			
		}
		
		/**
		 * Deletar curso
		 * @param idCodigo
		 */
		public void deletarCursos(int idCodigo) {
			
			//Comando SQL
			String sql = "DELETE FROM estoque WHERE idEstoque = ?";
			
			//Tentar realizar o comando SQL
			try{
				
				//Enviando os par�metros e executando
				PreparedStatement pstmt = this.conexao.prepareStatement(sql);
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
