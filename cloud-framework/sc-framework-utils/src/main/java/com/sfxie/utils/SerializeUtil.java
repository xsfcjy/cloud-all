package com.sfxie.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.github.xsonorg.XSON;

public class SerializeUtil {

	/**
	 * 序列化对象
	 * xsf
	 * 2015年7月1日下午2:32:49
	 * TODO
	 * byte[]
	 */
	public static byte[] serialize(Object object) {

		ObjectOutputStream oos = null;

		ByteArrayOutputStream baos = null;

		try {

			// 序列化

			baos = new ByteArrayOutputStream();

			oos = new ObjectOutputStream(baos);

			oos.writeObject(object);

			byte[] bytes = baos.toByteArray();

			return bytes;

		} catch (Exception e) {

		}

		return null;

	}

	/**
	 * 反序列化对象
	 * xsf
	 * 2015年7月1日下午2:33:03
	 * TODO
	 * Object
	 */
	public static <T> T unserialize(Class<T> t ,byte[] bytes) {

		ByteArrayInputStream bais = null;

		try {

			// 反序列化

			bais = new ByteArrayInputStream(bytes);

			ObjectInputStream ois = new ObjectInputStream(bais);

			return (T) ois.readObject();

		} catch (Exception e) {
			 e.printStackTrace();
		}

		return null;

	}

	public static byte[] serializeByXson(Object object){
		byte[] data = XSON.write(object);
		return data;
	}
	public static <T> T unserializeByXson(Class<T> t ,byte[] bytes) {
		return (T)XSON.parse(bytes);
	}
	
	/**
	 * 获取clz所在jvm的文件字符串
	 * @param clz
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String getFileString(Class<?> clz,String filePath) throws IOException{
    	InputStream i = clz.getResource(filePath).openStream();
		String dd = inputStream2String(i);
		return dd;
    }
    
    private static String inputStream2String(InputStream is) throws IOException{
 	   BufferedReader in = new BufferedReader(new InputStreamReader(is,"UTF-8"));
 	   StringBuffer buffer = new StringBuffer();
 	   String line = "";
 	   while ((line = in.readLine()) != null){
 		   System.out.println(line);
 	     buffer.append(line);
 	   }
 	  is.close();
 	  in.close();
 	   return buffer.toString();
 	}
    
    /**
	 * 获取clz所在jvm的文件字符串
	 * @param clz
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String getFileString(String filePath,Class<?> clz) throws IOException{
    	InputStream i = clz.getResource(filePath).openStream();
		String dd = readString(clz.getResource("").getPath()+filePath);
		System.out.println(dd);
		return dd;
    }

	private static String readString(String filePath) {

		String str = "";
		File file = new File(filePath);
		try {
			FileInputStream in = new FileInputStream(file);
			// size 为字串的长度 ，这里一次性读完
			int size = in.available();
			byte[] buffer = new byte[size];
			in.read(buffer);
			in.close();
			str = new String(buffer, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return str;
	}
}