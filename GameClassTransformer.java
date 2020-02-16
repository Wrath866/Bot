import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

public class GameClassTransformer extends ClassTransformer {
    @Override
    public void transform(ClassNode node) {
        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, "simpleMethod", "()V", null, null);
        methodNode.instructions.add(new InsnNode(Opcodes.RETURN));

        int size = methodNode.instructions.size();
        methodNode.visitMaxs(size, size);
        methodNode.visitEnd();
        node.methods.add(methodNode);
    }
}
