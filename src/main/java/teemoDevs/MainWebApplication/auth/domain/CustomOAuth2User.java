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
    private List<GrantedAuthority> authorities;
    private Map<String, Object> attributes;
    private String name;
    private String email;

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