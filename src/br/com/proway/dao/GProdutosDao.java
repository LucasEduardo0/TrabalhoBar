package br.com.proway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.com.proway.bean.ACargosBean;
import br.com.proway.bean.GProdutosBean;
import br.com.proway.connection.ConnectionFactory;

public class GProdutosDao {
	
	/**
	 * Atributo para obter a conexão
	 */
	private Connection conexao;
		
	//Construtor
	public GProdutosDao() {
		this.conexao = new ConnectionFactory().obterConexao();
	}
	
	/**
	 * Método para cadastrar
	 * @param pb
	 */
		public void cadastrarProduto(GProdutosBean pb){
			
			//Comando SQL
			String sql = "INSERT INTO `produtos`(`nomeProduto`, `idMarca`, `valorProduto`) VALUES (?, ?, ?)";
			
			//Tentar realizar o comando SQL
			try{
				
				//Enviando os parâmetros e executando
				PreparedStatement pstmt = this.conexao.prepareStatement(sql);
				pstmt.setString(1, pb.getNomeProduto());
				pstmt.setInt(2, pb.getIdMarca());
				pstmt.setDouble(3, pb.getValorProduto());
				pstmt.execute();
				
				//Fechar a conexão
				pstmt.close();
				
			}catch(Exception e){
				
				//Caso haja falhas
				JOptionPane.showMessageDialog(null, "Falha ao inserir os dados"+e.getMessage());
				
			}
			
			
			
		}
		
		/**
		 * Método para selecionar cursos
		 * @return
		 */
		public DefaultTableModel listarProdutos(){
			
			//Criando o DefaultTableModel
			DefaultTableModel modelo = new DefaultTableModel();
			
			//Definir as colunas do DefaultTableModel
			modelo.addColumn("Código");
			modelo.addColumn("Produto");
			modelo.addColumn("Código Marca");
			modelo.addColumn("Marca");
			modelo.addColumn("Valor");
			
			//Comando SQL
			String sql = "SELECT idProduto, nomeProduto, produtos.idMarca, fornecedor.nomeMarca, valorProduto FROM produtos, fornecedor WHERE fornecedor.idMarca = produtos.idMarca ORDER BY idProduto ASC";
			
			//Tentar realizar o comando SQL
			try{
				
				//Conectar e selecionar o comando SQL
				Statement stmt = conexao.createStatement();
				
				//Executando comando SQL e obtendo dados
				ResultSet rs = stmt.executeQuery(sql);
				
				//Listando cursos
				while(rs.next()){
					
					modelo.addRow(new Object[]{
						rs.getInt("idProduto"),
						rs.getString("nomeProduto"),
						rs.getInt("idMarca"),
						rs.getString("nomeMarca"),
						rs.getDouble("valorProduto")
					});
					
				}
				
				//Fechar a conexão
				stmt.close();	
				
				
			}catch(Exception e){
				
				//Caso haja falhas na seleção
				JOptionPane.showMessageDialog(null, "Falha ao selecionar os produtos.");
				
			}
			
			//Retornar o DefaultTableModel
			return modelo;
			
		}
		
		/**
		 * Alterar dados
		 * @param pb
		 */
		public void alterar(GProdutosBean pb) {
			
			//Comando SQL
					String sql = "UPDATE produtos SET nomeProduto = ?, idMarca = ?, valorProduto = ? WHERE idProduto = ?";
					
					//Tentar realizar o comando SQL
					try{
						
						//Enviando os parâmetros e executando
						PreparedStatement pstmt = this.conexao.prepareStatement(sql);
						pstmt.setString(1, pb.getNomeMarca());
						pstmt.setInt(2, pb.getIdMarca());
						pstmt.setDouble(3, pb.getValorProduto());
						pstmt.setInt(4, pb.getIdProduto());
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
		public GProdutosBean retornarDados(int codigo) {
			
			//CoamndoSQL
			String sql = "SELECT * FROM `produtos` WHERE idProduto = ?";
			
			GProdutosBean pb = new GProdutosBean();
			
			//Tentar realizar o comando SQL
					try{
						
						//Conectar e selecionar o comando SQL
						Statement stmt = conexao.createStatement();
						
						//Executando comando SQL e obtendo dados
						ResultSet rs = stmt.executeQuery(sql);
						
						pb.setIdProduto(rs.getInt("idProduto"));
						pb.setNomeProduto(rs.getString("nomeProduto"));
						pb.setIdMarca(rs.getInt("idmarca"));
						pb.setValorProduto(rs.getDouble("valorProduto"));
						
						//Fechar a conexão
						stmt.close();
						
						//Fechar a conexão
						stmt.close();	
						
					}catch(Exception e){
						
						//Caso haja falhas na seleção
						JOptionPane.showMessageDialog(null, "Falha ao alterar dados"+e.getMessage());
						
					}
					
					return pb;
				
			
		}
		
		/**
		 * Deletar produtos
		 * @param idCodigo
		 */
		public void deletarProdutos(int idCodigo) {
			
			//Comando SQL
			String sql = "DELETE FROM produtos WHERE idProduto = ?";
			
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
