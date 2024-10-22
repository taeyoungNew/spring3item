package com.taeyeong.spring3item.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taeyeong.spring3item.model.Item;

@Repository						// 첫번째인자에 entity타입, 두번째인자에 id타입
public interface ItemRepository extends CrudRepository<Item, Long> {
	
}
