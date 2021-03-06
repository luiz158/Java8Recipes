package chapter7;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Project: Java8Recipes
 * FileName: StreamExample
 * Date: 2017-01-03
 * Time: 오전 8:43
 * Author: user
 * Note:
 * To change this template use File | Settings | File Templates.
 */
public class StreamExample {
    static List<Stock> myStocks = new ArrayList();

    private static void createStocks() {
        myStocks.add(new Stock("ORCL", "Oracle", 500.0));
        myStocks.add(new Stock("AAPL", "Apple", 200.0));
        myStocks.add(new Stock("GOOG", "Google", 100.0));
        myStocks.add(new Stock("IBM", "IBM", 50.0));
        myStocks.add(new Stock("MCD", "McDonalds", 300.0));
    }


    public static void main(String[] args) {
        createStocks();
        myStocks.stream()
                .forEach(s -> System.out.println(s.getName()));
        boolean allGt = myStocks.stream()
                .allMatch(s -> s.getShares() > 100.0);
        System.out.println("All Stocks Greater than 100.0 Shares? " + allGt);
        System.out.println("== We have more than 100 shares of the following:");
        myStocks.stream()
                .filter(s -> s.getShares() > 100.0)
                .forEach(s -> System.out.println(s.getName()));

        System.out.println("== the following stocks are sorted by shares:");
        /*Comparable<Stock> byShares = Comparator.comparing(Stock::getShares);
        Stream<Stock> sortedByShares = myStocks.stream()
                .sorted((Comparator<? super Stock>) byShares);
        sortedByShares.forEach(s -> System.out.println("Stock: " + s.getName() +
                "- Shares: " + s.getShares()));*/

        Optional<Stock> maybe = myStocks.stream().findFirst();
        System.out.println("First Stock:" + maybe.get().getName());

        List newStocks = new ArrayList();
        Optional<Stock> maybeNot = newStocks.stream().findFirst();
        Consumer<Stock> myConsumer = (s) -> {
            System.out.println("First Stock (Optional): " + s.getName());
        };
        maybeNot.ifPresent(myConsumer);
        if (maybeNot.isPresent()) {
            System.out.println(maybeNot.get().getName());
        }
        newStocks.add(new Stock("MCD", "McDonalds", 300.0));
        Optional<Stock> maybeNow = newStocks.stream()
                .findFirst();
        maybeNow.ifPresent(myConsumer);
    }

}
