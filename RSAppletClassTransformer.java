import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.Iterator;
import java.util.Objects;

public class RSAppletClassTransformer extends ClassTransformer {
    @Override
    public void transform(ClassNode node) {
        node.interfaces.add("RSAppletInterface");

        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, "getGraphicsObject", "()Ljava/awt/Graphics;", null, null);
        methodNode.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
        methodNode.instructions.add(new FieldInsnNode(Opcodes.GETFIELD, "RSApplet", "graphics", "Ljava/awt/Graphics;"));
        methodNode.instructions.add(new InsnNode(Opcodes.ARETURN));
        int size = methodNode.instructions.size();
        methodNode.visitMaxs(size, size);
        methodNode.visitEnd();
        node.methods.add(methodNode);


        Objects.requireNonNull(node, "Node must not be null!");
        node.superName = "BotApplet";
        Iterator<MethodNode> mi = node.methods.iterator();
        // Loop through every method node
        while (mi.hasNext()) {
            MethodNode mNode = mi.next();
            if (mNode.name.equals("<init>")) {
                Iterator<AbstractInsnNode> instructs = mNode.instructions.iterator();
                while (instructs.hasNext()) {
                    AbstractInsnNode ain = (AbstractInsnNode) instructs.next();
                    if (ain.getOpcode() == Opcodes.INVOKESPECIAL) {
                        //cast to MethodInsnNode so we can change the "owner" variable to our own
                        MethodInsnNode min = (MethodInsnNode) ain;
                        min.owner = "BotApplet";
                    }
                }
            }
        }
    }
}
