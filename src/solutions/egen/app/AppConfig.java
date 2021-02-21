package solutions.egen.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class AppConfig extends ResourceConfig {

	public AppConfig() {
		packages("solutions.egen");
	}
}
