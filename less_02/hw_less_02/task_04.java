package less_02.hw_less_02;
/*
Разработайте программу, которая выбросит Exception, 
когда пользователь вводит пустую строку. 
Пользователю должно показаться сообщение, что пустые строки вводить нельзя
 */

import java.util.Scanner;

public class task_04 {
    public static void inputStr (){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String str = scanner.nextLine();
        if (str.trim().isEmpty()){ //в т.ч. проверка на строку из пробелов (.trim())
            System.out.println("Вы ввели ПУСТУЮ строку! Повторите ввод!");
            System.out.println("---------");
            inputStr ();
        } else {
            System.out.printf("--> Вы ввели строку: '%s'\n", str);
            System.out.printf("--> Длина строки: %d\n", str.length());
        }
        scanner.close();
    }
    
    public static void main(String[] args) {
        inputStr ();
    }
}
