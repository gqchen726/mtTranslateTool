package com.gqchen.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class GetTranslateResultUtil {
	private static String preUrl = null;
	private static String endUrl = null;
	private static BufferedReader br = null;
	private static String startStringOp = null;
	private static String endStringOp = null;
	private static int flag;
	private static InputStream in = null;
	private static List<String> list;
	static {

		list = new ArrayList<>();
		list.removeAll(list);

		preUrl = "http://www.baidu.com/s?ie=utf-8&wd=";
//		preUrl = "https://fanyi.sogou.com/?keyword=";
//		endUrl = "&transfrom=auto&transto=zh-CHS";
		flag = 0;

		startStringOp = "<span class=\"op_dict_text2\">";
		endStringOp = "</span>";

//		startString = "常用词典";
//		endString = "搜狗翻译";
	}
	public static List<String> getResult(String word) throws Exception {
			// 处理参数中的空格&特殊符号等
			word = checkWords(word, "_", " ");
			// 创建URL
			URL url = new URL(preUrl+word);
			// URL url = new URL(preUrl+word+endUrl);

			//创建代理
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("10.1.27.102",8080));

			// 创建HttpURLConnection连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
			// 设置连接信息
			conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent",  
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36");
            //设置请求方式
			conn.setRequestMethod("GET");


			// 启动连接
            conn.connect();
			// Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36
			// 获取连接的读取流
			br = new BufferedReader(
					new InputStreamReader(
							conn.getInputStream()));
			String line = null;
//			System.out.println(url);
			list.removeAll(list);
			// 通过行遍历返回的内容
			while ((line = br.readLine()) != null) {
				// 从指定的索引(指定字符串的索引处)开始存储到list集合中
				if (line.indexOf(startStringOp) != -1) {
					flag = 1;
				}
				// 从指定的索引(指定字符串的索引处)结束存储
				if (line.indexOf(endStringOp) != -1) {
					flag = 0;
				}
				// 标记为1时存储
				if (flag == 1) {
					String[] ss = {"\"","<span class=op_dict_text2>"," "};
					line = lrr(line, ss );
					list.add(line);
				}
			}
			// 关闭流
			if(br != null) br.close();
			// 将容器返回
			return list;
			
	}


	/**
	 * 将字符串中的一些内容剔除(即替换成""空字符串)
	 * @param line 处理的字符串
	 * @param ss 替换规则列表
	 * @return
	 */
	public static String lrr(String line,String[] ss) {
		
		for (int i = 0; i < ss.length; i++) {
			line = lrr(line, ss[i]);
		}
		
		return line;
	}
	/*
	 * 将字符串中的某个内容剔除(即替换成""空字符串)
	 */
	public static String lrr(String line,String srcString) {
		return line.replaceAll(srcString, "");
	}

	/**
	 * 利用校验规则处理word参数
	 * 	 校验规则：把" "字符串替换成%20,并且去掉_字符,并用" "代替
	 * @param word 校验的字符串
	 * @return 校验过的字符串
	 */
	public static String checkWord(String word,String checkChar) {
		String[] s = word.split(checkChar);
		String result = "";
		for(String s1 : s) {
			result += (s1 + "%20");
		}

		return result;
	}
	public static String checkWords(String word,String... checkChar) {
		String result = "";
		for (int i = 0;i < checkChar.length;i++) {
			result = checkWord(word,checkChar[i]);
			word = result;
		}
		return result;
	}
}

//https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=02003390_hao_pg&wd=blue%20sky&oq=red&rsv_pq=9904c0ab001015b4&rsv_t=6354abJ1xuJO0S9sigo3lcku%2FThJcfwonQBnsDKxB5EBOkzjGoL%2BLalGZn7YTfc6AsKA2r9n&rqlang=cn&rsv_enter=1&rsv_dl=tb&rsv_sug3=15&rsv_sug1=11&rsv_sug7=101&rsv_sug2=0&rsv_btype=t&inputT=4896&rsv_sug4=4896


//翻译一个单词
//<span class="op_dict_text2">
//蓝色;
//                        天蓝色;
//                        蔚蓝色;
//蓝色荣誉者(牛津或剑桥大学的校队运动员);
//                        蓝色荣誉的头衔;
//                        错误;
//                        失误;
//</span>
//翻译多个单词,字典中有收录
//<td>
//<span class="op_dict_text2">
//		蔚蓝的天空;
//</span>

//<p class="op_sp_fanyi_line_two">
//</span>