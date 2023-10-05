package less_03;
/*
Напишите приложение, которое будет запрашивать у пользователя 
следующие данные в произвольном порядке, разделенные пробелом:
Фамилия Имя Отчество датарождения номертелефона пол

Форматы данных:
фамилия, имя, отчество - строки
датарождения - строка формата dd.mm.yyyy
номертелефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.

Приложение должно проверить введенные данные по количеству. 
Если количество не совпадает с требуемым, вернуть код ошибки, 
обработать его и показать пользователю сообщение, 
что он ввел меньше и больше данных, чем требуется.

Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. 
Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. 
Можно использовать встроенные типы java и создать свои. 
Исключение должно быть корректно обработано, 
пользователю выведено сообщение с информацией, что именно неверно.

Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, 
в него в одну строку должны записаться полученные данные, вида

<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

Не забудьте закрыть соединение с файлом.

При возникновении проблемы с чтением-записью в файл, 
исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.
*/

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class hw {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException, InterruptedException {

        String [] inputData=null;
        boolean flag = false;
        Map <String, ArrayList<String>> person = new HashMap<>();

        while (!flag) { //зацикливаем пока не введем корректную строку
            ViewInput data = new ViewInput ();
            inputData = data.getInputString();
            CheckElements check = new CheckElements(inputData);
            try{
                person = check.checkAll ();
                flag = true;
            } catch (RuntimeException e){
                //e.printStackTrace();
                System.out.println("\nATTENTION!\n" + e);
                System.out.println("\nПовторите ввод всей строки!");
                flag = false;
            }
        }

        String fileName = person.get("isName").get(0);

        String surName = fileName;
        String name = person.get("isName").get(1);
        String fatherName = person.get("isName").get(2);
        String phoneNumber = person.get("isPhone").get(0);
        String gender = person.get("isGender").get(0);
        String birthDate = person.get("isBirthDate").get(0);
        
        String fileContent = String.format("%s %s %s %s %s %s\n",surName, name, fatherName, birthDate, phoneNumber, gender);
        
        FileRecord fileRecord = new FileRecord(fileName, fileContent);

        try{ // попытка записи в файл 
            fileRecord.writeToDisk();
        }catch(RuntimeException e){ //(если после 5 неудачных попыток так и не получилось - заканчиваем сеанс)
            System.out.printf("ATTENTION!\nПосле 5 НЕУДАЧНЫХ попыток запись в файл '%s' прервана!\nСтрока: %s не сохранена!\n",fileName, fileContent);
        }
    }
}
