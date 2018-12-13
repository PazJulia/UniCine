package controller;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import factory.JPAFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Compra;
import model.Filme;
import model.GeneroFilme;
import model.Ingresso;
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
    private TableColumn<Sessao, Integer> tcId;
    
    @FXML
    private TableColumn<Sessao, String> tcNomeFilme;

    @FXML
    private TableColumn<Sessao, LocalDate> tcDataExibicao;

    @FXML
    private TableColumn<Sessao, Time> tcHoraInicio;

    @FXML
    private TableColumn<Sessao, Time> tcHoraTermino;

    @FXML
    private TableColumn<Sessao, String> tcNomeSala;

    @FXML
    private TableColumn<Sessao, Integer> tcQtPoltronas;

    @FXML
    private Button btConfirma;

    @FXML
    private Button btAdd;

    @FXML
    private Button btPagar;

    @FXML
    private Button btCancelar;

    @FXML
    private TableView<Ingresso> tvIntensCompra;

    @FXML
    private TableColumn<Ingresso, Integer> tcSesssaoCompra;

    @FXML
    private TableColumn<Ingresso, TipoIngresso> tcTipoIngressoCompra;

    @FXML
    private TableColumn<Ingresso, Integer> tcQtdIngressoCompra;

    @FXML
    private TableColumn<Ingresso, String> tcPoltronaCompra;
    
    @FXML
    private ComboBox<TipoIngresso> cbTipoIngresso;
    
    @FXML
    private ComboBox<TipoPagamento> cbPagamento;
    
    @FXML
    private ComboBox<Sessao> cbSessao;
    
    @FXML
    private TextField tfPoltrona;
    
    @FXML
    private TextField tfSessao;

    @FXML
    private TextField tfQuantidade;
    

	
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
    	
    	tcSesssaoCompra.setCellValueFactory(new PropertyValueFactory<>("idSessao"));
    	tcQtdIngressoCompra.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    	tcTipoIngressoCompra.setCellValueFactory(new PropertyValueFactory<>("tipoIngresso"));
    	tcPoltronaCompra.setCellValueFactory(new PropertyValueFactory<>("poltrona"));
    	
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
    	
    	carregaComboBoxSessao();
	}
	
	@FXML
	void handleAdicionarIngresso(ActionEvent event) {
		Ingresso ingresso = new Ingresso();
		ingresso.setIdSessao(cbSessao.getValue());
		ingresso.setCompra(getCompra());
		ingresso.setTipoIngresso(cbTipoIngresso.getValue());
		ingresso.setPoltrona(tfPoltrona.getText());
		ingresso.setQuantidade(Integer.parseInt(tfQuantidade.getText()));

		if (getCompra().getListaIngresso() == null)
			getCompra().setListaIngresso(new ArrayList<Ingresso>());

		getCompra().getListaIngresso().add(ingresso);

		tvIntensCompra.setItems(FXCollections.observableList(getCompra().getListaIngresso()));
		
		//Limpar campos
		cbTipoIngresso.setValue(null);
		tfPoltrona.setText(null);
	}
	

    @FXML
    void handleFinalizarCompra(ActionEvent event) {
    	getCompra().setTipoPagamento(cbPagamento.getValue());

    	super.save(getCompra());
    	Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle("Informação");
		alerta.setHeaderText(null);
		alerta.setContentText("Compra realizada com sucesso!");
		alerta.show();
		
		cbPagamento.setValue(null);
		tvIntensCompra.getItems().clear();
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Compra realizada");
		alert.setHeaderText("Sua compra foi realizada com sucesso");
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
    
    public void carregaComboBoxSessao(){
    	SessaoRepository repository = new SessaoRepository(JPAFactory.getEntityManager());
		List<Sessao> lista = repository.getSessoes();
		
		cbSessao.setItems(FXCollections.observableList(lista));

    }

	public Compra getCompra() {
		if (compra == null)
			compra = new Compra();
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
}
