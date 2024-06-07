package jimmyrai321.recordShopAPI.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "album")
@Data //abstracts getter and setter and toString methods
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column
    String name;

    @Column
    String artist;

    @Column
    int releaseYear;

    @Column
    Genre genre;

    @Column
    String albumInfo;

    @OneToOne(mappedBy = "album",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Stock stock;



}
