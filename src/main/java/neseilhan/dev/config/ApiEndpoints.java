package neseilhan.dev.config;

public class ApiEndpoints {
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

    public static final String AUTH_PARAMS = "key={apiKey}&token={apiToken}";

}
