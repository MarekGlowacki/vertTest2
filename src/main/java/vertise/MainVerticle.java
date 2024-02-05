package vertise;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;

public class MainVerticle extends AbstractVerticle {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }

    @Override
    public void start() {
        String nazwaPliku = "nazwa_pliku.txt";
        vertx.fileSystem().exists(nazwaPliku, result -> {
            if (result.succeeded() && !result.result()) {
                // Plik nie istnieje, więc go utwórz
                vertx.fileSystem().writeFile(nazwaPliku, Buffer.buffer("Domyślna zawartość pliku"), writeResult -> {
                    if (writeResult.succeeded()) {
                        System.out.println("Utworzono plik: " + nazwaPliku);
                        // Kontynuuj operację odczytu pliku
                        readFileContent(nazwaPliku);
                    } else {
                        System.err.println("Błąd podczas tworzenia pliku: " + writeResult.cause().getMessage());
                    }
                });
            } else if (result.succeeded() && result.result()) {
                // Plik istnieje, więc odczytaj zawartość
                readFileContent(nazwaPliku);
            } else {
                System.err.println("Błąd sprawdzania istnienia pliku: " + result.cause().getMessage());
            }
        });
    }

    private void readFileContent(String nazwaPliku) {
        vertx.fileSystem().readFile(nazwaPliku, readResult -> {
            if (readResult.succeeded()) {
                Buffer fileData = readResult.result();
                // Tutaj możesz kontynuować przetwarzanie danych z pliku
                System.out.println("Zawartość pliku: " + fileData.toString());
            } else {
                System.err.println("Błąd odczytu pliku: " + readResult.cause().getMessage());
            }
        });
    }
}
