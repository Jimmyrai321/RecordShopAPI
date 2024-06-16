package jimmyrai321.recordShopAPI.service;

import jimmyrai321.recordShopAPI.model.Album;
import jimmyrai321.recordShopAPI.service.DTO.GetAlbumDto;
import jimmyrai321.recordShopAPI.service.DTO.PostAlbumDto;
import jimmyrai321.recordShopAPI.service.DTO.PutAlbumDto;

import java.util.List;

public interface AlbumService {

    List<GetAlbumDto> getAllAlbums();

    List<GetAlbumDto> getAlbumByName(String name);

    GetAlbumDto getAlbumByID(long id);

    PostAlbumDto addAlbum(PostAlbumDto album);

    PutAlbumDto updateAlbum(Long id, PutAlbumDto updateData);
}
