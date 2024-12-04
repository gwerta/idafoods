import controllers.AlimentosController;


public class Main {

    //classe principal que inicializa o controller para iniciar o programa
   
    public static void main(String[] args) throws Exception {
       
        AlimentosController controller = new AlimentosController();
        controller.iniciar();
    }
}