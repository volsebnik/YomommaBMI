package YomommaBMI;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JDialog;
import java.awt.Rectangle;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import javax.swing.JTextField;
import javax.swing.JButton;

public class YOMOMMAGUI {

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu helpMenu = null;
	private JMenuItem aboutMenuItem = null;
	private JDialog aboutDialog = null;
	private JPanel aboutContentPane = null;
	private JLabel aboutVersionLabel = null;
	private JLabel glavnaLabela = null;
	private JLabel molbaLabela = null;
	private JLabel visinaLabel = null;
	private JTextField visinaTextField = null;
	private JLabel tezinaLabel = null;
	private JTextField tezinaTextField = null;
	private JButton dugme = null;
	private JLabel prikazLabela = null;
	
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setJMenuBar(getJJMenuBar());
			jFrame.setSize(506, 346);
			jFrame.setContentPane(getJContentPane());
			jFrame.setTitle("YomommaBMI");
		}
		return jFrame;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			prikazLabela = new JLabel();
			prikazLabela.setBounds(new Rectangle(11, 220, 472, 47));
			prikazLabela.setFont(new Font("Dialog", Font.BOLD, 12));
			prikazLabela.setText("");
			tezinaLabel = new JLabel();
			tezinaLabel.setBounds(new Rectangle(217, 116, 82, 23));
			tezinaLabel.setHorizontalAlignment(SwingConstants.CENTER);
			tezinaLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			tezinaLabel.setText("Tezina (kg)");
			visinaLabel = new JLabel();
			visinaLabel.setBounds(new Rectangle(13, 113, 84, 23));
			visinaLabel.setHorizontalAlignment(SwingConstants.CENTER);
			visinaLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			visinaLabel.setText("Visina (cm)");
			molbaLabela = new JLabel();
			molbaLabela.setBounds(new Rectangle(11, 72, 296, 27));
			molbaLabela.setHorizontalTextPosition(SwingConstants.CENTER);
			molbaLabela.setHorizontalAlignment(SwingConstants.CENTER);
			molbaLabela.setText("Molimo unesite parametre za pocetak");
			glavnaLabela = new JLabel();
			glavnaLabela.setBounds(new Rectangle(11, 16, 449, 45));
			glavnaLabela.setHorizontalAlignment(SwingConstants.CENTER);
			glavnaLabela.setHorizontalTextPosition(SwingConstants.CENTER);
			glavnaLabela.setFont(new Font("Dialog", Font.BOLD, 16));
			glavnaLabela.setText("Dobrodosli u najsmesniji BMI kalkulator na svetu");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(glavnaLabela, null);
			jContentPane.add(molbaLabela, null);
			jContentPane.add(visinaLabel, null);
			jContentPane.add(getVisinaTextField(), null);
			jContentPane.add(tezinaLabel, null);
			jContentPane.add(getTezinaTextField(), null);
			jContentPane.add(getDugme(), null);
			jContentPane.add(prikazLabela, null);
			jContentPane.add(getIzlazButton(), null);
		}
		return jContentPane;
	}

	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}


	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About");
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog aboutDialog = getAboutDialog();
					aboutDialog.pack();
					Point loc = getJFrame().getLocation();
					loc.translate(20, 20);
					aboutDialog.setLocation(loc);
					aboutDialog.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
	}

	private JDialog getAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = new JDialog(getJFrame(), true);
			aboutDialog.setTitle("YomommaBMI powered by Milos");
			aboutDialog.setContentPane(getAboutContentPane());
		}
		return aboutDialog;
	}

	private JPanel getAboutContentPane() {
		if (aboutContentPane == null) {
			aboutContentPane = new JPanel();
			aboutContentPane.setLayout(new BorderLayout());
			aboutContentPane.add(getAboutVersionLabel(), BorderLayout.CENTER);
		}
		return aboutContentPane;
	}

	private JLabel getAboutVersionLabel() {
		if (aboutVersionLabel == null) {
			aboutVersionLabel = new JLabel();
			aboutVersionLabel.setText("Verzija 1.0 Cilj programa nije vredjanje bilo cije mame, vec iskljicivo zabava.");
			aboutVersionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return aboutVersionLabel;
	}

	private JTextField getVisinaTextField() {
		if (visinaTextField == null) {
			visinaTextField = new JTextField();
			visinaTextField.setBounds(new Rectangle(131, 116, 42, 24));
		}
		return visinaTextField;
	}

	private JTextField getTezinaTextField() {
		if (tezinaTextField == null) {
			tezinaTextField = new JTextField();
			tezinaTextField.setBounds(new Rectangle(330, 117, 42, 24));
		}
		return tezinaTextField;
	}

	private JButton getDugme() {
		if (dugme == null) {
			dugme = new JButton();
			dugme.setBounds(new Rectangle(82, 160, 105, 47));
			dugme.setText("?");
			dugme.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (k==0) unesiIzFajla();
					k++;
					boolean b = proveraPolja();
					double bmi;
					if (b){ 
						bmi = izracunajBMI(Integer.parseInt(tezinaTextField.getText()), Integer.parseInt(visinaTextField.getText()));
					prikazLabela.setText(recenice[kategorije(bmi)]);	
					}
				}
			});
		}
		return dugme;
	}
	
	private JButton getIzlazButton() {
		if (izlazButton == null) {
			izlazButton = new JButton();
			izlazButton.setBounds(new Rectangle(259, 160, 105, 47));
			izlazButton.setText("IZLAZ");
			izlazButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});

		}
		return izlazButton;
	}

	static int k = 0;
	static String [] recenice = new String[55];
	static int kategorija;
	private JButton izlazButton = null;
	
    double izracunajBMI(double a, double b){
        return a/b/b*10000;
    }
    
    public static int kategorije(double x){
        Random r = new Random();
        if (x<16) kategorija=r.nextInt(8)+1;
        if ((x>=16)&&(x<20)) kategorija=r.nextInt(9)+9;
        if ((x>=20)&&(x<22.5)) kategorija=53;
        if ((x>=22.5)&&(x<30)) kategorija=r.nextInt(9)+17;        
        if ((x>=30)&&(x<35)) kategorija=r.nextInt(9)+26;
        if ((x>=35)&&(x<40)) kategorija=r.nextInt(9)+35;  
        if (x>40) kategorija=r.nextInt(9)+44;      
        return kategorija;
    }
    
	public boolean proveraPolja(){
		if(visinaTextField.getText().isEmpty() || tezinaTextField.getText().isEmpty()){ 
			JOptionPane.showMessageDialog(jFrame, "Molimo unesite parametre");
			return false;
		}
		return true;
		
	}
    
    public static void unesiIzFajla(){
        int i=0;
                try{        
            BufferedReader in =
                    new BufferedReader (new FileReader("Yomomma.txt"));
        boolean kraj = false;

        while (!kraj){
        String pom = in.readLine();
        if (pom == null) kraj = true;
        else {recenice[i]=pom;
              i++;
            }
        }
        in.close();
        }catch(Exception e){
        System.out.println("Greska: "+e.getMessage());
        }
    }


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				YOMOMMAGUI application = new YOMOMMAGUI();
				application.getJFrame().setVisible(true);
				
			}
		});
	}

}
