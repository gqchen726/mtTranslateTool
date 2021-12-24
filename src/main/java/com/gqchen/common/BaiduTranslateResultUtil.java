package com.gqchen.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gqchen.bean.Trans_Result;
import com.gqchen.bean.TranslateResult;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BaiduTranslateResultUtil {
    private static Map<Object, Object> properties;
    private URL uri;
    private Proxy proxy;
    private HttpURLConnection conn;
    public static BaiduTranslateResultUtil btru;
    private BufferedReader bufferedReader;
    private StringBuilder resultText;
    static {
        //调用yaml工具类解析yaml文件获取连接参数
        try {
            if (btru == null) {
                btru = new BaiduTranslateResultUtil();
            }
            properties = YamlReader.yamlReader.getResult("baiduTranslateApi.yaml");
//            System.out.println(properties);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String getResult(String q,String from,String to) {
        try {
            HashMap<String, String> paramss = new LinkedHashMap<>();
            String appid = properties.get("appid").toString();
            String securityKey = properties.get("securityKey").toString();
            paramss.put("q",q);
            paramss.put("from",from);
            paramss.put("to",to);
            paramss.put("appid",appid);
            Long salt = System.currentTimeMillis();
            paramss.put("salt",salt.toString());
            // sign=appid+q+salt+securityKey
            String sign = appid + q + salt + securityKey;
            paramss.put("sign",MD5.md5(sign));
            // 拼接URL请求
            String url = this.getUrl(paramss);
            // 创建URL
            uri = new URL(url.toString());
            System.out.println("uri:"+uri);
            // 创建代理
            String host = properties.get("host").toString();
            Integer port = (Integer) properties.get("port");
            proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(host,port));
            // 开启HTTP连接
            conn = (HttpURLConnection)uri.openConnection(proxy);
            // 设置SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null,new TrustManager[]{myX509TrustManager},null);
            // 设置请求头的参数
            if (conn instanceof HttpsURLConnection) {
                ((HttpsURLConnection) conn).setSSLSocketFactory(sslContext.getSocketFactory());
            }
            conn.setRequestMethod(properties.get("meth").toString());
            conn.setConnectTimeout((Integer) properties.get("timeOut"));
            // 启动连接
            final int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("错误码："+responseCode);
            }
            // 处理响应
            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            resultText = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                resultText.append(line);
            }
            return resultText.toString();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedReader != null) bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    public String getUrl(Map<String,String> params) {
        final StringBuilder url = new StringBuilder();
        url.append(properties.get("url"));
        int i = 1;

        for (String param : params.keySet()) {
            if (i == 1) {
                url.append("?");
            } else {
                url.append("&");
            }
            url.append(param);
            url.append("=");
            url.append(encode(params.get(param)));
            i++;
        }
        return url.toString();
    }

    /**
     * 对输入的字符串进行URL编码, 即转换为%20这种形式
     *
     * @param input 原文
     * @return URL编码. 如果编码失败, 则返回原文
     */
    public static String encode(String input) {
        if (input == null) {
            return "";
        }

        try {
            return URLEncoder.encode(input, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return input;
    }


    private static TrustManager myX509TrustManager = new X509TrustManager() {

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    };

    /**
     * 最终结果,
     *  引入阿里巴巴的fastJson,将百度返回的json格式的数据格式化其编码后输出
     * @param q 关键字
     * @param from 源语言
     * @param to 目标语言
     * @return
     */
    public Trans_Result getFinalResult(String q,String from,String to) {
        final Trans_Result tr = new Trans_Result();
        final String result = this.getResult(q, from, to);
//        System.out.println(result);

        // 未反序列化的代码
        /*final JSONObject translateResult = JSON.parseObject(result);
        final JSONArray transResults = translateResult.getJSONArray("trans_result");
        final JSONObject transResult = transResults.getJSONObject(0);
        final JSONObject jsonObject1 = JSON.parseObject(transResult.toString());
        final Object dst = jsonObject1.get("dst");
        final Object src = jsonObject1.get("src");*/

        // 序列化之后的代码
        final TranslateResult translateResult = JSONObject.parseObject(result, TranslateResult.class);
        final Trans_Result trans_result = translateResult.getTrans_result();
        final String src = trans_result.getSrc();
        final String dst = trans_result.getDst();

        tr.setSrc(src.toString());
        tr.setDst(dst.toString());


        return tr;
    }

    /**
     * 默认from：auto，to：zh
     * @param q
     * @return
     */
    public Trans_Result getFinalResult(String q) {
        return this.getFinalResult(q,"auto","zh");
    }
}
