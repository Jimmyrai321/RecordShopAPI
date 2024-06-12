package jimmyrai321.recordShopAPI.model;


import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

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
    private long id;


    @Column(nullable = false)
    String name;

    @Column(nullable = false)
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
