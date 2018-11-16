package factory;

import java.io.IOException;

import controller.EditarFilmeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class EditarFilmeListControllerFactory {
	public static EditarFilmeController getInstance() throws IOException {
		FXMLLoader loader = new FXMLLoader(
				EditarFilmeListControllerFactory.class.getResource("/view/" + "EditarFilme.fxml"));
		Parent root = loader.load();

		// OBTENDO O CONTROLADOR
		EditarFilmeController listagem = loader.getController();
		listagem.setParent(root);

		return listagem;
	}
}
