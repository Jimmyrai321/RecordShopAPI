package jimmyrai321.recordShopAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Stock")
@Data //abstracts getter and setter and toString methods
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @Id
    @Column(name = "album_id")
    private long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "album_id")
    private Album album;

    @Column
    int stockCount;


}
