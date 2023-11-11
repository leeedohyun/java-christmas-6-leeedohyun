package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Utils;
import java.util.List;

public class InputView {

    public int inputVisitedDate() {
        return Utils.convertStringToInt(Console.readLine());
    }

    public List<String> inputMenusWithQuantity() {
        return Utils.splitDifferentMenus(Console.readLine());
    }
}
