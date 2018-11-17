package controller;

import java.net.URL;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import factory.JPAFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Filme;
import model.Sala;
import model.Sessao;
import repository.FilmeRepository;
import repository.SalaRepository;
import repository.SessaoRepository;

public class SessaoController extends Controller<Sessao> implements Initializable{
	
	private Sessao sessao;
	
	private Filme filme;
	
	private Sala sala;
	
    @FXML
    private ComboBox<Sala> cbSala;
    
    @FXML
    private ComboBox<Filme> cbTituloFilme;
    
    @FXML
    private DatePicker dpDataExibicao;

    @FXML
    private TextField tfHoraInicio;

    @FXML
    private TextField tfHoraTermino;
    
    @FXML
    private TextField tfBuscaSessao;
    
    @FXML
    private TableView<Sessao> tvSessao;

    @FXML
    private TableColumn<Sessao, Integer> tcId;

    @FXML
    private TableColumn<Sessao, Integer> tcNomeFilme;

    @FXML
    private TableColumn<Sessao, Integer> tcNomeSala;
    
    @FXML
    private TableColumn<Sessao, LocalDate> tcDataExibicao;

    @FXML
    private TableColumn<Sessao, Date> tcHoraInicio;

    @FXML
    private TableColumn<Sessao, Date> tcHoraTermino;

    @FXML
    private Button btLimpar;

    @FXML
    private Button btSalvar;
    
    @FXML
    private Button btExcluir;

    @FXML
    private Button btAlterar;
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	tcNomeFilme.setCellValueFactory(new PropertyValueFactory<>("filme"));
    	tcNomeSala.setCellValueFactory(new PropertyValueFactory<>("sala"));
    	tcDataExibicao.setCellValueFactory(new PropertyValueFactory<>("dataExibicao"));
    	tcHoraInicio.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
    	tcHoraTermino.setCellValueFactory(new PropertyValueFactory<>("horaTermino"));
    	
    	carregarComboBoxFilme();
    	carregarComboBoxSala();
    }

    @FXML
    void handleLimpar(ActionEvent event) {
		cbTituloFilme.setValue(null);
		cbSala.setValue(null);
		tfHoraInicio.setText("");
		tfHoraTermino.setText("");
		dpDataExibicao.setValue(null);
		
		filme = null;
    }

    @FXML
    void handleSalvar(ActionEvent event) throws ParseException{
    	getSessao().setFilme(cbTituloFilme.getValue());
    	getSessao().setSala(cbSala.getValue());
    	getSessao().setDataExibicao(dpDataExibicao.getValue());
    	stringToTime();

    	super.save(getSessao());

		handleLimpar(event);
		
		atualizarBotoes();
		
		atualizarTabela();
    }
    

    @FXML
    void handleExcluir(ActionEvent event) {
    	// MENSAGEM DE ALERTA PARA O USUARIO CONFIRMAR UMA EXCLUSAO
    			Alert alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Confirmação");
    			alert.setHeaderText("Está operação excluirá todas as informações selecionadas da base de dados.");
    			alert.setContentText("Deseja realmente excluir?");
    			// Capturar as resposta do usuario sobre a mensagem de confirmacao
    			Optional<ButtonType> resposta = alert.showAndWait();
    			if (resposta.get().equals(ButtonType.OK)) {
    				super.remove(getSessao());
    				handleLimpar(event);
    			} else if (resposta.get().equals(ButtonType.CANCEL)) {

    			}
    			
    			atualizarBotoes();
    			
    			atualizarTabela();
    }
    
    @FXML
    void handleAlterar(ActionEvent event) throws ParseException {
    	getSessao().setFilme(cbTituloFilme.getValue());
    	getSessao().setSala(cbSala.getValue());
    	getSessao().setDataExibicao(dpDataExibicao.getValue());
    	
    	stringToTime();

    	super.save(getSessao());

		handleLimpar(event);
		
		atualizarBotoes();
		
		atualizarTabela();
		
    }
    

    @FXML
    void handleOnMouseClicked(MouseEvent event) {
    	// VERIFICANDO SE AÇÃO DO BOTÃO PRINCIPAL QUE FOI CLICADO
    	if (event.getButton().equals(MouseButton.PRIMARY)) {
    		// VERIFICANDO SE A QUANTIDADE DE CLIQUES NO BOTÃO PRIMÁRIO IGUAL A 2
    		if (event.getClickCount()==2) {
				//preenche
    			sessao = tvSessao.getSelectionModel().getSelectedItem();
    			cbTituloFilme.setValue(getSessao().getFilme());
    			cbSala.setValue(getSessao().getSala());
    			dpDataExibicao.setValue(getSessao().getDataExibicao());
    			tfHoraInicio.setText(getSessao().getHoraInicio().toString());
    			tfHoraTermino.setText(getSessao().getHoraTermino().toString());
    			
    			atualizarBotoes();
			}
		}
    }
    

    @FXML
    void handleBuscarSessao(ActionEvent event) {
    	SessaoRepository repository = new SessaoRepository(JPAFactory.getEntityManager());
		List<Sessao> lista = repository.getListSessoes();

		if (lista.isEmpty()) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Informação:");
			alerta.setHeaderText(null);
			alerta.setContentText("A consulta não retornou dados!");
			alerta.show();
		}

		tvSessao.setItems(FXCollections.observableList(lista));
		atualizarBotoes();
    }
    
    public void carregarComboBoxFilme() {
		FilmeRepository repository = new FilmeRepository(JPAFactory.getEntityManager());
		List<Filme> lista = repository.getListNomesFilmes();

		cbTituloFilme.setItems(FXCollections.observableList(lista));
	}
    
    public void carregarComboBoxSala() {
		SalaRepository repository = new SalaRepository(JPAFactory.getEntityManager());
		List<Sala> lista = repository.getListNomesSalas();

		cbSala.setItems(FXCollections.observableList(lista));
	}
    
    private void atualizarBotoes() {
		btSalvar.setDisable(getSessao().getId() != null);
		btAlterar.setDisable(getSessao().getId() == null);
		btExcluir.setDisable(getSessao().getId() == null);
	}
    
    public void stringToTime() throws ParseException
    {
    	
    	//Convertendo String para o tipo Time (Hora Inicio)
    	String strHora = tfHoraInicio.getText().toString();
    	SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
    	Date data = formatador.parse(strHora);
    	Time time = new Time(data.getTime());
 
    	getSessao().setHoraInicio(time);
    	//Convertendo String para o tipo Time (Hora Termino)
    	strHora = tfHoraTermino.getText().toString();
    	data = formatador.parse(strHora);
    	time = new Time(data.getTime());
    	
    	getSessao().setHoraTermino(time);
    }
    
    public void atualizarTabela()
    {
    	SessaoRepository repository = new SessaoRepository(JPAFactory.getEntityManager());
		List<Sessao> lista = repository.getListSessoes();
		tvSessao.setItems(FXCollections.observableList(lista));
		atualizarBotoes();
    }

	public Sessao getSessao() {
		if (sessao == null)
			sessao = new Sessao();
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

}
