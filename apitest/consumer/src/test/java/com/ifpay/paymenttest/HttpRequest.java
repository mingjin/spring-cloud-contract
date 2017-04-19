package com.ifpay.paymenttest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by harvey.xu on 2017/3/24.
 */
public class HttpRequest {

    public static String sendPost(String url, Map<String, String> params) throws Exception {

        URL u;
        HttpURLConnection con = null;

        // �����������
        StringBuffer sb = new StringBuffer();
        String sendString = "" ;
        if (params != null) {
            for (Entry<String, String> e : params.entrySet()) {
                sb.append(e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            sendString = sb.substring(0, sb.length() - 1);
        }
        //System.out.println("Request Url:" + url);
        //System.out.println("Request msg:" + sb.toString());
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            OutputStreamWriter osw = new OutputStreamWriter(con
                    .getOutputStream(), "UTF-8");
            osw.write(sendString);
            osw.flush();
            osw.close();
        } catch (Exception e) {
            throw new Exception("����������ӷ�������");
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        // ��ȡ��������
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con
                    .getInputStream(), "UTF-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
            }
        } catch (Exception e) {
            throw new Exception("�ӷ�������ȡ����ʧ��");
        }
        return buffer.toString();
    }

    public static String doPost(Map reqMap, Map signMap) {

        String response = "";
        List keys = new ArrayList(signMap.keySet());
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod();
        method.setRequestHeader("Content-type", "text/xml; charset=gbk");
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

//    public static String doPost(String url,Map<String,String> map, String charset) throws UnsupportedEncodingException {
//        HttpClient httpClient = null;
//        HttpPost httpPost = null;
//        String result = null;
//        try {
//            httpClient = new SSLClient();
//            httpPost = new HttpPost(url);
//            //���ò���
//            List<NameValuePair> list = new ArrayList<org.apache.http.NameValuePair>();
//            Iterator iterator = map.entrySet().iterator();
//            while(iterator.hasNext()){
//                Entry<String,String> elem = (Entry<String, String>) iterator.next();
//                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
//            }
//            if(list.size() > 0){
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
//                httpPost.setEntity(entity);
//            }
//            HttpResponse response = httpClient.execute(httpPost);
//            if(response != null){
//                HttpEntity resEntity = response.getEntity();
//                if(resEntity != null){
//                    result = EntityUtils.toString(resEntity,charset);
//                }
//            }
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }
//        System.out.println("result:" + result);
//        return result;
//    }

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

    /**
     * ƴװhtml
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
                sbHtml.append("<input type=\"hidden\" name=\"").append(name).append("\" value=\"" + value + "\"/>");
            }
        }
        sbHtml.append("<input type=\"hidden\" name=\"sign\" value=\"").append(sign).append("\"/>");
        sbHtml.append("<input type=\"hidden\" name=\"sign_type\" value=\"").append(reqMap.get("sign_type")).append("\"/>");
        //submit��ť�ؼ��벻Ҫ����name����
        sbHtml.append(new String("<input type=\"submit\" value=\"ȷ��֧��\"></form>"));
//        sbHtml.append(new String("<input type=\"submit\" value=\"ȷ��֧��\"></form>".getBytes("gbk"),"utf-8"));
//        sbHtml.append("<input type=\"submit\" value=\"ȷ��֧��\"></form>");

        System.out.println("sbHtml = " + sbHtml);
        return sbHtml;
    }

    public static String sendGet(String url, Map<String, String> parameters) {
        String result = "";
        BufferedReader in = null;// ��ȡ��Ӧ������
        StringBuffer sb = new StringBuffer();// �洢����
        HttpURLConnection connection = null;
        String params;// ����֮��Ĳ���
        try {
            // �����������
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "GBK"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "GBK")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            String full_url = url + "?" + params;
            System.out.println("full_url = " + full_url);
            URL realUrl = new URL(full_url);
            // �򿪺�URL֮�������
            connection = (HttpURLConnection) realUrl.openConnection();
            //��������false��������Զ�redirect���ض����ĵ�ַ
//            connection.setInstanceFollowRedirects(false);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����ʵ�ʵ�����
            connection.connect();

            //�ж��Ƿ�����302�ض���
//            if (connection.getResponseCode() == 302) {
//                //������ض��򣬱���302�ض����ַ���Լ�Cookies,Ȼ�����·�������(ģ������)
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
//                System.out.println("��ת��ַ:" + location);
//            }

            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = connection.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // ���� BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("����GET��������쳣!");
            e.printStackTrace();
        }
        // ʹ��finally�����ر�������
        finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String sendPostStr(String url, String sendString) throws Exception {

        URL u;
        HttpURLConnection con = null;

        // �����������
        StringBuffer sb = new StringBuffer();
        System.out.println("Request Url:" + url);
        System.out.println("Request msg:" + sendString);
        // ���Է�������
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            OutputStreamWriter osw = new OutputStreamWriter(con
                    .getOutputStream(), "UTF-8");
            osw.write(sendString);
            osw.flush();
            osw.close();
        } catch (Exception e) {
            throw new Exception("����������ӷ�������");
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        // ��ȡ��������
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con
                    .getInputStream(), "UTF-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
            }
        } catch (Exception e) {
            throw new Exception("�ӷ�������ȡ����ʧ��");
        }
        return buffer.toString();
    }

    public static Map parseHtml2Map(String html) {
        Map map = new HashMap();

        String[] array0 = html.split("method=\"post\">\n");
        String[] array1 = array0[1].split("</form>");
        String[] array2 = array1[0].split("<input type=hidden ");
        for (int i = 1; i < array2.length; i++) {
            String[] temp = array2[i].replace("/>","").replace("\"","").split(" value=");
            String key = temp[0].replace("name=","").trim();
            String value = temp[1].trim();
            map.put(key, value);
        }
        //System.out.println("JSON : " + JSONArray.toJSON(map));
        return map;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String key = "87370168370964458408069453373324";
        TreeMap requestMap = new TreeMap();
        requestMap.put("encoding", "UTF-8");
        requestMap.put("signMethod", "02");
        requestMap.put("txnType", "01");
        requestMap.put("txnSubType", "010130");
        requestMap.put("channelType", "6002");
        requestMap.put("payAccessType", "02");
        requestMap.put("backEndUrl", "http://114.215.242.9:18170/PayRec?idx=10101");
        requestMap.put("merId", "996600008000060");
        requestMap.put("secMerId", "");
        requestMap.put("termId", "WEB");
        requestMap.put("termIp", "116.228.54.118");
        requestMap.put("orderId", "911704100012584912");
        requestMap.put("orderTime", "20170410181308");
        requestMap.put("productId", "test");
        requestMap.put("orderBody", "orderBody");
        requestMap.put("orderDetail", "orderDetail");
        requestMap.put("orderGoodsTag", "orderGoodsTag");
        requestMap.put("txnAmt", "5");
        requestMap.put("currencyType", "156");
        requestMap.put("accountFlag", "");
        requestMap.put("secMerFeeRate", "");
        requestMap.put("attach", "");
        requestMap.put("limitPay", "");
        requestMap.put("needBankType", "");

        String signMethod = null;
        try {
            signMethod = MD5Utils.md5Sign(requestMap, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        requestMap.put("signMethod", signMethod);

        String requestBody = URLEncoder.encode(Base64.encodeBase64String(
                JSONObject.toJSONString(requestMap).toString().getBytes("UTF-8"))
                .replace("+", "#"), "UTF-8");
        requestMap.clear();
        requestMap.put("sendData", requestBody);
        String respone = "";
        try {
            respone = sendPost("http://localhost:8888/CITICBank", requestMap);

            System.out.println("respone = " + respone.split("=")[1].replace("+", "#"));



            String receiveJson = new String(Base64.decodeBase64(respone.split("=")[1].replace("+", "#")));
            System.out.println("receiveJson = " + receiveJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
