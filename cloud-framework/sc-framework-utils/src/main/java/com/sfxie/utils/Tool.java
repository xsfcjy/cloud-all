package com.sfxie.utils;

//import net.sf.json.JSONObject;
//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * User: wquan
 * Date: 13-6-22
 * Time: 下午2:05
 */
public class Tool {
    protected static final Log logger = LogFactory.getLog(Tool.class);

    /**
     * 获取请求URL
     *
     * @param request
     * @return
     */
    public static String getURL(HttpServletRequest request) {
        StringBuffer url = new StringBuffer();
        url.append("http://");
        url.append(request.getServerName());
        url.append(":");
        url.append(request.getServerPort());
        url.append(request.getContextPath());
        url.append(request.getServletPath());
        return url.toString();
    }
    public static String getContextPath(HttpServletRequest request){
        StringBuffer url = new StringBuffer();
        url.append("http://");
        url.append(request.getServerName());
        url.append(":");
        url.append(request.getServerPort());
        url.append(request.getContextPath());
        return url.toString();
    }

    /**
     *去除字符串结尾逗号","
     * @param str
     * @return
     */
    public static String removeChar(String str){
        String res="";
       if(str.endsWith(",")){
            res=str.substring(0,str.lastIndexOf(","));
       }else {
           res=str;
       }
        return res;
    }

    /**
     * 替换域名
     *
     * @param url    被替换的url
     * @param domain 目标域名
     * @return 替换后的url
     */
    public static String replaceUrl(String url, String domain) {
        Pattern pattern = Pattern.compile("^http://([^/]+)(/.*)");
        Matcher matcher = pattern.matcher(url);
        String returnUrl = "http://";
        String qr = "";
        String dm = "";
        String port = "";
        if (matcher.find()) {
            qr = matcher.group(2);
            dm = matcher.group(1);
        }
        String attr[] = dm.split(":");
        if (attr.length > 1) {
            port = ":"+attr[1];
        }
        returnUrl = returnUrl + domain +port + qr;
        return returnUrl;
    }



    /**
     * 获取请求根URL
     * @param request
     * @return
     */
    public static String getRootURL(HttpServletRequest request) {
        StringBuffer url = new StringBuffer();
        url.append("http://");
        url.append(request.getServerName());
        url.append(":");
        url.append(request.getServerPort());
        url.append(request.getContextPath());
        return url.toString();
    }

    /**
     * 添加url参数
     *
     * @param url
     * @param paramName
     * @param paramValue
     * @return url
     */
    public static String addURLParam(String url, String paramName, String paramValue) {
        url += (url.indexOf("?") == -1 ? "?" : "&");
        url += paramName + "=" + paramValue;
        return url;
    }

    /**
     * post发送http 请求
     *
     * @param param  请求参数
     * @param urlStr url
     * @return 响应字符串
     */
    public static String postMethod(String param, String urlStr, String contentType) throws Exception {
        byte[] xmlData = param.getBytes("utf-8");
        //获得到位置服务的链接
        URL url = new URL(urlStr);
        URLConnection urlCon = url.openConnection();
        urlCon.setDoOutput(true);
        urlCon.setDoInput(true);
        urlCon.setUseCaches(false);
        urlCon.setRequestProperty("Content-Type", contentType);
        urlCon.setRequestProperty("Content-length", String.valueOf(xmlData.length));
        urlCon.setRequestProperty("Connection", "close");
        DataOutputStream printout = new DataOutputStream(urlCon.getOutputStream());
        printout.write(xmlData);
        printout.flush();
        printout.close();
        //读取响应
        String resStr = Tool.getStringFromInputStream(urlCon.getInputStream());
        return resStr;
    }

    /**
     * list是否为空
     * @param list
     * @return 是或否
     */
    public static boolean isListBank(List list) {
        if (null == list) return true;
        if (list.size() == 0) return true;
        return false;
    }

    public static String httpPost(String param, String urlStr, String contentType) throws Exception{
    	URL localURL = new URL(urlStr);
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
        
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", null==contentType?"application/x-www-form-urlencoded":contentType);
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(param.length()));
        
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        
        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);
            
            outputStreamWriter.write(param);
            outputStreamWriter.flush();
            
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }
            
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            
        } finally {
            
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            
            if (outputStream != null) {
                outputStream.close();
            }
            
            if (reader != null) {
                reader.close();
            }
            
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            
            if (inputStream != null) {
                inputStream.close();
            }
            
        }

        return resultBuffer.toString();
    }

    static DecimalFormat decimalFormat;
    static DecimalFormat decimalFormat2;
    /**
     * 获取格式为两位小位（.00）的字符串形式
     * From： 网络
     *
     * @create 1x1 @ 2014年7月14日 下午1:32:49
     */
    public static String get00FloatString(Number num) {
        if (null == num) return "null";
        if (null == decimalFormat) {
            decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
            decimalFormat.applyPattern("0.00");
        }
        return decimalFormat.format(num);
    }
    /**
     * post发送http 请求
     *
     * @param param  请求参数
     * @param urlStr url
     * @return 响应字符串
     */
    public static InputStream postMethodBytes(String param, String urlStr, String contentType) throws Exception {
        byte[] xmlData = param.getBytes("utf-8");
        //获得到位置服务的链接
        URL url = new URL(urlStr);
        URLConnection urlCon = url.openConnection();
        urlCon.setDoOutput(true);
        urlCon.setDoInput(true);
        urlCon.setUseCaches(false);
        urlCon.setRequestProperty("Content-Type", contentType);
        urlCon.setRequestProperty("Content-length", String.valueOf(xmlData.length));
        DataOutputStream printout = new DataOutputStream(urlCon.getOutputStream());
        printout.write(xmlData);
        printout.flush();
        printout.close();
        return urlCon.getInputStream();
    }

    /**
     * 字节流 转换成字符串
     *
     * @param inputStream
     * @return 字符串
     */
    public static String getStringFromInputStream(InputStream inputStream) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        StringBuffer buf = new StringBuffer();
        String line;
        while ((line = in.readLine()) != null) {
            buf.append(line + "\n");
        }
        String returnStr = buf.length() == 0 ? null : buf.toString();
        return returnStr;
    }

    /**
     * 字节流 转换成字符串
     *
     * @param inputStream
     * @return 字符串
     */
    public static String getStringFromInputStream2(InputStream inputStream)  {
        BufferedReader in = null;
        String returnStr = null;
        try {
            in = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuffer buf = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                buf.append(line + "\n");
            }
            returnStr = buf.length() == 0 ? null : buf.toString();
        } catch (UnsupportedEncodingException e) {
            logger.error("不支持的编码格式："+e.getMessage());
        } catch (IOException e1) {
            logger.error("IO异常："+e1.getMessage());
        }
        return returnStr;
    }

    /**
     * 判断字符串是否为空或空字符串
     * @param val
     * @return true 为空 fasle 非空
     */
    public static boolean isEmpty(Object val){
        if(val!=null&&!"".equals(val)){
            return false;
        }
        return true;
    }

    /**
     * 读取文件
     *
     * @param path 文件路径
     * @return 文件内容
     */
    public static String readFile(String path) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        StringBuffer buf = new StringBuffer();
        String line;
        while ((line = in.readLine()) != null) {
            buf.append(line + "\n");
        }
        String returnStr = buf.length() == 0 ? null : buf.toString();
        return returnStr;
    }

    /**
     * @param d
     * @param day
     * @return
     * @throws Exception
     */
    public static Date addDate(Date d, long day) throws Exception {
        long time = d.getTime();
        day = day * 24 * 60 * 60 * 1000;
        time += day;
        return new Date(time);
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param name     cookie名字
     * @param value    cookie值
     */
    public static void addCookie(HttpServletResponse response, String name, String value) {
        int maxAge = 60 * 60 * 24 * 365;
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 根据名字获取cookie
     *
     * @param request
     * @param name    cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }


    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    public static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 转义xml 特殊字符字符
     * @param src
     * @return
     */
    public static String convertXMLChar(String src){
        src=src.replaceAll("&","&amp;");
        src=src.replaceAll("<","&lt;");
        src=src.replaceAll(">","&gt;");
        src=src.replaceAll("\"","&quot;");
        return src;
    }

    /**
     * write xml to terminal
     *
     * @param data
     * @param response
     * @throws Exception
     */
    public static void write(String data, HttpServletResponse response)  {
        response.setContentType("text/xml;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Accept");
        byte[] xmldata = new byte[0];
        ServletOutputStream output=null;
        try {
            xmldata = data.getBytes("utf-8");
            response.setContentLength(xmldata.length);
            output = response.getOutputStream();
            output.write(xmldata);
        } catch (UnsupportedEncodingException e) {
            logger.error("不支持的编码格式："+e.getMessage());
        } catch (IOException e) {
            logger.error("IO异常："+e.getMessage());
        }finally {
            try {
                output.flush();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给指定邮箱发送邮件
     *
     * @param toMail  接收邮箱地址
     * @param content 邮件内容
     * @param subject 邮件主题
     * @return
     */
    /*public static boolean sendMail(String toMail, String content, String subject) {
        try {
            String fromMail = Constants.mailName;
            String fromMailPassword = Constants.mailPwd;
            JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
            //设定mail server
            String emailDomain = fromMail.split("@")[1];
            senderImpl.setHost("smtp." + emailDomain);
            MimeMessage mailMessage = senderImpl.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
            //设置收件人
            messageHelper.setTo(toMail);
            //设置寄件人
            messageHelper.setFrom(fromMail);
            //设置主题
            messageHelper.setSubject(subject);
            //设置邮件内容
            messageHelper.setText(content, true);
            //设置发送人邮箱
            senderImpl.setUsername(fromMail);
            //设置发送人邮箱密码
            senderImpl.setPassword(String.valueOf(fromMailPassword));
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.timeout", "25000");
            senderImpl.setJavaMailProperties(prop);
            //发送邮件
            senderImpl.send(mailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e);
            return false;
        }
        return true;
    }*/

    public static long ipToInt(String ip) {
        String[] items = ip.split("\\.");
        return Long.valueOf(items[0]) << 24
                | Long.valueOf(items[1]) << 16
                | Long.valueOf(items[2]) << 8 | Long.valueOf(items[3]);
    }

    public static String intToIp(long ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    //生成订单编号
    public static String generatedOrderCode(String userID){
        return "lhq"+userID + System.currentTimeMillis();
    }


    /**
     * 获取汉字拼音首字母
     * @param chinese
     * @return
     */
    /*public static String getFirstSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (temp != null) {
                        pybf.append(temp[0].charAt(0));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString().replaceAll("\\W", "").trim();
    }*/

    

    public static String gettablename(String tablename){
        String date = new SimpleDateFormat("yyyyMM").format(new Date());
        tablename = tablename + "_" + date;
        return tablename;
    }
    
    /**
	 * 获取指定时间表名
	 * @param tableName  获取的表名 
	 * @param date  需要获取的日期
	 * @return
	 */
	public static String getTableThisMonth(String tableName, Date date) {
		String retTable = tableName;
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		if (Integer.parseInt(formatter.format(date)) > 20150630) {
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMM");
			String month = formatter2.format(date);
			retTable += "_"+month;
		}
		
		return retTable;
	}
	
    /**
     * 获取今天剩余秒数
     * @return
     */
    public static int getCacheSeconds() {
        Date date = new Date();
        long end = DateHelper.getStartDate(date, 1, 1).getTime() / 1000;
        long start = date.getTime() / 1000;
        String cacheSec = String.valueOf(end - start);
        return Integer.parseInt(cacheSec);
    }

    public static void main(String[] args) {

    }
}
