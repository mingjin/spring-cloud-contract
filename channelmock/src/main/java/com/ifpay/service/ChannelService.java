package com.ifpay.service;

import com.ifpay.common.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.TreeMap;

/**
 * Created by harvey.xu on 2017/4/18.
 */
public class ChannelService {

    private static Logger logger = LogManager.getLogger(ChannelService.class.getName());

    public static TreeMap buildChannelMap(TreeMap receiveMap, TreeMap signMap) {

        signMap.put("encoding", receiveMap.get("encoding"));
        signMap.put("signMethod", "02");
        signMap.put("txnType", receiveMap.get("txnType"));
        signMap.put("txnSubType", receiveMap.get("txnSubType"));
        signMap.put("channelType", receiveMap.get("channelType"));
        signMap.put("merId", receiveMap.get("merId"));
        signMap.put("termId", receiveMap.get("termId"));
        signMap.put("orderId", receiveMap.get("orderId"));
        signMap.put("orderTime", receiveMap.get("orderTime"));
        signMap.put("orderBody", receiveMap.get("orderBody"));
        signMap.put("txnAmt", receiveMap.get("txnAmt"));
        signMap.put("currencyType", receiveMap.get("currencyType"));
//        signMap.put("txnTime", signMap.get("txnTime"));
//        signMap.put("respCode", signMap.get("respCode"));
//        signMap.put("respMsg", signMap.get("respMsg"));

        if (StringUtils.isNotEmpty(receiveMap.get("txnSubType").toString())) {
            if (receiveMap.get("txnSubType").equals("010130")) {
                signMap.remove("seqId");
                signMap.put("payAccessType", receiveMap.get("payAccessType"));
                signMap.put("txnSeqId", signMap.get("txnSeqId"));
                signMap.put("prepayId", signMap.get("prepayId"));
                signMap.put("codeUrl", "weixin://wxpay/bizpayurl?pr=" + RandomUtil.getCharAndNumr(7));
            } else if (receiveMap.get("txnSubType").equals("010302")) {
                signMap.remove("prepayId");
                signMap.remove("txnSeqId");
                signMap.put("backEndUrl", receiveMap.get("backEndUrl"));
                signMap.put("termIp", receiveMap.get("termIp"));
                signMap.put("orderDetail", receiveMap.get("orderDetail"));
                signMap.put("accountFlag", "N");
                signMap.put("seqId", signMap.get("seqId"));
                signMap.put("codeUrl", "https://qr.alipay.com/bax" + RandomUtil.getNumber(5) + RandomUtil.getCharAndNumr(16));
            }
        } else {
            logger.error("txnSubType is null!");
        }
        return signMap;
    }
}
