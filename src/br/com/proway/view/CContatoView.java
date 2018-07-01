package br.com.proway.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.CClienteFixoContatoBean;
import br.com.proway.dao.CClienteFixoContatoDao;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.CyclicBarrier;
import java.awt.event.ActionEvent;

public class CContatoView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	public CContatoView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		CClienteFixoContatoDao cfcd = new CClienteFixoContatoDao();
		
		JLabel lblNomeCliente = new JLabel("Nome Cliente");
		lblNomeCliente.setBounds(10, 34, 99, 22);
		contentPane.add(lblNomeCliente);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 67, 99, 22);
		contentPane.add(lblTelefone);
		
		textField = new JTextField();
		textField.setBounds(145, 35, 174, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(145, 68, 174, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obter dados
				CClienteFixoContatoBean cfcb = new CClienteFixoContatoBean();
				cfcb.setNomeCliente(textField.getText());
				cfcb.setTelefone(Integer.parseInt(textField_1.getText()));
				
				//Executar envio dos dados
				CClienteFixoContatoDao cfcd = new CClienteFixoContatoDao();
				cfcd.cadastrarContato(cfcb);
				
				//Limpar dados
				textField.setText("");
				textField_1.setText("");
				
				//Cursor no campo idCliente
				textField.requestFocus();
				
				//Atualizar tabela
				table.setModel(cfcd.listarContatos());
				
			}
		});
		btnCadastrar.setBounds(123, 107, 164, 32);
		contentPane.add(btnCadastrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 167, 414, 147);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(cfcd.listarContatos());
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {

				//Obter linha
				int linha = table.getSelectedRow();
				
				//Obter dados
				CClienteFixoContatoBean cfcb = new CClienteFixoContatoBean();
				cfcb.setIdCliente(Integer.parseInt(table.getValueAt(linha, 0).toString()));
				cfcb.setNomeCliente(table.getValueAt(linha, 1).toString());
				cfcb.setTelefone(Integer.parseInt(table.getValueAt(linha, 2).toString()));
				
				//Chamar tabela
				CAlterarContatoView acv = new CAlterarContatoView(cfcb);
				acv.setVisible(true);
				acv.setLocationRelativeTo(null);
				
				//Fechar tabela
				dispose();
				
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
