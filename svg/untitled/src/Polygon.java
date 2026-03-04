public class Polygon {
    private Point[] ps;

    public Polygon()
    { ps = new Point[0]; }

    public Polygon(Point[] points) {
        Point[] tmp = new Point[points.length];
        for (int i = 0; i < points.length; ++i)
            tmp[i] = new Point(points[i]);
        ps = tmp;
    }

    public Point getPoint(int index)
    { return new Point(ps[index]); }

    @Override
    public String toString() {
        String tmp = "Polygon[ ";
        for (int i = 0; i < ps.length; ++i)
            tmp += ps[i].toString(true) + (i < ps.length - 1 ? ", " : " ]");
        return tmp;
    }

    public String toSvg()
    {
        //<polygon points="220,10 300,210 170,250 123,234" style="fill:white;stroke:black;stroke-width:1" />
        String tmp = "<polygon points=\"";
        for (int i = 0; i < ps.length; ++i)
            tmp += (ps[i].getX() + "," + ps[i].getY()) + (i < ps.length - 1 ? " " : "\" style=\"fill:white;stroke:black;stroke-width:1\" />");
        return tmp;
    }
}
