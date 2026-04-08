import com.google.common.base.Strings;
import java.util.*;

public class TransformationDecorator extends ShapeDecorator{
    String transformers;

    private TransformationDecorator(Shape shape, String transformers) {
        super(shape);
        this.transformers = transformers;
    }

    @Override
    public String toSvg(String attr) {
        return super.toSvg(String.format(Locale.ENGLISH, "transform=\"%s\"", transformers) +
            (Strings.isNullOrEmpty(attr) ? "" : ' ' + attr));
    }

    public static class Builder
    {
        Shape shape;
        StringBuilder transformers;
        boolean noTrans;

        private Builder(Shape shape) { this.shape = shape; transformers = new StringBuilder(); noTrans = true; }

        public static Builder Setup(Shape shape)
        {
            return new Builder(shape);
        }

        public Builder Translate(Vec2 translation)
        {
            if(noTrans)
                noTrans = false;
            else
                transformers.append(' ');

            transformers.append(String.format(Locale.ENGLISH, "translate(%f, %f)",
                    translation.x(), translation.y()));
            return this;
        }

        public Builder Rotate(float angle, Vec2 center)
        {
            if(noTrans)
                noTrans = false;
            else
                transformers.append(' ');

            transformers.append(String.format(Locale.ENGLISH, "rotate(%f, %f, %f)",
                    angle, center.x(), center.y()));
            return this;
        }

        public Builder scale(Vec2 scaleFactor)
        {
            if(noTrans)
                noTrans = false;
            else
                transformers.append(' ');

            transformers.append(String.format(Locale.ENGLISH, "scale(%f, %f)",
                    scaleFactor.x(), scaleFactor.y()));
            return this;
        }

        public TransformationDecorator Finish()
        {
            return new TransformationDecorator(shape, transformers.toString());
        }
    }
}
