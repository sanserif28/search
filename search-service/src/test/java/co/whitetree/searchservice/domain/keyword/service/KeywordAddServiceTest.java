package co.whitetree.searchservice.domain.keyword.service;

import co.whitetree.searchservice.domain.keyword.entity.KeywordEntity;
import co.whitetree.searchservice.domain.keyword.repository.KeywordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class KeywordAddServiceTest {
    int THREAD_NUMBER = 10;
    ExecutorService es = Executors.newFixedThreadPool(THREAD_NUMBER);
    CountDownLatch latch = new CountDownLatch(THREAD_NUMBER);

    @Autowired
    KeywordAddService keywordAddService;

    @Autowired
    KeywordRepository keywordRepository;

    @Test
    void 동시처리에서_키워드_추가시_정합성이_지켜진다() throws InterruptedException {
        // given
        String query = "ATM";
        keywordAddService.add(query);

        // when
        for (int i = 0; i < THREAD_NUMBER; i++) {
            es.execute(() -> {

                keywordAddService.add(query);
//                try {
//                    keywordAddService.add(query);
//                } catch (ObjectOptimisticLockingFailureException e) {
//                    System.out.println("낙관적락: " + Thread.currentThread().getName());
//                    keywordAddService.add(query);
//                }
                latch.countDown();
            });
        }
        latch.await();

        // then
        KeywordEntity keywordEntity = keywordRepository.findByQuery(query).get();
        System.out.println("최종카운트 = " + keywordEntity.getSearchCount());
        assertThat(keywordEntity.getSearchCount()).isEqualTo(THREAD_NUMBER + 1);
    }
}