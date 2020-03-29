package com.example.shoppingcart.model.mapper;

import com.example.shoppingcart.entity.Images;
import com.example.shoppingcart.model.dto.ImageDto;

import java.util.Date;

public class ImageMapper {

    public static ImageDto toImageDto(Images images) {

        ImageDto imageDto = new ImageDto();
        imageDto.setId(images.getImageId());
        imageDto.setLink(images.getLink());
        imageDto.setProductId(images.getProduct().getProductId());

        return imageDto;
    }

    public static Images toImages(ImageDto imageDto) {

        Images images = new Images();
        images.setLink(imageDto.getLink());
        images.setCreatedDate(new Date());

        return images;
    }

    public static Images toImages(ImageDto imageDto, int imageId) {

        Images images = new Images();
        images.setImageId(imageId);
        images.setLink(imageDto.getLink());
        images.setCreatedDate(new Date());

        return images;
    }
}
