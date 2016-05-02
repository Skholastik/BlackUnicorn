package com.springapp.mvc.Service.AncillaryServices;

import org.springframework.stereotype.Component;

/** Класс для обработки слов и предложений */


@Component
public class WordProcessor {

    /** Используется для обработки и подготовки слова для БД.
     Убирает лишние пробелы между словами,
     делает только первую букву заглавной */

    public static String prepareWordToDB(String word){
        String formatted=word.toLowerCase().replaceAll("[\\s]{2,}", " ");
        return formatted.substring(0, 1).toUpperCase() + formatted.substring(1);
    }

}
