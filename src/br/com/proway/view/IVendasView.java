package br.com.proway.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.IVendasBean;
import br.com.proway.dao.IVendasDao;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class IVendasView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JButton btnCadastrar;

	public IVendasView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		IVendasDao vd = new IVendasDao();
		
		JLabel lblCdigoProduto = new JLabel("C\u00F3digo Produto ");
		lblCdigoProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCdigoProduto.setBounds(26, 25, 138, 29);
		contentPane.add(lblCdigoProduto);
		
		JLabel lblCdigoFuncionrio = new JLabel("C\u00F3digo Funcion\u00E1rio");
		lblCdigoFuncionrio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCdigoFuncionrio.setBounds(26, 65, 138, 30);
		contentPane.add(lblCdigoFuncionrio);
		
		JLabel lblCdigoCliente = new JLabel("C\u00F3digo Cliente");
		lblCdigoCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCdigoCliente.setBounds(26, 106, 138, 29);
		contentPane.add(lblCdigoCliente);
		
		textField = new JTextField();
		textField.setBounds(285, 25, 206, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(285, 68, 206, 29);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(285, 106, 206, 32);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obter dados
				IVendasBean vb = new IVendasBean();
				vb.setIdProduto(Integer.parseInt(textField.getText()));
				vb.setIdFuncionario(Integer.parseInt(textField_1.getText()));
				vb.setIdCliente(Integer.parseInt(textField_2.getText()));
				
				LocalDateTime tempo = LocalDateTime.now();
				int segundo = tempo.getSecond();
				int minuto = tempo.getMinute();
				int horas = tempo.getHour();
				String hora = horas+":"+minuto+":"+segundo;
				
				LocalDate datas = tempo.toLocalDate();
				
				String data = ""+datas;
				
				vb.setData(data);
				vb.setHora(hora);
				
				//Chamar ação
				vd.cadastrarVenda(vb);
				
				//Limpar campos
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				
				//Cursor no campo produto
				textField.requestFocus();
				
				//Atualizar tabela
				table.setModel(vd.listarVendas());
				
				
			}
		});
		btnCadastrar.setBounds(175, 155, 138, 42);
		contentPane.add(btnCadastrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 208, 639, 247);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(vd.listarVendas());
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {

				//Obter linha
				int linha = table.getSelectedRow();
				
				//Obter dados
				IVendasBean vb = new IVendasBean();
				vb.setIdVenda(Integer.parseInt(table.getValueAt(linha, 0).toString()));
				vb.setIdProduto(Integer.parseInt(table.getValueAt(linha, 1).toString()));
				vb.setIdFuncionario(Integer.parseInt(table.getValueAt(linha, 3).toString()));
				vb.setIdCliente(Integer.parseInt(table.getValueAt(linha, 7).toString()));
				
				//Fechar tabela
				dispose();
				
				//Chamar
				IAlterarVendasView avv = new IAlterarVendasView(vb);
				
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
