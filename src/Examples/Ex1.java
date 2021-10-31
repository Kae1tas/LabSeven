package Examples;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Ex1 {
    public static void main(String[] args) {
        try {
            File folder = new File("D:\\Файлы 1\\Задание 7");
            if (!folder.exists())
                folder.mkdir();
            File f1 = new File("D:\\Файлы 1\\Задание 7\\num1Mart.txt");
            f1.createNewFile();
            Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
            System.out.print("Сколько чисел надо записать в файл? \n => ");
            int count = sc.nextInt();

            RandomAccessFile rf = new RandomAccessFile(f1, "rw");
            System.out.println("Исходный размер файла в байтах =" + rf.length()
                    + ", указатель стоит на " + rf.getFilePointer() + "-м байте");

            System.out.println("Введите числа:");
            for (int i = 0; i < count; i++) {
                rf.writeFloat(sc.nextFloat());
            }
            System.out.println("Новый размер файла в байтах =" + rf.length()
                    + ", указатель стоит на " + rf.getFilePointer() + "-м байте");
            System.out.println("Количество байт на 1 число = " + rf.length() / count);
            rf.close();


            rf = new RandomAccessFile(f1, "r");

            System.out.println("\n Числа в файле:");
            for (int i = 0; i < count; i++) {
                rf.seek(i * 4);
                System.out.println("Число" + i + ": " + rf.readFloat());
            }
            System.out.println("Числа в обратном порядке:");
            for (int i = count - 1; i >= 0; i--) {
                rf.seek(i * 4);
                System.out.println("Число" + i + ": " + rf.readFloat());
            }
            rf.seek(rf.getFilePointer() - 4);
            System.out.println(" Количество чисел в файле= " + rf.length()/4
                    + ", последнее число= " + rf.readFloat());

            System.out.print("\nВведите число, которое нужно найти в файле => ");
            float x = sc.nextFloat();
            int kol = 0;
            for (int i = 0; i < count; i++) {
                rf.seek(i * 4);
                float number = rf.readFloat();
                if (number == x) {
                    kol++;
                    System.out.print("номер " + i+ ", ");
                }
            }
            System.out.println(" количество искомых чисел = " + kol);
            rf.close();

            rf = new RandomAccessFile(f1, "rw");
            for (int k = 0; k < count; k++) {
                for (int i = 0; i < count - k -1; i++) {
                    rf.seek(i * 4);
                    float number1 = rf.readFloat();
                    float number2 = rf.readFloat();
                    if (number1 > number2) {
                        rf.seek(i * 4);
                        rf.writeFloat(number2);
                        rf.writeFloat(number1);
                    } } }
            System.out.println("\n Числа, отсортированные в файле:");
            for (int i = 0; i < count; i++) {
                rf.seek(i * 4);
                System.out.print(" " + rf.readFloat());
            }
            rf.close();
        } catch (IOException e) {
            System.out.println("End of file " + e);
        }
    }
}

