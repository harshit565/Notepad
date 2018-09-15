import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.io.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;
class Notepad extends MouseAdapter implements ActionListener//,Adjustable
{
	String undoS1="",undoS2="",selectedFile="",selectedString="",remainingString="";
	JTextArea tf;
	JFrame f;
	JScrollPane sp;
	JMenuBar menubar;
	JMenu File,Edit,Format,View,Help;
	JMenuItem New,Open,Save,SaveAs,PageSetup,Print,Exit,Undo,Cut,Copy,Paste,Delete,Find,FindNext,Replace,GoTo,SelectAll,TimeDate,Font,About;
	JCheckBoxMenuItem WordWrap,StatusBar;
	JPopupMenu jp=new JPopupMenu("Pop");
	JMenuItem Undo2,Cut2,Copy2,Paste2,Delete2,SelectAll2;
	Notepad(String st)
	{
        f=new JFrame(st);
		Undo2=new JMenuItem("Undo");
		Cut2=new JMenuItem("Cut");
		Copy2=new JMenuItem("Copy");
		Paste2=new JMenuItem("Paste");
		Delete2=new JMenuItem("Delete");
		SelectAll2=new JMenuItem("Select All");
		jp.add(Undo2);
		jp.addSeparator();
		jp.add(Cut2);
		jp.add(Copy2);
		jp.add(Paste2);
		jp.add(Delete2);
		jp.addSeparator();
		jp.add(SelectAll2);
		Copy2.addActionListener(this);
		Paste2.addActionListener(this);
		Delete2.addActionListener(this);
		Cut2.addActionListener(this);
		SelectAll2.addActionListener(this);
		Undo2.addActionListener(this);
		JMenuBar menubar=new JMenuBar();
		New=new JMenuItem("New");
		Open=new JMenuItem("Open");
		Save=new JMenuItem("Save");
		SaveAs=new JMenuItem("Save As");
		PageSetup=new JMenuItem("Page Setup");
		Print=new JMenuItem("Print");
		Exit=new JMenuItem("Exit");
		File=new JMenu("File");
		File.add(New);
		File.add(Open);
		File.add(Save);
		File.add(SaveAs);
		File.addSeparator();
		File.add(PageSetup);
		File.add(Print);
		File.addSeparator();
		File.add(Exit);
		menubar.add(File);
		Undo=new JMenuItem("Undo",KeyEvent.VK_T);
		Cut=new JMenuItem("Cut");
		Copy=new JMenuItem("Copy");
		Paste=new JMenuItem("Paste");
		Delete=new JMenuItem("Delete");
		Find=new JMenuItem("Find");
		FindNext=new JMenuItem("Find Next");
		Replace=new JMenuItem("Replace");
		GoTo=new JMenuItem("GoTo");
		SelectAll=new JMenuItem("Select All");
		TimeDate=new JMenuItem("Time/Date");
		Edit=new JMenu("Edit");
		Edit.add(Undo);
		Edit.addSeparator();
		Edit.add(Cut);
		Edit.add(Copy);
		Edit.add(Paste);
		Edit.add(Delete);
		Edit.addSeparator();
		Edit.add(Find);
		Edit.add(FindNext);
		Edit.add(Replace);
		Edit.add(GoTo);
		Edit.addSeparator();
		Edit.add(SelectAll);
		Edit.add(TimeDate);
		menubar.add(Edit);
		WordWrap=new JCheckBoxMenuItem("Word Wrap");
		Font=new JMenuItem("Font");
		Format=new JMenu("Format");
		Format.add(WordWrap);
		Format.add(Font);
		menubar.add(Format);
		StatusBar=new JCheckBoxMenuItem("Status Bar");
		View=new JMenu("View");
		View.add(StatusBar);
		menubar.add(View);
		About=new JMenuItem("About");
		Help=new JMenu("Help");
		Help.add(About);
		menubar.add(Help);
		File.setMnemonic(KeyEvent.VK_F);
		Edit.setMnemonic(KeyEvent.VK_E);
		Format.setMnemonic(KeyEvent.VK_O);
		View.setMnemonic(KeyEvent.VK_V);
		Help.setMnemonic(KeyEvent.VK_H);
		KeyStroke ctrlXKeyStroke = KeyStroke.getKeyStroke("control X");
	    Cut.setAccelerator(ctrlXKeyStroke);
	    KeyStroke ctrlVKeyStroke = KeyStroke.getKeyStroke("control V");
	    Paste.setAccelerator(ctrlVKeyStroke);
	    KeyStroke ctrlZKeyStroke = KeyStroke.getKeyStroke("control Z");
	    Undo.setAccelerator(ctrlZKeyStroke);
	    KeyStroke ctrlSKeyStroke = KeyStroke.getKeyStroke("control S");
	    Save.setAccelerator(ctrlSKeyStroke);
	    KeyStroke ctrlCKeyStroke = KeyStroke.getKeyStroke("control C");
	    Copy.setAccelerator(ctrlCKeyStroke);
	    KeyStroke ctrlDKeyStroke = KeyStroke.getKeyStroke("control D");
	    Delete.setAccelerator(ctrlDKeyStroke);
		KeyStroke ctrlNKeyStroke = KeyStroke.getKeyStroke("control N");
	    New.setAccelerator(ctrlNKeyStroke);
	    KeyStroke ctrlOKeyStroke = KeyStroke.getKeyStroke("control O");
	    Open.setAccelerator(ctrlOKeyStroke);
	    KeyStroke ctrlPKeyStroke = KeyStroke.getKeyStroke("control P");
	    Print.setAccelerator(ctrlPKeyStroke);
	    KeyStroke ctrlFKeyStroke = KeyStroke.getKeyStroke("control F");
	    Find.setAccelerator(ctrlFKeyStroke);
		KeyStroke ctrlHKeyStroke = KeyStroke.getKeyStroke("control R");
	    Replace.setAccelerator(ctrlHKeyStroke);
	    KeyStroke ctrlGKeyStroke = KeyStroke.getKeyStroke("control G");
	    GoTo.setAccelerator(ctrlGKeyStroke);
	    KeyStroke ctrlAKeyStroke = KeyStroke.getKeyStroke("control A");
	    SelectAll.setAccelerator(ctrlAKeyStroke);
	    f.setJMenuBar(menubar);
		New.addActionListener(this);
		Open.addActionListener(this);
		Save.addActionListener(this);
		SaveAs.addActionListener(this);
		PageSetup.addActionListener(this);
		Print.addActionListener(this);
		Exit.addActionListener(this);
		Undo.addActionListener(this);
		Cut.addActionListener(this);
		Copy.addActionListener(this);
		Paste.addActionListener(this);
		Delete.addActionListener(this);
		Find.addActionListener(this);
		FindNext.addActionListener(this);
		Replace.addActionListener(this);
		GoTo.addActionListener(this);
		SelectAll.addActionListener(this);
		TimeDate.addActionListener(this);
		Font.addActionListener(this);
		About.addActionListener(this);
		WordWrap.addActionListener(this);
		StatusBar.addActionListener(this);
		tf=new JTextArea();
		tf.setBounds(0,0,2000,2000);
		tf.setVisible(true);
		tf.addMouseListener(this);
		tf.addKeyListener(new KeyListener() {

		    @Override
		    public void keyTyped(KeyEvent arg0) {

		    }

		    @Override
		    public void keyReleased(KeyEvent arg0) {
		        // TODO Auto-generated method stub

		    }
		    @Override
		    public void keyPressed(KeyEvent arg0) {
		    	if(WordWrap.isSelected()==false&&StatusBar.isSelected()==true)
				{
					int caretPos = tf.getCaretPosition();
					int rowNum = (caretPos == 0) ? 1 : 0;
					int offset=0;
					try {
						offset = Utilities.getRowStart(tf, caretPos);
					} catch (BadLocationException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					int colNum = caretPos - offset + 1;
					for (offset = caretPos; offset > 0;) {
					    try {
							offset = Utilities.getRowStart(tf, offset) - 1;
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    rowNum++;
					}
					System.out.println("Row: " + rowNum);
					System.out.println("Col: " + colNum);
				}
		    	
		    }
		});
		f.add(tf);
		sp = new JScrollPane (tf);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Font font = new Font("Arial", 0, 20);
		tf.setFont(font);
		f.add(sp);
		f.setSize(560,470);
		f.setLocation(400,150);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String... s)
	{
		new Notepad("Notepad");
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("New"))
		{
			f.setTitle("Notepad");
			tf.setText("");
		}
		if(e.getActionCommand().equals("Open"))
		{
			JFileChooser open = new JFileChooser();
			int option = open.showOpenDialog(tf);
			if (option == JFileChooser.APPROVE_OPTION) {
				this.tf.setText("");
				try {
					Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
					selectedFile=open.getSelectedFile().getPath();
					while (scan.hasNext()) 
						this.tf.append(scan.nextLine() + "\n");
					String openfilename=open.getSelectedFile().getName();
					String newTitle[]=openfilename.split("\\.");
					f.setTitle(newTitle[0]);
					scan.close();
					} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
		if(e.getActionCommand().equals("Save"))
		{
			if(f.getTitle()=="Notepad"){
			JFileChooser save = new JFileChooser();
			int option = save.showSaveDialog(tf);
			if (option == JFileChooser.APPROVE_OPTION) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
					out.write(this.tf.getText()); 
					out.close();
					selectedFile=save.getSelectedFile().getPath();
					System.out.println(selectedFile);
				} catch (Exception ex) { 
					System.out.println(ex.getMessage());
				}
			}}
			else
			{
					BufferedWriter out;
					try {
						out = new BufferedWriter(new FileWriter(selectedFile));
						out.write(this.tf.getText()); 
						out.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		}
		if(e.getActionCommand().equals("Save As"))
		{
			JFileChooser save = new JFileChooser();
			int option = save.showSaveDialog(tf);
			if (option == JFileChooser.APPROVE_OPTION) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
					out.write(this.tf.getText()); 
					out.close();
				} catch (Exception ex) { 
					System.out.println(ex.getMessage());
				}
			}
		}
		if(e.getActionCommand().equals("Page Setup"))
		{
			JOptionPane.showMessageDialog(f,"No Printer Found");
		}
		if(e.getActionCommand().equals("Print"))
		{
			JOptionPane.showMessageDialog(  
				    Notepad.this.f,  
				    "Get ur printer repaired first! It seems u dont have one!",  
				    "Bad Printer",  
				    JOptionPane.INFORMATION_MESSAGE  
				    );  
		}
		if(e.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}
		if(e.getActionCommand().equals("Undo"))
		{
			JOptionPane.showMessageDialog(f,"Undo By Yourself");			
		}
		if(e.getActionCommand().equals("Cut"))
		{
			tf.cut();
		}
		if(e.getActionCommand().equals("Copy"))
		{
            tf.copy();
		}
		if(e.getActionCommand().equals("Paste"))
		{
			tf.paste();
 		}
		if(e.getActionCommand().equals("Delete"))
		{
			tf.replaceSelection("");  
 		}
		if(e.getActionCommand().equals("Find"))
		{
			int size=0;
			String selectedString=JOptionPane.showInputDialog(f,"Enter the Line:",""+"find");  
			size=selectedString.length();
			String remainingString=tf.getText();
			tf.select(remainingString.indexOf(selectedString),remainingString.indexOf(selectedString)+selectedString.length());
		}
		if(e.getActionCommand().equals("Find Next"))
		{
			JOptionPane.showMessageDialog(f,"Find By Yourself");
 		}
		if(e.getActionCommand().equals("Replace"))
		{
			String selectedString=JOptionPane.showInputDialog(f,"Replace:",""+"replace");
			String replacedString=JOptionPane.showInputDialog(f,"Replace With",""+"??");
			String str=tf.getText();
			str=str.replace(selectedString,replacedString);
			tf.setText(str);
 		}
		if(e.getActionCommand().equals("GoTo"))
		{
			goTo();
 		}
		if(e.getActionCommand().equals("Select All"))
		{
            tf.selectAll();
		}
		if(e.getActionCommand().equals("Time/Date"))
		{
			Date date=new Date();
			DateFormat dateFormat = new SimpleDateFormat("hh:mm a dd-MMMM-yyyy");
            String strDate = dateFormat.format(date);
            tf.setText(tf.getText()+strDate);
            System.out.println("Date converted to String: " + strDate);
		}
		if(e.getActionCommand().equals("Font"))
		{
			JOptionPane.showMessageDialog(f,"Font Having Error");
		}
		if(e.getActionCommand().equals("About"))
		{
			//new Developer();
			JOptionPane.showMessageDialog(f,"Developer  ::  Prabhav Garg");
		}
		if(WordWrap.isSelected())
		{
			tf.setLineWrap(true);
		}
		else
		{
			tf.setLineWrap(false);
		}
		if(WordWrap.isSelected()==false&&StatusBar.isSelected()==true)
		{
			sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			int caretPos = tf.getCaretPosition();
			int rowNum = (caretPos == 0) ? 1 : 0;
			int offset=0;
			try {
				offset = Utilities.getRowStart(tf, caretPos);
			} catch (BadLocationException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			int colNum = caretPos - offset + 1;
			for (offset = caretPos; offset > 0;) {
			    try {
					offset = Utilities.getRowStart(tf, offset) - 1;
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    rowNum++;
			}
			System.out.println("Row: " + rowNum);
			System.out.println("Col: " + colNum);
		}
		else
		{
			sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		}
	}
	void goTo()  
	{  
		int lineNumber=0;  
		try  
		{  
			lineNumber=tf.getLineOfOffset(tf.getCaretPosition())+1;  
			String tempStr=JOptionPane.showInputDialog(f,"Enter Line Number:",""+lineNumber);  
			if(tempStr==null)  
			{return;}
			lineNumber=Integer.parseInt(tempStr);  
			tf.setCaretPosition(tf.getLineStartOffset(lineNumber-1));  
		}catch(Exception e){}  
	}
	public void mouseClicked(MouseEvent e)
	{
		if(e.getButton()==MouseEvent.BUTTON3)
		{
			jp.show(e.getComponent(),e.getX(),e.getY());
			System.out.println(e.getX()+" "+e.getY());
		}
	}	
}