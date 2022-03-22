package com.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.controller.StockController;

import com.rest.api.model.Grafico;
import com.rest.api.model.Stock;
import com.rest.api.repository.GraficoRepository;
import com.rest.api.repository.StockRepository;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.RequiredArgsConstructor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RequiredArgsConstructor
@SpringBootTest
@AutoConfigureMockMvc
class Api1ApplicationTests {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private GraficoRepository graficoRepository;
	@Autowired
	private StockRepository stockRepository;

	@InjectMocks
	private StockController stocksController;

	@BeforeEach
	public void setUp() {
		RestAssuredMockMvc.standaloneSetup(this.stocksController);
	}

	@Test
	void Get5Stocks() {
		RestAssuredMockMvc.given().accept(ContentType.JSON).when()
				.get("/stocks/")
				.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	void GetAllStocks() {
		RestAssuredMockMvc.given().accept(ContentType.JSON).when()
				.get("/stocks/all")
				.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	void GetStocksByName(){
		RestAssuredMockMvc.given().accept(ContentType.JSON).when()
				.get("/stocks/{stock_name}", toString())
				.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	void getGrafico() throws Exception {
		Optional<Grafico> order = graficoRepository.findById(21L);
		String token = "Bearer eyJraWQiOiJuQktld1lTTHB6YU5RaW9pRFJoQ2xRcUJtQ0V0OVRROXUzbU1zMm1LZkpRIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULl92bDBLV0Mtdjd6UkNnZ2VNQ0xjekszVGw0bTltVU5Kcll0b1NyektST3ciLCJpc3MiOiJodHRwczovL2Rldi0zNDE2MjE0Lm9rdGEuY29tL29hdXRoMi9kZWZhdWx0IiwiYXVkIjoiYXBpOi8vZGVmYXVsdCIsImlhdCI6MTY0Nzg4NDQ2NSwiZXhwIjoxNjQ3ODg4MDY1LCJjaWQiOiIwb2Ezang1cXpiZk1ub1lMYzVkNyIsInVpZCI6IjAwdTQ3NDJla3o4SlFJZGh1NWQ3Iiwic2NwIjpbImVtYWlsIiwib3BlbmlkIiwicHJvZmlsZSJdLCJhdXRoX3RpbWUiOjE2NDc4ODQ0NjEsInN1YiI6InZpdG9yaWFzbDMxMDdAZ21haWwuY29tIn0.V_759aVsnH5S9PYX_xExrGfdXqVKtL-QMOj_sHtHq1F_jikI4d4cWu_zo1h8za7RLqpt_RAJ7yUvJM0F2KIvP3CD8Re7fv8uQEv-Znox4W9BIHACmxdkWTQP1lkz6jiVIB29zBJ-ttDQp_Dr2ljcAQ-YsARf7nbkFiqoq7fVgLuwVMiBjqDMA6apurWdDSHrBxsS06FFtPFUt2nuozGFj90CPd4U4QwY00hnxz1QDZvXwwBvg42lxmExS17EJzwmznlG2sbmCr68EscT_xoyd2aoiIq5mp_xvb1K7YIDCpyW7PfqOQUKwhZRlmvCVB-5TJxV519ZqWgvlAVJ3Fbg8g";

		mockMvc.perform(get("/grafico/{id_Stock}", 64L).header(HttpHeaders.AUTHORIZATION, token).contentType("application/json")
				.content(objectMapper
						.writeValueAsString(order))).andExpect(status().isOk());

		Optional<Grafico> getGrafico = graficoRepository.findById(21L);
		Assertions.assertThat(getGrafico.get().getId().equals(21L));
	}

	@Test
	void getStocks() throws Exception {
		Optional<Stock> order = stockRepository.findById(1L);
		String token = "Bearer eyJraWQiOiJuQktld1lTTHB6YU5RaW9pRFJoQ2xRcUJtQ0V0OVRROXUzbU1zMm1LZkpRIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULl92bDBLV0Mtdjd6UkNnZ2VNQ0xjekszVGw0bTltVU5Kcll0b1NyektST3ciLCJpc3MiOiJodHRwczovL2Rldi0zNDE2MjE0Lm9rdGEuY29tL29hdXRoMi9kZWZhdWx0IiwiYXVkIjoiYXBpOi8vZGVmYXVsdCIsImlhdCI6MTY0Nzg4NDQ2NSwiZXhwIjoxNjQ3ODg4MDY1LCJjaWQiOiIwb2Ezang1cXpiZk1ub1lMYzVkNyIsInVpZCI6IjAwdTQ3NDJla3o4SlFJZGh1NWQ3Iiwic2NwIjpbImVtYWlsIiwib3BlbmlkIiwicHJvZmlsZSJdLCJhdXRoX3RpbWUiOjE2NDc4ODQ0NjEsInN1YiI6InZpdG9yaWFzbDMxMDdAZ21haWwuY29tIn0.V_759aVsnH5S9PYX_xExrGfdXqVKtL-QMOj_sHtHq1F_jikI4d4cWu_zo1h8za7RLqpt_RAJ7yUvJM0F2KIvP3CD8Re7fv8uQEv-Znox4W9BIHACmxdkWTQP1lkz6jiVIB29zBJ-ttDQp_Dr2ljcAQ-YsARf7nbkFiqoq7fVgLuwVMiBjqDMA6apurWdDSHrBxsS06FFtPFUt2nuozGFj90CPd4U4QwY00hnxz1QDZvXwwBvg42lxmExS17EJzwmznlG2sbmCr68EscT_xoyd2aoiIq5mp_xvb1K7YIDCpyW7PfqOQUKwhZRlmvCVB-5TJxV519ZqWgvlAVJ3Fbg8g";

		mockMvc.perform(get("/stocks/{stock_name}", "MINERVA").header(HttpHeaders.AUTHORIZATION, token).contentType("application/json")
				.content(objectMapper
						.writeValueAsString(order))).andExpect(status().isOk());

		Optional<Stock> getStocks = stockRepository.findById(1L);
		Assertions.assertThat(getStocks.get().getId().equals(1L));
	}

	@Test
	void getStocks2() throws Exception {
		Optional<Stock> order = stockRepository.findById(1L);
		String token = "Bearer eyJraWQiOiJuQktld1lTTHB6YU5RaW9pRFJoQ2xRcUJtQ0V0OVRROXUzbU1zMm1LZkpRIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULl92bDBLV0Mtdjd6UkNnZ2VNQ0xjekszVGw0bTltVU5Kcll0b1NyektST3ciLCJpc3MiOiJodHRwczovL2Rldi0zNDE2MjE0Lm9rdGEuY29tL29hdXRoMi9kZWZhdWx0IiwiYXVkIjoiYXBpOi8vZGVmYXVsdCIsImlhdCI6MTY0Nzg4NDQ2NSwiZXhwIjoxNjQ3ODg4MDY1LCJjaWQiOiIwb2Ezang1cXpiZk1ub1lMYzVkNyIsInVpZCI6IjAwdTQ3NDJla3o4SlFJZGh1NWQ3Iiwic2NwIjpbImVtYWlsIiwib3BlbmlkIiwicHJvZmlsZSJdLCJhdXRoX3RpbWUiOjE2NDc4ODQ0NjEsInN1YiI6InZpdG9yaWFzbDMxMDdAZ21haWwuY29tIn0.V_759aVsnH5S9PYX_xExrGfdXqVKtL-QMOj_sHtHq1F_jikI4d4cWu_zo1h8za7RLqpt_RAJ7yUvJM0F2KIvP3CD8Re7fv8uQEv-Znox4W9BIHACmxdkWTQP1lkz6jiVIB29zBJ-ttDQp_Dr2ljcAQ-YsARf7nbkFiqoq7fVgLuwVMiBjqDMA6apurWdDSHrBxsS06FFtPFUt2nuozGFj90CPd4U4QwY00hnxz1QDZvXwwBvg42lxmExS17EJzwmznlG2sbmCr68EscT_xoyd2aoiIq5mp_xvb1K7YIDCpyW7PfqOQUKwhZRlmvCVB-5TJxV519ZqWgvlAVJ3Fbg8g";

		mockMvc.perform(get("/stocks/all").header(HttpHeaders.AUTHORIZATION, token).contentType("application/json")
				.content(objectMapper
						.writeValueAsString(order))).andExpect(status().isOk());

		Optional<Stock> getStocks2 = stockRepository.findById(1L);
		Assertions.assertThat(getStocks2.get().getId().equals(1L));
	}


}
