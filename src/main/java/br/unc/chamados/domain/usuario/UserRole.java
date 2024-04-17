package br.unc.chamados.domain.usuario;

public enum UserRole {
    ADMIN("admin"),
    TI("ti"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
