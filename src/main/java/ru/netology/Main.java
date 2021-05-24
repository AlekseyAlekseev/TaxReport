package ru.netology;

import java.util.concurrent.atomic.LongAdder;

public class Main {


    /**
     * Объявляем переменную LongAdder
     */
    private static final LongAdder totalAmount = new LongAdder();

    public static void main(String[] args) throws InterruptedException {


        /**
         * Создаем три потока с нашими магазинами
         */
        Thread score1 = new Thread(null, createShop(), "Магазин1");
        Thread score2 = new Thread(null, createShop(), "Магазин2");
        Thread score3 = new Thread(null, createShop(), "Магазин3");


        score1.start();
        score2.start();
        score3.start();

        score1.join();
        score2.join();
        score3.join();


        /**
         * Вывод результата в консоль. Подсчет суммы выручки с трех магазинов.
         */
        System.out.println("Выручка по магазинам: " + score1.getName() + ", "  +
                ", "  + score2.getName() + ", "  + score3.getName() +
                " равна: " + totalAmount.sum() + " рублей.");


    }


    /**
     * При запуске потока генерируем 10 случайных чисел и добавляем их в переменную LongAdder.
     * @return
     */
    public static Runnable createShop () {
        return () -> {
            int countSales = 10;
            long value = (long) (Math.random() * 6666) + 50;
            for (int i = 0; i < countSales; i++) {
                totalAmount.add(value);
            }
            Thread.currentThread().interrupt();
        };
    }
}
