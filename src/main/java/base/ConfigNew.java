package base;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

public final class ConfigNew {
    private static final Properties CONFIG_PROPERTIES;
    // допустимо передача окружения через прямой нижнее_подчеркивание, например: rtest_ios
    private static final String env = System.getProperty("env.name");

    static {
        final String[] envs = env.split("_");
        CONFIG_PROPERTIES = new Properties();
        try (InputStream mainInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/main.properties")) {
            CONFIG_PROPERTIES.load(mainInputStream);
            try (InputStream standInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(String.format("config/%s/stand.properties", envs[0]))) {
                CONFIG_PROPERTIES.load(standInputStream);
                if (envs.length > 1) {
                    InputStream configInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(String.format("config/%s/%s/config.properties", envs[0], envs[1]));
                    CONFIG_PROPERTIES.load(configInputStream);
                    configInputStream.close();
                }
            } catch (IOException e) {
//                CustomLogger.fail("Unable to load configuration file", e);
                throw new IllegalStateException("Не могу загрузить файл конфига", e);
            }
        } catch (IOException ex) {
//            CustomLogger.fail("Unable to load configuration main file", ex);
            throw new IllegalStateException("Не могу загрузить файл конфига", ex);
        }
    }

    public static String getParametrizedProperty(String property) {
        if (Pattern.compile("\\$\\{.+}").matcher(property).find()) {
            String s = property;
            Set<String> envs = new HashSet<>();
            while (s.lastIndexOf("$") != -1) {
                envs.add(s.substring(s.indexOf("{") + 1, s.indexOf("}")));
                s = s.substring(s.indexOf("}") + 1);
            }
            for (String env : envs) {
                try {
                    property = property.replace(String.format("${%s}", env), System.getenv(env));
                } catch (NullPointerException e) {
//                    CustomLogger.failWOSS(String.format("Переменная окружения %s не установлена", env));
                }
            }
        }
        return property;
    }
}
