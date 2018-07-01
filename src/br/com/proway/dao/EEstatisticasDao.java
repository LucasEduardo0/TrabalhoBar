package br.com.proway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.com.proway.bean.EEstatisticasBean;
import br.com.proway.connection.ConnectionFactory;

public class EEstatisticasDao {
	
	/**
	 * Atributo para obter a conexão
	 */
	private Connection conexao;
	
	//Construtor
	public EEstatisticasDao() {
		
		this.conexao = new ConnectionFactory().obterConexao();
		
	}
	
	/**
	 * Método para cadastrar estatisticas
	 * @param eb
	 */
	public void cadastrarEstatistica(EEstatisticasBean eb) {
		
		//Comando SQL
		String sql = "INSERT INTO estatisticas (idFuncionario, quantidadeVendida) VALUES (?, ?)";
		
		//Tentar realizar comando sql
		try {
			
			//Enviando os parâmetros e executando
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setInt(1, eb.getIdFuncionario());
			pstmt.setInt(2, eb.getQuantidadeVendida());
			pstmt.execute();
			
			//Fechar conexão
			pstmt.close();
			
		}catch(Exception e) {
			
			//Caso de erros
			JOptionPane.showMessageDialog(null, "Falha ao cadastrar estatistica!");
			
		}
		
	}
	
	/**
	 * Método para selecionar dados
	 * @return
	 */
	public DefaultTableModel listarEstatisticas() {
		
		//Criar DefaulTableModel
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Código Funcionário");
		modelo.addColumn("Nome Funcionario");
		modelo.addColumn("Quantidade Vendida");
		
		//Comando SQL
		String sql = "SELECT idEstatisticas, estatisticas.idFuncionario, funcionarios.nomeFuncionario, quantidadeVendida FROM estatisticas, funcionarios WHERE estatisticas.idFuncionario = funcionarios.idFuncionario";
		
		//Tentar realizar comando SQL
		try {
			
			//Conectar e selecionar o comando SQL
			Statement stmt = conexao.createStatement();
			
			//Executando comando SQL e obtendo dados
			ResultSet rs = stmt.executeQuery(sql);
			
			//Listando estatisticas
			while(rs.next()) {
				
				modelo.addRow(new Object[] {
				rs.getInt("idEstatisticas"),
				rs.getInt("idFuncionario"),
				rs.getString("nomeFuncionario"),
				rs.getInt("quantidadeVendida")
				
				});
				
			}
			
			//Fechar a conexão
			stmt.close();
			
		}catch(Exception e) {
			
			//Caso haja falhas na seleção
			JOptionPane.showMessageDialog(null, "Falha ao selecionar as estatisticas."+e.getMessage());
			
		}
		
		//Retornar o DefaultTableModel
		return modelo;
		
	}
	
	/**
	 * Método para alterar
	 * @param eb
	 */
	public void alterar(EEstatisticasBean eb) {
		
		//Comando SQL
		String sql = ("UPDATE estatisticas SET idFuncionario = ?, quantidadeVendida = ? WHERE idEstatisticas = ?");
		
		//Tentar executar comando SQL
		try {
			
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setInt(1, eb.getIdFuncionario());
			pstmt.setInt(2, eb.getQuantidadeVendida());
			pstmt.setInt(3, eb.getIdEstatistica());
			pstmt.execute();
			
			//Fechar conexão
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
	public EEstatisticasBean retornarDados(int codigo) {
		
		//Comando SQL
		String sql = "SELECT * FROM estatisticas WHERE idEstatisticas = ?";
		
		EEstatisticasBean eb = new EEstatisticasBean();
		
		//Tentar realizar o comando SQL
		try {
			
			//Conectar e selecionar o comando SQL
			Statement stmt = conexao.createStatement();
			
			//Executando comando SQL e obtendo dados
			ResultSet rs = stmt.executeQuery(sql);
			
			eb.setIdEstatistica(rs.getInt("idEstatisticas"));
			eb.setIdFuncionario(rs.getInt("idFuncionario"));
			eb.setQuantidadeVendida(rs.getInt("quanridadeVendida"));
			
			//Fechar a conexão
			stmt.close();
			
		}catch(Exception e) {
			
			//Caso haja falhas na seleção
			JOptionPane.showMessageDialog(null, "Falha ao alterar dados"+e.getMessage());
			
		}
		
		return eb;
		
	}
	
	/**
	 * Método para deletar
	 * @param idEstatisticas
	 */
	public void deletarEstatisticas(int idEstatisticas) {
		
		//Comando SQL
		String sql = "DELETE FROM estatisticas WHERE idEstatisticas = ?";
		
		//Tentar executar o comand SQL
				try{
					
					//Enviando os parâmetros e executando
					PreparedStatement pstmt = this.conexao.prepareStatement(sql);
					pstmt.setInt(1, idEstatisticas);
					pstmt.execute();
					
					//Fechar a conexão
					pstmt.close();
					
				}catch(Exception e) {
					
					//Caso haja falhas
					JOptionPane.showMessageDialog(null, "Falha ao deletar"+e.getMessage());
					
				}
		
	}

}
