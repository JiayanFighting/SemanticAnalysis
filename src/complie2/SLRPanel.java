package complie2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;

import Entity.ACTIONTable;
import Entity.grammerTable;

import java.awt.Font;
import java.util.ArrayList;

public class SLRPanel {

	JFrame frame;
    private DefaultTableModel slrListTbModel;
    private DefaultTableModel firstListTbModel;
    private DefaultTableModel followListTbModel;
	private ArrayList<ACTIONTable> slrTable;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SLRPanel window = new SLRPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public SLRPanel() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 1353, 1008);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		frame.setLocation((screensize.width - frameSize.width) / 2,
				(screensize.height - frameSize.height) / 2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(0, 0, 1335, 961);
		frame.getContentPane().add(panel);
		
		slrListTbModel = new DefaultTableModel(new Object[][] {},
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
		JTable slrListTb = new JTable();
		slrListTb.setBackground(new Color(224, 255, 255));
		slrListTb.setFillsViewportHeight(true);
		slrListTb.setModel(slrListTbModel);
		// errorListTbModel.addRow(new Object[] { "出错", "类别","出错原因" ,"value"});
		RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(slrListTbModel);
		panel.setLayout(null);
		slrListTb.setRowSorter(sorter);
		JScrollPane slrSP = new JScrollPane();
		slrSP.setViewportView(slrListTb);
		slrSP.setBounds(14, 458, 1307, 477);
		panel.add(slrSP);
		
		JLabel lblDfaTable = new JLabel("SLR Table");
		lblDfaTable.setFont(new Font("宋体", Font.BOLD, 55));
		lblDfaTable.setBounds(526, 369, 344, 106);
		panel.add(lblDfaTable);
		grammerTable[] grammerTable = new readDFATable().Wenfa();
		slrTable=new demo().getSLRTable(grammerTable);
		ActionTest(slrTable);
		
// first集表格
		firstListTbModel = new DefaultTableModel(new Object[][] {}, new String[] {"符号", "FIRST集" });
		String[][] firstGroup = new demo().getFirstDroup(grammerTable);
		for (int i = 0; i < firstGroup.length; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(firstGroup[i][j] + "  ");
			}
			firstListTbModel.addRow(new Object[] { firstGroup[i][0],firstGroup[i][1] });
			System.out.println();
		}
		RowSorter<DefaultTableModel> sorter1 = new TableRowSorter<DefaultTableModel>(firstListTbModel);
// Follow表格
		followListTbModel = new DefaultTableModel(new Object[][] {},new String[] { "非终结符", "Follow集" });
		String[][] followGroup = new demo().getFollowGroup();
		for (int i = 0; i < followGroup.length; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(followGroup[i][j] + "  ");
			}
			followListTbModel.addRow(new Object[] { followGroup[i][0],followGroup[i][1] });
			System.out.println();
		}
		RowSorter<DefaultTableModel> sorter4 = new TableRowSorter<DefaultTableModel>(followListTbModel);
		JTable firstListTb = new JTable();
		firstListTb.setBackground(new Color(224, 255, 255));
		firstListTb.setFillsViewportHeight(true);
		firstListTb.setModel(firstListTbModel);
		firstListTb.setRowSorter(sorter1);
		JScrollPane firstSP = new JScrollPane();
		firstSP.setBounds(14, 95, 640, 279);
		panel.add(firstSP);
		firstSP.setViewportView(firstListTb);
		
		JTable followListTb = new JTable();
		followListTb.setBackground(new Color(224, 255, 255));
		followListTb.setFillsViewportHeight(true);
		followListTb.setModel(followListTbModel);
		followListTb.setRowSorter(sorter4);
		JScrollPane followSP = new JScrollPane();
		followSP.setBounds(681, 95, 640, 279);
		panel.add(followSP);
		followSP.setViewportView(followListTb);
		
		JLabel lblFirstTable = new JLabel("FIRST Table");
		lblFirstTable.setFont(new Font("宋体", Font.BOLD, 50));
		lblFirstTable.setBounds(127, 13, 344, 80);
		panel.add(lblFirstTable);
		
		JLabel lblFollowTable = new JLabel("FOLLOW Table");
		lblFollowTable.setFont(new Font("宋体", Font.BOLD, 50));
		lblFollowTable.setBounds(785, 13, 466, 80);
		panel.add(lblFollowTable);
	}
	public void ActionTest(ArrayList<ACTIONTable> actionTable){
		for (int i = 0; i < actionTable.size(); i++) {
			System.out.print("状态: " + actionTable.get(i).getState());
			System.out.print("  输入 :" + actionTable.get(i).getInput());
			String[] str1 =actionTable.get(i).getAction();
			StringBuffer sb1 = new StringBuffer();
			for (int y = 0; y < str1.length; y++) {
				if(!str1[y].equals("h"))
				{
					sb1.append(str1[y]);
				}
				
			}
			String s1 = sb1.toString();
			System.out.println("  转换:" + s1);
			System.out.println("  行数   " + actionTable.get(i).getState());
			System.out.println("  列数   " + getColumn(actionTable.get(i).getInput()));
			slrListTbModel.setValueAt(s1,actionTable.get(i).getState(), getColumn(actionTable.get(i).getInput()));
			System.out.println();
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
}
