public class Point {
    private float x, y;

    public Point() { x = 0; y = 0; }
    public Point(float x, float y)
    { this.x = x; this.y = y; }
    public Point(Point p) {x = p.x; y = p.y; }

    public float getX() {
        return x;
    }
    public Point setX(float x) {
        this.x = x; return this;
    }

    public float getY() {
        return y;
    }
    public Point setY(float y) {
        this.y = y;
        return this;
    }
    public  Point set(float x, float y) { this.x = x; this.y = y; return this; }

    @Override
    public String toString()
    { return toString(false); }
    public String toString(boolean isShort)
    { return (isShort ? "{x: " : "Point {x: ") + x + ", " + y + "}"; }

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
