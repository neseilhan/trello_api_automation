package neseilhan.dev.utils;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import neseilhan.dev.config.ConfigManager;

public class ApiUtils {
    private static final ConfigManager config = ConfigManager.getInstance();

    static {
        RestAssured.baseURI = config.getBaseUrl();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    /**
     * JSON içerik tipi ile temel bir RestAssured isteği oluşturur.
     */
    public static RequestSpecification getBaseRequest() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }
    /**
     * API anahtarı (key) ve token eklenmiş, doğrulamalı bir istek döner.
     */
    public static RequestSpecification getAuthenticatedRequest() {
        return getBaseRequest()
                .queryParam("key", config.getApiKey())
                .queryParam("token", config.getApiToken());
    }
    /**
     * API yanıtını loglayarak konsola durum kodu, süre ve body bilgisi yazar.
     */
    public static void logResponse(Response response) {
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Time: " + response.getTime() + "ms");
        System.out.println("Response Body: " + response.getBody().asString());
    }
    /**
     * API yanıtının beklenen durum kodunu döndürdüğünü doğrular.
     */
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        if (response.getStatusCode() != expectedStatusCode) {
            throw new AssertionError(
                    String.format("Expected status code %d but got %d. Response: %s",
                            expectedStatusCode, response.getStatusCode(), response.getBody().asString())
            );
        }
    }

}

