public class Segment {
    private Point p1, p2;

    public Segment() { p1 = new Point(); p2 = new Point(); }
    public Segment(Point p1, Point p2)
    { this.p1 = new Point(p1); this.p2 = new Point(p2); }
    public  Segment(float p1_x, float p1_y, float p2_x, float p2_y)
    { p1 = new Point(p1_x, p1_y); p2 = new Point(p2_x, p2_y); }

    public float length()
    { return (float)Math.sqrt(Math.pow(Math.abs(p1.getX() - p2.getX()), 2) + Math.pow(Math.abs(p1.getY() - p2.getY()), 2)); }

    public Point getPoint1()
    { return new Point(p1); }
    public Point getPoint2()
    { return new Point(p2); }
    public Segment setPoint1(Point p) {
        p1 = new Point(p);
        return this;
    }
    public Segment setPoint2(Point p) {
        p2 = new Point(p);
        return this;
    }

    public static Segment findLongest(Segment[] coll)
    {
        int lon_ind = 0;
        float len = coll[0].length();
        for(int i = 0; i < coll.length; i++)
            if(coll[i].length() > len)
            {
                lon_ind = i;
                len = coll[i].length();
            }
        return coll[lon_ind];
    }
    public Segment Perpendicular() { return Perpendicular(false); }
    public Segment Perpendicular(boolean horizontal)
    {
        Point Avg = new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
        if(!horizontal)
            return new Segment(new Point(p1.getX(), Avg.getY() + (p1.getY() - Avg.getY())), new Point(p2.getX(), Avg.getY() + (p2.getY() - Avg.getY())));
        else
            return new Segment(new Point(Avg.getX() + (p1.getX() - Avg.getX()), p1.getY()), new Point(Avg.getX() + (p2.getX() - Avg.getX()), p2.getY()));
    }

    @Override
    public String toString() {
        return "Segment{ p1: " + p1.toString(true) + ", p2: " + p2.toString(true) + " }";
    }
}
