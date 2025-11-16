package tests;

import io.restassured.response.Response;
import neseilhan.dev.entities.Board;
import neseilhan.dev.entities.Card;
import neseilhan.dev.entities.List;
import neseilhan.dev.utils.RandomUtils;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrelloApiTest extends tests.BaseTest {

    private String boardId;
    private String listId;
    private String card1Id;
    private String card2Id;
    private java.util.List<Board> boards = new ArrayList<>();

    @Test
    @Order(1)
    @DisplayName("Create a new board")
    public void createBoardTest() {
        logTestStep("1. Trello üzerinde bir board oluşturuluyor");

        String boardName = RandomUtils.generateRandomBoardName();
        logTestInfo("Board Name: " + boardName);

        Response response = boardPage.createBoard(boardName);

        Assertions.assertEquals(200, response.getStatusCode(), "Board oluşturma başarısız!");

        Board createdBoard = boardPage.extractBoardFromResponse(response);
        boardId = createdBoard.getId();

        Assertions.assertNotNull(boardId, "Board ID null olamaz!");
        Assertions.assertEquals(boardName, createdBoard.getName(), "Board ismi eşleşmiyor!");

        logTestSuccess("Board başarıyla oluşturuldu. Board ID: " + boardId);
    }

    @Test
    @Order(2)
    @DisplayName("Board'da listeleri oluşturma ve ilk listeyi alma")
    public void createListInBoardTest() {
        logTestStep("2. Oluşturulan board'da bir liste oluşturuluyor");

        String listName = RandomUtils.generateRandomListName();
        logTestInfo("Liste Name: " + listName);

        Response response = listPage.createList(listName, boardId);

        Assertions.assertEquals(200, response.getStatusCode(), "Liste oluşturma başarısız!");

        List list = listPage.extractListIdFromResponse(response);
        listId = list.getId();

        Assertions.assertNotNull(listId, "Liste ID null olamaz!");
        Assertions.assertEquals(listName, list.getName(), "Liste ismi eşleşmiyor!");

        logTestSuccess("Liste başarıyla oluşturuldu. Liste ID: " + listId + ", Board ID: " + boardId);
    }

    @Test
    @Order(3)
    @DisplayName("Create 2 Cards in Board")
    public void createTwoCardsTest() {
        logTestStep("3. Board'da iki kart oluşturuluyor");

        String card1Name = RandomUtils.generateRandomCardName();
        Response response1 = cardPage.createCard(card1Name, listId);

        Assertions.assertEquals(200, response1.getStatusCode(), "İlk kart oluşturma başarısız!");

        Card card1 = cardPage.extractCardFromResponse(response1);
        card1Id = card1.getId();
        logTestInfo("İlk kart oluşturuldu: " + card1Name + " (ID: " + card1Id + ")");

        String card2Name = RandomUtils.generateRandomCardName();
        Response response2 = cardPage.createCard(card2Name, listId);

        Assertions.assertEquals(200, response2.getStatusCode(), "İkinci kart oluşturma başarısız!");

        Card card2 = cardPage.extractCardFromResponse(response2);
        card2Id = card2.getId();

        logTestInfo("İkinci kart oluşturuldu: " + card2Name + " (ID: " + card2Id + ")");

        Assertions.assertEquals(boardId, card1.getBoardId(), "İlk kart yanlış board'da!");
        Assertions.assertEquals(listId, card1.getListId(), "İlk kart yanlış listede!");

        Assertions.assertEquals(boardId, card2.getBoardId(), "İkinci kart yanlış board'da!");
        Assertions.assertEquals(listId, card2.getListId(), "İkinci kart yanlış listede!");

        logTestSuccess("İki kart başarıyla oluşturuldu");
    }

    @Test
    @Order(4)
    @DisplayName("UpdateRandomCard")
    public void updateRandomCardTest() {
        logTestStep("4. Kartlardan biri rastgele güncelleniyor");

        Response response = listPage.getCardsInAList(listId);
        Assertions.assertEquals(200, response.getStatusCode(), "Kartlar alınamadı!");

        java.util.List<Card> cards = response.jsonPath().getList("$", Card.class);
        Assertions.assertTrue(cards.size() >= 2, "En az iki kart bulunamadı!");

        Card randomCard = RandomUtils.getRandomElement(cards);
        String newCardName = RandomUtils.generateUpdatedCardName();

        Response updateResponse = cardPage.updateCard(randomCard.getId(), newCardName);
        Assertions.assertEquals(200, updateResponse.getStatusCode(), "Kart güncelleme başarısız!");

        Card updatedCard = cardPage.extractCardFromResponse(updateResponse);
        Assertions.assertEquals(newCardName, updatedCard.getName(), "Kart ismi güncellenemedi!");

        logTestSuccess("Kart başarıyla güncellendi: " + updatedCard.getName() + " (ID: " + updatedCard.getId() + ")");
    }

    @Test
    @Order(5)
    @DisplayName("GetCustomerBoards")
    public void getBoardsOfCustomerTest() {
        logTestStep("5. Müşterinin board'ları alınıyor");

        Response response = boardPage.getCustomerBoards();

        Assertions.assertEquals(200, response.getStatusCode(), "Müşteri board'ları alınamadı!");

        Assertions.assertTrue(response.jsonPath().getList("$").size() > 0, "Müşteri board listesi boş!");

        logTestSuccess("Müşteri board'ları başarıyla alındı");

        for (Board board : response.jsonPath().getList("$", Board.class)) {
            logTestInfo("Board ID: " + board.getId() + ", Name: " + board.getName());
            Assertions.assertNotNull(board.getId(), "Board ID null olamaz!");
            boards.add(board);
        }
    }

    @Test
    @Order(6)
    @DisplayName("Delete Board")
    public void deleteBoardsTest() {
        logTestStep("6. Oluşturulan board siliniyor");

        Response response = boardPage.deleteBoard(boardId);

        Assertions.assertEquals(200, response.getStatusCode(), "Board silme başarısız!");

        boards.remove(boards.stream()
                .filter(board -> board.getId().equals(boardId))
                .findFirst()
                .orElse(null));

        logTestInfo("Tüm Boardlar . " + boards.toString());
        logTestSuccess("Board başarıyla silindi");
    }

    @Test
    @Order(7)
    @DisplayName("Delete All Boards")
    public void deleteAllBoardsTest() {

        logTestStep("7.Tüm Boardlar siliniyor: ");
        if (boards.isEmpty()) {
            logTestInfo("Silinecek board bulunamadı.");
            return;
        }

        for (Board board : boards) {
            logTestInfo("Siliniyor: " + board.getName() + " (ID: " + board.getId() + ")");
            Response response = boardPage.deleteBoard(board.getId());
            Assertions.assertEquals(200, response.getStatusCode(), "Board silme başarısız: " + board.getName());
            logTestSuccess("Board başarıyla silindi: " + board.getName());
            response = null;
        }
    }
}
