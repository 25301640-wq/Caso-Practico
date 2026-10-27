import java.util.*;

public class algoritmoreporte {

    private static final String APERTURA = "({[";
    private static final String CIERRE = ")}]";

    public static List<String> reportarErrores(String codigo) {
        List<String> errores = new ArrayList<>();
        Stack<Character> pila = new Stack<>();
        Stack<Integer> lineas = new Stack<>();

        String[] lineasCodigo = codigo.split("\n", -1);

        for (int i = 0; i < lineasCodigo.length; i++) {
            String linea = lineasCodigo[i];
            for (int j = 0; j < linea.length(); j++) {
                char c = linea.charAt(j);

                if (APERTURA.indexOf(c) != -1) {
                    pila.push(c);
                    lineas.push(i + 1);
                } else if (CIERRE.indexOf(c) != -1) {
                    if (pila.isEmpty()) {
                        errores.add("Línea " + (i + 1) + ": '" + c + "' sin apertura previa.");
                    } else {
                        char apertura = pila.pop();
                        lineas.pop();
                        if (!coinciden(apertura, c)) {
                            errores.add("Línea " + (i + 1) + ": '" + c + "' no cierra con '" + apertura + "'.");
                        }
                    }
                }
            }
        }

        // Símbolos que quedaron abiertos
        while (!pila.isEmpty()) {
            errores.add("Línea " + lineas.pop() + ": falta el cierre de '" + pila.pop() + "'.");
        }

        return errores;
    }

    // Método auxiliar: verifica que una apertura coincida con su cierre
    private static boolean coinciden(char apertura, char cierre) {
        return APERTURA.indexOf(apertura) == CIERRE.indexOf(cierre);
    }
}
