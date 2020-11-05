package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.utils.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;

/*
 * Aibės testavimas be Gui
 * Dirbant su Intellij ir norint konsoleje matyti gražų pasuktą medį,
 * reikia FIle -> Settings -> Editor -> File Encodings -> Global encoding pakeisti į UTF-8
 *
 */
public class ManualTest {

    static Car[] cars;
    static ParsableSortedSet<Car> cSeries = new ParsableBstSet<>(Car::new, Car.byPrice);

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        executeTest();
    }

    public static void executeTest() throws CloneNotSupportedException {
        Car c1 = new Car("Renault", "Laguna", 1997, 50000, 1700);
        Car c2 = new Car.Builder()
                .make("Renault")
                .model("Megane")
                .year(2001)
                .mileage(20000)
                .price(3500)
                .build();
        Car c3 = new Car.Builder().buildRandom();
        Car c4 = new Car("Renault Laguna 2001 115900 700");
        Car c5 = new Car("Renault Megane 1946 365100 9500");
        Car c6 = new Car("Honda   Civic  2001  36400 80.3");
        Car c7 = new Car("Renault Laguna 2001 115900 7500");
        Car c8 = new Car("Renault Megane 1946 365100 950");
        Car c9 = new Car("Honda   Civic  2007  36400 850.3");

        Car[] carsArray = {c9, c7, c8, c5, c1, c6};

        Ks.oun("Auto Aibė:");
        ParsableSortedSet<Car> carsSet = new ParsableBstSet<>(Car::new);

        for (Car c : carsArray) {
            carsSet.add(c);
            Ks.oun("Aibė papildoma: " + c + ". Jos dydis: " + carsSet.size());
        }
        Ks.oun("");
        Ks.oun(carsSet.toVisualizedString(""));

        ParsableSortedSet<Car> carsSetCopy = (ParsableSortedSet<Car>) carsSet.clone();

        carsSetCopy.add(c2);
        carsSetCopy.add(c3);
        carsSetCopy.add(c4);
        Ks.oun("Papildyta autoaibės kopija:");
        Ks.oun(carsSetCopy.toVisualizedString(""));

        c9.setMileage(10000);

        Ks.oun("Originalas:");
        Ks.ounn(carsSet.toVisualizedString(""));

        Ks.oun("Ar elementai egzistuoja aibėje?");
        for (Car c : carsArray) {
            Ks.oun(c + ": " + carsSet.contains(c));
        }
        Ks.oun(c2 + ": " + carsSet.contains(c2));
        Ks.oun(c3 + ": " + carsSet.contains(c3));
        Ks.oun(c4 + ": " + carsSet.contains(c4));
        Ks.oun("");

        Ks.oun("Ar elementai egzistuoja aibės kopijoje?");
        for (Car c : carsArray) {
            Ks.oun(c + ": " + carsSetCopy.contains(c));
        }
        Ks.oun(c2 + ": " + carsSetCopy.contains(c2));
        Ks.oun(c3 + ": " + carsSetCopy.contains(c3));
        Ks.oun(c4 + ": " + carsSetCopy.contains(c4));
        Ks.oun("");

        Ks.oun("Automobilių aibė su iteratoriumi:");
        Ks.oun("");
        for (Car c : carsSet) {
            Ks.oun(c);
        }
        Ks.oun("");
        Ks.oun("Automobilių aibė AVL-medyje:");
        ParsableSortedSet<Car> carsSetAvl = new ParsableAvlSet<>(Car::new);
        for (Car c : carsArray) {
            carsSetAvl.add(c);
        }
        Ks.ounn(carsSetAvl.toVisualizedString(""));

        Ks.oun("Automobilių aibė su iteratoriumi:");
        Ks.oun("");
        for (Car c : carsSetAvl) {
            Ks.oun(c);
        }

        Ks.oun("");
        Ks.oun("Automobilių aibė su atvirkštiniu iteratoriumi:");
        Ks.oun("");
        Iterator<Car> iter = carsSetAvl.descendingIterator();
        while (iter.hasNext()) {
            Ks.oun(iter.next());
        }

        Ks.oun("");
        Ks.oun("Automobilių aibės toString() metodas:");
        Ks.ounn(carsSetAvl);

        // Išvalome ir suformuojame aibes skaitydami iš failo
        carsSet.clear();
        carsSetAvl.clear();

        Ks.oun("");
        Ks.oun("Automobilių aibė DP-medyje:");
        carsSet.load("data\\ban.txt");
        Ks.ounn(carsSet.toVisualizedString(""));
        Ks.oun("Išsiaiškinkite, kodėl medis augo tik į vieną pusę.");

        Ks.oun("");
        Ks.oun("Automobilių aibė AVL-medyje:");
        carsSetAvl.load("data\\ban.txt");
        Ks.ounn(carsSetAvl.toVisualizedString(""));

        Set<String> carsSet4 = CarMarket.duplicateCarMakes(carsArray);
        Ks.oun("Pasikartojančios automobilių markės:\n" + carsSet4.toString());

        Set<String> carsSet5 = CarMarket.uniqueCarModels(carsArray);
        Ks.oun("Unikalūs automobilių modeliai:\n" + carsSet5.toString());

        //BstSet Remove Test
        System.out.println("BstSet Remove Test");

        ParsableBstSet<Car> carsBst = new ParsableBstSet<>(Car::new);
        carsBst.add(c5);
        carsBst.add(c1);
        carsBst.add(c3);
        carsBst.add(c2);
        carsBst.add(c6);
        carsBst.add(c7);
        carsBst.add(c9);
        carsBst.add(c8);

        for (Car item : carsBst){
            System.out.println(item);
        }

        System.out.println(carsBst.toVisualizedString(""));

        System.out.println("Po šalinimo:");

        carsBst.remove(c5);

        for (Car item : carsBst){
            System.out.println(item);
        }

        System.out.println(carsBst.toVisualizedString(""));


        //AvlSet Remove Test
        System.out.println("AvlSet Remove Test");

        ParsableAvlSet<Car> carsAvl = new ParsableAvlSet<>(Car::new);
        carsAvl.add(c5);
        carsAvl.add(c1);
        carsAvl.add(c3);
        carsAvl.add(c2);
        carsAvl.add(c6);
        carsAvl.add(c7);
        carsAvl.add(c9);
        carsAvl.add(c8);

        for (Car item : carsAvl){
            System.out.println(item);
        }

        System.out.println(carsAvl.toVisualizedString(""));

        System.out.println("Po šalinimo:");

        carsAvl.remove(c1);
        carsAvl.remove(c2);

        for (Car item : carsAvl){
            System.out.println(item);
        }

        System.out.println(carsAvl.toVisualizedString(""));

        /* subSet(E element1, E element2) */

        System.out.println("subSet Testas:");
        System.out.println("subSet medis:");

        ParsableSortedSet<Car> carsSetsubSet = new ParsableBstSet<>(Car::new);

        carsSetsubSet.add(c2);
        carsSetsubSet.add(c3);
        carsSetsubSet.add(c4);
        for (Car item : carsArray) {
            carsSetsubSet.add(item);
        }

        for (Car item : carsSetsubSet) {
            System.out.println(item);
        }

        System.out.println(carsSetsubSet.toVisualizedString(""));

        System.out.println("Element1: " + c4.toString());
        System.out.println("Element2: " + c6.toString());
        System.out.println("Kelias nuo element1 iki element2 iki jo:");
        BstSet<Car> subSet = (BstSet)carsSetsubSet.subSet(c2, c6);


        for (Car item : subSet) {
            System.out.println(item);
        }

        System.out.println(subSet.toVisualizedString(""));

        ParsableBstSet<Car> test = new ParsableBstSet<>(Car::new);
        test.add(c5);
        test.add(c1);
        test.add(c3);
        test.add(c2);
        test.add(c6);
        test.add(c7);
        test.add(c9);
        test.add(c8);

        for (Car item : test) {
            System.out.println(item);
        }
        System.out.println(test.toVisualizedString(""));

        Iterator<Car> iterator = test.iterator();
        Car car = (Car) test.toArray()[0];
        int i = 0;
        while (iterator.hasNext()) {
            if(i == 2)
                iterator.remove();

            else
                iterator.next();
            i++;
        }

        System.out.println("Po remove metodo");
        System.out.println(test.toVisualizedString(""));
        for (Car item : test) {
            System.out.println(item);
        }
    }

    static ParsableSortedSet<Car> generateSet(int kiekis, int generN) {
        cars = new Car[generN];
        for (int i = 0; i < generN; i++) {
            cars[i] = new Car.Builder().buildRandom();
        }
        Collections.shuffle(Arrays.asList(cars));

        cSeries.clear();
        Arrays.stream(cars).limit(kiekis).forEach(cSeries::add);
        return cSeries;
    }
}
