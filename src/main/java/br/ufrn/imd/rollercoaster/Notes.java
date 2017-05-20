package br.ufrn.imd.rollercoaster;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notes {
	
	private static Date date = new Date();
	private static int count = 1;
	
	public static String getHora(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); 
		date = new Date();
		return dateFormat.format(date); 
//		return time;
	}
	
	public static void print(String message){
		System.out.println(getHora() + "  " + count++ + "\t"+message);
	}
	

}
