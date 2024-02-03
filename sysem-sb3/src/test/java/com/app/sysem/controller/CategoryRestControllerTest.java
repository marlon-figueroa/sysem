package com.app.sysem.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.app.sysem.model.Category;
import com.app.sysem.response.CategoryResponseRest;
import com.app.sysem.services.ICategoryService;

class CategoryRestControllerTest {

	@InjectMocks
	CategoryRestController categoryRestController;

	@Mock
	private ICategoryService service;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void saveTest() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Category category = new Category();
		category.setId(Long.valueOf(1));
		category.setName("Lacteos");
		category.setDescription("productos derivados de la leche");

		when(service.save(any(Category.class))).thenReturn(new ResponseEntity<CategoryResponseRest>(HttpStatus.OK));
		ResponseEntity<CategoryResponseRest> response = categoryRestController.save(category);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

}
