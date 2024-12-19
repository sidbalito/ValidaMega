import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class ValidaMega {
    private static ValidaNúmeros valida = new ValidaNúmeros();
    public static void main(String[] args) {
     try {
            HashMap<Long, Long> bilhetes = new HashMap<>();
            long bilhete = (1<<20)-1;
            bilhetes.put(bilhete, null);
            System.out.println("Testar o bilhete: " + valida.dezenas(bilhete));
            verificaArranjos(bilhetes);
            System.out.println("Pressione ENTER para prosseguir...");
            System.in.read(); 

            verificaArranjos(valida.carregaBilhetes());//* */        
     } catch (Exception e) {
        System.err.println(e);
     }

        
                
    }    
    private static void verificaArranjos(HashMap<Long, Long> bilhetes) {
        long arranjo = 63;
        long últimoArranjo = arranjo << 54l;
        try {
            long checkpoint = System.currentTimeMillis();  
            long início = System.currentTimeMillis();
            long total = valida.arranjos(60, 6);
            DateFormat formato = DateFormat.getInstance();
            while(arranjo!=0) {
                Iterator<Long> iterador = bilhetes.keySet().iterator();
                //Thread.sleep(300); 
                while (arranjo<últimoArranjo) {
                    long bilhete = iterador.next();
                    if(System.currentTimeMillis() - checkpoint > 1000){
                        System.out.print("\033\143");
                        int posição = valida.posição(arranjo, 60);
                        System.out.println("Início: "+formato.format(início));
                        System.out.println(String.format("Arranjos verificados: %d", posição));
                        checkpoint = System.currentTimeMillis();
                        System.out.println("Estimativa para o término: "+formato.format(início+(checkpoint-início)*total/posição));//* */
                    }
                    if((arranjo & bilhete)==arranjo){
                        bilhetes.put(bilhete, arranjo);
                        break;
                    }
                }//* */
                arranjo = valida.próximo(arranjo, 60);
            } 
            System.out.println("Nenhum erro ao testar todas as combinações com os bilhetes:");
            for(long bilhete: bilhetes.keySet())
                System.out.println(valida.dezenas(bilhete));
        } catch (NoSuchElementException e) {
            System.err.println(String.format("Nenhum bilhete encontrado para %s", valida.dezenas(arranjo)));   
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}