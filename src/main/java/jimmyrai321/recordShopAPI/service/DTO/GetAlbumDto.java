package jimmyrai321.recordShopAPI.service.DTO;

import jimmyrai321.recordShopAPI.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetAlbumDto{

    private Long id;

    private String name;

    private String artist;

    private Integer release_year;

    private Genre genre;

    private String album_info;

    private Integer stock_count;



}
