package co.whitetree.searchservice.domain.place.model;

import co.whitetree.searchservice.domain.common.external.kakao.dto.KakaoSearchResponse;
import co.whitetree.searchservice.domain.common.external.naver.dto.NaverSearchResponse;
import co.whitetree.searchservice.domain.common.util.StringUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Place {
    private final String title;
    private final String address;
    private final String roadAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;
        Place place = (Place) o;
        String title1 = StringUtil.removeWhitespaces(getTitle());
        String title2 = StringUtil.removeWhitespaces(place.getTitle());
        String address1 = StringUtil.removeWhitespaces(getAddress());
        String address2 = StringUtil.removeWhitespaces(place.getAddress());
        String roadAddress1 = StringUtil.removeWhitespaces(getRoadAddress());
        String roadAddress2 = StringUtil.removeWhitespaces(place.getRoadAddress());

        return Objects.equals(title1, title2) &&
                Objects.equals(address1, address2) &&
                Objects.equals(roadAddress1, roadAddress2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAddress(), getRoadAddress());
    }

    @Builder
    private Place(String title, String address, String roadAddress) {
        this.title = StringUtil.removeTags(title);
        this.address = StringUtil.removeTags(address);
        this.roadAddress = StringUtil.removeTags(roadAddress);
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
