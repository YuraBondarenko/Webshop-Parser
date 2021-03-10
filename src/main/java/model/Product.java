package model;

import java.util.Objects;

public class Product {
    private Long id;
    private String category;
    private String title;
    private String url;
    private String currency;
    private double price;
    private double priceWithoutDiscount;
    private double priceWithShipping;
    private String delivery;
    private int numberOfPurchases;
    private String discount;
    private String description;
    private boolean verifiedSeller;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceWithoutDiscount() {
        return priceWithoutDiscount;
    }

    public void setPriceWithoutDiscount(double priceWithoutDiscount) {
        this.priceWithoutDiscount = priceWithoutDiscount;
    }

    public double getPriceWithShipping() {
        return priceWithShipping;
    }

    public void setPriceWithShipping(double priceWithShipping) {
        this.priceWithShipping = priceWithShipping;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public int getNumberOfPurchases() {
        return numberOfPurchases;
    }

    public void setNumberOfPurchases(int numberOfPurchases) {
        this.numberOfPurchases = numberOfPurchases;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVerifiedSeller() {
        return verifiedSeller;
    }

    public void setVerifiedSeller(boolean verifiedSeller) {
        this.verifiedSeller = verifiedSeller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0
                && Double.compare(product.priceWithoutDiscount, priceWithoutDiscount) == 0
                && Double.compare(product.priceWithShipping, priceWithShipping) == 0
                && numberOfPurchases == product.numberOfPurchases && Objects.equals(id, product.id)
                && Objects.equals(category, product.category)
                && Objects.equals(title, product.title)
                && Objects.equals(url, product.url) && Objects.equals(currency, product.currency)
                && Objects.equals(delivery, product.delivery)
                && Objects.equals(discount, product.discount)
                && Objects.equals(description, product.description)
                && Objects.equals(verifiedSeller, product.verifiedSeller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, title, url, currency, price,
                priceWithoutDiscount, priceWithShipping, delivery,
                numberOfPurchases, discount, description, verifiedSeller);
    }

    @Override
    public String toString() {
        return "Product{"
                + "id=" + id
                + ", category='" + category + '\''
                + ", title='" + title + '\''
                + ", url='" + url + '\''
                + ", currency='" + currency + '\''
                + ", price=" + price
                + ", priceWithoutDiscount=" + priceWithoutDiscount
                + ", priceWithShipping=" + priceWithShipping
                + ", delivery='" + delivery + '\''
                + ", numberOfPurchases='" + numberOfPurchases + '\''
                + ", discount='" + discount + '\''
                + ", description='" + description + '\''
                + ", verifiedSeller=" + verifiedSeller
                + '}';
    }
}
