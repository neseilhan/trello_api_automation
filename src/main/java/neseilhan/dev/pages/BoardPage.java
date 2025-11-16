package neseilhan.dev.pages;

import io.restassured.response.Response;
import neseilhan.dev.config.ApiEndpoints;
import neseilhan.dev.entities.Board;
import neseilhan.dev.utils.ApiUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardPage extends BasePage {

    public Response createBoard(String boardName) {
        logInfo("Creating board with name: " + boardName);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", boardName);

        Response response = getRequest()
                .body(requestBody)
                .post(ApiEndpoints.BOARDS);

        logResponse(response);
        return response;
    }
    public Response createBoard(Board board) {
        return createBoard(board.getName());
    }

    public Response getBoardById(String boardId) {
        logInfo("Getting board with ID: " + boardId);

        Response response = ApiUtils.getAuthenticatedRequest()
                .pathParam("id", boardId)
                .get(ApiEndpoints.BOARD_BY_ID);

        logResponse(response);
        return response;
    }

    public Response getCustomerBoards() {
        logInfo("Getting customer board");

        Response response = ApiUtils.getAuthenticatedRequest()
                .get(ApiEndpoints.GET_CUSTOMER_BOARD);

        logResponse(response);
        return response;
    }

    public Response getBoardCards(String boardId) {
        logInfo("Getting cards for board ID: " + boardId);

        Response response = ApiUtils.getAuthenticatedRequest()
                .pathParam("id", boardId)
                .get(ApiEndpoints.BOARD_CARDS);

        logResponse(response);
        return response;
    }

    public Response getBoardLists(String boardId) {
        logInfo("Getting lists for board ID: " + boardId);

        Response response = ApiUtils.getAuthenticatedRequest()
                .pathParam("id", boardId)
                .get(ApiEndpoints.BOARD_LISTS);

        logResponse(response);
        return response;
    }

    public Response deleteBoard(String boardId) {
        logInfo("Deleting board with ID: " + boardId);

        Response response = ApiUtils.getAuthenticatedRequest()
                .pathParam("id", boardId)
                .delete(ApiEndpoints.BOARD_BY_ID);

        logResponse(response);
        return response;
    }

    public Response updateBoard(String boardId, String newName) {
        logInfo("Updating board " + boardId + " with new name: " + newName);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", newName);

        Response response = ApiUtils.getAuthenticatedRequest()
                .pathParam("id", boardId)
                .body(requestBody)
                .put(ApiEndpoints.BOARD_BY_ID);

        logResponse(response);
        return response;
    }

    public Board extractBoardFromResponse(Response response) {
        System.out.println("EXTRACT RAW: " + response.asString());
        Board b = response.as(Board.class);
        System.out.println("EXTRACTED → id=" + b.getId() + " name=" + b.getName()
                + " org=" + b.getOrganizationId());
        return b;
    }


    public List<Board> extractBoardListFromResponse(Response response) {
        validateResponse(response, 200);
        return response.jsonPath().getList("", Board.class);
    }
}
