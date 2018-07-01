package br.com.proway.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.BClienteFixoEnderecoBean;
import br.com.proway.dao.BClienteFixoEnderecoDao;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BAlterarEnderecos extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public BAlterarEnderecos(BClienteFixoEnderecoBean cfeb) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCdigo.setBounds(10, 29, 127, 28);
		contentPane.add(lblCdigo);
		
		JLabel lblCdigoCliente = new JLabel("C\u00F3digo Cliente");
		lblCdigoCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCdigoCliente.setBounds(10, 68, 127, 28);
		contentPane.add(lblCdigoCliente);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(10, 107, 127, 28);
		contentPane.add(lblNome);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereo.setBounds(10, 153, 127, 28);
		contentPane.add(lblEndereo);
		
		textField = new JTextField(String.valueOf(cfeb.getIdEndereco()));
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(163, 29, 310, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(String.valueOf(cfeb.getIdCliente()));
		textField_1.setEditable(false);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setBounds(163, 68, 310, 28);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(cfeb.getNomeCliente());
		textField_2.setEditable(false);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setBounds(162, 107, 311, 28);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField(cfeb.getEndereco());
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_3.setBounds(163, 153, 310, 28);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Obter dados
				BClienteFixoEnderecoBean cfeb = new BClienteFixoEnderecoBean();
				cfeb.setIdEndereco(Integer.parseInt(textField.getText()));
				cfeb.setIdCliente(Integer.parseInt(textField_1.getText()));
				cfeb.setEndereco(textField_3.getText());
				
				//Chamar a ação alterar
				BClienteFixoEnderecoDao cfed = new BClienteFixoEnderecoDao();
				cfed.alterar(cfeb);
				
				//Fechar formulário
				dispose();
				
				//Chamar formulário
				BEnderecosView ev = new BEnderecosView();
				ev.setVisible(true);
				
			}
		});
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAlterar.setBounds(10, 221, 127, 28);
		contentPane.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Obter dados
				int idCodigo = Integer.parseInt(textField.getText());
				
				//Chamar método para excluir
				BClienteFixoEnderecoDao cfed = new BClienteFixoEnderecoDao();
				cfed.deletarEnderecos(idCodigo);
				
				//Fechar formulário
				dispose();
				
				//Chamar formulário
				BEnderecosView ev = new BEnderecosView();
				ev.setVisible(true);
				
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExcluir.setBounds(183, 221, 127, 28);
		contentPane.add(btnExcluir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Fechar formulário
				dispose();
				
				//Chamar formulário
				BEnderecosView ev = new BEnderecosView();
				ev.setVisible(true);
				
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancelar.setBounds(351, 221, 122, 28);
		contentPane.add(btnCancelar);
		
	}

}