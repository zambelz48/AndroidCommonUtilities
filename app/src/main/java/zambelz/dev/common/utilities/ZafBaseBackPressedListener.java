package zambelz.dev.common.utilities;

import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;

public class ZafBaseBackPressedListener implements ZafOnBackPressedListener {

	private final FragmentActivity fragmentActivity;
	
	public ZafBaseBackPressedListener(FragmentActivity fragmentActivity) {
		this.fragmentActivity = fragmentActivity;
	}
	
	@Override
	public void doBack() {
		fragmentActivity.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
	}
	
}
