package models;

//classe principal dos alimentos
public class Alimentos {
    private int alimentos_id;
    private String nome;
    private int calorias;
    private int sabor;
    private String saciedade;

    //método construtores

    public Alimentos(){
    }

    public Alimentos(int alimentos_id, String nome, int calorias, int sabor, String saciedade){
        this.alimentos_id = alimentos_id;
        this.nome = nome;
        this.calorias = calorias;
        this.sabor = sabor;
        this.saciedade = saciedade;
    }


    //métodos gets
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
//métodos sets
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
//mostrar os dados
    @Override
    public String toString(){
        return "Alimentos [alimentos_id=" + alimentos_id + ",nome=" + nome + ", calorias=" + calorias + ",sabor=" + sabor + ",saciedade=" + saciedade + "]";
    }
}
