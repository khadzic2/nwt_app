package ba.unsa.etf.nwt.system_events_service;

import ba.unsa.etf.nwt.system_events_service.services.SystemEventService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@OpenAPIDefinition
@SpringBootApplication
public class SystemEventsServiceApplication implements CommandLineRunner, ApplicationContextAware {

	static
	ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(SystemEventsServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Server server = ServerBuilder
				.forPort(9090)
				.addService(new SystemEventService(applicationContext))
				.build();
		try {
			server.start();
			server.awaitTermination();
		}catch (Exception e){}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
}
