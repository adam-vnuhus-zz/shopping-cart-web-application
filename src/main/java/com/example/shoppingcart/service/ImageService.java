package com.example.shoppingcart.service;

import com.example.shoppingcart.model.dto.ImageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageService {

    List<ImageDto> getListImages();

    ImageDto getImagetById(int imageId);

    ImageDto createImage(ImageDto imageDto);

    ImageDto updateImage(ImageDto imageDto, int imageId);

    void deleteImage(int imageId);
}
