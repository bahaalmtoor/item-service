package com.link.item_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.link.item_service.dto.ItemDto;
import com.link.item_service.entity.Item;

@Mapper
public interface ItemMapper {

	ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

	Item convert(ItemDto itemDto);

	ItemDto convert(Item item);
}
