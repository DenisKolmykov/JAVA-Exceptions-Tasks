package less_03;

import java.util.Scanner;

public class ViewInput {
    private Scanner scanner;

    public ViewInput() {
        this.scanner = new Scanner(System.in);
    }


    public String[] getInputString() {
        boolean flag = true;
        String[] splitedStr = null;
        while(flag){
            System.out.print("\nВ произвольном порядке введите, разделенные пробелом:\nФамилия Имя Отчество датарождения номертелефона пол:\n--> ");
            String inpData = scanner.nextLine();
            splitedStr = inpData.split(" ");

            if (getError(checkInputDataVolume (splitedStr))){
                flag = false;
            }
        }
        //scanner.close();
        return splitedStr;
    }

    
    private int checkInputDataVolume(String[] splitedStr){
        int errorCode = 0; // "0" - все норм: "-1" - если меньше; "1" - если больше
        int checkLenght = 6; // 6 = 3(ФИО) + 1(дата) + 1(тел) + 1(пол)
        if (splitedStr.length > checkLenght){
            errorCode = 1;
        } else if(splitedStr.length < checkLenght){
            errorCode = -1;
        }
        return errorCode;
    }

    private boolean getError(int errorCode){
        if (errorCode != 0){
            System.out.printf("\nERROR: '%d'\n", errorCode);
            switch (errorCode){
                case 1:
                    System.out.println("Вы ввели БОЛЬШЕ данных чем необходимо!\nПовторите ввод!");
                    return false;
                    //break;
                case -1:
                    System.out.println("Вы ввели МЕНЬШЕ данных чем необходимо!\nПовторите ввод!");
                    return false;
                    //break;
                }
        }
        return true;
    }
}
