package by.validators;

import java.text.SimpleDateFormat;

public class Date {

    public static String dateNow(){
        // Инициализация объекта date
        Date date = new Date();
        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        // Вывод текущей даты и времени с использованием toString()
        System.out.println(date1);
        return date1;
    }
}
