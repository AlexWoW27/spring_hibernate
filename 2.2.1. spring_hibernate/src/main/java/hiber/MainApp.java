package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        CarService carService = context.getBean(CarService.class);

        User user5 = new User("UserWithCar1", "LastName11", "user11@mail.ru");
        Car car1 = new Car("Mazda", 6);
        user5.setCar(car1);
        car1.setUser(user5);
        carService.add(car1);

        List<User> users2 = userService.UserWithCar("Mazda",6);
        for(User user : users2) {
            System.out.println(user.toString());
        }
        System.out.println();



        User user6 = new User("UserWithCar2", "LastName22", "user22@mail.ru");
        Car car2 = new Car("BMW", 5);
        user6.setCar(car2);
        car2.setUser(user6);
        carService.add(car2);

        List<User> users3 = userService.UserWithCar("BMW",5);
        for(User user : users3) {
            System.out.println(user.toString());
        }
        System.out.println();


        User user7 = new User("UserWithCar3", "LastName33", "user33@mail.ru");
        Car car3 = new Car("Jiguli", 5);
        user7.setCar(car3);
        car3.setUser(user7);
        carService.add(car3);

        List<User> users4 = userService.UserWithCar("Jiguli",5);
        for(User user : users4) {
            System.out.println(user.toString());
        }
        System.out.println();


        context.close();
    }
}
