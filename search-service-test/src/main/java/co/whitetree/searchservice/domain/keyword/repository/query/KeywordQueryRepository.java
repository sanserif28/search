package co.whitetree.searchservice.domain.keyword.repository.query;

import co.whitetree.searchservice.domain.keyword.entity.KeywordEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static co.whitetree.searchservice.domain.keyword.entity.QKeywordEntity.keywordEntity;

@RequiredArgsConstructor
@Repository
public class KeywordQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<KeywordEntity> findKeywordOrderBySearchCountAndLimit(long limit) {
        return jpaQueryFactory.select(keywordEntity)
                .from(keywordEntity)
                .orderBy(keywordEntity.searchCount.desc())
                .limit(limit)
                .fetch();
    }
}
