package org.example.backendproject_restapi.api_clients;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Setter @Getter
    private String baseUrl = "";

    private String getFullUrl(String url) {
        if (url.startsWith("http")) {
            return url;
        }
        return !baseUrl.endsWith("/") && !url.startsWith("/") ? baseUrl + "/" + url : baseUrl + url;
    }

    public <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        String fullUrl = getFullUrl(url);
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(fullUrl, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    public <T> T requestForEntityObject(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        ResponseEntity<T> responseEntity = this.requestForEntity(url, httpMethod, request, responseType, uriVariables);
        assert responseEntity != null;
        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
            return responseEntity.getBody();
        }
        return null;
    }
}
