package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalculatorController {
    @RequestMapping("/calc")
    public String hello(Model model, @RequestParam(value="num1") String number1,
                        @RequestParam(value="num2") String number2,
                        @RequestParam(value="op") String operator) {

        int x = Integer.parseInt(number1);
        int y = Integer.parseInt(number2);
        double result = Calculator.calculate(operator, x, y);

        model.addAttribute("num1", number1);
        model.addAttribute("num2", number2);
        model.addAttribute("op", operator);
        model.addAttribute("result", result);
        return "calc";
    }


}

