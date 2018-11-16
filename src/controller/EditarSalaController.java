package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import factory.JPAFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Sala;
import model.StatusSala;
import model.TipoSala;
import repository.SalaRepository;

public class EditarSalaController implements Initializable {
	
	private Sala sala;
	private Stage stage;
	private Parent parent;
	
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

    public void abrir() {
    	stage = new Stage();
		Scene scene = new Scene(parent, 750, 270);
		stage.setTitle("Consulta de Filmes");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();

    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	tcNome.setCellValueFactory(new PropertyValueFactory<>("nomeSala"));
    	tcTipoSala.setCellValueFactory(new PropertyValueFactory<>("tipoSala"));
    	tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
//    	private StatusSala status;
//    	
//    	private TipoSala tipoSala;
    }
    
    @FXML
    void handleOnMouseClicked(MouseEvent event) throws IOException {
		// VERIFICANDO SE AÇÃO DO BOTÃO PRINCIPAL QUE FOI CLICADO
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			// VERIFICANDO SE A QUANTIDADE DE CLIQUES NO BOTÃO PRIMÁRIO IGUAL A 2
			if (event.getClickCount() == 2) {

				//PREENCHE CIDADE
				sala = tvSalas.getSelectionModel().getSelectedItem();
				
//				SalaController passar = new SalaController();
//				passar.passarSala(sala);
//				
//				 
//				System.out.println(sala);
				//FECHA TELA DE LISTAGEM APÓS CLICK NA LINHA
				getStage().close();
				
				

			}
		}
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
    }
    

	public void setSala(Sala sala) {
		this.sala = sala;
	}
    
    public Sala getSalaSelecionada() {
    	return sala;
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

}
