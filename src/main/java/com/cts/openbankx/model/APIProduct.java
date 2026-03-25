package com.cts.openbankx.model;

import java.util.UUID;
import java.sql.Types;
import org.hibernate.type.SqlTypes; // Import this
import com.cts.openbankx.enums.ProductStatus;
import org.hibernate.annotations.JdbcTypeCode; // Import this
import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

@Entity
@Table(name = "api_products")
//@Data
//@AllArgsConstructor
//@Builder
//@NoArgsConstructor
public class APIProduct {
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;
    
    @Column(name="name", nullable=false, length=100)
    private String name;

    @JdbcTypeCode(Types.LONGVARCHAR) // Replaces columnDefinition="TEXT"
    @Column(name="description")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private ProductStatus status;

    @JdbcTypeCode(SqlTypes.JSON) // Replaces columnDefinition="JSON"
    @Column(name="endpoints_json")
    private String endpointsJSON; 
    
    public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public String getEndpointsJSON() {
		return endpointsJSON;
	}

	public void setEndpointsJSON(String endpointsJSON) {
		this.endpointsJSON = endpointsJSON;
	}

}