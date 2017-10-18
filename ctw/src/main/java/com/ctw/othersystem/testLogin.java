package com.ctw.othersystem;



import com.sun.deploy.net.HttpResponse;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.Closeable;
import java.io.IOException;


public class testLogin {
    private static final String SITE = "mail.163.com";
    private static final int PORT = 80;

    private static final String loginAction = "http://mail.163.com/";
    private static final String forwardURL =
"http://hwwebmail.mail.163.com/js6/main.jsp?sid=gBNpwRnlsUddCpIdlhlldTmhbVrsxyvK&df=webmail163#module=welcome.WelcomeModule%7C%7B%7D";

    /**
     * 模拟等录
     *
     * @param LOGON_SITE
     * @param LOGON_PORT
     * @param login_Action
     * @param params
     * @throws Exception
     */
    private static String[] loginHtml(String LOGON_SITE, int LOGON_PORT, String
            login_Action, String... params) throws Exception {
        String[] result = null;
        HttpClient client = new HttpClient();
        client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);
// 模拟登录页面
        PostMethod post = new PostMethod(login_Action);
        GetMethod get=new GetMethod(login_Action);
        NameValuePair userName = new NameValuePair("email", params[0]);
        NameValuePair password = new NameValuePair("password", params[1]);
        post.setRequestBody(new NameValuePair[]{userName, password});
      /*  client.executeMethod(post);
        System.out.println("执行状态：" + client.getState());
        post.releaseConnection();*/

        client.executeMethod(get);

        System.out.println("执行状态：" + client.getState());


        get.releaseConnection();


        // 查看 cookie 信息
        CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
        Cookie[] cookies = cookiespec.match(LOGON_SITE, LOGON_PORT, "/", false,
                client.getState().getCookies());
        if (cookies != null)
            if (cookies.length == 0) {
                System.out.println("Cookies is not Exists ");
            } else {
                for (int i = 0; i < cookies.length; i++) {
                    System.out.println("----------------------------------------------------");
                    System.out.println(cookies[i].toString());
                    result = cookies[i].toString().split("=");
                    System.out.println("----------------------------------------------------");
                }
            }

        return result;
    }

    /**
     * @param cookies
     * @return
     */
    public static Document getHtmlDocument(String[] cookies) {
        try {
            Document doc = Jsoup.connect(forwardURL).cookie(cookies[0], cookies[1]).get();
            return doc;
        } catch (IOException e) {
            System.out.println("页面获取异常！");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String[] params = {"HyChang0104@163.com", "xiaomei0104"};
        String[] strings = null;
        try {
            strings = loginHtml(SITE, PORT, loginAction, params);
            if (strings.length > 0) {
                Document htmlDocument = getHtmlDocument(strings);
                System.out.println(htmlDocument);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
