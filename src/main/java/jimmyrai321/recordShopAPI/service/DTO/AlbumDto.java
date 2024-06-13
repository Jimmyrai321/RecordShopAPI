package jimmyrai321.recordShopAPI.service.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jimmyrai321.recordShopAPI.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AlbumDto {

    private long id;

    @NotBlank(message = "[!] Must specify name of album!")
    private String name;

    @NotBlank(message = "[!] Must specify artist of album!")
    private String artist;

    @Min(value=1880, message = "[!] Release year must be above the year 1880!")
    @NotNull(message = "[!] Must specify release year!")//First commercially sold disc records was created in 1880s so don't accept a year lower than that.
    int releaseYear;

    @NotNull(message = "[!] Must specify genre of album!")
    Genre genre;

    @NotBlank(message = "[!] Must specify album info of album!")
    String albumInfo;


    @Min(value = 0,message = "[!] Stock has to be greater or equal to the value 0!")
    @NotNull(message = "[!] Must specify stock count!")
    int stock_count;

    private String message;

    public AlbumDto(String name, String artist, int releaseYear, Genre genre, String albumInfo, int stock_count) {
        this.name = name;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.albumInfo = albumInfo;
        this.stock_count = stock_count;
    }
}
