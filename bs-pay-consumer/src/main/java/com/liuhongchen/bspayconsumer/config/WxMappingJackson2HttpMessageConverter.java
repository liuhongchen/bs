package com.liuhongchen.bspayconsumer.config;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:WxMappingJackson2HttpMessageConverter
 * Package:com.liuhongchen.minipro.service.config
 * Description:
 *
 * @date:2020-03-07 10:43
 * @author:892698613@qq.com
 */
public class WxMappingJackson2HttpMessageConverter  extends MappingJackson2HttpMessageConverter {

    public WxMappingJackson2HttpMessageConverter(){
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.TEXT_HTML);
        setSupportedMediaTypes(mediaTypes);
    }

}
