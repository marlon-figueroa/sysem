package com.app.sysem.response;

import java.util.List;

import com.app.sysem.model.Category;

import lombok.Data;

@Data
public class CategoryResponse {

	private List<Category> category;

}
