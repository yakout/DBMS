package jdbc.imp.driver;

import jdbc.imp.connection.ConnectionAdapter;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DriverAdapter extends DBDriver {
    private static final String URL_REGEX = "jdbc:(\\w+)db://localhost";
    private static final Pattern urlPattern = Pattern.compile(URL_REGEX);
    private final String INVALID_URL = "Invalid url Format";

    static {
        try {
            DriverManager.registerDriver(new DriverAdapter());
        } catch (SQLException e) {
        }
    }
    public DriverAdapter() {
    }

    @Override
    public boolean acceptsURL(String arg0) throws SQLException {
        return true;
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        url = parseUrl(url);
        File path1 = (File) info.get("path");
        String path = path1.getAbsolutePath();
        return new ConnectionAdapter(url, path);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)
            throws SQLException {
        DriverPropertyInfo propertyInfos[] = new DriverPropertyInfo[info.keySet().size()];
        Iterator<Object> itr = info.keySet().iterator();
        int counter = 0;
        while (itr.hasNext()) {
            String str = (String) itr.next();
            propertyInfos[counter++] = new DriverPropertyInfo(str, info.getProperty(str));
        }
        return propertyInfos;
    }

    private String parseUrl(String url) throws SQLException {
        Matcher urlMatcher = urlPattern.matcher(url);
        if (!urlMatcher.matches()) throw new SQLException(INVALID_URL);
        return urlMatcher.group(1);
    }
}
