import java.util.*;
import java.util.stream.Collectors;

class Laptop {
    String brand;
    int ram;
    int hdd;
    String os;
    String color;

    public Laptop(String brand, int ram, int hdd, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class HW {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Baikal", 16, 256, "AstraLinux", "Black"));
        laptops.add(new Laptop("Asus", 16, 512, "Windows", "Black"));
        laptops.add(new Laptop("Kal", 16, 512, "Shindows", "Product Red"));
        laptops.add(new Laptop("Macbook air 2015", 4, 256, "MacOS", "White"));
        laptops.add(new Laptop("Lenovo", 32, 1024, "Windows", "Gray"));
        laptops.add(new Laptop("Alienware", 64, 2048, "Windows", "Blue"));

        Map<String, Object> filters = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите критерии для фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("0 - Завершить выбор");

        int choice;
        while (true) {
            choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1:
                    System.out.println("ОЗУ (от)");
                    filters.put("ram", scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Диск (от)");
                    filters.put("hdd", scanner.nextInt());
                    break;
                case 3:
                    System.out.println("ОС");
                    filters.put("os", scanner.next());
                    break;
                case 4:
                    System.out.println("Цвет");
                    filters.put("color", scanner.next());
                    break;
                default:
                    System.out.println("Ошибка: 0000000000000000000000000000000000000000000");
                    System.out.println("Это же очевидно, как ее решить!");
                    System.out.println("Пришло время переустанавливать шиндовс!");
            }
        }

        Set<Laptop> filteredLaptops = laptops.stream()
                .filter(laptop -> filters.getOrDefault("ram", 0) instanceof Integer && laptop.ram >= (int) filters.getOrDefault("ram", 0))
                .filter(laptop -> filters.getOrDefault("hdd", 0) instanceof Integer && laptop.hdd >= (int) filters.getOrDefault("hdd", 0))
                .filter(laptop -> filters.getOrDefault("os", "").equals("") || laptop.os.equalsIgnoreCase((String) filters.getOrDefault("os", "")))
                .filter(laptop -> filters.getOrDefault("color", "").equals("") || laptop.color.equalsIgnoreCase((String) filters.getOrDefault("color", "")))
                .collect(Collectors.toSet());

        System.out.println("Вам подходят:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }
}