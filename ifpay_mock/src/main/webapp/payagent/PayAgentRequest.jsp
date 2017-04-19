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

    String[] payeeNameArray = {"����","����","����","���","����","����","��С��","���","��ΰ��","��ΰ�",
                               "������","����","����","���","����","����","��С��","���","��ΰ","��ΰ�"};
    String[] payeeAcctNoArray = {"6222021001036221014","6214850210332645","6217001210063580944","6228480392725997118","6222021001036221015",
                                 "6214850210332646","6217001210063580945","6228480392725997119","9558801001173521508","9558801001173521506",
                                 "6222021001036221015","6214850210332646","6217001210063580944","6228480392725997118","6222021001036221015",
                                 "6214850210332646","6217001210063580945","6228480392725997119","9558801001173521509","9558801001173521506"};
    String[] payeeAcctBankNameArray = {"�й���������","��������","�й���������","�й�ũҵ����","�й���������",
                                       "��������","�й���������","�й�ũҵ����","�й���������","�й���������",
                                       "�й���������","��������","�й���������","�й�ũҵ����","�й���������",
                                       "��������","�й���������","�й�ũҵ����","�й���������","�й���������"};
    String[] remarkArray = {"���Ŵ�","���Ŵ�","���Ŵ�","���Ŵ�","�û�����","�û�����","�û�����","�û�����","���Ŵ�","�û�����",
                            "�û�����","�û�����","���Ŵ�","���Ŵ�","�û�����","�û�����","�û�����","�û�����","�û�����","�û�����"};
    String[] payeeNameArraySucc = {"����","����","����","��ΰ��","������","������","������","����","����","����","��ΰ��","������"};
    String[] payeeAcctNoArraySucc = {"6222021001036221015","6214850210332646","6217001210063580945","6228480392725997119","6222021001023980565","4367421217034281535",
                            "6214832143652696","6222021001036221015","6214850210332646","6217001210063580945","6228480392725997119","6222021001023980565"};
    String[] payeeAcctBankNameArraySucc = {"�й���������","��������","�й���������","�й�ũҵ����","�й���������","�й���������","��������",
                            "�й���������","��������","�й���������","�й�ũҵ����","�й���������"};
    String[] remarkArraySucc = {"�ɹ�","�ɹ�","�ɹ�","�ɹ�","�ɹ�","�ɹ�","�ɹ�","�ɹ�","�ɹ�","�ɹ�"};
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

        //���ʱÿ��֮����|������:���,�����˻�,������,������,����,֧��,��/˽,���,����,ʡ,��,�ֻ���,֤������,֤����,�û�Э���,�̻�������,��ע
        String batchContent = (i + 1) + "," + payeeAcctNo + "," + payeeName
                + "," + payeeAcctBankName + "," + request.getParameter("bankBranch")
                + "," + request.getParameter("bankSubbranch") + "," + request.getParameter("payeeAcctType")
                + "," + amount + "," + request.getParameter("currency")
                + "," + request.getParameter("province") + "," + request.getParameter("city")
                + "," + request.getParameter("mobile") + "," + request.getParameter("payeeIdType")
                + "," + request.getParameter("payeeIdNo") + "," + request.getParameter("protocolNumber")
                + "," + orderId + "," + remark;
        out.println("��" + (i + 1) + "�ʣ���ǩǰ���ַ�����" + batchContent);

//        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\key\\";
//        String path = "/home/wuser/production/apps/ifpaymock/webapps/ROOT/WEB-INF/key/";// ��������ַ
        String path = "D:\\workspace\\ifpay_mock\\src\\main\\webapp\\WEB-INF\\key\\";// TODO�޸�Ϊ�Լ��ĵ�ַ
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
        Map sParaNew = ArrayUtils.mapFilter(sPara); // ��ȥ�����еĿ�ֵ��ǩ������
        String sign = TextUtils.buildMysign(sParaNew, key);// ����ǩ�����
        sPara.put("sign", sign);
        sPara.put("sign_type", request.getParameter("signType"));
        String responseString = HttpRequest.httpReq(request.getParameter("requestUrl"), sPara);

        out.println("<br>");
        out.println("��" + (i + 1) + "�ʣ���ǩ����ַ�����" + sign);
        out.println("<br>");
        out.println("��" + (i + 1) + "�ʣ��������κţ�" + batchCurrnum);
        out.println("<br>");
        out.println("��" + (i + 1) + "�ʣ��̻������ţ�" + orderId);
        out.println("<br>");
        out.println("��" + (i + 1) + "�ʣ����׽�" + amount);
        out.println("<br>");

        if(StringUtils.isNotEmpty(responseString)) {
            Document doc = XmlUtils.getDocument(responseString);
            String status = XmlUtils.getNodeValue(doc, "status");
            if("fail".equals(status)) {
                String reason = XmlUtils.getNodeValue(doc, "reason");
                System.out.println("-------------" + StringEscapeUtils.unescapeHtml3(reason));
            }
            out.println("������������������������������������������������������������������������������������������������������������������������������������������");
            out.println("<br>");
            out.println("succ".equals(status) ? "��" + (i + 1) + "�ʣ��ɹ��ˣ�" : "��" + (i + 1) + "�ʣ�ʧ���ˣ�");
            out.println("<br>");
            out.println("��" + (i + 1) + "�ʣ�����˷��أ�" + responseString + "<br>");
        } else {
            out.println("��" + (i + 1) + "�ʣ�����ʧ�ܣ�����ϵͳ�Ƿ�������");
            out.print("<br>");
            out.print("��" + (i + 1) + "�ʣ�����ĵ�ַΪ��" + request.getParameter("requestUrl"));
            out.print("<br>");
        }
        out.println("*****************************************************************************************************************************************************************************************");
        out.println("<br>");
    }
%>
<html>
<head>
    <title>�ύ���ʴ�������</title>
</head>
</html>
