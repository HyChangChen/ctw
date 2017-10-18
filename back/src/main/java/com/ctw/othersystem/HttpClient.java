package com.ctw.othersystem;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;

@SuppressWarnings("deprecation")
public class HttpClient {
    static CookieStore cookieStore = new BasicCookieStore();
    static CloseableHttpClient client = HttpClients.createDefault();
    static CloseableHttpClient httpClient = HttpClients.custom().build();
    static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(15000)
            .setConnectTimeout(15000)
            .setConnectionRequestTimeout(15000)
            .build();
    private static HttpClient instance = null;

    private HttpClient() {
    }

    public static HttpClient getInstance() {
        if (instance == null) {
            instance = new HttpClient();
        }
        return instance;
    }

    /**
     * initialize a instance of the httpClient depending on your own request
     */
    private static CloseableHttpClient getInstanceClient() {
        CloseableHttpClient httpClient;
        StandardHttpRequestRetryHandler standardHandler = new StandardHttpRequestRetryHandler(5, true);
        HttpRequestRetryHandler handler = new HttpRequestRetryHandler() {

            @Override
            public boolean retryRequest(IOException arg0, int retryTimes, HttpContext arg2) {
                if (arg0 instanceof UnknownHostException || arg0 instanceof ConnectTimeoutException
                        || !(arg0 instanceof SSLException) || arg0 instanceof NoHttpResponseException) {
                    return true;
                }
                if (retryTimes > 5) {
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(arg2);
                HttpRequest request = clientContext.getRequest();
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                if (idempotent) {
                    // 如果请求被认为是幂等的，那么就重试。即重复执行不影响程序其他效果的
                    return true;
                }
                return false;
            }
        };
        HttpHost proxy = new HttpHost("127.0.0.1", 80);// 设置代理ip
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        httpClient = HttpClients.custom().setRoutePlanner(routePlanner).setRetryHandler(handler)
                .setConnectionTimeToLive(1, TimeUnit.DAYS).setDefaultCookieStore(cookieStore).build();
        return httpClient;
    }


    // 执行get
    public static String sendHttpGet(String url) {
        HttpGet get = new HttpGet(url);
        get.setConfig(requestConfig);
        HttpResponse response = null;
        String html = null;
        try {
            response = client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();// 连接代码
            Header[] headers = response.getAllHeaders();
            // 用于得到返回的文件头
            for (Header header : headers) {
                System.out.println(header);
            }
            html = new String(EntityUtils.toString(response.getEntity()).getBytes("iso8859-1"), "gb2312");
            // 在后面参数输入网站的编码，一般为utf-8
            // 返回的html代码,避免发生编码错误
            System.out.println(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html;
    }


    //POST
    public String sendHttpPost(String httpUrl) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        return sendHttpPost(httpPost);
    }


    /**
     * 发送Post请求
     *
     * @param httpPost
     * @return
     */
    private String sendHttpPost(HttpPost httpPost) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    /**
     * 发送 get请求
     *
     * @param httpUrl
     */
  /*  public static String sendHttpGet(String httpUrl) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        return sendHttpGet(httpGet);
    }*/

    /**
     * 发送 get请求Https
     *
     * @param httpUrl
     */
    public String sendHttpsGet(String httpUrl) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        return sendHttpsGet(httpGet);
    }

    /**
     * 发送Get请求
     *
     * @param httpGet
     * @return
     */
    private static  String sendHttpGet(HttpGet httpGet) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    /**
     * 发送Get请求Https
     *
     * @param httpGet
     * @return
     */
    private String sendHttpsGet(HttpGet httpGet) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader.load(new URL(httpGet.getURI().toString()));
            DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);
            httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }
}

