package com.zentagroup.birthdaycountdown.dto;

/**
 * Ariana González
 * ariana.sgm5@gmail.com
 */

public class RespCountdownDto <T> {
    private String name;
    private int age;
    private String ageText;
    private T countdownResult;

    public RespCountdownDto() {
    }

    public RespCountdownDto(String name, int age, String ageText) {
        this.name = name;
        this.age = age;
        this.ageText = ageText;
        this.countdownResult = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAgeText(String ageText) {
        this.ageText = ageText;
    }

    public void setCountdownResult(T countdownResult) {
        this.countdownResult = countdownResult;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAgeText() {
        return ageText;
    }

    public T getCountdownResult() {
        return countdownResult;
    }
}
