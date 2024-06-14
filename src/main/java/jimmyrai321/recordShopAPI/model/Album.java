package jimmyrai321.recordShopAPI.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "albums")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String artist;

    @Column
    Integer release_year;

    @Column
    Genre genre;

    @Column
    String album_info;


    @OneToOne(mappedBy = "album",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Stock stock;



}
