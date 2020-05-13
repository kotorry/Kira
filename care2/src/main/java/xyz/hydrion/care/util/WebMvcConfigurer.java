package xyz.hydrion.care.util;


        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:C:\\Users\\kira\\Desktop\\care2\\src\\main\\resources\\static\\assets\\images\\");
        registry.addResourceHandler("/js/**").addResourceLocations("file:C:\\Users\\kira\\Desktop\\care2\\src\\main\\resources\\static\\assets\\js\\");
        registry.addResourceHandler("/css/**").addResourceLocations("file:C:\\Users\\kira\\Desktop\\care2\\src\\main\\resources\\static\\assets\\css\\");

    }
}