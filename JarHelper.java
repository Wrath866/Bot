import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class JarHelper {
    private Manifest manifest;
    private Map<String, ClassNode> classes = new HashMap<>();

    public JarHelper(JarFile jarFile) {
        parseJar(jarFile);
    }

    public Map<String, ClassNode> getClasses() {
        return classes;
    }

    public void parseJar(JarFile file) {
        Objects.requireNonNull(file, "The jar file must not be null.");
        try {
            Enumeration<JarEntry> entries = file.entries();
            try {
                manifest = file.getManifest();
            } catch (IOException e) {
                e.printStackTrace();
            }

            do {
                JarEntry entry = entries.nextElement();

                if (!entry.getName().contains(".class")) continue;

                ClassReader classReader = new ClassReader(file.getInputStream(entry));
                ClassNode classNode = new ClassNode();
                classReader.accept(classNode, ClassReader.SKIP_DEBUG);
                classes.put(entry.getName(), classNode);

            } while (entries.hasMoreElements());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveJar() {
        try (JarOutputStream jos = new JarOutputStream(new FileOutputStream("newjar.jar"), manifest)) {

            Collection<ClassNode> classNodes = classes.values();

            List<String> names = new ArrayList<>();

            for (ClassNode node : classNodes) {
                if (names.contains(node.name)) continue;

                JarEntry newEntry = new JarEntry(node.name + ".class");
                jos.putNextEntry(newEntry);

                ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                node.accept(writer);
                jos.write(writer.toByteArray());

                names.add(node.name);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
