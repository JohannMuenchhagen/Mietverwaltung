package GUIs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();
	private JButton btnWohnungHM;
	private JButton btnBewerbungHM;
	private JButton btnVertragHM;
	private JButton btnKontaktpunktHM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setTitle("Hauptmen\u00FC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnWohnungHM = new JButton("Wohnung");
		
		
		btnBewerbungHM = new JButton("Bewerbung");
		
		
		btnVertragHM = new JButton("Vertrag");
	
		
		
		btnKontaktpunktHM = new JButton("Kontaktpunkt");
	
		
		JButton btnKundeHM = new JButton("Kunde");
		btnKundeHM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//dann �ffnet sich die Kundenseite auf dem 1. Level
			}
		});
		
		btnWohnungHM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//dann �ffnet sich die Wohnungsseite auf dem 1. Level
			}
		});
		
		btnBewerbungHM.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//dann �ffnet sich die Bewerbungseite auf dem 1. Level
			}
		});
		
		btnVertragHM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//dann �ffnet sich die Vertragseite auf dem 1. Level
			}
		});
		
		btnKontaktpunktHM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//dann �ffnet sich die Kontaktseite auf dem 1. Level
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnKundeHM)
						.addComponent(btnKontaktpunktHM)
						.addComponent(btnVertragHM)
						.addComponent(btnWohnungHM)
						.addComponent(btnBewerbungHM))
					.addContainerGap(292, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(60)
					.addComponent(btnKundeHM)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnWohnungHM)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBewerbungHM)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVertragHM)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnKontaktpunktHM)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}