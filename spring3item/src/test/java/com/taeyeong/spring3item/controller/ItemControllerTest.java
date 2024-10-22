package com.taeyeong.spring3item.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taeyeong.spring3item.model.Item;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

	@Autowired
	private MockMvc mvc;
	
	// 検索結果が想定通りであるかを確認するテスト
//	@Test
//	void testGetItem() throws Exception {
//		System.out.println("testGetItem hi");
//		int itemId = 2;
//		String responseJsonString = mvc.perform(get("/items/{itemId}", itemId) // Mockを使用し getメソッド, "/items/{itemId}"itemIdにアクセスする
//				.contentType(MediaType.APPLICATION_JSON)	// JSONタイプとして値を取得する
//				.accept(MediaType.APPLICATION_JSON_UTF8) // 非推奨であるが現時点では入れる必要がある？
//				.characterEncoding("UTF-8"))
//				.andExpect(status().isOk())	// ステータスコードチェック
//				.andReturn().getResponse().getContentAsString();
//	
//		// 取得したJSONオブジェクトをJavaオブジェクトにマッピングするインスタンス
//		ObjectMapper objectMapper = new ObjectMapper();
//		// 先ほど生成したJSONデータをItemクラスタイプにマッピング
//		Item responseItem = objectMapper.readValue(responseJsonString, Item.class);
//		
//		// Itemクラスにマッピングした、変数のgetItemIdが1Lと同じかどうか
//		assertThat(responseItem.getItemId()).isEqualTo(2L);
//		assertThat(responseItem.getItemName()).isEqualTo("ネックレス");
//		assertThat(responseItem.getItemCategory()).isEqualTo("ジュエリ");
//	}
	
//	@Test
	void testGetItems() throws Exception {// テストを行うには必ず必要
		List<Item> items = Arrays.asList(
				new Item("mouse", "家電"),
				new Item("ネックレス", "ジュエリ"),
				new Item("パソコン", "家電"),
				new Item("服", "衣類")
			);
		String responseJsonString = mvc.perform(get("/items")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.characterEncoding("UTF-8"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		
		ObjectMapper objectMapper = new ObjectMapper();
		Item responseItems = objectMapper.readValue(responseJsonString, Item.class);
//		System.out.println(responseItems);
//		assertArrayEquals(responseItems, items);
		
			
	}

	
	@Test
	void linesMatch() {
		List<String> expected = Arrays.asList("\\d+ms", "abc", ">> skip >>", ".{3}");
		List<String> actual   = Arrays.asList("123ms", "abc", "1", "2", "3", "zzz");
		
		assertLinesMatch(expected, actual);
	}
}
