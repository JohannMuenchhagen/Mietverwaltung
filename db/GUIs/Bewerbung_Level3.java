package GUIs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class Bewerbung_Level3 extends JFrame {

	private JPanel contentPaneB3;
	private JTextField txtFKundenIDB3;
	private JTextField txtFWohnungsIDB3;
	private JTextField txtFDatumB3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bewerbung_Level3 frame = new Bewerbung_Level3();
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
	public Bewerbung_Level3() {
		setTitle("Bewerbung hinzuf\u00FCgen");
		initComponents();
		createEvents();
	}

	////////////////////////////////////////////////////////////////////
	// Enth�lt den Code zum Erzeugen und
	// Initialisieren von Komponenten
	////////////////////////////////////////////////////////////////////
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 215);
		contentPaneB3 = new JPanel();
		contentPaneB3.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneB3);
		
		JButton btnZur�ckB3 = new JButton("Zum Bewerbungs-Men\u00FC");
		
		JLabel lblKundenIDB3 = new JLabel("Kunden-ID:");
		
		txtFKundenIDB3 = new JTextField();
		txtFKundenIDB3.setColumns(10);
		
		JLabel lblWohnungsIDB3 = new JLabel("Wohnungs-ID");
		
		txtFWohnungsIDB3 = new JTextField();
		txtFWohnungsIDB3.setColumns(10);
		
		JLabel lblDatumB3 = new JLabel("Datum:");
		
		txtFDatumB3 = new JTextField();
		txtFDatumB3.setHorizontalAlignment(SwingConstants.CENTER);
		txtFDatumB3.setColumns(10);
		
		JButton btnHinzuf�genB3 = new JButton("Bewerbung hinzuf\u00FCgen");
		
		JCheckBox czbStatusB3 = new JCheckBox("abgeschlossen");
		GroupLayout gl_contentPaneB3 = new GroupLayout(contentPaneB3);
		gl_contentPaneB3.setHorizontalGroup(
			gl_contentPaneB3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPaneB3.createSequentialGroup()
					.addComponent(btnZur�ckB3)
					.addContainerGap(308, Short.MAX_VALUE))
				.addGroup(gl_contentPaneB3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPaneB3.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDatumB3)
						.addComponent(lblWohnungsIDB3)
						.addComponent(lblKundenIDB3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPaneB3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPaneB3.createSequentialGroup()
							.addComponent(czbStatusB3, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 180, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPaneB3.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPaneB3.createParallelGroup(Alignment.LEADING)
								.addComponent(txtFWohnungsIDB3, 264, 279, Short.MAX_VALUE)
								.addComponent(txtFKundenIDB3, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
							.addGroup(gl_contentPaneB3.createSequentialGroup()
								.addComponent(txtFDatumB3, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addGap(193))))
					.addGap(95))
				.addGroup(gl_contentPaneB3.createSequentialGroup()
					.addGap(132)
					.addComponent(btnHinzuf�genB3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(169))
		);
		gl_contentPaneB3.setVerticalGroup(
			gl_contentPaneB3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPaneB3.createSequentialGroup()
					.addComponent(btnZur�ckB3)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPaneB3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKundenIDB3)
						.addComponent(txtFKundenIDB3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPaneB3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWohnungsIDB3)
						.addComponent(txtFWohnungsIDB3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPaneB3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDatumB3)
						.addComponent(txtFDatumB3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(czbStatusB3)
					.addPreferredGap(ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
					.addComponent(btnHinzuf�genB3)
					.addContainerGap())
		);
		contentPaneB3.setLayout(gl_contentPaneB3);
	}

	////////////////////////////////////////////////////////////////////
	// Enth�lt den Code zum Erzeugen von Events
	////////////////////////////////////////////////////////////////////
	
	private void createEvents() {
		// TODO Auto-generated method stub
		
	}
}