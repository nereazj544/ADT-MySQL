package adt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;


/*
 * Utiliza la interfaz PreparedStatement para visualizar el APELLIDO, SALARIO y OFICIO
de los empleados de un departamento cuyo valor se recibe desde los argumentos de main°.
Visualiza también el nombre del departamento.
Visualiza al final el salario medio y el número de empleados del departamento. Si el
departamento no existe en la tabla departamentos visualiza un mensaje indicándolo. Utiliza la
clase DecimalFormat para dar formato al salario. Ejemplo:
DecimalFormat formato = new DecimalFormat("##,##0.00");
String valorFormateado = formato.format(resul.getFloat(1));
 */

public class Actividad2_11 {
	public static void main(String[] args) throws SQLException {
		try {
			String dept_no =  args[0];
			Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/neo?useSSL=true&serverTimezone=UTC", "Neo", "NAS4");

			String sql = "SELECT apellido, salario, oficio FROM empleados WHERE dept_no = ?";

			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setString(1, dept_no);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				String apellido = resultSet.getString("Apellido");
				float salario = resultSet.getFloat("SALARIO");
                String oficio = resultSet.getString("OFICIO");

                DecimalFormat formato = new DecimalFormat("##,##0.00");
                String salarioFormateado = formato.format(salario);

                System.out.println("Apellido: " + apellido);
                System.out.println("Salario: " + salarioFormateado);
                System.out.println("Oficio: " + oficio);
                System.out.println("");
			}
		
			String sql2 = "SELECT AVG(salario), COUNT(*) FROM empleados WHERE dept_no = ?";
            PreparedStatement statement2 = conexion.prepareStatement(sql2);
            statement2.setString(1, dept_no);

            ResultSet resultado2 = statement2.executeQuery();

            // Mostrar el salario medio y el número de empleados

            if (resultado2.next()) {
                float salarioMedio = resultado2.getFloat(1);
                int emp_no = resultado2.getInt(2);

                DecimalFormat formato = new DecimalFormat("##,##0.00");
                String salarioMedioFormateado = formato.format(salarioMedio);

                System.out.println("Salario medio: " + salarioMedioFormateado);
                System.out.println("Número de empleados: " + emp_no);
            } else {
                System.out.println("El departamento no existe en la tabla departamentos.");
            }
           
			conexion.close();
			
			
		} catch (Exception e) {
			System.err.println("ERROR");
			e.printStackTrace();
		}

	}
}
