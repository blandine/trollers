import java.net.URI;
import java.net.URISyntaxException;

import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.deploy.Verticle;

public class Server extends Verticle {

    public void start() {
        RouteMatcher rm = new RouteMatcher();

        try {
            rm.all("/trollers/", new RestHandler(new URI("http://localhost:8080/"),
                "com.example.resources"));
        } catch (URISyntaxException e) {
            // Exceptions are currently not handled by the project.
            e.printStackTrace();
        }

        HttpServer server = vertx.createHttpServer().requestHandler(rm).listen(8080);
    }
}