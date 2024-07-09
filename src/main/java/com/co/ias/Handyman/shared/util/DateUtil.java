package com.co.ias.Handyman.shared.util;

import java.time.LocalDateTime;


public class DateUtil {

    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    public static final String THURSDAY = "THURSDAY";
    public static final String FRIDAY = "FRIDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final String SUNDAY = "SUNDAY";
    public static final String LUNES = "Lunes";
    public static final String MARTES = "Martes";
    public static final String MIERCOLES = "Miércoles";
    public static final String JUEVES = "Jueves";
    public static final String VIERNES = "Viernes";
    public static final String SABADO = "Sábado";
    public static final String DOMINGO = "Domingo";

    public static String dayWeek (String day) {

       String spanishDay = "";
        switch (day){
            case MONDAY: spanishDay = LUNES;
                break;
            case TUESDAY: spanishDay = MARTES;
                break;
            case WEDNESDAY: spanishDay = MIERCOLES;
                break;
            case THURSDAY: spanishDay = JUEVES;
                break;
            case FRIDAY: spanishDay = VIERNES;
                break;
            case SATURDAY: spanishDay = SABADO;
                break;
            case SUNDAY: spanishDay = DOMINGO;
                break;
        }

        return spanishDay;
    }



}
