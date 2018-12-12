package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.postgresql.shaded.com.ongres.scram.common.util.UsAsciiUtils;

import application.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Filme;
import model.Perfil;
import model.Usuario;

public class UsuarioController extends Controller<Usuario> implements Initializable {

	private Usuario usuario;

	@FXML
	private TextField tfCpf;

	@FXML
	private ComboBox<Perfil> cbTipoUsuario;

	@FXML
	private PasswordField pfSenha;

	@FXML
	private Button btIncluir;

	@FXML
	private Button btAlterar;

	@FXML
	private Button btExcluir;

	@FXML
	private Button btLimpar;

	@FXML
	private TextField tfNome;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbTipoUsuario.getItems().addAll(Perfil.values());

		cbTipoUsuario.setCellFactory(c -> new ListCell<Perfil>() {
			@Override
			protected void updateItem(Perfil item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});

		// seobrescreendo o método que mostra o conteudo selecionado
		cbTipoUsuario.setButtonCell(new ListCell<Perfil>() {
			@Override
			protected void updateItem(Perfil item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});
	}

	@FXML
	void handleIncluir(ActionEvent event) {
		getUsuario().setNome(tfNome.getText());
		getUsuario().setCpf(tfCpf.getText());
		getUsuario().setSenha(Util.encrypt(pfSenha.getText()));
		getUsuario().getPerfil();
		
		super.save(getUsuario());

		handleLimpar(event);
	}

	@FXML
	void handleLimpar(ActionEvent event) {
		tfNome.setText("");
		tfCpf.setText("");
		pfSenha.setText("");
		cbTipoUsuario.getSelectionModel().clearSelection();

		usuario = null;
		tfNome.requestFocus();
	}
	
	 @FXML
	    void handleAlterar(ActionEvent event) {
			getUsuario().setNome(tfNome.getText());
			getUsuario().setCpf(tfCpf.getText());
//			getUsuario().setSenha(pfSenha.getText());
			getUsuario().setSenha(Util.encrypt(pfSenha.getText()));
			getUsuario().getPerfil();
			
			super.save(getUsuario());

			handleLimpar(event);
	    	
	    	atualizarBotoes();
	    }
	 
	 @FXML
	    void handleExcluir(ActionEvent event) {
			// MENSAGEM DE ALERTA PARA O USUARIO CONFIRMAR UMA EXCLUSAO
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmação");
			alert.setHeaderText("Está operação excluirá todas as informações selecionadas da base de dados.");
			alert.setContentText("Deseja realmente excluir?");
			// Capturar as resposta do usuario sobre a mensagem de confirmation
			Optional<ButtonType> resposta = alert.showAndWait();
			if (resposta.get().equals(ButtonType.OK)) {
				super.remove(getUsuario());
				handleLimpar(event);
			} else if (resposta.get().equals(ButtonType.CANCEL)) {

			}
	    }
	
	private void atualizarBotoes() {
		btIncluir.setDisable(getUsuario().getId() != null);
		btAlterar.setDisable(getUsuario().getId() == null);
		btExcluir.setDisable(getUsuario().getId() == null);
	}

	
	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsurio(Usuario usuario) {
		this.usuario = usuario;
	}

}
