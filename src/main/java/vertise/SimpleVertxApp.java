package vertise;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class SimpleVertxApp extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {
        vertx.createHttpServer()
                .requestHandler(req -> {
                    req.response()
                            .putHeader("content-type", "text/plain")
                            .end("Hello from Vert.x!");
                })
                .listen(8080, http -> {
                    if (http.succeeded()) {
                        startPromise.complete();
                        System.out.println("Server started on port 8080");
                    } else {
                        startPromise.fail(http.cause());
                    }
                });
    }
}
