package com.link.item_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.link.item_service.entity.Item;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
	//
}
