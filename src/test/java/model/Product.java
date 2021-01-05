package model;

public class Product {
    private String url;
    private String name;
    private int quantity = 1;

    public Product(String url, String name, int quantity)
    {
        this.url = url;
        this.name = name;
        this.quantity = quantity;
    }

    public Product(String productUrl, String name) {
        this.name = name;
        this.url = productUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String productUrl) {
        this.url = productUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String productName) {
        this.name = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int count) {
        this.quantity = count;
    }
}
