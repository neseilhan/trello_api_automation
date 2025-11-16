package neseilhan.dev.utils;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RandomUtils {
    private static final Random random = new Random();

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }

        return result.toString();
    }

    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    public static <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }
        return list.get(random.nextInt(list.size()));
    }

    public static int getRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static boolean getRandomBoolean() {
        return random.nextBoolean();
    }

    public static String generateRandomBoardName() {
        return "Test Board " + generateRandomString(5) + " " + System.currentTimeMillis();
    }

    public static String generateRandomCardName() {
        return "Test Card " + generateRandomString(5) + " " + System.currentTimeMillis();
    }

    public static String generateUpdatedCardName() {
        return "Updated Card " + generateRandomString(5) + " " + System.currentTimeMillis();
    }

    public static String generateRandomListName() {
        return "Test List " + generateRandomString(5) + " " + System.currentTimeMillis();
    }
}
