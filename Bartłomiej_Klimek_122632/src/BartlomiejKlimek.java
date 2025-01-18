import java.util.*;

public class BartlomiejKlimek {
    // Tablica słów do odgadnięcia
    private static final String[] WORDS = {
            "java", "programowanie", "komputer", "klawiatura", "monitor", "mysz", "drukarka",
            "internet", "sieć", "oprogramowanie", "aplikacja", "algorytm", "baza", "dane",
            "serwer", "chmura", "kod", "debugowanie", "testowanie", "kompilator", "interpreter",
            "modem", "router", "karta", "pamięć", "procesor", "zasilacz", "windows", "linux",
            "macos", "grafika", "multimedia", "animacja", "wideo", "audio", "bluetooth",
            "wi-fi", "ethernet", "projekcja", "program", "informatyka", "telekomunikacja",
            "sieci", "protokół", "transfer", "plik", "format", "system", "komunikacja"
    };

    private static final List<String> wordHistory = new ArrayList<>(); // Historia słów
    private static final Random RANDOM = new Random(); // Obiekt Random do losowego wybierania słowa
    private String wordToGuess; // Słowo do odgadnięcia
    private Set<Character> guessedLetters = new HashSet<>(); // Zestaw liter, które zostały już odgadnięte
    private int wrongGuesses; // Licznik błędnych zgadywań
    private int maxWrongGuesses; // Maksymalna liczba błędnych zgadywań zależna od trudności

    public static void main(String[] args) {
        BartlomiejKlimek game = new BartlomiejKlimek(); // Tworzenie instancji gry
        game.showMainMenu(); // Wyświetlanie menu głównego
    }

    // Funkcja wyświetlająca menu główne
    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            clearConsole(); // Czyszczenie konsoli
            System.out.println("Bartłomiej Klimek 122632");
            System.out.println("MENU GŁÓWNE");
            System.out.println("1. Graj");
            System.out.println("2. Historia słów");
            System.out.println("3. Zakończ program");
            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    start(); // Rozpoczęcie gry
                    break;
                case 2:
                    showWordHistory(); // Wyświetlanie historii słów
                    break;
                case 3:
                    System.out.println("Do widzenia!");
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
            }
        }
    }

    // Funkcja czyszcząca konsolę
    private void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    // Funkcja rozpoczynająca grę
    public void start() {
        Scanner scanner = new Scanner(System.in);

        // Wybór trudności
        clearConsole(); // Czyszczenie konsoli
        System.out.println("Bartłomiej Klimek 122632");
        System.out.println("Wybierz poziom trudności: ");
        System.out.println("1. Łatwy (8 błędów)");
        System.out.println("2. Średni (6 błędów)");
        System.out.println("3. Trudny (4 błędy)");
        int difficulty = scanner.nextInt();

        switch (difficulty) {
            case 1:
                maxWrongGuesses = 8;
                break;
            case 2:
                maxWrongGuesses = 6;
                break;
            case 3:
                maxWrongGuesses = 4;
                break;
            default:
                System.out.println("Nieprawidłowy wybór. Ustawiono poziom Średni.");
                maxWrongGuesses = 6;
        }

        wordToGuess = WORDS[RANDOM.nextInt(WORDS.length)]; // Losowe wybieranie słowa
        wordHistory.add(wordToGuess); // Dodanie słowa do historii
        guessedLetters.clear(); // Czyszczenie zestawu odgadniętych liter
        wrongGuesses = 0; // Resetowanie licznika błędnych zgadywań

        // Główna pętla gry
        while (true) {
            clearConsole(); // Czyszczenie konsoli
            printCurrentState(); // Wyświetlanie aktualnego stanu gry
            if (isGameOver()) break; // Sprawdzanie, czy gra się skończyła
            System.out.print("Zgadnij literę: ");
            char guessedLetter = scanner.next().charAt(0); // Odczytanie litery od użytkownika
            if (!Character.isLetter(guessedLetter)) {
                System.out.println("Podaj literę!"); // Sprawdzenie, czy podany znak jest literą
                continue;
            }
            if (guessedLetters.contains(guessedLetter)) {
                System.out.println("Już zgadywałeś tę literę!"); // Sprawdzenie, czy litera była już zgadywana
                continue;
            }
            guessedLetters.add(guessedLetter); // Dodanie litery do zestawu odgadniętych liter
            if (wordToGuess.contains(String.valueOf(guessedLetter))) {
                System.out.println("Dobrze zgadłeś!"); // Wyświetlanie komunikatu o poprawnym zgadnięciu
            } else {
                System.out.println("Źle zgadłeś!"); // Wyświetlanie komunikatu o błędnym zgadnięciu
                wrongGuesses++; // Zwiększenie licznika błędnych zgadywań, jeśli litera nie jest w słowie
            }
        }
        endGameMenu(); // Wyświetlenie menu końcowego
    }

    // Funkcja wyświetlająca menu końcowe
    private void endGameMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            clearConsole(); // Czyszczenie konsoli
            System.out.println("Bartłomiej Klimek 122632");
            System.out.println("Koniec gry! Słowo to: " + wordToGuess);
            System.out.println("1. Jeszcze raz");
            System.out.println("2. Menu główne");
            System.out.println("3. Koniec");
            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    start(); // Rozpoczęcie nowej gry
                    return;
                case 2:
                    showMainMenu(); // Powrót do menu głównego
                    return;
                case 3:
                    System.out.println("Do widzenia!");
                    System.exit(0); // Zakończenie programu
                default:
                    System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
            }
        }
    }

    // Funkcja wyświetlająca historię słów
    private void showWordHistory() {
        clearConsole(); // Czyszczenie konsoli
        System.out.println("Bartłomiej Klimek 122632");
        if (wordHistory.isEmpty()) {
            System.out.println("Brak historii słów.");
        } else {
            System.out.println("Historia słów:");
            for (String word : wordHistory) {
                System.out.println(word);
            }
        }
        System.out.println("Naciśnij Enter, aby wrócić do menu głównego.");
        new Scanner(System.in).nextLine(); // Czekanie na naciśnięcie Enter
    }

    // Funkcja wyświetlająca aktualny stan gry
    private void printCurrentState() {
        System.out.println("Bartłomiej Klimek 122632");
        System.out.print("Aktualny stan: ");
        for (char letter : wordToGuess.toCharArray()) {
            if (guessedLetters.contains(letter)) {
                System.out.print(letter + " "); // Wyświetlanie odgadniętej litery
            } else {
                System.out.print("_ "); // Wyświetlanie podkreślenia dla nieodgadniętej litery
            }
        }
        System.out.println("\nBłędne zgadywania: " + wrongGuesses); // Wyświetlanie liczby błędnych zgadywań
    }

    // Funkcja sprawdzająca, czy gra się skończyła
    private boolean isGameOver() {
        if (wrongGuesses >= maxWrongGuesses) {
            System.out.println("Przegrałeś!"); // Sprawdzenie, czy gracz przegrał
            return true;
        }
        for (char letter : wordToGuess.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false; // Sprawdzenie, czy są jeszcze nieodgadnięte litery
            }

        }
        System.out.println("Wygrałeś!"); // Sprawdzenie, czy gracz wygrał
        return true;
    }
}