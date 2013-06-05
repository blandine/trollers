import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.deploy.Verticle;

public class Server extends Verticle {

    public void start() {
    	System.out.println("start server");
        HttpServer server = vertx.createHttpServer();

        RouteMatcher rm = new RouteMatcher();
        
        rm.getWithRegEx(".*\\.(js|css)", new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {
            	String fileName = req.path;
            	System.out.println("JS request: " + fileName);
            	req.response.sendFile(fileName.substring(1));
            }
        });
        
        rm.getWithRegEx("/images/.*", new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {
            	String fileName = req.path;
            	req.response.sendFile(fileName.substring(1));
            }
        });
        
        rm.get("/", new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader("index.html"));
                    StringBuilder sb = new StringBuilder();
                    String line = reader.readLine();
                    while (line != null) {
                        sb.append(line);
                        line = reader.readLine();
                    }
                    req.response.putHeader("Content-Type", "text/html");
                    req.response.end(sb.toString());

                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        
        rm.get("/trollers", new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader("resources/example.json"));
                    StringBuilder sb = new StringBuilder();
                    String line = reader.readLine();
                    while (line != null) {
                        sb.append(line);
                        line = reader.readLine();
                    }
                    req.response.putHeader("Content-Type", "application/json");
                    req.response.end(sb.toString());

                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        
        server.requestHandler(rm).listen(8080, "localhost");
    }
}