package jdbc.imp.test.onlineTester;

import org.junit.Assert;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {

    private static Class<?> implementation;
    private static String resourceDir;

    private static String showError(Throwable e)
            throws IOException {
        e.printStackTrace();
        StringBuffer buffer = new StringBuffer();
        buffer.append("\t\t\tError: " + e + " " + e.getMessage()).append("\n");
        for (StackTraceElement trace : e.getStackTrace()) {
            buffer.append("\n" + trace.getClassName() + "."
                    + trace.getMethodName() + "(): Line "
                    + trace.getLineNumber());
        }
        return buffer.toString().replaceAll("\\n", "\n\t\t\t\t");
    }

    public static Object getImplementationInstance(){
        try {
            for(Constructor<?> constructor : implementation.getDeclaredConstructors()){
                if(constructor.getParameterTypes().length == 0){
                    constructor.setAccessible(true);
                    return constructor.newInstance((java.lang.Object[])null);
                }
            }
        } catch (Throwable e) {
        }
        return null;
    }

    public static Object getImplementationInstance(Object arg){
        try {
            for(Constructor<?> constructor : implementation.getDeclaredConstructors()){
                if(constructor.getParameterTypes().length == 1){
                    constructor.setAccessible(true);
                    return constructor.newInstance(arg);
                }
            }
        } catch (Throwable e) {
        }
        return null;
    }

    public static String getResourceDir(){
        return resourceDir;
    }

    public static String[] run(String resourceDir, String testPackage, List<String> classes) throws Throwable{
        TestRunner.resourceDir = resourceDir;
        StringBuffer log = new StringBuffer();
        List<Class<?>> tests = new LinkedList<Class<?>>();
        try {
            for(String clazz : classes){
                Class<?> impl = Class.forName(clazz);
                if(impl.getName().startsWith(testPackage))
                    tests.add(impl);
            }
        } catch (Throwable e) {
            log.append("{{{Failed to create the test classes! Check if we compile the correct project.").append("\n");
            log.append("Please review the 'Sources' list above for the detected source-code").append("\n");
            log.append("\t").append(showError(e)).append("\n}}}");
            return new String[]{ "Compile", log.toString(),  "" };
        }
        log.append("UnitTests: " + (tests.isEmpty() ? "none!" : Arrays.toString(tests.toArray()))).append("\n");

        log.append("Classes: " + (classes.isEmpty() ? "none!" : Arrays.toString(classes.toArray()))).append("\n");
        int failed = 0;
        int total = 0;
        Set<String> implementations = new HashSet<String>();
        for(Class<?> testClass : tests){
            Class<?> specs = null;
            try {
                Method method = null;
                try{
                    method = testClass.getMethod("getSpecifications");
                }catch(Throwable e){
                    continue;
                }
                log.append("\n\nRunning UnitTest: <<<" + testClass.getName()).append(">>>\n");
                specs = (Class<?>)method.invoke(null);
                log.append("\tInterface: " + specs.getName()).append("\n");
            } catch (Throwable e) {
                log.append("\tFailed to find the specification interface!").append("\n");
                log.append("\t\t").append(showError(e)).append("\n");
                return new String[] { "NoSpec", log.toString(),  "" };
            }
            List<Class<?>> impls = new LinkedList<Class<?>>();
            try {
                for(String clazz : classes){
                    Class<?> impl = Class.forName(clazz);
                    for(Class<?> i : impl.getInterfaces()){
                        if(i.equals(specs)){
                            impls.add(impl);
                            break;
                        }
                    }
                }
                log.append("\tImplementations: " + (impls.isEmpty() ? "{{{none!}}}" : Arrays.toString(impls.toArray()))).append("\n");
            } catch (Throwable e) {
                log.append("\t{{{Failed to create the implementation class!").append("\n");
                log.append("\t\t").append(showError(e)).append("}}}\n");
                return new String[] { "NoImpl", log.toString(),  "" };
            }

            for(Class<?> impl : impls){
                log.append("\tTesting Implementation: " + impl.getName()).append("\n");
                implementations.add(impl.getName());
                implementation = impl;
                JUnitCore junit = new JUnitCore();
                Result result = junit.run(testClass);
                log.append("\t\t{{{Failed: " + result.getFailureCount()).append("\n");
                int i=0;
                for(Failure f : result.getFailures()){
                    log.append("\t\t\tFailure " + ++i + ":\n");
                    log.append("\t\t\t" + f.getTestHeader() + "[" + f.getMessage() + "]").append("\n");
                    log.append("\t\t\t" + f.getTrace().replaceAll("\\r\n|\\n\\r|\\r|\\n", "\n\t\t\t"));
                    log.append("\n");
                }
                log.append("}}}");
                failed += result.getFailureCount();
                total += result.getRunCount();
//				log.append("\t\tIgnore: " + result.getIgnoreCount()).append("\n");
                log.append("\t\t[[[Total : " + result.getRunCount()).append("]]]\n");
            }
        }
        String impls = "";
        for(String impl : implementations)
            impls += impl + ";";
        return new String[] { (total-failed) + "/" + total, log.toString(),  impls};
    }

    public static void fail(String message, Throwable throwable) {
        try {
            StringBuffer log = new StringBuffer();
            if(message!=null)
                log.append(message).append("\n");
            if(throwable!=null){
                Throwable cause = throwable.getCause();
                if(cause!=null)
                    log.append(showError(cause));
                log.append(showError(throwable));
            }
            Assert.fail(log.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
