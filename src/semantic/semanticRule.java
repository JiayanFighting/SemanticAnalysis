package semantic;

import java.util.ArrayList;

import Entity.AddrNum;
import Entity.CharTable;
import Entity.FourAddr;
import Entity.RreeSemanticRecord;
import Entity.SLRTree;

public class semanticRule {
	//ÓïÒåÆ¬¶Î1, T->X {t=X.type;w=X.width;} C
	public void semanticRule_1(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,int treeNodeNum){
		int child1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[1]);
	    String s1=findValue(treeSemanticRecord,child1,"type");
	    String s2=findValue(treeSemanticRecord,child1,"width");
	    updateValue(treeSemanticRecord, slrTreeArray.size()+1, "t",s1);
		RreeSemanticRecord r2=new RreeSemanticRecord();
		r2.setTreeNodeNum(slrTreeArray.size()+2);
		r2.setTreeNodeName("w");
		r2.setProperty("w");
		r2.setValue(s2);
		treeSemanticRecord.add(r2);
		}
	//ÓïÒåÆ¬¶Î2,T->X C{T.type=C.type;T.width=C.width;}
	public void semanticRule_2(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum){
		int child1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[0]);
	    String s1=findValue(treeSemanticRecord,child1,"type");
	    String s2=findValue(treeSemanticRecord,child1,"width");
	    RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(treeNodeNum);
		r1.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r1.setProperty("type");
		r1.setValue(s1);
		treeSemanticRecord.add(r1);
		RreeSemanticRecord r2=new RreeSemanticRecord();
		r2.setTreeNodeNum(treeNodeNum);
		r2.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r2.setProperty("width");
		r2.setValue(s2);
		treeSemanticRecord.add(r2);
	}
	//ÓïÒåÆ¬¶Î3,5 : E->E + E{E.addr=newtemp();gen(E.addr=E1.addr+E2.addr);}
	public String semanticRule_3(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<CharTable> charTable ,ArrayList<FourAddr> fourAddr,int treeNodeNum){	
			RreeSemanticRecord r1=new RreeSemanticRecord();
			r1.setTreeNodeNum(treeNodeNum);
			r1.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
			r1.setProperty("addr");
			r1.setValue("t"+treeNodeNum);
			int child1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[2]);
			int child2=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[0]);
			String s1=findValue(treeSemanticRecord,child1,"addr");
			String s2=findValue(treeSemanticRecord,child2,"addr");
			System.out.println("====== gen =======");
			System.out.println("t"+treeNodeNum+"="+s1+"+"+s2);
			System.out.println("====== gen =======");
			treeSemanticRecord.add(r1);
			String s="t"+treeNodeNum+"="+s1+"+"+s2;
			//ArrayList<FourAddr> fourAddr
			FourAddr fa=new FourAddr();
			fa.setOp("+");
			fa.setParam1(s1);
			fa.setParam2(s2);
			fa.setToaddr("t"+treeNodeNum);
			fourAddr.add(fa);
			//ArrayList<CharTable> charTable 
			int num1=-1;
			for(int j=0;j<charTable.size();j++)
			{
				if(charTable.get(j).getChara().equals(s1))
				{       num1=j;
						break;
				}
			}
			int num2=-1;
			for(int j=0;j<charTable.size();j++)
			{
				if(charTable.get(j).getChara().equals(s2))
				{       num2=j;
						break;
				}
			}
			System.out.println("num1 "+num1+" num2 "+num2);
			if(num1==-1&&num2!=-1)
			{
				if(!charTable.get(num2).getType().equals("int"))
				{
					return "error 3 "+s1+"(int) "+charTable.get(num2).getChara()+"("+charTable.get(num2).getType()+")";
				}
				
			}
			if(num2==-1&&num1!=-1)
			{
				if(!charTable.get(num1).getType().equals("int"))
				{
					return "error 3 "+charTable.get(num1).getChara()+"("+charTable.get(num1).getType()+") "+s2+"(int)";
				}
				
			}
			return s;
	}
	
	//ÓïÒåÆ¬¶Î4,E->E * E  {E.addr=newtemp();gen(E.addr=E1.addr*E2.addr);}
	public String semanticRule_4(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<CharTable> charTable ,ArrayList<FourAddr> fourAddr,int treeNodeNum){	
			RreeSemanticRecord r1=new RreeSemanticRecord();
			r1.setTreeNodeNum(treeNodeNum);
			r1.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
			r1.setProperty("addr");
			r1.setValue("t"+treeNodeNum);
			treeSemanticRecord.add(r1);
			int child1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[2]);
			int child2=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[0]);
			String s1=findValue(treeSemanticRecord,child1,"addr");
			String s2=findValue(treeSemanticRecord,child2,"addr");
			System.out.println("====== gen =======");
			System.out.println("t="+s1+"*"+s2);
			System.out.println("====== gen =======");
			String s="t"+treeNodeNum+"="+s1+"*"+s2;
			//ArrayList<FourAddr> fourAddr
			FourAddr fa=new FourAddr();
			fa.setOp("*");
			fa.setParam1(s1);
			fa.setParam2(s2);
			fa.setToaddr("t"+treeNodeNum);
			fourAddr.add(fa);
			//ArrayList<CharTable> charTable 
			int num1=-1;
			for(int j=0;j<charTable.size();j++)
			{
				if(charTable.get(j).getChara().equals(s1))
				{       
					num1=j;
						break;
				}
			}
			int num2=-1;
			for(int j=0;j<charTable.size();j++)
			{
				if(charTable.get(j).getChara().equals(s2))
				{       num2=j;
						break;
				}
			}
			System.out.println("num1 "+num1+" num2 "+num2);
			if(num1==-1&&num2!=-1)
			{
				if(!charTable.get(num2).getType().equals("int"))
				{
					return "error 3 "+s1+"(int) "+charTable.get(num2).getChara()+"("+charTable.get(num2).getType()+")";
				}
				
			}
			if(num2==-1&&num1!=-1)
			{
				if(!charTable.get(num1).getType().equals("int"))
				{
					return "error 3 "+charTable.get(num1).getChara()+"("+charTable.get(num1).getType()+") "+s2+"(int)";
				}
				
			}
			return s;
	}
	//ÓïÒåÆ¬¶Î5, E->- E {E.addr=newtemp();gen(E.addr=uminus E1.addr);}
	public String semanticRule_5(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<FourAddr> fourAddr,int treeNodeNum){	
			RreeSemanticRecord r1=new RreeSemanticRecord();
			r1.setTreeNodeNum(treeNodeNum);
			r1.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
			r1.setProperty("addr");
			r1.setValue("t"+treeNodeNum);
			treeSemanticRecord.add(r1);
			int child2=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[0]);
			String s2=findValue(treeSemanticRecord,child2,"addr");
			System.out.println("====== gen =======");
			System.out.println("t=-"+s2);
			System.out.println("====== gen =======");
			String s="t"+treeNodeNum+"=-"+s2;
			//ArrayList<FourAddr> fourAddr
			FourAddr fa=new FourAddr();
			fa.setOp("-");
			fa.setParam1(s2);
			fa.setParam2("-");
			fa.setToaddr("t"+treeNodeNum);
			fourAddr.add(fa);
			return s;
	}
	//ÓïÒåÆ¬¶Î6, E->( E ) {E.addr=E1.addr}
	public void semanticRule_6(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,int treeNodeNum){	
		    int child2=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[1]);
		    String s2=findValue(treeSemanticRecord,child2,"addr");
			RreeSemanticRecord r1=new RreeSemanticRecord();
			r1.setTreeNodeNum(treeNodeNum);
			r1.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
			r1.setProperty("addr");
			r1.setValue(s2);
			treeSemanticRecord.add(r1);
			
	}
	//ÓïÒåÆ¬¶Î7,9 : E->id {E.addr=lookup(id.lexeme);if E.addr==null then error}
	public String semanticRule_7(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<CharTable> charTable,int treeNodeNum){
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[0]);
		String value=findValue(treeSemanticRecord,childID,"lexeme");
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(treeNodeNum);
		r1.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r1.setProperty("addr");
		r1.setValue(value);
		treeSemanticRecord.add(r1);
		
		//ArrayList<CharTable> charTable 
		boolean is=false;
		for(int i=0;i<charTable.size();i++)
		{
			if(charTable.get(i).getChara().equals(value))
			{
				is=true;
			}
		}
		if(is)
		{
			System.out.println("success");
			return "success";
		}
		System.out.println("error 1 "+value);
		return "error 1 "+value;
	}
	//ÓïÒåÆ¬¶Î8,9 : E->digit {E.addr=lookup(id.lexeme);if E.addr==null then error}
		public void semanticRule_8(ArrayList<RreeSemanticRecord> treeSemanticRecord,
				ArrayList<SLRTree> slrTreeArray,int treeNodeNum){
			int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[0]);
			String value=findValue(treeSemanticRecord,childID,"lexeme");
			RreeSemanticRecord r1=new RreeSemanticRecord();
			r1.setTreeNodeNum(treeNodeNum);
			r1.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
			r1.setProperty("addr");
			r1.setValue(value);
			treeSemanticRecord.add(r1);
		}
	//ÓïÒåÆ¬¶Î9,E->L{E.addr=newtemp(); gen(E.addr=L.array[L.offset];}
	public String semanticRule_9(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<FourAddr> fourAddr,int treeNodeNum){
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(treeNodeNum);
		r1.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r1.setProperty("addr");
		r1.setValue("t"+treeNodeNum);
		treeSemanticRecord.add(r1);
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[0]);
		String array=findValue(treeSemanticRecord,childID,"array");
		String offset=findValue(treeSemanticRecord,childID,"offset");
		String a="t"+treeNodeNum+"="+array+"["+offset+"]";
		System.out.println("L.array[L.offset]="+a);
		//ArrayList<FourAddr> fourAddr
		FourAddr fa=new FourAddr();
		fa.setOp("=");
		fa.setParam1(array+"["+offset+"]");
		fa.setParam2("-");
		fa.setToaddr("t"+treeNodeNum);
		fourAddr.add(fa);
		return a;
	}
	//ÓïÒåÆ¬¶Î10,P->{offset=0}D P
	public void semanticRule_10(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum){
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(slrTreeArray.size());
		r1.setTreeNodeName("offset");
		r1.setProperty("offset");
		r1.setValue("0");
		treeSemanticRecord.add(r1);
	}
	//ÓïÒåÆ¬¶Î11,P->{S.next=newlabel();}S P
	public void semanticRule_11(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum,ArrayList<String> addrList){
		
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[1]);
		String childName=slrTreeArray.get(childID).getName();
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(childID);
		r1.setTreeNodeName(childName);
		r1.setProperty("next");
		r1.setValue(newlabel(addrList));
		treeSemanticRecord.add(r1);
	}
	//ÓïÒåÆ¬¶Î12,P->S{label(S.next);} P
	public void semanticRule_12(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,
			int treeNodeNum,ArrayList<String> addrResult,ArrayList<AddrNum> addrNum){
		//{label(S.next);}
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[1]);
		String cl=findValue(treeSemanticRecord,childID,"next");
		int l=addrResult.size()+1;
		System.out.println("S.NEXT="+l);
		System.out.println("labelS:"+cl+" "+l);
		//for(int i=0;i)
		AddrNum ad=new AddrNum();
		ad.setAddr(cl);
		ad.setNum(l);
		addrNum.add(ad);
	}
	//ÓïÒåÆ¬¶Î13,B->{ B1.true=B.true; B1.false = newlabel();}B1 or B2
	public void semanticRule_13(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<String> addrList,int treeNodeNum){
			int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[2]);
			String childName=slrTreeArray.get(childID).getName();
			String s1=findValue(treeSemanticRecord,treeNodeNum,"true");
			RreeSemanticRecord r1=new RreeSemanticRecord();
			r1.setTreeNodeNum(childID);
			r1.setTreeNodeName(childName);
			r1.setProperty("true");
			r1.setValue(s1);
			treeSemanticRecord.add(r1);
			RreeSemanticRecord r2=new RreeSemanticRecord();
			r2.setTreeNodeNum(childID);
			r2.setTreeNodeName(childName);
			r2.setProperty("false");
			r2.setValue(newlabel(addrList));
			treeSemanticRecord.add(r2);
	}
	//ÓïÒåÆ¬¶Î14,B->B1 or{label(B1.false); B2.true=B.true; B2.false = B.false;} B2
	public void semanticRule_14(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,
			ArrayList<String> addrResult ,ArrayList<AddrNum> addrNum,int treeNodeNum){
		int childID1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[2]);
		String b1false=findValue(treeSemanticRecord,childID1,"false");
		int l=addrResult.size()+1;
		System.out.println("label:"+b1false+" "+l);
		AddrNum ad=new AddrNum();
		ad.setAddr(b1false);
		ad.setNum(l);
		addrNum.add(ad);
		
		int childID2=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[0]);
		String childName2=slrTreeArray.get(childID2).getName();
		String s1=findValue(treeSemanticRecord,treeNodeNum,"true");
		String s2=findValue(treeSemanticRecord,treeNodeNum,"false");
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(childID2);
		r1.setTreeNodeName(childName2);
		r1.setProperty("true");
		r1.setValue(s1);
		treeSemanticRecord.add(r1);
		RreeSemanticRecord r2=new RreeSemanticRecord();
		r2.setTreeNodeNum(childID2);
		r2.setTreeNodeName(childName2);
		r2.setProperty("false");
		r2.setValue(s2);
		treeSemanticRecord.add(r2);
	}
	//ÓïÒåÆ¬¶Î15,B->{ B1.false=B.false; B1.true = newlabel();}B1 and B2
	public void semanticRule_15(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<String> addrList,int treeNodeNum){
			int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[2]);
			String childName=slrTreeArray.get(childID).getName();
			String s1=findValue(treeSemanticRecord,treeNodeNum,"false");
			RreeSemanticRecord r1=new RreeSemanticRecord();
			r1.setTreeNodeNum(childID);
			r1.setTreeNodeName(childName);
			r1.setProperty("false");
			r1.setValue(s1);
			treeSemanticRecord.add(r1);
			RreeSemanticRecord r2=new RreeSemanticRecord();
			r2.setTreeNodeNum(childID);
			r2.setTreeNodeName(childName);
			r2.setProperty("true");
			r2.setValue(newlabel(addrList));
			treeSemanticRecord.add(r2);
	}
	//ÓïÒåÆ¬¶Î16,B->B1 and {label(B1.true); B2.true=B.true; B2.false = B2.false;}B2
	public void semanticRule_16(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,
			ArrayList<String> addrResult ,ArrayList<AddrNum> addrNum,int treeNodeNum){
		int childID1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[2]);
		String b1false=findValue(treeSemanticRecord,childID1,"true");
		int l=addrResult.size()+1;
		System.out.println("label:"+b1false+" "+l);
		AddrNum ad=new AddrNum();
		ad.setAddr(b1false);
		ad.setNum(l);
		addrNum.add(ad);
		
		int childID2=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[0]);
		String childName2=slrTreeArray.get(childID2).getName();
		String s1=findValue(treeSemanticRecord,treeNodeNum,"true");
		String s2=findValue(treeSemanticRecord,treeNodeNum,"false");
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(childID2);
		r1.setTreeNodeName(childName2);
		r1.setProperty("true");
		r1.setValue(s1);
		treeSemanticRecord.add(r1);
		RreeSemanticRecord r2=new RreeSemanticRecord();
		r2.setTreeNodeNum(childID2);
		r2.setTreeNodeName(childName2);
		r2.setProperty("false");
		r2.setValue(s2);
		treeSemanticRecord.add(r2);
	}
	//ÓïÒåÆ¬¶Î17,B->not { B1.true=B.false; B1.false = B.true ;}B1
	public void semanticRule_17(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum){
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[0]);
		String childName=slrTreeArray.get(childID).getName();
		String s1=findValue(treeSemanticRecord,treeNodeNum,"false");
		String s2=findValue(treeSemanticRecord,treeNodeNum,"true");
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(childID);
		r1.setTreeNodeName(childName);
		r1.setProperty("true");
		r1.setValue(s1);
		treeSemanticRecord.add(r1);
		RreeSemanticRecord r2=new RreeSemanticRecord();
		r2.setTreeNodeNum(childID);
		r2.setTreeNodeName(childName);
		r2.setProperty("false");
		r2.setValue(s2);
		treeSemanticRecord.add(r2);
	}
	//ÓïÒåÆ¬¶Î18,B->({ B1.true=B.true; B1.false = B.false ;} B1 )
	public void semanticRule_18(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,int treeNodeNum){
			int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[1]);
			String childName=slrTreeArray.get(childID).getName();
			String s1=findValue(treeSemanticRecord,treeNodeNum,"true");
			String s2=findValue(treeSemanticRecord,treeNodeNum,"false");
			RreeSemanticRecord r1=new RreeSemanticRecord();
			r1.setTreeNodeNum(childID);
			r1.setTreeNodeName(childName);
			r1.setProperty("true");
			r1.setValue(s1);
			treeSemanticRecord.add(r1);
			RreeSemanticRecord r2=new RreeSemanticRecord();
			r2.setTreeNodeNum(childID);
			r2.setTreeNodeName(childName);
			r2.setProperty("false");
			r2.setValue(s2);
			treeSemanticRecord.add(r2);
	}
	//
	//ÓïÒåÆ¬¶Î19,B->E1 > E2  {gen (if E1.addr > E2.addr goto B.true);gen(goto B.false);}
	public String[] semanticRule_19(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<FourAddr> fourAddr,int treeNodeNum){
			int childID1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[2]);
			String childName1=slrTreeArray.get(childID1).getName();
			int childID2=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[0]);
			String childName2=slrTreeArray.get(childID2).getName();
			String s1=findValue(treeSemanticRecord,childID1,"addr");
			String s2=findValue(treeSemanticRecord,childID2,"addr");
		/*	System.out.println(s1);
			System.out.println(s2);
			int i1=Integer.parseInt(s1);
			int i2=Integer.parseInt(s2);*/
			String s3=findValue(treeSemanticRecord,treeNodeNum,"true");
			String s4=findValue(treeSemanticRecord,treeNodeNum,"false");
			System.out.println("====== gen =======");
			System.out.println("if "+s1+" > "+s2+" goto "+s3);
			System.out.println("else "+" goto "+s4);
			System.out.println("====== gen =======");
			String a1="if "+s1+" > "+s2+" goto "+s3;
			String a2="goto "+s4;
			String[] a={a1,a2};
			//ArrayList<FourAddr> fourAddr
			FourAddr fa=new FourAddr();
			fa.setOp("j>");
			fa.setParam1(s1);
			fa.setParam2(s2);
			fa.setToaddr(s3);
			fourAddr.add(fa);
			FourAddr fa2=new FourAddr();
			fa2.setOp("j");
			fa2.setParam1("-");
			fa2.setParam2("-");
			fa2.setToaddr(s4);
			fourAddr.add(fa2);
			return a;
			/*RreeSemanticRecord r1=new RreeSemanticRecord();
			r1.setTreeNodeNum(childID);
			r1.setTreeNodeName(childName);
			r1.setProperty("true");
			r1.setValue(s1);
			treeSemanticRecord.add(r1);*/
	}
	
	//ÓïÒåÆ¬¶Î20 B->true   {gen(goto B.true);}
	public String semanticRule_20(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,
			ArrayList<FourAddr> fourAddr,int treeNodeNum){
		String value=findValue(treeSemanticRecord,treeNodeNum,"true");
		String s="goto "+value;
		//ArrayList<FourAddr> fourAddr
		FourAddr fa=new FourAddr();
		fa.setOp("j");
		fa.setParam1("-");
		fa.setParam2("-");
		fa.setToaddr(value);
		fourAddr.add(fa);
		return s;
	}
	//ÓïÒåÆ¬¶Î21 B->false {gen(goto B.false);}
	public String semanticRule_21(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,
			ArrayList<FourAddr> fourAddr,int treeNodeNum){
		String value=findValue(treeSemanticRecord,treeNodeNum,"false");
		String s="goto "+value;
		//ArrayList<FourAddr> fourAddr
		FourAddr fa=new FourAddr();
		fa.setOp("j");
		fa.setParam1("-");
		fa.setParam2("-");
		fa.setToaddr(value);
		fourAddr.add(fa);
		return s;
	}
	//ÓïÒåÆ¬¶Î22,24:S->id=E;{p=lookup(id.lexeme);if p== nil then error;S.CODE=E.code||gen(p¡¯=¡¯E.addr);}
	public String semanticRule_22(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<CharTable> charTable ,ArrayList<FourAddr> fourAddr,int treeNodeNum){
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[3]);
		String p=findValue(treeSemanticRecord,childID,"lexeme");
		int childID2=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[1]);
		String value=findValue(treeSemanticRecord,childID2,"addr");
		System.out.println("====== gen =======");
		String s=p+"="+value;
		System.out.println(s);
		System.out.println("====== gen =======");
		//ArrayList<FourAddr> fourAddr
		FourAddr fa=new FourAddr();
		fa.setOp("=");
		fa.setParam1(value);
		fa.setParam2("-");
		fa.setToaddr(p);
		fourAddr.add(fa);
		//ArrayList<CharTable> charTable 
		boolean is=false;
		for(int i=0;i<charTable.size();i++)
		{
			if(charTable.get(i).getChara().equals(p))
			{
				is=true;
				boolean is2=false;
				int num=0;
				for(int j=0;j<charTable.size();j++)
				{
					if(charTable.get(j).getChara().equals(value))
					{       num=j;
							break;
					}
				}
				System.out.println("num="+num);
				if(num!=-1){
					if(charTable.get(i).getType().equals(charTable.get(num).getType()))
					{
						System.out.println("success");
						return s;
					}
					System.out.println("error 3 "+charTable.get(i).getType()+" "+charTable.get(num).getType());
					return "error 3 "+charTable.get(i).getChara()+"("+charTable.get(i).getType()+") "+charTable.get(num).getChara()+"("+charTable.get(num).getType()+")";
				}
			}
		}
		if(is)
		{
			System.out.println("success");
			return s;
		}
		System.out.println("error 1 "+value);
		return "error 1 "+p;
		
	}
	
	//ÓïÒåÆ¬¶Î23,S->L = E ; {gen(L.array[L.offset]=E.addr);}
	public String semanticRule_23(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<FourAddr> fourAddr,int treeNodeNum){
		int childID1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[3]);
		String array=findValue(treeSemanticRecord,childID1,"array");
		String offset=findValue(treeSemanticRecord,childID1,"offset");
		int childID2=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[1]);
		String addr=findValue(treeSemanticRecord,childID2,"addr");
		String a=array+"["+offset+"] = "+addr;
		System.out.println("L.array[L.offset]=E.addr="+a);
		//ArrayList<FourAddr> fourAddr
		FourAddr fa=new FourAddr();
		fa.setOp("=");
		fa.setParam1(addr);
		fa.setParam2("-");
		fa.setToaddr(array+"["+offset+"]");
		fourAddr.add(fa);
		return a;
	}
	
	//ÓïÒåÆ¬¶Î24,S->if {B.true=newlabel();B.false=S.next;} B { {label(B.true);S1.next=S.next}S1 }
	public void semanticRule_24(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum,ArrayList<String> addrList){
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[3]);
		String childName=slrTreeArray.get(childID).getName();
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(childID);
		r1.setTreeNodeName(childName);
		r1.setProperty("true");
		r1.setValue(newlabel(addrList));
		treeSemanticRecord.add(r1);
		RreeSemanticRecord r2=new RreeSemanticRecord();
		r2.setTreeNodeNum(childID);
		r2.setTreeNodeName(childName);
		r2.setProperty("false");
		String next=findValue(treeSemanticRecord,treeNodeNum,"next");
		r2.setValue(next);
		treeSemanticRecord.add(r2);
	}
	
	//ÓïÒåÆ¬¶Î25,S->if B { {label(B.true);S1.next=S.next} S1 }
	public void semanticRule_25(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,
			int treeNodeNum,ArrayList<String> addrResult ,ArrayList<AddrNum> addrNum){
		//label(B.true);
		int childID1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[3]);
		String cl=findValue(treeSemanticRecord,childID1,"true");
		int l=addrResult.size()+1;
		System.out.println("label:"+cl+" "+l);
		AddrNum ad=new AddrNum();
		ad.setAddr(cl);
		ad.setNum(l);
		addrNum.add(ad);
		
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[1]);
		String childName=slrTreeArray.get(childID).getName();
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(childID);
		r1.setTreeNodeName(childName);
		r1.setProperty("next");
		String value=findValue(treeSemanticRecord,treeNodeNum,"next");
		r1.setValue(value);
		treeSemanticRecord.add(r1);
	}
	//ÓïÒåÆ¬¶Î26 S->if {B.true=newlabel();B.false=newlabel();}B { S1 } else {S2 }
	public void semanticRule_26(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum,ArrayList<String> addrList){
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[7]);
		String childName=slrTreeArray.get(childID).getName();
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(childID);
		r1.setTreeNodeName(childName);
		r1.setProperty("true");
		r1.setValue(newlabel(addrList));
		treeSemanticRecord.add(r1);
		RreeSemanticRecord r2=new RreeSemanticRecord();
		r2.setTreeNodeNum(childID);
		r2.setTreeNodeName(childName);
		r2.setProperty("false");
		r2.setValue(newlabel(addrList));
		treeSemanticRecord.add(r2);
		
	}
	//ÓïÒåÆ¬¶Î27 S->if B { {label(B.true);S1.next=S.next}S1 } else { S2 }
	public void semanticRule_27(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum,
			ArrayList<String> addrResult,ArrayList<AddrNum> addrNum){
		//label(B.true);
		int childID1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[7]);
		String cl=findValue(treeSemanticRecord,childID1,"true");
		int l=addrResult.size()+1;
		System.out.println("label:"+cl+" "+l);
		AddrNum ad=new AddrNum();
		ad.setAddr(cl);
		ad.setNum(l);
		addrNum.add(ad);
		
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[5]);
		String childName=slrTreeArray.get(childID).getName();
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(childID);
		r1.setTreeNodeName(childName);
		r1.setProperty("next");
		String value=findValue(treeSemanticRecord,treeNodeNum,"next");
		r1.setValue(value);
		treeSemanticRecord.add(r1);
	}
	//ÓïÒåÆ¬¶Î28 S->if B { S1 } else { {label(B.false);S2.next=S.next}S2 }
	public void semanticRule_28(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum
			,ArrayList<String> addrResult,ArrayList<AddrNum> addrNum){
		//label(B.false)
		int childID1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[7]);
		String cl=findValue(treeSemanticRecord,childID1,"false");
		int l=addrResult.size()+1;
		System.out.println("label:"+cl+" "+l);
		AddrNum ad=new AddrNum();
		ad.setAddr(cl);
		ad.setNum(l);
		addrNum.add(ad);
		
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[1]);
		String childName=slrTreeArray.get(childID).getName();
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(childID);
		r1.setTreeNodeName(childName);
		r1.setProperty("next");
		String value=findValue(treeSemanticRecord,treeNodeNum,"next");
		r1.setValue(value);
		treeSemanticRecord.add(r1);
	}
	
	//ÓïÒåÆ¬¶Î29,S->while{S.begin=newlabel();label(S.begin);B.true=newlabel();B.false=S.next;} B {S}
	public void semanticRule_29(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum,
			ArrayList<String> addrList,ArrayList<String> addrResult,ArrayList<AddrNum> addrNum){
		//S.begin=newlabel();
		RreeSemanticRecord r3=new RreeSemanticRecord();
		r3.setTreeNodeNum(treeNodeNum);
		r3.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r3.setProperty("begin");
		r3.setValue(newlabel(addrList));
		treeSemanticRecord.add(r3);
		
		//label(S.begin);
		String cl=findValue(treeSemanticRecord,treeNodeNum,"begin");
		int l=addrResult.size()+1;
		System.out.println("label:"+cl+" "+l);
		AddrNum ad=new AddrNum();
		ad.setAddr(cl);
		ad.setNum(l);
		addrNum.add(ad);
		
		//B.true=newlabel()
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[3]);
		String childName=slrTreeArray.get(childID).getName();
		RreeSemanticRecord r2=new RreeSemanticRecord();
		r2.setTreeNodeNum(childID);
		r2.setTreeNodeName(childName);
		r2.setProperty("true");
		r2.setValue(newlabel(addrList));
		treeSemanticRecord.add(r2);
		//B.false=S.next;
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(childID);
		r1.setTreeNodeName(childName);
		r1.setProperty("false");
		String value=findValue(treeSemanticRecord,treeNodeNum,"next");
		r1.setValue(value);
		treeSemanticRecord.add(r1);
		
	}
	
	//ÓïÒåÆ¬¶Î30,S->while B {{label(B.true);S1.next=S.begin;} S1 }

	public void semanticRule_30(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum,
			ArrayList<String> addrResult,ArrayList<AddrNum> addrNum){
		//label(B.true);
		int childID1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[3]);
		String cl=findValue(treeSemanticRecord,childID1,"true");
		int l=addrResult.size()+1;
		System.out.println("label:"+cl+" "+l);
		AddrNum ad=new AddrNum();
		ad.setAddr(cl);
		ad.setNum(l);
		addrNum.add(ad);
		
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[1]);
		String childName=slrTreeArray.get(childID).getName();
		RreeSemanticRecord r2=new RreeSemanticRecord();
		r2.setTreeNodeNum(childID);
		r2.setTreeNodeName(childName);
		r2.setProperty("next");
		String value=findValue(treeSemanticRecord,treeNodeNum,"begin");
		r2.setValue(value);
		treeSemanticRecord.add(r2);
	}
	
	//ÓïÒåÆ¬¶Î31,S->{S1.next=newlabel();}S1 S2
	public void semanticRule_31(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum,ArrayList<String> addrList){
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[1]);
		String childName=slrTreeArray.get(childID).getName();
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(childID);
		r1.setTreeNodeName(childName);
		r1.setProperty("next");
		r1.setValue(newlabel(addrList));
		treeSemanticRecord.add(r1);
		}
	
	//ÓïÒåÆ¬¶Î32,S->S1 {label(S1.next);S2.next=S.next;} S2
	public void semanticRule_32(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum,
			ArrayList<String> addrList,ArrayList<String> addrResult,ArrayList<AddrNum> addrNum){
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[0]);
		String childName=slrTreeArray.get(childID).getName();
		String next=findValue(treeSemanticRecord,treeNodeNum,"next");
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(childID);
		r1.setTreeNodeName(childName);
		r1.setProperty("next");
		r1.setValue(next);
		treeSemanticRecord.add(r1);
		//label(S1.next)
		int childID1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[1]);
		String cl=findValue(treeSemanticRecord,childID1,"next");
		int l=addrResult.size()+1;
		System.out.println("label:"+cl+" "+l);
		AddrNum ad=new AddrNum();
		ad.setAddr(cl);
		ad.setNum(l);
		addrNum.add(ad);
	}
	//ÓïÒåÆ¬¶Î33,C->[ digit ]C1 {C.type=array(digit.lexeme,C1.type);C.width=digit.lexeme*C1.width;}
	public void semanticRule_33(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum){
		int childID1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[0]);
		String childName1=slrTreeArray.get(childID1).getName();
		String type=findValue(treeSemanticRecord,childID1,"type");
		String width=findValue(treeSemanticRecord,childID1,"width");
		int childID2=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[2]);
		String childName2=slrTreeArray.get(childID2).getName();
		String lexeme=findValue(treeSemanticRecord,childID2,"lexeme");
		
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(treeNodeNum);
		r1.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r1.setProperty("type");
		String a="array("+lexeme+","+type+")";
		System.out.println("array="+a);
		r1.setValue(a);
		treeSemanticRecord.add(r1);
		RreeSemanticRecord r2=new RreeSemanticRecord();
		r2.setTreeNodeNum(treeNodeNum);
		r2.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r2.setProperty("width");
		System.out.println("width="+width+" lexeme="+lexeme);
		int b=Integer.parseInt(width)*Integer.parseInt(lexeme);
		System.out.println(String.valueOf(b));
		r2.setValue(String.valueOf(b));
		treeSemanticRecord.add(r2);
	}
	
	//ÓïÒåÆ¬¶Î34,C->no {C.type=t;C.width=w;}
	public void semanticRule_34(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum){
		String c1=findValue(treeSemanticRecord,slrTreeArray.size()+1,"t");
		String c2=findValue(treeSemanticRecord,slrTreeArray.size()+2,"w");
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(treeNodeNum);
		r1.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r1.setProperty("type");
		r1.setValue(c1);
		treeSemanticRecord.add(r1);
		RreeSemanticRecord r2=new RreeSemanticRecord();
		r2.setTreeNodeNum(treeNodeNum);
		r2.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r2.setProperty("width");
		r2.setValue(c2);
		treeSemanticRecord.add(r2);
	}
	//ÓïÒåÆ¬¶Î35,L->id [ E ] {L.array=lookup(id.lexeme);if L.array==null then error;
	// L.type=L.array.type.elem; L.offset = newtemp(); gen(L.offset=E.addr*L.type.width);}
	public String semanticRule_35(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<CharTable> charTable,ArrayList<FourAddr> fourAddr,int treeNodeNum){
		int childID1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[3]);
		String childName1=slrTreeArray.get(childID1).getName();
		String lexeme=findValue(treeSemanticRecord,childID1,"lexeme");
		//L.array=lookup(id.lexeme);
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(treeNodeNum);
		r1.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r1.setProperty("array");
		r1.setValue(lexeme);
		treeSemanticRecord.add(r1);
		//L.type=L.array.type.elem;
		for(int i=0;i<charTable.size();i++)
		{
			if(charTable.get(i).getChara().equals(lexeme)){
				String ty=charTable.get(i).getType();
				int l=ty.indexOf(",");
				String elem=ty.substring(l+1, ty.length()-1);
				System.out.println("ty="+ty);
				System.out.println("l="+l);
				System.out.println("elem="+elem);
				RreeSemanticRecord r2=new RreeSemanticRecord();
				r2.setTreeNodeNum(treeNodeNum);
				r2.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
				r2.setProperty("type");
				r2.setValue(elem);
				treeSemanticRecord.add(r2);
				//L.offset = newtemp(); 
				RreeSemanticRecord r3=new RreeSemanticRecord();
				r3.setTreeNodeNum(treeNodeNum);
				r3.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
				r3.setProperty("offset");
				r3.setValue("t"+treeNodeNum);
				treeSemanticRecord.add(r3);
				//gen(L.offset=E.addr*L.type.width);
				int width=4;
				for(int j=0;j<ty.length();j++)
				{
					//System.out.println("width="+width);
					//System.out.println("ty.charAt(j)"+ty.charAt(j));
					if(ty.charAt(j)>=48 && ty.charAt(j)<=57)
					{   //System.out.println("ty.substring(j,j+1)="+ty.substring(j,j+1));
						width*=Integer.parseInt(ty.substring(j,j+1));
					}
				}
				int childID2=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[1]);
				String addr=findValue(treeSemanticRecord,childID2,"addr");
				String a="t"+treeNodeNum+"= "+addr+"*"+width;
				//ArrayList<FourAddr> fourAddr
				FourAddr fa=new FourAddr();
				fa.setOp("*");
				fa.setParam1(addr);
				fa.setParam2(String.valueOf(width));
				fa.setToaddr("t"+treeNodeNum);
				fourAddr.add(fa);
				return a;
			}
		}
		return "error 1 "+lexeme;
		
	}
	//ÓïÒåÆ¬¶Î36,L->L1 [ E ]  {L.array = L1.array; L.type=L1.type.elem;t=newtemp();
	//                     gen(t=E.addr*L.type.width); 
	//                     L.offset = newtemp(); 
	//                     gen(L.offset= L1.offset+t);}
	public String[] semanticRule_36(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,
			ArrayList<FourAddr> fourAddr,int treeNodeNum){
		int childID1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[3]);
		String array=findValue(treeSemanticRecord,childID1,"array");
		String l1offset=findValue(treeSemanticRecord,childID1,"offset");
		//L.array = L1.array;
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(treeNodeNum);
		r1.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r1.setProperty("array");
		r1.setValue(array);
		treeSemanticRecord.add(r1);
		//L.type=L1.type.elem;
		String ty=findValue(treeSemanticRecord,childID1,"type");
		int l=ty.indexOf(",");
		String elem=ty.substring(l+1, ty.length()-1);
		System.out.println("ty="+ty);
		System.out.println("l="+l);
		System.out.println("elem="+elem);
		RreeSemanticRecord r2=new RreeSemanticRecord();
		r2.setTreeNodeNum(treeNodeNum);
		r2.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r2.setProperty("type");
		r2.setValue(elem);
		treeSemanticRecord.add(r2);
		
		//t=newtemp();
		int childID2=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[1]);
		String t="t"+childID2;
		// gen(t=E.addr*L.type.width); 
		String addr=findValue(treeSemanticRecord,childID2,"addr");
		int width=4;
		for(int j=0;j<ty.length();j++)
		{
			//System.out.println("width="+width);
			//System.out.println("ty.charAt(j)"+ty.charAt(j));
			if(ty.charAt(j)>=48 && ty.charAt(j)<=57)
			{   //System.out.println("ty.substring(j,j+1)="+ty.substring(j,j+1));
				width*=Integer.parseInt(ty.substring(j,j+1));
			}
		}
		String b=t+"="+addr+"*"+width;
		//L.offset = newtemp();gen(L.offset= L1.offset+t);
		RreeSemanticRecord r3=new RreeSemanticRecord();
		r3.setTreeNodeNum(treeNodeNum);
		r3.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r3.setProperty("offset");
		r3.setValue("t"+treeNodeNum);
		treeSemanticRecord.add(r3);
		String a="t"+treeNodeNum+"="+ l1offset+"+"+t;
		String[] c={b,a};
		//ArrayList<FourAddr> fourAddr
		FourAddr fa=new FourAddr();
		fa.setOp("*");
		fa.setParam1(addr);
		fa.setParam2(String.valueOf(width));
		fa.setToaddr(t);
		fourAddr.add(fa);
		FourAddr fa2=new FourAddr();
		fa2.setOp("+");
		fa2.setParam1(l1offset);
		fa2.setParam2(t);
		fa2.setToaddr("t"+treeNodeNum);
		fourAddr.add(fa2);
		return c;
		
	}
	//ÓïÒåÆ¬¶Î37,36 : X->int {X.type=int;X.width=4;}
	public void semanticRule_37(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum){
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(treeNodeNum);
		r1.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r1.setProperty("type");
		r1.setValue("int");
		treeSemanticRecord.add(r1);
		RreeSemanticRecord r2=new RreeSemanticRecord();
		r2.setTreeNodeNum(treeNodeNum);
		r2.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r2.setProperty("width");
		r2.setValue("4");
		treeSemanticRecord.add(r2);
	}
	//ÓïÒåÆ¬¶Î38, X->char  {X.type=char;X.width=4;}
	public void semanticRule_38(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,int treeNodeNum){
		RreeSemanticRecord r1=new RreeSemanticRecord();
		r1.setTreeNodeNum(treeNodeNum);
		r1.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r1.setProperty("type");
		r1.setValue("char");
		treeSemanticRecord.add(r1);
		RreeSemanticRecord r2=new RreeSemanticRecord();
		r2.setTreeNodeNum(treeNodeNum);
		r2.setTreeNodeName(slrTreeArray.get(treeNodeNum).getName());
		r2.setProperty("width");
		r2.setValue("4");
		treeSemanticRecord.add(r2);
		}
	
	//ÓïÒåÆ¬¶Î39,S->while B { S1 {gen(goto S.begin);} }
	public String semanticRule_39(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<FourAddr> fourAddr,int treeNodeNum){
		String value=findValue(treeSemanticRecord,treeNodeNum,"begin");
		String s="goto "+value;
		System.out.println(s);
		//ArrayList<FourAddr> fourAddr
		FourAddr fa=new FourAddr();
		fa.setOp("j");
		fa.setParam1("-");
		fa.setParam2("-");
		fa.setToaddr(value);
		fourAddr.add(fa);
		return s;
	}
	//ÓïÒåÆ¬¶Î40,S->if B { S1 {gen(goto S.next);}} else { S2 }
	public String semanticRule_40(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<FourAddr> fourAddr,int treeNodeNum){
		String value=findValue(treeSemanticRecord,treeNodeNum,"next");
		String s="goto "+value;
		System.out.println(s);
		//ArrayList<FourAddr> fourAddr
		FourAddr fa=new FourAddr();
		fa.setOp("j");
		fa.setParam1("-");
		fa.setParam2("-");
		fa.setToaddr(value);
		fourAddr.add(fa);
		return s;
	}
	
	//ÓïÒåÆ¬¶Î41,D->T id ; {enter(id.lexeme,T.type,offset);offset=offset+T.width;}
	public String semanticRule_41(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,
			ArrayList<CharTable> charTable,int treeNodeNum){
		String value=findValue(treeSemanticRecord,treeNodeNum,"next");
		int childID1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[1]);
		String c1=findValue(treeSemanticRecord,childID1,"lexeme");
		int childID2=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[2]);
		String c2=findValue(treeSemanticRecord,childID2,"type");
		
		String offset=findValue(treeSemanticRecord,slrTreeArray.size(),"offset");
		System.out.println("¼ÓÈëµÚ"+charTable.size()+"¸ö"+c1+" "+c2+" "+offset);
		CharTable c=new CharTable();
		c.setChara(c1);
		c.setType(c2);
		c.setOffset(offset);
		charTable.add(c);
		String c3=findValue(treeSemanticRecord,childID2,"width");
		String c4=findValue(treeSemanticRecord,slrTreeArray.size(),"offset");
		//offset=offset+T.width;
		if(!c3.equals("null"))
		{
			System.out.println(c3);
			int off=Integer.parseInt(c3)+Integer.parseInt(c4);
			updateValue(treeSemanticRecord, slrTreeArray.size(), "offset",String.valueOf(off));
		}
		for(int i=0;i<charTable.size()-1;i++){
			if(charTable.get(i).getChara().equals(c1))
			{
				return "error "+2+" "+c1;
			}
		}
		return "success";
		
	}
	//ÓïÒåÆ¬¶Î42,F->E
	public void semanticRule_42(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<String> param,int treeNodeNum){
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[0]);
		String addr=findValue(treeSemanticRecord,childID,"addr");
		param.add(addr);
	}
	//ÓïÒåÆ¬¶Î43,F->F,E
	public void semanticRule_43(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<String> param,int treeNodeNum){
		int childID=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[0]);
		String addr=findValue(treeSemanticRecord,childID,"addr");
		param.add(addr);
	}
	//ÓïÒåÆ¬¶Î44,S->call id ( F ) ;{} 
	public ArrayList<String> semanticRule_44(ArrayList<RreeSemanticRecord> treeSemanticRecord,
			ArrayList<SLRTree> slrTreeArray,ArrayList<String> param,ArrayList<FourAddr> fourAddr,
			ArrayList<CharTable> charTable ,int treeNodeNum){
		int childID2=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[4]);
		String id=findValue(treeSemanticRecord,childID2,"lexeme");
		ArrayList<String> a=new ArrayList<String>();
		int n=0;
		for(int i=0;i<param.size();i++)
		{
			a.add("param "+param.get(i));
			FourAddr fa=new FourAddr();
			fa.setOp("param");
			fa.setParam1(param.get(i));
			fa.setParam2("-");
			fa.setToaddr("-");
			fourAddr.add(fa);
		}
		a.add("call "+id+" , "+param.size());
		FourAddr fa=new FourAddr();
		fa.setOp("call");
		fa.setParam1(id);
		fa.setParam2(String.valueOf(param.size()));
		fa.setToaddr("-");
		fourAddr.add(fa);
		//ArrayList<CharTable> charTable 
		boolean is=false;
		for(int i=0;i<charTable.size();i++)
		{
			if(charTable.get(i).getChara().equals(id))
			{
				if(charTable.get(i).getType().equals("proc"))
				{
					is=true;
				}
			}
		}
		if(is)
		{
			System.out.println("success");
			return a;
		}
		ArrayList<String> b=new ArrayList<String>();
		System.out.println("error 5 "+id);
		b.add("error 5 "+id);
		return b;
		
	}
	
	//ÓïÒåÆ¬¶Î45,D->proc id ; D S  {enter(id.lexeme,proc,int);}
		public String semanticRule_45(ArrayList<RreeSemanticRecord> treeSemanticRecord,ArrayList<SLRTree> slrTreeArray,
				ArrayList<CharTable> charTable,int treeNodeNum){
			int childID1=Integer.parseInt(slrTreeArray.get(treeNodeNum).getChildId()[3]);
			System.out.println("childID1="+childID1);
			String c1=findValue(treeSemanticRecord,childID1,"lexeme");
			System.out.println("¼ÓÈëµÚ"+charTable.size()+"¸ö"+c1+" ");
			CharTable c=new CharTable();
			c.setChara(c1);
			c.setType("proc");
			c.setOffset("int");
			charTable.add(c);
			for(int i=0;i<charTable.size()-1;i++){
				if(charTable.get(i).getChara().equals(c1))
				{
					return "error "+4+" "+c1;
				}
			}
			return "success";
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
	
    public void updateValue(ArrayList<RreeSemanticRecord> treeSemanticRecord,int treeNodeNum,String property,String value){
		for(int i=0;i<treeSemanticRecord.size();i++)
		{
			if(treeSemanticRecord.get(i).getTreeNodeNum()==treeNodeNum&&treeSemanticRecord.get(i).getProperty().equals(property))
			{
				
				treeSemanticRecord.get(i).setValue(value);;
			}
		}
		
	}
    
    private String newlabel(ArrayList<String> addrList){
    	System.out.println("newlabel");
    	if(addrList.size()!=0)
    	{
    		for(int i=0;i<addrList.size();i++)
    		{
    			System.out.println(addrList.get(i));
    		}
    		String addr=addrList.get(addrList.size()-1);
    		int a=Integer.parseInt(String.valueOf(addr.substring(1)));
    		int b=a+1;
    		String newaddr="L"+b;
    		addrList.add(newaddr);
    		return newaddr;
    	}
    	addrList.add("L1");
    	return "L1";
    }

}
