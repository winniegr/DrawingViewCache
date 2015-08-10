package winnie.glide.example.com.drawingcache;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by winniegr on 8/10/15.
 */
public class DrawingCacheUtils {

    public static Bitmap getMagicDrawingCache(View view, boolean quick_cache) {
        Bitmap bitmap = (Bitmap) view.getTag(R.id.CACHE_BITMAP_KEY);
        Boolean dirty = (Boolean) view.getTag(R.id.CACHE_BITMAP_DIRTY_KEY);
        if (view.getWidth() == 0 && view.getHeight() == 0) {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        }
        int width = view.getWidth();
        int height = view.getHeight();
        if (bitmap == null || bitmap.getWidth() != width || bitmap.getHeight() != height) {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            view.setTag(R.id.CACHE_BITMAP_KEY, bitmap);
            dirty = true;
        }
        if (dirty == true || !quick_cache) {
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            view.setTag(R.id.CACHE_BITMAP_DIRTY_KEY, false);
        }
        return bitmap;
    }
}
