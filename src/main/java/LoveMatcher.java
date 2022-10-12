import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Formatter;
import java.util.List;
import java.util.Set;

public abstract class LoveMatcher {
    protected List<User> users;
    Type userType = new TypeToken<List<User>>() {}.getType();
    protected long matchMillis;

    protected LoveMatcher(InputStream is) {
        users = new Gson().fromJson(new InputStreamReader(is), userType);
    }

    public abstract Set<Match> match();

    public String getMetrics() {
        return new Formatter().format("%s - %sμs",this.getClass().getSimpleName(), matchMillis).toString();
    }
}
