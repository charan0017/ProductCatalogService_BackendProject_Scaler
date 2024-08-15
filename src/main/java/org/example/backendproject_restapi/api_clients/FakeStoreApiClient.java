package org.example.backendproject_restapi.api_clients;

import org.example.backendproject_restapi.dtos.CategoryDto;
import org.example.backendproject_restapi.dtos.FakeStoreProductDto;
import org.example.backendproject_restapi.dtos.ProductDto;
import org.example.backendproject_restapi.utils.UrlParams;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FakeStoreApiClient extends ApiClient {
    public FakeStoreApiClient() {
        this.setBaseUrl("https://fakestoreapi.com");
    }

    public ProductDto getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto = this.requestForEntityObject(
                "/products/{id}",
                HttpMethod.GET,
                null,
                FakeStoreProductDto.class,
                id
        );
        return FakeStoreProductDto.toProductDto(fakeStoreProductDto);
    }

    public List<ProductDto> getAllProducts(String sort, int limit) {
        UrlParams urlParams = new UrlParams().add("sort", sort).add("limit", String.valueOf(limit));
        String url = "/products" + urlParams.toString();
        FakeStoreProductDto[] fakeStoreProductDtos = this.requestForEntityObject(
                url,
                HttpMethod.GET,
                null,
                FakeStoreProductDto[].class
        );
        return Arrays.stream(fakeStoreProductDtos)
                .map(FakeStoreProductDto::toProductDto)
                .collect(Collectors.toList());
    }

    public ProductDto createProduct(ProductDto productDto) {
        FakeStoreProductDto fakeStoreProductDto = FakeStoreProductDto.toFakeStoreProductDto(productDto);
        FakeStoreProductDto fakeStoreProductDtoResponse = this.requestForEntityObject(
                "/products",
                HttpMethod.POST,
                fakeStoreProductDto,
                FakeStoreProductDto.class
        );
        return FakeStoreProductDto.toProductDto(fakeStoreProductDtoResponse);
    }

    public ProductDto replaceProduct(ProductDto productDto, Long id) {
        FakeStoreProductDto fakeStoreProductDto = FakeStoreProductDto.toFakeStoreProductDto(productDto);
        FakeStoreProductDto fakeStoreProductDtoResponse = this.requestForEntityObject(
                "/products/{id}",
                HttpMethod.PUT,
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                id
        );
        return FakeStoreProductDto.toProductDto(fakeStoreProductDtoResponse);
    }

    public ProductDto updateProduct(ProductDto productDto, Long id) {
        FakeStoreProductDto fakeStoreProductDto = FakeStoreProductDto.toFakeStoreProductDto(productDto);
        FakeStoreProductDto fakeStoreProductDtoResponse = this.requestForEntityObject(
                "/products/{id}",
                HttpMethod.PATCH,
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                id
        );
        return FakeStoreProductDto.toProductDto(fakeStoreProductDtoResponse);
    }

    public ProductDto deleteProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto = this.requestForEntityObject(
                "/products/{id}",
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class,
                id
        );
        return FakeStoreProductDto.toProductDto(fakeStoreProductDto);
    }

    public List<CategoryDto> getAllCategories() {
        return null;
    }

    public List<ProductDto> getProductsByCategoryName(String categoryName) {
        FakeStoreProductDto[] fakeStoreProductDtos = this.requestForEntityObject(
                "/products/category/{categoryName}",
                HttpMethod.GET,
                null,
                FakeStoreProductDto[].class,
                categoryName
        );
        return Arrays.stream(fakeStoreProductDtos)
                .map(FakeStoreProductDto::toProductDto)
                .collect(Collectors.toList());
    }
}
