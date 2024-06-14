package jimmyrai321.recordShopAPI.service.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jimmyrai321.recordShopAPI.model.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostAlbumDto {

    private Long id;

    @NotBlank(message = "[!] Must specify name of album!")
    private String name;

    @NotBlank(message = "[!] Must specify artist of album!")
    private String artist;

    @Min(value=1880, message = "[!] Release year must be above the year 1880!")
    @NotNull(message = "[!] Must specify release year!")//First commercially sold disc records was created in 1880s so don't accept a year lower than that.
    Integer release_year;

    @NotNull(message = "[!] Must specify genre of album!")
    Genre genre;

    @NotBlank(message = "[!] Must specify album info of album!")
    String album_info;


    @Min(value = 0,message = "[!] Stock has to be greater or equal to the value 0!")
    @NotNull(message = "[!] Must specify stock count!")
    Integer stock_count;

    private String message;

    public PostAlbumDto(Long id, String name, String artist, Integer release_year, Genre genre, String album_info, Integer stock_count) {
        this.id=id;
        this.name = name;
        this.artist = artist;
        this.release_year = release_year;
        this.genre = genre;
        this.album_info = album_info;
        this.stock_count = stock_count;
    }
}
