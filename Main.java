import org.objectweb.asm.tree.ClassNode;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.jar.JarFile;

public class Main {
    public static void main(String[] args) {
        Applet applet;
        File file = new File("C:\\Users\\owner\\Desktop\\2006rebotted.jar");

        ClassLoader cl;

        {
            try {
                cl = new URLClassLoader(new URL[] { file.toURI().toURL() });
                Class<?> clazz;
                {
                    try {
                        clazz = cl.loadClass("Game");
                        try {
                            Constructor<?> init = clazz.getDeclaredConstructor();
                            init.setAccessible(true);
                            try {
                                applet = (Applet) init.newInstance();
                                Field field;

                                {
                                    try {
                                        field = clazz.getDeclaredField("nodeID");
                                        field.setAccessible(true);
                                        try {
                                            field.setInt(applet, 10);
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        }
                                    } catch (NoSuchFieldException e) {
                                        e.printStackTrace();
                                    }
                                }

                                Field portOff;

                                {
                                    try {
                                        portOff = clazz.getDeclaredField("portOff");
                                        portOff.setAccessible(true);
                                        try {
                                            portOff.setInt(applet, 0);
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        }
                                    } catch (NoSuchFieldException e) {
                                        e.printStackTrace();
                                    }
                                }

                                Method setHighMem;

                                {
                                    try {
                                        setHighMem = clazz.getDeclaredMethod("setHighMem");
                                        setHighMem.setAccessible(true);
                                        try {
                                            setHighMem.invoke(applet);
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        } catch (InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                    } catch (NoSuchMethodException e) {
                                        e.printStackTrace();
                                    }
                                }

                                Field isMembers;

                                {
                                    try {
                                        isMembers = clazz.getDeclaredField("isMembers");
                                        isMembers.setAccessible(true);
                                        try {
                                            isMembers.setBoolean(applet,true);
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        }
                                    } catch (NoSuchFieldException e) {
                                        e.printStackTrace();
                                    }
                                }

                                Class<?> signLink;

                                {
                                    try {
                                        signLink = cl.loadClass("Signlink");
                                        try {
                                            Field storeid = signLink.getDeclaredField("storeid");
                                            storeid.setAccessible(true);
                                            try {
                                                storeid.setInt(signLink, 32);

                                                Method startpriv;

                                                {
                                                    try {
                                                        startpriv = signLink.getDeclaredMethod("startpriv", InetAddress.class);
                                                        startpriv.setAccessible(true);
                                                        try {
                                                            startpriv.invoke(signLink, InetAddress.getLocalHost());
                                                        } catch (IllegalAccessException e) {
                                                            e.printStackTrace();
                                                        } catch (InvocationTargetException e) {
                                                            e.printStackTrace();
                                                        } catch (UnknownHostException e) {
                                                            e.printStackTrace();
                                                        }
                                                    } catch (NoSuchMethodException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            } catch (IllegalAccessException e) {
                                                e.printStackTrace();
                                            }
                                        } catch (NoSuchFieldException e) {
                                            e.printStackTrace();
                                        }
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }

                                Method initClientFrame;

                                {
                                    try {
                                        initClientFrame = clazz.getSuperclass().getDeclaredMethod("initClientFrame", int.class, int.class);
                                        initClientFrame.setAccessible(true);
                                        int width = 503;
                                        int height = 765;
                                        JFrame frame = new JFrame("Our bot!");
                                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        frame.setMinimumSize(new Dimension(765 + 8, 503 + 28));
                                        frame.setLocationRelativeTo(null);
                                        frame.add(applet);
                                        frame.setVisible(true);
                                        try {
                                            initClientFrame.invoke(applet, width, height);
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        } catch (InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                    } catch (NoSuchMethodException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        JarHelper helper = null;
        try {
            helper = new JarHelper(new JarFile("C:\\Users\\owner\\Desktop\\2006rebotted.jar"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ClassTransformer gct = new GameClassTransformer();
        ClassTransformer ract = new RSAppletClassTransformer();

        ClassNode gameNode = helper.getClasses().get("Game.class");
        //gct.transform(gameNode);

        ClassNode rsApplet = helper.getClasses().get("RSApplet.class");
        //ract.transform(rsApplet);

        //helper.saveJar();
    }
}
