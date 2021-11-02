import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Ex7Var5 {
    public static void main(String[] args) throws IOException, InputMismatchException{
        try {
            File f1 = new File("D:\\Файлы 1\\Задание 7\\var7_1.txt");
            File f2 = new File("D:\\Файлы 1\\Задание 7\\var7_1_2.txt");
            if (f1.exists()) {
                f1.delete();
                f2.delete();
            }
            f1.createNewFile();
            f2.createNewFile();

            RandomAccessFile rf = new RandomAccessFile(f1,"rw");
            System.out.println(rf.length());
            Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
            System.out.print("Введите количество команд для записи в файл\n"
                    + "=> ");
            int kol = sc.nextInt();
            sc.nextLine();


            for (int i = 0; i < kol; i++) {
                System.out.print("Введите название " + (i + 1) + "-ой команды => ");
                String name = sc.next();
                rf.seek(rf.length());
                rf.writeUTF(name);
                for (int j = 0; j < 20 - name.length(); j++)
                    rf.writeByte(1);
                System.out.print("Введите город команды=> ");
                String city = sc.next();
                rf.writeUTF(city);
                for (int j = 0; j < 20 - city.length(); j++)
                    rf.writeByte(1);
                System.out.print("Введите место в лиге => ");
                int number = sc.nextInt();
                sc.nextLine();
                rf.writeInt(number);
                System.out.print("Введите количество побед => ");
                int wins = sc.nextInt();
                sc.nextLine();
                rf.writeInt(wins);
            }
            System.out.println(rf.length());

            RandomAccessFile rf1 = new RandomAccessFile(f2, "rw");

            rf.seek(0);
            System.out.println("Информация о командах");
            System.out.println("Название \t\t Город \t\t Место в лиге \t\t Победы");

            int numberOfTeams = 0;
            for (int i = 0; i < kol; i++) {
                rf.seek(i * 52L);
                String name = rf.readUTF();
                rf.seek(i * 52L + 22);
                String city = rf.readUTF();
                rf.seek(i * 52L + 44);
                int number = rf.readInt();
                rf.seek(i * 52L + 48);
                int wins = rf.readInt();

                if (number < 4) {
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

                    numberOfTeams = numberOfTeams + 1;
                }
                System.out.println(name + "\t\t\t" + city + "\t\t\t" + number + "\t\t\t\t" + wins);
            }

            rf1.seek(0);
            System.out.println("Команды занимающие первые три места в лиге");
            System.out.println("Название \t\t Город \t\t Место в лиге \t\t Победы");
            for (int i = 0; i < numberOfTeams; i++) {
                rf1.seek(i * 52L);
                String name = rf1.readUTF();
                rf1.seek(i * 52L + 22);
                String city = rf1.readUTF();
                rf1.seek(i * 52L + 44);
                int number = rf1.readInt();
                rf1.seek(i * 52L + 48);
                int wins = rf1.readInt();
                System.out.println(name + "\t\t\t" + city + "\t\t\t" + number + "\t\t\t\t" + wins);
            }
            rf1.close();
            rf.close();

        } catch (IOException e) {
            System.out.println("End of file " + e);
        } catch (InputMismatchException e) {
            System.out.println("Exception " + e);
        }
    }
}