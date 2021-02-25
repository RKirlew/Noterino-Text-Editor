import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
public class NoterinoDemo extends JFrame implements ActionListener{
	JTextArea t;
	
	JFrame f;
	JScrollPane taScroll;
	NoterinoDemo(){
		JTextField s= new JTextField();
		
		f= new JFrame("Noterino");
		t= new JTextArea();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s.setMaximumSize(new Dimension(30,30));
		this.setLayout(new BorderLayout());
		s.getDocument().addDocumentListener(new DocumentListener(){
			//Font fon = new Font(t.getFont().deriveFont(Integer.valueOf(t.getText())));
			@Override
			public void insertUpdate(DocumentEvent e) {
				float val= Float.valueOf(s.getText());
				t.setFont(t.getFont().deriveFont(val));
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				//t.setFont(t.getFont().deriveFont(Float.valueOf(t.getText())));

				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				//t.setFont(t.getFont().deriveFont(Float.valueOf(t.getText())));

				
			}
			
		});
		s.setVisible(true);
		
		
		//JScrollPane jsp = new JScrollPane(s);
		
		taScroll = new JScrollPane(t);
		JMenuBar mb= new JMenuBar();
		JMenu m1= new JMenu("File");
		JMenu m2= new JMenu("Editor Settings");
		JMenu m3= new JMenu("Edit");
		
		JMenuItem mi1 = new JMenuItem("New"); 
        JMenuItem mi2 = new JMenuItem("Open"); 
        JMenuItem mi3 = new JMenuItem("Save"); 
        
        JMenu m21 = new JMenu("Fonts"); 
        JMenuItem m2Tim = new JMenuItem("Times New Roman"); 

        JMenuItem m2Hel = new JMenuItem("Helvetica"); 
        JMenuItem m2Ar = new JMenuItem("Arial"); 
        JMenu m22 = new JMenu("Color"); 
        
        
        JMenuItem m31= new JMenuItem("Copy");
        JMenuItem m32= new JMenuItem("Paste");
        JMenuItem m33= new JMenuItem("Cut");
        
        JMenuItem bg= new JMenuItem("Background");
        JMenuItem fg= new JMenuItem("Font color");
        m1.add(mi1); 
        m1.add(mi2); 
        m1.add(mi3); 
        
        m2.add(m21);
        m2.add(m22); 
        
        
        
        m21.add(m2Tim);
        m21.add(m2Hel);
        m21.add(m2Ar);
        
       
        mi1.addActionListener(this); 
        mi2.addActionListener(this); 
        mi3.addActionListener(this); 
        
        m2Tim.addActionListener(this);
        m2Hel.addActionListener(this); 
        m2Ar.addActionListener(this);

        m22.addActionListener(this); 
        m22.add(bg);
        m22.add(fg);
        bg.addActionListener(this);
        fg.addActionListener(this);

        
        m31.addActionListener(this);
        m32.addActionListener(this);
        m33.addActionListener(this);

        m1.add(mi1); 
        m1.add(mi2); 
        m1.add(mi3); 
        
        m2.add(m21);
        m2.add(m22);
        
        m3.add(m31);
        m3.add(m32);
        m3.add(m33);
        
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(s);
      
        
        
        f.setJMenuBar(mb);
        
       
        f.setSize(500,500);
		f.setResizable(false);
		f.add(taScroll);
		
        f.setVisible(true);
        
        t.setColumns(100);
        t.setRows(100);
		t.setLineWrap(true);
		t.setWrapStyleWord(true);
       
		int def=t.getFont().getSize();
        s.setText(String.valueOf(def));
       
	}
	
	public static void main(String[] args) {
		NoterinoDemo n = new NoterinoDemo();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s= e.getActionCommand();
		if(s.equals("Open")) {
			JFileChooser fc= new JFileChooser("F:");
			int r= fc.showOpenDialog(null);
			if(r==JFileChooser.APPROVE_OPTION) {
				File f1= new File(fc.getSelectedFile().getAbsolutePath());
				try {
					String s1= "", s2="";
					FileReader fr= new FileReader(f1);
					BufferedReader br = new BufferedReader(fr);
					s2=br.readLine();
					while((s1=br.readLine())!=null) {
						s2=s2+"\n"+s1;
					}
					t.setText(s2);
				}
				catch(Exception evt) {
					JOptionPane.showMessageDialog(f, evt.getMessage());
				}
			}
		}else if(s.equals("New")) {
			 t.setText(""); 
		}
		else  if (s.equals("Cut")) { 
            t.cut(); 
        } 
		else if (s.equals("Copy")) { 
	            t.copy(); 
	        } 
	    else if (s.equals("Paste")) { 
	            t.paste(); 
	        } 
		else if(s.equals("Save")) {
				JFileChooser j = new JFileChooser("f:"); 
			 	int r = j.showSaveDialog(null); 
	  
	            if (r == JFileChooser.APPROVE_OPTION) { 
	 
	                File fi = new File(j.getSelectedFile().getAbsolutePath()); 
	                try { 
	                  
	                    FileWriter wr = new FileWriter(fi, false); 
	                    BufferedWriter w = new BufferedWriter(wr); 
	   
	                    w.write(t.getText()); 
	  
	                    w.flush(); 
	                    w.close(); 
	                } 
	                catch (Exception evt) { 
	                    JOptionPane.showMessageDialog(f, evt.getMessage()); 
	                } 
	            }
		}
		else if(s.equals("Helvetica")) {
			t.setFont(new Font("Helvetica", 2, 12));
		}
		else if(s.equals("Arial")) {
			t.setFont(new Font("Arial", 2, 12));
		}
		else if(s.equals("Times New Roman")) {
			t.setFont(new Font("Times New Roman", 2, 12));
		}else if (s.equals("Font color")) {
			JColorChooser colorChooser= new JColorChooser();
			Color color= colorChooser.showDialog(null, "Choose a color", Color.BLACK);
			t.setForeground(color);
		}
		else if (s.equals("Background")) {
			JColorChooser colorChooser= new JColorChooser();
			Color color= colorChooser.showDialog(null, "Choose a color", Color.BLACK);
			t.setBackground(color);
		}
	}

}
