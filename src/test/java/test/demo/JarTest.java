package test.demo;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarTest {
	public static void main(String[] args) {
		try {
			new JarTest().searchClass();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searchClass() throws IOException, ClassNotFoundException {
        String basePack = "com.jfinal";
        //通过当前线程得到类加载器从而得到URL的枚举
        Enumeration<URL> urlEnumeration = Thread.currentThread().getContextClassLoader().getResources(basePack.replace(".", "/"));
        while (urlEnumeration.hasMoreElements()) {
            URL url = urlEnumeration.nextElement();//得到的结果大概是：jar:file:/C:/Users/ibm/.m2/repository/junit/junit/4.12/junit-4.12.jar!/org/junit
            String protocol = url.getProtocol();//大概是jar
            System.out.println(url.getPath());
//            if ("jar".equalsIgnoreCase(protocol)) {
//                //转换为JarURLConnection
//                JarURLConnection connection = (JarURLConnection) url.openConnection();
//                if (connection != null) {
//                    JarFile jarFile = connection.getJarFile();
//                    if (jarFile != null) {
//                        //得到该jar文件下面的类实体
//                        Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
//                        while (jarEntryEnumeration.hasMoreElements()) {
//                            /*entry的结果大概是这样：
//                                    org/
//                                    org/junit/
//                                    org/junit/rules/
//                                    org/junit/runners/*/
//                            JarEntry entry = jarEntryEnumeration.nextElement();
//                            String jarEntryName = entry.getName();
//                            //这里我们需要过滤不是class文件和不在basePack包名下的类
//                            if (jarEntryName.contains(".class") && jarEntryName.replaceAll("/",".").startsWith(basePack)) {
//                                String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
//                                Class cls = Class.forName(className);
//                                System.out.println(cls);
//                            }
//                        }
//                    }
//                }
//
//            }
        }
    }

}
