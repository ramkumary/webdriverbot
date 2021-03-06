package com.github.webdriverbot.context;

import com.github.webdriverbot.pagefactory.BotPage;
import com.github.webdriverbot.exceptions.WebDriverBotException;

public class WebDriverBotContext {

    private static final InheritableThreadLocal<WebDriverBotData> DATA = new InheritableThreadLocal<>();

    public static void initBotData() {
        if (DATA.get() == null) {
            DATA.set(new WebDriverBotData());
        }
    }

    private static WebDriverBotData getData() {
        if (DATA.get() == null) {
            throw new WebDriverBotException("WebDriverBotData in WebDriverBotContext is not set");
        }

        return DATA.get();
    }

    public static BotPage getPage(String handle) {
        return getData().getPageForHandle(handle);
    }

    public static <U extends BotPage> U getPage(String handle, Class<U> page) {
        return (U) getData().getPageForHandle(handle);
    }

    public static <U extends BotPage> U getPage(Class<U> pageClass) {
        return (U) getData().getUniquePage(pageClass);
    }

    public static void setPage(String handle, Class<? extends BotPage> pageClass) {
        getData().setPageForHandle(handle, pageClass);
    }

    public static void setBotProperty(String key, Object value) {
        getData().setProperty(key, value);
    }

    public static Object getBotProperty(String key) {
        return getData().getProperty(key);
    }

    public static <U> Object getBotProperty(String key, Class<U> propertyClass) {
        return getData().getProperty(key, propertyClass);
    }

    public static void removeBotProperty(String key) {
        getData().removeProperty(key);
    }
}
