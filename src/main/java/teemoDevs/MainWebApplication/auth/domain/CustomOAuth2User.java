package teemoDevs.MainWebApplication.auth.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 커스터마이징 OAuth2User
 * {@link OAuth2User} 인터페이스를 구현하여 추가적인 정보가 필요한 경우 넣어줄 수 있도록 함
 * */
public class CustomOAuth2User implements OAuth2User {
    private List<GrantedAuthority> authorities; // 권한
    private Map<String, Object> attributes;     // 커스텀 정보
    private String name;                        // 이름
    private String email;                       // 이메일

    /**
     * 모든 파라미터는 ResourceServer로 부터 받은 정보를 할당
     * @param authorities 권한 정보 리스트
     * @param attributes  추가 정보 맵. 이름, 이메일 등 부가적인 정보가 들어있다.
     * */
    public CustomOAuth2User(List<GrantedAuthority> authorities, Map<String, Object> attributes) {
        this.authorities = authorities;
        this.attributes = attributes;
        this.name = (String) attributes.get("name");
        this.email = (String) attributes.get("email");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new HashMap<>();
            this.attributes.put("name", this.getName());
            this.attributes.put("email", this.getEmail());
        }
        return attributes;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

}