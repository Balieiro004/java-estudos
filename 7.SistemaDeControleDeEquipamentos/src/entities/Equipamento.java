package entities;

public class Equipamento {

    private static int contador;
    private int id;
    private String nome;
    private String patrimonio;
    private String serviceTag;
    private boolean status;

    public Equipamento() {
    }

    public Equipamento(String nome, String patrimonio, String serviceTag) {
        contador++;
        this.id = contador;
        this.nome = nome;
        this.patrimonio = patrimonio;
        this.serviceTag = serviceTag;
        this.status = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getServiceTag() {
        return serviceTag;
    }

    public void setServiceTag(String serviceTag) {
        this.serviceTag = serviceTag;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Equipamento" +
                "\nid: " + id +
                "\nnome: " + nome +
                "\npatrimonio: " + patrimonio +
                "\nserviceTag: " + serviceTag +
                "\nstatus: " + status;
    }
}
