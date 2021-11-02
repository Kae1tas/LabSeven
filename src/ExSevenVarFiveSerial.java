import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.*;

class Team implements Serializable {
    String name;
    String city;
    int number;
    int wins;
}

public class ExSevenVarFiveSerial {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);

        File f=new File("D:\\Файлы 1\\Задание 7\\MyFileLabSer");
        f.createNewFile();

        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        System.out.print("Введите количество команд =>");
        int var = sc.nextInt();


        for (int i = 0;i < var; i++) {
            Team team = new Team();
            System.out.println("Введите информацию о команде " + (i + 1) + ":");
            sc.nextLine();
            System.out.print("Название команды => ");
            team.name = sc.nextLine();
            sc.nextLine();
            System.out.print("Город команды => ");
            team.city = sc.nextLine();
            System.out.print("Место команды в лиге => ");
            team.number = sc.nextInt();
            System.out.print("Количество побед команды => ");
            team.wins = sc.nextInt();

            oos.writeObject(team);
        }

        oos.flush();
        oos.close();

        int [] arrFirstThree = new int [var];
        for(int i = 0; i < var; i++) {
            for(int j =  i + 1; j < var; j++) {
                if(arrFirstThree[i] > arrFirstThree[j]) {
                    int fd = arrFirstThree[j];
                    arrFirstThree[j] = arrFirstThree[i];
                    arrFirstThree[i] = fd;
                }
            }
        }

        File f1=new File("D:\\Файлы 1\\Задание 7\\MyFileLabSer2");
        f1.createNewFile();

        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream oin = new ObjectInputStream(fis);
        FileOutputStream fs = new FileOutputStream(f1);
        ObjectOutputStream oos1 = new ObjectOutputStream(fs);

        for (int i = 0;i < var; i++) {
            Team team = (Team) oin.readObject();

            System.out.println(" Название команды " + team.name);
            System.out.println(" город = " + team.city);
            System.out.println(" место в лиге = " + team.number);
            System.out.println(" кол-во побед = " + team.wins);

            if (var >= 3) {
                if (team.number == arrFirstThree[0] | team.number == arrFirstThree[1] | team.number == arrFirstThree[2]) {
                    oos1.writeObject(team);
                }
            } else{
                oos1.writeObject(team);
            }
        }
        oin.close();
        oos1.flush();
        oos1.close();

        FileInputStream fis1 = new FileInputStream(f1);
        ObjectInputStream oin1 = new ObjectInputStream(fis1);


        System.out.print("Количество команд =>");
        for (int i = 0;i < var; i++) {
            Team team = (Team) oin1.readObject();

            System.out.println(" Название команды " + team.name);
            System.out.println(" город = " + team.city);
            System.out.println(" место в лиге = " + team.number);
            System.out.println(" кол-во побед = " + team.wins);
        }
        oin1.close();
    }
}
