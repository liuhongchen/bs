package com.liuhongchen.bsitemconsumer.client.fallback;

import com.liuhongchen.bsitemconsumer.client.RestItemClient;
import org.springframework.stereotype.Component;

@Component
public class ItemClientFallBack implements RestItemClient {


    @Override
    public Integer isbn(String isbn) throws Exception {
        return null;
    }
}
