public class Segment {
    public Point p1, p2;

    public float length()
    { return (float)Math.sqrt(Math.pow(Math.abs(p1.x - p2.x), 2) + Math.pow(Math.abs(p1.y - p2.y), 2)); }

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
}
