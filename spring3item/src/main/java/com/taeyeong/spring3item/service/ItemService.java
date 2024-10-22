package com.taeyeong.spring3item.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.taeyeong.spring3item.model.HelloMessage;
import com.taeyeong.spring3item.model.Item;
import com.taeyeong.spring3item.repo.ItemRepository;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;
	
	private RestTemplate restTemplate;
	
	// restTemplateBuilderを使用し他のアプリケーションの
	// APIを呼び出すことができる
	public ItemService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	
	// 複数のデータをListで返そう
	// 데이터 구조체
//	private List<Item> allItems = new ArrayList<> (Arrays.asList(
//			new Item("ネックレス", "ジュエリー"),
//			new Item("パーカー", "ファッション"),
//			new Item("10003", "フェイスタオル", "ビューティー"),
//			new Item("10004", "サプリメント", "ヘルス"),
//			new Item("10005", "マウス", "家電"),
//			new Item("10006", "ノートブック", "家電"),
//			new Item("10007", "ブルーベリー", "フード")));
	
	// cach
	@Cacheable("getItems") // キャッシュを管理するための任意の文字列
	public List<Item> getAllItems() {
		List<Item> allItems = new ArrayList<>();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		// findAllの戻り値の型はIterableなので直にallItemsに入れることは
		// できない、下記のようにforEacah()メソッドを使ってallItemsに::addで
		// 一つ一つ値を
		itemRepository.findAll().forEach(allItems::add);
		return allItems;
	}
	
	
	// 戻り値はOptional型
	@Cacheable(value="getItem", key="#p0")
	public Optional<Item> getItem(Long itemId) {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
//		for(int idx = 0; idx < allItems.size(); idx++) {
//			// idx번의 item을 가져와서 그 아이템의 id와 itemId를 비교
//			if(allItems.get(idx).getItemId().equals(itemId)) {
//				// allTimes앞에 (Item)은 타입을말하는건가?
//				return (Item)allItems.get(idx);
//			}
//		}
		
		return itemRepository.findById(itemId); 
	}

	@CacheEvict(value="getItems", allEntries=true)	// キャッシュをクリアするアノテーション
	public void addItem(Item item) {
//		allItems.add(item);
		itemRepository.save(item);
		
	}
	
	@Caching(evict = {
			@CacheEvict(value="getItem", key="#p0"),
			@CacheEvict(value="getItems", allEntries=true)
	})
	public void updateItem(Long itemId, Item item) {
		System.out.println(itemRepository.findById(itemId).get() != null);
		// item의 id값과 일치하는 데이터가 있으면 그 데이터에 덮어씌운다.
		if(itemRepository.findById(itemId).get() != null) {
			itemRepository.save(item);
		} 
		
		
//		for(int idx = 0; idx < allItems.size(); idx++) {
//			if(allItems.get(idx).getItemId().equals(itemId)) {
//				allItems.set(idx, item);
//			}
//		}
//		
	}
	
	@Caching(evict = {
			@CacheEvict(value="getItem", key="#p0"),
			@CacheEvict(value="getItems", allEntries=true)
	})
	public void deleteItem(Long itemId) {
		
		itemRepository.deleteById(itemId);
		
		// 一行でremoveができる
//		allItems.removeIf(item -> item.getItemId().equals(itemId));
//		for(int idx = 0; idx < allItems.size(); idx++) {
//			if(allItems.get(idx).getItemId().equals(itemId)) {
//				allItems.remove(idx);
//			}
//		}
	}
	
	public HelloMessage getHelloResponse() {
		String URL = "http://localhost:8081/hello";
		// 第一引数：URL, 第二引数：戻り値の型
		// 今回は"アクセス成"という文字列だけなのでString.classを
		// String.classを
		String hello = restTemplate.getForObject(URL, String.class);
	
		HelloMessage retHello = new HelloMessage(hello);
		
		return retHello;
	}
}