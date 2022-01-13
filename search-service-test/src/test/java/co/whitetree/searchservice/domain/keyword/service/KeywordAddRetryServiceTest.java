package co.whitetree.searchservice.domain.keyword.service;

import co.whitetree.searchservice.api.place.facade.PlaceFacade;
import co.whitetree.searchservice.domain.keyword.entity.KeywordEntity;
import co.whitetree.searchservice.domain.keyword.repository.KeywordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class KeywordAddRetryServiceTest {

    @Autowired
    KeywordAddRetryService keywordAddRetryService;

    @Autowired
    KeywordRepository keywordRepository;

    @Test
    void 동시처리에서_키워드_추가시_정합성이_지켜진다() throws InterruptedException {
        // given
        int THREAD_NUMBER = 10;
        ExecutorService es = Executors.newFixedThreadPool(THREAD_NUMBER);
        CountDownLatch latch = new CountDownLatch(THREAD_NUMBER);
        String query = "ATM";

        // when
        for (int i = 0; i < THREAD_NUMBER; i++) {
            es.execute(() -> {
                keywordAddRetryService.addKeywordSearchCount(query);
                latch.countDown();
            });
        }
        latch.await();

        // then
        KeywordEntity keywordEntity = keywordRepository.findByQuery(query).get();
        assertThat(keywordEntity.getSearchCount()).isEqualTo(THREAD_NUMBER);
    }
}