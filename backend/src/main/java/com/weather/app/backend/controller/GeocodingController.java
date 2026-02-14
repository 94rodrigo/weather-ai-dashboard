package com.weather.app.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.app.backend.dto.GeocodingResponse;
import com.weather.app.backend.service.GeocodingService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/geo")
public class GeocodingController {

	private final GeocodingService service;
	
	public GeocodingController(GeocodingService service) {
		this.service = service;
	}
	
	@GetMapping("/search")
	public List<GeocodingResponse> search(@RequestParam String query,
											@RequestHeader(value = "Accept-Language", defaultValue = "en") String lang) {
		return service.searchCity(query, lang);
	}
}
