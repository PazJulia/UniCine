package application;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Util {

	public static String encrypt(String value) {
		try {
			// Classe utilizada para gerar a criptografia em hash
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] digest = messageDigest.digest(value.getBytes("UTF-8"));

			// convertendo um array de bytes em hexadecimal
			StringBuilder stringBuilder = new StringBuilder();
			for (byte b : digest) {
				stringBuilder.append(String.format("%02X", 0xFF & b));
			}

			String senhaHexadecimal = stringBuilder.toString();
			return senhaHexadecimal;

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "Erro ao encriptar a senha";
	}

	public static Alert errorAlert(String mesage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Aviso");
		alert.setHeaderText(null);
		alert.setContentText(mesage);
		return alert;

	}

}
