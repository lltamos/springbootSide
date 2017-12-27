package org.font.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource({"classpath:config/application.properties"})
public class BlogProperties {
    @Value("${com.font.blog.name}")
    private String name;
    @Value("${com.font.blog.title}")
    private String title;

    @Value("${com.font.blog.bigNumber}")
    private String bigNumber;

    @Value("${com.font.blog.desc}")
    private String desc;

}
