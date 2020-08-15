package com.zentagroup.birthdaycountdown.dto;

/**
 * Ariana González
 * ariana.sgm5@gmail.com
 */

public class Poem {
    String title;
    String content;
    String url;
    Poet poet;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Poet getPoet() {
        return poet;
    }

    public void setPoet(Poet poet) {
        this.poet = poet;
    }
}
