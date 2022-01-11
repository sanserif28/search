package co.whitetree.searchservice.external.provider.naver.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("api.naver")
public class NaverApiProperty {
    private String url;
    private String clientId;
    private String clientSecret;
}
