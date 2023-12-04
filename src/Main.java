import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String LOG_FILE_PATH = "C:\\Users\\Ho Ho Ho\\Downloads\\demo (2)\\DZ NOMER 5\\src\\Sasha.txt";

    public static void main(String[] args) {
        // Створення пустого файлу log в директорії
        createLogFile();

        // Запис фрагмента тексту з різним префіксом в файл
        writeLogMessage("INFO", "Starting JuniorCrudServiceApplication using Java 17.0.7");
        writeLogMessage("DEBUG", "User admin created");

        // Читання всіх лог-повідомлень з файлу
        readAllLogMessages();

        // Параметр методу "лог левел"
        String logLevelToFind = "INFO";
        // Вивід тільки повідомлень вказаного лог-рівня
        findAndPrintLogMessagesByLevel(logLevelToFind);
    }

    private static void createLogFile() {
        try {
            File logFile = new File(LOG_FILE_PATH);
            if (logFile.createNewFile()) {
                System.out.println("Файл log створений успішно.");
            } else {
                System.out.println("Файл log вже існує.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeLogMessage(String level, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(level + ": " + message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readAllLogMessages() {
        try (Scanner scanner = new Scanner(new File(LOG_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String logMessage = scanner.nextLine();
                System.out.println(logMessage);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void findAndPrintLogMessagesByLevel(String targetLevel) {
        try (Scanner scanner = new Scanner(new File(LOG_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String logMessage = scanner.nextLine();
                if (logMessage.startsWith(targetLevel + ":")) {
                    System.out.println(logMessage);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}