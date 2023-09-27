package less_02.hw_less_02;
/*
Если необходимо, исправьте данный код
try {
    int d = 0;
    double catchedRes1 = intArray[8] / d;
    System.out.println("catchedRes1 = " + catchedRes1);
    } catch (ArithmeticException e) {
      System.out.println("Catching exception: " + e);
}

 */
public class task_02 {
    public static void main(String[] args) {
        int [] intArray = {1,2,3,4};
        try {
            int d = 0;
            if (d != 0){ // проверка деления на '0'
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
            } else {
                System.out.println("ATTENTION: You are trying to divide by zero!\nChange var 'd' value");
            }
         } catch (ArrayIndexOutOfBoundsException e) { //т.к. есть обращение к элементам массива - необходимо проверить на обращение к индексу в пределах массива
            System.out.println("Catching exception: " + e);
         }
         
    }
}
