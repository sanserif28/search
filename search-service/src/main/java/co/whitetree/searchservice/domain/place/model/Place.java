package co.whitetree.searchservice.domain.place.model;

import co.whitetree.searchservice.external.kakao.dto.KakaoSearchResponse;
import co.whitetree.searchservice.external.naver.dto.NaverSearchResponse;
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
    private final String equivalence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;
        Place place = (Place) o;
        return Objects.equals(getEquivalence(), place.getEquivalence());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEquivalence());
    }

    @Builder
    private Place(String title, String address, String roadAddress) {
        this.title = StringUtil.removeTags(title);
        this.address = StringUtil.removeTags(address);
        this.roadAddress = StringUtil.removeTags(roadAddress);
        this.equivalence = createEquivalence();
    }

    /**
     * 변형된 address 기준으로 동등성 비교를 한다.
     * address 가 없을 경우, title 로 동등성 비교를 한다.
     */
    private String createEquivalence() {
        String modifiedAddress = modifyAddress(this.address);
        String equivalence = StringUtils.hasText(modifiedAddress) ? modifiedAddress : this.title;
        return StringUtil.removeWhitespaces(equivalence);
    }

    /**
     * 행정구역을 같게 변환한다.
     * 서울, 서울시, 서울특별시 -> 서울
     * 경기, 경기도 -> 경기
     * 충청남도, 충남 -> 충남
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
