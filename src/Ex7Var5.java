import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Ex7Var5 {
    public static void main(String[] args) {
        try {
            File folder = new File("D:\\Файлы 1\\Задание 7");
            if (!folder.exists())
                folder.mkdir();

            File f1 = new File("D:\\Файлы 1\\Задание 7\\var7_1.txt");

            File f2 = new File("D:\\Файлы 1\\Задание 7\\var7_1_2.txt");

            if (!f1.exists())
                f1.createNewFile();

            if (!f2.exists())
                f1.createNewFile();

            RandomAccessFile rf = new RandomAccessFile(f1,"rw"); // чтение и запись


            Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
            System.out.print("Введите количество команд для записи в файл\n"
                    + "=> ");
            int kol = sc.nextInt();
            sc.nextLine(); // очистка буфера после ввода числа

            String name, city;
            int number, wins;

            int[] arrFirstThree = new int[kol];
            for (int i = 0; i < kol; i++) {
                System.out.print("Введите название " + (i + 1) + "-ой команды => ");
                name = sc.next();
                rf.seek(rf.length());
                rf.writeUTF(name);
                for (int j = 0; j < 20 - name.length(); j++)
                    rf.writeByte(1);
                System.out.print("Введите город команды=> ");
                city = sc.next();
                rf.writeUTF(city);
                for (int j = 0; j < 20 - city.length(); j++)
                    rf.writeByte(1);
                System.out.print("Введите место в лиге => ");
                number = sc.nextInt();
                sc.nextLine();
                rf.writeInt(number);
                System.out.print("Введите количество побед => ");
                wins = sc.nextInt();
                sc.nextLine();
                rf.writeInt(wins);

                arrFirstThree[i] = number;
            }
            rf.close();

            for(int i = 0; i < kol; i++) {
                for(int j =  i + 1; j < kol; j++) {
                    if(arrFirstThree[i] > arrFirstThree[j]) {
                        int fd = arrFirstThree[j];
                        arrFirstThree[j] = arrFirstThree[i];
                        arrFirstThree[i] = fd;
                    }
                }
            }

            rf = new RandomAccessFile(f1, "r");
            RandomAccessFile rf1 = new RandomAccessFile(f2, "rw");

            rf.seek(0);
            System.out.println("Информация о командах");
            System.out.println("Название \t\t Город \t\t Место в лиге \t\t Победы");
            for (int i = 0; i < kol; i++) {
                name = rf.readUTF();
                for (int j = 0; j < 20 - name.length(); j++) {
                    rf.readByte();
                }
                city = rf.readUTF();
                for (int j = 0; j < 20 - city.length(); j++) {
                    rf.readByte();
                }
                number = rf.readInt();
                wins = rf.readInt();

                if (kol >= 3) {
                    if (number == arrFirstThree[0] | number == arrFirstThree[1] | number == arrFirstThree[2]) {
                        rf1.seek(rf1.length());
                        rf1.writeUTF(name);
                        for (int j = 0; j < 20 - name.length(); j++) {
                            rf1.writeByte(1);
                        }
                        rf1.writeUTF(city);
                        for (int j = 0; j < 20 - city.length(); j++) {
                            rf1.writeByte(1);
                        }
                        rf1.writeInt(number);
                        rf1.writeInt(wins);
                    }
                } else{
                    rf1.seek(rf1.length());
                    rf1.writeUTF(name);
                    for (int j = 0; j < 20 - name.length(); j++) {
                        rf1.writeByte(1);
                    }
                    rf1.writeUTF(city);
                    for (int j = 0; j < 20 - city.length(); j++) {
                        rf1.writeByte(1);
                    }
                    rf1.writeInt(number);
                    rf1.writeInt(wins);
                }

                System.out.println(name + "\t\t\t" + city + "\t\t\t" + number + "\t\t\t\t" + wins);
            }
            rf.close();
            rf1.close();


            rf1 = new RandomAccessFile(f2, "r");
            rf1.seek(0);
            System.out.println("Команды занимающие первые три места в лиге");
            System.out.println("Название \t\t Город \t\t Место в лиге \t\t Победы");
            for (int i = 0; i < kol; i++) {
                name = rf1.readUTF();
                for (int j = 0; j < 20 - name.length(); j++)
                    rf1.readByte();
                city = rf1.readUTF();
                for (int j = 0; j < 20 - city.length(); j++)
                    rf1.readByte();
                number = rf1.readInt();
                wins = rf1.readInt();
                System.out.println(name + "\t\t\t" + city + "\t\t\t" + number + "\t\t\t\t" + wins);
            }
            rf1.close();

        } catch (IOException e) {
            System.out.println("End of file " + e);
        } catch (InputMismatchException e) {
            System.out.println("Exception " + e);
        }
    }
}
