package com.skillbox.users.controller;

import com.skillbox.users.dto.NewsDto;
import com.skillbox.users.entity.News;
import com.skillbox.users.mapper.AddressMapper;
import com.skillbox.users.mapper.NewsMapper;
import com.skillbox.users.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
	private final NewsService newsService;
	private final NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);

	@GetMapping
	public List<NewsDto> getNews() {
		log.info("Get list news...");
		
		List<News> news = newsService.findAll();
		return news.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody News news) {
		log.info(String.format("Create user with id %s", news.getId()));
		
		News savedNews = newsService.create(news);

		URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedNews.getId()).toUri();

		return ResponseEntity.created(locationUri).build();
	}

	@GetMapping("/{id}")
	public NewsDto getById(@PathVariable UUID id) {
		return convertToDto(newsService.findById(id));
	}

	@PutMapping("/{id}")
	public String update(@RequestBody News news, @PathVariable UUID id) {
		log.info(String.format("Update news with id %s", news.getId()));

		checkIdNews(news, id);

		return newsService.update(news);
	}

	private void checkIdNews(News news, UUID id) {
		if (news.getId() == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Не верно задан id для параметра News");
		
		if (!news.getId().equals(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Параметр Id не соотвествует Id параметра News");
		}
	}
	
	@DeleteMapping("/{id}")
	public String delete(@RequestBody News news, @PathVariable UUID id) {
		log.info(String.format("Delete news with id %s", news.getId()));

		checkIdNews(news, id);
		
		return newsService.delete(news);
	}

	@GetMapping("/user/{id}")
	public List<NewsDto> getNewsByUserCreator(@PathVariable UUID id) {
		log.info("Get list news...");

		List<News> news = newsService.findByUserCreatorId(id);
		return news.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private NewsDto convertToDto(News news) {
		return newsMapper.toNewsDto(news);
	}
}
