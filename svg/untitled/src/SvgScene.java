import java.util.ArrayList;
import java.util.List;

public class SvgScene {
    private float width, height;
    private List<Polygon> pols;

    public SvgScene(float width, float height)
    {
        this.width = width;
        this.height = height;
        pols = new ArrayList<Polygon>();
    }

    public float getWidth()
    { return width; }
    public float getHeight()
    { return height; }
    public SvgScene setWidth(float width)
    { this.width = width; return this; }
    public SvgScene setHeight(float height)
    { this.height = height; return this; }

    public SvgScene addPolygon(Polygon pol)
    {
        pols.add(new Polygon(pol));
        return this;
    }
    public Polygon getPolygon(int index)
    { return pols.get(index); }
    public SvgScene setPolygon(int index, Polygon pol)
    { pols.set(index, pol); return this; }

    public String toSvg()
    {
        String tmp = "<svg height=\"" + height + "\" width=\"" + width + "\" xmlns=\"http://www.w3.org/2000/svg\">";
        for (int i = 0; i < pols.size(); ++i)
        {
            tmp += "\n    " + pols.get(i).toSvg();
        }

        tmp += "\n</svg>";
        return tmp;
    }
}
