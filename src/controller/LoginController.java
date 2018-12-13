package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import application.Util;
import factory.JPAFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.GeneroFilme;
import model.Perfil;
import model.Usuario;
import repository.UsuarioRepository;

public class LoginController extends Controller implements Initializable {
	int tentativa = 3;
	
    @FXML
    private TextField tfCpf;
    
    @FXML
    private PasswordField pfSenhaUser;
    

    @FXML
    private ComboBox<Perfil> cbModo;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Controller.setUsuarioLogado(null);
		
		cbModo.getItems().addAll(Perfil.values());
		
		cbModo.setCellFactory(c -> new ListCell<Perfil>() {
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
		cbModo.setButtonCell(new ListCell<Perfil>() {
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
	void handleCancelar(ActionEvent event) {
		System.exit(-1);
	}

	@FXML
    void handleAcessar(ActionEvent event) throws IOException {

		System.out.println("#########Usuário: " + tfCpf.getText() + " - Senha: " + Util.encrypt(pfSenhaUser.getText().trim()));
    	
    	 UsuarioRepository repository = new UsuarioRepository(JPAFactory.getEntityManager());
    	 //List<Usuario> usuario = repository.getLogin(tfCpf.getText(), pfSenhaUser.getText());
    	 //List<Usuario> usuario = repository.getLogin(tfCpf.getText(), Util.encrypt(pfSenhaUser.getText().trim()));
    	 
    	 Usuario usuario = repository.getUsuario(tfCpf.getText(), Util.encrypt(pfSenhaUser.getText().trim()));

    	 if (usuario != null) {
    		 Controller.setUsuarioLogado(usuario);
    		 
		    Button button = (Button) event.getSource();
		    Stage stage = (Stage) button.getScene().getWindow();
		    stage.close();
		    
		    System.out.println("#########");

    	 }else{
	    	 tentativa--;
	
	    	 if(tentativa != 0)
	    		 System.out.println("Você tem mais " + tentativa + " tentativa");
	    	 else if (tentativa == 0)
	    		 System.exit(-1);
	    	 
	    	 System.out.println("#########´´´´´´´´´´´´´´´´´´´´");
    	 }
//    	Button button = (Button) event.getSource();
//    	Stage stage = (Stage) button.getScene().getWindow();
//    	stage.close();
    }

	public List<Usuario> verificaLogin(String login, String senha) {
		UsuarioRepository repository = new UsuarioRepository(JPAFactory.getEntityManager());
		List<Usuario> lista = repository.getUsuarioLogado(login, senha);

		return lista;
	}
}
