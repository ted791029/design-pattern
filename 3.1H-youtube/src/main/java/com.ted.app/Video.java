package com.ted.app;

public class Video {
    private String title;
    private String description;
    private int length;

    public Video(String title, String description, int length) {
        setTitle(title);
        setDescription(description);
        setLength(length);
    }

    /**
     * getter & setter
     **/
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
