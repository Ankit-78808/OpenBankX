package com.cts.openbankx.model;

import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "api_products")
public class APIProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;

    private String name;
    private String description;
    private String status;

    @Lob
    private String endpointsJSON; // Hibernate will map this to LONGTEXT/TEXT automatically

    // Getters and Setters
    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getEndpointsJSON() { return endpointsJSON; }
    public void setEndpointsJSON(String endpointsJSON) { this.endpointsJSON = endpointsJSON; }
}