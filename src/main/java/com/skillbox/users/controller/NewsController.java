package com.skillbox.users.controller;

import com.skillbox.users.dto.NewsDto;
import com.skillbox.users.entity.News;
import com.skillbox.users.mapper.NewsMapper;
import com.skillbox.users.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/news")
public class NewsController {
	
	final static Logger logger = LoggerFactory.getLogger(NewsController.class);

	private NewsService newsService;

	@Autowired
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	@GetMapping
	public List<NewsDto> getNews() {
		logger.info("Get list news...");
		
		List<News> news = newsService.findAll();
		return news.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody News news) {
		logger.info(String.format("Create user with id %s", news.getId()));
		
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
		logger.info(String.format("Update news with id %s", news.getId()));

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
		logger.info(String.format("Delete news with id %s", news.getId()));

		checkIdNews(news, id);
		
		return newsService.delete(news);
	}

	@GetMapping("/user/{id}")
	public List<NewsDto> getNewsByUserCreator(@PathVariable UUID id) {
		logger.info("Get list news...");

		List<News> news = newsService.findByUserCreatorId(id);
		return news.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private NewsDto convertToDto(News news) {
		return NewsMapper.INSTANCE.convert(news);
	}
}
