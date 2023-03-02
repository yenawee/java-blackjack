package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ValidatorTest {
    static Validator validator = new Validator();

    private static Stream<Arguments> provideFailPlayerNames() {
        return Stream.of(
                Arguments.of(List.of("a", "b", "c", "d", "e", "f", "g", "h")),
                Arguments.of(List.of()));
    }

    private static Stream<Arguments> provideSuccessPlayerNames() {
        return Stream.of(Arguments.of(List.of("a")),
                Arguments.of(List.of("a", "b", "c", "d", "e", "f", "g")));
    }

    @DisplayName("player 수 Fail 테스트")
    @ParameterizedTest
    @MethodSource("provideFailPlayerNames")
    void playerCountFail(List<String> input) {
        assertThatThrownBy(() -> validator.validatePlayerNames(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("player 수 성공 테스트")
    @ParameterizedTest
    @MethodSource("provideSuccessPlayerNames")
    void playerCountSuccess(List<String> input) {
        assertThatNoException().isThrownBy(() -> validator.validatePlayerNames(input));
    }
}
