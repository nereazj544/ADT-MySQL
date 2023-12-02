package adt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/*
 * Crea un programa Java que inserte un empleado en la tabla empleados, el programa recibe
desde la línea de argumentos de main() los valores a insertar. Los argumentos que recibe son los
siguientes: EMP_NO, APELLIDO, OFICIO, DIR, SALARIO, COMISIÓN, DEPT NO. Antes de
insertar se deben realizar las siguientes comprobaciones:
- que el departamento exista en la tabla departamentos, si no existe no se inserta.
- que el número del empleado no exista, si existe no se inserta.
- que el salario sea > que 0, si es <= O no se inserta.
- que el director (DIR, es el número de empleado de su director) exista en la tabla empleados,
si no existe no se inserta.
- El APELLIDO y el OFICIO no pueden ser nulos.
- La fecha de alta del empleado es la fecha actual.
Cuando se inserte la fila visualizar mensaje y si no se inserta visualizar el motivo
(departamento inexistente, número de empleado duplicado, director inexistente, etc.)
 */
public class actividad2_10 {
	public static void main(String[] args) throws SQLException {

		 if (args.length != 7) {
	            System.out.println("Se requieren 7 argumentos: emp_no, apellido, oficio, dir, salario, comision, dept_no");
	            
	        }

	        int emp_no = Integer.parseInt(args[0]);
	        String apellido = args[1];
	        String oficio = args[2];
	        int dir = Integer.parseInt(args[3]);
	        double salario = Double.parseDouble(args[4]);
	        double comision = Double.parseDouble(args[5]);
	        int dept_no = Integer.parseInt(args[6]);

	        // Realizar las comprobaciones
	        if (!existeDepartamento(dept_no)) {
	            System.out.println("No se puede insertar el empleado. El departamento no existe en la tabla departamentos.");
	            return;
	        }

	        if (existeEmpleado(emp_no)) {
	            System.out.println("No se puede insertar el empleado. El número de empleado ya existe.");
	            return;
	        }

	        if (salario <= 0) {
	            System.out.println("No se puede insertar el empleado. El salario debe ser mayor que 0.");
	            return;
	        }

	        if (!existeEmpleado(dir)) {
	            System.out.println("No se puede insertar el empleado. El director no existe en la tabla empleados.");
	            return;
	        }

	        if (apellido == null || oficio == null) {
	            System.out.println("No se puede insertar el empleado. El apellido y el oficio no pueden ser nulos.");
	            return;
	        }

	        // Insertar el empleado en la tabla empleados
	        LocalDate fechaAlta = LocalDate.now();
	        String sql = "INSERT INTO empleados (emp_no, apellido, oficio, dir, fecha_altsalario, comision, detp_no) VALUES (101, Smith, Analista, 100, 5000, 200, 10)";

	        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Neo?useSSL=true&serverTimezone=UTC", "Neo", "NAS4");
	                PreparedStatement statement = conexion.prepareStatement(sql)) {

	            statement.setInt(1, emp_no);
	            statement.setString(2, apellido);
	            statement.setString(3, oficio);
	            statement.setInt(4, dir);
	            statement.setDouble(5, salario);
	            statement.setDouble(6, comision);
	            statement.setInt(7, dept_no);
	            statement.setDate(8, java.sql.Date.valueOf(fechaAlta));

	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Se ha insertado el empleado correctamente.");
	            } else {
	                System.out.println("No se ha podido insertar el empleado.");
	            }

	        } catch (SQLException e) {
	            System.out.println("Error al insertar el empleado: " + e.getMessage());
	        }
	    }

	    // Método para comprobar si un departamento existe en la tabla departamentos
	    private static boolean existeDepartamento(int dept_no) {
	        String sql = "SELECT * FROM departamentos WHERE DEPT_NO = ?";

	        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Neo?useSSL=true&serverTimezone=UTC", "Neo", "NAS4");
	                PreparedStatement statement = conexion.prepareStatement(sql)) {

	            statement.setInt(1, dept_no);
	            ResultSet resultSet = statement.executeQuery();
	            return resultSet.next();

	        } catch (SQLException e) {
	            System.out.println("Error al comprobar la existencia del departamento: " + e.getMessage());
	        }

	        return false;
	    }

	    // Método para comprobar si un empleado existe en la tabla empleados
	    private static boolean Empleado1(int emp_no) {
	        String sql = "SELECT * FROM empleados WHERE EMP_NO = ?";

	        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Neo?useSSL=true&serverTimezone=UTC", "Neo", "NAS4");
	                PreparedStatement statement = conexion.prepareStatement(sql)) {

	            statement.setInt(1, emp_no);
	            ResultSet resultSet = statement.executeQuery();
	            return resultSet.next();

	        } catch (SQLException e) {
	            System.err.println("Error al comprobar la existencia del empleado: " + e.getMessage());
	        }

	        return false;
	    }

	    // Método para comprobar si un empleado existe en la tabla empleados
	    private static boolean existeEmpleado(int emp_no) {
	        String sql = "SELECT * FROM empleados WHERE EMP_NO = ?";

	        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/nombre_base_de_datos", "usuario", "contraseña");
	                PreparedStatement statement = conexion.prepareStatement(sql)) {

	            statement.setInt(1, emp_no);
	            ResultSet resultSet = statement.executeQuery();
	            return resultSet.next();

	        } catch (SQLException e) {
	            System.err.println("Error al comprobar la existencia del empleado: " + e.getMessage());
	        }

	        return false;
	    }	
		
	}


