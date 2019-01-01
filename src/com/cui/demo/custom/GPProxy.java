package com.cui.demo.custom;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.Tool;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class GPProxy {

    public static String ln = "\r\n";

    public static  Object newProxyInstance(GPClassLoader classLoader,Class<?>[] interfaces,GPInvocationHandler h) {

        try {


            //1.动态生成源代码 .java文件
            String src = generateSrc(interfaces);
            //2. java文件输出到磁盘
            String filePath = GPProxy.class.getResource("").getPath();
            File file = new File(filePath + "$Proxy.java");
            FileWriter fw = new FileWriter(file);
            fw.write(src);
            fw.flush();
            fw.close();

            //3. 把生成的java文件编译成class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = manager.getJavaFileObjects(file);

            JavaCompiler.CompilationTask task = compiler.getTask(null,manager,null,null,null,iterable);
            task.call();
            manager.close();

            //4. 把编译生成的class文件加载到jvm中
            Class proxyClass = classLoader.findClass("$Proxy");
            Constructor c = proxyClass.getConstructor(GPInvocationHandler.class);

            file.delete();

            //5、返回字节码重组以后的新的代理对象
            return c.newInstance(h);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {

        StringBuffer sb = new StringBuffer();
        sb.append("package com.cui.demo.custom;" + ln);
        sb.append("import com.cui.demo.staticproxy.Person;" + ln);
        sb.append("import java.lang.reflect.Method;");
        sb.append("public class $Proxy implements " + interfaces[0].getName() + "{" + ln);
        sb.append("     GPInvocationHandler h;" + ln);
        sb.append("     public $Proxy(GPInvocationHandler h){" + ln);
        sb.append("         this.h = h;" + ln);
        sb.append("     }" + ln);

        for (Method m : interfaces[0].getMethods()) {
            sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(){" +ln);
            sb.append("try{");
            sb.append("  Method m = " + interfaces[0].getName() + ".class.getMethod(\""+  m.getName() +"\",new Class[]{});" + ln);
            sb.append("  this.h.invoke(this,m,null);" + ln);
            sb.append("}catch(Throwable e){" + ln);
            sb.append("     e.printStackTrace();" + ln);
            sb.append("}");
            sb.append("}"+ln);
        }

        sb.append("}" + ln);

        return sb.toString();
    }

}
