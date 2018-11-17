package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import factory.JPAFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Usuario;
import repository.UsuarioRepository;

public class LoginController extends Controller implements Initializable {
	int tentativa = 3;
	
    @FXML
    private TextField tfCpf;
    
    @FXML
    private PasswordField pfSenhaUser;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	void handleCancelar(ActionEvent event) {
		System.exit(-1);
	}

	@FXML
    void handleAcessar(ActionEvent event) throws IOException {
//    	retornou um usuario
//    	Usuario usuario = new Usuario();
//    	usuario.setNome("Joao da Silva");
//    	usuario.setPerfil(Perfil.CADASTRO);
//    	Controller.setUsuarioLogado(usuario);
		System.out.println("#########Usuário: " + tfCpf.getText() + " - Senha: " + pfSenhaUser.getText());
    	
    	 UsuarioRepository repository = new UsuarioRepository(JPAFactory.getEntityManager());
    	 List<Usuario> usuario = repository.getLogin(tfCpf.getText(), pfSenhaUser.getText());

    	 if (!usuario.isEmpty()) {

	    	 for (Usuario lista : usuario){
		    	 Usuario usuarioteste = lista;
		    	 Controller.setUsuarioLogado(usuarioteste);
	    	 }
		    Button button = (Button) event.getSource();
		    Stage stage = (Stage) button.getScene().getWindow();
		    stage.close();

    	 }else{
	    	 tentativa--;
	
	    	 if(tentativa != 0)
	    		 System.out.println("Você tem mais " + tentativa + " tentativa");
	    	 else if (tentativa == 0)
	    		 System.exit(-1);
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
