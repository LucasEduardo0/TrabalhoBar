package br.com.proway.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.HEstoqueBean;
import br.com.proway.dao.HEstoqueDao;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class HEstoqueView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	public HEstoqueView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		HEstoqueDao ed = new HEstoqueDao();
		
		JLabel lblCdigoProduto = new JLabel("C\u00F3digo Produto");
		lblCdigoProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCdigoProduto.setBounds(27, 30, 141, 30);
		contentPane.add(lblCdigoProduto);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQuantidade.setBounds(27, 71, 141, 30);
		contentPane.add(lblQuantidade);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(221, 30, 141, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setBounds(221, 71, 141, 27);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Obter dados
				HEstoqueBean eb = new HEstoqueBean();
				eb.setIdProduto(Integer.parseInt(textField.getText()));
				eb.setQuantidadeEstoque(Double.parseDouble(textField_1.getText()));
				
				//Chamar ação
				HEstoqueDao ed = new HEstoqueDao();
				ed.cadastrarEstoque(eb);
				
				//Limpar
				textField.setText("");
				textField_1.setText("");
				
				//Cursor no campo codigo
				textField.requestFocus();
				
				//Atualizar tabela
				table.setModel(ed.listarEstoque());
				
			}
		});
		btnCadastrar.setBounds(148, 112, 141, 34);
		contentPane.add(btnCadastrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 157, 556, 209);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(ed.listarEstoque());
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {

				//Obter linha
				int linha = table.getSelectedRow();
				
				//Obter dados
				HEstoqueBean eb = new HEstoqueBean();
				eb.setIdEstoque(Integer.parseInt(table.getValueAt(linha, 0).toString()));
				eb.setIdProduto(Integer.parseInt(table.getValueAt(linha, 1).toString()));
				eb.setQuantidadeEstoque(Double.parseDouble(table.getValueAt(linha, 3).toString()));
				
				//Fechar tabela
				dispose();
				
				//Chamar
				HAlterarEstoqueView aev = new HAlterarEstoqueView(eb);
			
				
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
