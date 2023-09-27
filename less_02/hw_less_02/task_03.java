package less_02.hw_less_02;
/*
 Дан следующий код, исправьте его там, где требуется
 public static void main(String[] args) throws Exception {
   try {
       int a = 90;
       int b = 3;
       System.out.println(a / b);
       printSum(23, 234);
       int[] abc = { 1, 2 };
       abc[3] = 9;
   } catch (Throwable ex) {
       System.out.println("Что-то пошло не так...");
   } catch (NullPointerException ex) {
       System.out.println("Указатель не может указывать на null!");
   } catch (IndexOutOfBoundsException ex) {
       System.out.println("Массив выходит за пределы своего размера!");
   }
}
public static void printSum(Integer a, Integer b) throws FileNotFoundException {
   System.out.println(a + b);
}

 */


public class task_03 {
    public static void main(String[] args) {// нет необходимости в throws Exception
        try {
            int a = 90;
            int b = 3;
            if (b != 0){ //т.к7 предполагается деление на значение переменной 'b', то необходимо проверка на '0'
                System.out.println(a / b);  
            } else {
                System.out.println("ATTENTION: You are trying to divide by zero!\nChange var 'b' value");
            }
            printSum(23, 234);
            int[] abc = { 1, 2 };
            abc[3] = 9;
        // } catch (Throwable ex) { //не желательно применять, т.к. не будет выявлено конкретное исключение
        //     System.out.println("Что-то пошло не так...");
        // } catch (NullPointerException ex) { //в коде указан массив 'abc' и переменные 'a' и 'b' как-int, которые не могут быть null (если было бы 'Integer', то надо было бы и проверить на NullPointerException)
        //     System.out.println("Указатель не может указывать на null!");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Массив выходит за пределы своего размера!");
        }
     }
     public static void printSum(Integer a, Integer b) {//отсутствует обращение к какому-либо файлу, throws FileNotFoundException - не нужно
        System.out.println(a + b);
     }
}
