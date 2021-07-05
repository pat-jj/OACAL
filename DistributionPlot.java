import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DistributionPlot {
    static public void plot(ArrayList<Integer> distribute_plot) throws IOException {
        int width = distribute_plot.size() * 10;
        int height = 200;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D ig2 = bi.createGraphics();
        ig2.setPaint(Color.blue);

        for (int i = 0; i < distribute_plot.size(); i++) {
            int dn = distribute_plot.get(i);
            if(dn ==  1) {
                ig2.drawOval(i * 10, dn * 100, 5, 5);
            }
        }

        ImageIO.write(bi, "PNG", new File("chart.PNG"));
    }
}
