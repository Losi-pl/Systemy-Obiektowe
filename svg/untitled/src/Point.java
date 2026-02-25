public class Point {
    public float x, y;

    public Point() { }
    public Point(float x, float y)
    { this.x = x; this.y = y; }

    @Override
    public String toString()
    { return "Point {x: " + x + ", " + y + "}"; }

    public String toSvg()
    { return "<circle r=\"10\" cx=\"" + x + "\" cy=\"" + y + "\" fill=\"black\"/>"; }

    public Point translate(float dx, float dy)
    {
        x += dx;
        y += dy;
        return this;
    }
    public Point translated(float dx, float dy)
    {
        Point p = new Point();
        p.x = x + dx;
        p.y = y + dy;
        return p;
    }
}
