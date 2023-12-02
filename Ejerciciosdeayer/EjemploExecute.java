package database;

import java.sql.*;

public class EjemploExecute {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Neo?useSSL=true&serverTimezone=UTC", "Neo", "NAS4");
		String sql = "SELECT * FROM departamentos";
		//String sql = "UPDATE departamentos SET loc = LOWER (loc)"; //Cambiar font
		Statement sentencia = conexion.createStatement();
		boolean valor = sentencia.execute(sql);
		
		if (valor) {
			ResultSet rs = sentencia.getResultSet();
			while (rs.next())
				System.out.printf("%d, %s, %s %n", rs.getInt(1), rs.getString(2), rs.getString(3));
			rs.close();
		} else {
			int f = sentencia.getUpdateCount();
			System.out.printf("Filas afectadas:%d %n", f);
			sentencia.close();
			conexion.close();
		} // main
	}
}