package br.com.proway.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.EEstatisticasBean;
import br.com.proway.dao.EEstatisticasDao;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EAlterarEstatisticasView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public EAlterarEstatisticasView(EEstatisticasBean eb) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(10, 11, 112, 25);
		contentPane.add(lblCdigo);
		
		JLabel lblCdigoFuncionrio = new JLabel("C\u00F3digo Funcion\u00E1rio");
		lblCdigoFuncionrio.setBounds(10, 47, 112, 25);
		contentPane.add(lblCdigoFuncionrio);
		
		JLabel lblQuantidadeVendida = new JLabel("Quantidade Vendida");
		lblQuantidadeVendida.setBounds(10, 83, 112, 25);
		contentPane.add(lblQuantidadeVendida);
		
		textField = new JTextField(String.valueOf(eb.getIdEstatistica()));
		textField.setEditable(false);
		textField.setBounds(199, 13, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(String.valueOf(eb.getIdFuncionario()));
		textField_1.setBounds(199, 49, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(String.valueOf(eb.getQuantidadeVendida()));
		textField_2.setBounds(199, 85, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Obter dados
				EEstatisticasBean eb = new EEstatisticasBean();
				eb.setIdEstatistica(Integer.parseInt(textField.getText()));
				eb.setIdFuncionario(Integer.parseInt(textField_1.getText()));
				eb.setQuantidadeVendida(Integer.parseInt(textField_2.getText()));
				
				//Chamar ação
				EEstatisticasDao ed = new EEstatisticasDao();
				ed.alterar(eb);
				
				//Fechar formulário
				dispose();
				
				//Chamar
				EEstatisticasView ev = new EEstatisticasView();
				
			}
		});
		btnAlterar.setBounds(10, 182, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Obter dados
				int idEstatisticas = Integer.parseInt(textField.getText());
				
				//Chamar método para excluir
				EEstatisticasDao ed = new EEstatisticasDao();
				ed.deletarEstatisticas(idEstatisticas);
				
				//Fechar formulário
				dispose();
				
				//Chamar
				EEstatisticasView ev = new EEstatisticasView();
				
			}
		});
		btnDeletar.setBounds(136, 182, 89, 23);
		contentPane.add(btnDeletar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Fechar formulário
				dispose();
				
				//Chamar
				EEstatisticasView ev = new EEstatisticasView();
			}
		});
		btnCancelar.setBounds(254, 182, 89, 23);
		contentPane.add(btnCancelar);
		setVisible(true);
	}

}
