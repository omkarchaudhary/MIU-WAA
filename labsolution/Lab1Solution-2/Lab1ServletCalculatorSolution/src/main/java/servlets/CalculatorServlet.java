package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet("/calc")
public class CalculatorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //show calculator page
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>SIMPLE CALCULATOR<br><br><br></head>");
        out.println("<body>");
        showForm(out);
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double result = 0;
        String number1 = request.getParameter("number1");
        String number2 = request.getParameter("number2");
        String operator = request.getParameter("op");
        int x = Integer.parseInt(number1);
        int y = Integer.parseInt(number2);
        result = calculate(operator, x, y);


        //show calculator page
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>SIMPLE CALCULATOR<br><br><br></head>");
        out.println("<body>");
        showForm(out);
        out.println("<h2>The result of " + number1 + " " + operator + " " + number2 + " =" + result + "</h2>");
        // add calculation history;
        storeCurrentCalculationToSession(request, result, operator, x, y);
        showHistory(out, request);
        out.println("</body>");
        out.println("</html>");
        out.flush();
    }


    private void storeCurrentCalculationToSession(HttpServletRequest request, double result, String operator, int x, int y) {
        Calc calc = new Calc(x, y, operator, result);
        HttpSession session = request.getSession();
        Collection<Calc> calcHistory = (Collection<Calc>) session.getAttribute("calchistory");
        if (calcHistory == null) {
            calcHistory = new ArrayList<Calc>();
        }
        calcHistory.add(calc);
        session.setAttribute("calchistory", calcHistory);
    }

    private double calculate(String operator, int x, int y) {
        if (operator.equals("+")) {
            return x + y;
        } else if (operator.equals("-")) {
            return x - y;
        } else if (operator.equals("*")) {
            return x * y;
        } else if (operator.equals("/")) {
            return x / y;
        }
        return 0;
    }

    private void showForm(PrintWriter out) {
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
    }

    private void showHistory(PrintWriter out, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Collection<Calc> calcHistory = (Collection<Calc>) session.getAttribute("calchistory");
        out.println("<table border=1>");
        out.println("<tr>");
        out.println("<th>first</th>");
        out.println("<th>operation</th>");
        out.println("<th>second</th>");
        out.println("<th>result</th>");
        out.println("</tr>");
        for (Calc calc : calcHistory) {
            out.println("<tr>");
            out.println("<th>" + calc.getFirst() + "</th>");
            out.println("<th>" + calc.getOp() + "</th>");
            out.println("<th>" + calc.getSecond() + "</th>");
            out.println("<th>" + calc.getResult() + "</th>");
            out.println("</tr>");
        }
        out.println("</table >");
    }
}
