package com.project.RetriveTool;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public final class RetriveTool {
	//get related Tag
	public static NodeList getTagList(Parser parser,String properity) {
		NodeFilter filter=new TagNameFilter(properity);
		NodeList TagList=null;
		try {
			TagList = parser.extractAllNodesThatMatch(filter);
			return TagList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Parser Exception"+e);
		}
		return null;
		
	}
	//get node
	public static Node getNode(NodeList nodelist,String filterstr){
		for(int i=0;i<nodelist.size();i++){
			if(nodelist.elementAt(i).getText().toLowerCase().startsWith(filterstr)){
				return nodelist.elementAt(i);
			}
		}
		return null;
	}
	//wraper function getTag
	public static Node getTag(Parser parser,String properity,String filterstr) throws ParserException{
		return getNode(getTagList(parser,properity),filterstr);
	}
	//get related child tag
	public static NodeList getFilteredTagList(Node node,String properity){
		NodeList TagList=new NodeList(),TempList;
		Node TempNode;
		if(node.getChildren().size()>0){
			TempList=node.getChildren();
			for(int i=0;i<TempList.size();i++){
				TempNode=TempList.elementAt(i);
				if(TempNode.getText().toLowerCase().startsWith(properity)){
					TagList.add(TempNode);
				}
			}
		}
		return TagList;
	}
	//get plainText
	public static String getPlainText(NodeList TagList){
		String TextStr="";
		for(int i=0;i<TagList.size();i++){
			TextStr+=TagList.elementAt(i).toPlainTextString()+"\r\n";
		}
		return TextStr;
	}
	public static void plainTextWriter(String Data,String FileName,boolean flag) throws IOException{
		FileOutputStream fos=new FileOutputStream(FileName,flag);
		OutputStreamWriter osr=new OutputStreamWriter(fos);
		BufferedWriter bw=new BufferedWriter(osr);
		bw.write(Data);
		bw.close();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}

}
