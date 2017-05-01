package dict;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Dictionary extends JFrame {

	private JPanel contentPane;
	private JTextField txtWord;
	private JTextField txtMeaning;
	private JTextField txtInsWord;
	private JTextField txtInsMeaning;
	private JButton btnInsert;
	private JLabel lblWord;
	private JLabel lblMeaning_1;

	Connection conn ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dictionary frame = new Dictionary();
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
	public Dictionary() {
		
		
		
		initialise();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtWord = new JTextField();
		txtWord.setBounds(29, 147, 135, 20);
		contentPane.add(txtWord);
		txtWord.setColumns(10);
		
		txtMeaning = new JTextField();
		txtMeaning.setBounds(122, 207, 135, 20);
		contentPane.add(txtMeaning);
		txtMeaning.setColumns(10);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String meaning =Trie.search(txtWord.getText());
				txtMeaning.setText(meaning);
				
				
				
			}
		});
		btnSearch.setBounds(197, 146, 89, 23);
		contentPane.add(btnSearch);
		
		JLabel lblMeaning = new JLabel("Search Result :");
		lblMeaning.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMeaning.setBounds(29, 196, 93, 38);
		contentPane.add(lblMeaning);
		
		txtInsWord = new JTextField();
		txtInsWord.setBounds(44, 47, 86, 20);
		contentPane.add(txtInsWord);
		txtInsWord.setColumns(10);
		
		txtInsMeaning = new JTextField();
		txtInsMeaning.setBounds(211, 47, 86, 20);
		contentPane.add(txtInsMeaning);
		txtInsMeaning.setColumns(10);
		
		btnInsert = new JButton("INSERT");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
				String word = txtInsWord.getText();
				String meaning = txtInsMeaning.getText();
				
				try {
					Statement st = conn.createStatement() ;
					String sql = "insert into tbl (word,meaning)values ('"+word+"','"+meaning+"') ";
					st.executeUpdate(sql);
					Trie.insert(word,meaning);
					JOptionPane.showMessageDialog(null, "Successfully inserted  :  "+word);
					txtInsMeaning.setText("");
					txtInsWord.setText("");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage() );
					e1.printStackTrace();
				}
				
			}
		});
		btnInsert.setBounds(322, 46, 89, 23);
		contentPane.add(btnInsert);
		
		lblWord = new JLabel("Word :");
		lblWord.setBounds(10, 50, 46, 14);
		contentPane.add(lblWord);
		
		lblMeaning_1 = new JLabel("Meaning :");
		lblMeaning_1.setBounds(158, 50, 67, 14);
		contentPane.add(lblMeaning_1);
	}

	
	
	
	public void initialise() {
		
		 conn = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Virendra\\Desktop\\vsr\\PROJECT\\dictionary.sqlite");
		    } catch ( Exception e ) {
		      System.err.println(e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Opened database successfully");
		    
		    String sql = "select * from tbl";
		    try {
		    	java.sql.Statement stmt = conn.createStatement();
		    	ResultSet rs = stmt.executeQuery(sql);
				while(rs.next())
				{	
					String word = rs.getString(1);
					String meaning = rs.getString(2);
					Trie.insert(word, meaning);
				}
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		
	}

	}
