package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private String promo;
    private List<Product> productList;

    public Cart(List<Product> products, String promo){
        this.productList = products;
        this.promo = promo;
    }

    public Cart(String promo){
        this.promo = promo;
    }

    public List<Product> getProductList(){
        return new ArrayList<Product>(productList);
    }

    public Product getProduct(String name)
    {
        for (Product prod: productList
             ) {
            if(prod.getName() == name)
            {
                return prod;
            }
        }

        return null;
    }

    public Product getProduct(int index){
        return productList.get(index);
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
