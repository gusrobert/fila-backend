package br.com.sp.fatec.springbootapp.DTO;

public class UsuarioDTO {

    private String email;

    private String login;

    private String senha;

    private String perfil;

    public String getEmail() {
        return email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
