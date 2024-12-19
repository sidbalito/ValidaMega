import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.HashMap;

public class ValidaNúmeros {

    public int posição(long arranjo, long limite){
        byte[] matriz = new byte[Long.bitCount(arranjo)];
        int pos = 0;
        for(int i = 0; i<64; i++)
            if(((1l<<i)&arranjo)!=0){
                matriz[pos]=(byte)(i+1);
                pos++;
            }
        int tamanho = matriz.length;
        int acumulador = 0;
        for(int i = 0; i<tamanho; i++){
            int j=i>0?matriz[i-1]+1:1;
            for(;j<matriz[i];j++)
                acumulador += arranjos((int)limite-j, tamanho-i-1);
        }
        return (int) acumulador;
    }

    public int arranjos(int maior, int menor) {
        if(menor > maior)throw new RuntimeException(String.format("O primeiro número não pode ser menor que o segundo. (%d<%d)", maior, menor));
        long acumulador = 1;
        int dif = maior-menor;
        if(dif<menor){
            int temp = dif;
            dif = menor;
            menor = temp;
        }
        for(int i = dif+1; i<=maior; i++)
            acumulador*=i;
        for(int i=1;i<=menor;i++) acumulador/= i;
        return (int)acumulador;
    }

    public long próximo(long arranjo, long limite) {
        long bits = Long.bitCount(arranjo);
        if(arranjo < ((1l<<bits)-1l)<<(limite-bits)){
            long i = limite -1; 
            if((arranjo & 1L<<i)!=0)
                for(;i>=9;i--)
                    if(((1l<<i)&arranjo)==0) break;
            for(;i>=0;i--)
                if(((1L<<i)&arranjo)!=0)break;
            arranjo &=((1l<<i)-1);
            i++;
            for(;i<(i+bits-Long.bitCount(arranjo));i++)
                arranjo |= (1l<<i);
            return arranjo;            
        }
        throw new RuntimeException("Não há mais arranjos");
    }

    public String dezenas(long arranjo){
        StringBuilder sb = new StringBuilder();
        for(long l = 0;l<64;l++)
            if((1l<<l&arranjo)!=0) sb.append(String.format("%02d ", l+1));
        return sb.toString();
    }

    public HashMap<Long, Long> carregaBilhetes(){
        HashMap<Long, Long> bilhetes = new HashMap<Long, Long>();
        try{
            DataInputStream in = new DataInputStream(new FileInputStream("megainviavel.dat"));
            long máscara = (1l<<60l)-1L;
            while (in.available()>0) {
                long bilhete = in.readLong();
                int númeroDeDezenas = Long.bitCount(bilhete & máscara);
                if(númeroDeDezenas < 6 || númeroDeDezenas > 20) new RuntimeException(String.format("O bilhete $s não é válido, pois possui %d dezenas entre 01 e 60", dezenas(bilhete), númeroDeDezenas));
                bilhetes.put(bilhete, 0l);
            }
        }catch(Exception e){}
        
        
        return bilhetes;
    }
}
