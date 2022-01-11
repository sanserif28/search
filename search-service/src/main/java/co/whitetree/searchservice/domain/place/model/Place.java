package co.whitetree.searchservice.domain.place.model;

import co.whitetree.searchservice.external.provider.kakao.dto.KakaoSearchResponse;
import co.whitetree.searchservice.external.provider.naver.dto.NaverSearchResponse;
import co.whitetree.searchservice.domain.common.util.StringUtil;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Getter
public class Place {
    private final String title;
    private final String address;
    private final String roadAddress;
    private final String identity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;
        Place place = (Place) o;
        return Objects.equals(getIdentity(), place.getIdentity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentity());
    }

    @Builder
    private Place(String title, String address, String roadAddress) {
        this.title = StringUtil.removeTags(title);
        this.address = StringUtil.removeTags(address);
        this.roadAddress = StringUtil.removeTags(roadAddress);
        this.identity = createIdentity();
    }

    /**
     * 변형된 address 기준으로 동등성 기준을 잡는다.
     * address 가 없을 경우, title 로 동등성 기준을 잡는다.
     */
    private String createIdentity() {
        String modifiedAddress = modifyAddress(this.address);
        String result = StringUtils.hasText(modifiedAddress) ? modifiedAddress : this.title;
        return StringUtil.removeWhitespaces(result);
    }

    /**
     * 행정구역을 같게 변환한다.
     * 서울, 서울시, 서울특별시 -> 서울
     * 경기, 경기도 -> 경기
     * 충청남도, 충남 -> 충남
     * 전라남도, 전남 -> 전남
     * 경상남도, 경남 -> 경남
     */
    private String modifyAddress(String address) {
        if (!StringUtils.hasText(address)) return address;
        String[] split = address.split(" ");
        if (split.length <= 1) return address;

        String first = split[0];
        String modifiedFirst = first.replaceAll("시", "")
                .replaceAll("도", "")
                .replaceAll("특별", "")
                .replaceAll("광역", "")
                .replaceAll("자치", "")
                .replaceAll("청", "")
                .replaceAll("라", "")
                .replaceAll("상", "");
        split[0] = modifiedFirst;

        return String.join("", split);
    }

    public static Place ofKakao(KakaoSearchResponse.Document document) {
        return Place.builder()
                .title(document.getTitle())
                .address(document.getAddress())
                .roadAddress(document.getRoadAddress())
                .build();
    }

    public static Place ofNaver(NaverSearchResponse.Item item) {
        return Place.builder()
                .title(item.getTitle())
                .address(item.getAddress())
                .roadAddress(item.getRoadAddress())
                .build();
    }
}
