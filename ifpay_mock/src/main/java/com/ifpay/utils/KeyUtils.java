package com.ifpay.utils;

import org.apache.commons.lang.StringUtils;

/**
 * Created by harvey.xu on 2017/3/19.
 */
public class KeyUtils {

    /**
     * 如果request请求中的key非空，则使用request中的key
     * @param inKey  key
     * @param channel  渠道
     * @return
     */
    public String getKey(String inKey, String channel) {
        String key = "";
        if (StringUtils.isNotEmpty(inKey)) {
            key = inKey;
        } else {
//            String keyFlag;
            if (StringUtils.isNotEmpty(channel)) {
                if (channel.contains("100000000002262")) {
                    key = "8ccd54184823a77g6217dgf6dge3493ad0g7d0fce7d6b7b59ad18bf8e7ce301g";
//                    keyFlag = "agentpay";
//                    key = new ProUtils().getKeyFromProp(keyFlag);
                }
                if (channel.contains("100000000002004")) {
                    key = "31bad8ec6c7gge989116e6d0e0ae4b3c8dgec3c795edcga0283b7b8eb681e82f";
                }
                if (channel.contains("100000000002084")) {
                    key = "cg99709609c51gbd71f4ef67d432cbd4248f964bea1443b777e97296d7ac64ad";
                }
                if (channel.contains("100000000002224")) {
                    key = "ea7e29d050ce08289e4671062bgaf800g397aeggg313gb8f47ga26c44caff37e";
                }
                if (channel.contains("100000000002285")) {
                    key = "de4011e96d9cc7c4b7bc2dbca60e0bf18ee80e316224ac2ba759c4g65c012d58";
                }
            }
        }
        return key;
    }

}
