// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Point p = new Point(10, 20);

        System.out.println(p.toString());
        System.out.println(p.toSvg());

        Segment[] segs = new Segment[]{
                new Segment(new Point(0, 0), new Point(1, 1)),
                new Segment(new Point(0, -1), new Point(0, 1)),
                new Segment(new Point(1, 0), new Point(0, 1)),
                new Segment(new Point(0, 0), new Point(0, 0)),
                new Segment(new Point(15, 0), new Point(0, -3))
        };

        System.out.print(Segment.findLongest(segs).toString());
    }
}