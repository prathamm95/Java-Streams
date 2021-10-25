package java_streams_tutorial;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Person> people = getPeople();

        /*.***** IMPERATIVE APPROACH (without streams) *****.*/

//        List<Person> females = new ArrayList<>();
//        for (Person person : people) {
//            if (person.getGender().equals(Gender.FEMALE)){
//                females.add(person);
//            }
//        }
//
//        females.forEach(System.out::println);

        /*.***** DECLARATIVE APPROACH (with streams) *****.*/

        /* filter */
        List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());

//        females.forEach(System.out::println);

        /* sort */
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .collect(Collectors.toList());

//        sorted.forEach(System.out::println);

        /* all match */
        boolean ageCheck = people.stream()
                .allMatch(person -> person.getAge() > 5);

        System.out.println(ageCheck);

        /* any match */
        boolean ageCheck2 = people.stream()
                .anyMatch(person -> person.getAge() < 15);

        System.out.println(ageCheck2);

        /* None match */
        boolean nameSearchInList = people.stream()
                .noneMatch(person -> person.getName().equals("Peter Parker"));

        System.out.println(nameSearchInList);

        /* Max */
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        /* Min */
        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);
        System.out.println();

        /* Group */
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupByGender.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println();
        });

    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Eddie Brock", 27, Gender.MALE),
                new Person("Mary Jane", 24, Gender.FEMALE),
                new Person("Peter Parker", 25, Gender.MALE),
                new Person("Felicia Hardy", 25, Gender.FEMALE),
                new Person("May Parker", 58, Gender.FEMALE),
                new Person("Miles Morales", 16, Gender.MALE)
        );
    }

}
