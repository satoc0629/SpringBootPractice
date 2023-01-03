package om19.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Product {
    @Id
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private Integer price;

    @OneToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProductType productType;
}
