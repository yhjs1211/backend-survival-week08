package kr.megaptera.assignment.DTOs.product;

import kr.megaptera.assignment.Models.product.*;

public class ProductDTO {
    private String id;
    private String userId;
    private String type;
    private String name;
    private Integer price;
    private Integer quantity;
    private String content;

    public ProductDTO() {
    }

    public ProductDTO(String id, String type, String name, Integer price, String content) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.content = content;
    }

    public ProductDTO(String id, String userId, String type, String name, Integer price, Integer quantity, String content) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.content = content;
    }

    public ProductDTO(Product product) {
        this(
                product.id().toString(),
                product.type().toString(),
                product.name(),
                product.price(),
                product.content().toString());
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getContent() {
        return content;
    }
}
