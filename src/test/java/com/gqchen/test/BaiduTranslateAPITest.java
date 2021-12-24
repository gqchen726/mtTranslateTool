package com.gqchen.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gqchen.bean.Trans_Result;
import com.gqchen.bean.TranslateResult;
import com.gqchen.common.BaiduTranslateResultUtil;
import com.gqchen.common.BaiduTranslateResultUtil1;
import com.gqchen.common.MD5;
import com.gqchen.common.YamlReader;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Node;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class BaiduTranslateAPITest {
    @Test
    public void yamlTest1() {
        final Yaml yaml = new Yaml();
        Node node = null;
        String test = null;
        try {
            node = yaml.compose(new FileReader("src/main/resources/YamlTest.yaml"));
            test = yaml.dump("Test");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(node.getStartMark());
        System.out.println(test);
    }
    @Test
    public void yamlTest2() {
        final Yaml yaml = new Yaml();
        Node node = null;
        String test = null;
        FileReader fileReader = null;
        try {
//            yaml.load("src/main/resources/baiduTranslateApi.yaml");
            fileReader = new FileReader("src/main/resources/baiduTranslateApi.yaml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Iterable<Node> nodes = yaml.composeAll(fileReader);
        for (Node node1 : nodes) {

        }
//        System.out.println(yaml.dump("baidu.api.url"));
    }
    @Test
    public void yamlTest3() {
        try {
            final Map<Object, Object> properties = YamlReader.yamlReader.getProperties("src/main/resources/baiduTranslateApi.yaml");

    //        final Set<Map.Entry<Object, Object>> entrySet = properties.entrySet();
    //        for (Map.Entry<Object, Object> map :
    //                entrySet) {
    //            System.out.println(map.getValue());
    //        }
            for (Object o : properties.keySet()) {
                System.out.println(o.toString()+":"+properties.get(o));


                Map<String,Object> map1 = (Map<String,Object>)properties.get(o.toString());
                for (Object o1 : map1.keySet()) {
                    System.out.println("\t"+o1.toString()+":"+map1.get(o1.toString()));


                    Map<String,Object> map2 = (Map<String,Object>)map1.get(o1.toString());
                    for (Object o2 : map2.keySet()) {
                        System.out.println("\t\t"+o2.toString()+":"+map2.get(o2.toString()));

                        Map<String,Object> map3 = null;
                        Object obj = map2.get(o2.toString());
                        if(obj instanceof Map) {
                            map3 = (Map<String,Object>)obj;
                        }
                        if (map3 != null) {
                            for (Object o3 : map3.keySet()) {
                                System.out.println("\t\t\t"+o3.toString()+":"+map3.get(o3.toString()));
                                Object obj1 = map3.get(o3.toString());
                                System.out.println(obj1 instanceof Map);


                            }
                        } else {
                            continue;
                        }


                    }
                }
            }
    //        System.out.println(((Map<String,Object>)properties.get("baidu")).get("api"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void yamlTest4() {

        try {
            final Map<Object, Object> result = YamlReader.yamlReader.getResult("src/main/resources/baiduTranslateApi.yaml");

//        System.out.println(result.size());
            for (Object o : result.keySet()) {

                System.out.println(o+":"+result.get(o));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void yamlTest5() {

        try {
            final Map<Object, Object> resultG = YamlReader.yamlReader.getResultG("src/main/resources/baiduTranslateApi.yaml");
//            final String appid = resultG.get("baidu.api.requestProperties.appid").toString();
//            final String url = resultG.get("baidu.api.url").toString();
//            System.out.println(appid);
//            System.out.println(url);

            for(Object obj : resultG.keySet()) {
                System.out.println(obj.toString() + "->" + resultG.get(obj));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void yamlTest6() {

        try {
            final Map<Object, Object> resultG = YamlReader.yamlReader.getProperties("src/main/resources/baiduTranslateApi.yaml");


            for(Object obj : resultG.keySet()) {
                System.out.println(obj.toString() + "->" + resultG.get(obj));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void yamlTest7() {

        try {
            final Map<Object, Object> resultG2 = YamlReader.yamlReader.getResultG2("src/main/resources/YamlTest.yaml");
            for (Object obj : resultG2.keySet()) {
                System.out.println(obj+":"+resultG2.get(obj));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void baiduTranslate1() {

//        BaiduTranslateResultUtil.btru.getResult("spring");
        try {
            YamlReader yamlReader = YamlReader.yamlReader;
            final Map<Object, Object> properties = yamlReader.getProperties("src/main/resources/baiduTranslateApi.yaml");
            System.out.println(properties);
            final Map<Object, Object> result = yamlReader.getvalues(properties);
            System.out.println(result);
            for(Object o : result.keySet()) {
                System.out.println(o.toString() + ":" + result.get(o));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void baiduTranslate2() {
        String q = "blue";
        String string = BaiduTranslateResultUtil.btru.getResult(q,"en","zh");
        System.out.println(string);
        String dst = string.substring((56 + q.length()), (string.length() - 4));
//        System.out.println(dst.split("\\"));
        for (int i = 0;i < dst.length();) {
            String c = (dst.substring(i,i+=6));
            System.out.println(c);


        }


    }

    /**
     * 引入阿里巴巴的fastJson,将百度返回的json格式的数据格式化其编码后输出
     */
    @Test
    public void baiduTranslate3() {
        String q = "Department";
        String string = BaiduTranslateResultUtil.btru.getResult(q,"en","zh");
        final JSONObject jsonObject = JSON.parseObject(string);
        final JSONArray transResults = jsonObject.getJSONArray("trans_result");
        final JSONObject trans_result = transResults.getJSONObject(0);
        System.out.println(trans_result.toString());
        final JSONObject jsonObject1 = JSON.parseObject(trans_result.toString());
        final Object dst = jsonObject1.get("dst");
        final Object src = jsonObject1.get("src");
        System.out.println("src:"+src);
        System.out.println("dst:"+dst);



    }
    @Test
    public void baiduTranslate4() {
        System.out.println(BaiduTranslateResultUtil.btru.getFinalResult("red").toString());
    }
    @Test
    public void baiduTranslate5() {
        System.out.println(BaiduTranslateResultUtil1.btru.getFinalResult("red").toString());
    }

    /**
     * yamlReader工具类完善
     */
    @Test
    public void yamlTest() {
        try {
            final Map<Object, Object> properties = YamlReader.yamlReader.getProperties("src/main/resources/baiduTranslateApi.yaml");
            final Map<Object, Object> map = YamlReader.yamlReader.getvaluesG(properties, null);
            System.out.println(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void fastJsonTest() {
        String q = "Department";
        String string = BaiduTranslateResultUtil.btru.getResult(q,"en","zh");
        final JSONObject jsonObject = JSON.parseObject(string);
        final JSONArray transResults = jsonObject.getJSONArray("trans_result");
        final JSONObject trans_result = transResults.getJSONObject(0);
//        System.out.println(trans_result.toJSONString());

        final Trans_Result trans_result1 = JSONObject.parseObject(trans_result.toJSONString(), Trans_Result.class);
//        System.out.println(trans_result1);


    }
    @Test
    public void fastJsonTest1() {
        String q = "Department";
        String string = BaiduTranslateResultUtil.btru.getResult(q,"en","zh");
        final TranslateResult translateResult = JSONObject.parseObject(string, TranslateResult.class);
        System.out.println(translateResult);
//        System.out.println(translateResult.getTrans_result().toString());

    }

    /**
     * MD5签名校验
     * 代码测试结果(一致)：
     *  sign:20201016000590665评估1603273298062HcGhEQkE82otdClMM6xu
     *  md5:ef127bb1ae74c37fecfa9b9aa0ad76cc
     * 	chk:ef127bb1ae74c37fecfa9b9aa0ad76cc
     * 打包测试结果(不一致)：
     *  sign:20201016000590665评估1603273525356HcGhEQkE82otdClMM6xu
     * 	md5:193123ef7e0ea29cdfcb5de6e2bcac3c
     * 	chk:8d2a77e2eee04a45fd0faff2691eb5ca
     */
    @Test
    public void md5Test() {
        try {
            String s = "20201016000590665" + "迟到" + "1603272260473" + "HcGhEQkE82otdClMM6xu";
            s = "20201016000590665评估1603273688455HcGhEQkE82otdClMM6xu";
            System.out.println(MD5.md5(s));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
