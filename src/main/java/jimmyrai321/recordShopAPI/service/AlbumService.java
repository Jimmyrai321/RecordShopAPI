package jimmyrai321.recordShopAPI.service;

import jimmyrai321.recordShopAPI.model.Album;
import jimmyrai321.recordShopAPI.service.DTO.GetAlbumDto;
import jimmyrai321.recordShopAPI.service.DTO.PostAlbumDto;

import java.util.List;

public interface AlbumService {

    List<GetAlbumDto> getAllAlbums();

    List<GetAlbumDto> getAlbumByName(String name);

    PostAlbumDto getAlbumByID(long id);

    PostAlbumDto addAlbum(PostAlbumDto album);
}
