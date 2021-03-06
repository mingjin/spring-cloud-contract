package com.ifpay.mock.common.utils;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by harvey.xu on 2016/1/28.
 */
public  class HttpRequest {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param parameters 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, Map<String, String> parameters) {
        String result = "";
        BufferedReader in = null;// 读取响应输入流
        StringBuffer sb = new StringBuffer();// 存储参数
        String params;// 编码之后的参数
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            String full_url = url + "?" + params;

            URL realUrl = new URL(full_url);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            //必须设置false，否则会自动redirect到重定向后的地址
//            connection.setInstanceFollowRedirects(false);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();

            //判定是否会进行302重定向
//            if (connection.getResponseCode() == 302) {
//                //如果会重定向，保存302重定向地址，以及Cookies,然后重新发送请求(模拟请求)
//                String location = connection.getHeaderField("Location");
//                String cookies = connection.getHeaderField("Set-Cookie");
//
//                realUrl = new URL(location);
//                connection = (HttpURLConnection) realUrl.openConnection();
//                connection.setRequestMethod("GET");
//                connection.setRequestProperty("Cookie", cookies);
//                connection.addRequestProperty("Accept-Charset", "UTF-8;");
//                connection.addRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
//                connection.addRequestProperty("Referer", "http://matols.com/");
//                connection.connect();
//                System.out.println("跳转地址:" + location);
//            }

            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常!");
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
//            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String doPost(Map reqMap, Map signMap) {

        String response = "";
        List keys = new ArrayList(signMap.keySet());
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod();
        method.setRequestHeader("Content-type", "text/xml; charset=utf-8");
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, new Integer(60000));
        try{
            URL url = new URL(reqMap.get("requestUrl").toString());
            method.setPath(url.getPath());
            if(reqMap.get("requestUrl").toString().indexOf("https")==0){
                //https
                //TODO
            } else {
                client.getHostConfiguration().setHost(url.getHost(),url.getPort(),url.getProtocol());
            }
            NameValuePair[] nameValuePairs=new org.apache.commons.httpclient.NameValuePair[signMap.size()];
            for(int i=0; i < signMap.size(); i++){
                String name = (String)keys.get(i);
                String value = (String)signMap.get(name);
                nameValuePairs[i] = new org.apache.commons.httpclient.NameValuePair(name, value);
            }
            method.setQueryString(nameValuePairs);
            int rescode = client.executeMethod(method);
            System.out.println(reqMap.get("requestUrl").toString() + " rescode:" + rescode);
            if (rescode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),"utf-8"));
                String curline = "";
                while((curline = reader.readLine())!=null){
                    response+=curline;
                }
                System.out.println(reqMap.get("requestUrl").toString() + " response:" + response);
            } else {
                System.out.println(reqMap.get("requestUrl").toString() + " error:" + rescode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return response;
    }

    /**
     * 拼装html
     * @param reqMap
     * @param signMap
     * @param sign
     * @return
     */
    public static StringBuffer htmlBuild(Map reqMap, Map signMap, String sign) throws UnsupportedEncodingException {

        StringBuffer sbHtml = new StringBuffer();
        List keys = new ArrayList(signMap.keySet());

        sbHtml.append("<form id=\"kingnssubmit\" name=\"kingnssubmit\" action=\"").append(reqMap.get("requestUrl")).append("\" method=\"get\"  target=\"_blank\">");

        String name;
        String value;
        for (int i = 0; i < keys.size(); i++) {
            name = (String) keys.get(i);
            value = (String) signMap.get(name);
            if(value != null && !"".equals(value)){
                sbHtml.append("<input type=\"hidden\" name=\"").append(name).append("\" value=\"" + new String(value.getBytes(),"GBK") + "\"/>");
            }
        }
        sbHtml.append("<input type=\"hidden\" name=\"sign\" value=\"").append(sign).append("\"/>");
        sbHtml.append("<input type=\"hidden\" name=\"sign_type\" value=\"").append(reqMap.get("sign_type")).append("\"/>");
        //submit按钮控件请不要含有name属性
        sbHtml.append(new String("<input type=\"submit\" value=\"submit\"></form>"));
//        sbHtml.append(new String("<input type=\"submit\" value=\"确认支付\"></form>".getBytes("gbk"),"utf-8"));
//        sbHtml.append("<input type=\"submit\" value=\"确认支付\"></form>");

        System.out.println("sbHtml = " + sbHtml);
        return sbHtml;
    }

    public static String httpReq(String httpUrl, Map sPara){
        String response="";
        List keys = new ArrayList(sPara.keySet());
        org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
        PostMethod method = new PostMethod();
        method.setRequestHeader("Content-type", "text/xml; charset=GB2312");
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,new Integer(60000));
        try {
            URL url = new URL(httpUrl);
            method.setPath(url.getPath());
            client.getHostConfiguration().setHost(url.getHost(), url.getPort(),url.getProtocol());
            org.apache.commons.httpclient.NameValuePair[] nameValuePairs = new org.apache.commons.httpclient.NameValuePair[sPara.size()];
            for (int i = 0; i < sPara.size(); i++) {
                String name = (String) keys.get(i);
                String value = (String) sPara.get(name);
                nameValuePairs[i] = new org.apache.commons.httpclient.NameValuePair(name, value);
            }
            method.setQueryString(nameValuePairs);
            int rescode = client.executeMethod(method);
            System.out.println(httpUrl + " rescode:" + rescode);
            if (rescode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),"utf-8"));
                String curline = "";
                while ((curline = reader.readLine()) != null) {
                    response += curline;
                }
                System.out.println(httpUrl + " response:" + response);
            } else {
                System.out.println(httpUrl + " error:" + rescode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return response;
    }


//    public static void main(String[] args) {
//
//        Map<String, String> parameters = new HashMap<String, String>();
//        parameters.put("token", "EC-7q0Kv1583z4OG60ns");
//        //发送 GET 请求
//        String s = HttpRequest.sendGet("http://127.0.0.1/CITICBank", parameters);
//        System.out.println(s);
//
//        //发送 POST 请求
////        String sr = HttpRequest.sendPost("http://localhost:6144/Home/RequestPostString", "key=123&v=456");
////        System.out.println(sr);
//    }


}

