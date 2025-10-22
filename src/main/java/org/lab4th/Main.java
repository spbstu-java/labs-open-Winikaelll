package org.lab4th;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static OptionalDouble avgAmount(List<Integer> amounts) {
        return Optional.ofNullable(amounts)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .mapToInt(Integer::intValue)
                .average();
    }

    public static List<String> toUpperWithNewPrefix(List<String> inpt) {
        return Optional.ofNullable(inpt)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .filter(Objects::nonNull)
                .map(String::toUpperCase)
                .map(s -> "_new_" + s)
                .collect(Collectors.toList());
    }

    public static List<Integer> squaresOfSingles(List<Integer> inpt) {
        if (inpt == null) return Collections.emptyList();

        return inpt.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1L)
                .map(e -> e.getKey() * e.getKey())
                .collect(Collectors.toList());
    }

    public static <T> T lastOrThrow(Collection<T> col) {
        return Optional.ofNullable(col)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .reduce((first, second) -> second)
                .orElseThrow(() -> new NoSuchElementException("Коллекция пуста"));
    }

    public static int sumEvenOrZero(int[] arr) {
        return Optional.ofNullable(arr)
                .map(a -> Arrays.stream(a).filter(x -> x % 2 == 0).sum())
                .orElse(0);
    }

    public static Map<Character, String> toMapByFirstChar(List<String> inpt) {
        if (inpt == null) return Collections.emptyMap();

        return inpt.stream()
                .filter(Objects::nonNull)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s.substring(1),
                        (a, b) -> a + "," + b
                ));
    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(15,15,15,13,123,123,985,985);
        List<String> strs = Arrays.asList("awesome", "butterfly", "almost", "brown", "", null, "apperol", "violence");
        int[] arr = {1, 2, 3, 4, 6, 7};

        System.out.println("1) Среднее: " + avgAmount(nums).orElse(0.0));

        System.out.println("\n2) Капс+префикс:");
        toUpperWithNewPrefix(strs).forEach(System.out::println);

        System.out.println("\n3) Квадраты элементов, встречающихся один раз:");
        System.out.println(squaresOfSingles(nums));

        System.out.println("\n4) Ласт элемент коллекции:");
        System.out.println(lastOrThrow(nums));

        System.out.println("\n5) Сумма чётных в массиве:");
        System.out.println(sumEvenOrZero(arr));

        System.out.println("\n6) Маппинг по первому символу:");
        Map<Character, String> m = toMapByFirstChar(strs);
        m.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}
