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

    public String getNames() {
        return this.names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastnames() {
        return this.lastnames;
    }

    public void setLastnames(String lastnames) {
        this.lastnames = lastnames;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
