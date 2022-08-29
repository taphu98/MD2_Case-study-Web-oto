package rikkei.academy.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Config<E> {
    public static Scanner scanner(){
        return new Scanner(System.in);
    }

    public static int getValidInteger() {
        int integer;
        while (true) {
            String s = scanner().nextLine();
            if (s.matches("\\d+")) {
                integer = Integer.parseInt(s);
                break;
            } else {
                System.out.println("Invalid number");
            }
        }
        return integer;
    }
    public E read(String path) {

        try (
                FileInputStream fis = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return  (E) ois.readObject();
        } catch (Exception e) {
            return null;
        }

    }

    public void write(String path, E data) {
        try (
                FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
