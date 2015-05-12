package zambelz.dev.common.utilities;

import java.io.File;

import android.content.Context;

public class ZafFileCache {

	private File cacheDir;

	public ZafFileCache(Context context, String dir) {
		// Find the dir to save cached images
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), dir);
		} else {
			cacheDir = context.getCacheDir();
		}
		
		if (!cacheDir.exists()) {
			cacheDir.mkdirs();
		}
	}

	public File getFile(String url) {
		// identify images by hashcode. Not a perfect solution, good for the demo.
		String filename = String.valueOf(url.hashCode());
		
		// Another possible solution (thanks to grantland)
		// String filename = URLEncoder.encode(url);
		File file = new File(cacheDir, filename);
		
		return file;

	}

	public void clear() {
		File[] files = cacheDir.listFiles();
		
		if (files == null) {
			return;
		}
		
		for (File file : files) {
			file.delete();
		}
	}

}
