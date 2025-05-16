package ru.mirea.kuzmina.cryptoloader;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CryptoLoader extends AsyncTaskLoader<String> {
    private Bundle args;

    public CryptoLoader(@NonNull Context context, Bundle args) {
        super(context);
        this.args = args;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        byte[] encrypted = args.getByteArray("encrypted");
        byte[] keyBytes = args.getByteArray("key");
        SecretKey key = new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");
        return MainActivity.decryptMsg(encrypted, key);
    }
}