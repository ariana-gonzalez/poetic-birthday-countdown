package com.zentagroup.birthdaycountdown.dto;

/**
 * Ariana González
 * ariana.sgm5@gmail.com
 */

public class Poet {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Poet{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
