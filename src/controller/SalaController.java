package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import factory.JPAFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Sala;
import model.Sessao;
import model.StatusSala;
import model.TipoSala;
import repository.SalaRepository;
import repository.SessaoRepository;

public class SalaController extends Controller<Sala> implements Initializable {

	private Stage stage;
	private Parent parent;

	private Sala sala;

	@FXML
	private TextField tfSala;

	@FXML
	private ComboBox<TipoSala> cbTipoSala;

	@FXML
	private ComboBox<StatusSala> cbStatus;

	@FXML
	private Button btLimpar;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btExcluir;
    
	@FXML
    private Button btAlterar;

	@FXML
	private TextField tfBuscaSala;

	@FXML
	private Button btBuscarSala;

	@FXML
	private TableView<Sala> tvSalas;

	@FXML
	private TableColumn<Sala, String> tcId;

	@FXML
	private TableColumn<Sala, String> tcNome;

	@FXML
	private TableColumn<TipoSala, String> tcTipoSala;

	@FXML
	private TableColumn<StatusSala, String> tcStatus;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// adicionando o conteudo

		tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nomeSala"));
		tcTipoSala.setCellValueFactory(new PropertyValueFactory<>("tipoSala"));

		tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

		cbTipoSala.getItems().addAll(TipoSala.values());

		// spbreescrevendo o metodo que mostra o conteudo do combobox

		cbTipoSala.setCellFactory(c -> new ListCell<TipoSala>() {
			@Override
			protected void updateItem(TipoSala item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty)
					setText(null);

				else
					setText(item.getLabel());
			}

		});

		cbTipoSala.setButtonCell(new ListCell<TipoSala>() {
			@Override
			protected void updateItem(TipoSala item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty)
					setText(null);

				else
					setText(item.getLabel());
			}

		});

		// adicionando o conteudo

		cbStatus.getItems().addAll(StatusSala.values());

		// spbreescrevendo o metodo que mostra o conteudo do combobox

		cbStatus.setCellFactory(c -> new ListCell<StatusSala>() {
			@Override
			protected void updateItem(StatusSala item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty)
					setText(null);

				else
					setText(item.getLabel());
			}

		});

		cbStatus.setButtonCell(new ListCell<StatusSala>() {
			@Override
			protected void updateItem(StatusSala item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty)
					setText(null);

				else
					setText(item.getLabel());
			}

		});
	}

//    public void passarSala(Sala sala) {
//    	setSala(sala);
//    	tfSala.setText(sala.getNomeSala());
//    	
//    	System.out.println(sala.getNomeSala());
//    	
//    }
	@FXML
	void handleLimpar(ActionEvent event) {
		tfSala.setText("");
		cbTipoSala.getSelectionModel().clearSelection();
		cbStatus.getSelectionModel().clearSelection();

		tfSala.requestFocus();

		sala = null;
	}

	@FXML
	void handleSalvar(ActionEvent event) {
		getSala().setNomeSala(tfSala.getText());
		getSala().setTipoSala(cbTipoSala.getValue());
		getSala().setStatus(cbStatus.getValue());
		

		super.save(getSala());

		handleLimpar(event);
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
			super.remove(getSala());
			handleLimpar(event);
		} else if (resposta.get().equals(ButtonType.CANCEL)) {

		}
	}
	
	@FXML
    void handleAlterar(ActionEvent event) {
		getSala().setNomeSala(tfSala.getText());
		getSala().setTipoSala(cbTipoSala.getValue());
		getSala().setStatus(cbStatus.getValue());

		super.save(getSala());
    	
    	handleLimpar(event);
    	
    	atualizarBotoes();
    }
	
	private void atualizarBotoes() {
		btSalvar.setDisable(getSala().getId() != null);
		btAlterar.setDisable(getSala().getId() == null);
		btExcluir.setDisable(getSala().getId() == null);
	}

	@FXML
	void handleBuscarSala(ActionEvent event) {
		SalaRepository repository = new SalaRepository(JPAFactory.getEntityManager());
		List<Sala> lista = repository.getSalas(tfBuscaSala.getText());

		if (lista.isEmpty()) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Informação:");
			alerta.setHeaderText(null);
			alerta.setContentText("A consulta não retornou dados!");
			alerta.show();
		}

		tvSalas.setItems(FXCollections.observableList(lista));
		atualizarBotoes();
	}

	@FXML
	void handleOnMouseClicked(MouseEvent event) throws IOException {
		// VERIFICANDO SE AÇÃO DO BOTÃO PRINCIPAL QUE FOI CLICADO
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			// VERIFICANDO SE A QUANTIDADE DE CLIQUES NO BOTÃO PRIMÁRIO IGUAL A 2
			if (event.getClickCount() == 2) {

				// PREENCHE Cadastro
				sala = tvSalas.getSelectionModel().getSelectedItem();

				tfSala.setText(getSala().getNomeSala());
				cbTipoSala.setValue(getSala().getTipoSala());
				cbStatus.setValue(getSala().getStatus());

				// SETANDO O FOCUS NO NOME
				tfSala.requestFocus();

				atualizarBotoes();

			}
		}
	}
	
	public void atualizarTabela()
    {
    	SalaRepository repository = new SalaRepository(JPAFactory.getEntityManager());
		List<Sala> lista = repository.getListNomesSalas();
		tvSalas.setItems(FXCollections.observableList(lista));
		atualizarBotoes();
    }

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public Parent getParent() {
		return parent;
	}

	public Sala getSala() {
		if (sala == null)
			sala = new Sala();
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

}
