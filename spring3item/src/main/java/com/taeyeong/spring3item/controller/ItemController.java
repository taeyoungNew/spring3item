package com.taeyeong.spring3item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taeyeong.spring3item.exception.ItemNotFoundException;
import com.taeyeong.spring3item.model.HelloMessage;
import com.taeyeong.spring3item.model.Item;
import com.taeyeong.spring3item.service.ItemService;

@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	// List<List内に存在する要素の型>
	@GetMapping("/items")
	public List<Item> getAllItems() {
		// 複数のデータをListで返そう
//		List<Item> allItems = Arrays.asList(
//					new Item("10001", "ネックレス", "ジュエリー"),
//					new Item("10002", "パーカー", "ファッション"),
//					new Item("10003", "フェイスタオル", "ビューティー"),
//					new Item("10004", "サプリメント", "ヘルス"),
//					new Item("10005", "マウス", "家電"),
//					new Item("10006", "ノートブック", "家電"),
//					new Item("10007", "ブルーベリー", "フード"));
		List<Item> allItems = itemService.getAllItems();
		
		return allItems;
	}
	
	@GetMapping("/items/{itemId}")
	public Item getItem(@PathVariable("itemId") Long itemId) {
		// orElseThrow：Optionalインスタンスが値を保持している場合
		// その値を返し、値が存在しない場合は任意の検査例外をスローする
		return itemService.getItem(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
		
	}
	
	
	@PostMapping("/items")
	public void addItem(@RequestBody Item item) {
		System.out.println(item.getItemName());
		itemService.addItem(item);
		
	};
	
	@PutMapping("/items/{itemId}")
	public void updateItem(@RequestBody Item item, @PathVariable("itemId") Long itemId) {
		System.out.println(item.getItemId());
		itemService.updateItem(itemId, item);
	}
	
	@DeleteMapping("/items/{itemId}")
	public void deleteItem(@PathVariable("itemId") Long itemId) {
		System.out.println("delete");
		itemService.deleteItem(itemId);
	}
	
	@GetMapping("/callHello")
	public HelloMessage callHello () {
		return itemService.getHelloResponse();
	}
	
	
}
