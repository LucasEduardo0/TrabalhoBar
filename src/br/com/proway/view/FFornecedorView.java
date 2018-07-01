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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class FFornecedorView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	public FFornecedorView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		FFornecedorDao fd = new FFornecedorDao();
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(10, 46, 99, 23);
		contentPane.add(lblMarca);
		
		textField = new JTextField();
		textField.setBounds(107, 47, 131, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obter dados
				FFornecedorBean fb = new FFornecedorBean();
				fb.setNomeMarca(textField.getText());
				
				//Chamar ação
				fd.cadastrarFornecedor(fb);
				
				//Limpar campos
				textField.setText("");
				
				//Cursor no primeiro campo
				textField.requestFocus();
				
				//altualizar tabela
				table.setModel(fd.listarFornecedor());
				
			}
		});
		btnCadastrar.setBounds(43, 80, 89, 23);
		contentPane.add(btnCadastrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 114, 414, 227);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(fd.listarFornecedor());
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {

				//Obter linha
				int linha = table.getSelectedRow();
				
				//Obter dados
				FFornecedorBean fb = new FFornecedorBean();
				fb.setIdMarca(Integer.parseInt(table.getValueAt(linha, 0).toString()));
				fb.setNomeMarca(table.getValueAt(linha, 1).toString());
				
				//Fechar tabela
				dispose();
				
				//Chamar
				FAlterarFornecedorView afv = new FAlterarFornecedorView(fb);
				
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
