package com.cts.openbankx.model;


	import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

	@Entity
	@Table(name = "api_products")
	public class APIProduct {

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

		public String getEndpointsJSON() {
			return endpointsJSON;
		}

		public void setEndpointsJSON(String endpointsJSON) {
			this.endpointsJSON = endpointsJSON;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public List<APIPlan> getPlans() {
			return plans;
		}

		public void setPlans(List<APIPlan> plans) {
			this.plans = plans;
		}

		@Id
	    @GeneratedValue(strategy = GenerationType.UUID)
	    private UUID productId;

	    private String name;
	    private String description;

	    @Column(columnDefinition = "TEXT")
	    private String endpointsJSON;

	    private String status; // Active/Inactive

	    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<APIPlan> plans = new ArrayList<>();

	    // getters and setters

}
