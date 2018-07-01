package br.com.proway.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.ACargosBean;
import br.com.proway.dao.ACargosDao;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AAlterarCargosView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public AAlterarCargosView(ACargosBean cb) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCdigo.setBounds(47, 24, 101, 22);
		contentPane.add(lblCdigo);
		
		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCurso.setBounds(47, 57, 101, 22);
		contentPane.add(lblCurso);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValor.setBounds(47, 90, 101, 22);
		contentPane.add(lblValor);
		
		JLabel lblDesconto = new JLabel("Desconto");
		lblDesconto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDesconto.setBounds(47, 123, 101, 22);
		contentPane.add(lblDesconto);
		
		textField = new JTextField(String.valueOf(cb.getIdCargo()));
		textField.setEditable(false);
		textField.setBounds(208, 27, 247, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(cb.getNomeCargo());
		textField_1.setBounds(208, 60, 247, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(String.valueOf(cb.getValorSalario()));
		textField_2.setBounds(208, 90, 247, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField(String.valueOf(cb.getDescontoSalario()));
		textField_3.setBounds(208, 123, 247, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Obter dados
				ACargosBean cb = new ACargosBean();
				cb.setIdCargo(Integer.parseInt(textField.getText()));
				cb.setNomeCargo(textField_1.getText());
				cb.setValorSalario(Double.parseDouble(textField_2.getText()));
				cb.setDescontoSalario(Double.parseDouble(textField_3.getText()));
				
				//Chamar a ação alterar
				ACargosDao cd = new ACargosDao();
				cd.alterar(cb);
				
				//Fechar formulário
				dispose();
				
				//Chamar formulario
				ACargosView cv = new ACargosView();
				cv.setVisible(true);
				
			}
		});
		btnAlterar.setBounds(10, 172, 138, 34);
		contentPane.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Obter dados
				int idCodigo = Integer.parseInt(textField.getText());
				
				
				//Chamar ação para excluir
				ACargosDao cd = new ACargosDao();
				cd.deletarCursos(idCodigo);
				
				//Fechar formulário
				dispose();
				
				//Chamar formulario
				ACargosView cv = new ACargosView();
				cv.setVisible(true);
				
			}
		});
		btnExcluir.setBounds(158, 172, 149, 34);
		contentPane.add(btnExcluir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Fechar formulário
				dispose();
				
				//Chamar tabela
				ACargosView cv = new ACargosView();
				cv.setVisible(true);
				
			}
		});
		btnCancelar.setBounds(317, 172, 138, 34);
		contentPane.add(btnCancelar);
		
		
	}

}
