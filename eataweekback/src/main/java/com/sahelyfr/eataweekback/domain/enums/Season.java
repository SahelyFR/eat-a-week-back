package com.sahelyfr.eataweekback.domain.enums;

import java.time.LocalDate;

import com.sahelyfr.eataweekback.application.exceptions.NotFoundSeasonException;

public enum Season {

    SPRING("spring", 3, 6),
    SUMMER("summer", 6, 9),
    AUTUMN("autumn", 9, 12),
    WINTER("winter", 12, 3);

    public final String seasonName;
    public final int startMonth;
    public final int endMonth;

    Season(String season, int startMonth, int endMonth) {
        this.seasonName = season;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

    public static Season getCurrent() throws NotFoundSeasonException {
        return getFromGivenMonth(LocalDate.now().getMonth().getValue());

    }

    public static Season getFromMonth(int month) throws NotFoundSeasonException {
        return getFromGivenMonth(month);
    }

    private static Season getFromGivenMonth(int month) throws NotFoundSeasonException {

        return switch (month) {
            case 12, 1, 2:
                yield WINTER;
            case 3, 4, 5:
                yield SPRING;
            case 6, 7, 8:
                yield SUMMER;
            case 9, 10, 11:
                yield AUTUMN;
            default:
                throw new NotFoundSeasonException(month);
        };
    }

}
