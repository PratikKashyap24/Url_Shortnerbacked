package com.url.service;


import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.google.common.hash.Hashing;
import com.url.entity.Url;
import com.url.exception.IncorrectInputException;
import com.url.exception.ValueNotPresentException;
import com.url.repository.UrlHandleRepo;

@Service
public class UrlHandleService {
	@Autowired
	private UrlHandleRepo urlRepository;
	
	
	public String getOriginalUrl(String id) throws ValueNotPresentException {
		Optional<String> value=Optional.fromNullable(urlRepository.findByShortUrl(id));
		if(value.isPresent()) {
			return value.get();
		}
		else {
			throw new ValueNotPresentException("No Records Found");
		}

	}
	
	public Url generateShortUrl(String url) throws IncorrectInputException{
		System.out.println(url);
		if (!isUrlValid(url)) {
			throw new IncorrectInputException("Invalid Input");
		}
		
		Url urlObject = new Url();
		urlObject.setOriginalurl(url);
		urlObject.setShorturl(getShortUrl(url));
		return urlRepository.save(urlObject);
	}
public static String getShortUrl(String url) {
		
		String shortUrl = encodeUrl(url);
		return shortUrl;
	}
	
	private static String encodeUrl(String url) {
		String encodedUrl = "";
		LocalDateTime time = LocalDateTime.now();
		encodedUrl = Hashing.murmur3_32_fixed().hashString(url.concat(time.toString()), StandardCharsets.UTF_8).toString();
		return encodedUrl;
	}

	public static boolean isUrlValid(String url) {
		UrlValidator urlValidator = new UrlValidator(new String[] { "http", "https" });
		boolean result = urlValidator.isValid(url);
		return result;
	}

	
}
