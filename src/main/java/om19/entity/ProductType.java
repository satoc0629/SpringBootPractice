package om19.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product_type")
public class ProductType {
    @Id
    @Column
    private Long id;
    @Column
    private String name;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", referencedColumnName = "type_id")
    private Product product;

}
