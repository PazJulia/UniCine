package factory;

import java.io.IOException;

import controller.EditarSalaController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class EditarSalaListControllerFactory {
	public static EditarSalaController getInstance() throws IOException {
		FXMLLoader loader = new FXMLLoader(
				EditarSalaListControllerFactory.class.getResource("/view/" + "EditarSala.fxml"));
		Parent root = loader.load();

		// OBTENDO O CONTROLADOR
		EditarSalaController listagem = loader.getController();
		listagem.setParent(root);

		return listagem;
	}
}
