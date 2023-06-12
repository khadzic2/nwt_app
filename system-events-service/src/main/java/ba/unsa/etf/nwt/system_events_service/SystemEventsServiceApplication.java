package ba.unsa.etf.nwt.system_events_service;

import ba.unsa.etf.nwt.system_events_service.repositories.ActionRepository;
import ba.unsa.etf.nwt.system_events_service.services.ActionService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;


@SpringBootApplication
public class SystemEventsServiceApplication implements ApplicationRunner, ApplicationContextAware {
	private ActionRepository actionRepository;
	public static void main(String[] args) {

		SpringApplication.run(SystemEventsServiceApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		Server server = ServerBuilder.forPort(9090).addService(new ActionService(actionRepository)).build();
		server.start();
		server.awaitTermination();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		actionRepository=applicationContext.getBean(ActionRepository.class);
	}
}
