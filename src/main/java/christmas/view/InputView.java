package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Utils;
import java.time.LocalDate;
import java.util.List;

public class InputView {

    public LocalDate inputDateOfVisit() {
        System.out.println(ViewConstants.VISIT_DATE_QUESTION_MESSAGE);
        return Utils.convertStringToLocalDate(Console.readLine());
    }

    public List<String> inputMenusWithQuantity() {
        System.out.println(ViewConstants.ORDER_INSTRUCTION_MESSAGE);
        return Utils.splitDifferentMenus(Console.readLine());
    }
}
