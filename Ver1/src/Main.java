// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Point p = new Point(10, 20);

        System.out.println(p);
        System.out.println(p.toSvg());

        Segment[] segs = new Segment[]{
                new Segment(new Point(0, 0), new Point(1, 1)),
                new Segment(new Point(0, -1), new Point(0, 1)),
                new Segment(new Point(1, 0), new Point(0, 1)),
                new Segment(new Point(0, 0), new Point(0, 0)),
                new Segment(new Point(15, 0), new Point(0, -3))
        };

        Polygon pol;
        {
            Point[] po = new Point[segs.length * 2];
            for (int i = 0; i < segs.length; i++)
            { po[i*2] = segs[i].getPoint1(); po[(i*2)+1] = segs[i].getPoint2(); }
            pol = new Polygon(po);
        }
        System.out.println(pol);
        System.out.println(pol.toSvg());

        System.out.println(Segment.findLongest(segs).toString());

        Style s = new Style("Gold", "Black", 15);

        System.out.println(s.toSvg(true));
        System.out.println(s.toSvg(false));

        System.out.println(Segment.findLongest(segs).Perpendicular());
        System.out.println(Polygon.Square(Segment.findLongest(segs)).toSvg());
    }
}