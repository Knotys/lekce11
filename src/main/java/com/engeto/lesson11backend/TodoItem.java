package com.engeto.lesson11backend;

public class TodoItem {
    private Long id;
    private String item;
    private Boolean done;

    public TodoItem(Long id) {
        this.id = id;
    }

    public TodoItem(String item, Boolean done) {
        this.item = item;
        this.done = done;
    }

    public TodoItem(Long id, String item, Boolean done) {
        this.id = id;
        this.item = item;
        this.done = done;
    }

    public TodoItem() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
