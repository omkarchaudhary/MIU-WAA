package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/calc")
public class CalculatorServlet extends HttpServlet {

    List<Calculator> calculators = new ArrayList<>();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //show calculator page
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>SIMPLE CALCULATOR<br><br><br></head>");
        out.println("<body>");
        out.println("<form method = 'post' action = 'calc'>");
        out.println("enter the first number:<br>");
        out.println("<input type = 'text' name='number1'><br><br>");
        out.println("enter the second number:<br>");
        out.println("<input type = 'text' name='number2'><br><br>");
        out.println("enter the operation:<br><br>");
        out.println("<input type ='radio' name = 'op' value = '+'>add<br>");
        out.println("<input type = 'radio' name = 'op' value = '-'>sub<br>");
        out.println("<input type = 'radio' name = 'op' value = '*'>mul<br>");
        out.println("<input type = 'radio' name = 'op' value = '/'>div<br><br>");
        out.println("<input type = 'submit' name = 'result' value = 'submit'><br>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //show result page

        PrintWriter out = response.getWriter();
        int firstNumber = Integer.parseInt(request.getParameter("number1"));
        int secondNumber = Integer.parseInt(request.getParameter("number2"));
        String operation = request.getParameter("op");
        double result = 0;
        if(operation.equals("+")){
            result = firstNumber+secondNumber;
        } else if(operation.equals("-")){
            result = firstNumber-secondNumber;
        } else if(operation.equals("*")){
            result = firstNumber*secondNumber;
        } else if(operation.equals("/")){
            try{
                result = firstNumber/secondNumber;
            }catch(Exception ex){
                out.println("Invalid number "+ex);
            }
        }
        Calculator calculator = new Calculator(firstNumber,secondNumber,operation,result);
        calculators.add(calculator);
        out.println("<html><body>");
        out.println("<h3>The result of "+ firstNumber +" "+ operation +" "+ secondNumber +" = "+ result +"</h3><br>");
        out.println("<table style='border: 1px solid black'>");
        out.println("<th><tr>");
        out.println("<td style='border: 1px solid black; text-align: center;'> first </td>");
        out.println("<td style='border: 1px solid black; text-align: center;'> operation </td>");
        out.println("<td style='border: 1px solid black; text-align: center;'> second </td>");
        out.println("<td style='border: 1px solid black; text-align: center;'> result </td>");
        out.println("</tr></th>");

        for(Calculator cal : calculators){
            out.println("<tr>");
            out.println("<td style='border: 1px solid black; text-align: center;'>"+ cal.getFirstNumber()+"</td>");
            out.println("<td style='border: 1px solid black;text-align: center;'>"+ cal.getOperation()+"</td>");
            out.println("<td style='border: 1px solid black;text-align: center;'>"+ cal.getSecondNumber()+"</td>");
            out.println("<td style='border: 1px solid black;text-align: center;'>"+ cal.getResult()+"</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body></html>");
        out.flush();
    }

}
