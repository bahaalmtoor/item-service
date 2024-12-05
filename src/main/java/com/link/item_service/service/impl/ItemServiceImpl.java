package com.link.item_service.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.link.item_service.dto.ItemDto;
import com.link.item_service.entity.Item;
import com.link.item_service.enums.ItemStatus;
import com.link.item_service.error.ConflictException;
import com.link.item_service.error.NotFoundException;
import com.link.item_service.mapper.ItemMapper;
import com.link.item_service.repository.ItemRepository;
import com.link.item_service.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);
	
	@Autowired
	private ItemRepository itemRepository;

	private Item convert(ItemDto itemDto) {
		return ItemMapper.INSTANCE.convert(itemDto);
	}

	private ItemDto convert(Item item) {
		return ItemMapper.INSTANCE.convert(item);
	}

	private Item getOrElseThrow(String id) {
		return itemRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Item with Id [%s] not found!", id)));
	}

	@Override
	public ItemDto createItem(ItemDto itemDto) {

		LOGGER.info("Create item: {}", itemDto);
		
		String id = UUID.randomUUID().toString();

		if (itemRepository.findById(id).orElse(null) != null) {
			throw new ConflictException(String.format("Item with Id [%s] already exists!", id));
		}

		Item item = convert(itemDto);
		item.setId(id);
		item.setStatus(ItemStatus.ACTIVE);

		return convert(itemRepository.save(item));
	}

	@Override
	public ItemDto getItem(String id) {
		
		LOGGER.info("Fetching item with id: {}", id);
		
		return convert(getOrElseThrow(id));
	}

	@Override
	public Page<ItemDto> getAllItems(int pageNo, int pageSize, String sortBy) {

		LOGGER.info("Fetching all items");
		
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return itemRepository.findAll(pageable).map(item -> convert(item));
	}

	@Override
	public ItemDto updateItem(String id, ItemDto itemDto) {
		
		LOGGER.info("Update item: {} with id: {}", itemDto, id);
		
		Item item = getOrElseThrow(id);

		Item itemRequest = convert(itemDto);
		item.setName(itemRequest.getName());
		item.setDescription(itemRequest.getDescription());
		item.setPrice(itemRequest.getPrice());
		item.setType(itemRequest.getType());
		item.setStatus(ItemStatus.ACTIVE);

		return convert(itemRepository.save(item));
	}

	@Override
	public void deleteItem(String id) {

		LOGGER.info("Delete item with id: {}", id);
		
		getOrElseThrow(id);
		itemRepository.deleteById(id);
	}

	@Override
	public void softDeleteItem(String id) {

		LOGGER.info("Soft-Delete item with id: {}", id);
		
		Item item = getOrElseThrow(id);
		item.setStatus(ItemStatus.INACIVE);
		itemRepository.save(item);
	}
}