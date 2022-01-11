package co.whitetree.searchservice.domain.place.policy;

import co.whitetree.searchservice.domain.place.model.PlaceOrderedSet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntersectionAndUnionSortPolicy implements PlaceSortPolicy {

    /**
     * 맨 앞 PlaceOrderedSet 을 시작으로 나머지 PlaceOrderedSet 를 순서대로 교집합 처리한 후,
     * 교집합 결과에 모든 PlaceOrderedSet 을 합집합 처리한다.
     * @param placesList
     * @return 정렬된 PlaceOrderedSet 리스트
     */
    public PlaceOrderedSet sort(List<PlaceOrderedSet> placesList) {
        PlaceOrderedSet result = placesList.stream()
                .reduce(PlaceOrderedSet::orderedIntersection)
                .orElse(PlaceOrderedSet.emptySet());
        placesList.forEach(result::orderedUnion);

        return result;
    }
}
