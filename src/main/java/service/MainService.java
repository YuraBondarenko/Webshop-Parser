package service;

import java.util.List;
import model.Product;

public interface MainService {
    List<Product> getProducts(String url);

    public String getStringFromProducts(List<Product> products);
}
