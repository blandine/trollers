import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.json.impl.Json;
import org.vertx.java.deploy.Verticle;


public class Server extends Verticle {

    public void start() {
    	final List<Troll> trollers = getData();
    	
    	System.out.println("start server");
        HttpServer server = vertx.createHttpServer();

        RouteMatcher rm = new RouteMatcher();
        
        rm.getWithRegEx(".*\\.(js|css)", new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {
            	String fileName = req.path;
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
                req.response.putHeader("Content-Type", "application/json");
                req.response.end(Json.encode(trollers));
            }
        });
        
        rm.getWithRegEx("/trollers/([a-zA-Z]+)/denounce",  new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {
            	// Extract troll name
            	String trollName = req.path.split("/")[2];
            	
            	Troll matchingTroll = null;
            	for (Troll troll : trollers) {
            		if (troll.getName().equals(trollName)) {
            			matchingTroll = troll;
            		}
            	}
            	if (matchingTroll != null) {
            		matchingTroll.denouce();
            	} else {
            		req.response.statusCode = 404;
            	}
            	req.response.end();
            }
        });
        
        server.requestHandler(rm).listen(8080, "localhost");
    }

    
	private List<Troll> getData() {
		List<Troll> trolls = new LinkedList<Troll>();
		trolls.add(new Troll("NME", 0));
		trolls.add(new Troll("OBE", 0));
		trolls.add(new Troll("BDE", 0));
		trolls.add(new Troll("BCA", 0));
		trolls.add(new Troll("CPR", 0));
		trolls.add(new Troll("EFO", 0));
		trolls.add(new Troll("FMO", 0));
		trolls.add(new Troll("BORE", 0));
		trolls.add(new Troll("EBO", 0));
		trolls.add(new Troll("MBL", 0));
		trolls.add(new Troll("RRO", 0));
		
		return trolls;
	}
}