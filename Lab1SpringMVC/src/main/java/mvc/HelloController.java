package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public ModelAndView hello( @RequestParam(value="firstname") String firstName,
                        @RequestParam(value="lastname") String lastName) {

        String message=firstName+" "+lastName;

        Map<String, Object> params = new HashMap<>();
        params.put("message", message);

        return new ModelAndView("hello",params);
    }

    @RequestMapping("/calc")
    public  ModelAndView calc(@RequestParam(name = "num1") int num1,@RequestParam(name = "op") String op,@RequestParam(name = "num2") int num2){
        Map<String, Object> map = new HashMap<>();
        double result = 0;
        map.put("num1", num1);
        map.put("num2", num2);
        map.put("op",op);
        if(op.equals("+")){
            result = num1+num2;
        }else if(op.equals("-")){
            result = num1-num2;
        }else if(op.equals("*")){
            result = num1*num2;
        }else if(op.equals("/")){
            result = num1/num2;
        }
        map.put("result", result);
        return new ModelAndView("calc",map);
    }
}

