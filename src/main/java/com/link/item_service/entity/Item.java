package com.link.item_service.entity;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.link.item_service.enums.ItemStatus;
import com.link.item_service.enums.ItemType;

@Document(collection = "items")
public class Item {

	@Id
	private String id;

	private String name;

	private String description;

	private Double price;

	private ItemType type;

	private ItemStatus status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Item)) {
			return false;
		}

		return this.getId().equals(((Item) obj).getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.getName().length());
	}
}