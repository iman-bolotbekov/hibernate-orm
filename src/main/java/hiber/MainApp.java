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
      CarService carService = context.getBean(CarService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      Car car1 = new Car("BMW", 1234);
      Car car2 = new Car("Volvo", 3456);
      Car car3 = new Car("Toyota", 2345);
      Car car4 = new Car("Mercedes", 6453);
      Car car5 = new Car("Lada", 5335);
      Car car6 = new Car("Lexus", 3543);
      carService.add(car1);
      carService.add(car2);
      carService.add(car3);
      carService.add(car4);
      carService.add(car5);
      carService.add(car6);

      carService.setOwner(1, user1);
      carService.setOwner(2, user2);
      carService.setOwner(3, user3);
      carService.setOwner(4, user4);

      List<Car> cars = carService.listCars();
      for (Car car : cars) {
         System.out.println(car);
      }

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }


      System.out.println(userService.getUserByCarModelAndCarSeries("BMW", 1234));
      System.out.println(userService.getUserByCarModelAndCarSeries("Mercedes", 6453));
      context.close();
   }
}
