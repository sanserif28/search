package co.whitetree.searchservice.domain.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class StringUtil {

    public static String removeTags(String s) {
        return s.replaceAll("<[^>]*>", "");
    }

    public static String removeWhitespaces(String s) {
        return s.replaceAll("\\s+", "");
    }
}
