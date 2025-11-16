package tests;

import neseilhan.dev.config.ConfigManager;
import neseilhan.dev.pages.BoardPage;
import neseilhan.dev.pages.CardPage;
import neseilhan.dev.pages.ListPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected ConfigManager config;
    protected BoardPage boardPage;
    protected CardPage cardPage;
    protected ListPage listPage;
    private static final Logger logger = LogManager.getLogger();

    @BeforeAll
    public static void setUpClass() {
        ConfigManager config = ConfigManager.getInstance();
        BoardPage boardPage = new BoardPage();
        CardPage cardPage = new CardPage();
        ListPage listPage = new ListPage();

        System.out.println("=== Test Suite Started ===");
        System.out.println("Base URL: " + config.getBaseUrl());
        logger.info("=== Test Suite Started ===");
        logger.info("Base URL: " + config.getBaseUrl());
    }

    @BeforeEach
    public void setUp() {
        config = ConfigManager.getInstance();
        boardPage = new BoardPage();
        cardPage = new CardPage();
        listPage = new ListPage();

        System.out.println("\n--- Test Method Started ---");
        logger.info("\n--- Test Method Started ---");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("--- Test Method Completed ---\n");
        logger.info("--- Test Method Completed ---\n");
    }

    protected void logTestStep(String step) {
        System.out.println("🔸 " + step);
        logger.info("🔸 " + step);
    }

    protected void logTestInfo(String info) {
        System.out.println("ℹ️  " + info);
        logger.info("ℹ️  " + info);
    }

    protected void logTestSuccess(String message) {
        System.out.println("✅ " + message);
        logger.info("✅ " + message);
    }

    protected void logTestError(String error) {
        System.err.println("❌ " + error);
        logger.error("❌ " + error);
    }
}
