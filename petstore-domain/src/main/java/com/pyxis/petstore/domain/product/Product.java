package com.pyxis.petstore.domain.product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity @Access(AccessType.FIELD) @Table(name = "products")
public class Product implements Serializable {

	@SuppressWarnings("unused")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Id Long id;

    private @NotNull String number;

    private @NotNull String name;
    private String description;

    @Embedded @AttributeOverrides(
        @AttributeOverride(name = "fileName", column = @Column(name = "photo_file_name"))
    )
    private Attachment photo;

    Product() {}

    public Product(String number, String name) {
        this.number = number;
		this.name = name;
	}

    public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

    public String getDescription() {
		return description;
	}

    public void setDescription(String description) {
		this.description = description;
	}

    public String getPhotoFileName() {
        return photo != null ? photo.getFileName() : null;
    }

    public void attachPhoto(Attachment photo) {
        this.photo = photo;
	}

    public boolean hasPhoto() {
        return photo != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (number != null ? !number.equals(product.number) : product.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }

    @Override
    public String toString() {
        return number + " (" + name + ")"; 
	}
}
