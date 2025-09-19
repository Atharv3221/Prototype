import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")            // Apply to all endpoints
                .allowedOriginPatterns("*")  // Allow any origin using pattern matching
                .allowedMethods("*")         // Allow all HTTP methods (GET, POST, etc.)
                .allowedHeaders("*")         // Allow all headers
                .allowCredentials(true);    // Allow credentials (cookies/auth headers)
    }
}
