<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.w3c.dom.Document" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
<%@ page import="com.ifpay.utils.*" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="java.math.BigDecimal" %>
<%--
  Created by IntelliJ IDEA.
  User: harvey.xu
  Date: 2017/3/19
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>

<%
    request.setCharacterEncoding("UTF-8");

    String[] payeeNameArray = {"孙亮","许建峰","刘融","李红","孙良","许剑锋","刘小融","李虹","范伟杰","范伟婕",
                               "孙亮亮","许建枫","刘融","李红","孙良","许剑锋","刘小融","李虹","范伟","范伟婕"};
    String[] payeeAcctNoArray = {"6222021001036221014","6214850210332645","6217001210063580944","6228480392725997118","6222021001036221015",
                                 "6214850210332646","6217001210063580945","6228480392725997119","9558801001173521508","9558801001173521506",
                                 "6222021001036221015","6214850210332646","6217001210063580944","6228480392725997118","6222021001036221015",
                                 "6214850210332646","6217001210063580945","6228480392725997119","9558801001173521509","9558801001173521506"};
    String[] payeeAcctBankNameArray = {"中国工商银行","招商银行","中国建设银行","中国农业银行","中国工商银行",
                                       "招商银行","中国建设银行","中国农业银行","中国工商银行","中国工商银行",
                                       "中国工商银行","招商银行","中国建设银行","中国农业银行","中国工商银行",
                                       "招商银行","中国建设银行","中国农业银行","中国工商银行","中国工商银行"};
    String[] remarkArray = {"卡号错","卡号错","卡号错","卡号错","用户名错","用户名错","用户名错","用户名错","卡号错","用户名错",
                            "用户名错","用户名错","卡号错","卡号错","用户名错","用户名错","用户名错","用户名错","用户名错","用户名错"};
    String[] payeeNameArraySucc = {"孙亮","许建峰","刘融","范伟杰","范紫燕","范紫燕","范紫燕","孙亮","许建峰","刘融","范伟杰","范紫燕"};
    String[] payeeAcctNoArraySucc = {"6222021001036221015","6214850210332646","6217001210063580945","6228480392725997119","6222021001023980565","4367421217034281535",
                            "6214832143652696","6222021001036221015","6214850210332646","6217001210063580945","6228480392725997119","6222021001023980565"};
    String[] payeeAcctBankNameArraySucc = {"中国工商银行","招商银行","中国建设银行","中国农业银行","中国工商银行","中国建设银行","招商银行",
                            "中国工商银行","招商银行","中国建设银行","中国农业银行","中国工商银行"};
    String[] remarkArraySucc = {"成功","成功","成功","成功","成功","成功","成功","成功","成功","成功"};
    String key = new KeyUtils().getKey(request.getParameter("key"), request.getParameter("batchBizid"));
    for (int i = 0; i < Integer.valueOf(request.getParameter("batchCount")); i++) {
        String amount;
        String batchCurrnum;
        String orderId;
        String payeeAcctNo;
        String payeeName;
        String payeeAcctBankName;
        String remark;
        if (Integer.valueOf(request.getParameter("batchCount")) > 1 ) {
            int num = RandomUtils.random(1);
            if (i%10==0) {
                System.out.println("i = " + i);
                payeeName = payeeNameArraySucc[num];
                payeeAcctNo = payeeAcctNoArraySucc[num];
                payeeAcctBankName = payeeAcctBankNameArraySucc[num];
                amount = "0.02";
                remark = remarkArraySucc[num];
            } else {
                payeeName = payeeNameArray[num];
                payeeAcctNo = payeeAcctNoArray[num];
                payeeAcctBankName = payeeAcctBankNameArray[num];
//            amount = (new DecimalFormat("0.01").format((float)i/100)).toString();
                amount = "0.01";
                remark = remarkArray[num];
            }
        } else {
            payeeAcctNo = request.getParameter("payeeAcctNo");
            payeeName = request.getParameter("payeeName");
            payeeAcctBankName = request.getParameter("payeeAcctBankName");
            amount = new BigDecimal(request.getParameter("amount")).toString();
            remark = request.getParameter("remark");
        }

        batchCurrnum = request.getParameter("batchCurrnum") + String.valueOf(i);
        orderId = request.getParameter("orderId") + String.valueOf(i);

        //多笔时每笔之间以|来区分:序号,银行账户,开户名,开户行,分行,支行,公/私,金额,币种,省,市,手机号,证件类型,证件号,用户协议号,商户订单号,备注
        String batchContent = (i + 1) + "," + payeeAcctNo + "," + payeeName
                + "," + payeeAcctBankName + "," + request.getParameter("bankBranch")
                + "," + request.getParameter("bankSubbranch") + "," + request.getParameter("payeeAcctType")
                + "," + amount + "," + request.getParameter("currency")
                + "," + request.getParameter("province") + "," + request.getParameter("city")
                + "," + request.getParameter("mobile") + "," + request.getParameter("payeeIdType")
                + "," + request.getParameter("payeeIdNo") + "," + request.getParameter("protocolNumber")
                + "," + orderId + "," + remark;
        out.println("第" + (i + 1) + "笔，加签前的字符串：" + batchContent);

//        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\key\\";
//        String path = "/home/wuser/production/apps/ifpaymock/webapps/ROOT/WEB-INF/key/";// 服务器地址
        String path = "D:\\workspace\\ifpay_mock\\src\\main\\webapp\\WEB-INF\\key\\";// TODO修改为自己的地址
        String items = TextUtils.jm(batchContent, path);

        Map sPara = new HashMap();
        sPara.put("batchBizid", ArrayUtils.getSplitStr(request.getParameter("batchBizid")));
        sPara.put("batchVersion", request.getParameter("batchVersion"));
        sPara.put("batchBiztype", request.getParameter("batchBiztype"));
        sPara.put("batchDate", request.getParameter("batchDate"));
        sPara.put("batchCurrnum", batchCurrnum);
        sPara.put("batchCount", request.getParameter("batchCount"));
        sPara.put("batchAmount", request.getParameter("batchAmount"));
        sPara.put("batchContent", items);
        sPara.put("_input_charset", request.getParameter("inputCharset"));
        Map sParaNew = ArrayUtils.mapFilter(sPara); // 除去数组中的空值和签名参数
        String sign = TextUtils.buildMysign(sParaNew, key);// 生成签名结果
        sPara.put("sign", sign);
        sPara.put("sign_type", request.getParameter("signType"));
        String responseString = HttpRequest.httpReq(request.getParameter("requestUrl"), sPara);

        out.println("<br>");
        out.println("第" + (i + 1) + "笔，加签后的字符串：" + sign);
        out.println("<br>");
        out.println("第" + (i + 1) + "笔，请求批次号：" + batchCurrnum);
        out.println("<br>");
        out.println("第" + (i + 1) + "笔，商户订单号：" + orderId);
        out.println("<br>");
        out.println("第" + (i + 1) + "笔，交易金额：" + amount);
        out.println("<br>");

        if(StringUtils.isNotEmpty(responseString)) {
            Document doc = XmlUtils.getDocument(responseString);
            String status = XmlUtils.getNodeValue(doc, "status");
            if("fail".equals(status)) {
                String reason = XmlUtils.getNodeValue(doc, "reason");
                System.out.println("-------------" + StringEscapeUtils.unescapeHtml3(reason));
            }
            out.println("—————————————————————————————————————————————————————————————————————");
            out.println("<br>");
            out.println("succ".equals(status) ? "第" + (i + 1) + "笔，成功了！" : "第" + (i + 1) + "笔，失败了！");
            out.println("<br>");
            out.println("第" + (i + 1) + "笔，服务端返回：" + responseString + "<br>");
        } else {
            out.println("第" + (i + 1) + "笔，请求失败，请检查系统是否正常！");
            out.print("<br>");
            out.print("第" + (i + 1) + "笔，请求的地址为：" + request.getParameter("requestUrl"));
            out.print("<br>");
        }
        out.println("*****************************************************************************************************************************************************************************************");
        out.println("<br>");
    }
%>
<html>
<head>
    <title>提交单笔代付订单</title>
</head>
</html>
