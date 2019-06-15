package teemoDevs.MainWebApplication.auth;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 커스터마이징 AuthoritiesExtractor
 * {@link AuthoritiesExtractor}를 구현
 * Resource Server의 userInfoUri로 부터 받은 JSON 포맷의 'authorities'를 파싱하여 권한을 추출하여 리턴
 * */
@Component
public class CustomAuthoritiesExtractor implements AuthoritiesExtractor {

    @Override
    public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(asAuthorities(map));
    }

    private String asAuthorities(Map<String, Object> map) {
        List<String> authorities = new ArrayList<>();
        List<LinkedHashMap<String, String>> authz =
                (List<LinkedHashMap<String, String>>) map.get("authorities");
        for (LinkedHashMap<String, String> entry : authz) {
            authorities.add(entry.get("authority"));
        }
        return String.join(",", authorities);
    }
}
