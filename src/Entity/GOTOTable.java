package Entity;

public class GOTOTable {
	private int state;
	private String input;
	private int nextState;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public int getNextState() {
		return nextState;
	}
	public void setNextState(int nextState) {
		this.nextState = nextState;
	}

}
/*
package ManagerGUI;

import book.Book;
import book.BorrowedBook;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.RowSorter;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;

import java.awt.Panel;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import java.awt.Toolkit;

import javax.swing.JRadioButton;
import javax.swing.JLabel;

import java.awt.ScrollPane;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import Common.BorrowInfo;
import Common.ConfmChange;
import Common.FailSearch;
import Common.Success;
import Common.AboutLibrary;
import Common.BorrowNotSucced;
import Common.BackupsInfo;
import Common.RestoreInfo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ButtonGroup;

import student.Student;
import student.StudentManage;
import book.BookManage;
import worker.Manager;
import worker.Worker;

import worker.Worker;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

import login.LoginDialog;

import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class ManagerGUI {
	//
	private String  selectedSex; //记录管理员性别
	private ManagerGUI mi = this; 
	private Manager manager;	//管理员类
	private BookManage bookManage;  //图书管理类
	private StudentManage studentManage; //学生管理类
	private String borrowBookName;  //登记借阅图书 模块 与BorrowInfo.java交互
	private Date returnDate;
	private Date loanDate;
	private DefaultTableModel regesiterTableModel;  //JtableModel
	private DefaultTableModel workerListTbModel;
	private DefaultTableModel stuListTbModel;
	private DefaultTableModel bookTableModel;
	private DefaultTableModel table0Model; 
	private Worker worker;
	private JFrame frame;
	private JTable workerListTb;
	private JTable bookTable;
	private JTable stuListTb;
	private JTextField idText;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable regesiterTable;
	private JTable table0;

	*//**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerGUI window = new ManagerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	*//**
	 * Create the application.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 *//*
	public ManagerGUI() throws FileNotFoundException, ClassNotFoundException, IOException {
		//
		manager = new Manager();
		studentManage = new StudentManage();
		//
		bookManage = new BookManage();
		initialize();
	}

	*//**
	 * Initialize the contents of the frame.
	 *//*
	
	*//**
	 * Create the application.
	 * @param magnager
	 *//*
	public ManagerGUI(Manager magnager) {
		this.manager = manager;
		initialize();
	}
	
	*//**
	 * 
	 * @param borrowBookName
	 *//*
	public void setBorrowBookName(String borrowBookName) {
		this.borrowBookName = borrowBookName;
	}
	
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public void setloanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	*//**
	 * 借阅登记方法，登记借阅图书 模块 与BorrowInfo.java交互
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * 
	 *//*
		public void BorrowedBook() throws ClassNotFoundException, IOException
		{
			if( !bookManage.subBookNumber(borrowBookName) )
			{
				new BorrowNotSucced().show();
				return;
			}
			BorrowedBook borrowedBook = bookManage.changeBookToBorrowedBooks(borrowBookName, loanDate,returnDate,0);
			BorrowedBook bBook = bookManage.changeBookToBorrowedBooks(borrowBookName, loanDate,returnDate,idText.getText());
			try {
				studentManage.addBorrowedBooks(idText.getText(),borrowedBook);
				regesiterTableModel.addRow(borrowedBook.toStrings());
				bookManage.regesiterBorrow(bBook);
				//System.out.println("借阅成功");
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	*//**
	 * initialize
	 *//*
	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u7BA1\u7406\u5458");
		frame.setBounds(100, 100, 700, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u83DC\u5355");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u6CE8\u9500");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LoginDialog dialog = new LoginDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e0) {
					e0.printStackTrace();
				}
				frame.dispose();
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u9000\u51FA");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(menuItem_1);
		
		JMenu menu_5 = new JMenu("\u5E2E\u52A9");
		menuBar.add(menu_5);
		
		JMenuItem mntmlibrary = new JMenuItem("\u5173\u4E8Elibrary");
		mntmlibrary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AboutLibrary().show();
			}
		});
		menu_5.add(mntmlibrary);
		frame.getContentPane().setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width - 700) / 2, (screenSize.height - 450) / 2);
		stuListTbModel = new DefaultTableModel(
				new Object[][] {},
				new String[] {
					"\u6237\u540D\u7528", "\u59D3\u540D", "\u6027\u522B", "\u5E74\u9F84", "\u7F16\u53F7"
				}
			);
		
		table0Model = new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"书名","书号","出版社","作者","标价","借阅者编号","借阅日期","应还日期","入库日期"
				}
			);
		
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBounds(116, 0, 570, 385);
		frame.getContentPane().add(welcomePanel);
		welcomePanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\黄家晏\\workspace\\LibrarySystem\\LibrarySystem\\book1.png"));
		lblNewLabel.setBounds(0, 0, 570, 385);
		welcomePanel.add(lblNewLabel);
		stuListTb = new JTable();
		stuListTb.setFillsViewportHeight(true);
		stuListTb.setModel(stuListTbModel);  
		
			final Panel manageStudents = new Panel();
			manageStudents.setLayout(null);
			manageStudents.setBounds(116, 0, 570, 385);
			frame.getContentPane().add(manageStudents);
			
			JButton AddStuButn = new JButton("\u5B66\u751F");
			AddStuButn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String stus[] = {"","","","",""};
					stuListTbModel.addRow(stus);
				}
			});
			AddStuButn.setBounds(10, 335, 140, 40);
			manageStudents.add(AddStuButn);
			
			JButton DeleStuButn = new JButton("\u5220\u9664\u5B66\u751F");
			DeleStuButn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selected = stuListTb.getSelectedRow();
					if (selected == -1) return;
					try {
						studentManage.deleteStudent((String)stuListTbModel.getValueAt(selected, 4));
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					stuListTbModel.removeRow(selected);
				}
			});
			DeleStuButn.setBounds(218, 335, 140, 40);
			manageStudents.add(DeleStuButn);
			
			JButton ChangInfoButn = new JButton("\u6DFB\u52A0");
			ChangInfoButn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selected = stuListTb.getSelectedRow();
					if(selected != -1)
					{
					try {
						studentManage.addStudent((String)stuListTbModel.getValueAt(selected, 0), (String)stuListTbModel.getValueAt(selected,1),
									(String)stuListTbModel.getValueAt(selected,2), (String)stuListTbModel.getValueAt(selected,3), "123",
									(String)stuListTbModel.getValueAt(selected, 4));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					new Success().show();
					}
				}
			});
			ChangInfoButn.setBounds(420, 335, 140, 40);
			manageStudents.add(ChangInfoButn);
			
			JScrollPane StuListSP = new JScrollPane();
			StuListSP.setBounds(10, 10, 550, 293);
			manageStudents.add(StuListSP);
			
			
			
			StuListSP.setViewportView(stuListTb);
		
		JPanel panel0 = new JPanel();
		panel0.setBounds(116, 0, 570, 385);
		frame.getContentPane().add(panel0);
		panel0.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setAutoscrolls(true);
		scrollPane_2.setBounds(10, 36, 560, 339);
		panel0.add(scrollPane_2);
		table0 = new JTable();
		table0.setFillsViewportHeight(true);
		table0.setModel(table0Model);
		table0.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_2.setViewportView(table0);
		
		final Panel regesiter = new Panel();
		regesiter.setLayout(null);
		regesiter.setBounds(116, 0, 570, 385);
		frame.getContentPane().add(regesiter);
		
		JLabel label = new JLabel("qq\u8BF7\u8F93\u5165\u8BFB\u8005\u8BC1\u53F7\uFF1A");
		label.setBounds(29, 20, 116, 15);
		regesiter.add(label);
		
		idText = new JTextField();
		idText.setColumns(10);
		idText.setBounds(155, 17, 229, 21);
		regesiter.add(idText);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setAutoscrolls(true);
		scrollPane_1.setBounds(10, 56, 550, 265);
		regesiter.add(scrollPane_1);
		
		regesiterTableModel = new DefaultTableModel(
				new Object[][] {},
					new String[] {
							"书名","书号","出版社","作者","标价","类别","借阅日期","应还日期","入库日期"
					}
				);
		
		regesiterTable = new JTable();
		regesiterTable.setModel(regesiterTableModel);
		regesiterTable.setFillsViewportHeight(true);
		regesiterTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setViewportView(regesiterTable);
		
		JButton button_4 = new JButton("qq\u501F\u4E66");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(studentManage.findStudentById(idText.getText()) == null) return;
				BorrowInfo borrowInfo = new BorrowInfo(mi);
				borrowInfo.show();
			}
		});
		button_4.setBounds(126, 330, 140, 40);
		regesiter.add(button_4);
		
		
		
		JButton button_5 = new JButton("ss");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = regesiterTable.getSelectedRow();//获得选中行的索引
				if(selectedRow == -1) return;
				try {
					bookManage.addBookNumber(borrowBookName);
				} catch (ClassNotFoundException | IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				BorrowedBook borrowedBook = new BorrowedBook(
						(String)regesiterTableModel.getValueAt(selectedRow, 1) , (String)regesiterTableModel.getValueAt(selectedRow, 0) ,
                		(String)regesiterTableModel.getValueAt(selectedRow, 3) , (String)regesiterTableModel.getValueAt(selectedRow, 2) ,
                		Integer.valueOf( (String)regesiterTableModel.getValueAt(selectedRow, 5) ),
                		loanDate,returnDate,
                		Float.valueOf( (String)regesiterTableModel.getValueAt(selectedRow, 4) ) );

				try {
					bookManage.deleteBorrowedBooks( idText.getText() ,(String)regesiterTableModel.getValueAt(selectedRow, 0)  );
					studentManage.deleteBorrowedBooks(idText.getText(),(String)regesiterTableModel.getValueAt(selectedRow, 0) );
					regesiterTableModel.removeRow(selectedRow);
					//System.out.println("还书成功");
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_5.setBounds(339, 330, 140, 40);
		regesiter.add(button_5);
		
		JButton button_3 = new JButton("22\u67E5\u8BE2");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = studentManage.findStudentById(idText.getText());
				if(student!=null)
				{
					regesiterTableModel = new DefaultTableModel(
							new Object[][] {},
								new String[] {
										"书名","书号","出版社","作者","标价","类别","借阅日期","应还日期","入库日期"
								}
							);
					regesiterTable.setModel(regesiterTableModel);
					for(BorrowedBook e2 : student.getBorrowedBooks())
					{
						if(e2 == null) continue;
						regesiterTableModel.addRow(e2.toStrings());
					}
				}
				else
					new FailSearch().show();
			}
		});
		button_3.setBounds(429, 16, 93, 23);
		regesiter.add(button_3);
		
		
		
		final Panel infopanel = new Panel();
		infopanel.setBounds(116, 0, 570, 385);
		frame.getContentPane().add(infopanel);
		infopanel.setLayout(null);
		
		TextField usNamText = new TextField();
		usNamText.setBounds(263, 133, 104, 23);
		infopanel.add(usNamText);
		
		Label namlabel = new Label(" a");
		namlabel.setBounds(129, 46, 69, 23);
		infopanel.add(namlabel);
		
		Label sexlabel = new Label("b\u6027\u522B\uFF1A");
		sexlabel.setBounds(129, 75, 69, 23);
		infopanel.add(sexlabel);
		
		Label agelabel = new Label("c\u5E74\u9F84\uFF1A");
		agelabel.setBounds(129, 104, 69, 23);
		infopanel.add(agelabel);
		
		TextField namTex = new TextField();
		namTex.setBounds(263, 46, 104, 23);
		infopanel.add(namTex);
		
		Label usNamlabel = new Label("d\u7528\u6237\u540D\uFF1A");
		usNamlabel.setBounds(129, 133, 69, 23);
		infopanel.add(usNamlabel);
		
		TextField ageTex = new TextField();
		ageTex.setBounds(263, 104, 104, 23);
		infopanel.add(ageTex);
		
		JRadioButton rbman = new JRadioButton("\u7537");
		rbman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedSex = "男";
			}
		});
		buttonGroup.add(rbman);
		rbman.setBounds(263, 75, 59, 23);
		infopanel.add(rbman);
		
		JRadioButton rbwoman = new JRadioButton("\u5973");
		rbwoman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedSex = "女";
			}
		});
		buttonGroup.add(rbwoman);
		rbwoman.setBounds(324, 75, 43, 23);
		infopanel.add(rbwoman);
		
		TextField passwordTex = new TextField();
		passwordTex.setBounds(263, 162, 104, 23);
		passwordTex.setEchoChar('*');
		infopanel.add(passwordTex);
		
		Button confmChang = new Button("11\u786E\u8BA4\u4FEE\u6539");
		confmChang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//修改管理员信息
				try {
					manager.modifyManager(usNamText.getText(), namTex.getText(), selectedSex, ageTex.getText(), passwordTex.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				ConfmChange bconfmChang = new ConfmChange();
				bconfmChang.show();
				
			}
		});
		confmChang.setBounds(207, 249, 76, 23);
		infopanel.add(confmChang);
		
		JLabel Passwordlabel = new JLabel("qqq\u5BC6\u7801\uFF1A");
		Passwordlabel.setFont(new Font("宋体", Font.PLAIN, 12));
		Passwordlabel.setBounds(129, 170, 54, 15);
		infopanel.add(Passwordlabel);
		
		final Panel manageworker = new Panel();
		manageworker.setBounds(116, 0, 570, 385);
		frame.getContentPane().add(manageworker);
		manageworker.setLayout(null);
		
	    workerListTbModel = new DefaultTableModel(
				new Object[][] {},
				new String[] {
					" aa", "\u59D3\u540D", "\u6027\u522B", "\u5E74\u9F84", "\u7F16\u53F7"
				}
			);
		
		JButton AddWorkerButn = new JButton("添加工作人员");
		AddWorkerButn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] s = {"","","","",""};
				workerListTbModel.addRow(s);
			}
		});
		AddWorkerButn.setBounds(10, 335, 140, 40);
		manageworker.add(AddWorkerButn);
		
		JButton DeleWorkButn = new JButton("\u5220\u9664\u5DE5\u4F5C\u4EBA\u5458");
		DeleWorkButn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = workerListTb.getSelectedRow();
				if(selected == -1) return;
				try {
					manager.deleteWorker((String)workerListTbModel.getValueAt(selected,1));
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				workerListTbModel.removeRow(selected);
			}
		});
		DeleWorkButn.setBounds(224, 335, 140, 40);
		manageworker.add(DeleWorkButn);
		
		JButton ChangWorkInfo = new JButton("\u786E\u8BA4\u6DFB\u52A0");
		ChangWorkInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = workerListTb.getSelectedRow();
				if(selected == -1) return;
				try {
					manager.addWorker((String)workerListTbModel.getValueAt(selected, 0),(String) workerListTbModel.getValueAt(selected, 1),
							(String)workerListTbModel.getValueAt(selected,2), (String)workerListTbModel.getValueAt(selected,3), 
							"123" ,(String) workerListTbModel.getValueAt(selected,4));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new Success().show();
			}
		});
		ChangWorkInfo.setBounds(420, 335, 140, 40);
		manageworker.add(ChangWorkInfo);
		
		JScrollPane WorkerListSP = new JScrollPane();
		WorkerListSP.setBounds(10, 10, 550, 299);
		manageworker.add(WorkerListSP);
		
		workerListTb = new JTable();
		workerListTb.setFillsViewportHeight(true);
		
		workerListTb.setModel(workerListTbModel);
		WorkerListSP.setViewportView(workerListTb);
		
		final Panel manageBkinMng = new Panel();
		manageBkinMng.setLayout(null);
		manageBkinMng.setBounds(116, 0, 570, 385);
		frame.getContentPane().add(manageBkinMng);
		
	
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
				
			}
			@Override
			public void componentRemoved(ContainerEvent e) {
				
			}
		});
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 10, 550, 315);
		manageBkinMng.add(scrollPane);
		
		bookTableModel = new DefaultTableModel(
				new Object[][] {},
					new String[] {
						"书名","书号","出版社","作者","标价","类别","库存","总数量","入库日期"
					}
				);

		bookTable = new JTable();
		bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		bookTable.setFillsViewportHeight(true);
		bookTable.setModel(bookTableModel);
		scrollPane.setViewportView(bookTable);
		
		JButton button_1 = new JButton("\u5220\u9664\u56FE\u4E66");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = bookTable.getSelectedRow();//获得选中行的索引
                if(selectedRow!=-1)  //存在选中行
                {
                    try {
						bookManage.deleteBook((String)bookTableModel.getValueAt(selectedRow, 0));
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    bookTableModel.removeRow(selectedRow);  //删除行
                }
			}
		});
		button_1.setBounds(222, 335, 140, 40);
		manageBkinMng.add(button_1);
		
		JButton button_2 = new JButton("444\u786E\u8BA4\u6DFB\u52A0");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = bookTable.getSelectedRow();//获得选中行的索引
                if(selectedRow!= -1)   //是否存在选中行
                {
                    //修改指定的值：
                    System.out.println(bookTableModel.getValueAt(selectedRow, 0) );
                    Book book = new Book((String)bookTableModel.getValueAt(selectedRow, 1) , (String)bookTableModel.getValueAt(selectedRow, 0) ,
                    		(String)bookTableModel.getValueAt(selectedRow, 3) , (String)bookTableModel.getValueAt(selectedRow, 2) ,
                    		Integer.valueOf( (String)bookTableModel.getValueAt(selectedRow, 5) ),
                    		Integer.valueOf( (String)bookTableModel.getValueAt(selectedRow, 7) ),
                    		(String)bookTableModel.getValueAt(selectedRow, 8) ,
                    		Float.valueOf( (String)bookTableModel.getValueAt(selectedRow, 4) ) );
                    //table.setValueAt(arg0, arg1, arg2)
                    try {
                    	if( bookManage.searchByName((String)bookTableModel.getValueAt(selectedRow, 0)) == null )
						bookManage.addBook(book);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    
                    new Success().show();
                }
			}
		});
		button_2.setBounds(420, 335, 140, 40);
		manageBkinMng.add(button_2);
		
		JButton addBookButton = new JButton("54\u589E\u52A0\u56FE\u4E66");
		addBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String []	rowValues = {"1","2","3","4","5"};
				String []	rowValues = {"","","","","","","","","","",""};
                bookTableModel.addRow(rowValues);  //添加一行
			}
		});
		addBookButton.setBounds(10, 335, 140, 40);
		manageBkinMng.add(addBookButton);
		
		
		Panel menu_show = new Panel();
		menu_show.setBounds(0, 0, 117, 390);
		frame.getContentPane().add(menu_show);
		menu_show.setLayout(null);
		
		*//**
		 * 面板切换的方法（写在内部类里）
		 * @author Administrator
		 *
		 *//*
		class ControlPanel {
			public void choose(int type) {
				//sorter = new TableRowSorter<DefaultTableModel>(stuListTbModel);
				//stuListTb.setRowSorter(null); 
				infopanel.setVisible(false);
				manageStudents.setVisible(false);
				manageBkinMng.setVisible(false);
				manageworker.setVisible(false);
			    regesiter.setVisible(false);
			    welcomePanel.setVisible(false);
			    panel0.setVisible(false);
			    switch (type) {
			    case 0 :
			    	infopanel.setVisible(true);
			    	break;
			    case 1 :
			    	manageworker.setVisible(true);
			    	break;
			    case 2 :
			    	manageStudents.setVisible(true);
			    	break;
			    case 3 :
			    	manageBkinMng.setVisible(true);
			    	break;
			    case 4 :
			    	regesiter.setVisible(true);
			    	break;
			    case 5 :
			    	welcomePanel.setVisible(true);
			    	break;
			    case 6 :
			    	panel0.setVisible(true);
			    	break;
			    }
			}
		}
		JButton userInfo = new JButton("99\u7528\u6237\u4FE1\u606F");
		userInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//设置显示个人信息
				namTex.setText(manager.getName());
				ageTex.setText(manager.getAge());
				usNamText.setText(manager.getUser());
				passwordTex.setText(manager.getPassword());
				selectedSex = manager.getSex();
				if(manager.getSex().equals("男"))
					rbman.setSelected(true);
				else
					rbwoman.setSelected(true);
				//切换面板
			    ControlPanel mcp = new ControlPanel();
			    mcp.choose(0);
			}
		});
		userInfo.setBounds(0, 40, 115, 40);
		menu_show.add(userInfo);
		
		JButton mangeWorker = new JButton("\u7BA1\u7406\u5DE5\u4F5C\u4EBA\u5458");
		mangeWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    ControlPanel mcp = new ControlPanel();
			    mcp.choose(1);
			    workerListTb.setRowSorter(null);
			    workerListTbModel = new DefaultTableModel(
						new Object[][] {},
						new String[] {
							"\u7528\u6237\u540D", "\u59D3\u540D", "\u6027\u522B", "\u5E74\u9F84", "\u7F16\u53F7"
						}
					);
			    workerListTb.setModel(workerListTbModel);
			    ArrayList<Worker> workers = manager.getWorkers();
			    for(Worker e1 : workers)
			    {
			    	workerListTbModel.addRow(e1.toStrings());
			    }
			    RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(workerListTbModel);
			    workerListTb.setRowSorter(sorter); 
			}
		});
		mangeWorker.setBounds(0, 80, 115, 40);
		menu_show.add(mangeWorker);
		
		JButton mangeStus = new JButton("77\u7BA1\u7406\u5B66\u751F");
		mangeStus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlPanel mcp = new ControlPanel();
				mcp.choose(2);
				
				stuListTbModel = new DefaultTableModel(
						new Object[][] {},
						new String[] {
							"\u7528\u6237\u540D", "\u59D3\u540D", "\u6027\u522B", "\u5E74\u9F84", "\u7F16\u53F7"
						}
					);
				stuListTb.setModel(stuListTbModel);
				ArrayList<Student> students = studentManage.getStudents();
				for(Student e1 : students)
				{
					//stuListTb.setRowSorter(sorter);  
					stuListTbModel.addRow(e1.toStrings());
				}
				RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(stuListTbModel);
				stuListTb.setRowSorter(sorter);  
			}
		});
		mangeStus.setBounds(0, 120, 115, 40);
		menu_show.add(mangeStus);
		
		JButton mangeBks = new JButton("55\u7BA1\u7406\u56FE\u4E66");
		mangeBks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlPanel mcp = new ControlPanel();
			    mcp.choose(3);
			    bookTable.setRowSorter(null);
			    bookTableModel = new DefaultTableModel(
						new Object[][] {},
							new String[] {
								"书名","书号","出版社","作者","标价","类别","库存","总数量","入库日期"
							}
						);
			    bookTable.setModel(bookTableModel);
			    ArrayList<ArrayList<Book>> bookArray = bookManage.getBookArray();
				for(ArrayList<Book> e1:bookArray)
				{
					for(Book e2 : e1)
					{
						bookTableModel.addRow(e2.toStrings());
					}
				}
				RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(bookTableModel);
				bookTable.setRowSorter(sorter);  
			}
		});
		mangeBks.setBounds(0, 160, 115, 40);
		menu_show.add(mangeBks);
		
		JButton borrReadReg = new JButton("44\u501F\u9605\u767B\u8BB0");
		borrReadReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    ControlPanel mcp = new ControlPanel();
			    mcp.choose(4);
			}
		});
		borrReadReg.setBounds(0, 200, 115, 40);
		menu_show.add(borrReadReg);
		
		JButton button = new JButton("首页");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlPanel mcp = new ControlPanel();
			    mcp.choose(5);
			}
		});
		button.setBounds(0, 0, 115, 40);
		menu_show.add(button);
		
		JButton button_6 = new JButton("33\u56FE\u4E66\u53BB\u5411");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlPanel mcp = new ControlPanel();
			    mcp.choose(6);
			    table0.setRowSorter(null);
			    table0Model = new DefaultTableModel(
						new Object[][] {},
						new String[] {
								"书名","书号","出版社","作者","标价","借阅者编号","借阅日期","应还日期","入库日期"
						}
					);
		    	table0.setModel(table0Model);
			    if(bookManage.getBorrowedBooks() != null ) 
			    for(BorrowedBook e0 : bookManage.getBorrowedBooks())
			    {
			    	if(e0 == null) continue;
			    	table0Model.addRow( e0.toStrings0() );
			    }
			    RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(table0Model);
			    table0.setRowSorter(sorter);
			}
		});
		button_6.setBounds(0, 239, 115, 40);
		menu_show.add(button_6);
		
		JButton button_7 = new JButton("11\u4E00\u952E\u5907\u4EFD");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 File folder = new File("dat");
				     if( !folder.exists() ) 
				    	 folder.mkdir();
					bookManage.write();
					studentManage.write();
					manager.write();
					new BackupsInfo().show();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button_7.setBounds(0, 350, 115, 40);
		menu_show.add(button_7);
		
		JButton button_8 = new JButton("\22u4E00\u952E\u8FD8\u539F");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bookManage.read();
					studentManage.read();
					manager.read();
					new RestoreInfo().show();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_8.setBounds(0, 311, 115, 40);
		menu_show.add(button_8);
		frame.setVisible(true);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
*/