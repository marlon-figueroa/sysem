package com.app.sysem.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.sysem.model.Category;
import com.app.sysem.response.CategoryResponseRest;
import com.app.sysem.services.ICategoryService;
import com.app.sysem.util.CategoryExcelExporter;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/v1/app/category")
public class CategoryRestController {

	@Autowired
	private ICategoryService service;

	/**
	 * get all the categories
	 * 
	 * @return
	 */
	@GetMapping("/")
	public ResponseEntity<CategoryResponseRest> searchCategories() {

		ResponseEntity<CategoryResponseRest> response = service.search();
		return response;
	}

	/**
	 * get categories by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id) {

		ResponseEntity<CategoryResponseRest> response = service.searchById(id);
		return response;
	}

	/**
	 * save categories
	 * 
	 * @param Category
	 * @return
	 */
	@PostMapping("/")
	public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category) {

		ResponseEntity<CategoryResponseRest> response = service.save(category);
		return response;
	}

	/**
	 * update categories
	 * 
	 * @param category
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category, @PathVariable Long id) {

		ResponseEntity<CategoryResponseRest> response = service.update(category, id);
		return response;
	}

	/**
	 * delete categorie
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id) {

		ResponseEntity<CategoryResponseRest> response = service.deleteById(id);
		return response;
	}

	/**
	 * export to excel file
	 * 
	 * @param respons
	 * @throws IOException
	 */
	@GetMapping("/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {

		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=result_category.xlsx";
		response.setHeader(headerKey, headerValue);

		ResponseEntity<CategoryResponseRest> categoryResponse = service.search();

		CategoryExcelExporter excelExporter = new CategoryExcelExporter(
				categoryResponse.getBody().getCategoryResponse().getCategory());

		excelExporter.export(response);

	}

}
