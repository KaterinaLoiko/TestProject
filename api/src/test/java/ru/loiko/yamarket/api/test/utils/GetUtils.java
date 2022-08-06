package ru.loiko.yamarket.api.test.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetUtils {

    public static Response getSearchResult(String url, String cvredirect, String text) {
        return given()
                .contentType(ContentType.HTML)
                .baseUri(url)
                .basePath("search")
                .queryParam("text", text)
                .queryParam("cvredirect", cvredirect)
                .get();
    }

}
