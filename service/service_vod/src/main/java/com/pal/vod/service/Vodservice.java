package com.pal.vod.service;

import org.springframework.web.multipart.MultipartFile;

public interface Vodservice {
    String uploadVideoAly(MultipartFile file);
}
