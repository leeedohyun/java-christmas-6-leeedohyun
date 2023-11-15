package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Utils;
import java.time.LocalDate;
import java.util.List;

public class InputView {

    public LocalDate inputDateOfVisit() {
        return Utils.convertStringToLocalDate(Console.readLine());
    }

    public List<String> inputMenusWithQuantity() {
        return Utils.splitDifferentMenus(Console.readLine());
    }
}
