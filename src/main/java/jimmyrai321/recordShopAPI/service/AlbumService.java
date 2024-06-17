package jimmyrai321.recordShopAPI.service;

import jimmyrai321.recordShopAPI.model.Genre;
import jimmyrai321.recordShopAPI.service.DTO.GetAlbumDto;
import jimmyrai321.recordShopAPI.service.DTO.PostAlbumDto;
import jimmyrai321.recordShopAPI.service.DTO.PutAlbumDto;

import java.util.List;
import java.util.Map;

public interface AlbumService {

    List<GetAlbumDto> getAllAlbums();

    Map<String,String> getAlbumInfoByName(String name);

    GetAlbumDto getAlbumByID(Long id);

    List<GetAlbumDto> getAlbumsByArtist(String artist);

    List<GetAlbumDto> getAlbumsByRelease(Integer release_year);

    List<GetAlbumDto> getAlbumsByGenre(Genre genre);

    PostAlbumDto addAlbum(PostAlbumDto album);

    PutAlbumDto updateAlbum(Long id, PutAlbumDto updateData);

    String removeAlbum(Long id);


}
