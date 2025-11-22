import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Car> cars = new ArrayList<>();
        Rase race = new Rase();

        for (int i = 0; i < 3; i++) {
            System.out.println("Введите название " + (i + 1) + "-го автомобиля: ");
            String name = input.nextLine();

            boolean nameExists = false;
            for (Car car : cars) {
                if (car != null && car.name.equalsIgnoreCase(name)) {
                    nameExists = true;
                    break;
                }
            }

            if (!name.isEmpty() && !nameExists) {
                int speed;
                while (true) {
                    System.out.println("Введите скорость " + (i + 1) + "-го автомобиля: ");
                    if (input.hasNextInt()) {
                        speed = input.nextInt();
                        input.nextLine();

                        if (speed >= 0 && speed <= 250) {
                            break;
                        } else {
                            System.out.println("Скорость должна быть от 0 до 250. Попробуйте еще раз.");
                        }
                    } else {
                        System.out.println("Ошибка: введите целое число для скорости.");
                        input.nextLine();
                    }
                }
                Car newCar = new Car(name, speed);
                cars.add(newCar);
                race.determineNewLeader(newCar);
            } else {
                System.out.println("Название автомобиля не может быть пустым или дублироваться. Попробуйте еще раз.");
                i--;
            }
        }

        System.out.println("\nСписок добавленных автомобилей:");
        for (Car car : cars) {
            System.out.println("Название: " + car.name + ", Скорость: " + car.speed);
        }

        System.out.println("\nПобедитель гонки: " + race.winner);
    }
}

class Car {
   String name;
   int speed;

   public Car(String name, int speed) {
       this.name = name;
       this.speed = speed;
   }
}

class Rase {
    String winner = "";
    int maxDistance = 0;

    public void determineNewLeader(Car newCar) {
        int newCarDistance = 24 * newCar.speed;
        if (newCarDistance > this.maxDistance) {
            this.maxDistance = newCarDistance;
            this.winner = newCar.name;
        }
    }
}
