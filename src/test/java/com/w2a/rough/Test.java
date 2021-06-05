package com.w2a.rough;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;


public class Test {


	public static void main(String[] args) {
		String path = null;

		int row = 3;
		Hashtable<String,String> tab = new Hashtable<String, String>();


		tab.put("yat","123");
		tab.put("cha","456");
		Object [] obj = new Object[row];
		obj[0] = tab;


}
	
}
