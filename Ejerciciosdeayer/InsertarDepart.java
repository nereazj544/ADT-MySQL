package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertarDepart {
public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Neo?useSSL=true&serverTimezone=UTC", "Neo", "NAS4");
		
		if (args.length != 3) {
			System.out.println("no argumentos");
		}
		String dept = args[0];
		int depI=0;

		
		
		try {
			int d = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.err.println("Error");			
		}
		String dnombre = args[1];
		String loc = args[2];
		
		String sql = String.format("INSERT INTO departamentos VALUES ('%s', '%s', '%s')", dept, dnombre, loc);
		//String sql = "INSERT INTO departamentos VALUES (" d + "','" + nom + "','" + loc + ")";
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
