package vertise;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }

    @Override
    public void start() {
        // Utwórz obiekt routera
        Router router = Router.router(vertx);

        // Dodaj obsługę żądania GET na ścieżce /hello
        router.get("/hello").handler(routingContext -> {
            // Obsługa żądania - w tym przypadku zwracamy "Hello, World!"
            routingContext.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello, World!");
        });

        // Utwórz serwer HTTP i przypisz do niego router
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(router).listen(8080); // Serwer nasłuchuje na porcie 8080
    }
}
