package semantic;

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

import java.awt.Font;

public class DFAPanel {

	JFrame frame;
    private DefaultTableModel dfaListTbModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DFAPanel window = new DFAPanel();
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
	public DFAPanel() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 1353, 650);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		frame.setLocation((screensize.width - frameSize.width) / 2,
				(screensize.height - frameSize.height) / 2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(0, 0, 1335, 603);
		frame.getContentPane().add(panel);
		
		 dfaListTbModel = new DefaultTableModel(
					new Object[][] {},
					new String[] {
							"","","","","","","","","","","","","","","","",""
					}
				);
		    String[][] result=new readDFATable().showDFA();
		    for(int i=1;i<result.length;i++) {
		        for(int j=1;j<result[i].length-2;j++) {
		        	result[i][j]=result[i][j].replaceAll("-1", " ");
		        }
		    }
		    for(int i=0;i<result.length;i++) {
		        	dfaListTbModel.addRow(new Object[] { result[i][0],result[i][1],result[i][2],result[i][3],result[i][4],result[i][5],result[i][6],result[i][7],result[i][8],result[i][9]
		        			,result[i][10],result[i][11],result[i][12],result[i][13],result[i][14],result[i][15],result[i][16],result[i][17]});
		        
		    }
		JTable dfaListTb = new JTable();
		dfaListTb.setBackground(new Color(224, 255, 255));
		dfaListTb.setFillsViewportHeight(true);
		dfaListTb.setModel(dfaListTbModel);
		// errorListTbModel.addRow(new Object[] { "出错", "类别","出错原因" ,"value"});
		RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dfaListTbModel);
		panel.setLayout(null);
		dfaListTb.setRowSorter(sorter);
		JScrollPane dfaSP = new JScrollPane();
		dfaSP.setViewportView(dfaListTb);
		dfaSP.setBounds(14, 84, 1307, 493);
		panel.add(dfaSP);
		
		JLabel lblDfaTable = new JLabel("DFA Table");
		lblDfaTable.setFont(new Font("宋体", Font.BOLD, 60));
		lblDfaTable.setBounds(501, -8, 344, 106);
		panel.add(lblDfaTable);
	}
}
