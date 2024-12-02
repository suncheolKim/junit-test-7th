package kr.sparta.junittest;

public class DiscountCalculator {
    private static final double SHIPPING_COST = 5_000.0;

    public double calculatePrice(double price, double discountRate) {
        return calculatePrice(price, discountRate, false);
    }

    public double calculatePrice(double price, double discountRate, boolean isVip) {
        double discountedPrice = price - (price * discountRate);

        // VIP 고객은 추가 5% 할인
        if (isVip) {
            discountedPrice -= price * 0.05;
        }

        // 10만원 이상 구매시, 택배비 지원으로 추가 5천원 할인
        if (price >= 100_000) {
            discountedPrice -= SHIPPING_COST;
        }

        // 20만원 이상 구매시, 추가 2만원 할인
        if (price >= 200_000) {
            discountedPrice -= 20_000.0;
        }

        return discountedPrice;
    }
}
