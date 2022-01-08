package co.whitetree.searchservice.domain.common.external;

import co.whitetree.searchservice.domain.common.external.kakao.dto.KakaoSearchResponse;
import co.whitetree.searchservice.domain.common.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class RestTemplateClient {

    private final RestTemplate restTemplate;

    private static final int CONNECT_TIMEOUT_MILLISECONDS = 3000;
    private static final int READ_TIMEOUT_MILLISECONDS = 7000;

    public RestTemplateClient() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(CONNECT_TIMEOUT_MILLISECONDS);
        factory.setReadTimeout(READ_TIMEOUT_MILLISECONDS);

        this.restTemplate = new RestTemplate(factory);
    }

    public <T> T get(String url,
                     HttpHeaders httpHeaders,
                     Class<T> responseClass,
                     Object queryParams) {

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParams(MultiValueMapConverter.convert(ObjectMapperUtil.getObjectMapper(), queryParams))
                .build();

        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);

        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(uri.toString(), HttpMethod.GET, httpEntity, responseClass);
            return responseEntity.getBody();
        } catch (RestClientException e) {
            log.error("RestTemplate 요청/응답 중 오류가 발생했습니다.", e);
            return null;
        }
    }
}
