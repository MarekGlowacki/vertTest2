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
        // Obsługa zdarzenia - odczyt pliku
        vertx.fileSystem().readFile("nazwa_pliku.txt", result -> {
            if (result.succeeded()) {
                Buffer fileData = result.result();
                // Wykonaj asynchroniczną operację - np. zapis do bazy danych
                vertx.executeBlocking(future -> {
                    // Symulacja długotrwałej operacji
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    future.complete("Wynik długotrwałej operacji");
                }, res -> {
                    if (res.succeeded()) {
                        String operationResult = (String) res.result();
                        // Obsługa wyniku asynchronicznej operacji
                        System.out.println("Wynik operacji: " + operationResult);
                    } else {
                        // Obsługa błędu asynchronicznej operacji
                        System.err.println("Błąd operacji: " + res.cause().getMessage());
                    }
                });
            } else {
                // Obsługa błędu odczytu pliku
                System.err.println("Błąd odczytu pliku: " + result.cause().getMessage());
            }
        });
    }
}
