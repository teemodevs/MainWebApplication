package teemoDevs.MainWebApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.core.user.OAuth2User;
import teemoDevs.MainWebApplication.auth.domain.CustomOAuth2User;

@Configuration
@EnableOAuth2Client
public class WebSecurityConfigurerAdapterImpl extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.oauth2.client.provider.teemo.logoutSuccessUrl}")
    private String logoutSuccessUrl;

    // CustomAuth2UserService를 @Component로 등록해야 함
    @Autowired
    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // CSRF 토큰 활용 비활성화
                .csrf().disable()

                // 모든 URI에 대해 요청을 허용
                .authorizeRequests()
                .antMatchers("/**").permitAll()

                // 모든 요청은 인가받은 사람만 요청할 수 있음
                .anyRequest().authenticated()

                /** {@link org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer} 설정 **/
                .and().oauth2Login()

                            // 유저 정보 엔드포인트 설정
                            .userInfoEndpoint()

                                    // CustomOAuth2User 클래스를 teemo 클라이언트에 대한 유저 정보 클래스로 사용, Default : OAuth2User
                                    .customUserType(CustomOAuth2User.class, "teemo")

                                    // CustomOAuth2User 클래스를 얻는 데 사용하는 OAuth2UserService 등록, Default : DefaultOAuth2UserService
                                    .userService(oAuth2UserService);

        // 로그아웃 URL 설정
        http
                .logout().logoutSuccessUrl(logoutSuccessUrl);

        // h2 콘솔 X-Frame-Options in Spring Security 중지
        http
                .headers().frameOptions().disable();
    }

}