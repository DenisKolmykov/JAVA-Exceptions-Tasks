package less_03;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CheckElements {

    public String[] splitedStr;

    public CheckElements(String[] splitedStr){
        this.splitedStr = splitedStr;
    }

    // основной метод для формирования корректной записи (все элементы в введенной строке - корректны)
    public Map <String, ArrayList<String>> checkAll () throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        // Словарь введенных значений по типу элементов
        Map <String, ArrayList<String>> elemVal = new HashMap<>();
        elemVal.put("isGender", null);
        elemVal.put("isPhone", null);
        elemVal.put("isName", null);
        elemVal.put("isBirthDate",null);

        //эталонный словарь количества введенных значений (проверка на повторы форматов)
        Map <String, Integer> elemCount = new HashMap<>();
        elemCount.put("isGender", 1);
        elemCount.put("isPhone", 1);
        elemCount.put("isName", 3);
        elemCount.put("isBirthDate",1);
               
        for (String elem: this.splitedStr){
            ArrayList<String>chekedElement = new ArrayList<>();
            try{
                String key = checkElement(elem); //получаем тип корректного элемента (в корректном формате)
                    //ArrayList<String>chekedElement = elemVal.get(key);
                    //elemVal.getValue();
                    //System.out.println("key - " + key);
                    int val = elemCount.get(key)-1; // минусуем из установленного количества элементов (из эталонного словаря)
                    //chekedElement = elemVal.get(key);
                if (elemVal.get(key) == null){ // записываем корректный элемент в список (т.к. о имени три элемента должно быть)
                    chekedElement = new ArrayList<>();
                    chekedElement.add(elem);
                } else {
                    chekedElement = elemVal.get(key); 
                    chekedElement.add(elem); 
                }
                //System.out.println("val= " + val);
                elemCount.put(key, val); // обновляем количество элементов в эталонном словаре по конкретному ключу
                elemVal.put(key, chekedElement); //записываем корректный элемент (список корректных элементов) в словарь
                //System.out.println(elemVal);
                    
                    
            }catch (RuntimeException e){
                //e.printStackTrace();
                throw new checkElementFormatException(elem);
            } 
        }
            
        // проверка на количество повторов (когда все форматы корректны)
        boolean fl = true;
        for (Map.Entry<String, Integer> entry : elemCount.entrySet()) {               
            int val = entry.getValue();
            String key = entry.getKey();
            if (val != 0){
                System.out.println("\nATTENTION!");
                if (val < 0){
                    System.out.printf("Количество Элементов '%s' БОЛЬШЕ на %d чем установлено!\n", key, Math.abs(val));
                }
                if (val > 0) { 
                    System.out.printf("Количество Элементов '%s' МЕНЬШЕ на %d чем установлено!\n", key, val);
                }
                fl = false;
            }
        }
        if (!fl){
            throw new RuntimeException();
        }
        return elemVal; // если все проверки прошли - вовращаем словарь корреткных элементов (списков корректных элементов)
    }


    //проверка каждого элемента (из введенной строки) на все типы
    private static String checkElement(String elem) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        String[] funcNames = {"isGender", "isPhone","isName","isBirthDate"};
        boolean flag = true;
        String out = "";
        for (String f : funcNames){ //метод для перебора функций (имена из массива имен) проверки всех типов
            Method method = CheckElements.class.getDeclaredMethod(f,String.class);
            int res = (int) method.invoke(null, elem);
            if (res == 1){
                flag = true;
                out = f;
                break;
            } 
            flag = false;
        }
        if (!flag){
            throw new RuntimeException();
        }
        return out; //если соответтвует какому-то типу - возвращаем название этого типа
    }
    

    protected static int isName (String elem){
        if (elem.length() > 1){
            for (int ch : elem.toCharArray()){
                if (!Character.isLetter(ch)){
                    return 0;
                }   
            }
            return 1;
        }
        return 0;
    }

    protected static int isPhone(String elem){
        try {
            Long.parseLong(elem);
            return 1;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    protected static int isGender(String elem){
        if (elem.length() == 1){
            char gen = elem.charAt(0);
            if (gen == 'f' || gen == 'm'){
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    } 

    protected static int isBirthDate(String elem){
        Pattern pattern = Pattern.compile("\\d{2}\\s*\\.\\d{2}\\.\\s*\\d{4}");
        Matcher matcher = pattern.matcher(elem);
        if (matcher.find()) {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("dd.MM.yyyy")
            .toFormatter(Locale.ENGLISH);
    
            try {
                LocalDate currentDate = LocalDate.now();
                LocalDate parsedDate = LocalDate.parse(elem, formatter);
                if (parsedDate.isAfter(currentDate)) {
                    System.out.println("\nATTENTION!\nДата больше текущей");
                    return 0;
                } else {
                return 1;
                }
            } catch (DateTimeParseException e) {
                //System.out.println("Некорректный формат даты");
                return 0;
            }
        }else {
            return 0;
        }
    }

}


class checkElementFormatException extends RuntimeException {
    private String illegalElement;

    public String getIllegalElement() {
        return illegalElement;
    }

    public checkElementFormatException (String illegalElement){
        super (String.format("\nЗначение '%s' введено не корректно!", illegalElement));
        this.illegalElement = illegalElement;
    }
    
}



