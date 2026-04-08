import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Polygon triangle = new Polygon(new Vec2[]{
                new Vec2(0, 0),
                new Vec2(300, 0),
                new Vec2(150, 250)
        });

        Polygon rectangle = new SolidFilledPolygon(new Vec2[]{
                new Vec2(350, 0),
                new Vec2(750, 0),
                new Vec2(750, 200),
                new Vec2(350, 200)
        }, "Gold");

        Polygon pentagon = new Polygon(new Vec2[]{
                new Vec2(0, 260),
                new Vec2(100, 460),
                new Vec2(300, 560),
                new Vec2(500, 460),
                new Vec2(600, 260)
        });

        Ellipse ellipse = new Ellipse(new Vec2(500, 700), 400, 100);

        SvgScene scene = new SvgScene();
        scene.addShape(new SolidFillShapeDecorator(triangle, "Pink"));
        scene.addShape(TransformationDecorator.Builder.Setup(rectangle)
                .Translate(new Vec2(0, 50)).Finish());
        scene.addShape(new StrokeShapeDecorator(pentagon, "Cyan", 3));
        scene.addShape(new SolidFillShapeDecorator(ellipse, "Green"));
        scene.save("result.svg");
    }
}
