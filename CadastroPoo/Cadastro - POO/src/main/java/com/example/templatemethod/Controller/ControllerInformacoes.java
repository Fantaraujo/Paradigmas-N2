package com.example.templatemethod.Controller;

import com.example.templatemethod.Entity.Usuario;
import com.example.templatemethod.Service.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class ControllerInformacoes {

    @FXML
    private Button btnVer, btnModificar, btnRemover, btnSair;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    public void Initialize() {

    }

    @FXML
    private void ver() {

        List<Usuario> usuarios = usuarioService.listar();

        if (usuarios.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Usuários");
            alerta.setHeaderText(null);
            alerta.setContentText("Nenhum usuário cadastrado.");
            alerta.showAndWait();
            return;
        }

        String dados = "";

        for (Usuario usuario : usuarios) {
            dados += "ID: " + usuario.getId() + "\n";
            dados += "Nome: " + usuario.getNome() + "\n";
            dados += "E-mail: " + usuario.getEmail() + "\n";
            dados += "-------------------------\n";
        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Usuários cadastrados");
        alerta.setHeaderText("Lista de usuários");
        alerta.setContentText(dados);
        alerta.showAndWait();
    }

    @FXML
    private void modificar() {

        TextInputDialog dialogoId = new TextInputDialog();

        dialogoId.setTitle("Modificar usuário");
        dialogoId.setHeaderText("Digite o ID do usuário");
        dialogoId.setContentText("ID:");

        Optional<String> resultadoId = dialogoId.showAndWait();

        if (resultadoId.isEmpty()) {
            return;
        }

        String id = resultadoId.get();

        Usuario usuario = usuarioService.buscarPorId(id);

        if (usuario == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText(null);
            alerta.setContentText("Não existe usuário com o ID informado.");
            alerta.showAndWait();
            return;
        }

        TextInputDialog dialogoNome =
                new TextInputDialog(usuario.getNome());

        dialogoNome.setTitle("Modificar usuário");
        dialogoNome.setHeaderText("Digite o novo nome");
        dialogoNome.setContentText("Nome:");

        Optional<String> resultadoNome = dialogoNome.showAndWait();

        if (resultadoNome.isEmpty()) {
            return;
        }

        String nome = resultadoNome.get();

        TextInputDialog dialogoEmail =
                new TextInputDialog(usuario.getEmail());

        dialogoEmail.setTitle("Modificar usuário");
        dialogoEmail.setHeaderText("Digite o novo e-mail");
        dialogoEmail.setContentText("E-mail:");

        Optional<String> resultadoEmail = dialogoEmail.showAndWait();

        if (resultadoEmail.isEmpty()) {
            return;
        }

        String email = resultadoEmail.get();

        Usuario usuarioAtualizado = new Usuario(id, nome, email);

        if (usuarioService.atualizar(usuarioAtualizado)) {

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Sucesso");
            alerta.setHeaderText(null);
            alerta.setContentText("Usuário modificado com sucesso.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void remover() {

        TextInputDialog dialogoId = new TextInputDialog();

        dialogoId.setTitle("Remover usuário");
        dialogoId.setHeaderText("Digite o ID do usuário");
        dialogoId.setContentText("ID:");

        Optional<String> resultadoId = dialogoId.showAndWait();

        if (resultadoId.isEmpty()) {
            return;
        }

        String id = resultadoId.get();

        Usuario usuario = usuarioService.buscarPorId(id);

        if (usuario == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText(null);
            alerta.setContentText("Não existe usuário com o ID informado.");
            alerta.showAndWait();
            return;
        }

        if (usuarioService.excluir(id)) {

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Sucesso");
            alerta.setHeaderText(null);
            alerta.setContentText("Usuário removido com sucesso.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
}


