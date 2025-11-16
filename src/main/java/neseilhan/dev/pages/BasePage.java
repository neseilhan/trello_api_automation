package neseilhan.dev.pages;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import neseilhan.dev.config.ConfigManager;
import neseilhan.dev.utils.ApiUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage {
    public static final ConfigManager config = ConfigManager.getInstance();
    protected static final Logger logger = LogManager.getLogger(BasePage.class);


    public RequestSpecification getRequest() {
        return ApiUtils.getAuthenticatedRequest();
    }

    public void validateResponse(Response response, int expectedStatusCode) {
        ApiUtils.validateStatusCode(response, expectedStatusCode);
    }

    public void logResponse(Response response) {
        ApiUtils.logResponse(response);
    }

    public void logInfo(String message) {
        System.out.println("[INFO] " + message);
        logger.info("[INFO] " + message);
    }

    public void logError(String message) {
        System.err.println("[ERROR] " + message);
        logger.error("[ERROR] " + message);
    }
}