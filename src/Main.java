public class Main {

    // Исключение, выбрасываемое при неверном пользователе
    static class CustomerException extends Exception {
        public CustomerException(String message) {
            super(message);
        }
    }

    // Исключение, выбрасываемое при неверном товаре
    static class ProductException extends Exception {
        public ProductException(String message) {
            super(message);
        }
    }

    // Исключение, выбрасываемое при неверном количестве товара
    static class AmountException extends Exception {
        public AmountException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        // Создание массива покупателей
        Customer[] customers = new Customer[2];
        customers[0] = new Customer("Иванов Иван", 30, "+7(123)456-78-90");
        customers[1] = new Customer("Петров Петр", 25, "+7(987)654-32-10");


        // Создание массива товаров
        Product[] products = new Product[5];
        products[0] = new Product("Телефон", 1000);
        products[1] = new Product("Наушники", 200);
        products[2] = new Product("Флешка", 50);
        products[3] = new Product("Клавиатура", 300);
        products[4] = new Product("Мышка", 150);

        // Создание массива заказов
        Order[] orders = new Order[5];

        // Вызов метода "совершить покупку" несколько раз
        try {
            orders[0] = completePurchase(customers[0], products[0], 3);
            orders[1] = completePurchase(customers[1], products[1], -10);
            orders[2] = completePurchase(customers[0], products[3], 2);
            orders[3] = completePurchase(customers[1], new Product("Ноутбук", 1000), 1);
            orders[4] = completePurchase(new Customer("Сидоров Сидор", 20, "+7(111)222-33-44"), products[2], 5);
        } catch (CustomerException e) {
            System.out.println("Ошибка: неверный покупатель");
        } catch (ProductException e) {
            System.out.println("Ошибка: неверный товар");
        } catch (AmountException e) {
            System.out.println("Ошибка: неверное количество, покупка будет совершена с количеством 1");
            try {
                orders[1] = completePurchase(customers[1], products[1], 1);
            } catch (CustomerException | ProductException ex) {
                // Обработка возможных исключений снова
            } catch (AmountException ex) {
                throw new RuntimeException(ex);
            }
        }

        // Вывод информации о совершенных покупках
        System.out.println("Количествосовершенных покупок: " + countPurchases(orders));



    }
    // Статический метод для совершения покупки
    public static Order completePurchase (Customer customer, Product product, int quantity) throws
            CustomerException, ProductException, AmountException {
        if (!isValidCustomer(customer)) {
            throw new CustomerException("Несуществующий покупатель");
        }

        if (!isValidProduct(product)) {
            throw new ProductException("Несуществующий товар");
        }

        if (quantity <= 0 || quantity > 100) {
            throw new AmountException("Неверное количество товара, покупка будет совершена с количеством 1");
        }

        Order order = new Order(customer, product, quantity);
        return order;
    }
    // Метод для подсчета количества совершенных покупок
    public static int countPurchases (Order[]orders){
        int count = 0;

        for (Order order : orders) {
            if (order != null) {
                count++;
            }
        }

        return count;
    }

    // Метод для проверки валидности покупателя
    public static boolean isValidCustomer (Customer customer){
        // Проверка на null или другие условия валидности покупателя
        return customer != null;
    }

    // Метод для проверки валидности товара
    public static boolean isValidProduct (Product product){
        // Проверка на null или другие условия валидности товара
        return product != null;
    }


}
