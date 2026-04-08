import com.google.common.base.Strings;

import java.util.Locale;

public class SolidFilledPolygon extends Polygon
{
    private String color;

    public SolidFilledPolygon(Vec2[] points, String color) {
        super(points);
        this.color = color;
    }

    @Override
    public String toSvg(String attr)
    {
        return super.toSvg(String.format(Locale.ENGLISH, "fill=\"%s\"%s", color,
            Strings.isNullOrEmpty(attr) ? "" : ' ' + attr));
    }
}
