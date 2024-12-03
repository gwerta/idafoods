package models;

public class Alimentos {
    private int alimentos_id;
    private String nome;
    private int calorias;
    private int sabor;
    private String saciedade;

    public Alimentos(){
    }

    public Alimentos(int alimentos_id, String nome, int calorias, int sabor, String saciedade){
        this.alimentos_id = alimentos_id;
        this.nome = nome;
        this.calorias = calorias;
        this.sabor = sabor;
        this.saciedade = saciedade;
    }

    public int getAlimentosId(){
        return alimentos_id;
    }
    public String getNome(){
        return nome;
    }
    public int getCalorias(){
        return calorias;
    }

    public int getSabor(){
        return sabor;
    }
    public String getSaciedade(){
        return saciedade;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCalorias(int calorias){
        this.calorias = calorias;
    }
    public void setSabor(int sabor){
        this.sabor = sabor;
    }
    public void setSaciedade(String saciedade){
        this.saciedade = saciedade;
    }

    @Override
    public String toString(){
        return "Alimentos [alimentos_id=" + alimentos_id + ",nome=" + nome + ", calorias=" + calorias + ",sabor=" + sabor + ",saciedade=" + saciedade + "]";
    }
}
