package co.whitetree.searchservice.external.kakao.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("api.kakao")
public class KakaoApiProperty {
    private String url;
    private String restApiKey;
}
