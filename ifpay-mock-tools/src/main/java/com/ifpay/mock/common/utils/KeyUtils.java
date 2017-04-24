package com.ifpay.mock.common.utils;

import com.alibaba.druid.util.StringUtils;
import org.apache.logging.log4j.LogManager;

/**
 * Created by harvey.xu on 2017/4/11.
 */
public class KeyUtils {

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(KeyUtils.class.getName());

    /**
     * @param merId  渠道
     * @return
     */
    public static String getKey(String merId) {
        String key = "";
        if (!StringUtils.isEmpty(merId)) {
            if (merId.equals("996600008000060")) {
                key = "87370168370964458408069453373324";
            }
            if (merId.equals("996600008000061")) {
                key = "88912588894196800940732731517512";
            }
            if (merId.equals("996600009000068")) {
                key = "83694572786487019829744362696663";
            }
            if (merId.equals("")) {
                key = "";
            }
            if (merId.equals("")) {
                key = "";
            }
        } else {
            logger.error("merId is null!");
        }
        return key;
    }

}
