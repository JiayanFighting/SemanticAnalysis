package semantic;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import Entity.DFATable;
import Entity.DFATableState;
import Entity.grammerSemanticLoca;
import Entity.grammerTable;

public class readDFATable {
   // public static void main(String[] args) throws Exception {}

    /**

     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行

     * @param file 读取数据的源Excel

     * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1

     * @return 读出的Excel中数据的内容

     * @throws FileNotFoundException

     * @throws IOException

     */

    public static String[][] getData(File file, int ignoreRows)

           throws FileNotFoundException, IOException {

       List<String[]> result = new ArrayList<String[]>();

       int rowSize = 0;

       BufferedInputStream in = new BufferedInputStream(new FileInputStream(

              file));

       // 打开HSSFWorkbook

       POIFSFileSystem fs = new POIFSFileSystem(in);

       HSSFWorkbook wb = new HSSFWorkbook(fs);

       HSSFCell cell = null;

       for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {

           HSSFSheet st = wb.getSheetAt(sheetIndex);

           // 第一行为标题，不取

           for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {

              HSSFRow row = st.getRow(rowIndex);

              if (row == null) {

                  continue;

              }

              int tempRowSize = row.getLastCellNum() + 1;

              if (tempRowSize > rowSize) {

                  rowSize = tempRowSize;

              }

              String[] values = new String[rowSize];

              Arrays.fill(values, "");

              boolean hasValue = false;

              for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {

                  String value = "";

                  cell = row.getCell(columnIndex);

                  if (cell != null) {

                     // 注意：一定要设成这个，否则可能会出现乱码

                     cell.setEncoding(HSSFCell.ENCODING_UTF_16);

                     switch (cell.getCellType()) {

                     case HSSFCell.CELL_TYPE_STRING:

                         value = cell.getStringCellValue();

                         break;

                     case HSSFCell.CELL_TYPE_NUMERIC:

                         if (HSSFDateUtil.isCellDateFormatted(cell)) {

                            Date date = cell.getDateCellValue();

                            if (date != null) {

                                value = new SimpleDateFormat("yyyy-MM-dd")

                                       .format(date);

                            } else {

                                value = "";

                            }

                         } else {

                            value = new DecimalFormat("0").format(cell

                                   .getNumericCellValue());

                         }

                         break;

                     case HSSFCell.CELL_TYPE_FORMULA:

                         // 导入时如果为公式生成的数据则无值

                         if (!cell.getStringCellValue().equals("")) {

                            value = cell.getStringCellValue();

                         } else {

                            value = cell.getNumericCellValue() + "";

                         }

                         break;

                     case HSSFCell.CELL_TYPE_BLANK:

                         break;

                     case HSSFCell.CELL_TYPE_ERROR:

                         value = "";

                         break;

                     case HSSFCell.CELL_TYPE_BOOLEAN:

                         value = (cell.getBooleanCellValue() == true ? "Y"

                                : "N");

                         break;

                     default:

                         value = "";

                     }

                  }

                  if (columnIndex == 0 && value.trim().equals("")) {

                     break;

                  }

                  values[columnIndex] = rightTrim(value);

                  hasValue = true;

              }

 

              if (hasValue) {

                  result.add(values);

              }

           }

       }

       in.close();

       String[][] returnArray = new String[result.size()][rowSize];

       for (int i = 0; i < returnArray.length; i++) {

           returnArray[i] = (String[]) result.get(i);

       }

       return returnArray;

    }

   

    /**

     * 去掉字符串右边的空格

     * @param str 要处理的字符串

     * @return 处理后的字符串

     */

     public static String rightTrim(String str) {

       if (str == null) {

           return "";

       }

       int length = str.length();

       for (int i = length - 1; i >= 0; i--) {

           if (str.charAt(i) != 0x20) {

              break;

           }

           length--;

       }

       return str.substring(0, length);

    }
     public DFATable[] addDFA() throws Exception {
 		
    	 File file = new File("1.xls");

         String[][] result = getData(file, 0);

         int rowLength = result.length;
         DFATable dfa[] =new DFATable[663];
         int x=0;
         
         for(int i=1;i<rowLength;i++) {
             for(int j=1;j<result[i].length-2;j++) {
  	    	  dfa[x]=new DFATable();
  	    	   dfa[x].setState(Integer.parseInt(result[i][0]));
  	    	   String[] strArray = null;  
  	           strArray = result[0][j].split(" ");  
  	    	   dfa[x].setInput(strArray);
  	    	   dfa[x].setNextState(Integer.parseInt(result[i][j]));
          	  // System.out.print(result[i][j]+" ");
          	   x=x+1;
             }

           //  System.out.println();

         }
         for(int i=0;i<dfa.length;i++)
         {
        	 if(dfa[i].getState()==1)
 			{
 				 dfa[i].setType("标识符");
 			}
        	 if(dfa[i].getState()>=2&&dfa[i].getState()<=7)
  			{
  				 dfa[i].setType("常数");
  			}
        	 if(dfa[i].getState()>=8&&dfa[i].getState()<=11)
  			{
  				 dfa[i].setType("注释");
  			}
        	 if(dfa[i].getState()>=12&&dfa[i].getState()<=18||dfa[i].getState()>=20&&dfa[i].getState()<=28)
   			{
   				 dfa[i].setType("运算符");
   			}
        	 if(dfa[i].getState()==29||dfa[i].getState()==19)
    			{
    				 dfa[i].setType("界符");
    			}
         }
         
 		return dfa;
 	}
     public String[][] showDFA() throws Exception{
    	 File file = new File("1.xls");

         String[][] result = getData(file, 0);

         /*int rowLength = result.length;
         for(int i=1;i<rowLength;i++) {
             for(int j=1;j<result[i].length-2;j++) {
          	   System.out.print(result[i][j]+" ");
             }
             System.out.println();
         }*/
         return result;
     }
     public DFATableState[] showDFAState() throws Exception{
    	 File file = new File("2.xls");
         String[][] result = getData(file, 0);
         int rowLength = result.length;
         
         DFATableState state[] =new DFATableState[39];
         int x=0;
         for(int i=0;i<rowLength;i++) {
        	     state[i]=new DFATableState();
            	 state[i].setState(Integer.parseInt(result[i][0]));
            	 state[i].setFinish(result[i][1].equals("1")?true:false);
            	 state[i].setType(result[i][2]);
            	 //System.out.println(state[i].getState()+"  "+state[i].getType()+"  "+state[i].isFinish());
         }
         return state;
     }
     
     
     
     public grammerTable[] Wenfa() throws Exception{
    	 File file = new File("fuzhi.xls");
         String[][] result = getData(file, 0);
         
         Map<String,List> map = new HashMap<String,List>();
        
         int rowLength = result.length;
         for(int i=0;i<rowLength;i++) {
        	 List list = java.util.Arrays.asList(result[i]);
        	 map.put(result[i][0],list);
         }
         grammerTable[] w=new grammerTable[38];
         int i=0;
         Set<String> get = map.keySet(); 
         for (String test:get) {
        	 String[] e=(String[]) map.get(test).toArray();
	          for(int j=1;j<e.length;j++){
	        	  if(!e[j].equals(""))
	        	  {
	        		  w[i]=new grammerTable();
		        	  w[i].setName(test);
		        	  
		        	  String[] d=e[j].split(" ");
	        		//  System.out.println("e "+ j+"bu为空  ");
	        		  w[i].setValue(d);
		        	//  System.out.println("i=  "+i);
		        	  i++;
	        	  }
	          }
	          
         }
		return w;
     }
     
     public grammerSemanticLoca[] GrammerSemanticLoca() throws Exception{
    	 File file = new File("SemanticLoca.xls");
         String[][] result = getData(file, 0);
         grammerSemanticLoca[] w=new grammerSemanticLoca[45];
         for(int i=0;i<result.length;i++)
         {
        	// System.out.println(result[i][0]+result[i][1]+result[i][2]);
        	 w[i]=new grammerSemanticLoca();
       	     w[i].setGrammerNum(Integer.parseInt(result[i][0]));
       	     w[i].setRulelLoc(Integer.parseInt(result[i][1]));
       	     w[i].setRuleNum(Integer.parseInt(result[i][2]));
       
         }
         //语义规则与产生式对应序列
        /* System.out.println("语义规则与产生式对应序列==========");
         for(int i1=0;i1<w.length;i1++)
         {
        	 System.out.println(w[i1].getGrammerNum()+" "+w[i1].getRulelLoc()+" "+w[i1].getRuleNum());
         }
         System.out.println("语义规则与产生式对应序列结束==========");*/
		return w;
     }

     
     public String[][] showRule() throws Exception{
    	 File file = new File("3.xls");
         String[][] result = getData(file, 0);
         System.out.println(result[0][0]+result[0][1]);
         System.out.println("1"+result[0][1]);
         return result;
     }

}