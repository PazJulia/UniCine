package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import factory.EditarFilmeListControllerFactory;
import factory.EditarSalaListControllerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Filme;
import model.Sala;

public class TemplateController implements Initializable {

	private Filme filme;

	private Sala sala;

	@FXML
	private Label titulo;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// abrindo a tela de login
		abrirTelaLogin();
		
		titulo.setText("Bem Vindo");
		
	}

	private void abrirTelaLogin() {
		try {
			Stage stage = new Stage(StageStyle.TRANSPARENT);
			Parent parent = FXMLLoader.load(Main.class.getResource("/view/Login.fxml"));
			Scene scene = new Scene(parent, 379, 414);
			stage.setTitle("Login");
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
		}

		catch (IOException e1) {
			e1.printStackTrace();
		}
		

	}
	
	@FXML
	void HandleCadastroFilme(ActionEvent event) throws IOException {
		Main.trocaTela(1);
		titulo.setText("CADASTRO DE FILME");
	}

	@FXML
	void HandleCadastroSala(ActionEvent event) throws IOException {
		Main.trocaTela(2);
		titulo.setText("CADASTRO DE SALA");
	}

	@FXML
	void HandleCadastroSessao(ActionEvent event) throws IOException {
		Main.trocaTela(3);
		titulo.setText("CADASTRO DE SESSÃO");

	}

	@FXML
	void HandleOnMouseClickedEditarFilme(MouseEvent event) throws IOException {

		EditarFilmeController listagem = EditarFilmeListControllerFactory.getInstance();
		listagem.abrir();

		setFilme(listagem.getFilmeSelecionado());

//		tfNome.setText(getCidade().getNome());
//		tfEstado.setText(getCidade().getEstado());
//		tfPais.setText(getCidade().getPais());

	}

	@FXML
	void HandleOnMouseClickedEditarSala(MouseEvent event) throws IOException {

		EditarSalaController listagem = EditarSalaListControllerFactory.getInstance();
		listagem.abrir();

		setSala(listagem.getSalaSelecionada());

//		tfNome.setText(getCidade().getNome());
//		tfEstado.setText(getCidade().getEstado());
//		tfPais.setText(getCidade().getPais());

	}

	public Filme getfilme() {
		if (filme == null)
			filme = new Filme();
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Sala getSala() {
		if (sala == null)
			sala = new Sala();
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	@FXML
    void handleBloquear(ActionEvent event) {
		abrirTelaLogin();
    }

}
