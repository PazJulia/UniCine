package controller;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Filme;
import model.Sala;
import repository.FilmeRepository;

public class EditarFilmeController implements Initializable{
	
	private Filme filme;
	private Stage stage;
	private Parent parent;
	private Sala sala;
	
	@FXML
    private TextField tfBusca;

    @FXML
    private Button btBuscar;
	
	@FXML
    private TableColumn<Filme, Integer> tcId;

    @FXML
    private TableView<Filme> tvFilmes;

    @FXML
    private TableColumn<Filme, String> tcTitulo;
    
    @FXML
    private TableColumn<Filme, String> tcClassificação;

    @FXML
    private TableColumn<Filme, String> tcDuracao;

    public void abrir() {
    	stage = new Stage();
		Scene scene = new Scene(parent, 750, 270);
		stage.setTitle("Consulta de Filmes");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
    }
    

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// CONFIGURANDO AS COLUNAS DAS TABELAS CONFORME OS ATRIBUTOS DA CLASSE CLIENTE
		tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		tcClassificação.setCellValueFactory(new PropertyValueFactory<>("classificacao"));
		tcDuracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));
		
	}
	
    @FXML
    void handleOnMouseClicked(MouseEvent event) {
		// VERIFICANDO SE AÇÃO DO BOTÃO PRINCIPAL QUE FOI CLICADO
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			// VERIFICANDO SE A QUANTIDADE DE CLIQUES NO BOTÃO PRIMÁRIO IGUAL A 2
			if (event.getClickCount() == 2) {

				//PREENCHE CIDADE
				filme = tvFilmes.getSelectionModel().getSelectedItem();
				
				//FECHA TELA DE LISTAGEM APÓS CLICK NA LINHA
				getStage().close();

			}
		}
    }
	
    public Filme getFilmeSelecionado() {
    	return filme;
    }
    

    @FXML
    void handleBuscar(ActionEvent event) {
    	FilmeRepository repository = new FilmeRepository(JPAFactory.getEntityManager());
		List<Filme> lista = repository.getFilmes(tfBusca.getText());

		if (lista.isEmpty()) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Informação:");
			alerta.setHeaderText(null);
			alerta.setContentText("A consulta não retornou dados!");
			alerta.show();
		}

		tvFilmes.setItems(FXCollections.observableList(lista));
    }
    
	public void setFilme(Filme filme) {
		this.filme = filme;
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
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

}
