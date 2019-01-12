package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import jssc.SerialPort;
import jssc.SerialPortException;

public class CreateUserC {
	String driverName = "org.postgresql.Driver", urldb = "jdbc:postgresql://ec2-54-243-137-182.compute-1.amazonaws.com:5432/d9mn6hk25h95pe?sslmode=require",
			query = "select * from abc", dbName = "fbdlpvmecxgbnh", dbPass = "dbff70b0e153cd8a901a97849e0c2c30f08158885e10248fe9e0baa1fe914aeb";
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstm = null;

	public String JaiShivShankar(String userid) {
		System.out.println("it is user id:-" + userid);
		String str = null;
		SerialPort serialPort = new SerialPort("COM5");
		try {
			serialPort.openPort();
			serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
			Thread.sleep(1000);
			for (int k = 1; k <= 1; k++) {
				Thread.sleep(2000);
				serialPort.writeString("11\n");
				Thread.sleep(2000);
				serialPort.writeString(userid);
			}
			Thread.sleep(2000);
			for (int i = 1; i <= 10; i++) {
				str = serialPort.readString();
				Thread.sleep(2000);
				System.out.println(str);
				if(str!=null)
				if (str.contains("Stored")) {
					str = "Stored";
					break;
				} else {
					str = null;
				}
			}

			System.out.println(str);
			serialPort.writeString("200");
			Thread.sleep(1000);
			serialPort.closePort();
			System.out.println("Jai Bhole Nath");
		} catch (SerialPortException | InterruptedException ex) {
			// serialPort.writeString("200");
			System.out.println(ex);

		}
		System.out.println("it is value from" + str);
		return str;
	}

}
