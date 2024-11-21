package controller;

import model.Usuario;
import view.UsuarioView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    private UsuarioView view;
    private List<Usuario> listaUsuarios;

    public UsuarioController(UsuarioView view) {
        this.view = view;
        this.listaUsuarios = new ArrayList<>();
        configurarAcoes();
    }

    // Configura os eventos dos botões da View
    private void configurarAcoes() {
        view.getBotaoSalvar().addActionListener(e -> salvarUsuario());
        view.getBotaoVer().addActionListener(e -> listarUsuarios());
        view.getBotaoAtualizar().addActionListener(e -> atualizarUsuario());
        view.getBotaoExcluir().addActionListener(e -> excluirUsuario());
        view.getBotaoFechar().addActionListener(e -> fecharAplicacao());
    }

    // Método para salvar um usuário
    private void salvarUsuario() {
        String nome = view.getCampoTextoNome().getText();
        String email = view.getCampoTextoEmail().getText();
        String endereco = view.getCampoTextoEndereco().getText();

        if (nome.isEmpty() || email.isEmpty() || endereco.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Gera automaticamente o ID com base no tamanho da lista
        Usuario usuario = new Usuario(String.valueOf(listaUsuarios.size() + 1), nome, email, endereco);
        listaUsuarios.add(usuario);

        JOptionPane.showMessageDialog(view, "Usuário salvo com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
        listarUsuarios(); // Atualiza a tabela após salvar
        limparCampos();
    }

    // Método para listar todos os usuários na tabela
    private void listarUsuarios() {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) view.getTabelaUsuarios().getModel();
        model.setRowCount(0); // Limpa os dados atuais da tabela

        for (Usuario usuario : listaUsuarios) {
            model.addRow(new Object[]{usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getEndereco()});
        }
    }

    // Método para atualizar um usuário existente
    private void atualizarUsuario() {
        int selectedRow = view.getTabelaUsuarios().getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view, "Selecione um usuário na tabela!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = view.getTabelaUsuarios().getValueAt(selectedRow, 0).toString();
        String nome = view.getCampoTextoNome().getText();
        String email = view.getCampoTextoEmail().getText();
        String endereco = view.getCampoTextoEndereco().getText();

        if (nome.isEmpty() || email.isEmpty() || endereco.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = listaUsuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            JOptionPane.showMessageDialog(view, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setEndereco(endereco);

        JOptionPane.showMessageDialog(view, "Usuário atualizado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
        listarUsuarios();
        limparCampos();
    }

    // Método para excluir um usuário existente
    private void excluirUsuario() {
        int selectedRow = view.getTabelaUsuarios().getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view, "Selecione um usuário na tabela!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = view.getTabelaUsuarios().getValueAt(selectedRow, 0).toString();

        Usuario usuario = listaUsuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            JOptionPane.showMessageDialog(view, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        listaUsuarios.remove(usuario);

        JOptionPane.showMessageDialog(view, "Usuário excluído com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
        listarUsuarios();
        limparCampos();
    }

    // Método para limpar os campos de texto na View
    private void limparCampos() {
        view.getCampoTextoNome().setText("");
        view.getCampoTextoEmail().setText("");
        view.getCampoTextoEndereco().setText("");
    }

    // Método para fechar a aplicação
    private void fecharAplicacao() {
        int confirm = JOptionPane.showConfirmDialog(view,
                "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0); // Fecha o programa
        }
    }
}
