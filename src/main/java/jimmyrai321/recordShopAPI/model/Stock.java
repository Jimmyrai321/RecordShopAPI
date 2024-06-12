package jimmyrai321.recordShopAPI.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @Id
    @Column(name="album_id")
    private Long id;

    @Column(columnDefinition = "integer default 0")
    private int stock_count;

    @OneToOne
    @MapsId
    @JoinColumn(name = "album_id")
    private Album album;

}
