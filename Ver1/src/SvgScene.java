import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", ""})
public class SvgScene {
    private float width, height;
    private final List<Shape> pols;

    public SvgScene(float width, float height)
    {
        this.width = width;
        this.height = height;
        pols = new ArrayList<>();
    }

    public float getWidth()
    { return width; }
    public float getHeight()
    { return height; }
    public SvgScene setWidth(float width)
    { this.width = width; return this; }
    public SvgScene setHeight(float height)
    { this.height = height; return this; }

    public SvgScene addShape(Polygon pol)
    {
        pols.add(new Polygon(pol));
        return this;
    }
    public Shape getShape(int index)
    { return pols.get(index); }
    public SvgScene addShape(int index, Polygon pol)
    { pols.set(index, pol); return this; }

    public String toSvg()
    {
        StringBuilder tmp = new StringBuilder("<svg height=\"" + height + "\" width=\"" + width + "\" xmlns=\"http://www.w3.org/2000/svg\">");
        for (Shape pol : pols) {
            tmp.append("\n    ").append(pol.toSvg());
        }

        tmp.append("\n</svg>");
        return tmp.toString();
    }

    public void Save(String path, boolean fullSize) throws IOException {

        FileWriter fw = new FileWriter(path);
        if(fullSize)
            fw.write(toSvg());
        else
        {
            float max_x = 0, max_y = 0;
            for (Shape pol : pols) {
                var bb = pol.boundingBox();
                if (max_x < bb.x() + bb.width())
                    max_x = bb.x() + bb.width();
                if (max_y < bb.y() + bb.height())
                    max_y = bb.y() + bb.height();
            }
            float tmp_x = width, tmp_y = height;
            width = max_x; height = max_y;
            fw.write(toSvg());
            width = tmp_x; height = tmp_y;
        }
        fw.close();
    }
}
