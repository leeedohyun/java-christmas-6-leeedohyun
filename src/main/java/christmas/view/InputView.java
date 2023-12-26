package christmas.view;

import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Utils;

public class InputView {

    public int inputDateOfVisit() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return Utils.convertStringToLocalDate(Console.readLine());
    }

    public List<String> inputMenusWithQuantity() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return Utils.splitDifferentMenus(Console.readLine());
    }
}
