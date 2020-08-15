package com.zentagroup.birthdaycountdown.dto;

import java.time.LocalDate;

/**
 * Ariana González
 * ariana.sgm5@gmail.com
 */

public class ReqCountdownDto {
    private String names;
    private String lastnames;
    private LocalDate birthdate;

    public ReqCountdownDto(String names, String lastnames, LocalDate birthdate) {
        this.names = names;
        this.lastnames = lastnames;
        this.birthdate = birthdate;
    }
}
