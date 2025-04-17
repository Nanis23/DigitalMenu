package com.MenuBackend.MenuBackend.DTO;

import lombok.Data;

@Data
public class ProductDTO {

    private Long prodId;

    private String prodName;

    private String prodDescription;

    private Integer prodPrice;

    private String prodPicture;

    private String prodAvailability;

    private Long catId;

    private String catName;

}
