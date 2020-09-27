package com.rcdz.mykotlindemo.tools

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.module.AppGlideModule

/**
 * 后面可用GlideApp.with() 代替Glide.with()
 * @author:create by
 * 邮箱 983049539@qq.com
 */
@GlideModule
class MyGlideModule : AppGlideModule() {
    /**
     * 设置手机默认推荐缓存大小。MemorySizeCalculator类通过考虑设备给定的可用内存和屏幕大小想出合理的默认大小.
     * @param context
     * @param builder
     */
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val calculator = MemorySizeCalculator.Builder(context)
            .setMemoryCacheScreens(2f)
            .build()
        builder.setMemoryCache(LruResourceCache(calculator.memoryCacheSize.toLong()))
        //自定义缓存大小·
        val memoryCacheSizeBytes = 1024 * 1024 * 20 // 20mb
        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))
        //自定义内存大小
        val diskCacheSizeBytes = 1024 * 1024 * 100 // 100 MB
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes.toLong()))
        //指定路径
//        builder.setDiskCache(
//        new InternalCacheDiskCacheFactory(context, "cacheFolderName", diskCacheSizeBytes));
        super.applyOptions(context, builder)
    }

    /**
     * 设置清单解析，设置为false，避免添加相同的modules两次
     */
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
    /**
     * 基本用法
     * private void gildeAppUsed(){
     * GlideApp.with(this)
     * .load(URL1)
     * .placeholder(R.drawable.default_avatar)
     * .error(R.drawable.image_error)
     * .fallback(R.drawable.fallback_nodata)
     * .into(mImageView);
     * }
     *
     * 缓存.
     * private void diskCacheStrategyAll(){
     * GlideApp.with(this)
     * .asBitmap()
     * .load(URL)
     * .placeholder(R.drawable.default_avatar)
     * .error(R.drawable.image_error)
     * .fallback(R.drawable.fallback_nodata)
     * .diskCacheStrategy(DiskCacheStrategy.ALL)
     * .into(mImageView);
     * }
     * DiskCacheStrategy.NONE 不做磁盘缓存
     * DiskCacheStrategy.SOURCE 只缓存图像原图
     * DiskCacheStrategy.RESULT 只缓存加载后的图像，即处理后最终显示时的图像
     * DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为
     *
     * 只从缓存中读取，没有则失败
     * private void retrieveFromCache(){
     * GlideApp.with(this)
     * .asBitmap()
     * .load(URL)
     * .placeholder(R.drawable.default_avatar)
     * .error(R.drawable.image_error)
     * .fallback(R.drawable.fallback_nodata)
     * .onlyRetrieveFromCache(true)
     * .into(mImageView);
     * }
     *
     * 跳过缓存. 每次都从服务端获取最新.
     * private void skipCache(){
     * GlideApp.with(this)
     * .asBitmap()
     * .load(URL)
     * .diskCacheStrategy(DiskCacheStrategy.NONE)
     * .skipMemoryCache(true)
     * .into(mImageView);
     * 清除缓存
     * private void clearMemoryCache(){
     * // This method must be called on the main thread.
     * Glide.get(this).clearMemory();
     * }
     * 清除磁盘缓存
     * private void clearDiskCache(){
     *
     * new AsyncTask<Void></Void>,Void,Void>(){
     *
     * @Override
     * protected Void doInBackground(Void... params) {
     * // This method must be called on a background thread.
     * Glide.get(getApplicationContext()).clearDiskCache();
     * return null;
     * }
     * };
     */
}