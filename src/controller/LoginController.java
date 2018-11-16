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

public class LoginController extends Controller implements Initializable
{
	@FXML
	private static TextField tfUsuario;
	
	@FXML
	private static PasswordField tfSenha;

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
    	int i = 0;

		//while (i <= 10) {
			String use = "123";
    		String sen = "123";
    		
    		System.out.println("#########" + tfUsuario.getText());

			//verificaLogin(tfUsuario.getText(), tfSenha.getText());
	    	UsuarioRepository repository = new UsuarioRepository(JPAFactory.getEntityManager());
	    	List<Usuario> lista = repository.getUsuarioLogado(tfUsuario.getText(), tfSenha.getText());
	    	
			if(!lista.isEmpty()) {
				//System.out.println("############### - " + lista.get(0).getNome().toString());
				//System.exit(0);
				System.out.println("#########" + lista.get(0).getLogin());

			}
			
			/*if(!lista.isEmpty()) {
				i++;
				if(i==3) {
					System.exit(0);
				}
			}else {
				break;
			}
*/
		//}
        	
    	
    	
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
