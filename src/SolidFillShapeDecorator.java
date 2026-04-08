import com.google.common.base.Strings;

import java.util.Locale;

public class SolidFillShapeDecorator extends ShapeDecorator
{
    String color;

    public SolidFillShapeDecorator(Shape shape, String fill)
    {
        super(shape);
        color = fill;
    }

    @Override
    public String toSvg(String attr) {
        return super.toSvg(String.format(Locale.ENGLISH, "fill=\"%s\"%s", color,
                Strings.isNullOrEmpty(attr) ? "" : ' ' + attr));
    }
}
