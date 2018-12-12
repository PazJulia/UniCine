package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import factory.EditarFilmeListControllerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import model.Filme;
import model.GeneroFilme;

public class FilmeController extends Controller<Filme> implements Initializable {

	private Filme filme;
	
	@FXML
	private TextField tfTitulo;

	@FXML
	private TextField tfDiretor;

	@FXML
	private TextField tfDuracao;

	@FXML
	private TextField tfElenco;

	@FXML
	private ComboBox<GeneroFilme> cbGenero;

	@FXML
	private TextField tfProdutora;

	@FXML
    private Button btL;

    @FXML
    private Button bt10;

    @FXML
    private Button bt12;

    @FXML
    private Button bt14;

    @FXML
    private Button bt16;

    @FXML
    private Button bt18;

	@FXML
	private TextField tfDescricao;

	@FXML
	private Button btProx;

	@FXML
	private Button btLimpar;
	
    @FXML
    private Button btExcluir;

    @FXML
    private Button btAlterar;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbGenero.getItems().addAll(GeneroFilme.values());
		
		cbGenero.setCellFactory(c -> new ListCell<GeneroFilme>() {
			@Override
			protected void updateItem(GeneroFilme item, boolean empty) {
				super.updateItem(item, empty);
				
				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});
		
		// seobrescreendo o método que mostra o conteudo selecionado
		cbGenero.setButtonCell(new ListCell<GeneroFilme>() {
			@Override
			protected void updateItem(GeneroFilme item, boolean empty) {
				super.updateItem(item, empty);
				
				if (item == null || empty)
					setText(null);
				else
					setText(item.getLabel());
			}
		});
	}

	@FXML
	void handleLimpar(ActionEvent event) {
		//limpando as informacoes do cliente
		tfTitulo.setText("");
		tfDiretor.setText("");
		tfDuracao.setText("");
		tfElenco.setText("");
		cbGenero.getSelectionModel().clearSelection();
		tfProdutora.setText("");
		tfDescricao.setText("");

		filme = null;
		tfTitulo.requestFocus();
	}

	@FXML
	void handleProx(ActionEvent event) {

		getFilme().setTitulo(tfTitulo.getText());
		getFilme().setDiretor(tfDiretor.getText());
		getFilme().setDuracao(tfDuracao.getText());
		getFilme().setElenco(tfElenco.getText());
		getFilme().setGenero(cbGenero.getValue());
		getFilme().setProdutora(tfProdutora.getText());
		getFilme().getClassificacao();
		getFilme().setDescricao(tfDescricao.getText());

		super.save(getFilme());

		handleLimpar(event);
	}
	
    @FXML
    void handleAlterar(ActionEvent event) {
    	getFilme().setTitulo(tfTitulo.getText());
		getFilme().setDiretor(tfDiretor.getText());
		getFilme().setDuracao(tfDuracao.getText());
		getFilme().setElenco(tfElenco.getText());
		getFilme().setGenero(cbGenero.getValue());
		getFilme().setProdutora(tfProdutora.getText());
		getFilme().getClassificacao();
		getFilme().setDescricao(tfDescricao.getText());

		super.save(getFilme());
    	
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
			super.remove(getFilme());
			handleLimpar(event);
		} else if (resposta.get().equals(ButtonType.CANCEL)) {

		}
    }
    
	private void atualizarBotoes() {
		btProx.setDisable(getFilme().getId() != null);
		btAlterar.setDisable(getFilme().getId() == null);
		btExcluir.setDisable(getFilme().getId() == null);
	}

	

    @FXML
    void handleMouseClickedEditar(MouseEvent event) throws IOException {
    	EditarFilmeController listagem = EditarFilmeListControllerFactory.getInstance();
    	listagem.abrir();
		
		setFilme(listagem.getFilmeSelecionado());
		
		tfTitulo.setText(getFilme().getTitulo());
		tfDiretor.setText(getFilme().getDiretor());
		tfDescricao.setText(getFilme().getDescricao());
		tfElenco.setText(getFilme().getElenco());
		cbGenero.setValue(getFilme().getGenero());
		tfDuracao.setText(getFilme().getDuracao());
		tfProdutora.setText(getFilme().getProdutora());
		tfDescricao.setText(getFilme().getDescricao());
		
		atualizarBotoes();
    }
	
    @FXML
    void handleLivre(ActionEvent event) {
    	getFilme().setClassificacao(0);
    }
    
    @FXML
    void handle10(ActionEvent event) {
    	getFilme().setClassificacao(1);
    	}

    @FXML
    void handle12(ActionEvent event) {
    	getFilme().setClassificacao(2);
    }

    @FXML
    void handle14(ActionEvent event) {
    	getFilme().setClassificacao(4);
    }

    @FXML
    void handle16(ActionEvent event) {
    	getFilme().setClassificacao(5);
    }

    @FXML
    void handle18(ActionEvent event) {
    	getFilme().setClassificacao(6);
    }

	public Filme getFilme() {
		if (filme == null)
			filme = new Filme();
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

}
