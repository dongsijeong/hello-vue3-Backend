package demo.mo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import demo.mo.interceptor.JwtAuthinInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * JWT認証インターセプターの取得
     * @return JWT認証インターセプター
     */
    @Bean
    public JwtAuthinInterceptor getSecurityInterceptor() {
        return new JwtAuthinInterceptor();
    }
      /**
      * リソースハンドラーの追加
      * @param registry
      */
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/swagger-ui/**")
                 .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                 .resourceChain(false);
     }
    /**
     * CORSマッピングの追加
     * @param registry
     */
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOriginPatterns("*")
        .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS").allowCredentials(true).maxAge(3600);
    }

    /**
     * インターセプターの追加
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        addInterceptor.addPathPatterns("/v1/**")
        .excludePathPatterns("/**/.html")
        .excludePathPatterns("/**/.htm")
        .excludePathPatterns("/**/.css")
        .excludePathPatterns("/**/.js")
        .excludePathPatterns("/**/.png")
        .excludePathPatterns("/v1/jwtauth/**")
        .excludePathPatterns("/v1/Redirect/**")
        .excludePathPatterns("/v1/checkMessage/**")
        .excludePathPatterns("/403")
        .excludePathPatterns("/v1/vender/**")
        .excludePathPatterns("/v1/health");
    }
}
