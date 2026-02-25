public class Segment {
    public Point p1, p2;

    public float length()
    { return (float)Math.sqrt(Math.pow(Math.abs(p1.x - p2.x), 2) + Math.pow(Math.abs(p1.y - p2.y), 2)); }
}
