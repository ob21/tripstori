package com.colibri.tripstori.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.colibri.tripstori.adapters.InterestsListAdapter;

/**
 * Created by olivierbriand on 15/06/2015.
 */

public class VolleyManager {

    /** Internal instance variable. */
    private static VolleyManager sInstance;

    /** The request queue. */
    private RequestQueue mRequestQueue;

    /**
     * Volley image loader
     */
    private ImageLoader mImageLoader;

    /**
     * Image cache implementation
     */
    private ImageLoader.ImageCache mImageCache;

    private VolleyManager() {
        // no instances
    }

    /**
     * This is the initializer.
     * @param context Your application context.
     * @param cacheSize The size of your image cache.
     */
    public static void init(Context context, int cacheSize) {
        if (sInstance == null) {
            sInstance = new VolleyManager();
            sInstance.mRequestQueue = Volley.newRequestQueue(context);
            sInstance.mImageCache = new BitmapLruImageCache(cacheSize);
            sInstance.mImageLoader = new ImageLoader(VolleyManager.getRequestQueue(), sInstance.mImageCache);
        }
    }

    /**
     * Gets the image loader from the singleton.
     * @return The RequestQueue.
     * @throws java.lang.IllegalStateException This is thrown if init has not been called.
     */
    public static RequestQueue getRequestQueue() {
        if (sInstance == null) {
            throw new IllegalStateException("The VolleyManager must be initialized.");
        }
        return sInstance.mRequestQueue;
    }

    /**
     * Gets the image loader from the singleton.
     * @return The ImageLoader.
     * @throws java.lang.IllegalStateException This is thrown if init has not been called.
     */
    public static ImageLoader getImageLoader() {
        if (sInstance == null) {
            throw new IllegalStateException("The VolleyManager must be initialized.");
        }        return sInstance.mImageLoader;
    }

    public static VolleyManager getInstance() {
        return sInstance;
    }

}
