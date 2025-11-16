package org.example.config;

public class ApiEndpoints {
    // Board endpoints
    // Board endpoints
    public static final String BOARDS = "/boards";
    public static final String BOARD_BY_ID = "/boards/{id}";
    public static final String BOARD_CARDS = "/boards/{id}/cards";
    public static final String GET_CUSTOMER_BOARD = "/members/me/boards?fields=id,name";

    // Card endpoints
    public static final String CARDS = "/cards";
    public static final String CARD_BY_ID = "/cards/{id}";

    // List endpoints
    public static final String BOARD_LISTS = "/boards/{id}/lists";
    public static final String LIST= "/lists";
    public static final String LIST_BY_ID = "/lists/{id}";
    public static final String CARD_LISTS = "/list/{id}/cards";
    // Authentication parameters - Query parameter olarak

    public static final String AUTH_PARAMS = "key={apiKey}&token={apiToken}";
/*
    // Complete endpoints with auth - Query parameter olarak
    public static String getBoardsEndpoint() {
        return BOARDS + "?" + AUTH_PARAMS;
    }
    public static String getCreateListEndpoint(String boardId) {
        return CREATE_LIST+ "&" +AUTH_PARAMS;
    }
    public static String getBoardByIdEndpoint() {
        return BOARD_BY_ID + "?" + AUTH_PARAMS;
    }

    public static String getBoardCardsEndpoint() {
        return BOARD_CARDS + "?" + AUTH_PARAMS;
    }

    public static String getCardsEndpoint() {
        return CARDS + "?" + AUTH_PARAMS;
    }

    public static String getCardByIdEndpoint() {
        return CARD_BY_ID + "?" + AUTH_PARAMS;
    }

    public static String getBoardListsEndpoint() {
        return BOARD_LISTS + "?" + AUTH_PARAMS;
    }

 */

}
