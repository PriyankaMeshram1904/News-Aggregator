package com.example.news_blend.service;

import com.example.news_blend.model.NewsArticle;
import com.example.news_blend.repository.NewsArticleRepository;
import com.example.news_blend.response.NewsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NewsService {

    private static final String API_URL = "https://newsapi.org/v2/everything?q=";

    @Autowired
    private NewsArticleRepository newsRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${newsapi.api.key}")
    private String apiKey;

    public List<NewsArticle> getNewsArticles(String query) {
        String url = API_URL + query + "&apiKey=" + apiKey;
        try {
            NewsResponse response = restTemplate.getForObject(url, NewsResponse.class);
            if (response != null && response.getArticles() != null) {
                return response.getArticles();
            }
        } catch (Exception e) {
            // Consider logging the error
            System.err.println("Error fetching news articles: " + e.getMessage());
        }
        return List.of();
    }

    public void saveArticle(NewsArticle article) {
        if (article != null) {
            newsRepository.save(article);
        } else {
            // Optional: log a warning if a null article is being saved
            System.err.println("Attempted to save a null article");
        }
    }
}


