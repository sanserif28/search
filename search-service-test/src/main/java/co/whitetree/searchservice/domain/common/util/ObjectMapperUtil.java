package co.whitetree.searchservice.domain.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public abstract class ObjectMapperUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ObjectMapperUtil() {
        // config 설정
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
