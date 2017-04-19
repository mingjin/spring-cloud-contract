package com.ifpay.common.utils;

import com.alibaba.fastjson.*;
import sun.reflect.generics.tree.Tree;

import java.text.ParseException;
import java.util.*;

/**
 * Created by harvey.xu on 2016/1/13.
 *
 */
public class JsonUtil {

//    /**
//     * 将json格式的字符串解析成Map对象 <li>
//     * json格式：{"name":"admin","retries":"3fff","testname"
//     * :"ddd","testretries":"fffffffff"}
//     */
//    private static HashMap<String, String> toHashMap(Object object)
//    {
//        HashMap<String, String> mapData = new HashMap<String, String>();
//        // 将json字符串转换成jsonObject
//        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(object);
//        Iterator it = jsonObject.keys();
//        // 遍历jsonObject数据，添加到Map对象
//        while (it.hasNext())
//        {
//            String key = String.valueOf(it.next());
//            String value = jsonObject.get(key).toString();
//
////            System.out.println("key:"+key + " | " + "value = " + value);
//            mapData.put(key, value);
//        }
//        return mapData;
//    }

    /**
     * 将json格式的字符串解析成String字符串平装<li>
     * json格式：String objString = "{\"aa\":1,\"cc\":2,\"bb\":3}";
     */
    private static String toAnnotationStr(Object object)
    {
        // HashMap<String, String> data = new HashMap<String, String>();
        // 将json字符串转换成jsonObject
        if (object != null && !object.equals(""))
        {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(object);
            Iterator it = (Iterator) jsonObject.keySet();
            StringBuilder strBuilder = new StringBuilder();
            // 遍历jsonObject数据，添加到Map对象
            while (it.hasNext())
            {
                String key = String.valueOf(it.next());
                String value = jsonObject.get(key).toString();
                strBuilder.append(key)
                        .append("lego.lab.com.colon")
                        .append(value + " ");

            }
            return strBuilder.toString();
        }
        else
        {
            return "";
        }
    }

//    /**
//     * 将json串转换为map；类型
//     * @param jsonStr
//     * @return map
//     */
//    public static TreeMap<String, Object> parseJSON2Map(String jsonStr){
//        TreeMap<String, Object> map = new TreeMap<String, Object>();
//        //最外层解析
//        JSONObject json = (JSONObject) JSONObject.toJSON(jsonStr);
//        for(Object k : json.keySet()){
//            Object v = json.get(k);
//            //如果内层还是数组的话，继续解析
//            if(v instanceof JSONArray){
//                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//                Iterator<Object> it = ((JSONArray)v).iterator();
//                while(it.hasNext()){
//                    JSONObject json2 = (JSONObject) it.next();
//                    list.add(json2);
//                }
//                map.put(k.toString(), list);
//            } else {
//                map.put(k.toString(), v);
//            }
//        }
//        return map;
//    }

    /**
     * 创建JSONObject对象
     * @return
     */
    private static JSONObject createJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ret", new Integer(0));
        jsonObject.put("msg", "query");
        JSONObject dataelem1=new JSONObject();
        //{"deviceid":"SH01H20130002","latitude":"32.140","longitude":"118.640","speed":"","orientation":""}
        dataelem1.put("deviceid", "SH01H20130002");
        dataelem1.put("latitude", "32.140");
        dataelem1.put("longitude", "118.640");

        JSONObject dataelem2=new JSONObject();
        //{"deviceid":"SH01H20130002","latitude":"32.140","longitude":"118.640","speed":"","orientation":""}
        dataelem2.put("deviceid", "SH01H20130002");
        dataelem2.put("latitude", "32.140");
        dataelem2.put("longitude", "118.640");

        // 返回一个JSONArray对象
        JSONArray jsonArray = new JSONArray();

        jsonArray.add(0, dataelem1);
        jsonArray.add(1, dataelem2);
        jsonObject.put("data", jsonArray);

        return jsonObject;
    }

    public static TreeMap<String, Object> jsonStrToMap(String jsonStr){

        TreeMap<String, Object> map = JSON.parseObject(jsonStr,new TypeReference<TreeMap<String, Object>>(){});

        return map;
    }

    /**
     *
     * @param args
     * @throws JSONException
     * @throws ParseException
     */
    public static void main(String[] args) throws JSONException, ParseException {

        String jsonString="{\"txnType\":\"01\",\"channelType\":\"6002\",\"merId\":\"996600008000060\",\"orderBody\":\"productName\",\"orderTime\":\"20170412152021\",\"currencyType\":\"156\",\"signAture\":\"EB672B6FFE36B2C4A01DEBCFB3F57B8A\",\"txnSubType\":\"010130\",\"txnAmt\":\"1\",\"termId\":\"WEB\",\"productId\":\"20170412152005374\",\"signMethod\":\"02\",\"backEndUrl\":\"http://114.215.242.9:18170/PayRec11?idx=10101\",\"orderDetail\":\"productDesc\",\"needBankType\":\"Y\",\"attach\":\"\",\"payAccessType\":\"02\",\"encoding\":\"UTF-8\",\"termIp\":\"116.228.54.118\",\"orderId\":\"911704120012611248\"}";

//        TreeMap<String, String> map = new TreeMap<String, String>();
//        map.put("version", "1.0");
//        map.put("terminalType", "pc");
//        map.put("signType", "md5");
//        map.put("sign", "53c0dc0e99e53f481a5dd54d792d6fb6");
//        JSONObject json = JSONObject.fromObject(map);

        TreeMap map = jsonStrToMap(jsonString);
        System.out.println(map.get("merId"));

//        String result = JsonHelper.post(jsonString);
//        System.out.println(result);
    }

}