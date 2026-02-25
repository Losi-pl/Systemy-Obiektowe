public class Point {
    public float x, y;

    public String toString()
    { return "Point {x: " + x + ", " + y + "}"; }

    public String toSvg()
    {
        return "<circle r=\"10\" cx=\"" + x + "\" cy=\"" + y + "\" fill=\"black\"/>";
    }
}
