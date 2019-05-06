package teemoDevs.MainWebApplication.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import teemoDevs.MainWebApplication.auth.authorities.AuthoritiesExtractorImpl;

@Configuration
@EnableOAuth2Client
public class WebSecurityConfigurerAdapterImpl extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/error", "/webjars/**", "/resources/**", "/login**").permitAll()
                .anyRequest().authenticated()
                .and().oauth2Login();
    }

    @Bean
    public AuthoritiesExtractor teemoAuthoritiesExtractor() {
        return new AuthoritiesExtractorImpl();
    }

}