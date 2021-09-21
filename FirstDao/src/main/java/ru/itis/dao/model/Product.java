package ru.itis.dao.model;

public class Product {
    private Long id;
    private String name;
    private String category;
    private String country;
    private Integer price;

    public Product(Long id, String name, String category, String country, Integer price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.country = country;
        this.price = price;
    }


    public Product(String name, String category, String country, Integer price) {
        this.name = name;
        this.category = category;
        this.country = country;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", country='" + country + '\'' +
                ", price=" + price +
                '}';
    }

}
