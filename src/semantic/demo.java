package semantic;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Toolkit;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.RowSorter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JLabel;

import Entity.ACTIONTable;
import Entity.CharTable;
import Entity.DFATable;
import Entity.DFATableState;
import Entity.FourAddr;
import Entity.GOTOTable;
import Entity.SLRFormula;
import Entity.SLRTree;
import Entity.AddrNum;
import Entity.grammerSemanticLoca;
import Entity.grammerTable;
import Entity.RreeSemanticRecord;

import java.awt.Font;

import javax.swing.JMenu;

public class demo {

	private JFrame frame;
	private JTextArea textArea;
	//private JTable table;
	private DefaultTableModel tokenListTbModel;
	//private DefaultTableModel firstListTbModel;
	private DefaultTableModel errorListTbModel;
	private DefaultTableModel grammererrorListTbModel;
	private DefaultTableModel semerrorListTbModel;
	//private DefaultTableModel followListTbModel;
	//private DefaultTableModel slrListTbModel;
	private DefaultTableModel syntaxListTbModel;
	private String filepath2;
	private JScrollPane jScrollPane1;
	private ArrayList<ACTIONTable> slrTable;
	private DefaultTreeModel treeModel;
	
	//private JTextArea addrArea;
	private JScrollPane jScrollPane2;
	private DefaultTableModel charListTbModel;
	private DefaultTableModel addrListTbModel;
	// 运算符 operaters
	// String[] operaters={
	// ">",">=","<","<=","==","!=","|","&","||","&&","!","^","+","-","*","/","%","++","--","+=","-=","*=","%="};
	// 界符 divideLine
	// String[] divideLines={
	// ",","=",";","[","]","(",")","{",";","}",".","\"","\'"};
	// 关键字 keywords
	String[] keywords = { "char", "long", "short", "float", "double", "const",
			"boolean", "void", "null", "false", "true",  "int", "do",
			"while", "if", "else", "for", "then", "break", "continue", "class",
			"static", "final",  "return", "signed", "struct",
			"goto", "switch", "case", "default",
			"extern", "sizeof", "typedef", "proc","integer","record","real","call","and","or","not"};
	String[] tokens = { "char", "long", "short", "float", "double", "const",
			"boolean", "void", "null", "false", "true",  "int", "do",
			"while", "if", "else", "for", "then", "break", "continue", "class",
			"static", "final", "return", "signed", "struct",
			 "goto", "switch", "case", "default",
			"extern",  "sizeof", "typedef",   "proc","integer","record","real","call","and","or","not",">", ">=",
			"<", "<=", "==", "!=", "|", "&", "||", "&&", "!", "^", "+", "-",
			"*", "/", "%", "++", "--", "+=", "-=", "*=", "/=", ",", "=", ";",
			"[", "]", "(", ")", "{", "}", ".", "\"", "'"

	};

	/**
	 * Launch the application.
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		DFATable dfa[] = new readDFATable().addDFA();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					demo window = new demo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public demo() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 1323, 860);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		frame.setLocation((screensize.width - frameSize.width) / 2,
				(screensize.height - frameSize.height) / 2);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(0, 0, 1307, 815);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		 JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 153, 204));
		panel_1.setBounds(0, 0, 1308, 819);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		final JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(176, 196, 222));
		panel_5.setBounds(303, 13, 988, 785);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		panel_5.setVisible(false);
		final JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(176, 196, 222));
		panel_2.setBounds(303, 13, 988, 785);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setVisible(true);
		final JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(176, 196, 222));
		panel_4.setBounds(303, 13, 988, 785);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		panel_4.setVisible(false);
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(230, 230, 250));
		panel_3.setBounds(697, 70, 277, 702);
		panel_4.add(panel_3);
		panel_3.setLayout(null);
		//创建树对象
		final JTree tree = new JTree();
		tree.setModel(treeModel);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setRowHeight(20);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tree);
		panel_3.add(scrollPane);
		scrollPane.setBounds(0, 5, 275,697);
		
/**************开始*************************************************/
	        
/*************结束*************************************************/
		jScrollPane1 = new JScrollPane();
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setBackground(new Color(220, 220, 220));
		JScrollPane textAreaSP = new JScrollPane();
		textAreaSP.setBounds(14, 87, 275, 309);
		panel_1.add(textAreaSP);
		textAreaSP.setViewportView(textArea);
		
		// jScrollPane1.setViewportView(textArea);
		// jScrollPane1.setRowHeaderView(new LineNumberHeaderView());
		//textArea.setBounds(14, 87, 275, 309);
	//	panel_1.add(textArea);
		//textArea.setColumns(10);
		final grammerTable[] grammerTable = new readDFATable().Wenfa();
		System.out.println("***********文法表***********");
		for(int i=0;i<grammerTable.length;i++)
		{
			System.out.print(i+" : ");
			System.out.print(grammerTable[i].getName()+"->");
			for(int j=0;j<grammerTable[i].getValue().length;j++)
			{
				System.out.print(grammerTable[i].getValue()[j]+" ");
			}
			System.out.println();
		}
		System.out.print("***********文法表结束***********");
		
		final JButton btnNewButton_2 = new JButton("选择文件");
		btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 15));
		btnNewButton_2.setBackground(new Color(169, 169, 169));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(); // 设置选择器
				chooser.setMultiSelectionEnabled(true); // 设为多选
				int returnVal = chooser.showOpenDialog(btnNewButton_2);
				if (returnVal == JFileChooser.APPROVE_OPTION) { // 如果符合文件类型
					String filepath = chooser.getSelectedFile()
							.getAbsolutePath(); // 获取绝对路径
					System.out.println(filepath);
					filepath2 = filepath.replaceAll("\\\\", "/");
					File file = new File(filepath2);
					textArea.setText(txt2String(file));
				}
			}
		});
		btnNewButton_2.setBounds(14, 30, 107, 34);
		panel_1.add(btnNewButton_2);

		JButton btnNewButton_1 = new JButton("\u6D4B\u8BD5");
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 15));
		btnNewButton_1.setBackground(new Color(169, 169, 169));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = textArea.getText();
				String str2 = str.replaceAll("\r|\n", ""); // 去掉换行符空格
				try {
					ArrayList<String[]> a=lexicalAnalysis(str2);
			        ArrayList<SLRTree> slrTreeArray= slrTest(a,slrTable);
			        /**************开始构建树*************************************************/
			        treeModel= getTreeModel(slrTreeArray,slrTreeArray.size()-1);
					tree.setModel(treeModel);
					/**************开始语义分析*************************************************/
					System.out.println("********开始语义分析*************");
					grammerSemanticLoca[] grammersemanticLoca=new readDFATable().GrammerSemanticLoca();
					ArrayList<RreeSemanticRecord> treeSemanticRecord=new ArrayList<RreeSemanticRecord>();
					ArrayList<String> addrList=new ArrayList<String>();//地址表L1 L2 L3
					ArrayList<String> addrResult=new ArrayList<String>();//输出结果if a>b goto l1
					ArrayList<AddrNum> addrNum=new ArrayList<AddrNum>();//地址与序号的对应表
					ArrayList<CharTable> charTable=new ArrayList<CharTable>();//符号表
					ArrayList<FourAddr> fourAddr=new ArrayList<FourAddr>();//地址与序号的对应表
					ArrayList<String> param=new ArrayList<String>();//存数据
					initRecord(treeSemanticRecord,slrTreeArray);
					semanticTest(grammerTable,slrTreeArray,treeSemanticRecord,grammersemanticLoca,addrList,addrResult ,addrNum,charTable,fourAddr,param, slrTreeArray.size()-1);
					ArrayList<String> b=change(addrResult,addrNum);
					System.out.println("********地址表*************");
					for(int i=0;i<addrList.size();i++)
					{
						System.out.println(addrList.get(i));
						//addrListTbModel.addRow(new Object[] { i+1,addrResult.get(i)});
					}
					System.out.println("********地址输出表*************");
					for(int i=0;i<addrResult.size();i++)
					{
						System.out.println(addrResult.get(i));
						//addrListTbModel.addRow(new Object[] { i+1,addrResult.get(i)});
					}
					
					System.out.println(b.size()+" "+fourAddr.size());
					for(int i=0;i<b.size();i++)
					{
						String s="< "+fourAddr.get(i).getOp()+" , "+fourAddr.get(i).getParam1()+" , "+fourAddr.get(i).getParam2()+" , "+fourAddr.get(i).getToaddr()+" >";
						addrListTbModel.addRow(new Object[] { i+1,b.get(i),s});
					}
					System.out.println("********地址对应表*************");
					System.out.println("size="+addrNum.size());
					for(int i=0;i<addrNum.size();i++)
					{
						System.out.println(addrNum.get(i).getAddr()+" "+addrNum.get(i).getNum());
						
					}
					System.out.println("********四地址表*************");
					for(int i=0;i<fourAddr.size();i++)
					{
						System.out.println("< "+fourAddr.get(i).getOp()+" , "+fourAddr.get(i).getParam1()+" , "+fourAddr.get(i).getParam2()+" , "+fourAddr.get(i).getToaddr()+" >");
						
					}
					System.out.println("********符号表*************");
					for(int i=0;i<charTable.size();i++)
					{
						System.out.println(charTable.get(i).getChara()+" "+charTable.get(i).getType()+" "+charTable.get(i).getOffset());
						charListTbModel.addRow(new Object[] { charTable.get(i).getChara(),charTable.get(i).getType(),charTable.get(i).getOffset()});
					}
					System.out.println("********记录树信息表*************");
					testRecord(treeSemanticRecord);
					System.out.println("********结束语义分析*************");
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(171, 30, 92, 34);
		panel_1.add(btnNewButton_1);
		
		 
		
// 种别码表格
		tokenListTbModel = new DefaultTableModel(new Object[][] {},
				new String[] { "字符串", "类别", "种别码", "value" });
		RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tokenListTbModel);
	
// first集表格
		
		/*firstListTbModel = new DefaultTableModel(new Object[][] {}, new String[] {"符号", "FIRST集" });
		String[][] firstGroup = getFirstDroup(grammerTable);
		for (int i = 0; i < firstGroup.length; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(firstGroup[i][j] + "  ");
			}
			firstListTbModel.addRow(new Object[] { firstGroup[i][0],firstGroup[i][1] });
			System.out.println();
		}
		RowSorter<DefaultTableModel> sorter1 = new TableRowSorter<DefaultTableModel>(firstListTbModel);*/
// Follow表格
		/*followListTbModel = new DefaultTableModel(new Object[][] {},new String[] { "非终结符", "Follow集" });
		String[][] followGroup = getFollowGroup();
		for (int i = 0; i < followGroup.length; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(followGroup[i][j] + "  ");
			}
			followListTbModel.addRow(new Object[] { followGroup[i][0],followGroup[i][1] });
			System.out.println();
		}
		RowSorter<DefaultTableModel> sorter4 = new TableRowSorter<DefaultTableModel>(followListTbModel);*/
// 词法分析出错表格
		errorListTbModel = new DefaultTableModel(new Object[][] {},
				new String[] { "出错符号", "出错地方", "出错原因" });
		// errorListTbModel.addRow(new Object[] { "出错", "类别","出错原因" ,"value"});
		RowSorter<DefaultTableModel> sorter2 = new TableRowSorter<DefaultTableModel>(errorListTbModel);
		
// 语法分析出错表格
		grammererrorListTbModel = new DefaultTableModel(new Object[][] {},
				new String[] { "出错符号", "出错地方", "出错原因" });
		// errorListTbModel.addRow(new Object[] { "出错", "类别","出错原因" ,"value"});
		RowSorter<DefaultTableModel> sorter7 = new TableRowSorter<DefaultTableModel>(grammererrorListTbModel);

// 语义分析出错表格
		semerrorListTbModel = new DefaultTableModel(new Object[][] {},
				new String[] { "出错类型", "出错原因", "出错字符" });
		// errorListTbModel.addRow(new Object[] { "出错", "类别","出错原因" ,"value"});
		RowSorter<DefaultTableModel> sorter8 = new TableRowSorter<DefaultTableModel>(semerrorListTbModel);
// 地址表格
    addrListTbModel = new DefaultTableModel(new Object[][] {},new String[] { "序号", "三地址","四元式"});
	RowSorter<DefaultTableModel> sorter10 = new TableRowSorter<DefaultTableModel>(addrListTbModel);
// 符号表格
	charListTbModel = new DefaultTableModel(new Object[][] {},new String[] { "序号", "符号" , "偏移"});
		RowSorter<DefaultTableModel> sorter9 = new TableRowSorter<DefaultTableModel>(charListTbModel);
// slr分析表格
		/*slrListTbModel = new DefaultTableModel(new Object[][] {},
				new String[] { "", "","","","","","","","","", "", "A", "C", "T", "I", "O", "N", 
						  	"","", "","","","","","","","","","", "",
							"","","","G", "O", "T", "O","","",""});

		slrListTbModel.addRow(new Object[] { "",  "+", "*", "-", "(", "id", "digit", "=", "call", ")", "[", "]", ",", ";",
				">","true","false","not","and","or","if","then","else","while","do","proc","record","integer","real","$", 
				"S", "E", "L", "F", "B","P","D","X","C","T"});
		for(int j=1;j<85;j++)
		{
			slrListTbModel.addRow(new Object[] {j, "","","","","","","","","", "", "", "", "", "", "", "", 
				  	"","", "","","","","","","","","","", "",
					"","","","", "", "", "","","",""});
		}

		RowSorter<DefaultTableModel> sorter5 = new TableRowSorter<DefaultTableModel>(
				slrListTbModel);*/
		
		// 语法分析表格
				syntaxListTbModel = new DefaultTableModel(new Object[][] {},
						new String[] { "规约语句"});

			//	syntaxListTbModel.addRow(new Object[] { "字符串", "类别","种别码" });

				RowSorter<DefaultTableModel> sorter6 = new TableRowSorter<DefaultTableModel>(
						syntaxListTbModel);

		/*JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 204, 204));
		panel_2.setBounds(14, 364, 1246, 576);
		panel_1.add(panel_2);
		panel_2.setLayout(null);*/
		
		/*JTable slrListTb = new JTable();
		slrListTb.setBackground(new Color(224, 255, 255));
		slrListTb.setFillsViewportHeight(true);
		slrListTb.setModel(slrListTbModel);
		slrListTb.setRowSorter(sorter5);
		JScrollPane slrSP = new JScrollPane();
		slrSP.setBounds(14, 36, 1221, 297);
		panel_2.add(slrSP);
		slrSP.setViewportView(slrListTb);
		
		JLabel lblSlrTable = new JLabel("SLR TABLE");
		lblSlrTable.setFont(new Font("宋体", Font.BOLD, 35));
		lblSlrTable.setBounds(651, 0, 192, 40);
		panel_2.add(lblSlrTable);
				
		JTable firstListTb = new JTable();
		firstListTb.setBackground(new Color(250, 240, 230));
		firstListTb.setFillsViewportHeight(true);
		firstListTb.setModel(firstListTbModel);
		firstListTb.setRowSorter(sorter1);
		JScrollPane firstSP = new JScrollPane();
		firstSP.setBounds(741, 77, 258, 279);
		panel_1.add(firstSP);
		firstSP.setViewportView(firstListTb);
		
		JTable followListTb = new JTable();
		followListTb.setBackground(new Color(224, 255, 255));
		followListTb.setFillsViewportHeight(true);
		followListTb.setModel(followListTbModel);
		followListTb.setRowSorter(sorter4);
		JScrollPane followSP = new JScrollPane();
		followSP.setBounds(1013, 77, 242, 279);
		panel_1.add(followSP);
		followSP.setViewportView(followListTb);*/
		
		/*JLabel lblFollowTable = new JLabel("FOLLOW TABLE");
		lblFollowTable.setBounds(1036, 30, 206, 48);
		panel_1.add(lblFollowTable);
		lblFollowTable.setFont(new Font("宋体", Font.BOLD, 30));*/
		
		/*JLabel lblDfa = new JLabel("FIRST TABLE");
		lblDfa.setBounds(832, 30, 203, 42);
		panel_1.add(lblDfa);
		lblDfa.setFont(new Font("宋体", Font.BOLD, 30));*/
		
		
		
		
		JTable tokenListTb = new JTable();
		tokenListTb.setBackground(new Color(230, 230, 250));
		tokenListTb.setFillsViewportHeight(true);
		tokenListTb.setModel(tokenListTbModel);
		tokenListTb.setRowSorter(sorter);
		JScrollPane tokenSP = new JScrollPane();
		tokenSP.setBounds(14, 98, 487, 594);
		panel_2.add(tokenSP);
		tokenSP.setViewportView(tokenListTb);
		
		JLabel lblToken = new JLabel("TOKEN TABLE");
		lblToken.setBounds(125, 40, 254, 34);
		panel_2.add(lblToken);
		lblToken.setFont(new Font("宋体", Font.BOLD, 40));
		
		JTable errorListTb = new JTable();
		errorListTb.setBackground(new Color(230, 230, 250));
		errorListTb.setFillsViewportHeight(true);
		errorListTb.setModel(errorListTbModel);
		errorListTb.setRowSorter(sorter2);
		JScrollPane errorSP = new JScrollPane();
		errorSP.setBounds(571, 94, 403, 598);
		panel_2.add(errorSP);
		errorSP.setViewportView(errorListTb);
		
		JLabel lblErrorTable = new JLabel("ERROR TABLE");
		lblErrorTable.setBounds(676, 37, 248, 41);
		panel_2.add(lblErrorTable);
		lblErrorTable.setFont(new Font("宋体", Font.BOLD, 40));
		JTable syntaxListTb = new JTable();
		syntaxListTb.setBackground(new Color(230, 230, 250));
		syntaxListTb.setFillsViewportHeight(true);
		syntaxListTb.setModel(syntaxListTbModel);
		syntaxListTb.setRowSorter(sorter6);
		JScrollPane syntaxSP = new JScrollPane();
		syntaxSP.setBounds(46, 72, 611, 314);
		panel_4.add(syntaxSP);
		syntaxSP.setViewportView(syntaxListTb);
		JTable grammererrorListTb = new JTable();
		grammererrorListTb.setBackground(new Color(230, 230, 250));
		grammererrorListTb.setFillsViewportHeight(true);
		grammererrorListTb.setModel(grammererrorListTbModel);
		grammererrorListTb.setRowSorter(sorter7);
		JScrollPane grammererrorSP = new JScrollPane();
		grammererrorSP.setBounds(46, 447, 611, 325);
		panel_4.add(grammererrorSP);
		grammererrorSP.setViewportView(grammererrorListTb);
		
		JLabel lblAnalysisTable = new JLabel("Analysis TABLE");
		lblAnalysisTable.setBounds(198, 19, 320, 40);
		panel_4.add(lblAnalysisTable);
		lblAnalysisTable.setFont(new Font("宋体", Font.BOLD, 40));
		
		JLabel lblErrorTable_1 = new JLabel("ERROR TABLE");
		lblErrorTable_1.setFont(new Font("宋体", Font.BOLD, 40));
		lblErrorTable_1.setBounds(219, 395, 254, 40);
		panel_4.add(lblErrorTable_1);
		
		JTable semerrorListTb = new JTable();
		semerrorListTb.setBackground(new Color(230, 230, 250));
		semerrorListTb.setFillsViewportHeight(true);
		semerrorListTb.setModel(semerrorListTbModel);
		semerrorListTb.setRowSorter(sorter8);
		JScrollPane semerrorSP = new JScrollPane();
		semerrorSP.setBounds(528, 531, 446, 204);
		panel_5.add(semerrorSP);
		semerrorSP.setViewportView(semerrorListTb);
		
		JTable charListTb = new JTable();
		charListTb.setBackground(new Color(230, 230, 250));
		charListTb.setFillsViewportHeight(true);
		charListTb.setModel(charListTbModel);
		charListTb.setRowSorter(sorter9);
		JScrollPane charSP = new JScrollPane();
		charSP.setBounds(534, 76, 440, 369);
		panel_5.add(charSP);
		charSP.setViewportView(charListTb);
		
		JTable addrListTb = new JTable();
		addrListTb.setBackground(new Color(230, 230, 250));
		addrListTb.setFillsViewportHeight(true);
		addrListTb.setModel(addrListTbModel);
		addrListTb.setRowSorter(sorter10);
		JScrollPane addrSP = new JScrollPane();
		addrSP.setBounds(25, 76, 475, 659);
		panel_5.add(addrSP);
		addrSP.setViewportView(addrListTb);
		JLabel seAddrTable = new JLabel("Address TABLE");
		seAddrTable.setBounds(112, 19, 320, 40);
		panel_5.add(seAddrTable);
		seAddrTable.setFont(new Font("宋体", Font.BOLD, 40));
		JLabel charaTable = new JLabel("Symbol TABLE");
		charaTable.setBounds(616, 19, 320, 40);
		panel_5.add(charaTable);
		charaTable.setFont(new Font("宋体", Font.BOLD, 40));
		JLabel seErrorTable = new JLabel("Error TABLE");
		seErrorTable.setBounds(616, 478, 320, 40);
		panel_5.add(seErrorTable);
		seErrorTable.setFont(new Font("宋体", Font.BOLD, 40));
		/**
		 * 面板切换的方法（写在内部类里）
		 * @author Administrator
		 *
		 */
		class ControlPanel {
			public void choose(int type) {
				//sorter = new TableRowSorter<DefaultTableModel>(stuListTbModel);
				//stuListTb.setRowSorter(null); 
				panel_2.setVisible(false);
				panel_4.setVisible(false);
				panel_5.setVisible(false);
			    switch (type) {
			    case 0 :
			    	panel_2.setVisible(true);
			    	break;
			    case 1 :
			    	panel_4.setVisible(true);
			    	break;
			    case 2 :
			    	panel_5.setVisible(true);
			    	break;
	
			    }
			}
		}
		JButton button = new JButton("\u8BCD\u6CD5\u5206\u6790");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//切换面板
			    ControlPanel mcp = new ControlPanel();
			    mcp.choose(0);
			}
		});
		button.setBackground(new Color(169, 169, 169));
		button.setBounds(73, 423, 150, 40);
		panel_1.add(button);
		
		JButton button_1 = new JButton("\u8BED\u6CD5\u5206\u6790");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//切换面板
			    ControlPanel mcp = new ControlPanel();
			    mcp.choose(1);
			}
		});
		button_1.setBackground(new Color(169, 169, 169));
		button_1.setBounds(73, 476, 150, 40);
		panel_1.add(button_1);
		
				
		JButton btnDfa = new JButton("DFA\u8F6C\u6362\u8868");
		btnDfa.setBounds(73, 585, 150, 40);
		panel_1.add(btnDfa);
		btnDfa.setBackground(new Color(169, 169, 169));
		
		JButton btnSlr = new JButton("SLR\u5206\u6790\u8868");
		btnSlr.setBounds(73, 648, 150, 40);
		panel_1.add(btnSlr);
		btnSlr.setBackground(new Color(169, 169, 169));
		
		JButton button_2 = new JButton("\u8BED\u4E49\u89C4\u5219");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				semanticRulePanel window = null;
				try {
					window = new semanticRulePanel();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				window.frame.setVisible(true);
			}
		});
		button_2.setBackground(new Color(169, 169, 169));
		button_2.setBounds(73, 707, 150, 40);
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("\u8BED\u4E49\u5206\u6790");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//切换面板
			    ControlPanel mcp = new ControlPanel();
			    mcp.choose(2);
			}
		});
		button_3.setBackground(new Color(169, 169, 169));
		button_3.setBounds(73, 532, 150, 40);
		panel_1.add(button_3);
		btnSlr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SLRPanel window = null;
				try {
					window = new SLRPanel();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				window.frame.setVisible(true);
			}
		});
		btnDfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DFAPanel window = null;
				try {
					window = new DFAPanel();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				window.frame.setVisible(true);
			}
		});
		
		slrTable=getSLRTable(grammerTable);
		
		
	}

	// 读文件
	public static String txt2String(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result.append(s + System.lineSeparator());
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 词法分析开始*************************************************
	 */
	public ArrayList<String[]> lexicalAnalysis(String str) throws Exception {
		while (tokenListTbModel.getRowCount() > 0) {
			tokenListTbModel.removeRow(0);
		}
		while (errorListTbModel.getRowCount() > 0) {
			errorListTbModel.removeRow(0);
		}
		while (addrListTbModel.getRowCount() > 0) {
			addrListTbModel.removeRow(0);
		}
		while (charListTbModel.getRowCount() > 0) {
			charListTbModel.removeRow(0);
		}
		while (semerrorListTbModel.getRowCount() > 0) {
			semerrorListTbModel.removeRow(0);
		}
		ArrayList<String[]> slrInput2=new ArrayList<String[]>();
		//String[][] slrInput= null;
		//int inputCount=0;
		// 将字符串转化为字符数组
		char[] strline = str.toCharArray();
		String currentString = "";
		int currentState = 0; // 当前状态
		int lastState = 0; // 上一状态

		DFATable dfa[] = new readDFATable().addDFA();
		DFATableState DFAstate[] = new readDFATable().showDFAState();

		for (int i = 0; i < strline.length; i++) {
			currentString = currentString.concat(String.valueOf(strline[i]));
			lastState = currentState;

			if (currentString == " " || strline[i] == ' ') {
				currentString = currentString.replaceAll(" ", "");
				if (isKeyword(currentString)) {
					tokenListTbModel.addRow(new Object[] { currentString,
							"关键字", tokenID(currentString), "   " });
					slrInput2.add(new String[]{currentString,"关键字"});
					
				} else {
					tokenListTbModel.addRow(new Object[] {currentString,
							findTypeByState(lastState, DFAstate),
							tokenID(currentString),
							findTypeByState(lastState, DFAstate).equals("注释")
									|| findTypeByState(lastState, DFAstate)
											.equals("运算符")
									|| findTypeByState(lastState, DFAstate)
											.equals("界符") ? "   "
									: currentString });
					slrInput2.add(new String[]{currentString,findTypeByState(lastState, DFAstate)});
				}
				currentString = "";
				currentState = 0;
				continue;
			}
			currentState = stateChange(strline[i], currentState, dfa);
			if (currentState == -1 || currentState == -2)// 现在这个字符在线路上混不下去了
			{
				currentString = currentString.substring(0,
						currentString.length() - 1);
				// 判断当前状态是否为终止状态 如不是 报错
				if (!isFinishByState(lastState, DFAstate)) {
					errorListTbModel.addRow(new Object[] { currentString,
							"第" + i + "字符", "非法字符" });
					tokenListTbModel.addRow(new Object[] { currentString,
							"非法字符", "无", "   " });
				} else {
					if (isKeyword(currentString)) {
						tokenListTbModel.addRow(new Object[] { currentString,
								"关键字", tokenID(currentString), "   " });
						/*slrInput[inputCount][0]=currentString;
						slrInput[inputCount][1]="关键字";
						inputCount++;*/
						slrInput2.add(new String[]{currentString,"关键字"});
					} else {
						tokenListTbModel.addRow(new Object[] {
								currentString,
								findTypeByState(lastState, DFAstate),
								tokenID(currentString),
								findTypeByState(lastState, DFAstate).equals(
										"注释")
										|| findTypeByState(lastState, DFAstate)
												.equals("运算符")
										|| findTypeByState(lastState, DFAstate)
												.equals("界符") ? "   "
										: currentString });
				
						slrInput2.add(new String[]{currentString,findTypeByState(lastState, DFAstate)});
					}
				}
				if (currentState == -2) {
					
					tokenListTbModel.addRow(new Object[] { strline[i], "非法字符",
							"无", "   " });
					errorListTbModel.addRow(new Object[] { strline[i],
							"第" + i + "字符", "非法字符" });
					i++;
					// errorListTbModel.addRow(new Object[] {"/*", "注释未封闭"});
				}
				currentString = "";
				currentState = 0;
				i--;
			}
			// 最后一个符号
			if (i == strline.length - 1) {
				if (!isFinishByState(lastState, DFAstate)) {
			
					errorListTbModel.addRow(new Object[] { currentString,
							"第" + i + "字符", "非法字符" });
					tokenListTbModel.addRow(new Object[] { currentString,
							"非法字符", "无", "   " });
				} else {
					if (isKeyword(currentString)) {
						tokenListTbModel.addRow(new Object[] { currentString,
								"关键字", tokenID(currentString), "   " });
						slrInput2.add(new String[]{currentString,"关键字"});
					} else {
						tokenListTbModel.addRow(new Object[] {
								currentString,
								findTypeByState(currentState, DFAstate),
								tokenID(currentString),
								findTypeByState(currentState, DFAstate).equals(
										"注释")
										|| findTypeByState(currentState,
												DFAstate).equals("运算符")
										|| findTypeByState(currentState,
												DFAstate).equals("界符") ? "   "
										: currentString });
						slrInput2.add(new String[]{currentString,findTypeByState(currentState, DFAstate)});
					}
				}
				if (currentState == -2) {
					tokenListTbModel.addRow(new Object[] { strline[i], "非法字符",
							"无", "   " });
					errorListTbModel.addRow(new Object[] { strline[i],
							"第" + i + "字符", "非法字符" });
					i++;
				}
			}

		}
		return slrInput2;

	}
	
	/**
	 * 词法分析开始*************************************************
	 */
	public ArrayList<String[]> lexicalAnalysis2(String str) throws Exception {
		ArrayList<String[]> slrInput2=new ArrayList<String[]>();
		char[] strline = str.toCharArray();
		String currentString = "";
		int currentState = 0; // 当前状态
		int lastState = 0; // 上一状态
		DFATable dfa[] = new readDFATable().addDFA();
		DFATableState DFAstate[] = new readDFATable().showDFAState();
		for (int i = 0; i < strline.length; i++) {
			currentString = currentString.concat(String.valueOf(strline[i]));
			lastState = currentState;

			if (currentString == " " || strline[i] == ' ') {
				currentString = currentString.replaceAll(" ", "");
				if (isKeyword(currentString)) {
					slrInput2.add(new String[]{currentString,"关键字"});
					
				} else {
					slrInput2.add(new String[]{currentString,findTypeByState(lastState, DFAstate)});
				}
				currentString = "";
				currentState = 0;
				continue;
			}
			currentState = stateChange(strline[i], currentState, dfa);
			if (currentState == -1 || currentState == -2)// 现在这个字符在线路上混不下去了
			{
				currentString = currentString.substring(0,
						currentString.length() - 1);
				// 判断当前状态是否为终止状态 如不是 报错
				if (!isFinishByState(lastState, DFAstate)) {
				} else {
					if (isKeyword(currentString)) {
						slrInput2.add(new String[]{currentString,"关键字"});
					} else {
						slrInput2.add(new String[]{currentString,findTypeByState(lastState, DFAstate)});
					}
				}
				if (currentState == -2) {
					i++;
				}
				currentString = "";
				currentState = 0;
				i--;
			}
			// 最后一个符号
			if (i == strline.length - 1) {
				if (!isFinishByState(lastState, DFAstate)) {
				} else {
					if (isKeyword(currentString)) {
						slrInput2.add(new String[]{currentString,"关键字"});
					} else {
						slrInput2.add(new String[]{currentString,findTypeByState(currentState, DFAstate)});
					}
				}
				if (currentState == -2) {
					i++;
				}
			}

		}
		return slrInput2;

	}

	public int stateChange(char currentChar, int currentState, DFATable[] dfa) {
		boolean isInput = false;
		for (int i = 0; i < dfa.length; i++) {
			if (isList(dfa[i].getInput(), currentChar)) {
				isInput = true;// 存在该输入
				if (dfa[i].getState() == currentState
						&& dfa[i].getNextState() != -1)// 当前状态一样 输入有 下一状态不为空
														// 依然在当前自动机中
				{
					return dfa[i].getNextState();
				}
			}
		}
		if (isInput == false) {
			return -2;
		}
		// 在状态9 10 待太久没有结束 注释未封闭 报错
		return -1;
	}

	public static boolean isList(String[] arr, char currentChar) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(String.valueOf(currentChar))) {
				return true;
			}
		}
		return false;
	}

	// 根据state 返回该状态的类型
	public String findTypeByState(int state, DFATableState[] dfaState) {
		for (int i = 0; i < dfaState.length; i++) {
			if (dfaState[i].getState() == state) {
				return dfaState[i].getType();
			}
		}
		return "error";
	}

	// 根据state 返回该状态的是否为终止状态
	public boolean isFinishByState(int state, DFATableState[] dfaState) {
		for (int i = 0; i < dfaState.length; i++) {
			if (dfaState[i].getState() == state) {
				return dfaState[i].isFinish();
			}
		}
		return true;
	}

	// 判断当前即将要输出的字符串是否为关键字
	public boolean isKeyword(String str) {
		for (int i = 0; i < keywords.length; i++) {
			if (keywords[i].equals(str)) {
				return true;
			}
		}
		return false;
	}

	public int tokenID(String str) {
		boolean flag = false;
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].equals(str)) {
				flag = true;
				return i;
			}
		}
		if (flag == false) {
			tokens = Arrays.copyOf(tokens, tokens.length + 1);
			tokens[tokens.length - 1] = str;
			return tokens.length;
		}
		return -1;
	}

	/**
	 * 文法分析开始*************************************************
	 */

	/**
	 * 获得First集
	 * 
	 * @return first集
	 * @throws Exception
	 */
	public String[][] getFirstDroup(grammerTable[] grammerTable) throws Exception {
		
		String[][] firstGroup = new String[10][2];
		int x = 0;
		for (int k = 0; k < grammerTable.length; k++) {
			if (!isExitName(firstGroup, grammerTable[k].getName()))// first集已经存在该元素的first集
			{
				firstGroup[x][0] = grammerTable[k].getName();
				x++;
			}
		}
	//	System.out.println("===============================================grammerTable");
		/*for (int i = 0; i < 10; i++) {
			System.out.print(firstGroup[i][0]+" ");
		}*/
	//	System.out.println("===============================================firstGroup");
		for (int time = 0; time < 3; time++) {

			for (int k = 0; k < grammerTable.length; k++) {
				int flag = 0;
				for (int i = 0; i < firstGroup.length; i++) {
					if (firstGroup[i][0].equals(grammerTable[k].getName())) {
						flag = i;// 找到当前字符是first集的哪一个
						//System.out.println("flag= " + flag);
						break;
					}
				}
				String firstValue = grammerTable[k].getValue()[0];
				if (isTerminator(firstValue))// 第一个是终结符
				{
					//System.out.println("第一个为终结符");
					if (!isExitValue(firstGroup[flag][1], firstValue)) {
						//System.out.println("还没有 要添加");
						// addEx(firstGroup[flag][1],firstGroup[i][1]);
						if (firstGroup[flag][1] == null) {
							firstGroup[flag][1] = firstValue;
						} else {

							firstGroup[flag][1] += addEx(firstGroup[flag][1],
									firstValue);
						}
					}
				} else {
					for (int i = 0; i < firstGroup.length; i++) {
						if (firstGroup[i][0].equals(firstValue)) {
					
							if (!isExitValue(firstGroup[flag][1],
									firstGroup[i][1])) {
								if (firstGroup[flag][1] == null) {
									firstGroup[flag][1] = firstGroup[i][1];
								}

								firstGroup[flag][1] += addEx(
										firstGroup[flag][1], firstGroup[i][1]);
								
							}

						}
					}

				}
			}
		}
		return firstGroup;
	}

	/**
	 * 这个名字是不是已经有了 即first集有了的就不在新加一列了
	 * 
	 * @param first
	 * @param str
	 * @return
	 */
	public boolean isExitName(String[][] first, String str) {
		for (int i = 0; i < first.length; i++) {
			if (first[i][0] == null) {
				continue;
			}
			if (first[i][0].equals(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断当前即将要加入的值是否已存在
	 * 
	 * @param oldFirstValue
	 * @param newFirstValue
	 * @return
	 */
	public boolean isExitValue(String oldFirstValue, String newFirstValue) {

		if (oldFirstValue == null) {
			if (newFirstValue == null) {
				return true;
			}
			return false;
		} else {
			if (newFirstValue == null) {
				return true;
			}
			if (oldFirstValue.equals(newFirstValue)) {
				return true;
			}

			return false;

		}
	}

	/**
	 * 把新的里面旧的没有的字符串添加进旧的 在已有的value中 将新的里面没有的value加入进去
	 * 
	 * @param oldStr
	 * @param newStr
	 * @return
	 */
	public String addEx(String oldStr, String newStr) {
		String[] newFlag = newStr.split(" ");
		String[] oldFlag = oldStr.split(" ");
		String a = "";
		// int[] flag;
		for (int j = 0; j < newFlag.length; j++) {
			boolean flag = false;
			for (int k = 0; k < oldFlag.length; k++) {
				if (newFlag[j].equals(oldFlag[k])) {
					flag = true;
				}
			}
			if (!flag) {
				a += " " + newFlag[j];
			}

		}
		return a;
	}

	/**
	 * 判断是否为终结符
	 * 
	 * @param str
	 * @return
	 */
	public boolean isTerminator(String str) {
		char ch = str.toCharArray()[0];
		if (ch >= 'A' && ch <= 'Z') {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获得follow集
	 * 
	 * @throws Exception
	 */
	public String[][] getFollowGroup() throws Exception {
		grammerTable[] grammerTable = new readDFATable().Wenfa();
		String[][] followGroup = new String[10][2];
		int x = 0;
		for (int k = 0; k < grammerTable.length; k++) {
			if (!isExitName(followGroup, grammerTable[k].getName()))// follow集已经存在该元素的first集
			{
				followGroup[x][0] = grammerTable[k].getName();
				x++;
			}
		}
		for (int i = 0; i < 10; i++) {
			if (followGroup[i][0].equals("P")) {
				followGroup[i][1] = "$";
			}
		}
		for (int time = 0; time < 4; time++)// 重复三次 最好改善为没有改变
		{
			for (int k = 0; k < grammerTable.length; k++) {
				/*
				 * 遍历每一个 对每一个操作 第二部分每遇到一个终结符 判断后面是否为终结符 是则加上 不是判断后面那终结符是否为空
				 */
				int y = grammerTable[k].getValue().length;
			
				for (int i = 0; i < y; i++) {
					if (i == y - 1)// A->aB 最后一个 follow(A)全加入follow（B）中
					{
						if (!isTerminator(grammerTable[k].getValue()[i])) {
							int flag1 = findFollow(followGroup,grammerTable[k].getValue()[i]);// B在follow集的下标
							int flag2 = findFollow(followGroup,grammerTable[k].getName());// A在follow集的下标
		
							// follow(A)全加入follow（B）中
							if (followGroup[flag1][1] == null) {
								followGroup[flag1][1] = followGroup[flag2][1];
							} else {
								if (followGroup[flag2][1] == null)// A为空
								{
									continue;
								} else {
									followGroup[flag1][1] += addEx(
											followGroup[flag1][1],
											followGroup[flag2][1]);
								}

							}
						}
						continue;
					}
					//不是最后一个
					if (!isTerminator(grammerTable[k].getValue()[i]))// 当前是非终结符
					{
						int flag1 = findFollow(followGroup,grammerTable[k].getValue()[i]);// B在follow集的下标
						if (isTerminator(grammerTable[k].getValue()[i + 1]))// 后面是终结符  直接将终结符加入到follow（B）后面
						{
							if (followGroup[flag1][1] == null) {
								followGroup[flag1][1] = grammerTable[k].getValue()[i + 1];
							} else {
								followGroup[flag1][1] += addEx(
										followGroup[flag1][1],
										grammerTable[k].getValue()[i + 1]);
							}
						} else {// 后面是非终结符 将后面非终结符的first集加入follow集中
							String[][] firstGroup = getFirstDroup(grammerTable);
							int flag3 = findFirst(firstGroup,grammerTable[k].getValue()[i + 1]);// C在first集的下标
							String[] str = firstGroup[flag3][1].split(" ");//后面非终结符的first集
							boolean isNo = false;
							String newFirst="";
							for (int z = 0; z < str.length; z++){
								if (str[z].equals("no")) {
									isNo = true;
								}
								else{
									newFirst+=" "+str[z];
								}
							}
							if (isNo)// 如果有空的 则T->XC,c中有空把c的follow加到x的follow集中并把c的first集除空加到x的follow集中
							{
								//把c的first集除空加到x的follow集中
								if (followGroup[flag1][1] == null) {
									followGroup[flag1][1] =newFirst;
								} else {
									if (newFirst=="")// A为空
									{
										continue;
									} else {
										followGroup[flag1][1] += addEx(followGroup[flag1][1],newFirst);
									}
								}
								//把c的follow加到x的follow集中
								int flag4 = findFollow(followGroup,grammerTable[k].getValue()[i + 1]);
								if (followGroup[flag1][1] == null) {
									followGroup[flag1][1] = followGroup[flag4][1];
								} else {
									if (followGroup[flag4][1] == null)// A为空
									{
										continue;
									} else {
										followGroup[flag1][1] += addEx(
												followGroup[flag1][1],
												followGroup[flag4][1]);
									}
								}
							} else {//没有空
								if (followGroup[flag1][1] == null) {
									followGroup[flag1][1] = firstGroup[flag3][1];
								} else {
									if (firstGroup[flag3][1] == null)// A为空
									{
										continue;
									} else {
										followGroup[flag1][1] += addEx(
												followGroup[flag1][1],
												firstGroup[flag3][1]);
									}
								}
							}
						}
					}
				}

			}
		}
		return followGroup;
	}

	/**
	 * 找非终结符在follow集的下标
	 * 
	 * @param followGroup
	 * @param value
	 * @return
	 */
	public int findFollow(String[][] followGroup, String value) {
		for (int j = 0; j < followGroup.length; j++) {
			if (followGroup[j][0].equals(value)) {
				return j;
			}
		}
		return -1;

	}

	/**
	 * 找非终结符在first集的下标
	 * 
	 * @param firstGroup
	 * @param value
	 * @return
	 */
	public int findFirst(String[][] firstGroup, String value) {
		for (int j = 0; j < firstGroup.length; j++) {
			if (firstGroup[j][0].equals(value)) {
				return j;
			}
		}
		return -1;
	}

	/**
	 * 根据输入 从一个状态到构造另一个新状态
	 * 
	 * @param currentInput
	 * @param slrFormulaArray
	 *            当前的产生式集合
	 * @return 新的产生式集合
	 */
	public ArrayList<SLRFormula> GOTO(String currentInput,
			ArrayList<SLRFormula> slrFormulaArray) {
		ArrayList<SLRFormula> slrFormulaArray2 = new ArrayList<SLRFormula>();
		ArrayList<SLRFormula> slrFormulaArray3 = new ArrayList<SLRFormula>();
		int count = 0;
		// 遍历产生式集合的每一个产生式 看这个产生式的点后面的是不是与输入相同
		for (int k = 0; k < slrFormulaArray.size(); k++) {
			int flag = slrFormulaArray.get(k).getFlag();
			if (flag < slrFormulaArray.get(k).getNextString().length)// 不是规约状态
			{
				if (slrFormulaArray.get(k).getNextString()[flag].equals(currentInput))// 相同则加入 flag+1
				{
					slrFormulaArray3 = (ArrayList<SLRFormula>) slrFormulaArray.clone();
					SLRFormula slr = new SLRFormula();
					SLRFormula old = slrFormulaArray.get(k);
					slr.setBeforeString(old.getBeforeString());
					slr.setFlag(old.getFlag() + 1);
					slr.setNextString(old.getNextString());
					slrFormulaArray2.add(count, slr);
					slrFormulaArray2.get(count).setFlag(flag + 1);
					count++;
				}
			}

		}
		return slrFormulaArray2;

	}

	/**
	 * 根据已有的状态构造项目闭包
	 * 
	 * @param initFormulaArray
	 *            最开始的项目集闭包
	 * @param nowFormulaArray
	 *            当前的产生式集合
	 * @return 现在的加上添加的产生式集合
	 */
	// 当前下一个是否是初始状态的beforeString 是则加入
	public ArrayList<SLRFormula> CLOSURE(
			ArrayList<SLRFormula> initFormulaArray,
			ArrayList<SLRFormula> nowFormulaArray) {
		ArrayList<SLRFormula> slrFormulaArray2 = new ArrayList<SLRFormula>();
		int length = nowFormulaArray.size();
		// 遍历当前产生式集合的每一个产生式 看这个产生式的点后面是不是非终结符 是的话找一个一样的
		for (int k = 0; k < length; k++) {
			int flag = nowFormulaArray.get(k).getFlag();
			
			if (flag < nowFormulaArray.get(k).getNextString().length)// 不是规约状态
			{
				String next = nowFormulaArray.get(k).getNextString()[flag];// 点后面的字符
				
				if (!isTerminator(next))// 不是终结符 //还得看后面的后面！！！！
				{
					
					for (int i = 0; i < initFormulaArray.size(); i++)// 在初始状态找beforeString一样的
					{
						if (initFormulaArray.get(i).getBeforeString().equals(next))// 找到把相应的加入到产生式集合中
						{
							boolean a=isExitFormula(nowFormulaArray,initFormulaArray.get(i));
							
							if(!a)
							{
								nowFormulaArray.add(length, initFormulaArray.get(i));
							}
							
						
						}
					}
					/*
					 * slrFormulaArray.get(k).setFlag(flag+1);
					 * slrFormulaArray2.add(count,slrFormulaArray.get(k));
					 */
				}
			}
		}
		return nowFormulaArray;

	}

	//产生式是否存在
	public boolean isExitFormula(ArrayList<SLRFormula> theFormulaArray,SLRFormula theFormula)
	{
		for(int i=0;i<theFormulaArray.size();i++)
		{
			if(theFormulaArray.get(i).getBeforeString().equals(theFormula.getBeforeString()))
			{
				if(theFormulaArray.get(i).getFlag()==theFormula.getFlag())
				{
					String[] str1=theFormulaArray.get(i).getNextString();
					String[] str2=theFormula.getNextString();
					StringBuffer sb1 = new StringBuffer();
					StringBuffer sb2 = new StringBuffer();
					for (int y = 0; y < str1.length; y++) {
						sb1.append(str1[y]);
					}
					String s1 = sb1.toString();

					for (int y = 0; y < str2.length; y++) {
						sb2.append(str2[y]);
					}
					String s2 = sb2.toString();
					if (s1.equals(s2)) {
						return true;
					}
					
				}
			}
		}
		
		return false;
	}
	/**
	 * 判断是否存在状态与该状态一模一样 是就不再新建一个状态了
	 * 
	 * @param allState
	 *            已有的所有状态
	 * @param nowFormulaArray
	 *            要判断的状态
	 * @return 相同的状态号，没有相同的返回-1
	 */
	public int isExitTheSameState(ArrayList<ArrayList<SLRFormula>> allState,
			ArrayList<SLRFormula> nowFormulaArray) {
		// 对所以状态遍历 找到有相同产生式集合的状态 返回状态序号
		int len = nowFormulaArray.size();
		for (int i = 0; i < allState.size(); i++) {// 第i个状态
			int[] flag = new int[len];
			for (int k = 0; k < len; k++)// 当前产生式集合的第k个产生式
			{
				for (int j = 0; j < allState.get(i).size(); j++) {// 状态里的第j个产生式
																	// 如何找到就置1
																	// 开始下一次循环
					if (allState.get(i).get(j).getBeforeString()
							.equals(nowFormulaArray.get(k).getBeforeString())) {
						if (allState.get(i).get(j).getFlag() == nowFormulaArray
								.get(k).getFlag()) {
							String[] str1 = allState.get(i).get(j)
									.getNextString();
							String[] str2 = nowFormulaArray.get(k)
									.getNextString();
							StringBuffer sb1 = new StringBuffer();
							StringBuffer sb2 = new StringBuffer();
							for (int y = 0; y < str1.length; y++) {
								sb1.append(str1[y]);
							}
							String s1 = sb1.toString();

							for (int y = 0; y < str2.length; y++) {
								sb2.append(str2[y]);
							}
							String s2 = sb2.toString();
							if (s1.equals(s2)) {
								flag[k] = 1;// 第k个已找到
								break;// 跳出继续找下一个
							}
						}
					} else {
						flag[k] = 0;
					}
				}
			}
			boolean f = true;
			for (int x = 0; x < len; x++) {
				if (flag[x] != 1) {
					f = false;
					break;
				}
			}
			if (f&&allState.get(i).size()==len) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 判断两个状态是否一模一样 是就不再新建一个状态了
	 * 
	 * @param allState
	 *            已有的所有状态
	 * @param nowFormulaArray
	 *            要判断的状态
	 * @return 相同的状态号，没有相同的返回-1
	 */
	public boolean isTheSameState(ArrayList<SLRFormula> FormulaArray1,
			ArrayList<SLRFormula> FormulaArray2) {
		// 对所以状态遍历 找到有相同产生式集合的状态 返回状态序号
		int len = FormulaArray2.size();
		int[] flag = new int[len];
			for (int k = 0; k < len; k++)// 当前产生式集合的第k个产生式
			{
				for (int j = 0; j < FormulaArray1.size(); j++) {
					if (FormulaArray1.get(j).getBeforeString()
							.equals(FormulaArray2.get(k).getBeforeString())) {
						if (FormulaArray1.get(j).getFlag() == FormulaArray2
								.get(k).getFlag()) {
							String[] str1 =FormulaArray1.get(j)
									.getNextString();
							String[] str2 = FormulaArray2.get(k)
									.getNextString();
							StringBuffer sb1 = new StringBuffer();
							StringBuffer sb2 = new StringBuffer();
							for (int y = 0; y < str1.length; y++) {
								sb1.append(str1[y]);
							}
							String s1 = sb1.toString();

							for (int y = 0; y < str2.length; y++) {
								sb2.append(str2[y]);
							}
							String s2 = sb2.toString();
							if (s1.equals(s2)) {
								flag[k] = 1;// 第k个已找到
								break;// 跳出继续找下一个
							}
						}
					} else {
						flag[k] = 0;
					}
				}
			}
			boolean f = true;
			for (int x = 0; x < len; x++) {
				if (flag[x] != 1) {
					f = false;
					break;
				}
			}
			if (f&&FormulaArray1.size()==len) {
				return true;
			}
		return false;
	}

	/**
	 * 遇到规约状态 找到规定的表达式
	 * 
	 * @param slrFormula
	 *            当前要判断的为规约状态的产生式
	 * @param grammerTable
	 *            语法表
	 * @return 规约为哪一个语法产生式
	 */
	public int findActionNum(SLRFormula slrFormula, grammerTable[] grammerTable) {
		for (int i = 0; i < grammerTable.length; i++) {
			if (slrFormula.getBeforeString().equals(grammerTable[i].getName()))// 开始一样
			{
				boolean flag = true;
				for (int j = 0; j < grammerTable[i].getValue().length; j++) {
					if (!grammerTable[i].getValue()[j].equals(slrFormula
							.getNextString()[j])) {
						flag = false;
						break;
					}
				}
				if(grammerTable[i].getValue().length!=slrFormula.getNextString().length)
				{
					flag = false;
				}
				if (flag) {
					return i;
				}
			}

		}
		return -1;
	}
	
	public ArrayList<ACTIONTable> getSLRTable(grammerTable[] grammerTable) throws Exception {
		int stateCount = 0;
		// new action表和 goto表
		ArrayList<ACTIONTable> actionTable = new ArrayList<ACTIONTable>();
		ArrayList<GOTOTable> gotoTable = new ArrayList<GOTOTable>();
		ArrayList<ArrayList<SLRFormula>> slrStateArray = new ArrayList<ArrayList<SLRFormula>>();
		ArrayList<SLRFormula> slrFormulArray0 = new ArrayList<SLRFormula>();
		ArrayList<SLRFormula> slrFormulArray1 = new ArrayList<SLRFormula>();
		ArrayList<SLRFormula> slrFormulArray11 = new ArrayList<SLRFormula>();
		String[][] followGroup=getFollowGroup();
		
		// 初始化 第一个产生式集合
		for (int i = 0; i < grammerTable.length; i++) {
			SLRFormula slrFormula = new SLRFormula();
			slrFormula.setBeforeString(grammerTable[i].getName());
			slrFormula.setNextString(grammerTable[i].getValue());
			slrFormula.setFlag(0);
			slrFormulArray0.add(i, slrFormula);
		}
		slrStateArray.add(stateCount, slrFormulArray0);// 多一个状态
		stateCount++;
		
		SLRFormula slrFormula2 = new SLRFormula();
		slrFormula2.setBeforeString("Z");//增广式 Z->P
		String bb="P";
		slrFormula2.setNextString(bb.split(" "));
		slrFormula2.setFlag(0);
		slrFormulArray1.add(0, slrFormula2);
		boolean isEnough;
		int arrayLen=0;
		do{
			isEnough=true;
			arrayLen=slrFormulArray1.size();
			CLOSURE(slrStateArray.get(0), slrFormulArray1);
			if(arrayLen!=slrFormulArray1.size())
			{
				isEnough=false;
			}
		}while(!isEnough);
		
		slrStateArray.add(stateCount, slrFormulArray1);// 多一个状态
		stateCount++;
		
		for(int k=0;k<slrStateArray.size();k++)
		{ 
			for (int i = 0; i < slrStateArray.get(k).size(); i++) {
				//System.out.print(slrStateArray.get(k).get(i).getBeforeString() + " "+ " ");
				for (int j = 0; j < slrStateArray.get(k).get(i).getNextString().length; j++)
	            {
				//	System.out.print(slrStateArray.get(k).get(i).getNextString()[j] + " ");
				}
			  //  System.out.println(" " + slrStateArray.get(k).get(i).getFlag());
		   }
		//	System.out.println("==================================");
		}
	    // 验证结束
	//	System.out.println("==================================");

		String currentInput = null;
		// 对每一个状态 针对输入 到下一个状态
		//初始化状态为1
		for (int i = 1; i < slrStateArray.size(); i++) {
			for (int j = 0; j < slrStateArray.get(i).size(); j++) 
			{
				
				int flag = slrStateArray.get(i).get(j).getFlag();// 产生式的标识位
				// flag 是最后一个 及是规约状态
				if(flag >= slrStateArray.get(i).get(j).getNextString().length)
				{
					//规约
					//把follow中的都规约
					//System.out.println("状态 "+i+"规约");
					//System.out.println(slrStateArray.get(i).get(j).getBeforeString());
					if(slrStateArray.get(i).get(j).getBeforeString().equals("Z"))
					{
						ACTIONTable ac = new ACTIONTable();
						ac.setInput("$");
						ac.setState(i);
						String a = "acc "+0;
						// 根据当前的式子找到规约式子的序号
						ac.setAction(a.split(" "));
						actionTable.add(ac);
						continue;
					}
					int loca=findFollow(followGroup,slrStateArray.get(i).get(j).getBeforeString());
					//System.out.println(loca);
					String follow[]=followGroup[loca][1].split(" ");
					for(int q=0;q<follow.length;q++)
					{
						if(follow[q].equals(""))
						{
							continue;
						}
						ACTIONTable ac = new ACTIONTable();
						ac.setInput(follow[q]);
						ac.setState(i);
						int num = findActionNum(slrStateArray.get(i).get(j),grammerTable);
						String a = "r " + num;
						// 根据当前的式子找到规约式子的序号
						ac.setAction(a.split(" "));
						if(!isExitAction(actionTable,ac)){
							actionTable.add(ac);
						}
					}
					continue;
			  }
					// 输入为点的下一个字符 这里应有判断是否已输入过 ||之后判断是否有两个一模一样的状态
					currentInput = slrStateArray.get(i).get(j).getNextString()[flag];// 点的下一个字符
					//是否为空 即 A->no  0
					if(currentInput.equals("no"))
					{
						//c->no 找到c的follow集 添加规约
						int loca=findFollow(followGroup,slrStateArray.get(i).get(j).getBeforeString());
						
						String follow[]=followGroup[loca][1].split(" ");
						for(int q=0;q<follow.length;q++)
						{
							if(follow[q].equals(""))
							{
								continue;
							}
							ACTIONTable ac = new ACTIONTable();
							ac.setInput(follow[q]);
							ac.setState(i);
							int num = findActionNum(slrStateArray.get(i).get(j),grammerTable);
							String a = "r " + num;
							// 根据当前的式子找到规约式子的序号
							ac.setAction(a.split(" "));
							if(!isExitAction(actionTable,ac)){
								actionTable.add(ac);
							}
						}
						continue;
					}
					//当前产生式的下一个集合
					/*ArrayList<SLRFormula> newFormulaArray=new ArrayList<SLRFormula>();
					newFormulaArray=nextFormulaArray(slrStateArray.get(i),slrStateArray.get(0));
					for(int x=0;x<newFormulaArray.size();x++)
					{
						newFormulaArray=nextFormulaArray(newFormulaArray,slrStateArray.get(0));
					}*/
					ArrayList<SLRFormula> slrFormulaDemo = GOTO(currentInput,
							slrStateArray.get(i));
					boolean isEnough2;
					ArrayList<SLRFormula> slrFormulaDemo2=new ArrayList<SLRFormula>();
					int arrayLen2=0;
					do{
						isEnough2=true;
						arrayLen2=slrFormulaDemo.size();
					//	System.out.println("当前长度 "+arrayLen2);
						slrFormulaDemo2 = CLOSURE(slrStateArray.get(0), slrFormulaDemo);
					//	System.out.println("之后长度 "+slrFormulaDemo2.size());
						if(arrayLen2!=slrFormulaDemo2.size())
						{
							isEnough2=false;
						}
					}while(!isEnough2);
					//ArrayList<SLRFormula> slrFormulaDemo2 = CLOSURE(slrStateArray.get(0), slrFormulaDemo);
					/*for(int x=0;x<slrFormulaDemo2.size();x++)
					{
						slrFormulaDemo2=CLOSURE(slrStateArray.get(0),slrFormulaDemo2);
					}*/
					//这个产生式集合是否已经存在相同的集合了  是返回状态号  没有返回-1
					int sameState = isExitTheSameState(slrStateArray,slrFormulaDemo2);
					
					if (sameState == -1)// 判断这个状态是不是已经有啦 没有
					{
						//System.out.println("不存在相同状态=====================================");
						slrStateArray.add(stateCount, slrFormulaDemo2);// 多一个状态
						stateCount++;
					} else {
						//System.out.println("存在相同状态====================================="+ sameState);
					}
					if (isTerminator(currentInput))// 是终结符 加入action表
					{
						ACTIONTable ac = new ACTIONTable();
						ac.setInput(currentInput);
						ac.setState(i);
						String a;
						if (sameState == -1) {
							a = "s " + (stateCount-1);
						} else {
							a = "s " + sameState;
						}
						ac.setAction(a.split(" "));
						if(!isExitAction(actionTable,ac)){
							actionTable.add(ac);
						}
					} else {// 为非终结符加入goto表
						ACTIONTable ac = new ACTIONTable();
						ac.setInput(currentInput);
						ac.setState(i);
						String a;
						if (sameState == -1) {
							a = "h " + (stateCount-1);
						} else {
							a = "h " + sameState;
						}
						ac.setAction(a.split(" "));
						if(!isExitAction(actionTable,ac)){
							actionTable.add(ac);
						}
					}
			}

		}
		for (int i = 0; i < slrStateArray.size(); i++) {
		//	System.out.println("状态 " + i);
			for (int j = 0; j < slrStateArray.get(i).size(); j++) {
			//	System.out.print("|  "+ slrStateArray.get(i).get(j).getBeforeString()+ "-> ");
				int a = slrStateArray.get(i).get(j).getNextString().length;
				for (int k = 0; k < a; k++) {
			//		System.out.print(slrStateArray.get(i).get(j).getNextString()[k]+ " ");
				}
			//	System.out.print(slrStateArray.get(i).get(j).getFlag() + "  |");
			}
		//	System.out.println();
		}
	//	System.out.println("actionTable.size()========"+actionTable.size());
		actionTable=addError(actionTable,slrStateArray);
		ActionTest(actionTable);
		return actionTable;
	}
	
	public ArrayList<ACTIONTable> addError(ArrayList<ACTIONTable> actionTable,ArrayList<ArrayList<SLRFormula>> slrStateArray){
		for(int k=1;k<slrStateArray.size();k++)
		{
			boolean isExit1=false;
			boolean isExit11=false;
			boolean isExit111=false;
			boolean isExit2=false;
			boolean isExit3=false;
			boolean isExit33=false;
			boolean isExit333=false;
			//boolean isExit4=false;
			for(int q=0;q<actionTable.size();q++)
			{
				if(actionTable.get(q).getState()==k)
				{
					if(actionTable.get(q).getInput().equals("+"))
					{
						isExit1=true;
					}
					if(actionTable.get(q).getInput().equals("*"))
					{
						isExit11=true;
					}
					if(actionTable.get(q).getInput().equals("$"))
					{
						isExit111=true;
					}
					else if(actionTable.get(q).getInput().equals(")"))
					{
						isExit2=true;
					}
					if(actionTable.get(q).getInput().equals("id")||actionTable.get(q).getInput().equals("digit")||actionTable.get(q).getInput().equals("("))
					{
						isExit3=true;
					}
					if(actionTable.get(q).getInput().equals("digit"))
					{
						isExit33=true;
					}
					if(actionTable.get(q).getInput().equals("("))
					{
						isExit333=true;
					}
					
			
				}
				
			}
			if(!isExit1)
			{
				ACTIONTable table=new ACTIONTable();
				table.setState(k);
				table.setInput("+");
				String a = "e " +1;
				table.setAction(a.split(" "));
				actionTable.add(table);
			}
			if(!isExit11)
			{
				ACTIONTable table2=new ACTIONTable();
				table2.setState(k);
				table2.setInput("*");
				String b = "e " +1;
				table2.setAction(b.split(" "));
				actionTable.add(table2);
			}
			if(!isExit111)
			{
				ACTIONTable table3=new ACTIONTable();
				table3.setState(k);
				table3.setInput("$");
				String c = "e " +1;
				table3.setAction(c.split(" "));
				actionTable.add(table3);
			}
			if(!isExit2)
			{
				ACTIONTable table=new ACTIONTable();
				table.setState(k);
				table.setInput(")");
				String a = "e " +2;
				table.setAction(a.split(" "));
				actionTable.add(table);
			}
			if(!isExit3)
			{
				ACTIONTable table=new ACTIONTable();
				table.setState(k);
				table.setInput("id");
				String a = "e " +3;
				table.setAction(a.split(" "));
				actionTable.add(table);
				
				
			}
			if(!isExit33)
			{
				ACTIONTable table2=new ACTIONTable();
				table2.setState(k);
				table2.setInput("digit");
				String b = "e " +3;
				table2.setAction(b.split(" "));
				actionTable.add(table2);
			}
			if(!isExit333)
			{
				ACTIONTable table3=new ACTIONTable();
				table3.setState(k);
				table3.setInput("(");
				String c = "e " +3;
				table3.setAction(c.split(" "));
				actionTable.add(table3);
			}
			
		}
		return actionTable;
	}
	public void ActionTest(ArrayList<ACTIONTable> actionTable){
		for (int i = 0; i < actionTable.size(); i++) {
			//System.out.print("状态: " + actionTable.get(i).getState());
		//	System.out.print("  输入 :" + actionTable.get(i).getInput());
			String[] str1 =actionTable.get(i).getAction();
			StringBuffer sb1 = new StringBuffer();
			for (int y = 0; y < str1.length; y++) {
				if(!str1[y].equals("h"))
				{
					sb1.append(str1[y]);
				}
				
			}
			String s1 = sb1.toString();
		//	System.out.println("  转换:" + s1);
		//	System.out.println("  行数   " + actionTable.get(i).getState());
		//	System.out.println("  列数   " + getColumn(actionTable.get(i).getInput()));
		//	slrListTbModel.setValueAt(s1,actionTable.get(i).getState(), getColumn(actionTable.get(i).getInput()));
		//	System.out.println();
		}
	}

	public int getColumn(String str){
		switch (str){
		case "+":
			return 1;
		case "*":
			return 2;
		case "-":
			return 3;
		case "(":
			return 4;
		case "id":
			return 5;
		case "digit":
			return 6;
		case "=":
			return 7;
		case "call":
			return 8;
		case ")":
			return 9;
		case "[":
			return 10;
		case "]":
			return 11;
		case ",":
			return 12;
		case ";":
			return 13;
		case ">":
			return 14;	
		case "true":
			return 15;
		case "false":
			return 16;
		case "not":
			return 17;
		case "and":
			return 18;
		case "or":
			return 19;
		case "if":
			return 20;
		case "then":
			return 21;
		case "else":
			return 22;
		case "while":
			return 23;
		case "do":
			return 24;
		case "proc":
			return 25;
		case "record":
			return 26;
		case "integer":
			return 27;
		case "real":
			return 28;
		case "$":
			return 29;
		case "S":
			return 30;
		case "E":
			return 31;
		case "L":
			return 32;
		case "F":
			return 33;
		case "B":
			return 34;
		case "P":
			return 35;
		case "D":
			return 36;
		case "X":
			return 37;
		case "C":
			return 38;
		case "T":
			return 39;
		default:
			return 0;
		}
		//return -1;
	}
	public boolean isExitAction(ArrayList<ACTIONTable> actionTable,ACTIONTable action)
	{
		for(int i=0;i<actionTable.size();i++)
		{
			if(actionTable.get(i).getState()==action.getState()&&actionTable.get(i).getInput().equals(action.getInput()))
			{
				String[] str1 =actionTable.get(i).getAction();
				StringBuffer sb1 = new StringBuffer();
				for (int y = 0; y < str1.length; y++) {
					sb1.append(str1[y]);
				}
				String s1 = sb1.toString();
				String[] str2 =action.getAction();
				StringBuffer sb2 = new StringBuffer();
				for (int y = 0; y < str2.length; y++) {
					sb2.append(str2[y]);
				}
				String s2 = sb2.toString();
				if(s2.equals(s1))
				{
					return true;
				}
			}
		}
        return false;
	}
	public boolean isExitGoto(ArrayList<GOTOTable> gotoTable ,GOTOTable g)
	{
		for(int i=0;i<gotoTable.size();i++)
		{
			if(gotoTable.get(i).getState()==g.getState()&&gotoTable.get(i).getInput().equals(g.getInput())&&g.getNextState()==gotoTable.get(i).getNextState())
			{
					return true;
			}
		}
		return false;
	}
	
	
	public String[] findNext (ArrayList<ACTIONTable> slrTable,int state,String input)
	{
		String[] a=new String[2];
		if(state==77)
		{
			for(int i=slrTable.size()-1;i>=0;i--)
			{
				if(slrTable.get(i).getState()==state&&slrTable.get(i).getInput().equals(input))
				{
					for(int j=0;j<slrTable.get(i).getAction().length;j++)
					{
						a[j]=slrTable.get(i).getAction()[j];
					}
					
					return a; 
				}
			}
		}
		for(int i=0;i<slrTable.size();i++)
		{
			if(slrTable.get(i).getState()==state&&slrTable.get(i).getInput().equals(input))
			{
				//System.out.println("i="+i);
			//	System.out.println("slrTable.get(i).getInput()"+slrTable.get(i).getInput());
				for(int j=0;j<slrTable.get(i).getAction().length;j++)
				{
					//System.out.println("action wei");
					//System.out.println(slrTable.get(i).getAction()[j]);
				//	System.out.println(slrTable.get(i).getAction()[j]);
					a[j]=slrTable.get(i).getAction()[j];
				}
				
				return a; 
			}
		}
		
		//System.out.println("没找到");
		return a;
	}
	
	public ArrayList<SLRTree> slrTest(ArrayList<String[]> input,ArrayList<ACTIONTable> slrTable) throws Exception{
		while (syntaxListTbModel.getRowCount() > 0) {
			syntaxListTbModel.removeRow(0);
		}
		while (grammererrorListTbModel.getRowCount() > 0) {
			grammererrorListTbModel.removeRow(0);
		}
		//addrArea.setText("");
		ArrayList<SLRTree> slrTreeArray=new ArrayList<SLRTree>();
		String inputStr=" ";
		ArrayList<String> output=new ArrayList<String>();
		for(int i=0;i<input.size();i++){
		 SLRTree slrTree =new SLRTree();
		 slrTree.setName(input.get(i)[0]);
		 String a="-1";
		 String[] b=a.split(" ");
		 slrTree.setChildId(b);
		 slrTreeArray.add(slrTree);
	     }
		 for(int i=0;i<input.size();i++){
			 if(input.get(i)[0].equals("$")||isKeyword(input.get(i)[0]))//call interger real and not or proc record
			 {
				 inputStr +=input.get(i)[0]+" ";
			 }
			 else if(input.get(i)[1].equals("关键字")||input.get(i)[1].equals("标识符")){
				 inputStr +="id"+" ";
				 SLRTree slrTree2 =new SLRTree();
				 slrTree2.setName("id");
				 String aa=String.valueOf(i);
				 String[] bb=aa.split(" ");
				 slrTree2.setChildId(bb);
				 slrTreeArray.add(slrTree2);
			 }
			 else if(input.get(i)[1].indexOf("数")!=-1)
			 {
				 inputStr +="digit"+" ";
				 SLRTree slrTree2 =new SLRTree();
				 slrTree2.setName("digit");
				 String aa=String.valueOf(i);
				 String[] bb=aa.split(" ");
				 slrTree2.setChildId(bb);
				 slrTreeArray.add(slrTree2);
			 }
			 /*else if(input.get(i)[1].equals("运算符"))
			 {
				 inputStr +="relop"+" ";
			 }*/
			 else if(input.get(i)[1].equals("注释"))
			 {
				 inputStr +=input.get(i)[0]+" ";
			 }
			 else{
				 inputStr +=input.get(i)[0]+" ";
			 }
		 }
		 System.out.println("inputStr是"+inputStr);
		// System.out.println(inputStr);
		 grammerTable[] grammerTable = new readDFATable().Wenfa();
		//用于标记树的栈
		 Stack<Integer>  treeStack = new Stack<Integer>(); 
		 treeStack.push(-1);
		 //状态栈
		 Stack<Integer> stateStack = new Stack<Integer>();
		 stateStack.push(1);
		 //符号栈
		 Stack<String> charStack = new Stack<String>();
		 charStack.push("$");
		 //输入栈
		 Stack<String> inputStack = new Stack<String>(); 
		 inputStack.push("$");
		 
		 String[] inputStr2=inputStr.split(" ");
		 for(int i=inputStr2.length-1;i>0;i--)
		 {
		//	 System.out.print(inputStr2[i]+"  ");
			 inputStack.push(inputStr2[i]);
			 //System.out.println("输入栈顶部状态："+inputStack.peek()); 
		 }
		 System.out.println();
		 /********************初始化完成***********************/
		 System.out.println("测试开始======================");
		 int sign=0;
		 boolean ise1=false;
		 do{
			 System.out.println("======================");
			 System.out.println("状态栈顶部状态："+stateStack.peek());
			 System.out.println("符号栈顶部状态："+charStack.peek());
			 System.out.println("输入栈顶部状态："+inputStack.peek()); 
			 System.out.println("树栈顶部状态："+treeStack.peek()); 
			 String next[]=findNext(slrTable,stateStack.peek(),inputStack.peek());
			 System.out.println("======================");
			 if(next[0]==null)
			 {
				 System.out.println("没找到  "+inputStack.peek());
				 inputStack.pop();
				 sign=sign+1;
				 continue;
			 }
			 if(next[0].equals("acc"))
			 {
				 System.out.println("成功！！！！！！！！！！！！！！");
				// syntaxListTbModel.addRow(new Object[] { output.get(j), "第"+j+"行" });
				 break;
			 }
			 //s 3移进   输入栈第一个到符号栈最上面 ， 状态栈为下一个状态
			 if(next[0].equals("s"))
			 {
				 System.out.print("移进");
				 System.out.println("   下一状态："+next[1]);
				 charStack.push(inputStack.peek());
				 inputStack.pop();
				 if(ise1)
				 {
					 treeStack.push(slrTreeArray.size()-2);
					 ise1=false; 
				 }
				 else{
					 treeStack.push(getFatherId(slrTreeArray,sign));
					 sign=sign+1;
					 ise1=false;
				 }
				 stateStack.push(Integer.parseInt(next[1]));
				 
				 
			 }
			 //r 3 规约   输入栈第一个到符号栈最上面 ， 状态栈为下一个状态
			 else if(next[0].equals("r"))//这里要画树
			 {
				 System.out.println("规约");
				 int num = Integer.parseInt(next[1]);
				 String out="";
				 String tree="";
				 if(!grammerTable[num].getValue()[0].equals("no"))
				 {
					 int len=grammerTable[num].getValue().length;
					 for(int k=0;k<len;k++)
					 {
						 out=charStack.peek()+" "+out;
						 tree=tree+treeStack.peek()+" ";
						 charStack.pop();
						 stateStack.pop();
						 treeStack.pop();
					 }
				 }
				 if(tree.equals(""))
				 {
					 tree="-1";
				 }
				 SLRTree slrTree =new SLRTree();
				 slrTree.setName(grammerTable[num].getName());
				 String a=tree;
				 String[] b=a.split(" ");
				 slrTree.setChildId(b);
				 slrTreeArray.add(slrTree);
				 
				 syntaxListTbModel.addRow(new Object[] { grammerTable[num].getName()+"->"+out});
				 charStack.push(grammerTable[num].getName());
				 String next2[]=findNext(slrTable,stateStack.peek(),charStack.peek());
				 stateStack.push(Integer.parseInt(next2[1]));
				 treeStack.push(slrTreeArray.size()-1);
			 }
			 else if(next[0].equals("e")){
				 int num = Integer.parseInt(next[1]);
				 System.out.println("出错 "+num);
				 if(num==2)
				 {
					 grammererrorListTbModel.addRow(new Object[]{"第"+getLine(sign)+"行错误",inputStack.peek(),"不匹配右括号"});
					 inputStack.pop();
					 sign=sign+1;
				 }
				 else if(num==1)
				 {
					 grammererrorListTbModel.addRow(new Object[]{"第"+getLine(sign)+"行错误",inputStack.peek(),"缺少运算分量",""});
					 inputStack.push("id");
					 SLRTree slrTree =new SLRTree();
					 slrTree.setName("id");
					 String a="-1";
					 String[] b=a.split(" ");
					 slrTree.setChildId(b);
					 slrTreeArray.add(slrTree);
					 ise1=true;
				 }
				 else if(num==3)
				 {
					 grammererrorListTbModel.addRow(new Object[]{"第"+getLine(sign)+"行错误",inputStack.peek(),"缺少运算符"});
					 inputStack.push("+");
					 SLRTree slrTree =new SLRTree();
					 slrTree.setName("+");
					 String a="-1";
					 String[] b=a.split(" ");
					 slrTree.setChildId(b);
					 slrTreeArray.add(slrTree);
					 ise1=true;
				 }
				 
			 }
		 }while(true);
		 for(int i=0;i<slrTreeArray.size();i++)
		 {
			 System.out.print("序号 "+i);
			 System.out.print(" 元素"+slrTreeArray.get(i).getName());
			 for(int j=0;j<slrTreeArray.get(i).getChildId().length;j++)
			 {
				 System.out.print("  孩子编号："+slrTreeArray.get(i).getChildId()[j]);
			 }
			 System.out.println();
		 }
		 for(int i=0;i<slrTreeArray.size();i++)
		 {
			 System.out.print("序号 "+i);
			 System.out.print(" 元素"+slrTreeArray.get(i).getName());
			 System.out.print("  孩子：");
			 for(int j=slrTreeArray.get(i).getChildId().length-1;j>=0;j--)
			 {
				 if(slrTreeArray.get(i).getChildId()[j].equals("-1"))
				 {
					 System.out.print("无");
					 break;
				 }
				 System.out.print(" "+slrTreeArray.get(Integer.parseInt(slrTreeArray.get(i).getChildId()[j])).getName());
			 }
			 System.out.println();
		 }
		// getAnalysisTree(slrTreeArray);
		return slrTreeArray;
		 }
	
	
	public int getFatherId(ArrayList<SLRTree> slrTreeArray,int childId){
		for(int i=0;i<slrTreeArray.size();i++)
		 {
			for(int j=0;j<slrTreeArray.get(i).getChildId().length;j++)
			 {
				//System.out.println("slrTreeArray.get(i).getChildId()[j]="+slrTreeArray.get(i).getChildId()[j]);
				if(slrTreeArray.get(i).getChildId()[j].equals(String.valueOf(childId)))
				{
					return i;
				}
			 }
		 }
		return childId;
	}
	
	/**
	 * 获得树模型
	 * @param slrTreeArray
	 * @param num
	 * @return
	 */
	public DefaultTreeModel getTreeModel(ArrayList<SLRTree> slrTreeArray,int num){
		//System.out.println(num);
		String fatherStr=slrTreeArray.get(num).getName();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(fatherStr);
		//System.out.println("root="+fatherStr);
		DefaultTreeModel treeModel1 = new DefaultTreeModel(root);
		for(int j=0;j<slrTreeArray.get(num).getChildId().length;j++)
		{
			int childID=Integer.parseInt(slrTreeArray.get(num).getChildId()[j]);
			
		//	System.out.println("root childID:"+childID);
			if(childID!=-1)
			{
				String childName=slrTreeArray.get(childID).getName();
				DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(childName);
				//DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(childID);
			//	treeModel1.insertNodeInto(childNode,root,root.getChildCount());
				//getMutableTreeNode(childNode,childID,slrTreeArray);
				treeModel1.insertNodeInto(
						getMutableTreeNode(childNode,childID,slrTreeArray),
						root,
						j);
				//System.out.println("treeModel1成功");
			}
		}
		return treeModel1;
	}
	/**
	 * 获得树结点
	 * @param fathNode
	 * @param num
	 * @param slrTreeArray
	 * @return
	 */
	public MutableTreeNode getMutableTreeNode(MutableTreeNode fathNode,int num,ArrayList<SLRTree> slrTreeArray){
		//System.out.println("**************开始************");
	//	System.out.println("fathNode:"+fathNode);
		for(int j=0;j<slrTreeArray.get(num).getChildId().length;j++)
		{
		//	System.out.println("j="+j);
			int childID=Integer.parseInt(slrTreeArray.get(num).getChildId()[j]);
			
		//	System.out.println("childID="+childID);
			if(childID!=-1)
			{
				String childName=slrTreeArray.get(childID).getName();
				DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(childName);
				MutableTreeNode childchildNode=getMutableTreeNode(childNode,childID,slrTreeArray);
				fathNode.insert(childchildNode,fathNode.getChildCount());
			//	System.out.println("fathNode"+fathNode);
			}
		}
		return fathNode;
	}
	
	/**
	 * 获得当前行数
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public int getLine(int count) throws Exception{
		String str=textArea.getText();
		String[] a=str.split("\n");
		int len=0;
		for(int i=0;i<a.length;i++)
		{
			len+=lexicalAnalysis2(a[i]).size();
			if(len>count)
			{
				return i+1;
			}
		}
		return 0;
	}
	
	/**
	 * 语义分析开始
	 * @throws Exception ****************************
	 */
	
public void initRecord(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray){
		
		for(int i=0;i<slrTreeArray.size();i++)
		{
			int firstchildID=Integer.parseInt(slrTreeArray.get(i).getChildId()[0]);
			if(firstchildID==-1)
			{
				int fatherId=getFatherId(slrTreeArray,i);
				String fatherName=slrTreeArray.get(fatherId).getName();
				if(fatherName.equals("id")||fatherName.equals("digit"))
				{
					RreeSemanticRecord r=new RreeSemanticRecord();
					r.setTreeNodeNum(fatherId);
					r.setTreeNodeName(fatherName);
					r.setProperty("lexeme");
					r.setValue(slrTreeArray.get(i).getName());
					treeSemanticRecord.add(r);
				}
			}
		}
		RreeSemanticRecord r=new RreeSemanticRecord();
		r.setTreeNodeNum(slrTreeArray.size()+1);
		r.setTreeNodeName("t");
		r.setProperty("t");
		r.setValue("ceshi");
		treeSemanticRecord.add(r);
	}
	/**
	 * 
	 * @param grammerTable产生式表
	 * @param slrTreeArray语义分析得到的树
	 * @param treeSemanticRecord记录树各个结点信息的集合
	 * @param grammersemanticLoca读文档的表记录每个产生式的哪里有语义片段
	 * @param num当前树结点
	 * @throws Exception
	 */
	public void semanticTest(grammerTable[] grammerTable,ArrayList<SLRTree> slrTreeArray,ArrayList<RreeSemanticRecord> treeSemanticRecord,
			grammerSemanticLoca[] grammersemanticLoca,ArrayList<String> addrList,
			ArrayList<String> addrResult,ArrayList<AddrNum> addrNum,
			ArrayList<CharTable> charTable,ArrayList<FourAddr> fourAddr,ArrayList<String> param,int num) throws Exception{
		
		//找到对应的产生式
		System.out.println(" "+slrTreeArray.get(num).getName());
		System.out.println("num:"+num);
		
		int length=slrTreeArray.get(num).getChildId().length;//孩子结点的个数
		//System.out.println("孩子个数length:"+length);
		String beforString = slrTreeArray.get(num).getName();//结点的名字
		String nextString="";
		for(int j=length-1;j>=0;j--)
		 {
			 if(slrTreeArray.get(num).getChildId()[j].equals("-1"))
			 {
				 nextString="no";
				 break;
			 }
			 nextString+=slrTreeArray.get(Integer.parseInt(slrTreeArray.get(num).getChildId()[j])).getName()+" ";
		 }
		//System.out.println("孩子字符串："+nextString);
		SLRFormula formula=new SLRFormula();
		formula.setBeforeString(beforString);
		formula.setFlag(0);
		formula.setNextString(nextString.split(" "));
		int grammerNum=findActionNum(formula,grammerTable);//文法表的产生式序号
		System.out.println("产生式的编号："+grammerNum);
		//语法分析开始
		
		//对产生式的每一部分
		for(int i=0;i<length;i++){
			//当前位置有程序片段
			//num节点编号，grammarNum产生式编号，i产生式的位置
			record(grammersemanticLoca,slrTreeArray, treeSemanticRecord,addrList,addrResult, addrNum, charTable, fourAddr, param, num,grammerNum,i);
        	
        	int firstchildID=Integer.parseInt(slrTreeArray.get(num).getChildId()[0]);
        	if(firstchildID!=-1)//自己是叶节点，结束
        	{
        	//	System.out.println("不是叶节点");
        		//对当前子节点，如果有孩子结点，递归，无孩子结点，跳过
            	int childID=Integer.parseInt(slrTreeArray.get(num).getChildId()[length-1-i]);
            //	System.out.println("====孩子结点:"+childID);
            	semanticTest(grammerTable,slrTreeArray,treeSemanticRecord,grammersemanticLoca,addrList, addrResult, addrNum, charTable, fourAddr, param, childID);
        	}
		}
		//最后是否也有语义片段
		//System.out.println("产生式最后");
		record(grammersemanticLoca,slrTreeArray, treeSemanticRecord,addrList,addrResult, addrNum, charTable, fourAddr, param, num,grammerNum,length);
       
	}
	
	/**
	 * 获得当前产生式当前位置是否有语义规则片段，有则返回片段号，无则返回-1
	 * @param grammerNum
	 * @param loc
	 * @param grammersemanticLoca
	 * @return
	 */
	public int isExitSemanticRule(int grammerNum,int loc,grammerSemanticLoca[] grammersemanticLoca){
		for(int i=0;i<grammersemanticLoca.length;i++)
		{
			if(grammersemanticLoca[i].getGrammerNum()==grammerNum&&grammersemanticLoca[i].getRulelLoc()==loc)
			{
				return grammersemanticLoca[i].getRuleNum();
			}
		}
		return -1;
	}
	
	public void record(grammerSemanticLoca[] grammersemanticLoca,ArrayList<SLRTree> slrTreeArray,
			ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<String> addrList,
			ArrayList<String> addrResult,ArrayList<AddrNum> addrNum,ArrayList<CharTable> charTable,
			ArrayList<FourAddr> fourAddr,ArrayList<String> param,int treeNodeNum,int grammerNum,int loc){
		
		int ruleNum=isExitSemanticRule(grammerNum,loc,grammersemanticLoca);
		if(ruleNum!=-1)
		{
			System.out.println("产生式"+grammerNum+" 位置"+loc+" 的语义片段序号："+ruleNum);
		}
		switch(ruleNum){
		case 1:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_1(treeSemanticRecord,slrTreeArray,treeNodeNum);
			//testRecord(treeSemanticRecord);
			break;
		case 2:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_2(treeSemanticRecord,slrTreeArray,treeNodeNum);
			//testRecord(treeSemanticRecord);
			break;
		case 3:
			System.out.println("第"+ruleNum+"个");
			String s3=new semanticRule().semanticRule_3(treeSemanticRecord,slrTreeArray,charTable, fourAddr, treeNodeNum);
			//testRecord(treeSemanticRecord);
			String[] e3=s3.split(" ");
			if(e3[0].equals("error"))
			{
				semerrorListTbModel.addRow((new Object[]{3,"变量不匹配",e3[2]+" "+e3[3]}));
				break;
			}
			addrResult.add(s3);
			break;
		case 4:
			System.out.println("第"+ruleNum+"个");
			String s4=new semanticRule().semanticRule_4(treeSemanticRecord,slrTreeArray,charTable, fourAddr, treeNodeNum);
			//testRecord(treeSemanticRecord);
			String[] e4=s4.split(" ");
			if(e4[0].equals("error"))
			{
				semerrorListTbModel.addRow((new Object[]{3,"变量不匹配",e4[2]+" "+e4[3]}));
				break;
			}
			addrResult.add(s4);
			break;
		case 5:
			System.out.println("第"+ruleNum+"个");
			String s5=new semanticRule().semanticRule_5(treeSemanticRecord,slrTreeArray,fourAddr, treeNodeNum);
			//testRecord(treeSemanticRecord);
			
			addrResult.add(s5);
			break;
		case 6:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_6(treeSemanticRecord,slrTreeArray,treeNodeNum);
			 //testRecord(treeSemanticRecord);
			break;
		case 7:
			System.out.println("第"+ruleNum+"个");
			String e7=new semanticRule().semanticRule_7(treeSemanticRecord,slrTreeArray,charTable, treeNodeNum);
			// testRecord(treeSemanticRecord);
			String[] e77=e7.split(" ");
			if(e77[0].equals("error"))
			{
				semerrorListTbModel.addRow((new Object[]{1,"变量未定义",e77[2]}));
			}
			break;
		case 8:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_8(treeSemanticRecord,slrTreeArray,treeNodeNum);
			//testRecord(treeSemanticRecord);
			break;
		case 9:
			System.out.println("第"+ruleNum+"个");
			String s9=new semanticRule().semanticRule_9(treeSemanticRecord,slrTreeArray,fourAddr, treeNodeNum);
			//testRecord(treeSemanticRecord);
			
			addrResult.add(s9);
		case 10:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_10(treeSemanticRecord,slrTreeArray,treeNodeNum);
			//testRecord(treeSemanticRecord);
			break;
		case 11:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_11(treeSemanticRecord,slrTreeArray,treeNodeNum,addrList);
			//testRecord(treeSemanticRecord);
			break;
		case 12:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_12(treeSemanticRecord,slrTreeArray,treeNodeNum, addrResult, addrNum);
			//testRecord(treeSemanticRecord);
			testAddr(addrNum);
			break;
		case 13:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_13(treeSemanticRecord,slrTreeArray, addrList, treeNodeNum);
			//testRecord(treeSemanticRecord);
			break;
		case 14:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_14(treeSemanticRecord,slrTreeArray,addrResult, addrNum, treeNodeNum);
			//testRecord(treeSemanticRecord);
			testAddr(addrNum);
			break;
		case 15:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_15(treeSemanticRecord,slrTreeArray, addrList, treeNodeNum);
			//testRecord(treeSemanticRecord);
			break;
		case 16:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_16(treeSemanticRecord,slrTreeArray,addrResult, addrNum, treeNodeNum);
			//testRecord(treeSemanticRecord);
			testAddr(addrNum);
			break;
		case 17:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_17(treeSemanticRecord,slrTreeArray,treeNodeNum);
			//testRecord(treeSemanticRecord);
			break;
		case 18:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_18(treeSemanticRecord,slrTreeArray,treeNodeNum);
			//testRecord(treeSemanticRecord);
			break;
		case 19:
			System.out.println("第"+ruleNum+"个");
			String[] s19=new semanticRule().semanticRule_19(treeSemanticRecord,slrTreeArray,fourAddr, treeNodeNum);
			//testRecord(treeSemanticRecord);
			String s191=s19[0];
			String s192=s19[1];
			
			addrResult.add(s191);
			addrResult.add(s192);
			break;
		case 20:
			System.out.println("第"+ruleNum+"个");
			String s20=new semanticRule().semanticRule_20(treeSemanticRecord,slrTreeArray,fourAddr, treeNodeNum);
			//testRecord(treeSemanticRecord);
			
			addrResult.add(s20);
			break;
		case 21:
			System.out.println("第"+ruleNum+"个");
			String s21=new semanticRule().semanticRule_21(treeSemanticRecord,slrTreeArray,fourAddr, treeNodeNum);
			//testRecord(treeSemanticRecord);
			
			addrResult.add(s21);
			break;
		case 22:
			System.out.println("第"+ruleNum+"个");
			String s22=new semanticRule().semanticRule_22(treeSemanticRecord,slrTreeArray,charTable, fourAddr, treeNodeNum);
			//testRecord(treeSemanticRecord);
			System.out.println(s22+"=s22");
			String[] e22=s22.split(" ");
			if(e22[0].equals("error"))
			{
				if(e22[1].equals("1"))
				{
					semerrorListTbModel.addRow((new Object[]{1,"变量未定义",e22[2]}));
				}
				if(e22[1].equals("3"))
				{
					semerrorListTbModel.addRow((new Object[]{3,"变量不匹配",e22[2]+" "+e22[3]}));
				}
				break;
			}
			
			addrResult.add(s22);
			break;
		case 23:
			System.out.println("第"+ruleNum+"个");
			String s23=new semanticRule().semanticRule_23(treeSemanticRecord,slrTreeArray,fourAddr, treeNodeNum);
			//testRecord(treeSemanticRecord);
			
			addrResult.add(s23);
			break;
		case 24:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_24(treeSemanticRecord,slrTreeArray,treeNodeNum,addrList);
			//testRecord(treeSemanticRecord);
			break;
		case 25:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_25(treeSemanticRecord,slrTreeArray,treeNodeNum, addrResult, addrNum);
			//testRecord(treeSemanticRecord);
			testAddr(addrNum);
			break;
		case 26:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_26(treeSemanticRecord,slrTreeArray,treeNodeNum,addrList);
			//testRecord(treeSemanticRecord);
			break;
		case 27:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_27(treeSemanticRecord,slrTreeArray,treeNodeNum, addrResult, addrNum);
			//testRecord(treeSemanticRecord);
			testAddr(addrNum);
			break;
		case 28:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_28(treeSemanticRecord,slrTreeArray,treeNodeNum, addrResult, addrNum);
			//testRecord(treeSemanticRecord);
			testAddr(addrNum);
			break;
		case 29:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_29(treeSemanticRecord,slrTreeArray,treeNodeNum,addrList, addrResult, addrNum);
			//testRecord(treeSemanticRecord);
			testAddr(addrNum);
			break;
		case 30:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_30(treeSemanticRecord,slrTreeArray,treeNodeNum, addrResult, addrNum);
			//testRecord(treeSemanticRecord);
			testAddr(addrNum);
			break;
		case 31:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_31(treeSemanticRecord,slrTreeArray,treeNodeNum,addrList);
			//testRecord(treeSemanticRecord);
			break;
		case 32:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_32(treeSemanticRecord,slrTreeArray,treeNodeNum,addrList, addrResult, addrNum);
			//testRecord(treeSemanticRecord);
			testAddr(addrNum);
			break;
		case 33:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_33(treeSemanticRecord,slrTreeArray,treeNodeNum);
			//testRecord(treeSemanticRecord);
			break;
		case 34:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_34(treeSemanticRecord,slrTreeArray,treeNodeNum);
			//testRecord(treeSemanticRecord);
			break;
		case 35:
			System.out.println("第"+ruleNum+"个");
			String s35=new semanticRule().semanticRule_35(treeSemanticRecord,slrTreeArray,charTable, fourAddr, treeNodeNum);
			//testRecord(treeSemanticRecord);
			String[] e35=s35.split(" ");
			if(e35[0].equals("error"))
			{
				semerrorListTbModel.addRow((new Object[]{1,"变量未定义",e35[2]}));
				break;
			}
			addrResult.add(s35);
			break;
		case 36:
			System.out.println("第"+ruleNum+"个");
			String[] s36=new semanticRule().semanticRule_36(treeSemanticRecord,slrTreeArray,fourAddr, treeNodeNum);
			//testRecord(treeSemanticRecord);
			String s361=s36[0];
			String s362=s36[1];
			
			addrResult.add(s361);
			addrResult.add(s362);
			break;
		case 37:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_37(treeSemanticRecord,slrTreeArray,treeNodeNum);
			//testRecord(treeSemanticRecord);
			break;
		case 38:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_38(treeSemanticRecord,slrTreeArray,treeNodeNum);
			//testRecord(treeSemanticRecord);
			break;
		case 39:
			System.out.println("第"+ruleNum+"个");
			String s39=new semanticRule().semanticRule_39(treeSemanticRecord,slrTreeArray,fourAddr, treeNodeNum);
			//testRecord(treeSemanticRecord);
			
			addrResult.add(s39);
			break;
		case 40:
			System.out.println("第"+ruleNum+"个");
			String s40=new semanticRule().semanticRule_40(treeSemanticRecord,slrTreeArray,fourAddr, treeNodeNum);
			//testRecord(treeSemanticRecord);
		
			addrResult.add(s40);
			break;
		case 41:
			System.out.println("第"+ruleNum+"个");
			String e41=new semanticRule().semanticRule_41(treeSemanticRecord,slrTreeArray,charTable, treeNodeNum);
			//testRecord(treeSemanticRecord);
			String[] e411=e41.split(" ");
			if(e411[0].equals("error"))
			{
				semerrorListTbModel.addRow((new Object[]{2,"变量重复定义",e411[2]}));
			}
			break;
		case 42:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_42(treeSemanticRecord,slrTreeArray,param,treeNodeNum);
			break;
		case 43:
			System.out.println("第"+ruleNum+"个");
			new semanticRule().semanticRule_43(treeSemanticRecord,slrTreeArray,param,treeNodeNum);
			break;
		case 44:
			System.out.println("第"+ruleNum+"个");
			ArrayList<String> s44=new semanticRule().semanticRule_44(treeSemanticRecord,slrTreeArray,param,fourAddr, charTable, treeNodeNum);
			String e44=s44.get(0);
			String[] e441=e44.split(" ");
			if(e441[0].equals("error"))
			{
				semerrorListTbModel.addRow((new Object[]{5,"过程名未定义",e441[2]}));
			}
			for(int i=0;i<s44.size();i++)
			{
				addrResult.add(s44.get(i));
			}
			break;
		case 45:
			System.out.println("第"+ruleNum+"个==============");
			String e45=new semanticRule().semanticRule_45(treeSemanticRecord,slrTreeArray,charTable, treeNodeNum);
			//testRecord(treeSemanticRecord);
			String[] e451=e45.split(" ");
			if(e451[0].equals("error"))
			{
				semerrorListTbModel.addRow((new Object[]{4,"过程名定义冲突",e451[2]}));
			}
			break;
		default :
			//System.out.println("没有"+ruleNum);
			break;
			
		}
		
	}
	public void testRecord(ArrayList<RreeSemanticRecord> treeSemanticRecord){
		for(int i=0;i<treeSemanticRecord.size();i++)
		{
			System.out.print("结点号 "+treeSemanticRecord.get(i).getTreeNodeNum());
			System.out.print(" 结点符号 "+treeSemanticRecord.get(i).getTreeNodeName());
			System.out.print(" 属性"+treeSemanticRecord.get(i).getProperty());
			System.out.println(" 值 "+treeSemanticRecord.get(i).getValue());
		}
	}
	
	public void testAddr(ArrayList<AddrNum> addrNum){
		for(int i=0;i<addrNum.size();i++)
		{
			System.out.println(addrNum.get(i).getAddr()+"  "+addrNum.get(i).getNum());
		}
	}
	
	public String findValue(ArrayList<RreeSemanticRecord> treeSemanticRecord,int treeNodeNum,String property){
		for(int i=0;i<treeSemanticRecord.size();i++)
		{
			if(treeSemanticRecord.get(i).getTreeNodeNum()==treeNodeNum&&treeSemanticRecord.get(i).getProperty().equals(property))
			{
				return treeSemanticRecord.get(i).getValue();
			}
		}
		return "null";
	}
	
	private ArrayList<String> change(ArrayList<String> addrResult,ArrayList<AddrNum> addrNum){
		ArrayList<String> a= new ArrayList<String>();
		for(int i=0;i<addrResult.size();i++)
		{
			String[] b=addrResult.get(i).split(" ");
			StringBuffer sb2 = new StringBuffer();
			for(int j=0;j<b.length;j++)
			{
				boolean is=false;
				for(int z=0;z<addrNum.size();z++)
				{
					if(b[j].equals(addrNum.get(z).getAddr()))
					{
						sb2.append(addrNum.get(z).getNum()+" ");
						is=true;
						continue;
					}
				}
				if(!is)
				{
					sb2.append(b[j]+" ");
				}
				
			}
			a.add(sb2.toString());
		}
		return a;
	}
}


