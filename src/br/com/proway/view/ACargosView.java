package br.com.proway.view;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ACargosView extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomecargo;
	private JTextField txtSalarioCargo;
	private JTable tblListarCursos;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField textDescontoSalario;


	public ACargosView() {
		
		//Objecto da classe CursosDao
		ACargosDao cd = new ACargosDao();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		JLabel lblNomeCurso = new JLabel("Nome do cargo");
		lblNomeCurso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeCurso.setBounds(10, 63, 124, 27);
		contentPane.add(lblNomeCurso);
		
		JLabel lblValorCurso = new JLabel("Salário do cargo");
		lblValorCurso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValorCurso.setBounds(10, 101, 147, 27);
		contentPane.add(lblValorCurso);
		
		JLabel lblNewLabel = new JLabel("Desconto salário");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 139, 150, 38);
		contentPane.add(lblNewLabel);
		
		txtNomecargo = new JTextField();
		txtNomecargo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNomecargo.setBounds(175, 61, 249, 33);
		contentPane.add(txtNomecargo);
		txtNomecargo.setColumns(10);
		
		txtSalarioCargo = new JTextField();
		txtSalarioCargo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSalarioCargo.setColumns(10);
		txtSalarioCargo.setBounds(175, 99, 249, 33);
		contentPane.add(txtSalarioCargo);
		
		textDescontoSalario = new JTextField();
		textDescontoSalario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textDescontoSalario.setBounds(175, 139, 249, 33);
		contentPane.add(textDescontoSalario);
		textDescontoSalario.setColumns(10);
			
		JButton btnCadastrarCurso = new JButton("Cadastrar Curso");
		btnCadastrarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				//Obter dados informados
				ACargosBean cb = new ACargosBean();
				cb.setNomeCargo(txtNomecargo.getText());
				cb.setValorSalario(Double.parseDouble(txtSalarioCargo.getText()));
				cb.setDescontoSalario(Double.parseDouble(textDescontoSalario.getText()));
				
				//Executar envio dos dados
				ACargosDao cd = new ACargosDao();
				cd.cadastrarCargo(cb);
				
				//Limpar campos
				txtNomecargo.setText("");
				txtSalarioCargo.setText("");
				textDescontoSalario.setText("");
				
				//Cursor no campo nome curso
				txtNomecargo.requestFocus();
				
				//Atualizar tabela
				table.setModel(cd.listarCursos());
				
				
			}
		});
		
		
		btnCadastrarCurso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCadastrarCurso.setBounds(123, 218, 181, 45);
		contentPane.add(btnCadastrarCurso);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 294, 414, 164);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setModel(cd.listarCursos());
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {

				//Obter linha
				int linha = table.getSelectedRow();
				
				//Obter dados
				ACargosBean cb = new ACargosBean();
				cb.setIdCargo(Integer.parseInt(table.getValueAt(linha, 0).toString()));
				cb.setNomeCargo(table.getValueAt(linha, 1).toString());
				cb.setValorSalario(Double.parseDouble(table.getValueAt(linha, 2).toString()));
				cb.setDescontoSalario(Double.parseDouble(table.getValueAt(linha, 3).toString()));
				
				//Chamar tabela
				AAlterarCargosView acv = new AAlterarCargosView(cb);
				
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








