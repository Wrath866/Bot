import org.objectweb.asm.tree.ClassNode;

public abstract class ClassTransformer {

    public abstract void transform(ClassNode node);
}