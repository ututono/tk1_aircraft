
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
 
public class RESTStartUp {
 
    static final String BASE_URI = "http://localhost:9999/calcrest/";
	private static Logger logger = Logger.getLogger(RESTStartUp.class.getName());
	
    public static void main(String[] args) {
        try {
        	ResourceConfig rc = new ResourceConfig().packages("rest");
        	JdkHttpServerFactory.createHttpServer(URI.create(BASE_URI),rc);
        	logger.log(Level.INFO, "REST Server start up succesfully");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
