package dev.mvc.art_v1sbm3c;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dev.mvc.gallery.Gallery;
import dev.mvc.tool.Tool;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Windows: path = "C:/kd/deploy/resort_v2sbm3c_blog/gallery/storage";
        // ▶ file:///C:/kd/deploy/resort_v2sbm3c_blog/gallery/storage
      
        // Ubuntu: path = "/home/ubuntu/deploy/resort_v2sbm3c_blog/gallery/storage";
        // ▶ file:////home/ubuntu/deploy/resort_v2sbm3c_blog/gallery/storage
      
        // JSP 인식되는 경로: http://localhost:9093/gallery/storage";
        registry.addResourceHandler("/gallery/storage/**").addResourceLocations("file:///" +  Gallery.getUploadDir());
        
        // JSP 인식되는 경로: http://localhost:9093/attachfile/storage";
        // registry.addResourceHandler("/gallery/storage/**").addResourceLocations("file:///" +  Tool.getOSPath() + "/attachfile/storage/");
        
        // JSP 인식되는 경로: http://localhost:9093/member/storage";
        // registry.addResourceHandler("/gallery/storage/**").addResourceLocations("file:///" +  Tool.getOSPath() + "/member/storage/");
    }
 
}
