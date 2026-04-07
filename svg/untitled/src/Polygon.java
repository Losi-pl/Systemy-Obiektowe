public class Polygon extends Shape {
    private Point[] ps;

    public Polygon()
    {
        super(new Style("none", "black", 1));
        ps = new Point[0];
    }

    public Polygon(Point[] points, Style style) {
        super(new Style(style));
        Point[] tmp = new Point[points.length];
        for (int i = 0; i < points.length; ++i)
            tmp[i] = new Point(points[i]);
        ps = tmp;
    }
    public Polygon(Point[] points) {
        this(points, new Style("none", "black", 1));
    }
    public Polygon(Polygon pol)
    { this(pol.ps, new Style("none", "black", 1)); }

    public Point getPoint(int index)
    { return new Point(ps[index]); }

    @Override
    public String toString() {
        String tmp = "Polygon[ ";
        for (int i = 0; i < ps.length; ++i)
            tmp += ps[i].toString(true) + (i < ps.length - 1 ? ", " : " ]");
        return tmp;
    }

    @Override
    public String toSvg()
    {
        //<polygon points="220,10 300,210 170,250 123,234" style="fill:white;stroke:black;stroke-width:1" />
        String tmp = "<polygon points=\"";
        for (int i = 0; i < ps.length; ++i)
            tmp += (ps[i].getX() + "," + ps[i].getY()) + (i < ps.length - 1 ? " " : "\" " + style.toSvg() + " />");
        return tmp;
    }

    public BoundingBox boundingBox()
    {
        float minX = ps[0].getX(), maxX = ps[0].getX(),
              minY = ps[0].getY(), maxY = ps[0].getY();
        for (int i = 0; i < ps.length; ++i)
        {
            if(minX > ps[i].getX())
                minX = ps[i].getX();
            if(maxX < ps[i].getX())
                maxX = ps[i].getX();

            if(minY > ps[i].getY())
                minY = ps[i].getY();
            if(maxY < ps[i].getY())
                maxY = ps[i].getY();
        }
        return new BoundingBox(minX, minY, maxX - minX, maxY - minY);
    }

    public static Polygon Square(Segment intersection)
    {
        var minX = Math.min(intersection.getPoint1().getX(), intersection.getPoint2().getX());
        var maxX = Math.max(intersection.getPoint1().getX(), intersection.getPoint2().getX());

        var minY = Math.min(intersection.getPoint1().getY(), intersection.getPoint2().getY());
        var maxY = Math.max(intersection.getPoint1().getY(), intersection.getPoint2().getY());

        return new Polygon(new Point[] {
                new Point(minX, minY),
                new Point(maxX, minY),
                new Point(maxX, maxY),
                new Point(minX, maxY)
            });
    }
}

