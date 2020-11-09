import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 */
enum Gender {
    FEMALE, MALE, OTHER
}

/**
 * Used to experiment with.
 */
class Person {

    // **** members ****
    private String name;
    private Gender gender;
    private LocalDate birthDay;
    private double weight;
    private double height;

    // **** constructors(s) ****
    public Person() {
    }

    public Person(String name, Gender gender, LocalDate birthDay) {
        this.name = name;
        this.gender = gender;
        this.birthDay = birthDay;
    }

    // **** getters ****
    public String getName() {
        return this.name;
    }

    public Gender getGender() {
        return this.gender;
    }

    public LocalDate getBirthDay() {
        return this.birthDay;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getHeight() {
        return this.height;
    }

    // **** setters ****
    public void setName(String name) {
        this.name = name;
    }

    // **** ****
    @Override
    public String toString() {
        return "(name: " + this.name + ", gender: " + this.gender + ", birthDay: " + this.birthDay + ")";
    }
}


/**
 * 
 */
class PersonComparator implements Comparator<Person> { 

    @Override
    public int compare(Person p1, Person p2) { 

        // **** for comparison ****
        int nameCompare = p1.getName().compareTo(p2.getName()); 

        // **** ****
        return nameCompare;
    }
}


/**
 * Experimenting with Java streams.
 */
public class JavaStreams {

    /**
     * Generate female list.
     * Imperative approach.
     */
    static void before0(List<Person> people) {

        // // **** display people list ****
        // for (Person person : people) {
        //     System.out.println("before0 <<< person: " + person);
        // }
        // System.out.println();

        // **** generate female list ****
        List<Person> females = new ArrayList<Person>();
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getGender().equals(Gender.FEMALE))
                females.add(people.get(i));
        }

        // **** display female list ****
        for (Person f : females) {
            System.out.println("before0 <<< f: " + f);
        }
        System.out.println();
    }


    /**
     * Sort the people list.
     * You can easily reverse the sorting order.
     */
    static void before1(List<Person> people) {

        // **** make a copy of the people list ****
        List<Person> sorted = new ArrayList<Person>();
        for (int i = 0; i < people.size(); i++) {
            sorted.add(people.get(i));
        }

        // **** sort the list ****
        Collections.sort(sorted, new PersonComparator());

        // **** display the sorted list ****
        for (int i = 0; i < people.size(); i++) {
            System.out.println("before1 <<< person: " + sorted.get(i));
        }

        // **** for the looks ****
        System.out.println();
    }


    /**
     * Check if all match condition.
     * This is not completely correct because Period uses LocalDate (Java 8).
     * Imperative approach.
     */
    static void before2(List<Person> people, int age) {

        // **** initialization ****
        boolean allMatch = true;

        // **** loop checking ages ****
        for (int i = 0; i < people.size() && allMatch == true; i++) {
            if (Period.between(people.get(i).getBirthDay(), LocalDate.now()).getYears() <= age)
                allMatch = false;
        }

        // **** display result ****
        System.out.println("before2 <<< age: " + age + " allMatch: " + allMatch);
    }


    /**
     * Check if at least one person matches the condition.
     * Using an iterator; could have used a for loop.
     * This is not fair because Period uses LocalDate (Java 8).
     * Imperative approach.
     */
    static void before3(List<Person> people, int age) {

        // **** initialization ****
        boolean anyMatch = false;

        // **** traverse the list ****
        Iterator<Person> it = people.iterator();
        while (it.hasNext()) {

            // **** get next person ****
            Person person = it.next();

            // **** check if at this age or older ****
            if (Period.between(person.getBirthDay(), LocalDate.now()).getYears() >= age) {
                anyMatch = true;
                break;
            }
        }

        // **** display result ****
        System.out.println("before3 <<< age: " + age + " anyMatch: " + anyMatch);
    }


    /**
     * Display people and associated age.
     * Compute and display min and max ages. 
     * This is not fair because Period uses LocalDate (Java 8).
     * Imperative approach.
     */
    static void before4(List<Person> people) {

        // **** *****
        int minAge = Integer.MAX_VALUE;
        int maxAge = Integer.MIN_VALUE;

        // **** traverse list of people ****
        for (int i = 0; i < people.size(); i++) {

            // **** compute age ****
            int age = Period.between(people.get(i).getBirthDay(), LocalDate.now()).getYears();

            // **** display person and age ****
            System.out.println("before4 <<< person: " + people.get(i) + " age: " + age);

            // **** update min and max ages ****
            minAge = Math.min(minAge, age);
            maxAge = Math.max(maxAge, age);
        }

        // **** display min and max ages ****
        System.out.println("before4 <<< minAge: " + minAge + " maxAge: " + maxAge + "\n");
    }


    /**
     * Find any person with the specified name.
     * Imperative approach.
     */
    static void before5(List<Person> people, String name) {

        // **** initialization ****
        Person person = null;
        boolean found = false;

        // **** ****
        for (int i = 0; i < people.size() && !found; i++) {

            // **** get curtrent person ****
            person = people.get(i);

            // **** check name ****
            if (person.getName().equals(name)) {
                found = true;
            }
        }

        // **** display person with specified name (if any) ****
        if (found)
            System.out.println("before5 <<< found person: " + person.toString());
        else
            System.out.println("before5 <<< did NOT find person with name ==>" + name + "<==");
    }

    
    /**
     * Youngest person?
     * Imperative approach.
     */
    static void before6(List<Person> people) {

        // **** initialization*****
        int minAge = Integer.MAX_VALUE;
        int minIndex = -1;

        // **** traverse list of people ****
        for (int i = 0; i < people.size(); i++) {

            // **** compute age ****
            int age = Period.between(people.get(i).getBirthDay(), LocalDate.now()).getYears();

            // **** update variables (if needed) ****
            if (age < minAge) {
                minAge = age;
                minIndex = i;
            }
        }

        // **** display the youngest person ****
        System.out.println("before6 <<< person: " + people.get(minIndex));
    }


    /**
     * Oldest person?
     * Imperative approach.
     */
    static void before7(List<Person> people) {

        // **** initialization*****
        int maxAge = Integer.MIN_VALUE;
        int maxIndex = -1;

        // **** traverse list of people ****
        for (int i = 0; i < people.size(); i++) {

            // **** compute age ****
            int age = Period.between(people.get(i).getBirthDay(), LocalDate.now()).getYears();

            // **** update variables (if needed) ****
            if (age > maxAge) {
                maxAge = age;
                maxIndex = i;
            }
        }

        // **** display the oldest person ****
        System.out.println("before7 <<< person: " + people.get(maxIndex));
    }


    /**
     * Group people by gender.
     * Imperative approach.
     */
    static void before8(List<Person> people) {

        // **** hash map ****
        Map<Gender, List<Person>> mapByGender = new HashMap<Gender, List<Person>>();

        // **** generate hash map by gender ****
        for (int i = 0; i < people.size(); i++) {

            // **** get this person ****
            Person person = people.get(i);

            // **** ****
            if (!mapByGender.containsKey(person.getGender())) {
                List<Person> people1 = new ArrayList<Person>();
                people1.add(person);
                mapByGender.put(person.getGender(), people1);
            } else {
                List<Person> people1 = mapByGender.get(person.getGender());
                people1.add(person);
            }
        }

        // **** entry set of hash map ****
        Set<Entry<Gender, List<Person>>> set = mapByGender.entrySet();

        // **** for the looks ****
        System.out.println("before8:");

        // **** iterate through the genders ****
        Iterator<Entry<Gender, List<Person>>> it = set.iterator();
        while (it.hasNext()) {

            // **** ****
            Entry<Gender, List<Person>> e = it.next();

            // **** ****
            System.out.println(e.getKey());

            // **** get the list ****
            List<Person> l = e.getValue();

            // **** iterate throhj each person ****
            for (int i = 0; i < l.size(); i++) {
                System.out.println(l.get(i));
            }
        }

        // **** for the looks ****
        System.out.println();
    }


    /**
     * Generate female list.
     */
    static void after0(List<Person> people) {

        // **** generate list of females ***
        List<Person> females = people.stream()
            .filter(person -> person.getGender().equals(Gender.FEMALE))
            .collect(Collectors.toList());

        // **** display list of females ****
        // females.forEach(System.out::println);
        females.forEach( f -> System.out.println("after0 <<< f: " + f) );

        // **** for the looks ****
        System.out.println();
    }


    /**
     * Sort people list.
     * You can easily reverse the sorting order.
     */
    static void after1(List<Person> people) {

        // **** sort people list ****
        List<Person> sorted = people.stream()

            // .sorted(Comparator.comparing(Person::getName))
            // .sorted(Comparator.comparing(Person::getName).reversed())

            .sorted(Comparator.comparing(Person::getName).thenComparing(Person::getGender))

            .collect(Collectors.toList());

        // **** display sorted list ****
        sorted.forEach( p -> System.out.println("after1 <<< p: " + p) );

        // **** for the looks ****
        System.out.println();
    }


    /**
     * Check if all match condition.
     */
    static void after2(List<Person> people, int age) {

        // **** check ages ****
        boolean allMatch = people.stream()
            .allMatch( p -> Period.between(p.getBirthDay(), LocalDate.now()).getYears() > age );

        // **** display results ****
        System.out.println("after2 <<<  age: " + age + " allMatch: " + allMatch + "\n");
    }


    /**
     * Check if at least one person matches the condition.
     */
    static void after3(List<Person> people, int age) {

        // **** ****
        boolean anyMatch = people.stream()
            .anyMatch( p -> Period.between(p.getBirthDay(), LocalDate.now()).getYears() >= age);

        // **** display result ****
        System.out.println("after3 <<<  age: " + age + " anyMatch: " + anyMatch + "\n");           
    }


    /**
     * Display people and associated age.
     * Compute and display min and max ages.
     */
    static void after4(List<Person> people) {

        // **** ****
        people.forEach( p -> { 
            int age = Period.between(p.getBirthDay(), LocalDate.now()).getYears();
            System.out.println("after4 <<< p: " + p + " age: " + age);
        });
    
        // **** for the looks ****
        System.out.println();
    }


    /**
     * Find any person with the specified name.
     */
    static void after5(List<Person> people, String name) {

        // **** ****
        boolean noneMatch = people.stream()
            .noneMatch( p -> p.getName().equals(name));

        // **** display results ****
        System.out.println("after5 <<< name ==>" + name + "<== noneMatch: " + noneMatch + "\n");
    }


    /**
     * Youngest person?
     */
    static void after6(List<Person> people) {

        people.stream()
            .max(Comparator.comparing(Person::getBirthDay))
            .ifPresent( p -> System.out.println("after6 <<< p: " + p + "\n"));
    }


    /**
     * Oldest person?
     */
    static void after7(List<Person> people) {

        people.stream()
            .min(Comparator.comparing(Person::getBirthDay))
            .ifPresent( p -> System.out.println("after7 <<< p: " + p + "\n"));
    }


    /**
     * Group people by gender.
     */
    static void after8(List<Person> people) {

        // **** ****
        Map<Gender, List<Person>> mapByGender = people.stream()
            .collect(Collectors.groupingBy(Person::getGender));

        // **** ****
        System.out.println("after8:");
        mapByGender.forEach( (gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
        });

        // **** for the looks ****
        System.out.println();
    }


    /**
     * Test scaffolding.
     * The date of birth for the characters was made up.
     */
    public static void main(String[] args) {

        // **** ****
        int age = 0;

        // **** create list of people ****
        List<Person> people = new ArrayList<Person>();

        // **** populate the list of people ****
        people.add(new Person("Money Penny", Gender.FEMALE, LocalDate.of(1980, Month.MAY, 22)));
        people.add(new Person("James Bond", Gender.MALE, LocalDate.of(1970, Month.APRIL, 3)));
        people.add(new Person("Dink", Gender.FEMALE, LocalDate.of(1965, Month.JANUARY, 31)));
        people.add(new Person("Tatiana Romanova", Gender.FEMALE, LocalDate.of(1982, Month.AUGUST, 19)));

        people.add(new Person("Auric Goldfinger", Gender.MALE, LocalDate.of(1960, Month.AUGUST, 15)));
        people.add(new Person("Honey Rider", Gender.FEMALE, LocalDate.of(1975, Month.FEBRUARY, 13)));
        people.add(new Person("Julius No", Gender.MALE, LocalDate.of(1965, Month.JULY, 4)));
        people.add(new Person("Anya Amasova", Gender.FEMALE, LocalDate.of(1972, Month.JUNE, 17)));

        people.add(new Person("Domino Derval", Gender.FEMALE, LocalDate.of(1973, Month.SEPTEMBER, 28)));
        people.add(new Person("Aki", Gender.FEMALE, LocalDate.of(1973, Month.NOVEMBER, 14)));


        // **** display the list of people ****
        people.forEach( p -> System.out.println("main <<< p: " + p) );
        System.out.println();


        // **** generate list of females ****
        before0(people);

      // **** generate list of females ****
        after0(people);


        // **** sort people ****
        before1(people);

        // **** sort people ****
        after1(people);


        // **** specify age ****
        age = 37;

        // **** all people are atleast this age ****
        before2(people, age);

        // **** all people are atleast this age ****
        after2(people, age);


        // **** specify age ****
        age = 61;

        // **** any person older than this age ****
        before3(people, age);

        // **** any person older than this age ****
        after3(people, age);


        // **** display people and age ****
        before4(people);

        // **** display people and age ****
        after4(people);


        // **** specify name ****
        String name = "James";

        // **** find any person with the specified name ****
        before5(people, name);

        // **** find any person with the specified ****
        after5(people, name);


        // **** oldest person ****
        before6(people);

        // **** oldest person ****
        after6(people);


        // **** youngest person ****
        before7(people);

        // **** youngest person ****
        after7(people);


        // **** group by gender ****
        before8(people);

        // **** group by gender ****
        after8(people);
    }
}