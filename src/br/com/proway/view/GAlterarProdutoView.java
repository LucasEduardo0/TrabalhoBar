package br.com.proway.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.GProdutosBean;
import br.com.proway.dao.GProdutosDao;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GAlterarProdutoView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public GAlterarProdutoView(GProdutosBean pb) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 307, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(10, 11, 79, 14);
		contentPane.add(lblCdigo);
		
		JLabel lblNomeProduto = new JLabel("Nome Produto");
		lblNomeProduto.setBounds(10, 36, 79, 14);
		contentPane.add(lblNomeProduto);
		
		JLabel lblCdigoMarca = new JLabel("C\u00F3digo Marca");
		lblCdigoMarca.setBounds(10, 61, 79, 14);
		contentPane.add(lblCdigoMarca);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(10, 86, 46, 14);
		contentPane.add(lblValor);
		
		textField = new JTextField(String.valueOf(pb.getIdProduto()));
		textField.setBounds(173, 8, 114, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(pb.getNomeProduto());
		textField_1.setBounds(173, 33, 114, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(String.valueOf(pb.getIdMarca()));
		textField_2.setBounds(173, 58, 114, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField(String.valueOf(pb.getValorProduto()));
		textField_3.setBounds(173, 83, 114, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obter dados
				GProdutosBean pb = new GProdutosBean();
				pb.setIdMarca(Integer.parseInt(textField.getText()));
				pb.setNomeProduto(textField_1.getText());
				pb.setIdMarca(Integer.parseInt(textField_2.getText()));
				pb.setValorProduto(Double.parseDouble(textField_3.getText()));
				
				//Chamar ação
				GProdutosDao pd = new GProdutosDao();
				pd.alterar(pb);
				
				//Fechar formulário
				dispose();
				
				//Chamar formulário
				GProdutosView pv = new GProdutosView();
				
				
				
			}
		});
		btnAlterar.setBounds(0, 155, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obter dados
				int idCodigo = Integer.parseInt(textField.getText());
				
				//Chamar método para excluir
				GProdutosDao pd = new GProdutosDao();
				pd.deletarProdutos(idCodigo);
				
				//Fechar formulário
				dispose();
				
				//Chamar formulário
				GProdutosView pv = new GProdutosView();
				
			}
		});
		btnDeletar.setBounds(99, 155, 89, 23);
		contentPane.add(btnDeletar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Fechar formulário
				dispose();
				
				//Chamar formulário
				GProdutosView pv = new GProdutosView();
				
			}
		});
		btnCancelar.setBounds(198, 155, 89, 23);
		contentPane.add(btnCancelar);
	}

}
