package com.link.item_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.link.item_service.dto.ItemDto;
import com.link.item_service.service.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@PostMapping
	public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto) {
		return new ResponseEntity<>(itemService.createItem(itemDto), HttpStatus.CREATED);
	}

	@GetMapping("/{itemId}")
	public ResponseEntity<ItemDto> getItem(@PathVariable String itemId) {
		return ResponseEntity.ok(itemService.getItem(itemId));
	}

	@GetMapping
	public ResponseEntity<Page<ItemDto>> getAllItems(@RequestParam int pageNo, @RequestParam int pageSize,
			@RequestParam String sortBy) {
		return ResponseEntity.ok(itemService.getAllItems(pageNo, pageSize, sortBy));
	}

	@PutMapping("/{itemId}")
	public ResponseEntity<ItemDto> updateItem(@PathVariable String itemId, @Valid @RequestBody ItemDto itemDto) {
		return ResponseEntity.ok(itemService.updateItem(itemId, itemDto));
	}

	@DeleteMapping("/{itemId}")
	public HttpStatus deleteItem(@PathVariable String itemId) {
		itemService.deleteItem(itemId);
		return HttpStatus.OK;
	}

	@DeleteMapping("/soft/{itemId}")
	public HttpStatus softDeleteItem(@PathVariable String itemId) {
		itemService.softDeleteItem(itemId);
		return HttpStatus.OK;
	}
}
