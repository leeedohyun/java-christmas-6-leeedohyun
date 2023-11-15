package christmas.view;

import java.util.List;

public final class ViewConstants {

    static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    static final String VISIT_DATE_QUESTION_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    static final String ORDER_INSTRUCTION_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    static final String EVENT_PREVIEW_MESSAGE_FORMAT = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    static final String ORDER_MENU_HEADER = "<주문 메뉴>";
    static final String ORDER_MENU_FORMAT = "%s %d개\n";
    static final String PRICE_BEFORE_DISCOUNT_HEADER = "<할인 전 총주문 금액>";
    static final String PRICE_FORMAT = "%s원\n";
    static final String DISCOUNT_PRICE_FORMAT = "-%s원\n";
    static final String TOTAL_BENEFIT_PRICE_HEADER = "<총혜택 금액>";
    static final String DISCOUNTED_PRICE_HEADER = "<할인 후 예상 결제 금액>";
    static final String BADGE_HEADER = "<12월 이벤트 배지>";
    static final String DEFAULT_GIVEAWAY_MENU_HEADER = "<증정 메뉴>";
    static final String BENEFIT_DETAIL_FORMAT = "%s: -%s원\n";
    static final List<String> BENEFIT_DETAIL_MESSAGES = List.of("크리스마스 디데이 할인", "평일 할인", "주말 할인", "특별 할인");
    static final int CHRISTMAS_DAY_DISCOUNT_INDEX = 0;
    static final int WEEKDAY_DISCOUNT_INDEX = 1;
    static final int WEEKEND_DISCOUNT_INDEX = 2;
    static final int SPECIAL_DISCOUNT_INDEX = 3;
    static final String BENEFIT_HEADER = "<혜택 내역>";
    static final String GIVEAWAY_EVENT_MESSAGE = "증정 이벤트";

    private ViewConstants() {
    }
}