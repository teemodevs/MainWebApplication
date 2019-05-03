package teemoDevs.MainWebApplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@Configuration
public class WebSecurityConfigurerAdapterImpl extends WebSecurityConfigurerAdapter {

}
