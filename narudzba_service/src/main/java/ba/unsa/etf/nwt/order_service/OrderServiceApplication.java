package ba.unsa.etf.nwt.order_service;

import ba.unsa.etf.nwt.order_service.model.*;
import ba.unsa.etf.nwt.order_service.repository.DateRepository;
import ba.unsa.etf.nwt.order_service.repository.OrderRepository;
import ba.unsa.etf.nwt.order_service.repository.StateRepository;
import ba.unsa.etf.nwt.order_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(DateRepository dateRepository, OrderRepository orderRepository, StateRepository stateRepository, UserRepository userRepository){
        return args -> {
            Date date1 = new Date(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10));
            Date date2 = new Date(LocalDate.now().plusDays(5), LocalDate.now().plusDays(5));
            Date date3 = new Date(LocalDate.now().plusDays(15), LocalDate.now().plusDays(10));
            Date date4 = new Date(LocalDate.now().plusDays(15), LocalDate.now().plusDays(10));
            Date date5 = new Date(LocalDate.now().plusDays(15), LocalDate.now().plusDays(10));

            dateRepository.save(date1);
            dateRepository.save(date2);
            dateRepository.save(date3);
            dateRepository.save(date4);
            dateRepository.save(date5);

            State state1 = new State(StateType.PREGLED_NARUDZBE.label);
            State state2 = new State(StateType.NABAVKA_MATERIJALA.label);
            State state3 = new State(StateType.MONTIRANJE.label);
            State state4 = new State(StateType.SPREMNO_ZA_ISPORUKU.label);

            stateRepository.save(state1);
            stateRepository.save(state2);
            stateRepository.save(state3);
            stateRepository.save(state4);

            User user = new User("Kanita","Hadzic");
            userRepository.save(user);

            Orders orders1 = new Orders(user,1, date1, state1);
            Orders orders2 = new Orders(user,2, date2, state1);
            Orders orders3 = new Orders(user,3, date3, state2);
            Orders orders4 = new Orders(user,4, date4, state3);
            Orders orders5 = new Orders(user,5, date5, state4);

            orderRepository.save(orders1);
            orderRepository.save(orders2);
            orderRepository.save(orders3);
            orderRepository.save(orders4);
            orderRepository.save(orders5);
        };
    }
}
