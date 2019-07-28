package teemoDevs.MainWebApplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer {
    /**
     * "/resources/img/developers/**" 로 들어오는 url은
     * HttpServletRequest.getSession().getServletContext().getRealPath("/") 쪽의 리소스에 매핑
     * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/img/developers/**").addResourceLocations("/");
    }
}
