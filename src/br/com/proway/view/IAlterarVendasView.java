package br.com.proway.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.IVendasBean;
import br.com.proway.dao.IVendasDao;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IAlterarVendasView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public IAlterarVendasView(IVendasBean vb) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(10, 28, 102, 23);
		contentPane.add(lblCdigo);
		
		JLabel lblCdigoProduto = new JLabel("C\u00F3digo Produto");
		lblCdigoProduto.setBounds(10, 62, 102, 23);
		contentPane.add(lblCdigoProduto);
		
		JLabel lblCdigoFuncionrio = new JLabel("C\u00F3digo Funcion\u00E1rio");
		lblCdigoFuncionrio.setBounds(10, 96, 102, 23);
		contentPane.add(lblCdigoFuncionrio);
		
		JLabel lblCdigoCliente = new JLabel("C\u00F3digo Cliente");
		lblCdigoCliente.setBounds(10, 130, 102, 23);
		contentPane.add(lblCdigoCliente);
		
		textField = new JTextField(String.valueOf(vb.getIdVenda()));
		textField.setBounds(185, 29, 154, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(String.valueOf(vb.getIdProduto()));
		textField_1.setBounds(185, 63, 154, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(String.valueOf(vb.getIdFuncionario()));
		textField_2.setBounds(185, 97, 154, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField(String.valueOf(vb.getIdCliente()));
		textField_3.setBounds(185, 131, 154, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obter dados
				IVendasBean vb = new IVendasBean();
				vb.setIdVenda(Integer.parseInt(textField.getText()));
				vb.setIdProduto(Integer.parseInt(textField_1.getText()));
				vb.setIdFuncionario(Integer.parseInt(textField_2.getText()));
				vb.setIdCliente(Integer.parseInt(textField_3.getText()));
				
				//Chamar ação
				IVendasDao vd = new IVendasDao();
				vd.alterar(vb);
				
				//Fechar
				dispose();
				
				//Chamar
				IVendasView vv = new IVendasView();
				
			}
		});
		btnAlterar.setBounds(10, 191, 107, 23);
		contentPane.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obter dados
				int idCodigo = Integer.parseInt(textField.getText());
				
				//Chamar método para excluir
				IVendasDao vd = new IVendasDao();
				vd.deletarVendas(idCodigo);
				
				//Fechar
				dispose();
				
				//Chamar
				IVendasView vv = new IVendasView();
				
			}
		});
		btnDeletar.setBounds(115, 191, 107, 23);
		contentPane.add(btnDeletar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Fechar
				dispose();
				
				//Chamar
				IVendasView vv = new IVendasView();
				
			}
		});
		btnCancelar.setBounds(221, 191, 107, 23);
		contentPane.add(btnCancelar);
		
		
		
	}

}
