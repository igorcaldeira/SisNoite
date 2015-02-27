package conectaBanco;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.Toolkit;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTabbedPane;

public class formCurso extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtFiltroNome;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtCh;
	private JTextField txtNalunos;
	private JTabbedPane tabbedPane;
	private JButton btnEditar_Form;
	private JButton btnSalvar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formCurso frame = new formCurso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public formCurso() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(formCurso.class.getResource("/com/sun/java/swing/plaf/motif/icons/Warn.gif")));
		setTitle("Cursos - SisNoite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 551, 311);
		contentPane.add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Consulta", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 526, 172);
		panel_2.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "CH", "N. Alunos"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(43);
		table.getColumnModel().getColumn(1).setPreferredWidth(151);
		table.getColumnModel().getColumn(2).setPreferredWidth(39);
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("Nome:");
		label.setBounds(10, 23, 51, 14);
		panel_2.add(label);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tunga", Font.PLAIN, 18));
		
		txtFiltroNome = new JTextField();
		txtFiltroNome.setBounds(71, 19, 175, 20);
		panel_2.add(txtFiltroNome);
		txtFiltroNome.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(416, 11, 120, 37);
		panel_2.add(btnPesquisar);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				selecionaValores();
			
			}
		});
		btnPesquisar.setIcon(new ImageIcon(formCurso.class.getResource("/com/sun/java/swing/plaf/windows/icons/ListView.gif")));
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBounds(427, 245, 109, 27);
		panel_2.add(btnDeletar);
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				// Buscar o id da tabela
				int chave =  (Integer)table.getValueAt(table.getSelectedRow(), 0);
				
				try 
				{
					Curso c = new Curso();
					c.deletar(chave);
					
					selecionaValores();
					
				}
				//Tratamento da exceção
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar o(s) curso(s) desejado(s). \n\n Mais informações : \n\nErro: " + e.getClass() +"\n\n"+  e.getMessage());
				}
			
			}
		});
		btnDeletar.setIcon(new ImageIcon(formCurso.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
		
		JButton btnEditar_Consulta = new JButton("Editar");
		btnEditar_Consulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				// text box recebe o valor da linha selecionada na tabela
				txtId.setText(String.valueOf((Integer)table.getValueAt(table.getSelectedRow(), 0)));
				txtNome.setText((String)table.getValueAt(table.getSelectedRow(), 1));
				txtCh.setText(String.valueOf((Integer)table.getValueAt(table.getSelectedRow(), 2)));
				txtNalunos.setText(String.valueOf((Integer)table.getValueAt(table.getSelectedRow(), 3)));
				
				//muda a aba automaticamente
				tabbedPane.setSelectedIndex(1);
				
				//habilitar o botão editar
				btnEditar_Form.setEnabled(true);
				
				//desabilitar o botão salvar
				btnSalvar.setEnabled(false);
				
			}
		});
		btnEditar_Consulta.setBounds(308, 245, 109, 27);
		panel_2.add(btnEditar_Consulta);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Formul\u00E1rio", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("ID:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tunga", Font.PLAIN, 18));
		label_1.setBounds(45, 33, 55, 14);
		panel_1.add(label_1);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(110, 30, 139, 20);
		panel_1.add(txtId);
		
		JLabel label_2 = new JLabel("Nome:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tunga", Font.PLAIN, 18));
		label_2.setBounds(29, 74, 71, 14);
		panel_1.add(label_2);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(110, 71, 139, 20);
		panel_1.add(txtNome);
		
		JLabel label_3 = new JLabel("CH:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tunga", Font.PLAIN, 18));
		label_3.setBounds(316, 33, 58, 14);
		panel_1.add(label_3);
		
		txtCh = new JTextField();
		txtCh.setColumns(10);
		txtCh.setBounds(384, 30, 139, 20);
		panel_1.add(txtCh);
		
		JLabel label_4 = new JLabel("N. Alunos:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Tunga", Font.PLAIN, 18));
		label_4.setBounds(284, 74, 90, 14);
		panel_1.add(label_4);
		
		txtNalunos = new JTextField();
		txtNalunos.setColumns(10);
		txtNalunos.setBounds(384, 71, 139, 20);
		panel_1.add(txtNalunos);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try
				{
					//Criou um objeto do curso
					Curso c = new Curso();
					
					//Colocou as informações que o usuario
					//digitou dentro do objeto
					c.setId(Integer.parseInt(txtId.getText()));
					c.setNome(txtNome.getText());
					c.setDuracao(Integer.parseInt(txtCh.getText()));
					c.setN_alunos(Integer.parseInt(txtNalunos.getText()));
					
					//Cadastrou o objeto no banco
					c.cadastrar();

					//muda a aba automaticamente
					tabbedPane.setSelectedIndex(0);
				}
				//Tratamento da exceção
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir o curso desejado. \n\n Mais informações : \n\nErro: " + e1.getClass() +"\n\n"+  e1.getMessage());
				}
				
				selecionaValores();
			}
		});
		btnSalvar.setBounds(397, 238, 139, 34);
		panel_1.add(btnSalvar);
		
		btnEditar_Form = new JButton("Editar");
		btnEditar_Form.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					//Criou um objeto do curso
					Curso c = new Curso();
					
					//Colocou as informações que o usuario
					//digitou dentro do objeto
					c.setId(Integer.parseInt(txtId.getText()));
					c.setNome(txtNome.getText());
					c.setDuracao(Integer.parseInt(txtCh.getText()));
					c.setN_alunos(Integer.parseInt(txtNalunos.getText()));
					
					//Cadastrou o objeto no banco
					c.alterar();
				}
				//Tratamento da exceção
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir o curso desejado. \n\n Mais informações : \n\nErro: " + e.getClass() +"\n\n"+  e.getMessage());
				}
				
				//Atualiza valores da tabela
				selecionaValores();
				
				//muda a aba automaticamente
				tabbedPane.setSelectedIndex(0);

				//desabilitar o botão editar
				btnEditar_Form.setEnabled(false);
				
				//Habilitar o botão salvar
				btnSalvar.setEnabled(true);
				
				//Limpa o formulário
				txtId.setText("");
				txtNome.setText("");
				txtCh.setText("");
				txtNalunos.setText("");
			}
		});
		btnEditar_Form.setEnabled(false);
		btnEditar_Form.setBounds(248, 238, 139, 34);
		panel_1.add(btnEditar_Form);
		
	}
	
	public void selecionaValores() {

		try
		{
			
			//Busca a tabela
			DefaultTableModel modelo = (DefaultTableModel)table.getModel();
			
			//Limpa os valores da tabela
			while (modelo.getRowCount() != 0) {
				modelo.removeRow(0);
			}
			
			//Busca os cursos no banco de dados
			
			Curso curso = new Curso();
			
			ArrayList<Curso> cursos = curso.selecionar(txtFiltroNome.getText());
			
			for (Curso c : cursos) {

				int codigo = c.getId();
				String nome = c.getNome();
				int n_alunos = c.getN_alunos();
				int duracao = c.getDuracao();
				
				Object[] linha = {codigo, nome, n_alunos, duracao};
				
				//Adiciona cada linha a tabela
				modelo.addRow(linha);
			}
		
		}
		//Tratamento da exceção
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar o(s) curso(s) desejado(s). \n\n Mais informações : \n\nErro: " + e.getClass() +"\n\n"+  e.getMessage());
		}
	}
}




