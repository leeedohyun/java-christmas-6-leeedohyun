package christmas.view;

import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Utils;

public class InputView {

    public int inputDateOfVisit() {
        System.out.println(ViewConstants.VISIT_DATE_QUESTION_MESSAGE);
        return Utils.convertStringToLocalDate(Console.readLine());
    }

    public List<String> inputMenusWithQuantity() {
        System.out.println(ViewConstants.ORDER_INSTRUCTION_MESSAGE);
        return Utils.splitDifferentMenus(Console.readLine());
    }
}
