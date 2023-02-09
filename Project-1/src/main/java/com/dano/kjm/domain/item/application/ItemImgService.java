package com.dano.kjm.domain.item.application;

import com.dano.kjm.domain.item.dao.ItemImgRepository;
import com.dano.kjm.domain.item.entity.ItemImg;
import com.dano.kjm.global.util.CustomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemImgService {

    @Value("${custom.itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    @Transactional
    public void saveImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception {
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if (!CustomUtil.isEmpty(oriImgName)) {
            String property = System.getProperty("user.home");

            imgName = fileService.uploadImg(itemImgLocation, oriImgName, itemImgFile.getBytes());
            imgUrl = property+itemImgLocation + "/" + imgName;
        }
        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);
    }

    @Transactional
    public void updateImg(Long itemImgId, MultipartFile itemImgFile) throws Exception {
        if (!itemImgFile.isEmpty()) {
            ItemImg saveItemImg = itemImgRepository.findById(itemImgId).orElseThrow(EntityNotFoundException::new);

            if(!CustomUtil.isEmpty(saveItemImg.getImgName())) {
                fileService.deleteImg(itemImgLocation + "/" + saveItemImg.getImgName());
            }

            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadImg(itemImgLocation, oriImgName, itemImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;
            saveItemImg.updateItemImg(oriImgName, imgName, imgUrl);
        }
    }

}
