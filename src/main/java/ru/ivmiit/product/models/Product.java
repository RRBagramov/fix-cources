package ru.ivmiit.product.models;

import lombok.Builder;
import lombok.Data;

/**
 * 12.03.2018
 *
 * @author Robert Bagramov.
 */
@Data
@Builder
public class Product {
    private int id;
    private String name;
    private double price;
    private String type;
}
