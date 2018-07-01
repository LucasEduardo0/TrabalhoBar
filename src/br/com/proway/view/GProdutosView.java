package br.com.proway.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.GProdutosBean;
import br.com.proway.dao.GProdutosDao;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class GProdutosView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	public GProdutosView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		GProdutosDao pd = new GProdutosDao();
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setBounds(10, 36, 91, 23);
		contentPane.add(lblProduto);
		
		JLabel lblCdigoMarca = new JLabel("C\u00F3digo Marca");
		lblCdigoMarca.setBounds(10, 70, 91, 23);
		contentPane.add(lblCdigoMarca);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(10, 104, 91, 23);
		contentPane.add(lblValor);
		
		textField = new JTextField();
		textField.setBounds(143, 37, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(143, 71, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(143, 105, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obter dados
				GProdutosBean pb = new GProdutosBean();
				pb.setNomeProduto(textField.getText());
				pb.setIdMarca(Integer.parseInt(textField_1.getText()));
				pb.setValorProduto(Double.parseDouble(textField_2.getText()));
				
				//Chamar ação
				pd.cadastrarProduto(pb);
				
				//Limpar campos
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				
				//Cursor no primeiro campo
				textField.requestFocus();
				
				//Atualizar tabela
				table.setModel(pd.listarProdutos());
				
			}
		});
		btnCadastrar.setBounds(108, 160, 89, 23);
		contentPane.add(btnCadastrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 224, 414, 99);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(pd.listarProdutos());
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {

				//Obter linha
				int linha = table.getSelectedRow();
				
				//Obter dados
				GProdutosBean pb = new GProdutosBean();
				pb.setIdProduto(Integer.parseInt(table.getValueAt(linha, 0).toString()));
				pb.setNomeProduto(table.getValueAt(linha, 1).toString());
				pb.setIdMarca(Integer.parseInt(table.getValueAt(linha, 2).toString()));
				pb.setValorProduto(Double.parseDouble(table.getValueAt(linha, 4).toString()));
				
				//Fechar tabela
				dispose();
				
				//Chamar
				GAlterarProdutoView apv = new GAlterarProdutoView(pb);
				
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
