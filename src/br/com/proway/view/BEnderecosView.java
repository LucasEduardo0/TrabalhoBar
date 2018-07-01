package br.com.proway.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.BClienteFixoEnderecoBean;
import br.com.proway.dao.BClienteFixoEnderecoDao;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class BEnderecosView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	public BEnderecosView() {
		
		//Objeto da classe ClienteFixoEnderecoDao
		BClienteFixoEnderecoDao cfed = new BClienteFixoEnderecoDao();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		JLabel lblCdigoCliente = new JLabel("C\u00F3digo Cliente");
		lblCdigoCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCdigoCliente.setBounds(10, 22, 110, 27);
		contentPane.add(lblCdigoCliente);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereo.setBounds(10, 60, 110, 27);
		contentPane.add(lblEndereo);
		
		textField = new JTextField();
		textField.setBounds(228, 62, 251, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(228, 24, 251, 27);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Obter dados informados 
				BClienteFixoEnderecoBean cfeb = new BClienteFixoEnderecoBean();
				cfeb.setIdCliente(Integer.parseInt(textField_1.getText()));
				cfeb.setEndereco(textField.getText());
				
				//Executar envio dos dados
				BClienteFixoEnderecoDao cfed = new BClienteFixoEnderecoDao();
				cfed.cadastrarCargo(cfeb);
				
				//Limpar dados
				textField.setText("");
				textField_1.setText("");
				
				//Cursor no campo idCliente
				textField_1	.requestFocus();
				
				//Atualizar tabela
				table.setModel(cfed.listarEndereco());
				
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCadastrar.setBounds(160, 133, 146, 38);
		contentPane.add(btnCadastrar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 200, 469, 146);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setModel(cfed.listarEndereco());
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {

				//Obter linha
				int linha = table.getSelectedRow();
				
				//Obter dados 
				BClienteFixoEnderecoBean cfeb = new BClienteFixoEnderecoBean();
				cfeb.setIdEndereco(Integer.parseInt(table.getValueAt(linha, 0).toString()));
				cfeb.setIdCliente(Integer.parseInt(table.getValueAt(linha, 1).toString()));
				cfeb.setNomeCliente(table.getValueAt(linha, 2).toString());
				cfeb.setEndereco(table.getValueAt(linha, 3).toString());
				
				//Fechar tabela
				dispose();
				
				//Chamar formulário
				BAlterarEnderecos ae = new BAlterarEnderecos(cfeb);
				ae.setVisible(true);
				
				
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