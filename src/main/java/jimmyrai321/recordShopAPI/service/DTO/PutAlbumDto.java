package jimmyrai321.recordShopAPI.service.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jimmyrai321.recordShopAPI.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PutAlbumDto {

     private Long id;

     private String name;

     private String artist;

     @Min(value=1880, message = "[!] Release year must be above the year 1880!")
     Integer release_year;

     Genre genre;

     String album_info;

     @Min(value = 0,message = "[!] Stock has to be greater or equal to the value 0!")
     Integer stock_count;

     private String message;
}
