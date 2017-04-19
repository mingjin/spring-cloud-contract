//package com.ifpay.orm;
//
//import com.ifpay.common.constant.ResultCodeEnum;
//import com.ifpay.common.constant.ResultMsgEnum;
//import com.ifpay.common.utils.DateUtil;
//import com.ifpay.common.utils.RandomUtil;
//import net.sf.json.JSONObject;
//
//import java.sql.*;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.TreeMap;
//
///**
// * Created by harvey.xu on 2016/3/2.
// */
//public class DataBaseHandler {
//
//    private static Connection con = null;
//    private static PreparedStatement ps = null;
//    private static ResultSet rs;
//
//    public static Map databaseOperation(Map reqMap) throws Exception {
//
//        Map<String, Object> respMap = new HashMap();
//
//        String sql;
//        Map<String, String> sqlMap = new HashMap();
//        TreeMap respHeadMap = new TreeMap();
//
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//
//        // 选择数据源
//        Map dbSourcesMap = selectDbSources(String.valueOf(reqMap.get("stage")));
//
//        try {
//            // 数据连接
//            con = DriverManager.getConnection(String.valueOf(dbSourcesMap.get("url")), String.valueOf(dbSourcesMap
//                    .get("username")), String.valueOf(dbSourcesMap.get("userpass")));
//            System.out.println("***************数据库连接成功***************");
//
//            // 组装sql语句
//            int num = Integer.parseInt(String.valueOf(reqMap.get("caseNo")));
//            switch (num) {
//                case 0:
//                    // 查快捷绑卡信息
//                    sql = "SELECT * FROM PWMTQPAG where usr_no = (SELECT usr_no FROM PAYADM.VPAL_VCP_USER where " +
//                            "user_id ='" + reqMap.get("userId") + "')";
//                    respMap = excuteQuery(con, respMap, sql);
//                    break;
//                case 1:
//                    // 删信购开户数据，保留实名信息
//                    sqlMap.put("delPWMTQPAGSql", "DELETE FROM PWMTQPAG WHERE usr_no = (SELECT usr_no FROM PAYADM" +
//                            ".VPAL_VCP_USER WHERE user_id ='" + reqMap.get("userId") + "')");
//                    sqlMap.put("delVPALVCPUSERSql", "DELETE FROM PAYADM.VPAL_VCP_USER WHERE user_id ='" + reqMap.get
//                            ("userId") + "'");
//                    sqlMap.put("updateURMTPINFSql", "UPDATE URMTPINF SET REAL_NM_FLG = '01', CUS_NM=' ', ID_NO=' ' " +
//                            "WHERE ifpay_usr ='" + reqMap.get("userId") + "'");
//                    respMap = excuteUpdate(con, respMap, sqlMap);
//                    break;
//                case 2:
//                    // 改账户状态（预授信用户）
//                    sqlMap.put("updateVCPUSERSql", "UPDATE PAYADM.VPAL_VCP_USER set USER_STATUS='10' where user_id='"
//                            + reqMap.get("userId") + "'");
//                    respMap = excuteUpdate(con, respMap, sqlMap);
//
//                    break;
//                case 3:
//                    // 查账户在支付平台的状态（是否开通子账户）
//                    sql = "select User_Id,user_status,acc_type,credit_amount from PAYADM.VPAL_VCP_USER  where " +
//                            "user_id='" + reqMap.get("userId") + "'";
//                    respMap = excuteQuery(con, respMap, sql);
//                    break;
//                case 4:
//                    // 同步用户到支付平台
//                    sqlMap.put("adduserSql", "insert into URMTPINF (USR_NO, USR_PROV, USR_CITY, USR_CITY_NM, USR_STS," +
//                            " REAL_NM_FLG, REAL_NM_ATH_MOD, USR_STSW, PSN_CRP_FLG, REG_TYP, REG_MBL, REG_EMAIL, " +
//                            "CRED_LVL, CUS_NM, ID_TYP, ID_NO, CI_NO, USR_GRP, REG_DT, REG_TM, REG_AGR_VER, " +
//                            "REG_SYS_CNL, RCMD_NM, OLD_MBL_NO, WAIT_CLO_DT, CLO_DT, CUS_MGR, CTT_MBL, EMAIL, MAIN_AC," +
//                            " UPD_JRN, BOSS_STSW, BOSS_CUS_LVL, USR_BRD_CD, USR_IMSI, CUS_BOSS_NM, TM_SMP, " +
//                            "REG_BUS_CNL, NEW_REAL_NM_FLG, ifpay_USR, SALT, BIND_MBL_NO, BIND_EMAIL, USR_OPR_LOG_SHOW, " +
//                            "HEAD_URL, CHANNEL, REF_USR_NO)" +
//                            "values (" + RandomUtil.getNumber(12) + ", ' ', ' ', ' ', '0', '01', '1', " +
//                            "'000000000000000', '0', '1', '13544441111', ' ', ' ', ' ', '00', ' ', '0', ' ', " +
//                            "'20150713', '131010', ' ', 'IPS', ' ', '13544441111', ' ', ' ', ' ', '0', ' ', " +
//                            "'1000000001674049231', '201507033776763089', '000000000000000', ' ', ' ', ' ', ' ', " +
//                            "'20160223141048', 'WWW', 'U', '" + reqMap.get("userId") + "', '" + RandomUtil
//                            .getCharAndNumr(6) + "', '13544441111', '0', null, null, 'ifpay', null)");
//                    respMap = excuteUpdate(con, respMap, sqlMap);
//                    break;
//                case 5:
//                    // 实名信息修改
//                    break;
//                case 6:
//                    // 绑定手机号修改
//                    break;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (Exception e) {
//                respHeadMap.put("resultCode", ResultCodeEnum.ERROR);
//                respHeadMap.put("resultMsg", ResultMsgEnum.SYSTEM_ERROR);
//                respMap.put("responseHeader", JSONObject.fromObject(respHeadMap));
//                e.printStackTrace();
//            }
//            try {
//                if (ps != null) {
//                    ps.close();
//                }
//            } catch (Exception e) {
//                respHeadMap.put("resultCode", ResultCodeEnum.ERROR);
//                respHeadMap.put("resultMsg", ResultMsgEnum.SYSTEM_ERROR);
//                respMap.put("responseHeader", JSONObject.fromObject(respHeadMap));
//                e.printStackTrace();
//            }
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (Exception e) {
//                respHeadMap.put("resultCode", ResultCodeEnum.ERROR);
//                respHeadMap.put("resultMsg", ResultMsgEnum.SYSTEM_ERROR);
//                respMap.put("responseHeader", JSONObject.fromObject(respHeadMap));
//                e.printStackTrace();
//            }
//            System.out.println("***************数据库连接关闭***************");
//        }
//        return respMap;
//    }
//
//    /**
//     * 选择数据源
//     *
//     * @param stage
//     * @return
//     */
//    public static Map<String, String> selectDbSources(String stage) {
//        Map<String, String> map = new HashMap<String, String>();
//        if (stage.equals("Staging")) {
//            map.put("url", "jdbc:oracle:thin:@10.199.161.152:1521:orapay");
//            map.put("username", "mydba");
//            map.put("userpass", "ifpayshop2020dba");
//        }
//        if (stage.equals("Funcation")) {
//            map.put("url", "jdbc:oracle:thin:@10.199.48.27:1521:orapay");
//            map.put("username", "mydba");
//            map.put("userpass", "ifpayshopdba2015");
//        }
//        return map;
//    }
//
//    /**
//     * 执行查询 查询
//     *
//     * @param con
//     * @param respMap
//     * @param sql
//     * @return
//     * @throws SQLException
//     */
//    public static Map<String, Object> excuteQuery(Connection con, Map respMap, String sql) throws SQLException {
//
//        TreeMap respHeadMap = new TreeMap();
//        StringBuffer sb = new StringBuffer();
//
//        ps = con.prepareStatement(sql);
//        System.out.println("++++++++++++++开始执行查询[" + DateUtil.getTime() + "]++++++++++++++");
//        System.out.println("+ 当前执行SQL： " + sql);
//        rs = ps.executeQuery();
//
//        ResultSetMetaData rsm = rs.getMetaData();
//
//        int j = 1;
//        while (rs.next()) {
//            Map queryMap = new HashMap();
//            for (int i = 0; i < rsm.getColumnCount(); ++i) {
//                queryMap.put(rsm.getColumnName(i + 1), rs.getString(i + 1));
////					System.out.println(rsm.getColumnName(i + 1) + " = " + rs.getString(i + 1));
//            }
//            sb.append("(" + j++ + ")");
//            sb.append("[");
//            sb.append(JSONObject.fromObject(queryMap));
//            sb.append("]");
//        }
//        if (sb.length() > 0) {
//            System.out.println("+ 执行结果：成功");
//        } else {
//            System.out.println("+ 执行结果：成功-无数据");
//            sb.append("查询结果为空");
//        }
//
//        System.out.println("++++++++++++++结束执行查询[" + DateUtil.getTime() + "]++++++++++++++");
//
//        respMap.put("executeResult", String.valueOf(sb));
//        respHeadMap.put("resultCode", ResultCodeEnum.SUCCESS);
//        respHeadMap.put("resultMsg", ResultMsgEnum.SUCCESS);
//
//        respMap.put("responseHeader", JSONObject.fromObject(respHeadMap));
//        return respMap;
//    }
//
//    /**
//     * 执行delete update insert
//     *
//     * @param con
//     * @param respMap
//     * @param sqlMap
//     * @return
//     */
//    public static Map excuteUpdate(Connection con, Map respMap, Map<String, String> sqlMap) {
//
//        TreeMap respHeadMap = new TreeMap();
//        StringBuffer sb = new StringBuffer();
//
//        int executeTotal = 0;
//        for (Map.Entry<String, String> entry : sqlMap.entrySet()) {
//
//
//            int count;
//            try {
//                ps = con.prepareStatement(entry.getValue());
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                System.out.println("++++++++++++++开始执行[" + DateUtil.getTime() + "]++++++++++++++");
//                System.out.println("+ 当前执行SQL： " + entry.getValue());
//                count = ps.executeUpdate(entry.getValue());
//                if (count > 0) {
//                    System.out.println("+ 执行结果：成功");
//                    executeTotal++;
//                } else {
//                    System.out.println("+ 执行结果：失败");
//                }
//                System.out.println("++++++++++++++结束执行[" + DateUtil.getTime() + "]++++++++++++++");
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if (sqlMap.size() == executeTotal) {
//            respHeadMap.put("resultCode", ResultCodeEnum.SUCCESS);
//            respHeadMap.put("resultMsg", ResultMsgEnum.SUCCESS);
//            respMap.put("executeResult", "执行成功");
//        } else {
//            respHeadMap.put("resultCode", ResultCodeEnum.FAILURE);
//            respHeadMap.put("resultMsg", ResultMsgEnum.DB_ERROR);
//            respMap.put("executeResult", "执行失败，请联系相关测试人员");
//        }
//
//        respMap.put("responseHeader", JSONObject.fromObject(respHeadMap));
//
//        return respMap;
//    }
//
//}