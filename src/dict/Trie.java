package dict;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Trie {

	static Node root = new Node() ;
	static class Node
	{
		String word , meaning ;
		boolean isEnd ;
		Map<Character, Node> children = new HashMap();
		Node()
		{
			isEnd = false ;
		}

	}

	public static void main(String[] args) {

		//insert("hello","hi");
			System.out.println("kk");

	}

	public static String search(String string) {

		Node current = root ;
		int l = string.length();
		int i = 0;
		while(i < l)
		{
			Node n = current.children.get(string.charAt(i));
			if(n!=null)
			{
					current = n ;
			}
			else
			{
				return "Word not found ";
			}
			i++;
		}
		if(current.isEnd==true)
		{
			return current.meaning ;
		}

		else
			return "Word not found ";

	}





	public static void insert(String word,String meaning) {

		Node current = root ;
		int l = word.length();
		int i=0 ;
		while(i<l)
		{
			char c = word.charAt(i);
			Node n = current.children.get(c);
			if(n==null)
			{
				 n = new Node() ;
				current.children.put(c, n);
			}
			current = n ;
			i++;
		}
		current.isEnd = true ;
		current.word = word ;
		current.meaning = meaning ;
	}

}
