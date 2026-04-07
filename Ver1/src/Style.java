public class Style {
    public String fillColor;
    public String strokeColor;
    public double strokeWidth;

    public Style(String fillColor, String strokeColor, double strokeWidth)
    {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }
    public Style(Style s)
    {
        this(s.fillColor, s.strokeColor, s.strokeWidth);
    }

    public String toSvg() { return toSvg(true); }
    public String toSvg(boolean inStyle)
    {
        if(inStyle)
            return "style=\"" + "fill: " + fillColor.toLowerCase() + "; stroke: " + strokeColor.toLowerCase() + "; stroke-width: " + strokeWidth + ";\"";
        else
            return "fill=\"" + fillColor + "\" stroke=\"" + strokeColor + "\" stroke-width=\"" + strokeWidth + "px\"";
    }
}
