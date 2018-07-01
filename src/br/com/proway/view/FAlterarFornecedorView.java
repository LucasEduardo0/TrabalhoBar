package br.com.proway.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.FFornecedorBean;
import br.com.proway.dao.FFornecedorDao;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FAlterarFornecedorView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	public FAlterarFornecedorView(FFornecedorBean fb) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(20, 48, 46, 14);
		contentPane.add(lblCdigo);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(20, 87, 46, 14);
		contentPane.add(lblMarca);
		
		textField = new JTextField(String.valueOf(fb.getIdMarca()));
		textField.setEditable(false);
		textField.setBounds(158, 45, 131, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(fb.getNomeMarca());
		textField_1.setBounds(158, 84, 131, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obter dados
				FFornecedorBean fb = new FFornecedorBean();
				fb.setIdMarca(Integer.parseInt(textField.getText()));
				fb.setNomeMarca(textField_1.getText());
				
				//Chamar ação
				FFornecedorDao fd = new FFornecedorDao();
				fd.alterar(fb);
				
				//Fechar formulário
				dispose();
				
				//Chamar
				FFornecedorView fv = new FFornecedorView();
				
			}
		});
		btnAlterar.setBounds(10, 130, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obter dados
				int idCodigo = Integer.parseInt(textField.getText());
				
				//Chamar método para excluir
				FFornecedorDao fd = new FFornecedorDao();
				fd.deletarFornecedorCursos(idCodigo);
				
				//Fechar formulário
				dispose();
				
				//Chamar
				FFornecedorView fv = new FFornecedorView();
				
			}
		});
		btnDeletar.setBounds(109, 130, 89, 23);
		contentPane.add(btnDeletar);
		
		JButton btnCanselar = new JButton("Canselar");
		btnCanselar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Fechar formulário
				dispose();
				
				//Chamar
				FFornecedorView fv = new FFornecedorView();
				
			}
		});
		btnCanselar.setBounds(208, 130, 89, 23);
		contentPane.add(btnCanselar);
	}

}
