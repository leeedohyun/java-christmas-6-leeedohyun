package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Utils;

public class InputView {

    public int inputVisitedDate() {
        return Utils.convertStringToInt(Console.readLine());
    }
}
