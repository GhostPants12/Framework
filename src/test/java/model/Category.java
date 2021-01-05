package model;

public class Category {
    private String name;
    private String sorting;

    public Category(String name, String sorting){
        this.name = name;
        this.sorting = sorting;
    }

    public Category(String name){
        this.name = name;
    }

    public String getSorting() {
        return sorting;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}