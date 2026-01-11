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
