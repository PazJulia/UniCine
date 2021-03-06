package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {// UniCine

	private static BorderPane root;

	public static void main(String[] args) {
		launch(args);

	}

	public static void trocaTela(int numero) throws IOException {
		Parent clienteView = null;

		clienteView = FXMLLoader.load(Main.class.getResource("/view/CadastroDeFilme.fxml"));
		
		if (numero == 1) {
			clienteView = FXMLLoader.load(Main.class.getResource("/view/CadastroDeFilme.fxml"));
		} else if (numero == 2) {
			clienteView = FXMLLoader.load(Main.class.getResource("/view/CadastroDeSala.fxml"));
		} else if (numero == 3) {
			clienteView = FXMLLoader.load(Main.class.getResource("/view/CadastroDeSessao.fxml"));
		} else if (numero == 4) {
			clienteView = FXMLLoader.load(Main.class.getResource("/view/CadastroUsuario.fxml"));
		} else if (numero == 5) {
			clienteView = FXMLLoader.load(Main.class.getResource("/view/CompraIngresso.fxml"));
		}
		

		// adicionando a tela inicial no template (parte central)
		ScrollPane scroll = (ScrollPane) root.getChildren().get(2);
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.getChildren().add(clienteView);

		scroll.setContent(vbox);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		
		root = FXMLLoader.load(getClass().getResource("/view/TemplateCrud.fxml"));
		Parent clienteView = FXMLLoader.load(getClass().getResource("/view/BemVindo.fxml"));

		// adicionando a tela inicial no template (parte central)
		ScrollPane scroll = (ScrollPane) root.getChildren().get(2);
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.getChildren().add(clienteView);
		scroll.setContent(vbox);

		Scene scene = new Scene(root, 800, 600);

//		primaryStage.setFullScreen(true);
		primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
		primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());

		primaryStage.setTitle("Administrador");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
