package com.zentagroup.birthdaycountdown.dto;

/**
 * Ariana González
 * ariana.sgm5@gmail.com
 */

public class RespCountdownDto <T> {
    String name;
    int age;
    String ageText;
    T countdownResult;

    public String getAgeText() {
        return ageText;
    }

    public void setAgeText(String ageText) {
        this.ageText = ageText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public T getCountdownResult() {
        return countdownResult;
    }

    public void setCountdownResult(T countdownResult) {
        this.countdownResult = countdownResult;
    }
}
