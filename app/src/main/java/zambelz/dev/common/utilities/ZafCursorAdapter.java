package zambelz.dev.common.utilities;

import android.app.Activity;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ZafCursorAdapter extends SimpleCursorAdapter {
	
	private Activity activity;
	private ListView lv;
	private ListItemClickListener listener;
	
	public ZafCursorAdapter(Activity activity, ListView lv, int layout, Cursor csr, String[] columns, int[] items) {
		super(activity, layout, csr, columns, items, 0);
		this.activity = activity;
		this.lv = lv;
	}
	
	public void useContextMenu() {
		activity.registerForContextMenu(lv);
	}
	
	public void build() {
		lv.setAdapter(this);
	}
	
	public void OnListItemClick(ListItemClickListener listenerClick) {
		this.listener = listenerClick;
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				listener.onListItemClick(parent, view, position, id);
			}
				
		});
	}
	
	public interface ListItemClickListener {
		public void onListItemClick(AdapterView<?> parent, View view, int position, long id);
	}
	
}