package kr.sparta.junittest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountCalculatorTest {

    DiscountCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new DiscountCalculator();
    }

    @DisplayName("기본 할인정책")
    @Test
    void testCaseOfDefaultDiscount() {
        double actual = calculator.calculatePrice(50_000, 0.1, false);
        Assertions.assertThat(actual).isEqualTo(45_000.0);
    }

    @DisplayName("VIP 할인정책")
    @Test
    void testCaseOfVip() {
        // VIP 추가할인 + 10만원 추가할인 + 20만원 추가할인
        double actual = calculator.calculatePrice(200_000, 0.2, true);
        Assertions.assertThat(actual).isEqualTo(125_000.0);

        // VIP 추가할인 + 10만원 추가할인 + 20만원 추가할인
        actual = calculator.calculatePrice(300_000, 0.3, true);
        Assertions.assertThat(actual).isEqualTo(170_000.0);

        // VIP 추가할인 + 10만원 이상 추가할인
        actual = calculator.calculatePrice(150_000, 0.1, true);
        Assertions.assertThat(actual).isEqualTo(122_500.0);

        // VIP 추가할인
        actual = calculator.calculatePrice(9_000, 0.1, true);
        Assertions.assertThat(actual).isEqualTo(7_650.0);
    }

    @DisplayName("Non-VIP 10만원 이상 사용시 할인")
    @Test
    void testCaseOfNonVipOver10K() {
        // 10만원 이상 구매, 추가할인 5천원
        double actual = calculator.calculatePrice(100_000, 0.2, false);
        Assertions.assertThat(actual).isEqualTo(75_000.0);
    }

    @DisplayName("Non-VIP 20만원 이상 사용시 할인")
    @Test
    void testCaseOfNonVipOver20K() {
        double actual = calculator.calculatePrice(200_000, 0.2, false);
        Assertions.assertThat(actual).isEqualTo(135_000.0);
    }
}