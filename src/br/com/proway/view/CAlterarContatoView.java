package br.com.proway.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.CClienteFixoContatoBean;
import br.com.proway.dao.CClienteFixoContatoDao;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CAlterarContatoView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public CAlterarContatoView(CClienteFixoContatoBean cfcb) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo ");
		lblCdigo.setBounds(10, 25, 120, 26);
		contentPane.add(lblCdigo);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 62, 120, 26);
		contentPane.add(lblNome);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 99, 120, 26);
		contentPane.add(lblTelefone);
		
		textField = new JTextField(String.valueOf(cfcb.getIdCliente()));
		textField.setEditable(false);
		textField.setBounds(159, 25, 203, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(cfcb.getNomeCliente());
		textField_1.setBounds(159, 62, 203, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(String.valueOf(cfcb.getTelefone()));
		textField_2.setBounds(159, 99, 203, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obter dados
				CClienteFixoContatoBean cfcb = new CClienteFixoContatoBean();
				cfcb.setIdCliente(Integer.parseInt(textField.getText()));
				cfcb.setNomeCliente(textField_1.getText());
				cfcb.setTelefone(Integer.parseInt(textField_2.getText()));
				
				//Chamar a ação alterar
				CClienteFixoContatoDao cfcd = new CClienteFixoContatoDao();
				cfcd.alterar(cfcb);
				
				//Fechar formulário
				dispose();
				
				//Chamar formulario
				CContatoView cv = new CContatoView();
				cv.setVisible(true);
				
			}
		});
		btnAlterar.setBounds(0, 152, 142, 31);
		contentPane.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obter dados
				int idCodigo = Integer.parseInt(textField.getText());
				
				//Chamar método para excluir
				CClienteFixoContatoDao cfcd = new CClienteFixoContatoDao();
				cfcd.deletarContatos(idCodigo);
				
				//Fechar formulário
				dispose();
				
				//Chamar formulario
				CContatoView cv = new CContatoView();
				cv.setVisible(true);
				
			}
		});
		btnDeletar.setBounds(140, 152, 147, 31);
		contentPane.add(btnDeletar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Fechar formulário
				dispose();
				
				//Chamar formulario
				CContatoView cv = new CContatoView();
				cv.setVisible(true);
				
			}
		});
		btnCancelar.setBounds(282, 152, 142, 31);
		contentPane.add(btnCancelar);
	}
}
