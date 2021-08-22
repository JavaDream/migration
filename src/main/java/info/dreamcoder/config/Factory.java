package info.dreamcoder.config;

public class Factory {
    public static Config getConfig() {
        return new EnvConfig();
    }

    private Factory() {
        throw new IllegalStateException("Utility class");
    }
}
