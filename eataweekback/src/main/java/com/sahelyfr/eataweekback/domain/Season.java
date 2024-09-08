package com.sahelyfr.eataweekback.domain;

import java.time.LocalDate;

public enum Season {

    SPRING("spring", new Period(3, 21), new Period(6, 20)),
    SUMMER("summer", new Period(6, 21), new Period(9, 20)),
    AUTUMN("autumn", new Period(9, 21), new Period(12, 20)),
    WINTER("winter", new Period(12, 21), new Period(3, 20));

    public final String name;
    public final Period startDate;
    public final Period endDate;

    private Season(String name, Period startDate, Period endDate){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Season getCurrent() throws Exception {
        LocalDate today = LocalDate.now();

        for(Season s : values()){
           LocalDate start = LocalDate.of(LocalDate.now().getYear(), s.startDate.month, s.startDate.day);
           LocalDate end = LocalDate.of(LocalDate.now().getYear(), s.endDate.month, s.endDate.day);

           if(today.isAfter(start) && today.isBefore(end)){
               return s;
           }
       }
       throw new Exception("Provided date does not correspond to defined seasons");
    }

    public static class Period {
        public int month;
        public int day;

        public Period(int month, int day){
            this.month = month;
            this.day = day;
        }
    }


}
