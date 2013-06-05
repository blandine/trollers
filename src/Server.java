import java.util.Map;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.deploy.Verticle;

public class Server extends Verticle {

    public void start() {
    	System.out.println("start server");
        HttpServer server = vertx.createHttpServer();

        server.requestHandler(new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest request) {
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> header : request.headers().entrySet()) {
                    sb.append(header.getKey()).append(": ").append(header.getValue()).append("\n");
                }
                request.response.putHeader("content-type", "text/plain");
                request.response.end(sb.toString());
            }
        }).listen(8080, "localhost");
    }
}