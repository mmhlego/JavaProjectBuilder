import java.io.*;

public class ProjectCreator {
    String path, artifactid, group, version, editor;

    ProjectCreator(String p, String a, String g, String v, String e) {
        path = p + "\\" + a;
        artifactid = a;
        group = g;
        version = v;
        editor = e;

        if (new File(path).mkdir()) {
            System.out.println("Directory created successfully.");
        } else {
            System.out.println("Cannot create directory. pleease try again.");
            new Main();
            return;
        }

        createMain();
        createTarget();
        createClasspath(true);
        createProject();
        createPom();
        openProject();
        /*try {
            Thread.sleep(10000);
        } catch (Exception er) {
            System.out.println(er.toString());
        }
        createClasspath(false);*/
    }

    public void createClasspath(boolean create) {
        File classpath = new File(path + "\\.classpath");
        try {
            if (create) {
                classpath.createNewFile();
            }
            PrintWriter writer = new PrintWriter(classpath);
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<classpath>");

            /*writer.println("\t<classpathentry kind=\"src\" output=\"target/test-classes\" path=\"src/test/java\">");
            writer.println("\t\t<attributes>");
            writer.println("\t\t\t<attribute name=\"optional\" value=\"true\"/>");
            writer.println("\t\t\t<attribute name=\"maven.pomderived\" value=\"true\"/>");
            writer.println("\t\t\t<attribute name=\"test\" value=\"true\"/>");
            writer.println("\t\t</attributes>");
            writer.println("\t</classpathentry>");*/

            writer.println(
                    "\t<classpathentry kind=\"con\" path=\"org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.8\">");
            writer.println("\t\t<attributes>");
            writer.println("\t\t\t<attribute name=\"maven.pomderived\" value=\"true\"/>");
            writer.println("\t\t</attributes>");
            writer.println("\t</classpathentry>");

            writer.println("\t<classpathentry kind=\"con\" path=\"org.eclipse.m2e.MAVEN2_CLASSPATH_CONTAINER\">");
            writer.println("\t\t<attributes>");
            writer.println("\t\t\t<attribute name=\"maven.pomderived\" value=\"true\"/>");
            writer.println("\t\t</attributes>");
            writer.println("\t</classpathentry>");

            /*writer.println("\t<classpathentry kind=\"src\" path=\"target/generated-sources/annotations\">");
            writer.println("\t\t<attributes>");
            writer.println("\t\t\t<attribute name=\"optional\" value=\"true\"/>");
            writer.println("\t\t\t<attribute name=\"maven.pomderived\" value=\"true\"/>");
            writer.println("\t\t\t<attribute name=\"ignore_optional_problems\" value=\"true\"/>");
            writer.println("\t\t\t<attribute name=\"m2e-apt\" value=\"true\"/>");
            writer.println("\t\t</attributes>");
            writer.println("\t</classpathentry>");*/

            writer.println("\t<classpathentry kind=\"src\" path=\"target/generated-sources/annotations\">");
            writer.println("\t\t<attributes>");
            writer.println("\t\t\t<attribute name=\"optional\" value=\"true\"/>");
            writer.println("\t\t\t<attribute name=\"maven.pomderived\" value=\"true\"/>");
            writer.println("\t\t\t<attribute name=\"ignore_optional_problems\" value=\"true\"/>");
            writer.println("\t\t\t<attribute name=\"m2e-apt\" value=\"true\"/>");
            writer.println("\t\t</attributes>");
            writer.println("\t</classpathentry>");

            writer.println("\t<classpathentry kind=\"src\" output=\"target/classes\" path=\"src/main/java\">");
            writer.println("\t\t<attributes>");
            writer.println("\t\t\t<attribute name=\"optional\" value=\"true\"/>");
            writer.println("\t\t\t<attribute name=\"maven.pomderived\" value=\"true\"/>");
            writer.println("\t\t</attributes>");
            writer.println("\t</classpathentry>");

            writer.println("\t<classpathentry kind=\"output\" path=\"target/classes\"/>");
            writer.println("</classpath>");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void createProject() {
        File project = new File(path + "\\.project");
        try {
            project.createNewFile();
            PrintWriter writer = new PrintWriter(project);
            writer.println("<?xml version=\"" + version + "\" encoding=\"UTF-8\"?>");
            writer.println("<projectDescription>");
            writer.println("\t<name>" + artifactid + "</name>");
            writer.println("\t<comment></comment>");
            writer.println("\t<projects>");
            writer.println("\t</projects>");
            writer.println("\t<buildSpec>");
            writer.println("\t\t<buildCommand>");
            writer.println("\t\t\t<name>org.eclipse.jdt.core.javabuilder</name>");
            writer.println("\t\t\t<arguments>");
            writer.println("\t\t\t</arguments>");
            writer.println("\t\t</buildCommand>");
            writer.println("\t\t<buildCommand>");
            writer.println("\t\t\t<name>org.eclipse.m2e.core.maven2Builder</name>");
            writer.println("\t\t\t<arguments>");
            writer.println("\t\t\t</arguments>");
            writer.println("\t\t</buildCommand>");
            writer.println("\t</buildSpec>");
            writer.println("\t<natures>");
            writer.println("\t\t<nature>org.eclipse.jdt.core.javanature</nature>");
            writer.println("\t\t<nature>org.eclipse.m2e.core.maven2Nature</nature>");
            writer.println("\t</natures>");
            writer.println("\t<filteredResources>");
            writer.println("\t\t<filter>");
            writer.println("\t\t\t<id>1610634213378</id>");
            writer.println("\t\t\t<name></name>");
            writer.println("\t\t\t<type>30</type>");
            writer.println("\t\t\t<matcher>");
            writer.println("\t\t\t\t<id>org.eclipse.core.resources.regexFilterMatcher</id>");
            writer.println("\t\t\t\t<arguments>node_modules|.git|__CREATED_BY_JAVA_LANGUAGE_SERVER__</arguments>");
            writer.println("\t\t\t</matcher>");
            writer.println("\t\t</filter>");
            writer.println("\t</filteredResources>");
            writer.println("</projectDescription>");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void createPom() {
        File pom = new File(path + "\\pom.xml");
        try {
            PrintWriter writer = new PrintWriter(pom);
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println(
                    "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">");
            writer.println("\t<modelVersion>4.0.0</modelVersion>");
            writer.println("\t<groupId>" + group + "</groupId>");
            writer.println("\t<artifactId>" + artifactid + "</artifactId>");
            writer.println("\t<version>" + version + "</version>");
            writer.println("\t<name>" + artifactid + "</name>");
            writer.println("\t<url>http://www.example.com</url>");

            writer.println("\t<properties>");
            writer.println("\t\t<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>");
            writer.println("\t\t<maven.compiler.source>1.8</maven.compiler.source>");
            writer.println("\t\t<maven.compiler.target>1.8</maven.compiler.target>");
            writer.println("\t</properties>");

            writer.println("\t<dependencies>");
            writer.println("\t\t<dependency>");
            writer.println("\t\t\t<groupId>junit</groupId>");
            writer.println("\t\t\t<artifactId>junit</artifactId>");
            writer.println("\t\t\t<version>4.11</version>");
            writer.println("\t\t\t<scope>test</scope>");
            writer.println("\t\t</dependency>");
            writer.println("\t</dependencies>");

            writer.println("\t<build>");
            writer.println("\t\t<pluginManagement>");
            writer.println("\t\t\t<plugins>");
            writer.println("\t\t\t\t<plugin>");
            writer.println("\t\t\t\t\t<artifactId>maven-clean-plugin</artifactId>");
            writer.println("\t\t\t\t\t<version>3.1.0</version>");
            writer.println("\t\t\t\t</plugin>");
            writer.println("\t\t\t\t<plugin>");
            writer.println("\t\t\t\t\t<artifactId>maven-resources-plugin</artifactId>");
            writer.println("\t\t\t\t\t<version>3.0.2</version>");
            writer.println("\t\t\t\t</plugin>");
            writer.println("\t\t\t\t<plugin>");
            writer.println("\t\t\t\t\t<artifactId>maven-compiler-plugin</artifactId>");
            writer.println("\t\t\t\t\t<version>3.8.0</version>");
            writer.println("\t\t\t\t</plugin>");
            writer.println("\t\t\t\t<plugin>");
            writer.println("\t\t\t\t\t<artifactId>maven-surefire-plugin</artifactId>");
            writer.println("\t\t\t\t\t<version>2.22.1</version>");
            writer.println("\t\t\t\t</plugin>");
            writer.println("\t\t\t\t<plugin>");
            writer.println("\t\t\t\t\t<artifactId>maven-jar-plugin</artifactId>");
            writer.println("\t\t\t\t\t<version>3.0.2</version>");
            writer.println("\t\t\t\t</plugin>");
            writer.println("\t\t\t\t<plugin>");
            writer.println("\t\t\t\t\t<artifactId>maven-install-plugin</artifactId>");
            writer.println("\t\t\t\t\t<version>2.5.2</version>");
            writer.println("\t\t\t\t</plugin>");
            writer.println("\t\t\t\t<plugin>");
            writer.println("\t\t\t\t\t<artifactId>maven-deploy-plugin</artifactId>");
            writer.println("\t\t\t\t\t<version>2.8.2</version>");
            writer.println("\t\t\t\t</plugin>");
            writer.println("\t\t\t\t<plugin>");
            writer.println("\t\t\t\t\t<artifactId>maven-site-plugin</artifactId>");
            writer.println("\t\t\t\t\t<version>3.7.1</version>");
            writer.println("\t\t\t\t</plugin>");
            writer.println("\t\t\t\t<plugin>");
            writer.println("\t\t\t\t\t<artifactId>maven-project-info-reports-plugin</artifactId>");
            writer.println("\t\t\t\t\t<version>3.0.0</version>");
            writer.println("\t\t\t\t</plugin>");
            writer.println("\t\t\t</plugins>");
            writer.println("\t\t</pluginManagement>");
            writer.println("\t</build>");
            writer.println("</project>");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void createMain() {
        File main = new File(path + "\\src\\main\\java\\Main.java");
        try {
            new File(path + "\\src").mkdir();
            new File(path + "\\src\\main").mkdir();
            new File(path + "\\src\\main\\java").mkdir();
            main.createNewFile();
            PrintWriter writer = new PrintWriter(main);
            writer.println("public class Main{");
            writer.println("\tpublic static void main(String[] args){");
            writer.println("\t\t// program");
            writer.println("\t}");
            writer.println("}");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void createTarget() {
        try {
            new File(path + "\\target\\classes").mkdir();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void openProject() {
        try {
            String program = "";
            if (editor.equals("atom")) {
                program = "atom";
            } else if (editor.equals("vs code")) {
                program = "code";
            }

            Runtime.getRuntime().exec("cmd /c " + program + " .", null, new File(path));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
