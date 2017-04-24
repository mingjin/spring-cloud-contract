package com.ifpay.mock.servlet;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.ifpay.mock.common.utils.HttpRequest;
import com.ifpay.mock.common.utils.JsonUtil;
import com.ifpay.mock.common.utils.KeyUtils;
import com.ifpay.mock.common.utils.MD5Utils;
import com.ifpay.mock.service.ChannelService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CITICBankAll extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(CITICBankAll.class.getName());

    private static ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
    static {
        poolTaskExecutor.setQueueCapacity(100000);
        poolTaskExecutor.setCorePoolSize(5);
        poolTaskExecutor.setMaxPoolSize(20);
        poolTaskExecutor.setKeepAliveSeconds(5000);
        poolTaskExecutor.initialize();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        long startReturnCodeUrl = System.currentTimeMillis();
        logger.info("----------CITICBank return codeUrl Start:" + startReturnCodeUrl + "------------");

        String receiveJson = new String(Base64.decodeBase64(request.getParameter("sendData").replace("#", "+")));
        logger.info("receiveJson = " + receiveJson);

        TreeMap receiveMap = JsonUtil.jsonStrToMap(receiveJson);
        receiveMap.remove("signAture");

        // 获取子商户密钥
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

        final Map finalMap = signMap;
        finalMap.put("key", key);
        finalMap.put("backEndUrl", receiveMap.get("backEndUrl"));
        /* 多线程处理 */
        FutureTask<Boolean> futureTask = new FutureTask<Boolean>(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                /* notify back to payment*/
                long startAsynNotify = System.currentTimeMillis();
                logger.info("----------CITICBank asyn notify back to payment Start:" + startAsynNotify + " orderId: " + finalMap.get("orderId") + "------------");
                try {
                    // 增加等待时间，先返码，后通知
                    Thread.currentThread().sleep(300);

                    Map notifySignMap = new ConcurrentHashMap();
                    notifySignMap = ChannelService.buildAsynNotifyMap(finalMap, notifySignMap);

                    String signAtureNotify = null;
                    try {
                        signAtureNotify = MD5Utils.md5Sign(notifySignMap, finalMap.get("key").toString());
                    } catch (Exception e) {
                        logger.info("========md5util===========ex");
                        e.printStackTrace();
                    }
                    notifySignMap.put("signAture", signAtureNotify.toUpperCase());

                    String notifyJson = JSONObject.toJSONString(notifySignMap);
                    logger.info("notifyJson = " + notifyJson);
                    String notifyRequest = "sendData=" + URLEncoder.encode(Base64.encodeBase64String(notifyJson.getBytes()));

                    // 请求payment
                    String paymentResponse = HttpRequest.sendPost(finalMap.get("backEndUrl").toString(), notifyRequest);
                    if (!StringUtils.isEmpty(paymentResponse)) {
                        String paymentResponseJson;
                        if (!paymentResponse.startsWith("sendData=")) {
                            paymentResponseJson = paymentResponse;
                            logger.info("paymentResponse is not start with sendData=");
                        } else {
                            paymentResponseJson = new String(Base64.decodeBase64(paymentResponse.replace("sendData=", "").replace("#", "+")), "utf-8");
                        }

                        if (paymentResponseJson.contains("\"respCode\":\"0000\"")) {
                            logger.info(">>>>>>>>>异步通知payment成功!transactionId = " + notifySignMap.get("transactionId"));
                        } else {
                            logger.error(">>>>>>>>>异步通知payment失败!paymentResponseJson = " + paymentResponseJson);
                        }
                    } else {
                        logger.error("请求失败，请检查系统是否正常!请求地址为： " + finalMap.get("backEndUrl"));
                    }
                } catch (Exception e) {
                    logger.info("===========all========ex");
                    e.printStackTrace();
                }
                logger.info("----------CITICBank return notify back to payment End:" + (System.currentTimeMillis() - startAsynNotify) + "ms------------");
                /* notify back to payment*/
                return true;
            }
        });
        poolTaskExecutor.submit(futureTask);

        logger.info("----------CITICBank return codeUrl End:" + (System.currentTimeMillis() - startReturnCodeUrl) + "ms " + receiveMap.get("orderId") + " ------------");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        logger.info("Hello to get CITIC Bank All Mock!");
        PrintWriter out = response.getWriter();
        out.println("Hello to get CITIC Bank All Mock!");
    }
}
