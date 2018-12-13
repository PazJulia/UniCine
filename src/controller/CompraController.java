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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Compra;
import model.Filme;
import model.GeneroFilme;
import model.Sessao;
import model.TipoIngresso;
import model.TipoPagamento;
import repository.SessaoRepository;

public class CompraController extends Controller<Compra> implements Initializable{

	private Sessao sessao;
	
	private Filme filme;
	
	private Compra compra;
	
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
    private TableColumn<Compra, Integer> tcId;

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
    
    @FXML
    private ComboBox<TipoIngresso> cbTipoIngresso;
    
    @FXML
    private ComboBox<TipoPagamento> cbPagamento;

    
    @FXML
    private TextField tfPoltrona;
    
    @FXML
    private TextField tfSessao;
    

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	tcNomeFilme.setCellValueFactory(new PropertyValueFactory<>("filme"));
    	tcNomeSala.setCellValueFactory(new PropertyValueFactory<>("sala"));
    	tcDataExibicao.setCellValueFactory(new PropertyValueFactory<>("dataExibicao"));
    	tcHoraInicio.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
    	tcHoraTermino.setCellValueFactory(new PropertyValueFactory<>("horaTermino"));
    	tcQtPoltronas.setCellValueFactory(new PropertyValueFactory<>("poltronas"));
    	
    	buscarSessao();
    	
    	
    	cbTipoIngresso.getItems().addAll(TipoIngresso.values());
		
    	cbTipoIngresso.setCellFactory(c -> new ListCell<TipoIngresso>() {
			@Override
			protected void updateItem(TipoIngresso item, boolean empty) {
				super.updateItem(item, empty);
				
				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});
		
		// seobrescreendo o método que mostra o conteudo selecionado
    	cbTipoIngresso.setButtonCell(new ListCell<TipoIngresso>() {
			@Override
			protected void updateItem(TipoIngresso item, boolean empty) {
				super.updateItem(item, empty);
				
				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});
    	
    	cbPagamento.getItems().addAll(TipoPagamento.values());
    	
    	cbPagamento.setCellFactory(c -> new ListCell<TipoPagamento>() {
			@Override
			protected void updateItem(TipoPagamento item, boolean empty) {
				super.updateItem(item, empty);
				
				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});
		
		// seobrescreendo o método que mostra o conteudo selecionado
    	cbPagamento.setButtonCell(new ListCell<TipoPagamento>() {
			@Override
			protected void updateItem(TipoPagamento item, boolean empty) {
				super.updateItem(item, empty);
				
				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});
	}
	
	
	
	@FXML
	void handleAdicionar(ActionEvent event) {
		getCompra().setIdSessao(null);
		getCompra().setTipoIngresso(cbTipoIngresso.getValue());
		getCompra().setPoltrona(Integer.parseInt(tfPoltrona.getText()));
		getCompra().setTipoPagamento(null);

		super.save(getCompra());

//		handleLimpar(event);
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

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
}
