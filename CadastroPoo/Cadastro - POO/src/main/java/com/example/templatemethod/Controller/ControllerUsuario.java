package com.example.templatemethod.Controller;

import com.example.templatemethod.Entity.Usuario;
import com.example.templatemethod.Service.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ControllerUsuario {

    @FXML
    private TextField txtNome, txtEmail;

    @FXML
    private Button btnConfirmar, btnDados;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    public void initialize() {

        btnConfirmar.setDisable(true);

        txtNome.setOnKeyReleased(event -> {
            if (txtNome.getText().isBlank() || txtEmail.getText().isBlank()) {
                btnConfirmar.setDisable(true);
            } else {
                btnConfirmar.setDisable(false);
            }
        });

        txtEmail.setOnKeyReleased(event -> {
            if (txtNome.getText().isBlank() || txtEmail.getText().isBlank()) {
                btnConfirmar.setDisable(true);
            } else {
                btnConfirmar.setDisable(false);
            }
        });
    }

    @FXML
    private void confirmar() {

        if (txtNome.getText().isBlank() || txtEmail.getText().isBlank()) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Atenção");
            alerta.setHeaderText(null);
            alerta.setContentText("Preencha o nome e o e-mail antes de confirmar.");
            alerta.showAndWait();

            return;
        }

        int maiorId = 0;

        for (Usuario usuario : usuarioService.listar()) {
            int idAtual = Integer.parseInt(usuario.getId());

            if (idAtual > maiorId) {
                maiorId = idAtual;
            }
        }

        String id = String.valueOf(maiorId + 1);
        String nome = txtNome.getText();
        String email = txtEmail.getText();

        Usuario usuario = new Usuario(id, nome, email);

        if (usuarioService.cadastrar(usuario)) {

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Sucesso");
            alerta.setHeaderText(null);
            alerta.setContentText("Usuário cadastrado com sucesso!");
            alerta.showAndWait();

            txtNome.clear();
            txtEmail.clear();
        }
    }

    @FXML
    private void abrirDados() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/templatemethod/hello-view2.fxml"));

            Scene scene = new Scene(loader.load());

            Stage stage = new Stage();
            stage.setTitle("Informações dos Usuários");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}