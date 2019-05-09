package teemoDevs.MainWebApplication.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import teemoDevs.MainWebApplication.auth.domain.CustomOAuth2User;

import java.util.List;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    // CustomAuthoritiesExtractor를 @Component로 등록해야 함
    @Autowired
    private AuthoritiesExtractor authoritiesExtractor;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        List<GrantedAuthority> grantedAuthorityList = authoritiesExtractor.extractAuthorities(oAuth2User.getAttributes());
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(grantedAuthorityList, oAuth2User.getAttributes());
        customOAuth2User.setName(oAuth2User.getName());

        return customOAuth2User;
    }
}

