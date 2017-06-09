package fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esri.arcgisruntime.sample.smarttobacco.R;

/**
 * @name LoginFragment
 * @Descripation 登陆的Fragment<br>
 * @author 禹慧军
 * @date 2014-10-25
 * @version 1.0
 */
public class LoginFragment extends Fragment {
	private Context context;

	public LoginFragment(Context context) {
		this.context = context;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.login, container,false);
	}

}
