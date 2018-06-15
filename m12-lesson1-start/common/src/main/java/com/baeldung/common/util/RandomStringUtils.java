package com.baeldung.common.util;

import org.apache.commons.text.RandomStringGenerator;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

public class RandomStringUtils {

    private static RandomStringGenerator randomAlphaNumericGenerator;
    private static RandomStringGenerator randomAlphabeticGenerator;
    private static RandomStringGenerator randomNumericGenerator;

    static {
        randomAlphaNumericGenerator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        randomAlphabeticGenerator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        randomNumericGenerator = new RandomStringGenerator.Builder().withinRange('0', '9').build();
    }

    public static String randomAlphanumeric(final int count) {
        return randomAlphaNumericGenerator.generate(count);
    }

    public static String randomAlphabetic(final int count) {
        return randomAlphabeticGenerator.generate(count);
    }

    public static String randomNumeric(final int count) {
        return randomNumericGenerator.generate(count);
    }
}
