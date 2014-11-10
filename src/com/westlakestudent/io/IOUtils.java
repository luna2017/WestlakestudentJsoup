package com.westlakestudent.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * IOUtils
 * @author chendong
 * 2014年9月12日 下午4:52:22
 * 
 * @version 2.0.0
 *
 */
public class IOUtils {
	private static final String path = "F:ANDROID_SOURCE/";
	private static IOUtils instance = null;
	
	private IOUtils(){
		File f = new File(path);
		if(!f.exists())
			f.mkdir();
	}
	
	public static IOUtils getInstance(){
		if(instance == null)
			instance = new IOUtils();
		return instance;
	}
	
	public void mkdirchild(String url){
		int start = url.indexOf("java/android/");
//		System.out.println(url.substring(start + 13, url.length()));
		String filename = url.substring(start + 13, url.length());
		String realpath = path + filename;
		File child = new File(realpath);
		if(!child.exists())
			child.mkdir();
		
	}
	
	public void saveFile(String url,String content){
		int start = url.indexOf("java/android/");
//		System.out.println(url.substring(start + 13, url.length()));
		String filename = url.substring(start + 13, url.length());
		String realpath = path + filename;
		File f = new File(realpath);
		System.out.println(filename + ": downloading....");
		try {
			FileOutputStream out = new FileOutputStream(f);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			bout.write(content.getBytes());
			bout.close();
			System.out.println(filename + ": download over");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
