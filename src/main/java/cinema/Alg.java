package cinema;

public class Alg {
    public static void main(String[] args) {
        String rsl = "";
        String s = "";
        String sep = System.lineSeparator();
        for (int i = 1; i <= 6; i++) {
            s += "<tr>"
                    + " <th>" + i + "ряд" + "</th>";
            for (int j = 1; j <= 6; j++) {
                s += " <td>" + sep
                        + " <div id =" + i + "," + j + ";" + " class=\"check\">" + sep
                        + " <div class=\"check-mark\">"
                        + "</div>" + sep
                        + "</div>" + sep
                        + "</td>" + sep;
            }
            s = s + "</tr>";
        }
        System.out.println(s);
    }

}
