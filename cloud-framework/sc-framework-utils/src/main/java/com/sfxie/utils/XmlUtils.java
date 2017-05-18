package com.sfxie.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

public class XmlUtils {
	
	public static String getHttpText(String urlInfo,String userName,String password)throws MalformedURLException,IOException {
		URL url = null;
		String tempStr = null;
            url = new URL(urlInfo);
	        HttpURLConnection huc = null;
	        String  credit  =  userName+":"+password;
	        String  encoding  =  new  sun.misc.BASE64Encoder().encode  (credit.getBytes());	        
	        
	        StringBuffer sb = new StringBuffer();
	        huc = (HttpURLConnection) url.openConnection();
	        huc.setAllowUserInteraction(false);
	        huc.setRequestProperty  ("Authorization",  "Basic  "  +  encoding);
	        BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream(),"utf-8"));
	        String line = null;	         
	        while ((line = br.readLine()) != null) {
	                sb.append(line).append("\n");
	            }
	        tempStr = sb.toString();	
        return tempStr;	
    }
	
	public static String postHttpData(String postUrl) throws Exception {
		OutputStreamWriter out = null;
		InputStream in = null;
		BufferedReader br = null;
		HttpURLConnection httpURLConnection = null;
		try {
			URL url = new URL(postUrl);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setDoOutput(false);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("content-type",
					"text/xml;charset=UTF-8");
			httpURLConnection.setRequestProperty("Accept-Language", "zh-cn");
			in = httpURLConnection.getInputStream();
			httpURLConnection.getResponseMessage();

			StringBuilder sb = new StringBuilder();
			if (200 == httpURLConnection.getResponseCode()) {
				br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				String readLine = null;
				while ((readLine = br.readLine()) != null) {
					sb.append(readLine);
				}

			}
			return sb.toString();
		} catch (IOException e) {
			throw new RuntimeException("连接" + postUrl + "错误：", e);
		} finally {
			try {
				if (null != out) {
					out.close();
				}
				if (null != br) {
					br.close();
				}
				if (null != in) {
					in.close();
				}
				if (null != httpURLConnection) {
					httpURLConnection.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 将注解有@XmlRootElement注解的对象序列化为byte[]
	 * @param xmlObj
	 * @return
	 * @throws JAXBException
	 */
	public static byte[] serializerBytes(Object xmlObj) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(xmlObj.getClass());  
        Marshaller marshaller = context.createMarshaller(); 
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        marshaller.marshal(xmlObj, baos);
        return baos.toByteArray();
	}
	/**
	 * 将注解有@XmlRootElement注解的对象序列化为OutputStream
	 * @param xmlObj
	 * @return
	 * @throws JAXBException
	 */
	public static OutputStream serializerOutputStream(Object xmlObj) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(xmlObj.getClass());  
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        marshaller.marshal(xmlObj, baos);
        return baos;
	}
	/**
	 * 将注解有@XmlRootElement注解的对象序列化为String
	 * @param xmlObj
	 * @return
	 * @throws JAXBException
	 * @throws ParserConfigurationException 
	 */
	public static String serializerXmlString(Object xmlObj) throws JAXBException, ParserConfigurationException{
        ByteArrayOutputStream baos= (ByteArrayOutputStream) serializerOutputStream(xmlObj);
        byte[] bystr = baos.toByteArray();
        String str = "";
        try {
            str = new String(bystr,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
	}
	/**
	 * 将xml字符串反序列化为注解有@XmlRootElement注解的对象
	 * @param clazz
	 * @param xml
	 * @return
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unserializer(Class<T> clazz,String xml) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(clazz);  
        Unmarshaller unmarshaller = context.createUnmarshaller();  
		T obj = (T) unmarshaller.unmarshal(new StringReader(xml));  
		return obj;
	}
	/**
	 * 将byte[]字符串反序列化为注解有@XmlRootElement注解的对象
	 * @param clazz
	 * @param xml
	 * @return
	 * @throws JAXBException
	 */
	public static <T> T unserializer(Class<T> clazz,byte[] xml) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(clazz);  
        Unmarshaller unmarshaller = context.createUnmarshaller();  
        InputStream is = new ByteArrayInputStream(xml); 
        @SuppressWarnings("unchecked")
		T obj = (T) unmarshaller.unmarshal(is);  
		return obj;
	}
	/**
	 * 将xml的InputStream反序列化为注解有@XmlRootElement注解的对象
	 * @param clazz
	 * @param xmlInput
	 * @return
	 * @throws JAXBException
	 */
	public static <T> T unserializer(Class<T> clazz,InputStream xmlInput) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(clazz);  
        Unmarshaller unmarshaller = context.createUnmarshaller();  
        @SuppressWarnings("unchecked")
		T obj = (T) unmarshaller.unmarshal(xmlInput);  
		return obj;
	}
}
