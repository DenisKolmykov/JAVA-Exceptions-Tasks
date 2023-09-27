package less_02.hw_less_02;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float),
и возвращает введенное значение. 
Ввод текста вместо числа не должен приводить к падению приложения, 
вместо этого, необходимо повторно запросить у пользователя ввод данных.
 */
public class task_01 {
    private static void inputNum (){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите дробное число: ");
        try {
            float num = scanner.nextFloat();
            System.out.printf("Вы ввели: %.2f\n", num);;
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели не число\nПопробуйте еще раз\n");
            inputNum ();
        }finally {
            scanner.close();
        }
    }

    public static void main(String[] args) {
        inputNum();
    }
}
