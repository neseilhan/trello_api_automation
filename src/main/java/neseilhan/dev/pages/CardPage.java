package neseilhan.dev.pages;

import io.restassured.response.Response;
import neseilhan.dev.config.ApiEndpoints;
import neseilhan.dev.entities.Card;
import neseilhan.dev.utils.ApiUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardPage extends BasePage{

    public Response createCard(String cardName, String listId) {
        logInfo("Creating card with name: " + cardName + " in list: " + listId);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", cardName);
        requestBody.put("idList", listId);

        Response response = getRequest()
                .body(requestBody)
                .post(ApiEndpoints.CARDS); // Sadece endpoint

        logResponse(response);
        return response;
    }

//    public Response createCard(Card card) {
//        return createCard(card.getName(), card.getListId());
//    }

    public Response getCardById(String cardId) {
        logInfo("Getting card with ID: " + cardId);

        Response response = getRequest()
                .pathParam("id", cardId)
                .get(ApiEndpoints.CARD_BY_ID);

        logResponse(response);
        return response;
    }

    public Response updateCard(String cardId, String newName) {
        logInfo("Updating card " + cardId + " with new name: " + newName);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", newName);

        Response response = getRequest()
                .pathParam("id", cardId)
                .body(requestBody)
                .put(ApiEndpoints.CARD_BY_ID);

        logResponse(response);
        return response;
    }

    public Response updateCard(String cardId, String newName, String newDescription) {
        logInfo("Updating card " + cardId + " with name: " + newName + " and description: " + newDescription);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", newName);
        requestBody.put("desc", newDescription);

        Response response = getRequest()
                .pathParam("id", cardId)
                .body(requestBody)
                .put(ApiEndpoints.CARD_BY_ID);

        logResponse(response);
        return response;
    }

    public Response deleteCard(String cardId) {
        logInfo("Deleting card with ID: " + cardId);

        Response response = getRequest()
                .pathParam("id", cardId)
                .delete(ApiEndpoints.CARD_BY_ID);

        logResponse(response);
        return response;
    }

    public Card extractCardFromResponse(Response response) {
        validateResponse(response, 200);
        return response.as(Card.class);
    }

    public List<Card> extractCardListFromResponse(Response response) {
        validateResponse(response, 200);
        return response.jsonPath().getList("", Card.class);
    }
}
