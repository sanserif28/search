package co.whitetree.searchservice.domain.keyword.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "keyword",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"query"})})
@Entity
public class KeywordEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "query")
    private String query;

    @Column(name = "search_count")
    private Long searchCount;

    @Builder
    public KeywordEntity(Long id, String query, Long searchCount) {
        this.id = id;
        this.query = query;
        this.searchCount = searchCount;
    }

    public void incrementSearchCount() {
        this.searchCount += 1;
    }
}
