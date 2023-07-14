import java.util.*;

public class Task1 {
    private String brand;
    private int ram;
    private int storage;
    private String os;
    private String color;

    public Task1(String brand, int ram, int storage, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getRAM() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOS() {
        return os;
    }

    public String getColor() {
        return color;
    }

    public static List<Task1> filterNotebooks(Set<Task1> notebooks, Map<Integer, String> criteria) {
        List<Task1> filteredNotebooks = new ArrayList<>();

        for (Task1 notebook : notebooks) {
            boolean meetsCriteria = true;

            for (Map.Entry<Integer, String> entry : criteria.entrySet()) {
                int criterion = entry.getKey();
                String value = entry.getValue();

                if (!matchesCriterion(notebook, criterion, value)) {
                    meetsCriteria = false;
                    break;
                }
            }

            if (meetsCriteria) {
                filteredNotebooks.add(notebook);
            }
        }

        return filteredNotebooks;
    }


    private static boolean matchesCriterion(Task1 notebook, int criterion, String value) {
        switch (criterion) {
            case 1:
                return notebook.getRAM() >= Integer.parseInt(value);
            case 2:
                return notebook.getStorage() >= Integer.parseInt(value);
            case 3:
                return notebook.getOS().equalsIgnoreCase(value);
            case 4:
                return notebook.getColor().equalsIgnoreCase(value);
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        Set<Task1> notebooks = new HashSet<>();
        notebooks.add(new Task1("Dell", 8, 500, "Windows", "Черный"));
        notebooks.add(new Task1("HP", 16, 1000, "Windows", "Серебристы"));
        notebooks.add(new Task1("Apple", 16, 1000, "MacOS", "Белый"));

        Scanner scanner = new Scanner(System.in, "Cp866");

        Map<Integer, String> criteria = new HashMap<>();
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ (ГБ)");
        System.out.println("2 - Объем ЖД (ГБ)");
        System.out.println("3 - Операционная система (Windows, MacOS)");
        System.out.println("4 - Цвет");
        System.out.println();

        boolean addMoreCriteria = true;

        while (addMoreCriteria) {
        System.out.print("Введите номер критерия: ");
        int criterion;
        try {
            criterion = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: введите числовое значение критерия.");
            scanner.nextLine();
            continue;
        }
        scanner.nextLine(); 
        System.out.print("Введите значение критерия: ");
        String value = scanner.nextLine();

        if (criterion < 1 || criterion > 4) {
            System.out.println("Ошибка: введите корректное значение критерия.");
            continue;
    }
        criteria.put(criterion, value);

        while (true) {
            System.out.print("Добавить еще один критерий? (да/нет): ");
            String choice = scanner.nextLine();
            System.out.println(choice);

            if (choice.equalsIgnoreCase("да")) {
                break;
            } else if (choice.equalsIgnoreCase("нет")) {
                addMoreCriteria = false;
                break;
            } else {
                System.out.println("Ошибка: введите 'да' или 'нет'.");
            }
        }
    }


        List<Task1> filteredNotebooks = filterNotebooks(notebooks, criteria);

        System.out.println("\nРезультаты фильтрации:");
        if (filteredNotebooks.isEmpty()) {
            System.out.println("Ноутбуки, удовлетворяющие заданным критериям, не найдены.");
        } else {
            for (Task1 notebook : filteredNotebooks) {
                System.out.println("Бренд: " + notebook.getBrand());
                System.out.println("ОЗУ: " + notebook.getRAM() + " ГБ");
                System.out.println("Объем ЖД: " + notebook.getStorage() + " ГБ");
                System.out.println("Операционная система: " + notebook.getOS());
                System.out.println("Цвет: " + notebook.getColor());
                System.out.println();
            }
        }
    }
}