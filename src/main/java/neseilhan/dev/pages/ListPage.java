package neseilhan.dev.pages;

import io.restassured.response.Response;
import neseilhan.dev.config.ApiEndpoints;
import neseilhan.dev.entities.List;

import java.util.HashMap;
import java.util.Map;

public class ListPage extends  BasePage{
    public List extractListIdFromResponse(Response response) {
        if (response.getStatusCode() != 200) {
            logError("Failed to create list. Status code: " + response.getStatusCode());
            return null;
        }

        String listId = response.jsonPath().getString("id");
        String listName = response.jsonPath().getString("name");
        String boardId = response.jsonPath().getString("idBoard");

        List list = new List();
        list.setId(listId);
        list.setName(listName);
        list.setIdBoard(boardId);

        logInfo("List created with ID: " + listId + ", Name: " + listName + ", Board ID: " + boardId);
        return list;

    }

    public Response createList(String listName, String boardId) {
        logInfo("Creating list with name: " + listName + " in board: " + boardId);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", listName);
        requestBody.put("idBoard", boardId);

        Response response = getRequest()
                .body(requestBody)
                .post(ApiEndpoints.LIST);

        logResponse(response);
        return response;
    }

    public Response getCardsInAList(String listId) {
        logInfo("Getting cards for list ID: " + listId);

        Response response = getRequest()
                .pathParam("id", listId)
                .get(ApiEndpoints.CARD_LISTS);

        logResponse(response);
        return response;
    }

}
