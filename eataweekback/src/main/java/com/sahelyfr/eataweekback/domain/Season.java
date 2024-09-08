package com.sahelyfr.eataweekback.configuration;

import java.time.LocalDate;

public enum Season {

    private static final int THIS_YEAR = LocalDate.now().getYear();
    private static final LocalDate SPRING_START = LocalDate.of(THIS_YEAR, 3, 21);
    private static final LocalDate SPRING_END = LocalDate.of(THIS_YEAR, 6, 20);
    private static final LocalDate SUMMER_START = LocalDate.of(THIS_YEAR, 6, 21);
    private static final LocalDate SUMMER_END = LocalDate.of(THIS_YEAR, 9, 20);
    private static final LocalDate AUTUMN_START = LocalDate.of(THIS_YEAR, 9, 21);
    private static final LocalDate AUTUMN_END = LocalDate.of(THIS_YEAR, 12, 20);
    private static final LocalDate WINTER_START = LocalDate.of(THIS_YEAR, 12, 21);
    private static final LocalDate WINTER_END = LocalDate.of(THIS_YEAR+1, 3, 20);

    SPRING("spring", SPRING_START, SPRING_END),
    SUMMER("summer", , ),
    AUTUMN("autumn", , ),
    WINTER("winter", , );

    public final String name;
    public LocalDate startDate;
    public LocalDate endDate;

    private Season(String name, LocalDate startDate, LocalDate endDate){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Season current(LocalDate today){
        return
    }
}
