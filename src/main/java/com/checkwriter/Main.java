package main.java.com.checkwriter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter a number that is less than 5 digits: ");
            Scanner scanner = new Scanner(System.in);
            new CheckWriter().convertDollars(scanner.nextLine());
        }
    }
}
