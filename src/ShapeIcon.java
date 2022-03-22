import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ShapeIcon implements Icon {
    private int radius = 11;
    private Shape shape;
    private Color color;
    private boolean antiAliasing = true;

    ShapeIcon(Color color) {
        this.color = color;
        shape = new Ellipse2D.Double(0, 0, radius, radius);
    }

    Color getColor() {
        return color;
    }

    void setColor(Color color) {
        this.color = color;
    }

    void setShape(Shape shape) {
        this.shape = shape;
    }

    Shape getShape() {
        return shape;
    }

    /**
     * Use AntiAliasing when painting the shape
     *
     * @returns true for AntiAliasing false otherwise
     */
    public boolean isAntiAliasing() {
        return antiAliasing;
    }

    /**
     * Set AntiAliasing property for painting the Shape
     *
     * @param antiAliasing true for AntiAliasing, false otherwise
     */
    public void setAntiAliasing(boolean antiAliasing) {
        this.antiAliasing = antiAliasing;
    }


    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();
        if (antiAliasing) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        Rectangle bounds = shape.getBounds();
        g2d.translate(x - bounds.x, y - bounds.y);
        g2d.setColor(color);
        g2d.fill(shape);
        g2d.dispose();
    }

    @Override
    public int getIconWidth() {
        return radius;
    }

    @Override
    public int getIconHeight() {
        return radius;
    }
}
