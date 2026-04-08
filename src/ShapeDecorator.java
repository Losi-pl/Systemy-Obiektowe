public class ShapeDecorator implements Shape
{
    private Shape shape;

    public ShapeDecorator(Shape shape)
    {
        this.shape = shape;
    }

    @Override
    public BoundingBox boundingBox() {
        return shape.boundingBox();
    }

    @Override
    public String toSvg(String attr) {
        return shape.toSvg(attr);
    }
}
