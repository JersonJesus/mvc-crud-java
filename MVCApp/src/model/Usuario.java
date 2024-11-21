package model;

/**
 *
 * @author Jerson Vndré
 */
public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String endereco;

    // Construtor
    public Usuario(String id, String nome, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Representação textual do objeto
    @Override
    public String toString() {
        return "Usuario{"
                + "id='" + id + '\''
                + ", nome='" + nome + '\''
                + ", email='" + email + '\''
                + ", endereco='" + endereco + '\''
                + '}';
    }
}
