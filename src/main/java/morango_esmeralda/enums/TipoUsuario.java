package morango_esmeralda.enums;

public enum TipoUsuario {
    ADMINISTRADOR("admin"),
    CLIENTE("cliente");

    private String tipo;

    TipoUsuario(String tipo) {
        this.tipo = tipo;
    }

    public String getRole() {
        return tipo;
    }

}

