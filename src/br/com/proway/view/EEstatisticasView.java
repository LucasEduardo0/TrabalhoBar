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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class EEstatisticasView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	public EEstatisticasView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		EEstatisticasDao ed = new EEstatisticasDao();
		
		JLabel lblCdigoFuncionrio = new JLabel("C\u00F3digo Funcion\u00E1rio");
		lblCdigoFuncionrio.setBounds(10, 11, 120, 24);
		contentPane.add(lblCdigoFuncionrio);
		
		JLabel lblQuantidadeVendida = new JLabel("Quantidade Vendida");
		lblQuantidadeVendida.setBounds(10, 61, 120, 24);
		contentPane.add(lblQuantidadeVendida);
		
		textField = new JTextField();
		textField.setBounds(163, 13, 135, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(163, 61, 135, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Obter dados
				EEstatisticasBean eb = new EEstatisticasBean();
				eb.setIdFuncionario(Integer.parseInt(textField.getText()));
				eb.setQuantidadeVendida(Integer.parseInt(textField_1.getText()));
				
				//Executar envio dos dados
				EEstatisticasDao ed = new EEstatisticasDao();
				ed.cadastrarEstatistica(eb);
				
				//Limpar campos
				textField.setText("");
				textField_1.setText("");
				
				//Cursor no primeiro campo
				textField.requestFocus();
				
				//Atualizar tabela
				table.setModel(ed.listarEstatisticas());
				
				
			}
		});
		btnCadastrar.setBounds(120, 109, 89, 23);
		contentPane.add(btnCadastrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 159, 414, 196);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(ed.listarEstatisticas());
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {

				//Obter linha
				int linha = table.getSelectedRow();
				
				//Obter dados
				EEstatisticasBean eb = new EEstatisticasBean();
				eb.setIdEstatistica(Integer.parseInt(table.getValueAt(linha, 0).toString()));
				eb.setIdFuncionario(Integer.parseInt(table.getValueAt(linha, 1).toString()));
				eb.setQuantidadeVendida(Integer.parseInt(table.getValueAt(linha, 3).toString()));
				
				//Fechar tabela
				dispose();
				
				//Chamar
				EAlterarEstatisticasView eav = new EAlterarEstatisticasView(eb);
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
