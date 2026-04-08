import com.google.common.base.Strings;

import java.util.Locale;

public class StrokeShapeDecorator extends ShapeDecorator
{
    String color;
    double width;

    public StrokeShapeDecorator(Shape shape, String stroke, double width) {
        super(shape);
        this.width = width;
        color = stroke;
    }

    @Override
    public BoundingBox boundingBox() {
        var old = super.boundingBox();
        return new BoundingBox(old.x() - (width/2), old.y() - (width/2),
            old.width() + width, old.height() + width);
    }

    @Override
    public String toSvg(String attr) {
        return super.toSvg(String.format(Locale.ENGLISH, "stroke=\"%s\" stroke-width=\"%f\"", color, width) +
                (Strings.isNullOrEmpty(attr) ? "" : ' ' + attr));
    }
}
