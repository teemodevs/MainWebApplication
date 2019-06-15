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
/**
 * 커스터마이징 OAuth2UserService
 *
 * */
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    /**
     * Resource Server의 userInfoUri로 부터 받은 JSON 포맷을 파싱하여 권한을 추출
     * CustomAuthoritiesExtractor를 @Component로 등록해야 함
     * */
    @Autowired
    private AuthoritiesExtractor authoritiesExtractor;

    /**
     * Resource Server의 userInfoUri로 부터 받은 JSON 포맷의 'authentication'을 파싱하여 CustomOAuth2User 객체로 바인딩 후 리턴
     * */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // Resource Server의 /me를 호출한 결과를 바탕으로 OAuth2User를 만듦. (Default : DefaultOAuth2User)
        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("OAuth2UserRequest.toString() : " + userRequest.toString());
        System.out.println("OAuth2UserRequest.getAccessToken() : " + userRequest.getAccessToken());
        System.out.println("OAuth2UserRequest.getAdditionalParameters() : " + userRequest.getAdditionalParameters());
        System.out.println("OAuth2UserRequest.getClientRegistration() : " + userRequest.getClientRegistration());

        System.out.println("OAuth2User.toString() : " + oAuth2User.toString());
        System.out.println("OAuth2User.getName() : " + oAuth2User.getName());
        System.out.println("OAuth2User.getAttributes() : " + oAuth2User.getAttributes());
        System.out.println("OAuth2User.getAuthorities() : " + oAuth2User.getAuthorities());


        List<GrantedAuthority> grantedAuthorityList = authoritiesExtractor.extractAuthorities(oAuth2User.getAttributes());
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(grantedAuthorityList, oAuth2User.getAttributes());
        customOAuth2User.setName(oAuth2User.getName());

        return customOAuth2User;
    }
}

