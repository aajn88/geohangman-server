package com.doers.geohangman.utils;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.doers.geohangman.constants.Constants;

/**
 *
 * Restful Utils class. This class implements requesting methods as GET and POST
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public final class RestUtils {

	/** Private default constructor **/
	private RestUtils() {
	}

	/**
	 * Given a URL, a Class Type and a map with variables, this method performs
	 * GET method request to the given url
	 *
	 * @param url
	 *            The URL where GET request will be done.
	 * @param responseClass
	 *            Expected response class
	 * @param <T>
	 *            Expected return class
	 * @return Get method response
	 */
	public static <T> T get(String url, Class<T> responseClass) {
		return get(url, responseClass, null);
	}

	/**
	 * Given a URL, a Class Type and a map with variables, this method performs
	 * GET method request to the given url
	 *
	 * @param url
	 *            The URL where GET request will be done.
	 * @param responseClass
	 *            Expected response class
	 * @param urlVariables
	 *            Url Variables
	 * @param <T>
	 *            Expected return class
	 * @return Get method response
	 */
	public static <T> T get(String url, Class<T> responseClass,
			Map<String, ?> urlVariables) {
		RestTemplate template = getRestTemplate();
		return (urlVariables != null ? template.getForObject(url,
				responseClass, urlVariables) : template.getForObject(url,
				responseClass));
	}

	/**
	 *
	 * Given a URL, a request and a Class Type, this method performs POST method
	 * request to the given url
	 *
	 * @param url
	 *            The URL where POST request will be done.
	 * @param request
	 *            Request to be sent. Could be null
	 * @param responseClass
	 *            Expected response class
	 * @param <T>
	 *            Expected response class
	 * @return POST request response
	 */
	public static <T> T post(String url, Object request, Class<T> responseClass) {
		return post(url, request, responseClass, null);
	}

	/**
	 *
	 * Given a URL, a request, a Class Type and a map with variables, this
	 * method performs POST method request to the given url
	 *
	 * @param url
	 *            The URL where POST request will be done.
	 * @param request
	 *            Request to be sent. Could be null
	 * @param responseClass
	 *            Expected response class
	 * @param urlVariables
	 *            url Variables. Could be null
	 * @param <T>
	 *            Expected response class
	 * @return POST request response
	 */
	public static <T> T post(String url, Object request,
			Class<T> responseClass, Map<String, ?> urlVariables) {
		RestTemplate template = getRestTemplate();
		T response;

		response = template.postForObject(url, request, responseClass,
				urlVariables);

		return response;
	}
	
	/**
	 * Do exchange using Spring framwork
	 * 
	 * @param url Target URL
	 * @param requestClass Requested class
	 * @param request Request
	 * @param responseClass Response class
	 * @param method Http method
	 * @param useGcmHeaders Specifies whether use GCM Headers or not
	 * @return Request response
	 */
	public static <T, R> T exchange(String url, Class<R> requestClass, R request, Class<T> responseClass, HttpMethod method, boolean useGcmHeaders) {
		RestTemplate template = getRestTemplate();
		HttpEntity<R> httpRequest = new HttpEntity<R>(request, getGcmHeaders());
		ResponseEntity<T> response = template.exchange(url, method, httpRequest, responseClass);
		return response.getBody();
	}
	
	/**
	 * This method returns standard GCM Headers
	 * @return GCM Headers
	 */
	private static HttpHeaders getGcmHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, String.format("key=%s",System.getenv(Constants.APP_API_KEY_ENV_VAR)));
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
		return headers;
	}

	/**
	 * Get a configured RestTemplate
	 * 
	 * @return configured restTemplate
	 */
	private static RestTemplate getRestTemplate() {
		RestTemplate template = new RestTemplate();
		return template;
	}
}
