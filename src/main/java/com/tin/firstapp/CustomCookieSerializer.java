package com.tin.firstapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * Created by VAN_TIEN on 10/04/2017.
 */
@Configuration
public class CustomCookieSerializer {
    @Bean
    public DefaultCookieSerializer cookieSerializer()
    {
        final DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookiePath( "/" );

        return serializer;
    }
}
