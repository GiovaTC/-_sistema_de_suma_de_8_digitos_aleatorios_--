# -_sistema_de_suma_de_8_digitos_aleatorios_-- :. 
# Sistema de Suma de 8 Dígitos Aleatorios:  
**Java (Swing) + Oracle Database 19c** :

<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/4d76e979-e968-44e1-8bc0-bdc116f6f83c" />        

<img width="2553" height="1079" alt="image" src="https://github.com/user-attachments/assets/888ef3cc-64a3-455c-8bd0-b14c589cda0e" />    

A continuación se presenta una **solución completa, profesional y ejecutable**, alineada con buenas prácticas académicas y técnicas, que cumple exactamente con los siguientes requisitos:

- Interfaz gráfica en Java (Swing – IntelliJ IDEA)
- Generación de **8 números aleatorios distintos** en el rango **1 a 6000**
- **Suma automática** de los 8 dígitos
- Persistencia en **Oracle Database 19c**
- Inserción mediante **procedimiento almacenado**
- Acceso a datos usando **JDBC**

---

## 1. Modelo de Base de Datos (Oracle 19c)

### 1.1 Tabla

```sql
CREATE TABLE SUMA_DIGITOS (
    ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    N1 NUMBER,
    N2 NUMBER,
    N3 NUMBER,
    N4 NUMBER,
    N5 NUMBER,
    N6 NUMBER,
    N7 NUMBER,
    N8 NUMBER,
    RESULTADO NUMBER,
    FECHA_REGISTRO DATE DEFAULT SYSDATE
);
1.2 Procedimiento Almacenado

CREATE OR REPLACE PROCEDURE SP_GUARDAR_SUMA (
    P_N1 IN NUMBER,
    P_N2 IN NUMBER,
    P_N3 IN NUMBER,
    P_N4 IN NUMBER,
    P_N5 IN NUMBER,
    P_N6 IN NUMBER,
    P_N7 IN NUMBER,
    P_N8 IN NUMBER,
    P_RESULTADO IN NUMBER
) AS
BEGIN
    INSERT INTO SUMA_DIGITOS (
        N1, N2, N3, N4, N5, N6, N7, N8, RESULTADO
    ) VALUES (
        P_N1, P_N2, P_N3, P_N4, P_N5, P_N6, P_N7, P_N8, P_RESULTADO
    );
    COMMIT;
END;
/
2. Proyecto Java – Estructura Recomendada

src/
 ├── db
 │   └── ConexionOracle.java
 ├── dao
 │   └── SumaDAO.java
 ├── ui
 │   └── SumaUI.java
 └── Main.java
3. Conexión a Oracle (JDBC)

package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionOracle {

    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521/ORCLPDB1";
    private static final String USER = "TU_USUARIO";
    private static final String PASS = "TU_PASSWORD";

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
4. DAO – Llamada al Procedimiento Almacenado

package dao;

import db.ConexionOracle;
import java.sql.CallableStatement;
import java.sql.Connection;

public class SumaDAO {

    public void guardarSuma(int[] numeros, int resultado) throws Exception {
        String sql = "{ CALL SP_GUARDAR_SUMA(?,?,?,?,?,?,?,?,?) }";

        try (Connection con = ConexionOracle.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            for (int i = 0; i < 8; i++) {
                cs.setInt(i + 1, numeros[i]);
            }
            cs.setInt(9, resultado);

            cs.execute();
        }
    }
}
5. Interfaz Gráfica (Swing)

package ui;

import dao.SumaDAO;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SumaUI extends JFrame {

    private JTextField[] campos = new JTextField[8];
    private JTextField txtResultado = new JTextField();
    private int[] numeros = new int[8];

    public SumaUI() {
        setTitle("Suma de 8 Dígitos Aleatorios");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(11, 2, 5, 5));

        for (int i = 0; i < 8; i++) {
            panel.add(new JLabel("Número " + (i + 1) + ":"));
            campos[i] = new JTextField();
            campos[i].setEditable(false);
            panel.add(campos[i]);
        }

        panel.add(new JLabel("Resultado:"));
        txtResultado.setEditable(false);
        panel.add(txtResultado);

        JButton btnGenerar = new JButton("Generar y Sumar");
        JButton btnGuardar = new JButton("Guardar en Oracle");

        panel.add(btnGenerar);
        panel.add(btnGuardar);

        add(panel);

        btnGenerar.addActionListener(e -> generarNumeros());
        btnGuardar.addActionListener(e -> guardarDatos());
    }

    private void generarNumeros() {
        Set<Integer> set = new HashSet<>();
        Random rnd = new Random();
        int suma = 0;

        while (set.size() < 8) {
            set.add(rnd.nextInt(6000) + 1);
        }

        int i = 0;
        for (int n : set) {
            numeros[i] = n;
            campos[i].setText(String.valueOf(n));
            suma += n;
            i++;
        }
        txtResultado.setText(String.valueOf(suma));
    }

    private void guardarDatos() {
        try {
            int resultado = Integer.parseInt(txtResultado.getText());
            new SumaDAO().guardarSuma(numeros, resultado);
            JOptionPane.showMessageDialog(this,
                    "Registro guardado correctamente en Oracle");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
6. Clase Principal

import ui.SumaUI;

public class Main {
    public static void main(String[] args) {
        new SumaUI().setVisible(true);
    }
}
7. Funcionamiento del Sistema
El usuario presiona “Generar y Sumar”

Se generan 8 números aleatorios distintos (1–6000)

El sistema calcula automáticamente la suma

Al presionar “Guardar en Oracle”:

Se invoca el procedimiento almacenado

Se registra la información en Oracle Database 19c

8. Observaciones Técnicas
Unicidad garantizada mediante HashSet

Separación clara de responsabilidades (UI / DAO / DB)

Compatible con IntelliJ IDEA

Apto para evaluaciones académicas, prácticas JDBC y PL/SQL . :. . /.
