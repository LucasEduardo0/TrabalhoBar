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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class DFuncionariosView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	public DFuncionariosView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		
		DFuncionariosDao fd = new DFuncionariosDao();
		
		JLabel lblNomeFuncionrio = new JLabel("Nome Funcion\u00E1rio");
		lblNomeFuncionrio.setBounds(10, 24, 150, 24);
		contentPane.add(lblNomeFuncionrio);
		
		JLabel lblSexoFuncionrio = new JLabel("Sexo Funcion\u00E1rio");
		lblSexoFuncionrio.setBounds(10, 59, 150, 24);
		contentPane.add(lblSexoFuncionrio);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(10, 94, 150, 24);
		contentPane.add(lblCargo);
		
		JLabel lblTurno = new JLabel("Turno");
		lblTurno.setBounds(10, 129, 150, 24);
		contentPane.add(lblTurno);
		
		textField = new JTextField();
		textField.setBounds(170, 24, 241, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(170, 59, 241, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(170, 94, 241, 24);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(170, 129, 241, 24);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Obter os dados
				DFuncionariosBean fb = new DFuncionariosBean();
				fb.setNomeFuncionario(textField.getText());
				fb.setSexofuncionario(textField_1.getText());
				fb.setIdCargo(Integer.parseInt(textField_2.getText()));
				fb.setTurnos(textField_3.getText());
				
				//Executar envio dos dados
				DFuncionariosDao fd = new DFuncionariosDao();
				fd.cadastrarFuncionario(fb);
				
				//Limpar campos
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				
				//Cursor no primeiro campo
				textField.requestFocus();
				
				//Atualizar tabela
				table.setModel(fd.listarFuncionarios());
				
			}
		});
		btnCadastrar.setBounds(172, 182, 171, 24);
		contentPane.add(btnCadastrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 243, 519, 150);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(fd.listarFuncionarios());
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {

				//Obter linha
				int linha = table.getSelectedRow();
				
				//Obter dados
				DFuncionariosBean fb = new DFuncionariosBean();
				fb.setIdFuncionario(Integer.parseInt(table.getValueAt(linha, 0).toString()));
				fb.setNomeFuncionario(table.getValueAt(linha, 1).toString());
				fb.setSexofuncionario(table.getValueAt(linha, 2).toString());
				fb.setIdCargo(Integer.parseInt(table.getValueAt(linha, 3).toString()));
				fb.setTurnos(table.getValueAt(linha, 5).toString());
				
				//Fechar tabela
				dispose();
				
				//Chamar formulário
				DAlterarFuncionarioView afv = new DAlterarFuncionarioView(fb);
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
