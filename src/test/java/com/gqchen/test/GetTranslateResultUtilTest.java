package com.gqchen.test;

import com.gqchen.common.GetTranslateResultUtil;
import org.junit.Test;

import java.util.List;

public class GetTranslateResultUtilTest {
	public static void main(String[] args) {
		try {
			List<String> result = GetTranslateResultUtil.getResult("red");
			result.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void checkWord() {
		System.out.println(GetTranslateResultUtil.checkWords("blue_sky hello","_"," "));
	}
}
