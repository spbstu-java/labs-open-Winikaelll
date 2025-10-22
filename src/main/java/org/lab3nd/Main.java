package org.lab3nd;

import java.io.*;
import java.util.*;

class InvalidFileFormatException extends Exception {
    public InvalidFileFormatException(String message) {
        super(message);
    }
}

class FileReadException extends Exception {
    public FileReadException(String message) {
        super(message);
    }
}

class LinguaConverter {
    private Map<String, String> lexiconMap;

    public LinguaConverter() {
        this.lexiconMap = new HashMap<>();
    }

    public void loadLexicon(String filePath) throws InvalidFileFormatException, FileReadException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\|", 2);
                if (parts.length != 2) {
                    throw new InvalidFileFormatException(
                            String.format("Невернй формат в строке %d: %s", lineNumber, line)
                    );
                }

                String key = parts[0].trim().toLowerCase();
                String value = parts[1].trim();

                if (key.isEmpty() || value.isEmpty()) {
                    throw new InvalidFileFormatException(
                            String.format("Слово пустое или нет перевода в строке %d", lineNumber)
                    );
                }

                lexiconMap.put(key, value);
            }

        } catch (FileNotFoundException e) {
            throw new FileReadException("Файл не найден: " + filePath);
        } catch (IOException e) {
            throw new FileReadException("Ошибка чтения файла: " + e.getMessage());
        }
    }

    public String convertText(String inputText) {
        if (inputText == null || inputText.trim().isEmpty()) {
            return inputText;
        }

        String[] words = inputText.split("\\s+");
        List<String> resultParts = new ArrayList<>();

        int i = 0;
        while (i < words.length) {
            String longestMatch = findLongestMatch(words, i);
            if (longestMatch != null) {
                resultParts.add(lexiconMap.get(longestMatch));
                i += longestMatch.split("\\s+").length;
            } else {
                resultParts.add(words[i]);
                i++;
            }
        }

        return String.join(" ", resultParts);
    }

    private String findLongestMatch(String[] words, int startIndex) {
        String currentPhrase = "";
        String bestMatch = null;

        for (int i = startIndex; i < words.length; i++) {
            if (!currentPhrase.isEmpty()) {
                currentPhrase += " ";
            }
            currentPhrase += words[i].toLowerCase();

            if (lexiconMap.containsKey(currentPhrase)) {
                bestMatch = currentPhrase;
            }
        }

        return bestMatch;
    }

    public void printLexicon() {
        System.out.println("Текущий словарь:");
        for (Map.Entry<String, String> entry : lexiconMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }
    }
}

public class Main {
    private static String LEXICON_FILE = "lexicon.txt";

    public static void main(String[] args) {
        LinguaConverter converter = new LinguaConverter();

        try {
            converter.loadLexicon(LEXICON_FILE);
            System.out.println("Словарь успешно загружен.");

             //converter.printLexicon();

            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Введите текст для перевода, quit для выхода:");

                while (true) {
                    System.out.print("> ");
                    String userInput = scanner.nextLine().trim();

                    if (userInput.equalsIgnoreCase("quit")) {
                        System.out.println("Выход из программы.");
                        break;
                    }

                    if (userInput.isEmpty()) {
                        continue;
                    }

                    String convertedText = converter.convertText(userInput);
                    System.out.println("Результат - " + convertedText);
                }
            }

        } catch (InvalidFileFormatException | FileReadException e) {
            System.err.println("Ошибка - " + e.getMessage());
        }
    }
}