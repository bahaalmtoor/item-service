package com.link.item_service.service;

import org.springframework.data.domain.Page;

import com.link.item_service.dto.ItemDto;

public interface ItemService {

	ItemDto createItem(ItemDto itemDto);

	ItemDto getItem(String itemId);

	Page<ItemDto> getAllItems(int pageNo, int pageSize, String sortBy);

	ItemDto updateItem(String itemId, ItemDto itemDto);

	void deleteItem(String itemId);
	
	void softDeleteItem(String itemId);
}
