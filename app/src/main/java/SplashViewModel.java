import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.czq.chinesepinyin.database.UserDatabase;

/**
 * @date 2020.3.27
 * @author czq
 */
public class SplashViewModel extends AndroidViewModel {

    private UserDatabase userDatabase;

    public SplashViewModel(@NonNull Application application) {
        super(application);
    }


}
