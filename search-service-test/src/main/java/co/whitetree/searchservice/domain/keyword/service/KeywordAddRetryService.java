package co.whitetree.searchservice.domain.keyword.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.persistence.LockTimeoutException;

@Slf4j
@RequiredArgsConstructor
@Service
public class KeywordAddRetryService {
    private final KeywordAddService keywordAddService;

    public void addKeywordSearchCount(String query) {
        int repeatCount = 10; // 동시에 접근할 수 있는 쓰레드 수만큼 설정하면 재시도시 성공 확률이 높아진다.
        while (repeatCount > 0) {
            try {
                keywordAddService.add(query);
                repeatCount = 0;
            } catch (OptimisticLockingFailureException e) {
                log.error("동시 업데이트로 낙관적락 예외가 발생하여 재시도 합니다. (검색어: {}, 재시도 남은 횟수: {})", query, --repeatCount);
            } catch (DataIntegrityViolationException e) {
                log.error("데이터 정합성이 맞지 않아 재시도 합니다. (검색어: {}, 재시도 남은 횟수: {})", query, --repeatCount);
            } catch (LockTimeoutException e) { // 비관적락 타임아웃 설정시 타임아웃 초과시 발생
                log.error("키워드({}) 검색 횟수 업데이트 중 타임아웃 오류가 발생했습니다.", query);
                repeatCount = 0;
            }
        }
    }
}
