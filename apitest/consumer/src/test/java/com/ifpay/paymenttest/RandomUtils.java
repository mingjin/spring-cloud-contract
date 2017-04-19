package com.ifpay.paymenttest;

import java.util.Random;

/**
 * Created by harvey.xu on 2017/3/25.
 */
public class RandomUtils {

    /**
     * 生成N位随机数
     * @param n
     * @return num
     */
    public  int random(Integer n) {
        int num = 0;

        for(int i = 0; i < n; i++){
            num += (int)(Math.random()*10);
        }
        return num;
    }

    /**
     * 生成时间戳随机数
     * @return num
     */
    public  String random() {
        String num;
        //num = System.currentTimeMillis() + String.valueOf((long) (Math.random() * 1000000));
        Random rand = new Random(java.util.UUID.randomUUID().hashCode());
        num=String.valueOf(rand.nextDouble());
        return num;
    }



    public static void main(String[] args) {
String a = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
        "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
        "<html>\n" +
        "<head>\n" +
        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
        "<title>收银台</title>\n" +
        "<link href=\"/css/style.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
        "<link href=\"/css/cash.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
        "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=EmulateIE7\" />\n" +
        "<script language=\"javascript\" src=\"/js/jquery-1.7.js\"></script>\n" +
        "<script language=\"javascript\" src=\"/js/jqstyle.js\"></script>\n" +
        "</head>\n" +
        "\n" +
        "\n" +
        "\n" +
        "<body onload=\"PAForm.submit();\" oncontextmenu=\"return false\">\n" +
        "  <form \n" +
        "      name=\"PAForm\"\n" +
        "      action=\"http://114.215.242.9:18170/Ebank\"\n" +
        "      method=\"post\">\n" +
        "\n" +
        "    \n" +
        "      <input type=hidden name=\"userIp\" value=\"116.228.54.118\"/>\n" +
        "    \n" +
        "      <input type=hidden name=\"_orderId\" value=\"318966720868962259\"/>\n" +
        "    \n" +
        "      <input type=hidden name=\"_persistence\" value=\"7BYH5Vt3TCYK9PyLsZ75XcA/a+nZ60q5i/qvaPdxd8syQJYYgDKR0zb/P6jYkrPBp3f9sz1frKNl  glwWEOtPsN4RMA1Kw+I4o5GrVc1YYwnPUhLBlbnwwcf9BPOmft1yWjYtGEETMJ9pYKR1C8s3MXfe  ffPtSXLXqNgqxCbR3sjS18BsNeD1lZHLW7/zHHuJCh0U1jBFMBeoXr+UoBfj3d/+fuURrps4xrnv  JiKmlRyzqz+OFS1cziDmwbAc9U24uffLy4wQYHDfLfigedLXLR8eGoWpx3KxdZgtpohcKBHbJ7et  78Q6ByKe90VQy/bpA+uAZ0nCmmRsLIhMd7yQNDzZOEggUnQ9HB5yanl3UIlO9rjhyew4MKom7hSA  Zs6/4PT/jx3YxxaecTj010GwKlPhA8mUbJvc/jojTIRfnhW0UuxwIBOwZkaBYQuGM5R1aM2M5sVe  lMRFXKFrooDFD3zfrH9yAclKQtqFuWsb97LUkhSkatG4tkHr8EYZvflv7dJidvE0YyLl/pHqfB43  1myqI6SLw44myIBobRZfBcAvKQfGjqCZquX3r0MTNOgKhoC+0I318Ii9XGBJugcRpJJZP2yc0KY8  OJ8DO0/02P7aL3yZ3zdocPiolZfDqtHOMOImMmP0dkWLWBRKnld0Bj7YKEvWIXNIhPnlq9KvuJCh  E0im3cfaY/7BSvpTdlYtM41IH+26ukBVEBde9cTd8xuTCZ5Ag0ibS0VcpLpAw59RZO6r3NCHq5U2  dyEJun/TAU1JKRv6ZaJEaia+DDZdFKLU6GYLZ43MUn4S6VqaBtD5E8zGpEwpLwiIxvbYWOkwVNle  50TOzOYqlzi0XoB1cxYfqOXDgmEnoilYocww6e+S5H3PhrR+N6IpWKHMMOnv7dmep17LfFVW2/Ut  Mb4vl8n2zl8lMuZnsjuiB/Fzu6RNafDDwbt5r/NGK8W4w9fAw4smHlNTOFFt13eUdzKVAOIuy8dh  0sz3s+lfkQUssPrVjDVGdlgLl+3Znqdey3xV5aD4qsEVJh43xCXqEDiAo0js06PgZJwUHajRNMON  9wPjOCLYSA+5WkzjpwQCRwAoAFzCXhgyRcS89Q24zLlaOFxdYTlU7IfDzdcv111VMsOPfHvKhYCu  sZUAPUZXp1VsI1OYzbHIotqMB+asd/lRQ2b40nEuUTniFOv6f1t6RL/59ndEiikNLdorPcNowI/Q  4J2I5HNyhjPohypqO2RSa/nyoDyX68vUchZDU+u+qN132SbM7oPXeUYiVlS6T1FWvPUNuMy5WjiU  JaVVY1Zvm5tHelz8kNLrN/tao+F1e3s3+1qj4XV7e5/kGQf1Hnx3uBaAxWmBfL2Z6/x+Zofx9Tf7  WqPhdXt7N/tao+F1e3vEPwnY1EBUUl/tNvY/G5kqtFcMnYhS0iyDrHuYpoPRaD1ZDnkUk9dDXTz7  P4F+QxFDecHavcKXStWuAcUQgIIwgM2pJ95UPp6yTfMzyWYvbZ67Syveh1tracvz8w0KxA6AP/Kb  advrDr/I5Pi3xLea5BjekfX7VXVSfhLpWpoG0G9EkZL4oTJ5LJ6pfHDH6mO8rtRyzZaLCAHJVlO0  4xg4htw7wwgsn+4Q5ssUFUy3gZU1oE/v9sL8339hvEFm3Y8hYU7ztI/Ewzb4DEBHYsKLVlE5lcoP  Bet6DMwrZlgEwlCOrTCkWBepU1NpUzmibIVsT+gARzTGsEutJX8ilj6lJ/CFRyJG7rdM6xzCIpDX  IvxhwXHEakiMVw6kmGQjr5Rkd5bKAUPqJeCdn4HMSQogumvOBQrfDHGEkwrST/IfBz0cJgIYgweQ  \"/>\n" +
        "    \n" +
        "      <input type=hidden name=\"appMsg\" value=\"\"/>\n" +
        "    \n" +
        "      <input type=hidden name=\"isApp\" value=\"web\"/>\n" +
        "    \n" +
        "      <input type=hidden name=\"appName\" value=\"\"/>\n" +
        "    \n" +
        "      <input type=hidden name=\"userID\" value=\"1579\"/>\n" +
        "    \n" +
        "      <input type=hidden name=\"terminal\" value=\"\"/>\n" +
        "    \n" +
        "      <input type=hidden name=\"backUrl\" value=\"\"/>\n" +
        "    \n" +
        "      <input type=hidden name=\"_id\" value=\"fBda016c81460adB184003Ea447B02\"/>\n" +
        "    \n" +
        "      <input type=hidden name=\"action\" value=\"http://114.215.242.9:18170/Ebank\"/>\n" +
        "    \n" +
        "      <input type=hidden name=\"grade\" value=\"a\"/>\n" +
        "    \n" +
        "      <input type=hidden name=\"appType\" value=\"\"/>\n" +
        "    \n" +
        "      <input type=hidden name=\"merchantjson\" value=\"B1c85234d50B19d50a4013f0553645af98792B934819dE19471fEE457B18ffdE1E6a804a19c30a1c54aE142B57a49E6f12f55001cd194101Ec477306fada006B83420BdB0a5d13af1E2458959E492Bd45001cd653504fE5B6158a59E593dc82d4E9d44530BfE1f3742Bad01f74804B09c1194709f2466d05fEd0086B88421481470558Ba0E1c43B8861E31c20219c30a1259Bd053053BEc80a79f63070cd045355B9112243a69E523adf1919d50a26698c361a14E6c85f29d51749B0461E13E6557007f2d3066d86400Bd71E4908Ea457103f3c81c79c3174f9a5a1f6Ea9052f14f0c8582fc50201c0074008EE597200f2c40175824601d7184801f3052642Bf985E04c40057c1420241fE5B6145a38d5E798B500E8d111006E4447052a9d2556ad0415Ed9191204Bf467205a98E043982410Bd80a5d13B5040246Bac80a79c61759cd045345B5032f53E8d0126a935E199B470550B0282553afc80a79815c09cd045342B905355fa98f1261931d5583411f548307224fE8c6122Bd00B568a5c195EB8557914aE83423Ed2066B8E51531dfE04265aa68f4204d41f5a8644530BfE437304fcdd0362864a7B9E595f52B31a611aE89a5122dc17559B770548ac12610cE8dB1226\"/>\n" +
        "    \n" +
        "  </form>\n" +
        "  <script language=\"javascript\">\n" +
        "    window.status=\"redirect to secure https site...\";\n" +
        "    window.focus();\n" +
        "  </script>\n" +
        "</body>";
    }
}
