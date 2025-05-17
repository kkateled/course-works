import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * unhuhng
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file_output = new File("resources/output.txt");
        PrintWriter pw = new PrintWriter(file_output);

        var url = Main.class.getResource("input.txt");
        File fileInput = new File(url.getPath());
        Scanner sc = new Scanner(fileInput);

        while (sc.hasNextLine()) {
            String expression = sc.nextLine();
            pw.print(expression+" = ");

            String[] parts = expression.trim().split(" ");
            String a = parts[0];
            String oper = parts[1];
            String b = parts[2];
            String res;
            Double resN;
            try {
                double num1 = Double.parseDouble(a);
                double num2 = Double.parseDouble(b);
                switch (oper) {
                    case "+":
                        resN = num1 + num2;
                        break;
                    case "-":
                        resN = num1 - num2;
                        break;
                    case "*":
                        resN = num1 * num2;
                        break;
                    case "/":
                        resN = num1 / num2;
                        break;
                    default:
                        throw new Exception("Operation Error!");
                }
                res = Double.toString(resN);
            } catch (NumberFormatException ex) {
                res = "Error! Not number";
            } catch (Exception ex) {
                res = ex.getMessage();
            }
            pw.println((res.equals("Infinity")) ? "Error! Division by zero" : res);
        }
        sc.close();
        pw.close();
    }
}
