package com.MenuBackend.MenuBackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "products")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodId;

    private String prodName;
    private String prodDescription;
    private int prodPrice;
    private String prodPicture;
    private String prodAvailability;
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cat_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

}
