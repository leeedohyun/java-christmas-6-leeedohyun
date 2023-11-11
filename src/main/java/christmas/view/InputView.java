package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Utils;
import java.time.LocalDate;
import java.util.List;

public class InputView {

    public LocalDate inputVisitedDate() {
        return Utils.convertStringToLocalDate(Console.readLine());
    }

    public List<String> inputMenusWithQuantity() {
        return Utils.splitDifferentMenus(Console.readLine());
    }
}
