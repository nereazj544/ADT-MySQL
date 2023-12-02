package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase_1 {
	public static void main(String[] args) throws SQLException{
		try {
		Class.forName("com.mysql.jdbc.Driver"); //Cargar el driver
		//Establecemos la conexión con la BD
		//Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Neo?useSSL=true&serverTimezone=UTC", "Neo", "NAS4");
		
		Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\nzjha\\Desktop\\CLASE23-24\\ASIGNATURAS\\ADT\\DATOS\\ejemplo.db");
		//Conexion con sqlite
    	
		java.sql.DatabaseMetaData dbmd = con.getMetaData();
		ResultSet resul = null;
		String nombre = dbmd.getDatabaseProductName();
		String driver = dbmd.getDriverName();
		String url = dbmd.getURL();
		String usuario = dbmd.getUserName();
		
		System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS:");
		
		System.out.println("-------------------------------------");
				System.out.printf("Nombre : %s %n", nombre ) ;
				System.out.printf("Driver : %s %n", driver );
				System.out.printf("Usuario: %s %n", usuario ) ;
				System.out.printf("URL: %s %n", url ) ;
				resul = dbmd.getTables("neo", "ejemplo", null, null);

				while (resul.next()) {
					String catalogo = resul.getString(1);//columna 1
					String esquema = resul.getString(2); //columna 2
					String tabla = resul.getString(3); //columna 3
					String tipo = resul.getString(4); //columna 4
					System.out.printf("%s - Catalogo: %s, Esquema: %s, Nombre: %s %n", tipo, catalogo, esquema, tabla);
					}
					con.close(); //Cerrar conexión
		}
					catch (ClassNotFoundException cn) {cn.printStackTrace();}
					catch (SQLException e) {e.printStackTrace();}
					}//fin de main
					}
// fin de la clase