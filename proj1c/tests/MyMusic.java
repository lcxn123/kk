import edu.princeton.cs.algs4.StdAudio;
import gh2.GuitarString;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyMusic {
    @Test
    public void AtestPlay(){

         final double C = 261.63;
         final double C_SHARP = 277.18;
         final double D = 293.66;
         final double D_SHARP = 311.13;
         final double E = 329.63;
         final double F = 349.23;
         final double F_SHARP = 369.99;
         final double G = 392.00;
         final double G_SHARP = 415.30;
         final double A = 440.0;  // 基准音
         final double A_SHARP = 466.16;
         final double B = 493.88;
        GuitarString aString = new GuitarString(A);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }


    @Test
    public void BtestPlay() {
        final double B = 493.88;
        GuitarString aString = new GuitarString(B);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }

    @Test
    public void CtestPlay() {
        final double C = 261.63;
        GuitarString aString = new GuitarString(C);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }

    public static void main(String[] args) {
        Map<String,Double> music = new LinkedHashMap<>();
        music.put("C", 261.63);
        music.put("C_SHARP", 277.18);
        music.put("D", 293.66);
        music.put("D_SHARP", 311.13);
        music.put("E", 329.63);
        music.put("F", 349.23);
        music.put("F_SHARP", 369.99);
        music.put("G", 392.00);
        music.put("G_SHARP", 415.30);
        music.put("A", 440.0);   // 基准音
        music.put("A_SHARP", 466.16);
        music.put("B", 493.88);
        for(Map.Entry<String,Double> entry : music.entrySet()){
            double p = entry.getValue();
            GuitarString aString = new GuitarString(p);
            aString.pluck();
            for (int i = 0; i < 50000; i += 1) {
                StdAudio.play(aString.sample());
                aString.tic();
            }
        }
    }
}
