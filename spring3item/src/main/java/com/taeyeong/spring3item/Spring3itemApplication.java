package com.taeyeong.spring3item;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.taeyeong.spring3item.model.Item;
import com.taeyeong.spring3item.repo.ItemRepository;

@SpringBootApplication
@EnableCaching
public class Spring3itemApplication implements CommandLineRunner {

	@Autowired
	private ItemRepository itemRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Spring3itemApplication.class, args);
	
	}
	
	@Override
	public void run(String ...args)throws Exception {
		// CommandLineRunnerのrunメソッドをオーバーライド
		// アプリケーションが実行した時に下記のrunメソッドが実施される
		List<Item> items = Arrays.asList(
					new Item("mouse", "家電"),
					new Item("ネックレス", "ジュエリ"),
					new Item("パソコン", "家電"),
					new Item("服", "衣類")
				);
//		itemRepository.save(new Item("ネックレス", "ジュエリ"));
		
		itemRepository.saveAll(items);
	}
}
