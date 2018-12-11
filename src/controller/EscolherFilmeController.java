package controller;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import factory.JPAFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Compra;
import model.Filme;
import model.Sessao;
import model.TipoIngresso;
import repository.SessaoRepository;

public class EscolherFilmeController implements Initializable{

	private Sessao sessao;
	
	private Filme filme;
	
	@FXML
    private TextField tfBuscaSessao;

    @FXML
    private Button btBuscarSessao;

    @FXML
    private TableView<Sessao> tvSessao;

    @FXML
    private TableColumn<Compra, String> tcNomeFilme;

    @FXML
    private TableColumn<Compra, LocalDate> tcDataExibicao;

    @FXML
    private TableColumn<Compra, Time> tcHoraInicio;

    @FXML
    private TableColumn<Compra, Time> tcHoraTermino;

    @FXML
    private TableColumn<Compra, String> tcNomeSala;

    @FXML
    private TableColumn<Compra, Integer> tcQtPoltronas;

    @FXML
    private ToggleGroup TipoIngresso;

    @FXML
    private RadioButton rdTipo;

    @FXML
    private Button btConfirma;

    @FXML
    private Button btAdd;

    @FXML
    private Button btPagar;

    @FXML
    private Button btCancelar;

    @FXML
    private TableView<Compra> tvIntensCompra;

    @FXML
    private TableColumn<Compra, Integer> tcIdCompra;

    @FXML
    private TableColumn<Compra, String> tcFilmeCompra;

    @FXML
    private TableColumn<Compra, LocalDate> tcDataSessaoCompra;

    @FXML
    private TableColumn<Compra, Time> tcHorarioCompra;

    @FXML
    private TableColumn<Compra, TipoIngresso> tcTipo;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	tcNomeFilme.setCellValueFactory(new PropertyValueFactory<>("filme"));
    	tcNomeSala.setCellValueFactory(new PropertyValueFactory<>("sala"));
    	tcDataExibicao.setCellValueFactory(new PropertyValueFactory<>("dataExibicao"));
    	tcHoraInicio.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
    	tcHoraTermino.setCellValueFactory(new PropertyValueFactory<>("horaTermino"));
    	tcQtPoltronas.setCellValueFactory(new PropertyValueFactory<>("poltronas"));
    	
    	 buscarSessao();
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
	
    public void buscarSessao() {
    	SessaoRepository repository = new SessaoRepository(JPAFactory.getEntityManager());
		List<Sessao> lista = repository.getListSessoes(filme);

		if (lista.isEmpty()) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Informação:");
			alerta.setHeaderText(null);
			alerta.setContentText("A consulta não retornou dados!");
			alerta.show();
		}

		tvSessao.setItems(FXCollections.observableList(lista));
    }
}
