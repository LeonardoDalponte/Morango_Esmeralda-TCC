package morango_esmeralda.domain.user;

public enum UserRole {
    ADMINISTRADOR("admin"),
    CLIENTE("cliente");

    private String tipo;

    UserRole(String tipo) {
        this.tipo = tipo;
    }

    public String getRole() {
        return tipo;
    }

}

