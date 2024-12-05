package com.link.item_service.annotation;

import org.springframework.util.StringUtils;

import com.link.item_service.enums.ItemType;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ItemTypeValidator implements ConstraintValidator<ValidItemType, String> {

	@Override
	public boolean isValid(String type, ConstraintValidatorContext context) {

		if (!StringUtils.hasText(type)) {
			return false;
		}

		try {
			ItemType.valueOf(type.toUpperCase());
		} catch (IllegalArgumentException ex) {
			return false;
		}

		return true;
	}
}