package com.project.PARSEHTML;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.*;

import org.htmlparser.*;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.project.Classes.Person;
import com.spreada.utils.chinese.ZHConverter;

public class RetriveHTML {
	final static String NAME = "";
	final static String GENDER_M = "女";
	final static String GENDER_F = "男";
	final static String OCCUPATION = "职业";
	final static String NATION = "国籍";
	final static String NATIVEPLACE = "籍贯";
	final static String DATEOFBORTH = "出生";
	final static String MERRIAGE = "配偶";
	final static String DATHORNOT = "在世人物";

	// 返回特定的tablelist
	public static NodeList getTable(Parser parser) {
		NodeFilter filter = new NodeClassFilter(TableTag.class);
		NodeList table_list = null;
		NodeList result_list = null;
		try {
			table_list = parser.extractAllNodesThatMatch(filter);
			Node table_node = table_list.elementAt(0);
			// for(int i=0;i<table_list.size();i++){
			if (table_node.getText().toLowerCase()
					.startsWith("table class=\"infobox vcard\"")) {
				result_list = table_node.getChildren();
				return result_list;
			}
			// }
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 返回div
	public static NodeList getDiv(Parser parser) {
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList Div_list = null;
		NodeList result_list = null;
		Node node;
		try {
			Div_list = parser.extractAllNodesThatMatch(filter);
			for (int i = 0; i < Div_list.size(); i++) {
				node = Div_list.elementAt(i);
				if (node.getText().toLowerCase()
						.startsWith("div id=\"mw-normal-catlinks")) {
					result_list = node.getChildren();
					return result_list;
				}
			}
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// catch personal data
	public static void catchData(String url, Connection conn) {
		try {
			/*
			 * person对象创建
			 */
			Person person = new Person();
			person.setName(url);
			// statement用来执行SQL语句
			Statement statement = conn.createStatement();
			/*
			 * 抓取网页一次
			 */
			Parser parser = new Parser((HttpURLConnection) (new URL(
					"http://zh.wikipedia.org/wiki/" + url)).openConnection());
			Parser parser1 = new Parser((HttpURLConnection) (new URL(
					"http://zh.wikipedia.org/wiki/" + url)).openConnection());
			/*
			 * 抓取table
			 */
			NodeList table_list = getTable(parser);
			Node node;
			NodeList child_list, tdth_list;
			String attrstr = null, valuestr = null;
			for (int i = 0; i < table_list.size(); i++) {
				node = table_list.elementAt(i);
				Parser mparser = Parser.createParser(node.toHtml(), "GB2312");
				NodeFilter filter = new TagNameFilter("tr");
				child_list = mparser.extractAllNodesThatMatch(filter);
				for (int j = 0; j < child_list.size(); j++) {
					System.out.println("@@@@@@@@@@@");
					node = child_list.elementAt(j);
					tdth_list = node.getChildren();
					System.out.println(tdth_list.size());
					if (tdth_list.size() > 3) {
						for (int i1 = 0; i1 < tdth_list.size(); i1++) {
							node = tdth_list.elementAt(i1);
							if (attrstr == null
									&& node.toPlainTextString().trim().length() != 0) {
								attrstr = node.toPlainTextString().trim(); //
								System.out.println(node.toPlainTextString()
										+ "\n####");
								valuestr = "";
								i1++;
								continue;
							}
							if (attrstr != null
									&& node.toPlainTextString().trim().length() != 0) {
								valuestr += node.toPlainTextString().trim(); //
								System.out.println(node.toPlainTextString()
										+ "\n####");
							}
						}
					}
					if (attrstr != null && valuestr != "") {
						ZHConverter converter = ZHConverter
								.getInstance(ZHConverter.SIMPLIFIED);
						attrstr = converter.convert(new String((attrstr
								.replaceAll(" ", "").trim()).getBytes("GBK"),
								"gbk"));
						valuestr = converter.convert(new String((valuestr
								.replaceAll(" ", "").trim()).getBytes("GBK"),
								"gbk"));
						System.out.println(attrstr + ":" + valuestr);
						AttrFill(attrstr, valuestr, person);

						attrstr = null;
						valuestr = "";
					}
				}
			}

			/*
			 * 抓取GENDER和DATHORNOT信息
			 */
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAA");
			child_list = getDiv(parser1);
			

			Parser parser2 = Parser.createParser(child_list.toHtml(), "GB2312");
			NodeFilter filter = new TagNameFilter("ul");
			NodeList List = parser2.extractAllNodesThatMatch(filter);
			System.out.println( List.size());
			for (int j = 0; j < List.size(); j++) {
				System.out.println(List.elementAt(j).toPlainTextString());
				valuestr=List.elementAt(j).toPlainTextString();
			}

			System.out.println("AAAAAAAAAAAAAAAAAAAAAAA");
			ZHConverter converter = ZHConverter
					.getInstance(ZHConverter.SIMPLIFIED);
			valuestr = converter.convert(new String((valuestr.replaceAll(" ",
					"").trim()).getBytes("GBK"), "gbk"));
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAA"+valuestr);
			FillRegx(valuestr, person);
			
			PersonPrinter(person);
			// 要执行的SQL语句
			String sql = "insert into person (Name,Gender,DateOfBorn,Occupation,Nationality,NativePlace,DeathOrNot,Marriage) values";
			sql += " (\"" + person.getName() + "\",";
			sql += "\"" + person.getGender() + "\",";
			sql += "\"" + person.getDateOfBorn() + "\",";
			sql += "\"" + person.getOccupation() + "\",";
			sql += "\"" + person.getNationality() + "\",";
			sql += "\"" + person.getNativePlace() + "\",";
			sql += person.isDeathOrNot() + ",";
			sql += person.isMarriage() + ")";
			statement.execute(sql);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	/*
	 * 用於匹配
	 */
	// 填充匹配函數
	public static void FillRegx(String str, Person person) {
		System.out.println("%%%%%%%%%%%%"+str);
		if (str.contains(GENDER_M)) {
			System.out.println(GENDER_M);
			person.setGender(GENDER_M);
		}
		else if(str.contains(GENDER_F)) {
			System.out.println(GENDER_F);
			person.setGender(GENDER_F);
		}
		if (str.contains(DATHORNOT)) {
			System.out.println(DATHORNOT);
			person.setDeathOrNot(false);
		}
		
		System.out.println("%%%%%%%%%%%%");

	}

	// 填充函数
	public static void AttrFill(String atr, String value, Person person) {
		String nativeplace=GetProvince(value,ProvinceReader());
		System.out.println("ATTRFILL"+value+ProvinceReader()+nativeplace);
		if (atr.equalsIgnoreCase(OCCUPATION)) {
			person.setOccupation(value.trim());
		} else if (atr.equalsIgnoreCase(NATION)) {
			person.setNationality(value.replaceAll("&#160;", "").trim());
		} else if (atr.equalsIgnoreCase(NATIVEPLACE)) {
			person.setNativePlace(nativeplace);
		} else if (atr.equalsIgnoreCase(DATEOFBORTH)) {
			if(person.getNativePlace()==null){
				person.setNativePlace(nativeplace);
			}
			value = value.substring(0, value.lastIndexOf("日") + 1).trim();
			value = value.replaceAll("月", "-").replaceAll("年", "-")
					.replaceAll("日", "");
			person.setDateOfBorn(value);
		} else if (atr.equalsIgnoreCase(MERRIAGE)) {
			person.setMarriage(true);
		}
	}

	// 打印person
	public static void PersonPrinter(Person person) {
		System.out.println(person.getName() + person.getNationality()
				+ person.getOccupation() + person.getDateOfBorn());
	}
	/*
	 * 省份reader
	 * 返回arrayList
	 * */
	public static ArrayList<String> ProvinceReader(){
		ArrayList<String> result_list=new ArrayList<String>();
		String str_item=null;
		try {
			FileInputStream fis=new FileInputStream("province.txt");
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader br=new BufferedReader(isr);
			
			while((str_item=br.readLine())!=null){ 
				result_list.add(str_item);
			}
			br.close();
			isr.close();
			fis.close();
			return result_list;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 省份提取
	 * 从文本中提取存在的省份
	 * */
	public static String GetProvince(String input,ArrayList<String> list){
		String namestr=null;
		for(int i=0;i<list.size();i++){
			namestr=list.get(i).trim();
/*			if(input.contains(namestr)){
				return namestr;
			}*/
			if(Pattern.compile(namestr).matcher(input).find()){
				return namestr;
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub

		try {
			// /数据库连接陈旭
			String driver = "com.mysql.jdbc.Driver";
			String sql_url = "jdbc:mysql://127.0.0.1:3366/test";
			String user = "root";
			String password = "315214";
			// 加载驱动程序
			Class.forName(driver);
			// 连续数据库
			Connection conn = DriverManager.getConnection(sql_url, user,
					password);
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			int i = 0;
			String namestr;
			FileInputStream is = new FileInputStream("name.txt");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			try {
				while ((namestr = br.readLine()) != null && i < 50) {
					catchData(namestr, conn);
					i++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// getTagList(parser,"");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
