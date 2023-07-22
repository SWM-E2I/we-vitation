package com.e2i.wemeet.web.service.profile;

import org.springframework.web.multipart.MultipartFile;

public interface ProfileImageService {

    /*
     * ProfileImage 등록
     */
    void postProfileImage(Long memberId, MultipartFile file, boolean isMain);
}
