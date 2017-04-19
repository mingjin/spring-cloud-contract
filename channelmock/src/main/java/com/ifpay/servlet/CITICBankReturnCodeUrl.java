package com.ifpay.servlet;

import com.alibaba.fastjson.JSONObject;
import com.ifpay.common.utils.*;
import com.ifpay.service.ChannelService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

/**
 * Created by harvey.xu on 2017/4/10.
 */
@WebServlet(name = "CITICBankServlet")
public class CITICBankReturnCodeUrl extends HttpServlet {

    private static Logger logger = LogManager.getLogger(CITICBankReturnCodeUrl.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        long startReturnCodeUrl = System.currentTimeMillis();
        logger.info("----------CITICBank return codeUrl Start:" + startReturnCodeUrl + "------------");

        String receiveJson = new String(Base64.decodeBase64(request.getParameter("sendData").replace("#", "+")));
        logger.info("receiveJson = " + receiveJson);

        // 解析Json->Map
        TreeMap receiveMap = JsonUtil.jsonStrToMap(receiveJson);
        receiveMap.remove("signAture");

        String key = KeyUtils.getKey(receiveMap.get("merId").toString());
        String respCode = "0000";
        String respMsg = "交易成功";

        // 如果金额等于99.99返回状态为失败
        if (receiveMap.get("txnAmt").equals("9999")) {
            respCode = "1001";
            respMsg = "交易出现异常。详情请咨询";
        }

        // 根据不同渠道拼装返码Map
        Map signMap = new ConcurrentHashMap();
        signMap.put("respCode", respCode);
        signMap.put("respMsg", respMsg);
        signMap = ChannelService.buildReCodeUrlMap(receiveMap, signMap);

        String signAture = null;
        try {
            signAture = MD5Utils.md5Sign(signMap, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        signMap.put("signAture", signAture.toUpperCase());

        String responseJson = JSONObject.toJSONString(signMap);
        logger.info("responseJson = " + responseJson);
        String responseStr = "sendData=" + URLEncoder.encode(Base64.encodeBase64String(responseJson.getBytes()));
        PrintWriter out = response.getWriter();
        out.write(responseStr);

        logger.info("----------CITICBank return codeUrl End:" + (System.currentTimeMillis() - startReturnCodeUrl) + "ms " + receiveMap.get("orderId") + " ------------");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        logger.info("Hello to get CITICBank return codeUrl Mock!");
        PrintWriter out = response.getWriter();
        out.println("Hello to get CITICBank return codeUrl Mock!");
    }
}
