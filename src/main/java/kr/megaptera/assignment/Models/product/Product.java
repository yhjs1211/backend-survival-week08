package kr.megaptera.assignment.Models.product;

import jakarta.persistence.*;
import kr.megaptera.assignment.DTOs.product.*;
import kr.megaptera.assignment.DTOs.review.*;
import kr.megaptera.assignment.Models.*;
import kr.megaptera.assignment.Models.review.*;
import kr.megaptera.assignment.exceptions.*;

import java.util.*;

@Entity
@Table(name = "products")
public class Product {

    @Embedded
    @Id
    @Column(name = "id")
    private ProductId id;
    @Embedded
    @Column(name = "type")
    private ProductType type;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Integer price;
    @Embedded
    private MultilineText content;
    @Embedded
    private Quantity quantity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    @OrderBy("id")
    private List<Review> reviews;

    public Product() {
    }

    public Product(ProductId id, ProductType type, String name, Integer price, MultilineText content) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.content = content;
    }

    public Product(ProductType type, String name, Integer price, MultilineText content) {
        this.id = ProductId.generate();
        this.type = type;
        this.name = name;
        this.price = price;
        this.content = content;
    }

    public ProductId id() {
        return id;
    }

    public ProductType type() {
        return type;
    }

    public String name() {
        return name;
    }

    public Integer price() {
        return price;
    }

    public MultilineText content() {
        return content;
    }

    public Quantity quantity() {
        return quantity;
    }

    public void update(ProductUpdateDTO productUpdateDTO) {
        this.name = productUpdateDTO.getName();
        this.price = productUpdateDTO.getPrice();
        this.content = MultilineText.of(productUpdateDTO.getContent());
    }

    public List<Review> list() {
        return new ArrayList<>(reviews);
    }

    public Review addReview(String productId, ReviewCreateDTO reviewCreateDTO) {
        Review review = new Review(
                ProductId.of(productId),
                reviewCreateDTO.getAuthor(),
                MultilineText.of(reviewCreateDTO.getContent())
        );

        reviews.add(review);

        return review;
    }

    public void updateReview(String id, ReviewUpdateDTO reviewUpdateDTO) {
        Review review = reviews.stream().filter(
                t -> t.id().equals(id)
        ).findFirst().get();

        if (review == null) {
            throw new ReviewNotFound("존재하지 않는 리뷰입니다.");
        }

        review.update(reviewUpdateDTO);
    }

    public void removeReview(String id) {
        Review review = reviews.stream().filter(
                        t -> t.id().equals(id)
                ).findFirst()
                .get();

        if (review == null) {
            throw new ReviewNotFound("존재하지 않는 리뷰입니다.");
        }

        reviews.remove(review);
    }

    public void updateQuantity(Integer quantity) {
        this.quantity = Quantity.of(quantity);
    }
}
