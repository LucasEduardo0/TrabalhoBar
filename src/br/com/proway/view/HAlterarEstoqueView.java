package br.com.proway.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.HEstoqueBean;
import br.com.proway.dao.HEstoqueDao;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HAlterarEstoqueView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public HAlterarEstoqueView(HEstoqueBean eb) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 411, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(10, 28, 129, 26);
		contentPane.add(lblCdigo);
		
		JLabel lblCdigoProduto = new JLabel("C\u00F3digo Produto");
		lblCdigoProduto.setBounds(10, 75, 129, 33);
		contentPane.add(lblCdigoProduto);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(10, 119, 129, 33);
		contentPane.add(lblQuantidade);
		
		textField = new JTextField(String.valueOf(eb.getIdEstoque()));
		textField.setBounds(185, 28, 163, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(String.valueOf(eb.getIdProduto()));
		textField_1.setBounds(185, 72, 163, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(String.valueOf(eb.getQuantidadeEstoque()));
		textField_2.setBounds(185, 122, 163, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Obter dados
				HEstoqueBean eb = new HEstoqueBean();
				eb.setIdEstoque(Integer.parseInt(textField.getText()));
				eb.setIdProduto(Integer.parseInt(textField_1.getText()));
				eb.setQuantidadeEstoque(Double.parseDouble(textField_2.getText()));
				
				//Chamar ação
				HEstoqueDao ed = new HEstoqueDao();
				ed.alterar(eb);
				
				//Fechar formulário
				dispose();
				
				//Chamar
				HEstoqueView ev = new HEstoqueView();
				
			}
		});
		btnAlterar.setBounds(10, 194, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Obter dados
				int idCodigo = Integer.parseInt(textField.getText());
				
				//Chamar método para excluir
				HEstoqueDao ed = new HEstoqueDao();
				ed.deletarCursos(idCodigo);
				
				//Fechar formulário
				dispose();
				
				//Chamar
				HEstoqueView ev = new HEstoqueView();
				
			}
		});
		btnDeletar.setBounds(135, 194, 89, 23);
		contentPane.add(btnDeletar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Fechar formulário
				dispose();
				
				//Chamar
				HEstoqueView ev = new HEstoqueView();
				
				
				
			}
		});
		btnCancelar.setBounds(260, 194, 89, 23);
		contentPane.add(btnCancelar);
	}

}
