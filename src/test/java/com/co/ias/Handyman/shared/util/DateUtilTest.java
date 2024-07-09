package com.co.ias.Handyman.shared.util;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DateUtilTest {

    @InjectMocks
    private DateUtil dateUtil;

    @Test
    public void dayWeek(){
       assertEquals(DateUtil.dayWeek("MONDAY"),"Lunes");
        assertEquals(DateUtil.dayWeek("TUESDAY"),"Martes");
        assertEquals(DateUtil.dayWeek("WEDNESDAY"),"Miércoles");
        assertEquals(DateUtil.dayWeek("THURSDAY"),"Jueves");
        assertEquals(DateUtil.dayWeek("FRIDAY"),"Viernes");
        assertEquals(DateUtil.dayWeek("SATURDAY"),"Sábado");
        assertEquals(DateUtil.dayWeek("SUNDAY"),"Domingo");

    }





}