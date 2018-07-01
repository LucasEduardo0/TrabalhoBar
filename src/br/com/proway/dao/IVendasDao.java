package br.com.proway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.com.proway.bean.ACargosBean;
import br.com.proway.bean.IVendasBean;
import br.com.proway.connection.ConnectionFactory;

public class IVendasDao {
	
	/**
	 * Atributo para obter a conexão
	 */
		private Connection conexao;
		
		//Construtor
		public IVendasDao (){
			
			this.conexao = new ConnectionFactory().obterConexao();
		}
		
		
		/**
		 * Método para cadastrar venda
		 * @param vb
		 */
		public void cadastrarVenda(IVendasBean vb){
			
			//Comando SQL
			String sql = "INSERT INTO vendas (idProduto, idFuncionario, data, hora, idcliente) VALUES (?, ?, ?, ?, ?)";
			
			//Tentar realizar o comando SQL
			try{
				
				//Enviando os parâmetros e executando
				PreparedStatement pstmt = this.conexao.prepareStatement(sql);
				pstmt.setInt(1, vb.getIdProduto());
				pstmt.setInt(2, vb.getIdFuncionario());
				pstmt.setString(3, vb.getData());
				pstmt.setString(4, vb.getHora());
				pstmt.setInt(5, vb.getIdCliente());
				pstmt.execute();
				
				//Fechar a conexão
				pstmt.close();
				
			}catch(Exception e){
				
				//Caso haja falhas
				JOptionPane.showMessageDialog(null, "Falha ao inserir os dados");
				
			}
			
			
			
		}
		
		/**
		 * Método para selecionar vendas
		 * @return
		 */
		public DefaultTableModel listarVendas(){
			
			//Criando o DefaultTableModel
			DefaultTableModel modelo = new DefaultTableModel();
			
			//Definir as colunas do DefaultTableModel
			modelo.addColumn("Código");
			modelo.addColumn("Código Produto");
			modelo.addColumn("Produto");
			modelo.addColumn("Código Funcionário");
			modelo.addColumn("Funcionário");
			modelo.addColumn("Data");
			modelo.addColumn("Hora");
			modelo.addColumn("Código Cliente");
			modelo.addColumn("Cliente");
			
			//Comando SQL
			String sql = "SELECT `idVenda`, vendas.`idProduto`, produtos.nomeProduto , vendas.`idFuncionario`, funcionarios.nomeFuncionario,`data`, `hora`, vendas.`idCliente`, clientesfixoscontato.nomeCliente FROM `vendas`,produtos, clientesfixoscontato, funcionarios WHERE (vendas.idProduto = produtos.idProduto) AND (vendas.idFuncionario = funcionarios.idFuncionario) AND (vendas.idCliente = clientesfixoscontato.idCliente)";
			
			//Tentar realizar o comando SQL
			try{
				
				//Conectar e selecionar o comando SQL
				Statement stmt = conexao.createStatement();
				
				//Executando comando SQL e obtendo dados
				ResultSet rs = stmt.executeQuery(sql);
				
				//Listando cursos
				while(rs.next()){
					
					modelo.addRow(new Object[]{
						rs.getInt("idVenda"),
						rs.getInt("idProduto"),
						rs.getString("nomeProduto"),
						rs.getInt("idFuncionario"),
						rs.getString("nomeFuncionario"),
						rs.getString("data"),
						rs.getString("hora"),
						rs.getInt("idCliente"),
						rs.getString("nomeCliente")
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
		
		/**
		 * Alterar dados
		 * @param vb
		 */
		public void alterar(IVendasBean vb) {
			
			//Comando SQL
					String sql = "UPDATE `vendas` SET `idProduto`= ?,`idFuncionario`= ?,`data`= ?,`hora` = ?,`idCliente`= ? WHERE `idVenda`= ?";
					
					//Tentar realizar o comando SQL
					try{
						
						//Enviando os parâmetros e executando
						PreparedStatement pstmt = this.conexao.prepareStatement(sql);
						pstmt.setInt(1, vb.getIdProduto());
						pstmt.setInt(2, vb.getIdFuncionario());
						pstmt.setString(3, vb.getData());
						pstmt.setString(4, vb.getHora());
						pstmt.setInt(5, vb.getIdCliente());
						pstmt.setInt(6, vb.getIdVenda());
						
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
		public IVendasBean retornarDados(int codigo) {
			
			//CoamndoSQL
			String sql = "SELECT * FROM vendas WHERE idVenda = ?";
			
			IVendasBean vb = new IVendasBean();
			
			//Tentar realizar o comando SQL
					try{
						
						//Conectar e selecionar o comando SQL
						Statement stmt = conexao.createStatement();
						
						
						//Executando comando SQL e obtendo dados
						ResultSet rs = stmt.executeQuery(sql);
						
						vb.setIdVenda(rs.getInt("idVenda"));
						vb.setIdProduto(rs.getInt("idProduto"));
						vb.setIdFuncionario(rs.getInt("idFuncionario"));
						vb.setData(rs.getString("data"));
						vb.setHora(rs.getString("hora"));
						vb.setIdCliente(rs.getInt("idCliente"));
						
						//Fechar a conexão
						stmt.close();	
						
						
					}catch(Exception e){
						
						//Caso haja falhas na seleção
						JOptionPane.showMessageDialog(null, "Falha ao alterar dados"+e.getMessage());
						
					}
					
					return vb;
				
			
		}
		
		/**
		 * Deletar vendas
		 * @param idCodigo
		 */
		public void deletarVendas(int idCodigo) {
			
			//Comando SQL
			String sql = "DELETE FROM `vendas` WHERE idVenda = ?";
			
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
				JOptionPane.showMessageDialog(null, "Falha ao deletar");
				
			}

			
		}

}
