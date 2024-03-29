package adt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Actividad2_6 {
    public static void main(String[] args) throws SQLException{
    	/**
    	 * jdbc:mysql://127.0.0.1:3306/Neo?useSSL=true&serverTimezone=UTC", "Neo", "NAS4"
    	 *  
    	 * 127.0.0.1 = localhost.
    	 * 3306 = nº de puerto.
    	 * Neo  = ¿DATABASE? (1)
    	 * Neo = el usuario. (2)
    	 * NAS4 = la contraseña del usuario.
    	 * 
    	 */
    	
    	Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Neo?useSSL=true&serverTimezone=UTC", "Neo", "NAS4");
    	

        String sql = "SELECT apellido, oficio, salario FROM empleados WHERE DEPT_NO=10"; 
        //Seleccionar los datos de la tabla de empleados del departamento nº 10.
        
        /**
         * Si esto se quiere probar tanto por consola o por entorno grafico:
         * 
         * SELECT apellido, oficio, salario FROM neo.empleados WHERE DEPT_NO=10
         * 
         * neo correspondo a la database donde esta almacenada dicha tabla. 
         */
        
        Statement sen = con.createStatement();
        boolean valor = sen.execute(sql);

        if(valor) {
            ResultSet rs = sen.getResultSet();

            while(rs.next()) {
            	
            	//Referencia a las columnas de la tabla de empleados.
                String apellido = rs.getString("apellido");
                String oficio = rs.getString("oficio");
                double salario = rs.getFloat("salario");
                System.out.printf("Empleado: %s, oficio: %s, salario: %.2f \n", apellido, oficio, salario);
            }
        }

        

         String sql2 = "SELECT apellido, salario, departamentos.dnombre FROM empleados JOIN departamentos  ON empleados.dept_no = departamentos.dept_no WHERE empleados.salario = (SELECT MAX(salario) FROM empleados)";
        Statement sen2 = con.createStatement();
        boolean valor2 = sen2.execute(sql2);

        if(valor2) {
            ResultSet rs2 = sen2.getResultSet();

            while(rs2.next()) {
                String apellido = rs2.getString("apellido");
                double salario = rs2.getDouble("salario");
                String departamentos = rs2.getString("dnombre");
                System.out.printf("\n Empleado con máximo salario: %s\n salario: %.2f\n departamentos: %s\n", apellido, salario, departamentos);
            }
            
            //NO SALE NINGUN RESULTADO
        }

         
    }
}