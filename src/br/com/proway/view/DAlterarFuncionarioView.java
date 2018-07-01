package br.com.proway.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.DFuncionariosBean;
import br.com.proway.dao.DFuncionariosDao;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DAlterarFuncionarioView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public DAlterarFuncionarioView(DFuncionariosBean fb) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		JLabel lblCdigoFuncionrio = new JLabel("C\u00F3digo Funcion\u00E1rio");
		lblCdigoFuncionrio.setBounds(10, 31, 155, 24);
		contentPane.add(lblCdigoFuncionrio);
		
		JLabel lblNomeFuncionrio = new JLabel("Nome Funcion\u00E1rio");
		lblNomeFuncionrio.setBounds(10, 66, 155, 24);
		contentPane.add(lblNomeFuncionrio);
		
		JLabel lblSexoFuncionrio = new JLabel("Sexo Funcion\u00E1rio");
		lblSexoFuncionrio.setBounds(10, 101, 155, 24);
		contentPane.add(lblSexoFuncionrio);
		
		JLabel lblCdigoCargo = new JLabel("C\u00F3digo Cargo");
		lblCdigoCargo.setBounds(10, 136, 155, 24);
		contentPane.add(lblCdigoCargo);
		
		JLabel lblTurno = new JLabel("Turno");
		lblTurno.setBounds(10, 171, 155, 24);
		contentPane.add(lblTurno);
		
		textField = new JTextField(String.valueOf(fb.getIdFuncionario()));
		textField.setEditable(false);
		textField.setBounds(202, 31, 216, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(fb.getNomeFuncionario());
		textField_1.setBounds(202, 66, 216, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(fb.getSexofuncionario());
		textField_2.setBounds(202, 101, 217, 24);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField(String.valueOf(fb.getIdCargo()));
		textField_3.setBounds(202, 136, 216, 24);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField(fb.getTurnos());
		textField_4.setBounds(202, 171, 216, 24);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Obter dados
				DFuncionariosBean fb = new DFuncionariosBean();
				fb.setIdFuncionario(Integer.parseInt(textField.getText()));
				fb.setNomeFuncionario(textField_1.getText());
				fb.setSexofuncionario(textField_2.getText());
				fb.setIdCargo(Integer.parseInt(textField_3.getText()));
				fb.setTurnos(textField_4.getText());
				
				//Chamar a ação alterar
				DFuncionariosDao fd = new DFuncionariosDao();
				fd.alterar(fb);
				
				//Fechar formulário
				dispose();
				
				//Chamar frmulário
				DFuncionariosView fv = new DFuncionariosView();
				
			}
		});
		btnAlterar.setBounds(10, 243, 130, 30);
		contentPane.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Obter dados
				int idFuncionario = Integer.parseInt(textField.getText());
				
				//Chamar método para excluir
				DFuncionariosDao fd = new DFuncionariosDao();
				fd.deletarFuncionario(idFuncionario);
				
				//Fechar formulário
				dispose();
				
				//Chamar formulário
				DFuncionariosView fv = new DFuncionariosView();
				
			}
		});
		btnDeletar.setBounds(170, 247, 155, 26);
		contentPane.add(btnDeletar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Fechar formulário
				dispose();
				
				//Chamar frmulário
				DFuncionariosView fv = new DFuncionariosView();
				
			}
		});
		btnCancelar.setBounds(371, 247, 112, 30);
		contentPane.add(btnCancelar);
		setVisible(true);
		setLocationRelativeTo(null);
		
		
	}
}
