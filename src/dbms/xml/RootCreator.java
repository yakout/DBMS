package dbms.xml;

import java.io.PrintWriter;

class RootCreator {
	protected static void createRoot(String table, PrintWriter out) {
//		out.println("<!DOCTYPE " + table + "[");
	}

	protected static void terminateFile(PrintWriter out) {
//		out.println("]>");
		out.close();
	}
}
