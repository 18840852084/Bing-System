package com.project.PARSEHTML;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.project.RetriveTool.RetriveTool;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;

public class RetriveAttrs {
	final String NAME ="";
	final String GENDER="";
	final String OCCUPATION="职业";
	final String NATION="国籍";
	final String NATIVEPLACE="籍贯";
	final String DATEOFBORTH="出生";
	final String MERRIAGE="配偶";
	class P_node{
		private String Name=null;
		private String Gender=null;
		private String Occupation=null;
		private String Nationality=null;
		private String NativePlace=null;
		private String DateOfBorn=null;
		private boolean Marriage;
		private String Works=null;
		private boolean DeathOrNot;
		public String getName() {
			return Name;
		}
		public String getNativePlace() {
			return NativePlace;
		}
		public void setNativePlace(String nativePlace) {
			NativePlace = nativePlace;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getGender() {
			return Gender;
		}
		public void setGender(String gender) {
			Gender = gender;
		}
		public String getOccupation() {
			return Occupation;
		}
		public void setOccupation(String occupation) {
			Occupation = occupation;
		}
		public String getNationality() {
			return Nationality;
		}
		public void setNationality(String nationality) {
			Nationality = nationality;
		}
		public String getDateOfBorn() {
			return DateOfBorn;
		}
		public void setDateOfBorn(String dateOfBorn) {
			DateOfBorn = dateOfBorn;
		}
		public boolean isMarriage() {
			return Marriage;
		}
		public void setMarriage(boolean marriage) {
			Marriage = marriage;
		}
		public String getWorks() {
			return Works;
		}
		public void setWorks(String works) {
			Works = works;
		}
		public boolean isDeathOrNot() {
			return DeathOrNot;
		}
		public void setDeathOrNot(boolean deathOrNot) {
			DeathOrNot = deathOrNot;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> attrlist = new ArrayList<String>();
		try {
			Node node;
			NodeList list;
			String value;
			String namestr;
			FileInputStream is;

			is = new FileInputStream("name.txt");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			while ((namestr = br.readLine()) != null) {
				System.out.println(namestr);
				try {
					HttpURLConnection connection = (HttpURLConnection) (new URL(
							"http://zh.wikipedia.org/wiki/" + namestr))
							.openConnection();
					connection
							.setRequestProperty("User-Agent",
									"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
					Parser parser = new Parser(connection);
					list = RetriveTool.getTagList(parser, "td");
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							node = list.elementAt(i);
							if (node.getText()
									.toLowerCase()
									.startsWith(
											"td style=\"white-space:nowrap;")) {
								value = node.toPlainTextString().trim()
										+ "\r\n";
								if (!attrlist.contains(value)) {
									attrlist.add(value);
									System.out.println(value);
								}
							}
						}
					}
				} catch (Exception e) {
					System.out.println(namestr+e);
				}
			}
			for (int j = 0; j < attrlist.size(); j++) {
				System.out.println(attrlist.get(j));
			}
			br.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception" + e);
		}
	}
}
